///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
// */
//
//package controller.common;
//
//import dao.Subject1DAO;
//import java.io.IOException;
//import java.io.PrintWriter;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import java.util.List;
//import model.Category;
//import model.Subject;
//
///**
// *
// * @author admin
// */
//@WebServlet(name="SubjectByCategoryIdController", urlPatterns={"/subjectcategory"})
//
//public class SubjectByCategoryIdController extends HttpServlet {
//   
//    /** 
//     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
//     * @param request servlet request
//     * @param response servlet response
//     * @throws ServletException if a servlet-specific error occurs
//     * @throws IOException if an I/O error occurs
//     */
//    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
//    throws ServletException, IOException {
//        response.setContentType("text/html;charset=UTF-8");
//        try (PrintWriter out = response.getWriter()) {
//            /* TODO output your page here. You may use following sample code. */
//            out.println("<!DOCTYPE html>");
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet CourseByCategoryIdController</title>");  
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Servlet CourseByCategoryIdController at " + request.getContextPath () + "</h1>");
//            out.println("</body>");
//            out.println("</html>");
//        }
//    } 
//
//    /** 
//     * Handles the HTTP <code>GET</code> method.
//     * @param request servlet request
//     * @param response servlet response
//     * @throws ServletException if a servlet-specific error occurs
//     * @throws IOException if an I/O error occurs
//     */
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response)
//    throws ServletException, IOException {
//        //processRequest(request, response);
//        Subject1DAO s = new Subject1DAO();
//        String category_id_raw = request.getParameter("category_id");
//        
//        try{
//        int category_id = Integer.parseInt(category_id_raw);
//        String[] listPrice = {
//            "On Sale",
//            "Under $100",
//            "$100 - $500",
//            "Above $500"
//        };
//        int flag = 2;
//        request.setAttribute("flag",flag);
//        List<Category> listCY = s.getAllCategory();
//        List<Subject> listS = s.getAllSubjectByCtegoryId(category_id);
//        request.setAttribute("listPrice", listPrice);
//        request.setAttribute("category_id", category_id);
//        request.setAttribute("listCY", listCY);
//        request.setAttribute("listS", listS);
//        request.getRequestDispatcher("subjectlist.jsp").forward(request, response);
//        
//        } catch(Exception e){
//            
//        }
//        
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
//
//    /** 
//     * Returns a short description of the servlet.
//     * @return a String containing servlet description
//     */
//    @Override
//    public String getServletInfo() {
//        return "Short description";
//    }// </editor-fold>
//
//}
