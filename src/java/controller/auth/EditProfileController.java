/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.auth;

import dao.AccountDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import model.Account;

/**
 *
 * @author DELL
 */
public class EditProfileController extends BaseRequiredAuthorizationController {





    @Override
    protected void doAuthGet(HttpServletRequest request, HttpServletResponse response, Account acc) throws ServletException, IOException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    protected void doAuthPost(HttpServletRequest request, HttpServletResponse response, Account acc) throws ServletException, IOException {
  String PHONE_REGEX = "^(\\+\\d{1,3}[- ]?)?\\d{10}$";
        boolean check = false;
        String error_phoneNumber = "";
        String error_dob = "";
        String error_fullname = "";

        //DAO
        AccountDAO ad = new AccountDAO();

        // Parameter
        String fullname = request.getParameter("fullname");
        String dob_raw = request.getParameter("birthday");
        String phone = request.getParameter("phone");

        // Kiem tra fullname day du
        if (fullname == null || fullname.isBlank()) {
            check = true;
            error_fullname = "Full name cannot be empty.";
        } else if (!fullname.matches("[a-zA-Z ]+")) {
            check = true;
            error_fullname = "Full name contains only letters and spaces.";
        } else if (fullname.length() < 2 || fullname.length() > 50) {
            check = true;
            error_fullname = "Full name must be between 2 and 50 characters.";
        }

        // Kiem tra phonenumber day du
        if (phone == null || phone.isBlank()) {
            check = true;
            error_phoneNumber = "Phone number cannot be empty.";
        } else if (!phone.matches(PHONE_REGEX)) {
            check = true;
            error_phoneNumber = "Invalid phone number format.";
        }

        // Kiem tra dob_raw
        LocalDate dob = null;
        if (dob_raw == null || dob_raw.isBlank()) {
            check = true;
error_dob = "Date of birth cannot be empty.";
        } else {
            try {
                dob = LocalDate.parse(dob_raw);
                if (Period.between(dob, LocalDate.now()).getYears() <= 16) {
                    check = true;
                    error_dob = "Date of birth must be over 16 years old.";
                }
            } catch (DateTimeParseException e) {
                check = true;
                error_dob = "Date of birth is in invalid format.";
            }

        }

        if (check) {
            request.setAttribute("error_phoneNumber", error_phoneNumber);
            request.setAttribute("error_dob", error_dob);
            request.setAttribute("error_fullname", error_fullname);
            request.getRequestDispatcher("EditProfile.jsp").forward(request, response);
        } else {
            //Sesson
            HttpSession session = request.getSession();
            // Kiểm tra sự thay đổi và cập nhật thông tin
            if (!fullname.equals(acc.getFullname()) || !dob_raw.equals(acc.getDob()) || !phone.equals(acc.getPhone())) {
                ad.updateProfile(fullname, dob_raw, phone, acc.getAccount_id());
                acc.setFullname(fullname);
                acc.setDob(dob_raw);
                acc.setPhone(phone);
                session.setAttribute("account", acc);
                request.setAttribute("success", "Profile updated successfully.");
            } else {
                request.setAttribute("success", "There are no changes to update.");
            }

            request.getRequestDispatcher("profile.jsp").forward(request, response);
        }    }

}
