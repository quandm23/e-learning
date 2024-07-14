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
import java.util.List;
import model.Account;
import model.Category;
import model.Subject;

/**
 *
 * @author slhoa
 */
@WebServlet(name = "EditSubjectController", urlPatterns = {"/editsubject"})
public class EditSubjectController extends BaseRequiredAuthorizationController {

    @Override
    protected void doAuthGet(HttpServletRequest request, HttpServletResponse response, Account acc) throws ServletException, IOException {
        // editsubject?sid=1
        SubjectDAO sd = new SubjectDAO();
        CategoryDAO cd = new CategoryDAO();
        List<Category> listCate = cd.getAllCategory();
        request.setAttribute("listCate", listCate);
        String subjectId = request.getParameter("sid");
        Subject subject = sd.getSubjectByID(subjectId);
        request.setAttribute("subject", subject);
        request.getRequestDispatcher("editsubject.jsp").forward(request, response);
    }

    @Override
    protected void doAuthPost(HttpServletRequest request, HttpServletResponse response, Account acc) throws ServletException, IOException {
        CategoryDAO cd = new CategoryDAO();
        List<Category> listCate = cd.getAllCategory();
        request.setAttribute("listCate", listCate);
        String sid = request.getParameter("subject_id");
        String updatedDate = java.time.LocalDate.now().toString();
        String name = request.getParameter("subjectname");
        String description = request.getParameter("description");
        String image = request.getParameter("image");
        String price = request.getParameter("price");
        String discount = request.getParameter("discount");
        String category = request.getParameter("category");
        // giu lai nhung gi thang nguoi dung nhap
        Subject subject_temp = new Subject();
        subject_temp.setSubject_id(sid);
        subject_temp.setSubject_name(name);
        subject_temp.setDescription(description);
        subject_temp.setImage(image);
        subject_temp.setCategory_id(category);
        String error = "";
        try {
            if (Double.parseDouble(price) < 0 || Double.parseDouble(price) > Double.MAX_VALUE) {
                throw new NumberFormatException();
            } else if (Double.parseDouble(discount) > 100 || Double.parseDouble(discount) < 0) {
                throw new NumberFormatException();
            } else {
                SubjectDAO s = new SubjectDAO();
                s.updateSubject(name, description, image, price, updatedDate, discount, category, sid);

                Subject updatedSubject = s.getSubjectByID(sid);
                request.setAttribute("subject", updatedSubject);

                request.setAttribute("mess", "Updated successfully !");
                request.getRequestDispatcher("editsubject.jsp").forward(request, response);
            }
        } catch (NumberFormatException e) {

            if (Double.parseDouble(price) < 0) {
                error += "\n PRICE";
            }
            if (Double.parseDouble(discount) > 100 || Double.parseDouble(discount) < 0) {
                error += "\n DISCOUNT";
            }
            if (Double.parseDouble(price) > 0) {
                error += "\n PRICE";
            }
            if (Double.parseDouble(discount) < 100 && Double.parseDouble(discount) > 0) {
                subject_temp.setDiscount(discount);
            }
            request.setAttribute("subject", subject_temp);
            request.setAttribute("mess", "Please check again " + error);
            request.getRequestDispatcher("editsubject.jsp").forward(request, response);
        }
    }

}
