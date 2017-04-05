/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.jjs.bookwebapp.controller;

import edu.wctc.jjs.bookwebapp.model.AuthorFacade;
import edu.wctc.jjs.bookwebapp.model.BookFacade;
import edu.wctc.jjs.bookwebapp.model.BookFacade;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @book jstra
 */
public class BookController extends HttpServlet {

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

    private final String BOOKS_PAGE = "/books.jsp";
    private final String ADD_BOOK_PAGE = "/updateBook.jsp";

    private final String ACTION = "action";
    private final String ACTION_BOOK_LIST = "bookList";
    private final String ACTION_BOOK_ADD = "bookAdd";
    private final String ACTION_BOOK_EDIT_DEL = "bookEditDel";
    private final String ACTION_BOOK_UPDATE = "bookUpdate";
    private final String ACTION_BOOK_SEARCH = "search";

    private final String DB_BOOK_TABLE = "book";
    private final String DB_BOOK_ID = "book_id";
    private final String DB_BOOK_NAME = "book_name";
    private final String DB_DATE_ADDED = "date_added";

    private final String PRAM_BOOK_NAME = "bookName";
    private final String PRAM_BOOK_ID = "bookId";
    private final String PRAM_DATE_ADDED = "dateAdded";
    private final String PRAM_CHECK_BOX = "optionsCheckbox";
    private final String PRAM_SUBMIT = "submit";
    private final String PRAM_DEL = "del";

    private final String ATT_ERR_MSG = "errMsg";

    private final List<String> COL_NAMES = new ArrayList<>(Arrays.asList(DB_BOOK_NAME, DB_DATE_ADDED));

    private String returnPage;

    @EJB
    private BookFacade service;

     @EJB
    private AuthorFacade aService;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        returnPage = "index.jsp";
        try {
            switch (request.getParameter(ACTION)) {
                case ACTION_BOOK_LIST:
                    returnPage = BOOKS_PAGE;
                    listRefresh(request, service);
                    break;
                case "bookAdd":
                   returnPage ="/updateAuthor.jsp";
                   //int num = Integer.parseInt(request.getParameter("authorID"));
                    //request.setAttribute("author", aService.find(num));
                    
                    service.addNew(request.getParameter("bookTitle"),request.getParameter("isbm"),request.getParameter("authorId"));
                    listRefresh(request, service);
                    request.setAttribute("author", aService.findAuthor(request.getParameter("authorId")));
                    System.out.println(aService.findAuthor(request.getParameter("authorId")).getBookSet());
                    break;
                case ACTION_BOOK_EDIT_DEL:
                    String[] parameterValues = request.getParameterValues(PRAM_CHECK_BOX);
                    if(request.getParameter("add") != null){
                        returnPage = "/addBook.jsp";
                    request.setAttribute("authorId", request.getParameter("authorId"));
                    
                    }else if (parameterValues == null) {
                        returnPage ="/updateAuthor.jsp"; 
                        
                    } else if (request.getParameter(PRAM_DEL) != null) {
                        returnPage ="/updateAuthor.jsp";
                       
                        for (String s : parameterValues) {

                            service.deleteById(s);
                        }
                     
                    }else if(request.getParameter("edit") != null){ 

                        returnPage =ADD_BOOK_PAGE;
                        request.setAttribute(DB_BOOK_TABLE, service.find(Integer.parseInt(parameterValues[0])));
                    }
                     request.setAttribute("author", aService.findAuthor(request.getParameter("authorId")));
                    listRefresh(request, service);

                    break;
                    case "bookEdit":
                    returnPage ="/updateAuthor.jsp";
                    request.setAttribute("author", aService.findAuthor(request.getParameter("authorId")));
                    service.update(request.getParameter("bookTitle"), request.getParameter("isbn"), request.getParameter("bookId"),request.getParameter("authorId"));
                    break;
            }

        } catch (ClassNotFoundException | NumberFormatException | SQLException e) {
            returnPage = "/index.jsp";
            request.setAttribute(ATT_ERR_MSG, e.getMessage());
        }
        RequestDispatcher view = getServletContext().getRequestDispatcher((response.encodeURL(returnPage)));
        view.forward(request, response);

    }

    public void listRefresh(HttpServletRequest request, BookFacade service) throws ClassNotFoundException, SQLException {
        request.setAttribute(ACTION_BOOK_LIST, service.findAll());
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
