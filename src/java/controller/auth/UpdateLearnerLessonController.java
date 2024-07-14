/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.auth;

import dao.ChapterDAO;
import dao.CourseCertificateDAO;
import dao.CourseDAO;
import dao.LearnerChapterDAO;
import dao.LearnerCourseDAO;
import dao.LearnerLessonDAO;
import dao.LearnerSubjectDAO;
import dao.LessonDAO;
import dao.SubjectCertificateDAO;
import dao.SubjectDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import model.Account;

/**
 *
 * @author Admin
 */
@WebServlet(name = "UpdateLearnerLessonController", urlPatterns = {"/updatelearnerlesson"})
public class UpdateLearnerLessonController extends BaseRequiredAuthorizationController {

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
            out.println("<title>Servlet UpdateLearnerLessonController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UpdateLearnerLessonController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }
    private LearnerSubjectDAO lsd;
    private SubjectDAO sd;
    private CourseDAO cd;
    private ChapterDAO chd;
    private LessonDAO ld;
    private LearnerChapterDAO lchd;
    private LearnerLessonDAO lld;
    private LearnerCourseDAO lcd;

    public void init() throws ServletException {
        // Khởi tạo các DAO chỉ một lần
        lsd = new LearnerSubjectDAO();
        sd = new SubjectDAO();
        cd = new CourseDAO();
        chd = new ChapterDAO();
        ld = new LessonDAO();
        lchd = new LearnerChapterDAO();
        lld = new LearnerLessonDAO();
        lcd = new LearnerCourseDAO();
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
//    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Account acc = (Account) request.getSession().getAttribute("account");

        // Get parameter
        String account_id = acc.getAccount_id() + "";
//        String id = request.getParameter("id");
        String subject_id = request.getParameter("subject_id");
        String course_id = request.getParameter("course_id");
        String lesson_id = request.getParameter("lesson_id");
        String chapter_id = request.getParameter("chapter_id");
        String message = "";
        // Update lesson status
        boolean checkupdate = lld.updateStatusLesson(account_id, lesson_id);
        if (checkupdate) {
            message = "Update successful";
            // Retrieve counts to avoid redundant queries
            int totalLessonsInChapter = ld.countAllLessonByChapter(chapter_id);
            int completedLessonsInChapter = lld.CountAllLearnerLessonComplex(account_id, chapter_id);

            if (totalLessonsInChapter == completedLessonsInChapter) {
                // Check and update chapter status if necessary
                if (lchd.CheckStatusLearnerChapter(account_id, chapter_id) == 1) {
                    lchd.updateStatusChapter(account_id, chapter_id);
                }

                // Retrieve counts for course if chapter status is updated
                int totalChaptersInCourse = chd.CountAllChapterByCourse(course_id);
                int completedChaptersInCourse = lchd.CountAllLearnerChapterComplex(account_id, course_id);

                if (totalChaptersInCourse == completedChaptersInCourse) {
                    // Check and update course status if necessary
                    if (lcd.CheckStatusLearnerCourse(account_id, course_id) == 1) {
                        lcd.updateStatusCourse(account_id, course_id);
                        int id_learner_course = lcd.selectIdByLerner_idAndCourse_id(account_id, course_id);
                        CourseCertificateDAO ccd = new CourseCertificateDAO();
                        ccd.addCourseCertificateD(id_learner_course + "", LocalDate.now().toString());
                    }

                    // Retrieve counts for subject if course status is updated
                    int totalCoursesInSubject = cd.CountAllCourseBySubject(subject_id);
                    int completedCoursesInSubject = lcd.CountAllCourseBySubject(account_id, subject_id);

                    if (totalCoursesInSubject == completedCoursesInSubject) {
                        // Check and update subject status if necessary
                        if (lsd.CheckStatusLearnerSubject(account_id, subject_id) == 1) {
                            lsd.updateStatusSubject(account_id, subject_id);
                            int id_learner_subject = lsd.selectIdByLearner_idAndSubject_id(account_id, subject_id);
                            SubjectCertificateDAO scd = new SubjectCertificateDAO();
                            scd.addSubjectCertificateD(id_learner_subject + "", LocalDate.now().toString());
                        }
                    }
                }
            }
        } else {
            message = "Update Fail";
        }
        request.getSession().setAttribute("message", message);
        response.sendRedirect("viewlesson?subject_id=" + subject_id + "&course_id=" + course_id + "&chapter_id=" + chapter_id + "&lesson_id=" + lesson_id);
    }
//
//    /** 
//     * Handles the HTTP <code>POST</code> method.
//     * @param request servlet request
//     * @param response servlet response
//     * @throws ServletException if a servlet-specific error occurs
//     * @throws IOException if an I/O error occurs
//     */
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response)
//    throws ServletException, IOException {
//        processRequest(request, response);
//    }

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
    protected void doAuthGet(HttpServletRequest request, HttpServletResponse response, Account acc) throws ServletException, IOException {
//        LessonDAO ld = new LessonDAO();
//        CourseDAO cd = new CourseDAO();
//        ChapterDAO chd = new ChapterDAO();
//        LearnerLessonDAO lld = new LearnerLessonDAO();
//        LearnerChapterDAO lchd = new LearnerChapterDAO();
//        LearnerCourseDAO lcd = new LearnerCourseDAO();
//        LearnerSubjectDAO lsd = new LearnerSubjectDAO();
//
//        // Get parameter
//        String account_id = acc.getAccount_id() + "";
////        String id = request.getParameter("id");
//        String subject_id = request.getParameter("subject_id");
//        String course_id = request.getParameter("course_id");
//        String lesson_id = request.getParameter("lesson_id");
//        String chapter_id = request.getParameter("chapter_id");
//        String message;
//
//        // Update lesson status
//        boolean updateLessonSuccess = lld.updateSatusLesson(account_id, lesson_id);
//        if (updateLessonSuccess) {
//            message = "Lesson updated successfully. ";
//            // Retrieve counts to avoid redundant queries
//            int totalLessonsInChapter = ld.CountAllLessonByChapter(chapter_id);
//            int completedLessonsInChapter = lld.SelectAllLearnerLessonComplex(account_id, chapter_id).size();
//
//            if (totalLessonsInChapter == completedLessonsInChapter) {
//                // Check and update chapter status if necessary
//                if (lchd.CheckStatusLearnerChapter(account_id, chapter_id) == 1) {
//                    lchd.updateStatusChapter(account_id, chapter_id);
//                }
//
//                // Retrieve counts for course if chapter status is updated
//                int totalChaptersInCourse = chd.CountAllChapterByCourse(course_id);
//                int completedChaptersInCourse = lchd.SelectAllLearnerChapterComplex(account_id, course_id).size();
//
//                if (totalChaptersInCourse == completedChaptersInCourse) {
//                    // Check and update course status if necessary
//                    if (lcd.CheckStatusLearnerCourse(account_id, course_id) == 1) {
//                        lcd.updateStatusCourse(account_id, course_id);
//                    }
//
//                    // Retrieve counts for subject if course status is updated
//                    int totalCoursesInSubject = cd.CountAllCourseBySubject(subject_id);
//                    int completedCoursesInSubject = lcd.CountAllCourseBySubject(account_id, subject_id);
//
//                    if (totalCoursesInSubject == completedCoursesInSubject) {
//                        // Check and update subject status if necessary
//                        if (lsd.CheckStatusLearnerSubject(account_id, subject_id) == 1) {
//                            lsd.updateStatusSubject(account_id, subject_id);
//                        }
//                    }
//                }
//            }
//        } else {
//            message = "Lesson update failed.";
//        }
//        request.setAttribute("message", message);
//        RequestDispatcher dispatcher = request.getRequestDispatcher("viewlesson");
//        dispatcher.forward(request, response);
    }

    @Override
    protected void doAuthPost(HttpServletRequest request, HttpServletResponse response, Account acc) throws ServletException, IOException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
