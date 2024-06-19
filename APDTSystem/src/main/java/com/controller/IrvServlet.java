package com.controller;

import com.DAO.IrvDAO;
import com.model.Irv;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "IrvServlet", urlPatterns = {"/irv/*"})
public class IrvServlet extends HttpServlet {

    private IrvDAO irvDAO;

    @Override
    public void init() {
        irvDAO = new IrvDAO();
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
                case "/new4":
                    showNewForm(request, response);
                    break;
                case "/insert":
                    insertIrv(request, response);
                    break;
                case "/delete":
                    deleteIrv(request, response);
                    break;
                case "/edit":
                    showEditForm(request, response);
                    break;
                case "/update":
                    updateIrv(request, response);
                    break;
                default:
                    listIrv(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void listIrv(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<Irv> listIrv = irvDAO.selectAllIrv();
        request.setAttribute("listIrv", listIrv);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/IrvList.jsp");
        dispatcher.forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/IrvForm.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int irvid = Integer.parseInt(request.getParameter("irvid"));
        Irv existingIrv = irvDAO.selectIrv(irvid);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/IrvForm.jsp");
        request.setAttribute("irv", existingIrv);
        dispatcher.forward(request, response);
    }

    private void insertIrv(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        String progcode = request.getParameter("progcode");
        String date = request.getParameter("date");
        String status = request.getParameter("status");
        int docid = Integer.parseInt(request.getParameter("docid"));
        String notes = request.getParameter("notes");
        Irv irv = new Irv(progcode, date, status, docid, notes);
        irvDAO.insertIrv(irv);
        response.sendRedirect(request.getContextPath() + "/IrvForm.jsp?success=true");
    }

    private void updateIrv(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        int irvid = Integer.parseInt(request.getParameter("irvid"));
        String progcode = request.getParameter("progcode");
        String date = request.getParameter("date");
        String status = request.getParameter("status");
        int docid = Integer.parseInt(request.getParameter("docid"));
        String notes = request.getParameter("notes");

        Irv irv = new Irv(irvid, progcode, date, status, docid, notes);
        irvDAO.updateIrv(irv);
        response.sendRedirect(request.getContextPath() + "/irv/listIrv?success=true");
    }

    private void deleteIrv(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int irvid = Integer.parseInt(request.getParameter("irvid"));
        irvDAO.deleteIrv(irvid);
        response.sendRedirect(request.getContextPath() + "/irv/listIrv");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
