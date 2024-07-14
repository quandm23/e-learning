/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.common;

import dao.AccountDAO;
import dao.CourseDAO;
import dao.SubjectDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import model.Account;
import model.Course;
import model.Subject;

/**
 *
 * @author Admin
 */
public class SearchCourseController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet SearchCourseController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SearchCourseController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        CourseDAO cd = new CourseDAO();
        AccountDAO ad = new AccountDAO();
        SubjectDAO sd = new SubjectDAO();

        // select
        String search_course = request.getParameter("search_course");
        String subject_id = request.getParameter("subject_id");
        Subject subject = sd.selectNameAnDesSubject(subject_id);
        Account account = ad.selectTTAcountLecturer(subject_id);

        ArrayList<Course> data_course;
        //Check input
        if (search_course.isBlank()) {
            data_course = cd.selectAllCourseBySubject_id(subject_id);
        } else {
            data_course = cd.searchCourseByName(search_course);
        }

        //Check search isEmpty
        if (data_course.isEmpty()) {
            request.getRequestDispatcher("isEmpty.jsp").forward(request, response);
        }else{
        request.setAttribute("subject", subject);
        request.setAttribute("subject_id", subject_id);
        request.setAttribute("account", account);
        request.setAttribute("data_course", data_course);
        request.getRequestDispatcher("course-list.jsp").forward(request, response);
    }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
