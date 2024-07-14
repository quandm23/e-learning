package controller.auth;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
import dao.ChapterDAO;
import dao.CourseDAO;
import dao.LearnerSubjectDAO;
import dao.SubjectDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import model.Account;
import model.Course;
import model.Subject;

/**
 *
 * @author Admin
 */
@WebServlet(name = "ManagementCourse", urlPatterns = {"/managementcourse"})
public class ManagementCourseController extends BaseRequiredAuthorizationController {

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
            out.println("<title>Servlet ManagementCourse</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ManagementCourse at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doAuthGet(HttpServletRequest request, HttpServletResponse response,
            Account acc) throws ServletException, IOException {
        // DAO instances
        SubjectDAO sd = new SubjectDAO();
        CourseDAO cd = new CourseDAO();
        LearnerSubjectDAO lsd = new LearnerSubjectDAO();

        // Parameters
        String subject_id = request.getParameter("subject_id");
        String action = request.getParameter("action");
        String course_id = request.getParameter("course_id");

        // Check learner subject status
        Boolean checkLearnerSubject = lsd.checkLearnerSubject(subject_id);
        String mess = "";
        if (action != null) {
            switch (action) {
                case "delete":
                    if (!checkLearnerSubject) {
                        cd.DeleteCourseByCourse_id(course_id);
                        mess = "Delete Course Succsefully";
                    }
                    break;
                case "Add":
                    String course_name = request.getParameter("course_name").trim();
                    String course_no = request.getParameter("course_no");
                    String description = request.getParameter("description").trim();
                    if (!checkLearnerSubject && !cd.CheckNameExits(course_name, subject_id)) {
                        cd.insertCourse(course_name, course_no, description, subject_id);
                        mess = "Add Course Succsefully";
                    } else {
                        request.setAttribute("err", "Course name already exists or cannot add course.");
                    }
                    break;
                case "Update":
                    course_name = request.getParameter("course_name").trim();
                    description = request.getParameter("description").trim();
                    if (!checkLearnerSubject && !cd.CheckNameExitsUpdate(course_name, subject_id, course_id)) {
                        mess = "Update Course Succsefully";
                        cd.update(course_name, description, course_id);
                    } else {
                        request.setAttribute("err", "Course name already exists or cannot update course.");
                    }
                    break;
            }
        }

        // Fetch subject and courses data
        Subject subject = sd.selectNameAnDesSubject(subject_id);
        ArrayList<Course> data_course = cd.selectAllCourseBySubject_id(subject_id);
        int maxcourseno = cd.SelectMaxCourseNoBySubject_id(subject_id);

        // Set attributes for the request
        request.setAttribute("subject_id", subject_id);
        request.setAttribute("mess", mess);
        request.setAttribute("maxcourseno", maxcourseno);
        request.setAttribute("subject", subject);
        request.setAttribute("checkLearnerSubject", checkLearnerSubject);
        request.setAttribute("data_course", data_course);

        // Forward to JSP
        request.getRequestDispatcher("managementcourse.jsp").forward(request, response);
    }

    @Override
    protected void doAuthPost(HttpServletRequest request, HttpServletResponse response,
            Account acc) throws ServletException, IOException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
