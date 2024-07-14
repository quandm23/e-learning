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
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.File;
import java.nio.file.Paths;
import model.Account;
import model.Blog;

/**
 *
 * @author ThaiGay
 */
@WebServlet(name = "EditBlogController", urlPatterns = {"/editblog"})
@MultipartConfig
public class EditBlogController extends BaseRequiredAuthorizationController {


    @Override
    protected void doAuthGet(HttpServletRequest request, HttpServletResponse response, Account acc) throws ServletException, IOException {
        BlogDAO bd = new BlogDAO();
        String blogId = request.getParameter("blogid");
        Blog blog = bd.getBlogByID(blogId);
        request.setAttribute("blog", blog);
        request.getRequestDispatcher("blogedit.jsp").forward(request, response);    }

    @Override
    protected void doAuthPost(HttpServletRequest request, HttpServletResponse response, Account acc) throws ServletException, IOException {
        BlogDAO bd = new BlogDAO();
        String blogid = request.getParameter("blogid");
        String title = request.getParameter("title");
        String content = request.getParameter("content");
        String description = request.getParameter("description");

        Blog blog = bd.getBlogByID(blogid);
        
        // Xử lý phần ảnh:
        String uploadFilePath = "C:\\Users\\ThaiGay\\Desktop\\e-learning-trungnh\\web\\img";
        // Lấy part của file
        Part filePart = request.getPart("image");
        // Nếu có thay đổi ảnh => xóa ảnh cũ và thay bằng ảnh mới
        if (filePart != null && filePart.getSize() > 0) {
            String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
            // Tạo ảnh mới
            filePart.write(uploadFilePath + File.separator + fileName);
            // Cập nhật blog với ảnh mới
            bd.editBlog(blogid, title, content, description, fileName);
            // Xóa ảnh cũ
            File imageToDelete = new File(uploadFilePath + blog.getImage());
            if(imageToDelete.exists() && imageToDelete.isFile()){
                imageToDelete.delete();
            }
        }else{
            // Giữ nguyên ảnh đang có => cho image = ""
            // Cập nhật blog nhưng không đổi ảnh
            bd.editBlog(blogid, title, content, description, "");
        }
        Blog updatedblog = bd.getBlogByID(blogid);
        request.setAttribute("blog", updatedblog);
        request.setAttribute("mess", "Edit successfully");
        request.getRequestDispatcher("blogedit.jsp").forward(request, response);    }

}
