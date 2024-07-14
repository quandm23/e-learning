package controller.auth;

import dao.RegistrationDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Account;

/**
 * Servlet implementation class EnrollFreeController
 */
@WebServlet(name = "EnrollFreeController", urlPatterns = {"/enrollment"})
public class EnrollFreeController extends BaseRequiredAuthorizationController {
    private static final long serialVersionUID = 1L;


    @Override
    protected void doAuthGet(HttpServletRequest request, HttpServletResponse response, Account acc) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("account");

        int subjectId = Integer.parseInt(request.getParameter("subject_id"));
        RegistrationDAO regDao = new RegistrationDAO();

        try {
            regDao.enrollLearnerSubject(account.getAccount_id(), subjectId);
            regDao.enrollLearnerCourse(account.getAccount_id(), subjectId);
            regDao.enrollLearnerChapter(account.getAccount_id(), subjectId);
            regDao.enrollLearnerLesson(account.getAccount_id(), subjectId);
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Enrollment failed!");
            request.getRequestDispatcher("error.jsp").forward(request, response);
            return;
        }

        request.setAttribute("message", "Enrollment successful!");
        request.getRequestDispatcher("success.jsp").forward(request, response);    }

    @Override
    protected void doAuthPost(HttpServletRequest request, HttpServletResponse response, Account acc) throws ServletException, IOException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
