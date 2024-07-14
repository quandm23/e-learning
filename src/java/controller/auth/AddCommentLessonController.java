/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.auth;

import dao.CommentLessonDAO;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import model.Account;
import model.CommentLesson;

/**
 *
 * @author Admin
 */
public class AddCommentLessonController extends BaseRequiredAuthorizationController {

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
            out.println("<title>Servlet AddCommentLessonController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddCommentLessonController at " + request.getContextPath() + "</h1>");
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
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response)
//    throws ServletException, IOException {
//        processRequest(request, response);
//    } 
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String message = "";
        // Add
//           DAO
        CommentLessonDAO cl = new CommentLessonDAO();

        // Session
        Account account = (Account) request.getSession().getAttribute("account");
        String account_id = account.getAccount_id() + "";

//        Date
        LocalDateTime date = LocalDateTime.now();
        Date comment_date = Date.from(date.atZone(ZoneId.systemDefault()).toInstant());

        // Get input 
        String comment = request.getParameter("comment");
        String subject_id = request.getParameter("subject_id");
        String lesson_id = request.getParameter("lesson_id");
        String parent_comment_id = request.getParameter("comment_id");
        System.out.println(parent_comment_id);

        if (parent_comment_id == null || parent_comment_id.isEmpty()) {
            String course_id = request.getParameter("course_id");
            String chapter_id = request.getParameter("chapter_id");

            // Set input 
            CommentLesson commentLesson = new CommentLesson("", account_id, lesson_id, comment, comment_date, "", "");
            if (cl.insertCommentLesson(commentLesson)) {
                message = "Add successful";
            } else {
                message = "Add Fail";
            }
            request.getSession().setAttribute("message", message);
            response.sendRedirect("viewlesson?subject_id=" + subject_id + "&course_id=" + course_id + "&chapter_id=" + chapter_id + "&lesson_id=" + lesson_id);
        } else {
            CommentLesson commentLesson = new CommentLesson("", account_id, lesson_id, comment, comment_date, "", parent_comment_id);
            if (cl.insertCommentLesson(commentLesson)) {
                message = "Reply successful";
            } else {
                message = "Reply Fail";
            }
            request.getSession().setAttribute("message", message);
            response.sendRedirect("responemanagement?subject_id=" + subject_id);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    } // </editor-fold>

    @Override
    protected void doAuthGet(HttpServletRequest request, HttpServletResponse response, Account acc) throws ServletException, IOException {
    }

    @Override
    protected void doAuthPost(HttpServletRequest request, HttpServletResponse response, Account acc) throws ServletException, IOException {
    }

}
