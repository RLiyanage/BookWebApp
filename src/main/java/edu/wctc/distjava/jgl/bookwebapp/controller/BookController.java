package edu.wctc.distjava.jgl.bookwebapp.controller;

import edu.wctc.distjava.jgl.bookwebapp.model.AuthorService;
import edu.wctc.distjava.jgl.bookwebapp.model.Book;
import edu.wctc.distjava.jgl.bookwebapp.model.BookService;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Rasika
 */
@WebServlet(name = "BookController", urlPatterns = {"/bookController"})
public class BookController extends HttpServlet {

    private static final String ACTION = "action";
    private static final String LIST_ACTION = "list";
    private static final String DELETE_BOOK = "delete";
    private static final String ADD_BOOK = "add";
    private static final String UPDATE_BOOK = "update";
    private static final String EDIT_BOOK = "edit";
    private static final String ACTION_SAVE = "Save";
    private static final String ACTION_INSERT = "insertData";
    @EJB
    private BookService bookService;

    @EJB
    private AuthorService authorService;

    @Override
    public void init() throws ServletException {

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

        String destination = "/bookList.jsp";
        String message;

        try {

            String action = request.getParameter(ACTION);
            String bookId = request.getParameter("bookId");
            String bookTitle = request.getParameter("title");
            String bookIsbn = request.getParameter("isbn");
            String authorName = request.getParameter("authorName");
            String authorId = request.getParameter("authorId");
            String buttonAction = request.getParameter("buttonAction");

            if (action.equalsIgnoreCase(LIST_ACTION)) {
                refreshList(bookService, request);

            } else if (action.equalsIgnoreCase(DELETE_BOOK)) {
                bookService.removeById(bookId);
                refreshList(bookService, request);

            } else if (action.equalsIgnoreCase(ADD_BOOK)) {
                request.setAttribute("authorList", authorService.getAuthorList());
                destination = "/addBook.jsp";

            } else if (action.equalsIgnoreCase(UPDATE_BOOK)) {
                if (buttonAction.equalsIgnoreCase(ACTION_SAVE)) {
                    bookService.updateBookDetails(bookId, bookTitle, bookIsbn, authorId);
                }
                refreshList(bookService, request);
                destination = "/bookList.jsp";

            } else if (action.equalsIgnoreCase(EDIT_BOOK)) {
                request.setAttribute("authorList", authorService.getAuthorList());
                Book bookRec = bookService.findBookById(bookId);
                request.setAttribute("bookRec", bookRec);
                destination = "/editBook.jsp";

            } else if (action.equalsIgnoreCase(ACTION_INSERT)) {
                if (buttonAction.equalsIgnoreCase(ACTION_SAVE)) {
                    bookService.addNewBook(bookTitle, bookIsbn, authorId);
                }
                refreshList(bookService, request);
                destination = "/bookList.jsp";
            }
        } catch (Exception e) {
            destination = "/bookList.jsp";
            request.setAttribute("errMessage", e.getMessage());
        }

        RequestDispatcher view = request.getRequestDispatcher(destination);
        view.forward(request, response);
    }

    private void refreshList(BookService bookService, HttpServletRequest request)
            throws ClassNotFoundException, SQLException, Exception {
        List<Book> bookList;
        //bookList = bookService.getBOOkList();
        bookList = bookService.findAll();
        request.setAttribute("bookList", bookList);
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
