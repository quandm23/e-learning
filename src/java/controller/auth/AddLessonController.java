/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.auth;

import dao.LessonDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Account;

/**
 *
 * @author ThaiGay
 */
@WebServlet(name = "AddLessonController", urlPatterns = {"/addLesson"})
public class AddLessonController extends BaseRequiredAuthorizationController {


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
        LessonDAO ld = new LessonDAO();
        String chapterId = request.getParameter("chapterid");
        request.setAttribute("chapterid", chapterId);
        int lessonNo = ld.getMaxLessonNo(chapterId);
        request.setAttribute("lesson_no", lessonNo);

        request.getRequestDispatcher("addLesson.jsp").forward(request, response);    }

    @Override
    protected void doAuthPost(HttpServletRequest request, HttpServletResponse response, Account acc) throws ServletException, IOException {
        LessonDAO ld = new LessonDAO();
        String lesson_no = request.getParameter("lesson_no");
        String lesson_name = request.getParameter("lesson_name");
        String video = request.getParameter("video");
        String document = request.getParameter("document");
        String description = request.getParameter("description");
        String chapterid = request.getParameter("chapterid");
        
        ld.addLesson(lesson_no, lesson_name, video, document, chapterid, description);

        response.sendRedirect("manageLesson?chapterid="+chapterid+"&mess=New lesson is added successfully");    }

}
