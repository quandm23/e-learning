package utils;

import com.paypal.api.payments.Amount;
import com.paypal.api.payments.Details;
import com.paypal.api.payments.ItemList;
import com.paypal.api.payments.Item;
import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payer;
import com.paypal.api.payments.PayerInfo;
import com.paypal.api.payments.Payment;
import com.paypal.api.payments.PaymentExecution;
import com.paypal.api.payments.RedirectUrls;
import com.paypal.api.payments.Transaction;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;
import java.util.ArrayList;
import java.util.List;
import model.OrderDetail;

/**
 *
 * @author trungnh
 */
public class PaymentServices {
    private static final String CLIENT_ID="AZaBXf3y69B5LNOnNHQOOmSouyjdBZKk94_J_UaWCAuDLV2oxNtfaPJ8JVTc0bwS9OPZkHoOIJhd_9Qe";
    private static final String CLIENT_SECRET="EMFRNZzTp1WgfuSyTHrjRhHBdJJzwduoEpWnA5sYd_Z-PmYy-z8z8Rb8K9lU6rpm1HqOL-oAE-iC16_q";
    private static final String MODE = "sandbox";
    
    public String authorizePayment(OrderDetail orderDetail) throws PayPalRESTException{
        Payer payer = getPayerInformation();
        RedirectUrls redirectUrls = getRedirectURLs();
        
        List<Transaction> listTransactions = getTransactionInformation(orderDetail);
        Payment requestPayment = new Payment();
        requestPayment.setTransactions(listTransactions)
                .setRedirectUrls(redirectUrls)
                .setPayer(payer)
                .setIntent("authorize");
        
        APIContext apiContext = new APIContext(CLIENT_ID, CLIENT_SECRET, MODE);
        Payment approvedPayment= requestPayment.create(apiContext);
        
        return getApprovalLink(approvedPayment);
    }

    private String getApprovalLink(Payment approvedPayment){
        List<Links> links = approvedPayment.getLinks();
        String approvalLink = null;
        for (Links link : links){
            if(link.getRel().equalsIgnoreCase("approval_url")){
                approvalLink=link.getHref();
            }
        }
        return approvalLink;
    }
            
    private RedirectUrls getRedirectURLs() {
        RedirectUrls redirectUrls = new RedirectUrls();
        redirectUrls.setCancelUrl("http://localhost:8080/learnx/cart");
        redirectUrls.setReturnUrl("http://localhost:8080/learnx/review_payment");
        return redirectUrls;
    }
	private List<Transaction> getTransactionInformation(OrderDetail orderDetail) {  
		Details details = new Details();	
		details.setSubtotal(orderDetail.getSubtotal());

		Amount amount = new Amount();
		amount.setCurrency("USD");
		amount.setTotal(orderDetail.getTotal());
		amount.setDetails(details);

		Transaction transaction = new Transaction();
                
		transaction.setAmount(amount);
		
		ItemList itemList = new ItemList();
		List<Item> items = new ArrayList<>();
		
		Item item = new Item();
		item.setCurrency("USD");
		
		item.setPrice(orderDetail.getSubtotal());
		item.setQuantity("1");
		    
		items.add(item);
		itemList.setItems(items);
		transaction.setItemList(itemList);

		List<Transaction> listTransaction = new ArrayList<>();
		listTransaction.add(transaction);	
		
		return listTransaction;
	}
    public Payment getPaymentDetails(String paymentId) throws PayPalRESTException {
    APIContext apiContext = new APIContext(CLIENT_ID, CLIENT_SECRET, MODE);
    return Payment.get(apiContext, paymentId);
}
    
    private Payer getPayerInformation() {
        Payer payer = new Payer();
        payer.setPaymentMethod("paypal");
        
        PayerInfo payerInfo = new PayerInfo();
        payerInfo.setFirstName("?")
                .setLastName("?")
                .setEmail("?");
        
        payer.setPayerInfo(payerInfo);
        return payer;
    }
    public Payment executePayment(String paymentId, String payerId)
        throws PayPalRESTException {
    PaymentExecution paymentExecution = new PaymentExecution();
    paymentExecution.setPayerId(payerId);
 
    Payment payment = new Payment().setId(paymentId);
 
    APIContext apiContext = new APIContext(CLIENT_ID, CLIENT_SECRET, MODE);
 
    return payment.execute(apiContext, paymentExecution);
}
}
