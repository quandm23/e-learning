/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.auth;

import dao.AccountDAO;
import dao.ChapterDAO;
import dao.CommentLessonDAO;
import dao.CourseDAO;
import dao.LearnerCourseDAO;
import dao.LearnerSubjectDAO;
import dao.LessonDAO;
import dao.SubjectDAO;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import model.Account;
import model.Chapter;
import model.CommentLesson;
import model.Course;
import model.LearnerCourse;
import model.Lesson;
import model.Subject;

/**
 *
 * @author Admin
 */
public class ViewLessionController extends BaseRequiredAuthorizationController {

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
            out.println("<title>Servlet ViewLession</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ViewLession at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }
    private LearnerSubjectDAO lsd;
    private LearnerCourseDAO lcd;
    private AccountDAO ad;
    private SubjectDAO sd;
    private CourseDAO cd;
    private ChapterDAO chd;
    private LessonDAO ld;

    public void init() throws ServletException {
        // Khởi tạo các DAO chỉ một lần
        ad = new AccountDAO();
        sd = new SubjectDAO();
        cd = new CourseDAO();
        chd = new ChapterDAO();
        ld = new LessonDAO();
        lcd = new LearnerCourseDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Account acc = (Account) request.getSession().getAttribute("account");
        LearnerSubjectDAO lsd = new LearnerSubjectDAO();
        String account_id = acc.getAccount_id() + "";
        String subject_id = request.getParameter("subject_id");
        String course_id = request.getParameter("course_id");
        if (lsd.CheckStatusLearnerSubject(account_id, subject_id) == 0) {
            request.setAttribute("subject_id", subject_id);
            request.setAttribute("course_id", course_id);
            request.setAttribute("err", "You need to purchase the course in advance");
            RequestDispatcher dispatcher = request.getRequestDispatcher("course-details");
            dispatcher.forward(request, response);
        } else {

            // Get subject_id and course_id
            String chapter_id = request.getParameter("chapter_id");
            String lesson_id = request.getParameter("lesson_id");
            // Query 
            Subject subject = sd.selectNameAnDesSubject(subject_id);
            Course course = cd.selectCourseByCourse_id(course_id);
            Account account_lecturer = ad.selectTTAcountLecturer(subject_id);
            ArrayList<Chapter> data_chapter = chd.selectAllChapterAndStatusByCourse_id(course_id,account_id);
            ArrayList<Lesson> data_lesson = ld.selectAllLessonAndStatusByCourse_id(course_id,account_id);
            LearnerCourse learnercourse = lcd.selectStatus_idAndRateCourse(account_id, course_id);

            // Set Attribute
            request.setAttribute("subject_id", subject_id);
            request.setAttribute("course_id", course_id);
            request.setAttribute("subject", subject);
            request.setAttribute("course", course);
            request.setAttribute("account_lecturer", account_lecturer);
            request.setAttribute("data_chapter", data_chapter);
            request.setAttribute("data_lesson", data_lesson);
            request.setAttribute("learnercourse", learnercourse);

            if (lesson_id != null) {
                CommentLessonDAO cld = new CommentLessonDAO();
                Lesson lesson = ld.selectLessonByLesson_id(lesson_id);
                int page = 1;
                int recordsPerPage = 5;
                if (request.getParameter("page") != null) {
                    page = Integer.parseInt(request.getParameter("page"));
                }

                ArrayList<CommentLesson> datacommentlesson = cld.selectAllCommentLesson(lesson_id, recordsPerPage * (page - 1), recordsPerPage);
                int noOfRecords = cld.getNoOfCommentsByLesson(lesson_id);
                if (noOfRecords > 0) {
                    System.out.println(noOfRecords);
                    int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
                    System.out.println(noOfPages);
                    request.setAttribute("noOfPages", noOfPages);
                }
                request.setAttribute("datacommentlesson", datacommentlesson);
                request.setAttribute("page", page);
                request.setAttribute("lesson", lesson);
                request.setAttribute("chapter_id", chapter_id);
                request.setAttribute("lesson_id", lesson_id);
            }
            String message = (String) request.getSession().getAttribute("message");
            request.setAttribute("message", message);
            request.getSession().removeAttribute("message");
            request.getRequestDispatcher("viewlession.jsp").forward(request, response);
        }
    }

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
//    @Override
    protected void doAuthGet(HttpServletRequest request, HttpServletResponse response, Account acc) throws ServletException, IOException {

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

    @Override
    protected void doAuthPost(HttpServletRequest request, HttpServletResponse response, Account acc) throws ServletException, IOException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
