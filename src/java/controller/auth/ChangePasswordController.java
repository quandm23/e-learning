package controller.auth;

import dao.AccountDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Account;

import utils.MD5Encryption;

/**
 *
 * @author Thai Nguyen
 */
@WebServlet(name = "ChangePasswordController", urlPatterns = "/changepassword")
public class ChangePasswordController extends BaseRequiredAuthorizationController {

    private static final String PASSWORD_REGEX = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%]).{8,20}";
// khai bao regex de dam bao password theo cac tieu chi

    @Override
    protected void doAuthGet(HttpServletRequest request, HttpServletResponse response, Account acc) throws ServletException, IOException {
        if (acc == null) { // neu khong co session thi nhay ve trang login
            response.sendRedirect("login");
        } else {
            request.getRequestDispatcher("changepassword.jsp").forward(request, response); // nhay vao trang change pass
        }
    }

    @Override
    protected void doAuthPost(HttpServletRequest request, HttpServletResponse response, Account acc) throws ServletException, IOException {
        // LAY account bang session
        Account account = (Account) request.getSession().getAttribute("account");
        String username = account.getUsername();//lay username tu account   
        String oldPass = request.getParameter("oldPassword");
        String newPass = request.getParameter("newPassword");
        String newPass_raw = newPass;
        String confirmPass = request.getParameter("confirmPassword");
        String curPass = acc.getPassword(); // lay password cua acc (acc lay tu session)
        // String curPass = request.getParameter("curPass");
        try {
            oldPass = new MD5Encryption().convertPassword(oldPass); // ma hoa oldPass
            newPass = new MD5Encryption().convertPassword(newPass); // ma hoa newPass
            confirmPass = new MD5Encryption().convertPassword(confirmPass);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(ChangePasswordController.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (!oldPass.equals(curPass)) {
            request.setAttribute("err", "Your old password is not correct"); // set thuoc tinh err
            request.getRequestDispatcher("changepassword.jsp").forward(request, response); // quay ve trang changepass
        } else if (newPass.equals(oldPass)) {
            request.setAttribute("err", "New password must be different from old password");
            request.getRequestDispatcher("changepassword.jsp").forward(request, response);
        } else if (!newPass.equals(confirmPass)) {
            request.setAttribute("err", "Confirm password is incorrect");
            request.getRequestDispatcher("changepassword.jsp").forward(request, response);
        } else if (!newPass_raw.matches(PASSWORD_REGEX)) { // bat regex cho newPass_raw
            request.setAttribute("err", "Your password must has at least 8 characters and contain uppercase, lowercase, number and specical syntax (!, @, #, $, %)");
            request.getRequestDispatcher("changepassword.jsp").forward(request, response);
        } else {
            AccountDAO ad = new AccountDAO(); // tao doi tuong AccountDao moi thao tac du liệu bằng account account trong databse
            ad.changePassword(username, newPass);
            request.getSession().invalidate(); // ket thuc session ( ko lưu account)
            request.setAttribute("err", "Your password has been changed successfully");
            request.getRequestDispatcher("changepassword.jsp").forward(request, response);
        }
    }

}


