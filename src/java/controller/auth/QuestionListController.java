/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.auth;

import com.google.gson.Gson;
import dao.CourseDAO;
import dao.QuestionDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.Account;
import model.Chapter;
import model.Course;
import model.Question;

/**
 *
 * @author slhoa
 */
@WebServlet(name = "QuestionListController", urlPatterns = {"/questionlist"})
public class QuestionListController extends BaseRequiredAuthorizationController {

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
            out.println("<title>Servlet QuestionListController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet QuestionListController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doAuthGet(HttpServletRequest request, HttpServletResponse response, Account acc) throws ServletException, IOException {
        String subjectId = request.getParameter("sid");
        request.setAttribute("sid", subjectId);
        QuestionDAO qd = new QuestionDAO();
        CourseDAO cd = new CourseDAO();
        List<Course> listCourse = cd.selectAllCourseBySubject_id(subjectId);
        List<Chapter> listChapter = qd.getChapterOfSubject(subjectId);

        request.setAttribute("listCourse", listCourse);
        request.setAttribute("listChapter", listChapter);

        request.getRequestDispatcher("managequestion.jsp").forward(request, response);
    }

    @Override
    protected void doAuthPost(HttpServletRequest request, HttpServletResponse response, Account acc) throws ServletException, IOException {
        String chapters = request.getParameter("checkedChapters");
        QuestionDAO qd = new QuestionDAO();
        System.out.println(chapters);
        List<Question> listQuestion = qd.getQuestionByChapterId(request.getParameter("sid"), chapters);

        Gson gson = new Gson();
        String jsonData = gson.toJson(listQuestion);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        response.getWriter().write(jsonData);
    }

}
