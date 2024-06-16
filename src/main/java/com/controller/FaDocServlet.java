/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.controller;

import com.DAO.FaDocDAO;
import com.model.FaDoc;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Lenovo
 */
@WebServlet("/")
@MultipartConfig
public class FaDocServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private FaDocDAO fadocDAO;
    
    @Override
    public void init(){
        fadocDAO = new FaDocDAO();
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
                case "/new":
                    showNewForm(request, response);
                    break;
                case "/insert":
                    insertFaDoc(request, response);
                    break;
                case "/delete":
                    deleteFaDoc(request, response);
                    break;
                case "/edit":
                    showEditForm(request, response);
                    break;
                case "/update":
                    updateFaDoc(request, response);
                    break;
                default:
                    listFaDoc(request, response);
                    break;
            }
        }catch(SQLException ex){
            throw new ServletException(ex);
        }
    }
    
    private void listFaDoc(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException{
        List < FaDoc > listFaDoc = fadocDAO.selectAllFaDoc();
        request.setAttribute("listFaDoc", listFaDoc);
        RequestDispatcher dispatcher = request.getRequestDispatcher("FaDocList.jsp");
        dispatcher.forward(request, response);
    }
    
    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        RequestDispatcher dispatcher = request.getRequestDispatcher("FaDocForm.jsp");
        dispatcher.forward(request, response);
    }
    
    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException{
        int docid = Integer.parseInt(request.getParameter("docid"));
        FaDoc existingFaDoc = fadocDAO.selectFaDoc(docid);
        RequestDispatcher dispatcher = request.getRequestDispatcher("FaDocForm.jsp");
        request.setAttribute("fadoc", existingFaDoc);
        dispatcher.forward(request, response);
    }
    
    private void insertFaDoc(HttpServletRequest request, HttpServletResponse response)
        throws SQLException, IOException, ServletException {
    String progcode = request.getParameter("progcode");
    String docname = request.getParameter("docname");
    Part filePart = request.getPart("docfile"); // Retrieves <input type="file" name="docfile">
    InputStream fileInputStream = null;

    try {
        fileInputStream = filePart.getInputStream();
        byte[] fileBytes = fileInputStream.readAllBytes(); // Read the file content into a byte array

        String date = request.getParameter("date");
        
        FaDoc fadoc = new FaDoc(progcode, docname, fileBytes, date);
        fadocDAO.insertFaDoc(fadoc);
        response.sendRedirect("FaDocForm.jsp?success=true");
    } catch (IOException e) {
        throw new ServletException("File upload failed", e);
    } finally {
        if (fileInputStream != null) {
            try {
                fileInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

    
    private void updateFaDoc(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
            int docid = Integer.parseInt(request.getParameter("docid"));
            String progcode = request.getParameter("progcode");
            String docname = request.getParameter("docname");
            Part filePart = request.getPart("docfile"); // Get the file part

            byte[] fileBytes = null;
            if (filePart != null && filePart.getSize() > 0) {
                try (InputStream fileInputStream = filePart.getInputStream()) {
                    fileBytes = fileInputStream.readAllBytes();
                }
            }

            String date = request.getParameter("date");

            FaDoc fadoc = new FaDoc(docid, progcode, docname, fileBytes, date);
            fadocDAO.updateFaDoc(fadoc);
            response.sendRedirect("listfadoc?success=true");
        }
    
    private void deleteFaDoc(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException{
        int docid = Integer.parseInt(request.getParameter("docid"));
        fadocDAO.deleteFaDoc(docid);
        response.sendRedirect("listfadoc");
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
