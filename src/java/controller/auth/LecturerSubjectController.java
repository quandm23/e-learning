/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.auth;

import dao.SubjectDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;

import model.Account;
import model.Subject;

/**
 *
 * @author HoangAnh
 */
@WebServlet(name = "LecturerSubjectController", urlPatterns = {"/lecturersubject"})
public class LecturerSubjectController extends BaseRequiredAuthorizationController {

    @Override
    protected void doAuthPost(HttpServletRequest request, HttpServletResponse response, Account acc) throws ServletException, IOException {
    }

    @Override
    protected void doAuthGet(HttpServletRequest request, HttpServletResponse response, Account acc) throws ServletException, IOException {
        //DAO
        SubjectDAO sd = new SubjectDAO();
        String account_id = acc.getAccount_id() + "";
        ArrayList<Subject> data_subject = sd.SelectAllLecturerSubject(account_id);

        //Check data is empty
        if (data_subject.isEmpty()) {
            request.getRequestDispatcher("isEmpty.jsp").forward(request, response);
        } else {
            request.setAttribute("data_subject", data_subject);
            request.getRequestDispatcher("lecturersubject.jsp").forward(request, response);
        }
    }

}
