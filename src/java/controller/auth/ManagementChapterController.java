/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.auth;

import dao.ChapterDAO;
import dao.CourseDAO;
import dao.LearnerSubjectDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import model.Account;
import model.Chapter;
import model.Course;

/**
 *
 * @author Admin
 */
public class ManagementChapterController extends BaseRequiredAuthorizationController {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ManagementChapterController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ManagementChapterController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doAuthGet(HttpServletRequest request, HttpServletResponse response, Account acc) throws ServletException, IOException {
        ChapterDAO chd = new ChapterDAO();
        CourseDAO cd = new CourseDAO();
        LearnerSubjectDAO lsd = new LearnerSubjectDAO();

        String subject_id = request.getParameter("subject_id");
        String course_id = request.getParameter("course_id");
        String mess = (String) request.getSession().getAttribute("mess");
        ArrayList<Chapter> data_chapter = chd.selectAllChapterByCourse_id(course_id);
        boolean checkLearnerSubject = lsd.checkLearnerSubject(subject_id);
        Course course = cd.selectCourseByCourse_id(course_id);

        request.setAttribute("subject_id", subject_id);
        request.setAttribute("mess", mess);
        request.getSession().removeAttribute("mess");
        request.setAttribute("course_id", course_id);
        request.setAttribute("data_chapter", data_chapter);
        request.setAttribute("course", course);
        request.setAttribute("checkLearnerSubject", checkLearnerSubject);
        request.getRequestDispatcher("managementchapter.jsp").forward(request, response);
    }

    @Override
    protected void doAuthPost(HttpServletRequest request, HttpServletResponse response, Account acc) throws ServletException, IOException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
