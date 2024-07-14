/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.common;

import com.google.gson.Gson;
import dao.CategoryDAO;
import dao.RegistrationDAO;
import dao.SubjectDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.Account;
import model.Category;
import model.Subject;

/**
 *
 * @author slhoa
 */
@WebServlet(name = "SubjectListController", urlPatterns = {"/subjectlist"})
public class SubjectListController extends HttpServlet {

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
            out.println("<title>Servlet SubjectListController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SubjectListController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

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
        SubjectDAO sd = new SubjectDAO();
        CategoryDAO cd = new CategoryDAO();
        RegistrationDAO regDAO = new RegistrationDAO();
        

        List<Category> listCate = cd.getAllCategory();
        request.setAttribute("listCate", listCate);
        try {

            List<Subject> listSubject = sd.getSubjectWithLecture();
            List<Subject> listTop3 = sd.selectTop3SubjectCreate();
            if (listSubject.isEmpty()) {
                request.setAttribute("mess", "There are no courses available");
            } else {

                Account account = (Account) request.getSession().getAttribute("account");

                if (account != null) {
                    List<Integer> boughtSubjects = regDAO.getBoughtSubjectsByLearnerId(account.getAccount_id());
                    for (Subject subject : listTop3) {
                        if (boughtSubjects.contains(Integer.parseInt(subject.getSubject_id()))) {
                            subject.setBought(true); // Giả sử bạn đã có setter cho thuộc tính này
                        }
                    }
                }

                request.setAttribute("listSubject", listSubject);
                request.setAttribute("listTop3", listTop3);
                request.getRequestDispatcher("subjectlist.jsp").forward(request, response);
            }

        } catch (Exception e) {
        }

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

        String cate = request.getParameter("checkedCategory");
        String sreach = request.getParameter("txtSearch");
        String sort = request.getParameter("sort");
        SubjectDAO s = new SubjectDAO();
        RegistrationDAO regDAO = new RegistrationDAO();
        List<Subject> listFiu = s.filterSubject(cate, sreach, sort);

        // Lấy thông tin người dùng hiện tại từ session
        Account acc = (Account) request.getSession().getAttribute("account");

        if (acc != null) {
            // Lấy danh sách các khóa học đã mua bởi người dùng
            List<Integer> boughtSubjects = regDAO.getBoughtSubjectsByLearnerId(acc.getAccount_id());

            // Đánh dấu các khóa học đã được mua
            for (Subject subject : listFiu) {
                if (boughtSubjects.contains(Integer.parseInt(subject.getSubject_id()))) {
                    subject.setBought(true);
                }
            }
        }

        Gson gson = new Gson();
        String jsonData = gson.toJson(listFiu);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(jsonData);
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
