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
import model.Lesson;

/**
 *
 * @author ThaiGay
 */
@WebServlet(name = "EditLessonController", urlPatterns = {"/editLesson"})
public class EditLessonController extends BaseRequiredAuthorizationController {

    @Override
    protected void doAuthGet(HttpServletRequest request, HttpServletResponse response, Account acc) throws ServletException, IOException {
        LessonDAO ld = new LessonDAO();
        String lessonid = request.getParameter("lessonid");
        Lesson lesson = ld.getLessonByLessonId(lessonid);
        if (lesson != null) {
            request.setAttribute("lesson", lesson);
        }
        request.getRequestDispatcher("editLesson.jsp").forward(request, response);
    }

    @Override
    protected void doAuthPost(HttpServletRequest request, HttpServletResponse response, Account acc) throws ServletException, IOException {
        LessonDAO ld = new LessonDAO();
        String lesson_no = request.getParameter("lesson_no");
        String lesson_name = request.getParameter("lesson_name");
        String video = request.getParameter("video");
        String document = request.getParameter("document");
        String description = request.getParameter("description");
        String lesson_id = request.getParameter("lessonid");
        ld.editLesson(lesson_id, lesson_name, video, document, description);
        Lesson lesson = ld.getLessonByLessonId(lesson_id);
        request.setAttribute("lesson", lesson);
        request.setAttribute("mess", "Lesson is updated successfully");
        request.getRequestDispatcher("editLesson.jsp").forward(request, response);
    }

}
