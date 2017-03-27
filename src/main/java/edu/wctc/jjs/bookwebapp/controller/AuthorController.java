/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.jjs.bookwebapp.controller;

import edu.wctc.jjs.bookwebapp.model.AuthorDao;
import edu.wctc.jjs.bookwebapp.model.AuthorService;
import edu.wctc.jjs.bookwebapp.model.DbAccessor;
import edu.wctc.jjs.bookwebapp.model.IAuthorDao;
import edu.wctc.jjs.bookwebapp.model.MySqlDbAccessor;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Constructor;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
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
    
   
   
    private String driverClass;
    private String url;
    private String userName;
    private String password;
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
    
    
    
    
    
    
    
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        returnPage ="index.jsp";

        try {
            AuthorService service = injectDependenciesAndGetAuthorService();
            switch (request.getParameter(ACTION)) {
                case ACTION_AUTHOR_LIST:
                    returnPage =AUTHORS_PAGE;
                    listRefresh(request, service);
                    break;
                case ACTION_AUTHOR_ADD:
                   returnPage =AUTHORS_PAGE;
                    List values = new ArrayList<>();
                    values.add(request.getParameter(PRAM_AUTHOR_NAME));
                    service.addAuthor(DB_AUTHOR_TABLE, COL_NAMES, values);
                    listRefresh(request, service);
                    break;
                case ACTION_AUTHOR_EDIT_DEL:
                    String[] parameterValues = request.getParameterValues(PRAM_CHECK_BOX);
                    if (parameterValues == null) {
                        returnPage =AUTHORS_PAGE;
                    } else if (request.getParameter(PRAM_DEL) != null) {
                        returnPage =AUTHORS_PAGE;

                        for (String s : parameterValues) {

                            service.deleteAuthor(DB_AUTHOR_TABLE, DB_AUTHOR_ID, s);
                        }

                    } else {

                        returnPage =ADD_AUTHOR_PAGE;
                        request.setAttribute(DB_AUTHOR_TABLE, service.getSingleAuthor(DB_AUTHOR_TABLE, 50, DB_AUTHOR_ID, parameterValues[0]));
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
                        service.updateAuthor(DB_AUTHOR_TABLE, COL_NAMES, val, DB_AUTHOR_ID, request.getParameter(PRAM_AUTHOR_ID));
                    }
                    listRefresh(request, service);
                    break;
                case ACTION_AUTHOR_SEARCH:
                    returnPage =AUTHORS_PAGE;
             
                    request.setAttribute(ACTION_AUTHOR_LIST, service.getAuthorByName(DB_AUTHOR_TABLE, 50, DB_AUTHOR_NAME, request.getParameter("search")));
                      
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
    private AuthorService injectDependenciesAndGetAuthorService() throws Exception {
        // Use Liskov Substitution Principle and Java Reflection to
        // instantiate the chosen DbAccessor based on the class name retrieved
        // from web.xml
        Class dbClass = Class.forName(dbStrategyClassName);
        // Use Java reflection to instanntiate the DbAccessor object
        // Note that DbAccessor classes have no constructor params
        DbAccessor db = (DbAccessor) dbClass.newInstance();

        // Use Liskov Substitution Principle and Java Reflection to
        // instantiate the chosen DAO based on the class name retrieved above.
        // This one is trickier because the available DAO classes have
        // different constructor params
        IAuthorDao authorDao = null;
        Class daoClass = Class.forName(daoClassName);
        Constructor constructor = null;
        
        // This will only work for the non-pooled AuthorDao
        try {
            constructor = daoClass.getConstructor(new Class[]{
                DbAccessor.class, String.class, String.class, String.class, String.class
            });
        } catch(NoSuchMethodException nsme) {
            // do nothing, the exception means that there is no such constructor,
            // so code will continue executing below
        }

        // constructor will be null if using connectin pool dao because the
        // constructor has a different number and type of arguments
        
        if (constructor != null) {
            // conn pool NOT used so constructor has these arguments
            Object[] constructorArgs = new Object[]{
                db, driverClass, url, userName, password
            };
            authorDao = (IAuthorDao) constructor
                    .newInstance(constructorArgs);
        } else {
            /*
             Here's what the connection pool version looks like. First
             we lookup the JNDI name of the Glassfish connection pool
             and then we use Java Refletion to create the needed
             objects based on the servlet init params
             */
            Context ctx = new InitialContext();
            Context envCtx = (Context) ctx.lookup("java:comp/env");
            DataSource ds = (DataSource) envCtx.lookup(jndiName);
            constructor = daoClass.getConstructor(new Class[]{
                DataSource.class, DbAccessor.class
            });
            Object[] constructorArgs = new Object[]{
                ds, db
            };

            authorDao = (IAuthorDao) constructor
                    .newInstance(constructorArgs);
        }
        
        return new AuthorService(authorDao);
    }
    public void listRefresh(HttpServletRequest request, AuthorService service) throws ClassNotFoundException, SQLException {
        request.setAttribute(ACTION_AUTHOR_LIST, service.getAllAuthor(DB_AUTHOR_TABLE, 50));
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
        driverClass = getServletContext()
                .getInitParameter("db.driver.class");
        url = getServletContext()
                .getInitParameter("db.url");
        userName = getServletContext()
                .getInitParameter("db.username");
        password = getServletContext()
                .getInitParameter("db.password");

        dbStrategyClassName = getServletContext().getInitParameter("dbStrategy");
        daoClassName = getServletContext().getInitParameter("authorDao");
        jndiName = getServletContext().getInitParameter("connPoolName");
    }
}
