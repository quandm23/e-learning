package controller.common;

import dao.AccountDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Account;

@WebServlet(name = "VerifyController", urlPatterns = {"/verifyotp"})
public class VerifyController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");
        request.setAttribute("email", email);
        request.getRequestDispatcher("verifyotp.jsp").forward(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String otp = request.getParameter("otp");
        String email = request.getParameter("email");
        AccountDAO dbAccount = new AccountDAO();
        Account acc = dbAccount.findByEmail(email);
        if (acc == null) {
             request.setAttribute("msgVerify", "Email not register!");
            request.getRequestDispatcher("verifyotp.jsp").forward(request, response);
        } else {
            if (!acc.getOtp().equals(otp)) {
                request.setAttribute("msgVerify", "Your verify code is wrong. Please enter again.");
                request.setAttribute("email", email);
                request.getRequestDispatcher("verifyotp.jsp").forward(request, response);
            } else {
                response.sendRedirect("resetpassword?username=" + acc.getUsername());
                dbAccount.verify(acc.getUsername());
                request.getSession().setAttribute("account", acc);
                
            }
        }
    }
}

