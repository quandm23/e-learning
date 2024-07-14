/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller.auth;

import dao.AccountDAO;
import dao.CourseDAO;
import dao.LearnerSubjectDAO;
import dao.SubjectCertificateDAO;
import dao.SubjectDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import model.Account;
import model.Course;
import model.Subject;
import model.SubjectCertificate;

/**
 *
 * @author Admin
 */
@WebServlet(name="CertificateSubjetcController", urlPatterns={"/certificatesubject"})
public class CertificateSubjetcController extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
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
            out.println("<title>Servlet CertificateSubjetcController</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CertificateSubjetcController at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        //        DAO
        AccountDAO ac = new AccountDAO();
        CourseDAO c = new CourseDAO();
        SubjectDAO s = new SubjectDAO();
        LearnerSubjectDAO lsd = new LearnerSubjectDAO();
        SubjectCertificateDAO scd = new SubjectCertificateDAO();
        
        String subject_id = request.getParameter("subject_id");
        Account acc = (Account) request.getSession().getAttribute("account");
        String account_id =  acc.getAccount_id()+"";
       
        if(lsd.CheckStatusLearnerSubject(account_id, subject_id) == 2){
        Account lecturer = ac.selectTTAcountLecturer(subject_id);
        Subject subject = s.selectNameAnDesSubject(subject_id);
        ArrayList<Course> data_course = c.selectAllCourseBySubject_id(subject_id);
        SubjectCertificate certificate = scd.selectSubjectCertificateBySubject_id(subject_id,account_id);
        
//        LearnerCourse learnercourse = lcd.
        request.setAttribute("lecturer", lecturer);
        request.setAttribute("subject", subject);
        request.setAttribute("certificate", certificate);
        request.setAttribute("data_course", data_course);
        request.getRequestDispatcher("certificate.jsp").forward(request, response);
        }else{
            request.getRequestDispatcher("isEmpty.jsp").forward(request, response);
        }
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
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
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
