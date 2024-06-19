package com.controller;

import com.DAO.AppDAO;
import com.model.App;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "AppServlet", urlPatterns = {"/app/*"})
public class AppServlet extends HttpServlet {

    private AppDAO appDAO;

    @Override
    public void init() {
        appDAO = new AppDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        handleRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        handleRequest(request, response);
    }

    private void handleRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getPathInfo();

        try {
            switch (action) {
                case "/new3":
                    showNewForm(request, response);
                    break;
                case "/insert":
                    insertApp(request, response);
                    break;
                case "/delete":
                    deleteApp(request, response);
                    break;
                case "/edit":
                    showEditForm(request, response);
                    break;
                case "/update":
                    updateApp(request, response);
                    break;
                default:
                    listApp(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void listApp(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<App> listApp = appDAO.selectAllApp();
        request.setAttribute("listApp", listApp);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/AppList.jsp");
        dispatcher.forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/AppForm.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int appid = Integer.parseInt(request.getParameter("appid"));
        App existingApp = appDAO.selectApp(appid);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/AppForm.jsp");
        request.setAttribute("app", existingApp);
        dispatcher.forward(request, response);
    }

    private void insertApp(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        String progcode = request.getParameter("progcode");
        String appname = request.getParameter("appname");
        String department = request.getParameter("department");
        String position = request.getParameter("position");
        App app = new App(progcode, appname, department, position);
        appDAO.insertApp(app);
        response.sendRedirect(request.getContextPath() + "/AppForm.jsp?success=true");
    }

    private void updateApp(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        int appid = Integer.parseInt(request.getParameter("appid"));
        String progcode = request.getParameter("progcode");
        String appname = request.getParameter("appname");
        String department = request.getParameter("department");
        String position = request.getParameter("position");

        App app = new App(appid, progcode, appname, department, position);
        appDAO.updateApp(app);
        response.sendRedirect(request.getContextPath() + "/app/listApp?success=true");
    }

    private void deleteApp(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int appid = Integer.parseInt(request.getParameter("appid"));
        appDAO.deleteApp(appid);
        response.sendRedirect(request.getContextPath() + "/appid/listApp");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
