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
@WebServlet(name = "DeleteLessonController", urlPatterns = {"/deleteLesson"})
public class DeleteLessonController extends BaseRequiredAuthorizationController {

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
            out.println("<title>Servlet DeleteLessonController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet DeleteLessonController at " + request.getContextPath() + "</h1>");
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
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
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
    protected void doAuthGet(HttpServletRequest request, HttpServletResponse response, Account acc) throws ServletException, IOException {
        LessonDAO ld = new LessonDAO();
        String lessonid = request.getParameter("lessonid");
        String chapterid = request.getParameter("chapterid");
        // lấy lesson_no của lesson sắp xóa
        int lessonNo = ld.getLessonNoByLessonId(lessonid);
        // lấy lesson_no lớn nhất của list lesson của chapter
        int maxLessonNo = ld.getCurrentMaxLessonNo(chapterid);
        // Nếu lessonNo bị xóa chính là max thì không cần giảm những lesson No khác
        // Nếu lessonNo bị xóa không phải là max thì giảm các lessonNo lớn hơn nó xuống 1 đơn vị
        if (lessonNo == maxLessonNo) {
            ld.deleteLesson(lessonid, lessonNo, chapterid, false);
        } else {
            ld.deleteLesson(lessonid, lessonNo, chapterid, true);
        }
        System.out.println(lessonid + "---" + lessonNo + "---" + chapterid + "----" + maxLessonNo);
        response.sendRedirect("manageLesson?chapterid=" + chapterid + "&mess=A lesson is deleteded successfully");
    }

    @Override
    protected void doAuthPost(HttpServletRequest request, HttpServletResponse response, Account acc) throws ServletException, IOException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
