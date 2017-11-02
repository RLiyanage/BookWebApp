package edu.wctc.distjava.jgl.bookwebapp.model;

import java.io.Serializable;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;


/**
 *
 * @author Rasika
 */
@Stateless
public class AuthorService implements Serializable{

    private final String AUTHOR_TBL = "author";
    private final String AUTHOR_PK = "author_id";
    @PersistenceContext(unitName = "book_PU")
    private EntityManager em;

    public AuthorService() {
    }
    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }


//    public final int addAuthor(List<Object> colValues) throws SQLException, ClassNotFoundException {
//
//        return 0;
//    }

//    public final int updateAuthorDetails(List<Object> colValues, Object pkValue)
//            throws SQLException, ClassNotFoundException {
//
//        return 0;
//    }
//
//    public final int removeAuthorById(String id)
//            throws ClassNotFoundException, SQLException,
//            NumberFormatException {
//        if (id == null || id.equals("")) {
//            throw new IllegalArgumentException("id must be a Integer greater than 0");
//        }
//        Integer value = Integer.parseInt(id);
//        return 0;
//    }
//    
//
//    public String getCurrentDate() {
//        LocalDate date = LocalDate.now();
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//        String dateFormatted = formatter.format(date);
//        return dateFormatted;
//    }
//
//    public final Map<String, Object> findAuthorById(String Id) throws ClassNotFoundException, SQLException,
//            NumberFormatException {
//        int IdValue = Integer.parseInt(Id);
//        return null;
//    }

    public final List<Author> getAuthorList()
            throws Exception{
        
        String jpql = " select a from Author a";
        TypedQuery<Author> q = getEm().createQuery(jpql, Author.class);
        q.setMaxResults(500);
        
        return q.getResultList();
    }

   
}
