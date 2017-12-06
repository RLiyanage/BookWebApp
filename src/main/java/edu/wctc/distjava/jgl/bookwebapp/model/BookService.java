package edu.wctc.distjava.jgl.bookwebapp.model;


import edu.wctc.distjava.jgl.bookwebapp.repository.AuthorRepository;
import edu.wctc.distjava.jgl.bookwebapp.repository.BookRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Rasika
 */

@Service
public class BookService  {
    
    @Autowired
    private BookRepository bookRepo;
    
    @Autowired
    private AuthorRepository authorRepo; 
    
    public List<Book> getBOOkList()
            throws Exception {
        return bookRepo.findAll();
    }

    public void removeById(String bookId) {
        Book book = new Book();
        book.setBookId(Integer.parseInt(bookId));
        bookRepo.delete(book);
    }

    public void addNewBook(String title, String isbn, String authorId) {              
        Author author = authorRepo.findOne( new Integer(authorId));
        Book book = new Book();
        book.setTitle(title);
        book.setIsbn(isbn);  
        book.setAuthor(author);
        bookRepo.save(book);

    }

    public void updateBookDetails(String id, String title, String isbn, String authorId) {
        int value = Integer.parseInt(id);
        Book book = bookRepo.findOne(value);
        book.setTitle(title);
        book.setIsbn(isbn);
        Author author =authorRepo.findOne( new Integer(authorId));
        book.setAuthor(author);
        bookRepo.save(book);

    }


    public Book findBookById(String Id) {
        Integer id = new Integer(Id);
        return bookRepo.findOne(id);

    }
}
