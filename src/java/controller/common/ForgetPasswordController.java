/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.common;

import dao.AccountDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import utils.EmailOtp;

@WebServlet(name = "ForgetPasswordController", urlPatterns = "/forgetpassword")
public class ForgetPasswordController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("forgetpassword.jsp").forward(request, response);
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");
        AccountDAO dbAccount = new AccountDAO();
        boolean checkEmail = dbAccount.checkEmail(email); // khai bao check email
        if (!checkEmail) {
            request.setAttribute("msg", "Your email is not registered");
            request.setAttribute("email", email);
            request.getRequestDispatcher("forgetpassword.jsp").forward(request, response);
        } else {
            String otp = EmailOtp.generateOTP();// khoi tao otp
            dbAccount.updateOTP(email, otp); 
            EmailOtp.sendEmail(email, "Online Learning - Forget Password", "Hello, Your OTP code is: " + otp);
            response.sendRedirect("verifyotp?email=" + email);
        }

    }

}
