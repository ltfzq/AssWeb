/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.controller;

import com.DAO.Mqa02DAO;
import com.model.Mqa02;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Lenovo
 */
@WebServlet(name = "Mqa02Servlet", urlPatterns = {"/mqa02/*"})
public class Mqa02Servlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private Mqa02DAO mqa02DAO;
    
    @Override
    public void init(){
        mqa02DAO = new Mqa02DAO();
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
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
        String action = request.getServletPath();
        
        try{
            switch(action){
                case "/mqa02/new2":
                    showNewForm(request, response);
                    break;
                case "/mqa02/insert":
                    insertMqa02(request, response);
                    break;
                case "/mqa02/delete":
                    deleteMqa02(request, response);
                    break;
                case "/mqa02/edit":
                    showEditForm(request, response);
                    break;
                case "/mqa02/update":
                    updateMqa02(request, response);
                    break;
                default:
                    listMqa02(request, response);
                    break;
            }
        }catch(SQLException ex){
            throw new ServletException(ex);
        }
    }
    
    private void listMqa02(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException{
        List < Mqa02 > listMqa02 = mqa02DAO.selectAllMqa02();
        request.setAttribute("listMqa02", listMqa02);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/Mqa02List.jsp");
        dispatcher.forward(request, response);
    }
    
    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        RequestDispatcher dispatcher = request.getRequestDispatcher("/Mqa02Form.jsp");
        dispatcher.forward(request, response);
    }
    
    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException{
        int mqa02id = Integer.parseInt(request.getParameter("mqa02id"));
        Mqa02 existingMqa02 = mqa02DAO.selectMqa02(mqa02id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/Mqa02Form.jsp");
        request.setAttribute("mqa02", existingMqa02);
        dispatcher.forward(request, response);
    }
    
    private void insertMqa02(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException{
        String progcode = request.getParameter("progcode");
        int docid = Integer.parseInt(request.getParameter("docid"));
        String status = request.getParameter("status");
        String notes = request.getParameter("notes");
        Mqa02 mqa02 = new Mqa02(progcode, docid, status, notes);
        mqa02DAO.insertMqa02(mqa02);
        response.sendRedirect("/mqa02?success=true");
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
            response.sendRedirect("/mqa02?success=true");
        }
    
    private void deleteMqa02(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException{
        int mqa02id = Integer.parseInt(request.getParameter("mqa02id"));
        mqa02DAO.deleteMqa02(mqa02id);
        response.sendRedirect("/mqa02");
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
        doGet(request, response);
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
