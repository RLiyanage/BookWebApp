/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.distjava.jgl.bookwebapp.controller;

import edu.wctc.distjava.jgl.bookwebapp.model.Author;
import edu.wctc.distjava.jgl.bookwebapp.model.AuthorDao;
import edu.wctc.distjava.jgl.bookwebapp.model.AuthorService;
import edu.wctc.distjava.jgl.bookwebapp.model.IAuthorDao;
import edu.wctc.distjava.jgl.bookwebapp.model.MySqlDataAccess;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author jlombardo
 */
@WebServlet(name = "AuthorController", urlPatterns = {"/authorController"})
public class AuthorController extends HttpServlet {

    private static final String ACTION = "action";
    private static final String LIST_ACTION = "list";
    private static final String DELETE_AUTHOR = "delete";
    private static final String ADD_AUTHOR = "add";
    private static final String UPDATE_AUTHOR = "update";
    private static final String EDIT_AUTHOR = "edit";
    private static final String ACTION_SAVE = "Save";
    private static final String ACTION_INSERT = "insertData";
    private String driverClass;
    private String url;
    private String userName;
    private String password;

    
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
 }
    
    
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String destination = "/authorList.jsp"; // default
        String message;

        try {

            String action = request.getParameter(ACTION);
            String authorId = request.getParameter("authorId");
            String authorName = request.getParameter("authorName");
            String dateAdded = request.getParameter("dateAdded");           
            String buttonAction = request.getParameter("buttonAction");
            IAuthorDao dao = new AuthorDao(
//                    "com.mysql.jdbc.Driver",
//                    "jdbc:mysql://localhost:3306/book",
//                    "root", "admin",
                    driverClass,
                    url,
                    userName,
                    password,
                    new MySqlDataAccess()
            );

            AuthorService authorService = new AuthorService(dao);

            List<Author> authorList = null;

            if (action.equalsIgnoreCase(LIST_ACTION)) {
                refreshList(authorService, request);
            } else if (action.equalsIgnoreCase(DELETE_AUTHOR)) {
                authorService.removeAuthorById(authorId);                
                refreshList(authorService, request);

            } else if (action.equalsIgnoreCase(ADD_AUTHOR)) {
                String date = authorService.getCurrentDate();
                request.setAttribute("date_added", date);
                destination = "/addAuthor.jsp";

            } else if (action.equalsIgnoreCase(UPDATE_AUTHOR)) {
                if (buttonAction.equalsIgnoreCase(ACTION_SAVE)) {
                    
                 authorService.updateAuthorDetails(Arrays.asList(authorName, dateAdded), authorId);           
                }
                refreshList(authorService, request);
                destination = "/authorList.jsp";

            } else if (action.equalsIgnoreCase(EDIT_AUTHOR)) {
                Map<String, Object> authorRec = authorService.findAuthorById(authorId);
                request.setAttribute("authorRec", authorRec);
                destination = "/editAuthor.jsp";

            } else if (action.equalsIgnoreCase(ACTION_INSERT)) {
                if (buttonAction.equalsIgnoreCase(ACTION_SAVE)) {                    
                authorService.addAuthor(Arrays.asList((authorName), dateAdded));                    
                }
                refreshList(authorService, request);
                destination = "/authorList.jsp";
            }
        } catch (Exception e) {
            destination = "/authorList.jsp";
            request.setAttribute("errMessage", e.getMessage());
        }

        RequestDispatcher view = request.getRequestDispatcher(destination);
        view.forward(request, response);
    }

    private void refreshList(AuthorService authorService, HttpServletRequest request)
            throws ClassNotFoundException, SQLException {
        List<Author> authorList;
        authorList = authorService.getAuthorList();
        request.setAttribute("authorList", authorList);
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
