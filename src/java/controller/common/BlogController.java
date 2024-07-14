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
import java.util.List;
import model.Blog;

/**
 *
 * @author hoanganh
 */
@WebServlet(name = "BlogController", urlPatterns = {"/blog"})
public class BlogController extends HttpServlet {

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
            out.println("<title>Servlet BlogController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet BlogController at " + request.getContextPath() + "</h1>");
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
        BlogDAO blogDAO = new BlogDAO();
//        List<Blog> listB = blogDAO.getAllBlog();// lấy danh sách tất cả các bài đăng blog từ DB
//        request.setAttribute("listBlog", listB);//Đặt danh sách bài đăng blog vào thuộc tính của yêu cầu để chuyển đến trang JSP. Thuộc tính này sẽ được sử dụng trong JSP để hiển thị dữ liệu bài đăng blog.
//        request.getRequestDispatcher("blog.jsp").forward(request, response);//Chuyển tiếp yêu cầu và phản hồi tới trang JSP "blog.jsp" để hiển thị danh sách bài đăng blog.

        int record_per_page = 3;//số bài đăng trong 1 trang
        String txtSearch = request.getParameter("searchTitle");//lưu trữ nội dung tìm kiếm nếu ko có trả về null
        if(txtSearch == null) txtSearch = "";
        request.setAttribute("searchTitle", txtSearch);
        int page = 1;//trang đầu tiên khi vào blog
        if (request.getParameter("p") != null) {// nếu p tồn tại thì chuyển đến trang đó ko có mặc định int =1
            page = Integer.parseInt(request.getParameter("p"));
        }
        List<Blog> listBlog = blogDAO.getBlogPaging(page, record_per_page, txtSearch);//list các bài đăng hiện tại , số bài đăng tìm kiếm nếu có
        int totalRecords = blogDAO.searchBlogByTitle(txtSearch).size();  // co tat ca bao nhieu blog thoa man
        int totalPages = (int) Math.ceil(totalRecords * 1.0 / record_per_page); // tính tôngh số trang cần thiết chứa các bài đăng 

        request.setAttribute("totalRecords", totalRecords);
        request.setAttribute("listBlog", listBlog);
        request.setAttribute("currentPage", page);
        request.setAttribute("totalPages", totalPages);
        
        // sau khi add thi lay mess tu addblogcontroller
        if(request.getParameter("mess") != null){// nếu có add thêm hiển thị dòng mess nếu ko có thì thôi
            request.setAttribute("mess", request.getParameter("mess"));
        }
        
        request.getRequestDispatcher("blog.jsp").forward(request, response);
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
//        BlogDAO blogDAO = new BlogDAO();// Tạo một đối tượng của lớp BlogDAO
//        String title = request.getParameter("searchTitle");//Lấy tham số "searchTitle" từ yêu cầu POST
//        List<Blog> listBlogSearch = blogDAO.searchBlogByTitle(title);//Gọi phương thức search từ đối tượng BlogDAO để tìm kiếm các bài đăng có tiêu đề chứa từ khóa được nhập từ người dùng.
//        request.setAttribute("search", title + "-" + listBlogSearch.size());//Đặt một thuộc tính "search" vào yêu cầu, bao gồm từ khóa tìm kiếm và số lượng bài đăng tìm được.
//
//        request.setAttribute("listBlog", listBlogSearch);//Đặt danh sách các bài đăng tìm được vào thuộc tính của yêu cầu.
//        request.getRequestDispatcher("blog.jsp").forward(request, response);//Chuyển tiếp yêu cầu và phản hồi tới trang JSP "blog.jsp" để hiển thị danh sách bài đăng tìm được và thông tin tìm kiếm

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
