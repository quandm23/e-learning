/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.auth;

import com.google.gson.Gson;
import dao.QuestionDAO;
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
 * @author slhoa
 */
@WebServlet(name = "AddQuestionController", urlPatterns = {"/addquestion"})
public class AddQuestionController extends BaseRequiredAuthorizationController {


    @Override
    protected void doAuthGet(HttpServletRequest request, HttpServletResponse response, Account acc) throws ServletException, IOException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    protected void doAuthPost(HttpServletRequest request, HttpServletResponse response, Account acc) throws ServletException, IOException {
        QuestionDAO qd = new QuestionDAO();
        String content = request.getParameter("content");
        String op1 = request.getParameter("option1");
        String op2 = request.getParameter("option2");
        String op3 = request.getParameter("option3");
        String op4 = request.getParameter("option4");
        String ans = request.getParameter("ans");
        String sid = request.getParameter("sid");
        String chapid = request.getParameter("chapterid");
        String level = request.getParameter("level");

        System.out.println(content + " --- " + op1 + " --- " + ans + " --- " + sid + " --- " + chapid + " --- " + level + " --- ");

        qd.addQuestion(content, op1, op2, op3, op4, ans, sid, chapid, level);
        Gson gson = new Gson();
        String jsonData = gson.toJson("New question is added successfully");

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        response.getWriter().write(jsonData);
    }

}
