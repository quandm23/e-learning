/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.auth;

import dao.BlogDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.File;
import java.nio.file.Paths;
import java.time.LocalDate;
import model.Account;

/**
 *
 * @author ThaiVD
 */
@WebServlet(name = "AddBlogController", urlPatterns = {"/addblog"})
@MultipartConfig
public class AddBlogController extends BaseRequiredAuthorizationController {

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
            out.println("<title>Servlet AddBlogController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddBlogController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    @Override
    protected void doAuthGet(HttpServletRequest request, HttpServletResponse response, Account acc) throws ServletException, IOException {
        request.getRequestDispatcher("Addblog.jsp").forward(request, response);//chuyển hướng ng dung đến trang Addblog.jsp
    }

    @Override
    protected void doAuthPost(HttpServletRequest request, HttpServletResponse response, Account acc) throws ServletException, IOException {
        BlogDAO bd = new BlogDAO();
        String title = request.getParameter("title");//Lấy các thông tin title gửi từ trình duyệt
        String description = request.getParameter("description");//Lấy các thông tin description gửi từ trình duyệt
        String content = request.getParameter("content");
        String created_date = LocalDate.now().toString();//biểu diễn ngày hiện tạ đăng
        String tag = request.getParameter("category");
        // dang fix cung co the sua doi
        String status = "1";
        String marketer_id = "4";
        
        
        // Xử lý phần ảnh: //C:\\Users\\ThaiGay\\Desktop\\e-learning-trungnh\\web\\img
        String uploadFilePath = "C:\\Users\\Admin\\Desktop\\LearnXgitlab\\LearnX\\web\\img";
        // Tạo folder img nếu chưa tồn tại
        File uploadDir = new File(uploadFilePath);
        if(!uploadDir.exists()){
            uploadDir.mkdirs();
        }
        // Lấy part của file
        Part filePart = request.getPart("image");
        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
        
        // Lưu file
        filePart.write(uploadFilePath + File.separator + fileName);
        
        
        bd.addBlog(title, content, description, created_date, status, marketer_id, tag, fileName);
        response.sendRedirect("blog?mess=NEW BLOG IS ADDED SUCCESSFULLY");// đăng bài thành công hiển thị thông báo 
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

}
