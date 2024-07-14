/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.auth;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import model.Account;


/**
 *
 * @author Trungnh
 */
public abstract class BaseAuthenticationController extends HttpServlet {

    public boolean isAuthenticated(HttpServletRequest request) {
        Account acc = (Account) request.getSession().getAttribute("account");
        return acc != null;
    }

    protected abstract void doGet(HttpServletRequest request, HttpServletResponse response, Account acc) throws ServletException, IOException;

    protected abstract void doPost(HttpServletRequest request, HttpServletResponse response, Account acc) throws ServletException, IOException;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (isAuthenticated(request)) {
            Account acc = (Account) request.getSession().getAttribute("account");
            doPost(request, response, acc);
        } else {
            request.getRequestDispatcher("Login.jsp").forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (isAuthenticated(request)) {
            Account acc = (Account) request.getSession().getAttribute("account");
            doGet(request, response, acc);
        } else {
            request.getRequestDispatcher("Login.jsp").forward(request, response);
        }
    }

}
