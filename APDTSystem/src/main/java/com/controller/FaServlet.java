package com.controller;

import com.DAO.FaDAO;
import com.model.Fa;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "FaServlet", urlPatterns = {"/fa/*"})
public class FaServlet extends HttpServlet {

    private FaDAO faDAO;

    @Override
    public void init() {
        faDAO = new FaDAO();
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
                case "/new5":
                    showNewForm(request, response);
                    break;
                case "/insert":
                    insertFa(request, response);
                    break;
                case "/delete":
                    deleteFa(request, response);
                    break;
                case "/edit":
                    showEditForm(request, response);
                    break;
                case "/update":
                    updateFa(request, response);
                    break;
                default:
                    listFa(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void listFa(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<Fa> listFa = faDAO.selectAllFa();
        request.setAttribute("listFa", listFa);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/FaList.jsp");
        dispatcher.forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/FaForm.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int faid = Integer.parseInt(request.getParameter("faid"));
        Fa existingFa = faDAO.selectFa(faid);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/FaForm.jsp");
        request.setAttribute("fa", existingFa);
        dispatcher.forward(request, response);
    }

    private void insertFa(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        String progcode = request.getParameter("progcode");
        int mqa02id = Integer.parseInt(request.getParameter("mqa02id"));
        int appid = Integer.parseInt(request.getParameter("appid"));
        int irvid = Integer.parseInt(request.getParameter("irvid"));
        String status = request.getParameter("status");
        Fa fa = new Fa(progcode, mqa02id, appid, irvid, status);
        faDAO.insertFa(fa);
        response.sendRedirect(request.getContextPath() + "/Mqa02Form.jsp?success=true");
    }

    private void updateFa(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        int faid = Integer.parseInt(request.getParameter("faid"));
        String progcode = request.getParameter("progcode");
        int mqa02id = Integer.parseInt(request.getParameter("mqa02id"));
        int appid = Integer.parseInt(request.getParameter("appid"));
        int irvid = Integer.parseInt(request.getParameter("irvid"));
        String status = request.getParameter("status");

        Fa fa = new Fa(faid, progcode, mqa02id, appid, irvid, status);
        faDAO.updateFa(fa);
        response.sendRedirect(request.getContextPath() + "/fa/listFa?success=true");
    }

    private void deleteFa(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int faid = Integer.parseInt(request.getParameter("faid"));
        faDAO.deleteFa(faid);
        response.sendRedirect(request.getContextPath() + "/fa/listFa");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
