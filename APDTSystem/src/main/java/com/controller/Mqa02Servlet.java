package com.controller;

import com.DAO.Mqa02DAO;
import com.model.Mqa02;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "Mqa02Servlet", urlPatterns = {"/mqa02/*"})
public class Mqa02Servlet extends HttpServlet {

    private Mqa02DAO mqa02DAO;

    @Override
    public void init() {
        mqa02DAO = new Mqa02DAO();
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
                case "/new2":
                    showNewForm(request, response);
                    break;
                case "/insert":
                    insertMqa02(request, response);
                    break;
                case "/delete":
                    deleteMqa02(request, response);
                    break;
                case "/edit":
                    showEditForm(request, response);
                    break;
                case "/update":
                    updateMqa02(request, response);
                    break;
                default:
                    listMqa02(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void listMqa02(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<Mqa02> listMqa02 = mqa02DAO.selectAllMqa02();
        request.setAttribute("listMqa02", listMqa02);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/Mqa02List.jsp");
        dispatcher.forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/Mqa02Form.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int mqa02id = Integer.parseInt(request.getParameter("mqa02id"));
        Mqa02 existingMqa02 = mqa02DAO.selectMqa02(mqa02id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/Mqa02Form.jsp");
        request.setAttribute("mqa02", existingMqa02);
        dispatcher.forward(request, response);
    }

    private void insertMqa02(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        String progcode = request.getParameter("progcode");
        int docid = Integer.parseInt(request.getParameter("docid"));
        String status = request.getParameter("status");
        String notes = request.getParameter("notes");
        Mqa02 mqa02 = new Mqa02(progcode, docid, status, notes);
        mqa02DAO.insertMqa02(mqa02);
        response.sendRedirect(request.getContextPath() + "/Mqa02Form.jsp?success=true");
    }

    private void updateMqa02(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        int mqa02id = Integer.parseInt(request.getParameter("mqa02id"));
        String progcode = request.getParameter("progcode");
        int docid = Integer.parseInt(request.getParameter("docid"));
        String status = request.getParameter("status");
        String notes = request.getParameter("notes");

        Mqa02 mqa02 = new Mqa02(mqa02id, progcode, docid, status, notes);
        mqa02DAO.updateMqa02(mqa02);
        
        // Retrieve the updated list and set it as a request attribute
        List<Mqa02> listMqa02 = mqa02DAO.selectAllMqa02();
        request.setAttribute("listMqa02", listMqa02);

        // Forward to the list JSP page
        RequestDispatcher dispatcher = request.getRequestDispatcher("/Mqa02List.jsp?success=true");
        dispatcher.forward(request, response);
    }

    private void deleteMqa02(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int mqa02id = Integer.parseInt(request.getParameter("mqa02id"));
        mqa02DAO.deleteMqa02(mqa02id);
        response.sendRedirect(request.getContextPath() + "/mqa02");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
