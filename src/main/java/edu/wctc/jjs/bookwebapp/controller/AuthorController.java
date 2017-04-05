/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.jjs.bookwebapp.controller;


import edu.wctc.jjs.bookwebapp.model.AuthorFacade;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.ejb.EJB;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

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
    
   
   
    
    private String dbStrategyClassName;
    private String daoClassName;
    private String jndiName;
    
    private final String AUTHORS_PAGE ="/authors.jsp";
    private final String ADD_AUTHOR_PAGE ="/updateAuthor.jsp";
    
    private final String ACTION = "action";
    private final String ACTION_AUTHOR_LIST = "authorList";
    private final String ACTION_AUTHOR_ADD = "authorAdd";
    private final String ACTION_AUTHOR_EDIT_DEL = "authorEditDel";
    private final String ACTION_AUTHOR_UPDATE = "authorUpdate";
    private final String ACTION_AUTHOR_SEARCH = "search";
    
    private final String DB_AUTHOR_TABLE = "author";
    private final String DB_AUTHOR_ID = "author_id";
    private final String DB_AUTHOR_NAME = "author_name";
    private final String DB_DATE_ADDED = "date_added";
    
    private final String PRAM_AUTHOR_NAME = "authorName";
    private final String PRAM_AUTHOR_ID = "authorId";
    private final String PRAM_DATE_ADDED = "dateAdded";
    private final String PRAM_CHECK_BOX = "optionsCheckbox";
    private final String PRAM_SUBMIT = "submit";
    private final String PRAM_DEL = "del";
    
    private final String ATT_ERR_MSG = "errMsg";
    
    
    private final List<String> COL_NAMES = new ArrayList<>(Arrays.asList(DB_AUTHOR_NAME, DB_DATE_ADDED));
    
     
    private String returnPage;
    
    
    @EJB
    private AuthorFacade service;
    
    
    
    
    
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        returnPage ="index.jsp";

        try {
            
            switch (request.getParameter(ACTION)) {
                case ACTION_AUTHOR_LIST:
                    returnPage =AUTHORS_PAGE;
                    listRefresh(request, service);
                    break;
                case ACTION_AUTHOR_ADD:
                   returnPage =AUTHORS_PAGE;
                    
                    service.addNew(request.getParameter(PRAM_AUTHOR_NAME));
                    listRefresh(request, service);
                    break;
                case ACTION_AUTHOR_EDIT_DEL:
                    String[] parameterValues = request.getParameterValues(PRAM_CHECK_BOX);
                    if (parameterValues == null) {
                        returnPage =AUTHORS_PAGE;
                    } else if (request.getParameter(PRAM_DEL) != null) {
                        returnPage =AUTHORS_PAGE;

                        for (String s : parameterValues) {

                            service.deleteById(s);
                        }

                    } else {

                        returnPage =ADD_AUTHOR_PAGE;
                        request.setAttribute(DB_AUTHOR_TABLE, service.find(Integer.parseInt(parameterValues[0])));
                        System.out.println(service.find(Integer.parseInt(parameterValues[0])).getBookSet());
                    }
                    
                    listRefresh(request, service);

                    break;
                case ACTION_AUTHOR_UPDATE:
                    returnPage =AUTHORS_PAGE;
                    if (request.getParameter(PRAM_SUBMIT) != null) {
                        List val = new ArrayList<>();
                        val.add(request.getParameter(PRAM_AUTHOR_NAME));
                        System.out.println(request.getParameter(PRAM_DATE_ADDED));
                        val.add(request.getParameter(PRAM_DATE_ADDED));
                        service.addOrUpdate(request.getParameter(PRAM_AUTHOR_ID),request.getParameter(PRAM_AUTHOR_NAME));
                    }
                    listRefresh(request, service);
                    break;
                case ACTION_AUTHOR_SEARCH:
                    returnPage =AUTHORS_PAGE;
             
                   // request.setAttribute(ACTION_AUTHOR_LIST, service.getAuthorByName(DB_AUTHOR_TABLE, 50, DB_AUTHOR_NAME, request.getParameter("search")));
                      
                    break;
                     case "autohrsForBook":
                    returnPage ="/addBook.jsp";
                    listRefresh(request, service);
                    
                      
                    break;
                default:
                    break;
            }

        } catch (Exception e) {
           returnPage=AUTHORS_PAGE;
            request.setAttribute(ATT_ERR_MSG, e.getMessage());
        }
                  RequestDispatcher view = getServletContext().getRequestDispatcher((response.encodeURL(returnPage)));
        view.forward(request, response);

    }
    

     /*
        This helper method just makes the code more modular and readable.
        It's single responsibility principle for a method.
    */
    
    public void listRefresh(HttpServletRequest request, AuthorFacade service) throws ClassNotFoundException, SQLException {
        request.setAttribute(ACTION_AUTHOR_LIST, service.findAll());
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

    @Override
    public void init() throws ServletException {
        
    }
}
