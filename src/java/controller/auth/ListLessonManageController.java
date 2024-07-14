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
import java.util.List;
import model.Account;
import model.Lesson;

/**
 *
 * @author ThaiGay
 */
@WebServlet(name = "ListLessonManageController", urlPatterns = {"/manageLesson"})
public class ListLessonManageController extends BaseRequiredAuthorizationController {


    @Override
    protected void doAuthGet(HttpServletRequest request, HttpServletResponse response, Account acc) throws ServletException, IOException {
        int records_per_page = 3;
        LessonDAO ld = new LessonDAO();
        String chapterId = request.getParameter("chapterid");
        request.setAttribute("chapterid", chapterId);
        String txtSearch = request.getParameter("txtSearch");
        if (txtSearch == null) {
            txtSearch = "";
        }
        request.setAttribute("txtSearch", txtSearch);
        int page = 1;
        if (request.getParameter("p") != null) {
            page = Integer.parseInt(request.getParameter("p"));
        }
        List<Lesson> listLesson = ld.getLessonByChapterId(chapterId, page, records_per_page, txtSearch);
        int totalRecords = ld.getNumberOfLessonByChapterId(chapterId, txtSearch);
        request.setAttribute("totalRecords", totalRecords);
        int totalPages = (int) Math.ceil(totalRecords * 1.0 / records_per_page);
        request.setAttribute("listLesson", listLesson);
        request.setAttribute("currentPage", page);
        request.setAttribute("totalPages", totalPages);

        // check mess sau khi add lesson
        if (request.getParameter("mess") != null) {
            request.setAttribute("mess", request.getParameter("mess"));
        }

        request.getRequestDispatcher("listLessonManage.jsp").forward(request, response);
    }

    @Override
    protected void doAuthPost(HttpServletRequest request, HttpServletResponse response, Account acc) throws ServletException, IOException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
