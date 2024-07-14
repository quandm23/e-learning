package utils;

import com.paypal.api.payments.PayerInfo;
import com.paypal.api.payments.Payment;
import com.paypal.api.payments.Transaction;
import com.paypal.base.rest.PayPalRESTException;
import dao.RegistrationDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Account;
import model.Cart;
import model.Item;
import model.OrderDetail;

/**
 *
 * @author trungnh
 */
@WebServlet(name = "ExecutePayment", urlPatterns = {"/execute_payment"})
public class ExecutePayment extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String paymentId = request.getParameter("paymentId");
        String payerId = request.getParameter("PayerID");
        String paymentDate = request.getParameter("paymentDate");
        String[] subject_name = request.getParameterValues("subject_name");
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("account");

        List<String> listS = new ArrayList<>();
        for (String s : subject_name) {
            listS.add(s);
        }
        request.setAttribute("subject_name", listS);
        request.setAttribute("receiptDate", paymentDate);
        try {
            PaymentServices paymentServices = new PaymentServices();
            Payment payment = paymentServices.executePayment(paymentId, payerId);
            PayerInfo payerInfo = payment.getPayer().getPayerInfo();
            Transaction transaction = payment.getTransactions().get(0);
            request.setAttribute("payer", payerInfo);
            request.setAttribute("transaction", transaction);

            // Xử lý ghi danh sau khi thanh toán thành công
            Cart cart = (Cart) session.getAttribute("cart");
            RegistrationDAO regDao = new RegistrationDAO();
            for (Item item : cart.getItems()) {
                int subjectId = item.getOrderSubject().getSubject_id();
                regDao.enrollLearnerSubject(account.getAccount_id(), subjectId);
                regDao.enrollLearnerCourse(account.getAccount_id(), subjectId);
                regDao.enrollLearnerChapter(account.getAccount_id(), subjectId);
                regDao.enrollLearnerLesson(account.getAccount_id(), subjectId);
            }
            
            // Xóa giỏ hàng sau khi thanh toán thành công
            session.removeAttribute("cart");
            session.setAttribute("size", 0);
            
            request.getRequestDispatcher("receipt.jsp").forward(request, response);
        } catch (PayPalRESTException ex) {
            request.setAttribute("errorMessage", ex.getMessage());
            ex.printStackTrace();
            request.getRequestDispatcher("error.jsp").forward(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(ExecutePayment.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
