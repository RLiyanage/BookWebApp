package edu.wctc.distjava.jgl.bookwebapp.repository;


import edu.wctc.distjava.jgl.bookwebapp.model.Book;
import java.io.Serializable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Rasika
 */
@Repository
public interface BookRepository extends JpaRepository<Book, Integer>, Serializable {
    
}
