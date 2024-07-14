/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.auth;

import dao.CategoryDAO;
import dao.SubjectDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import java.util.Set;
import model.Account;
import model.Category;

/**
 *
 * @author slhoa
 */
@WebServlet(name = "AddSubject", urlPatterns = {"/addsubject"})
public class AddSubjectController extends BaseRequiredAuthorizationController {

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
            out.println("<title>Servlet AddSubject</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddSubject at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    @Override
    protected void doAuthGet(HttpServletRequest request, HttpServletResponse response, Account acc) throws ServletException, IOException {
        CategoryDAO cd = new CategoryDAO();
        List<Category> listCate = cd.getAllCategory();
        request.setAttribute("listCate", listCate);
        request.getRequestDispatcher("addsubject.jsp").forward(request, response);
    }

    @Override
    protected void doAuthPost(HttpServletRequest request, HttpServletResponse response, Account acc) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Account accDangDangNhap = (Account) session.getAttribute("account");
        int acc_id = accDangDangNhap.getAccount_id();
        String createdDate = java.time.LocalDate.now().toString();
        String name = request.getParameter("subjectname");
        String description = request.getParameter("description");
        String image = request.getParameter("image");
        String price = request.getParameter("price");
        String discount = request.getParameter("discount");
        String category = request.getParameter("category");
        CategoryDAO cd = new CategoryDAO();
        List<Category> listCate = cd.getAllCategory();
        request.setAttribute("listCate", listCate);
        try {
            if (Integer.parseInt(price) < 0) {
                throw new NumberFormatException();

            } else if (Integer.parseInt(discount) > 100 || Integer.parseInt(discount) < 0) {
                throw new NumberFormatException();
            } else {
                SubjectDAO s = new SubjectDAO();
                s.addSubject(name, description, image, createdDate, price, discount, acc_id, category);
                request.setAttribute("mess", "A new subject added successfully !");
                request.getRequestDispatcher("addsubject.jsp").forward(request, response);
            }
        } catch (NumberFormatException e) {

            request.setAttribute("mess", "Please check again price and discount");
            request.getRequestDispatcher("addsubject.jsp").forward(request, response);
        }
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
