/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.common;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import dao.AccountDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Account;
import model.Constants;
import utils.UserGoogle;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.fluent.Form;
import org.apache.http.client.fluent.Request;
import utils.MD5Encryption;

/**
 *
 * @author Trungnh
 */
@WebServlet(name = "LoginController", urlPatterns = "/login")
public class LoginController extends HttpServlet {

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

    }

    // get token and get user info
    public static String getToken(String code) throws ClientProtocolException, IOException {
        // call api to get token
        String response = Request.Post(Constants.GOOGLE_LINK_GET_TOKEN)
                .bodyForm(Form.form().add("client_id", Constants.GOOGLE_CLIENT_ID)
                        .add("client_secret", Constants.GOOGLE_CLIENT_SECRET)
                        .add("redirect_uri", Constants.GOOGLE_REDIRECT_URI).add("code", code)
                        .add("grant_type", Constants.GOOGLE_GRANT_TYPE).build())
                .execute().returnContent().asString();
        JsonObject jobj = new Gson().fromJson(response, JsonObject.class);
        String accessToken = jobj.get("access_token").toString().replaceAll("\"", "");
        return accessToken;
    }

    // Retrieve user information from Google
    public static UserGoogle getUserInfo(final String accessToken) throws ClientProtocolException, IOException {
        String link = Constants.GOOGLE_LINK_GET_USER_INFO + accessToken;
        String response = Request.Get(link).execute().returnContent().asString();

        UserGoogle googlePojo = new Gson().fromJson(response, UserGoogle.class);

        return googlePojo;
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
        HttpSession session = request.getSession();
        if (session.getAttribute("account") != null) {
            request.getRequestDispatcher("home").forward(request, response);
        } else {
            request.getRequestDispatcher("Login.jsp").forward(request, response);
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
        AccountDAO ad = new AccountDAO();
        String username = request.getParameter("user");
        String password = request.getParameter("pass");
        String remember = request.getParameter("remember");
        
        manageCookies(response, username, password, remember);

        UserGoogle userG = null;
        if (username == null || username.isEmpty()) {
            String code = request.getParameter("code");
            String accessToken = getToken(code);
            userG = getUserInfo(accessToken);
        }
        if (userG != null) {
            handleGoogleLogin(request, response, ad, userG);
        } else {
            handleNormalLogin(request, response, ad, username, password);
        }
    }

    private void manageCookies(HttpServletResponse response, String username, String password, String remember) {
        Cookie cuser = new Cookie("cuser", username);
        Cookie cpass = new Cookie("cpass", password);
        Cookie crem = new Cookie("crem", remember);
        int maxAge = (remember != null) ? 3600 * 24 : 0;
        cuser.setMaxAge(maxAge);
        cpass.setMaxAge(maxAge);
        crem.setMaxAge(maxAge);
        response.addCookie(cuser);
        response.addCookie(cpass);
        response.addCookie(crem);
    }
    
        // Handle Google login
    private void handleGoogleLogin(HttpServletRequest request, HttpServletResponse response, AccountDAO ad, UserGoogle userG)
            throws IOException, ServletException {
        String email = userG.getEmail();
        String fullname = userG.getName();
        Account account = ad.findAccountByEmail(email);
        HttpSession session = request.getSession();
        if (account == null) {
                ad.addAccGoogle(fullname, email);
                session.setAttribute("account", account);
                session.setMaxInactiveInterval(1000);
                response.sendRedirect("home");
        }
        session.setAttribute("account", account);
        session.setMaxInactiveInterval(1000);
        request.getRequestDispatcher("home").forward(request, response);
    }

        // Handle normal login
    private void handleNormalLogin(HttpServletRequest request, HttpServletResponse response, AccountDAO ad, String username, String password)
            throws ServletException, IOException {
        try {
            password = new MD5Encryption().convertPassword(password);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Account a = ad.login(username, password);
        if (a == null) {
            request.setAttribute("mess", "Username or password is incorrect.");
            request.getRequestDispatcher("Login.jsp").forward(request, response);
                        } else if (a.getActive() == 2) {
                request.setAttribute("mess", "Your account has been BLOCKED");
                request.getRequestDispatcher("Login.jsp").forward(request, response);
        } else {
            HttpSession session = request.getSession();
            session.setAttribute("account", a);
            session.setMaxInactiveInterval(1000);
            response.sendRedirect("home");
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
