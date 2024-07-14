/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.auth;

import dao.ChapterDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Account;
import model.Chapter;

/**
 *
 * @author Admin
 */
@WebServlet(name = "UpdateChapterController", urlPatterns = {"/updatechapter"})
public class UpdateChapterController extends BaseRequiredAuthorizationController {

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
            out.println("<title>Servlet UpdateChapterController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UpdateChapterController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doAuthGet(HttpServletRequest request, HttpServletResponse response, Account acc) throws ServletException, IOException {
        ChapterDAO chd = new ChapterDAO();
        String subject_id = request.getParameter("subject_id");
        String course_id = request.getParameter("course_id");
        String chapter_id = request.getParameter("chapter_id");

        Chapter chapter = chd.selectChapterByChapter_id(chapter_id);

        request.setAttribute("subject_id", subject_id);
        request.setAttribute("course_id", course_id);
        request.setAttribute("chapter_id", chapter_id);
        request.setAttribute("chapter", chapter);
        request.getRequestDispatcher("updatechapter.jsp").forward(request, response);
    }

    @Override
    protected void doAuthPost(HttpServletRequest request, HttpServletResponse response, Account acc) throws ServletException, IOException {
        ChapterDAO chd = new ChapterDAO();
        String subject_id = request.getParameter("subject_id");
        String course_id = request.getParameter("course_id");
        String chapter_id = request.getParameter("chapter_id");
        String chapter_name = request.getParameter("chapter_name").trim();
        String chapter_no = request.getParameter("chapter_no");

        request.setAttribute("subject_id", subject_id);
        request.setAttribute("course_id", course_id);
        boolean checknameexits = chd.CheckNameExitsUpdate(course_id, chapter_name, chapter_id);
        if (!checknameexits) {
            String mess = "Update Chapter Succsefully";
            request.getSession().setAttribute("mess", mess);
            chd.updateChapter(chapter_name, chapter_id);
            response.sendRedirect("managementchapter?subject_id=" + subject_id + "&course_id=" + course_id);
        } else {
            String err = "Update Chapter Fail";
            request.setAttribute("err", err);
            Chapter chapter = chd.selectChapterByChapter_id(chapter_id);
            request.setAttribute("chapter", chapter);
            request.getRequestDispatcher("updatechapter.jsp").forward(request, response);
        }
    }

}
