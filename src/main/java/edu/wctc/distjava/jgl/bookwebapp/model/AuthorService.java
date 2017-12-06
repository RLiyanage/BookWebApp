
package edu.wctc.distjava.jgl.bookwebapp.model;

import edu.wctc.distjava.jgl.bookwebapp.repository.AuthorRepository;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Rasika
 */
@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepo; 

    
    public  void updateAuthorDetails(String id, String name)
            throws Exception {        
      Author author = findAuthorById(id);
      author.setAuthorName(name);      
       authorRepo.save(author);
      
    }
    public  void addAuthor(List<Object> colValues) throws SQLException, ClassNotFoundException {
        Author author = new Author();
        author.setAuthorName(colValues.get(0).toString());
        author.setDateAdded(new Date());
        authorRepo.save(author);
        
       
    }

    public void removeAuthorById(String id) throws Exception {        
         authorRepo.delete(findAuthorById(id));
    }
     public List<Author> getAuthorList()
            throws Exception { 
        return authorRepo.findAll();
    }
    public String getCurrentDate() {
        LocalDate date = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String dateFormatted = formatter.format(date);
        return dateFormatted;
    }
    public Author findAuthorById(String Id) {        
        return authorRepo.findOne(new Integer(Id));
    }

}
