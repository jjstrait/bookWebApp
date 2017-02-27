/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.jjs.bookwebapp.controller;

import edu.wctc.jjs.bookwebapp.model.AuthorDao;
import edu.wctc.jjs.bookwebapp.model.AuthorService;
import edu.wctc.jjs.bookwebapp.model.MySqlDbAccessor;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Joshua
 */
@WebServlet(name = "AuthorController", urlPatterns = {"/AuthorController"})
public class AuthorController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private final String TABLE_NAME = "author";
    private final List<String> COL_NAMES = new ArrayList<>(Arrays.asList("author_name", "date_added"));
    private AuthorService service = new AuthorService(
            new AuthorDao(
                    new MySqlDbAccessor(),
                    "com.mysql.jdbc.Driver",
                    "jdbc:mysql://localhost:3306/book",
                    "root", "admin"
            )
    );
    private final String AUTHORS = "authors.jsp";
    private final String AUTHOR_ID = "author_id";
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        RequestDispatcher view = request.getRequestDispatcher("index.jsp");

        try {

            switch (request.getParameter("action")) {
                case "authorList":
                    view = request.getRequestDispatcher(AUTHORS);
                    listRefresh(request);
                    break;
                case "authorAdd":
                    view = request.getRequestDispatcher(AUTHORS);
                    List values = new ArrayList<>();
                    values.add(request.getParameter("authorName"));
                    System.out.println(service.addAuthor(TABLE_NAME, COL_NAMES, values));
                    listRefresh(request);
                    break;
                case "authorEditDel":
                  String[] parameterValues = request.getParameterValues("optionsCheckbox");
                    if(parameterValues == null){
                        view = request.getRequestDispatcher(AUTHORS);
                    }else if (request.getParameter("del")!= null) {
                        view = request.getRequestDispatcher(AUTHORS);
                        
                        
                        for (String s : parameterValues) {

                            service.deleteAuthor(TABLE_NAME, AUTHOR_ID, s);
                        }
                         
                                
                                

                    }else {
                        
                    view = request.getRequestDispatcher("updateAuthor.jsp");
                    request.setAttribute("author", service.getSingleAuthor(TABLE_NAME, 50, AUTHOR_ID, parameterValues[0]));
                    }
                    listRefresh(request);

                    break;
                case "authorUpdate":
                    view = request.getRequestDispatcher(AUTHORS);
                    if (request.getParameter("submit")!= null) {
                    List val = new ArrayList<>();
                    val.add(request.getParameter("authorName"));
                    System.out.println(request.getParameter("dateAdded"));
                    val.add(request.getParameter("dateAdded"));  
                    service.updateAuthor(TABLE_NAME, COL_NAMES, val,AUTHOR_ID,request.getParameter("authorId"));
                    }
                    listRefresh(request);
                    break;
                case "search":
                    view = request.getRequestDispatcher("updateAuthor.jsp");
                    request.setAttribute("author", service.getSingleAuthor(TABLE_NAME, 50, AUTHOR_ID, request.getParameter("search")));
                    break;
                default:
                    break;
            }

        } catch (Exception e) {
            view = request.getRequestDispatcher(AUTHORS);
            request.setAttribute(" errMsg", e.getMessage());
        }

        view.forward(request, response);

    }

    public void listRefresh(HttpServletRequest request) throws ClassNotFoundException, SQLException {
        request.setAttribute("authorList", service.getAllAuthor("author", 50));
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
        processRequest(request, response);
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
        processRequest(request, response);
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
