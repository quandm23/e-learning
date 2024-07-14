
package controller.auth;

import com.google.gson.Gson;
import dao.AdminDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.Account;
import model.Role;

/**
 *
 * @author trungnh
 */
@WebServlet(name = "ManageAccountController", urlPatterns = {"/manageaccount"})
public class ManageAccountController extends BaseRequiredAuthorizationController {


    @Override
    protected void doAuthGet(HttpServletRequest request, HttpServletResponse response, Account acc) throws ServletException, IOException {
        AdminDAO dao = new AdminDAO();
        List<Role> listRole = dao.getAllRole();
        request.setAttribute("listR", listRole);
        request.getRequestDispatcher("manageaccount.jsp").forward(request, response);
    }

    @Override
    protected void doAuthPost(HttpServletRequest request, HttpServletResponse response, Account acc) throws ServletException, IOException {
        AdminDAO dao = new AdminDAO();

        String aid = request.getParameter("aid");
        String action = request.getParameter("action");
        String role_id = request.getParameter("role");
        System.out.println(aid);
        System.out.println(action);
        if (action.equals("block") && !aid.isEmpty()) {
            dao.blockAccount(aid);
        } else if (action.equals("active") && !aid.isEmpty()) {
            dao.activeAccount(aid);
        } else if (action.equals("edit") && !role_id.isEmpty()) {
            dao.editRole(aid, role_id);
        }

        String r = request.getParameter("roleCheck");
        if (r == null) {
            r = "";
        }
        String a = request.getParameter("activeCheck");
        String[] role = {};
        if (!r.isEmpty()) {
            role = r.split(",");
        }
        String[] active = {};
        if (!a.isEmpty()) {
            active = a.split(",");
        }

        List<Account> list = dao.getAccountToManage(role, active);
        Gson gson = new Gson();
        String listAccount = gson.toJson(list);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(listAccount);
    }

}
