/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller.common;

import dao.BlogDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Blog;

/**
 *
 * @author hoanganh
 */
@WebServlet(name="BlogDetailController", urlPatterns={"/blogdetail"})
public class BlogDetailController extends HttpServlet {
   
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
            out.println("<title>Servlet BlogDetailController</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet BlogDetailController at " + request.getContextPath () + "</h1>");
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
        BlogDAO bd = new BlogDAO(); //Tạo một đối tượng của lớp BlogDAO
        String id = request.getParameter("id");//Lấy tham số "id" bài đăng mà người dùng muốn xem chi tiết.
        Blog detail = bd.getBlogByID(id);//Gọi phương thức getBlogByID(id) từ đối tượng BlogDAO để lấy thông tin chi tiết của bài đăng dựa trên ID được cung cấp.
        if(detail != null){ //Kiểm tra xem liệu chi tiết bài đăng đã được tìm thấy hay không, nếu có thì xử lý
            request.setAttribute("detail", detail); //Đặt thông tin chi tiết của bài đăng vào thuộc tính "detail" của yêu cầu. Thông tin này sau đó sẽ được sử dụng trong trang JSP để hiển thị chi tiết bài đăng.
            request.getRequestDispatcher("blogdetail.jsp").forward(request, response);//Chuyển tiếp yêu cầu và phản hồi tới trang JSP "blogdetail.jsp" để hiển thị chi tiết của bài đăng.
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