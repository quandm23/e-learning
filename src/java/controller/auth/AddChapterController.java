/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.auth;

import dao.ChapterDAO;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Account;

/**
 *
 * @author Admin
 */
public class AddChapterController extends BaseRequiredAuthorizationController {

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
    }

    @Override
    protected void doAuthGet(HttpServletRequest request, HttpServletResponse response, Account acc) throws ServletException, IOException {
        ChapterDAO chd = new ChapterDAO();
        String subject_id = request.getParameter("subject_id");
        String course_id = request.getParameter("course_id");
        int maxchapterno = chd.SelectMaxChapterNoByCourse_id(course_id);
        request.setAttribute("maxchapterno", maxchapterno);
        request.setAttribute("subject_id", subject_id);
        request.setAttribute("course_id", course_id);
        request.getRequestDispatcher("addchapter.jsp").forward(request, response);
    }

    @Override
    protected void doAuthPost(HttpServletRequest request, HttpServletResponse response, Account acc) throws ServletException, IOException {
        ChapterDAO chd = new ChapterDAO();
        String subject_id = request.getParameter("subject_id");
        String course_id = request.getParameter("course_id");
        String chapter_no = request.getParameter("chapter_no");
        String chapter_name = request.getParameter("chapter_name").trim();
        boolean checknameexits = chd.CheckNameExits(course_id, chapter_name);
        if (!checknameexits) {
            System.out.println(checknameexits);
            chd.insertChapter(chapter_no, chapter_name, course_id);
            String mess = "Add Chapter Succsefully";
            request.getSession().setAttribute("mess", mess);
            response.sendRedirect("managementchapter?subject_id=" + subject_id + "&course_id=" + course_id);
        } else {
            String err = "Add Chapter Fail";
            int maxchapterno = chd.SelectMaxChapterNoByCourse_id(course_id);
            request.setAttribute("err", err);
            request.setAttribute("maxchapterno", maxchapterno);
            request.setAttribute("subject_id", subject_id);
            request.setAttribute("course_id", course_id);
            request.setAttribute("chapter_name", chapter_name);
            request.getRequestDispatcher("addchapter.jsp").forward(request, response);
        }
    }

}
