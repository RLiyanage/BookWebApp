package edu.wctc.distjava.jgl.bookwebapp.model;


import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Rasika
 */
@Stateless
public class BookService extends AbstractFacade<Book> {

    @PersistenceContext(unitName = "book_PU")
    private EntityManager em;

    @Override
    protected EntityManager getEm() {
        return em;
    }

    public BookService() {
        super(Book.class);
    }

    public List<Book> getBOOkList()
            throws Exception {
        return findAll();
    }

    public void removeById(String bookId) {
        Book book = new Book();
        book.setBookId(Integer.parseInt(bookId));
        remove(book);
    }

    public void addNewBook(String title, String isbn, String authorId) {
        Book book = new Book();
        book.setTitle(title);
        book.setIsbn(isbn);
        
        Author author = getEm().find(Author.class, new Integer(authorId));
        book.setAuthor(author);
        getEm().merge(book);

    }

    public void updateBookDetails(String id, String title, String isbn, String authorId) {
        int value = Integer.parseInt(id);
        Book book = getEm().find(Book.class, value);
        book.setTitle(title);
        book.setIsbn(isbn);
        Author author = getEm().find(Author.class, new Integer(authorId));
        book.setAuthor(author);
        getEm().merge(book);

    }


    public Book findBookById(String Id) {
//        Book book = new Book();
//        book.setBookId(Integer.parseInt(Id));
        Integer id = new Integer(Id);
        return findById(id);

    }
}
