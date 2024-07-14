/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.auth;

import dao.AccountDAO;
import dao.ChapterDAO;
import dao.CourseDAO;
import dao.LessonDAO;
import dao.SubjectDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import model.Account;
import model.Chapter;
import model.Course;
import model.Lesson;
import model.Subject;

/**
 *
 * @author Admin
 */
public class CourseDetailsController extends BaseRequiredAuthorizationController {

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
            out.println("<title>Servlet CourseDetailsController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CourseDetailsController at " + request.getContextPath() + "</h1>");
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
    protected void doAuthGet(HttpServletRequest request, HttpServletResponse response, Account acc) throws ServletException, IOException {
        AccountDAO ad = new AccountDAO();
        SubjectDAO sd = new SubjectDAO();
        CourseDAO cd = new CourseDAO();
        ChapterDAO chd = new ChapterDAO();
        LessonDAO ld = new LessonDAO();
        
        // Get subject_id and course_id
        String subject_id = request.getParameter("subject_id");
        String course_id = request.getParameter("course_id");
        String err = (String) request.getAttribute("err");
        
        // Query 
        Subject subject = sd.selectNameAnDesSubject(subject_id);
        Account account_lecturer = ad.selectTTAcountLecturer(subject_id);
        Course course = cd.selectCourseByCourse_id(course_id);
        ArrayList<Chapter> data_chapter = chd.selectAllChapterByCourse_id(course_id);
        ArrayList<Course> data_course = cd.selectAllCourseBySubject_idAndCourse_id(subject_id, course_id);
        ArrayList<Lesson> data_lesson = ld.selectAllLessonByCourse_id(course_id);

        
        // Set Attribute
        request.setAttribute("subject_id", subject_id);
        request.setAttribute("course_id", course_id);
        request.setAttribute("subject", subject);
        request.setAttribute("err", err);
        request.setAttribute("account_lecturer", account_lecturer);
        request.setAttribute("course", course);
        request.setAttribute("data_chapter", data_chapter);
        request.setAttribute("data_lesson", data_lesson);
        request.setAttribute("data_course", data_course);
        request.getRequestDispatcher("course-details.jsp").forward(request, response);

    }

    @Override
    protected void doAuthPost(HttpServletRequest request, HttpServletResponse response, Account acc) throws ServletException, IOException {
        processRequest(request, response);
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
