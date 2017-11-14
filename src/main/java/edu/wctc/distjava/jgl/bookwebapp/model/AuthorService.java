package edu.wctc.distjava.jgl.bookwebapp.model;

import java.io.Serializable;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author Rasika
 */
@Stateless
public class AuthorService implements Serializable {

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

    public  int addAuthor(List<Object> colValues) throws SQLException, ClassNotFoundException {
        Author author = new Author();
        author.setAuthorName(colValues.get(0).toString());
        author.setDateAdded(new Date());
        getEm().persist(author );
        return 1;
    }
    public  int updateAuthorDetails(String id, String name)
            throws Exception {
      int result = 0;
      int value = Integer.parseInt(id);    
      Author author = getEm().find(Author.class, value);
      author.setAuthorName(name);      
      author = getEm().merge(author);
      if(author != null) result = 1;
      return result;
    }

    public int removeAuthorById(String id) throws Exception {          
        String jpql = "delete from Author a where a.authorId = :authorId";        
        Query q = getEm().createQuery(jpql);
        q.setParameter("authorId", new Integer(id));
        return q.executeUpdate();
    }
    
    

    public String getCurrentDate() {
        LocalDate date = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String dateFormatted = formatter.format(date);
        return dateFormatted;
    }

    public Author findAuthorById(String Id) {        
        String jpql = "select a from Author a where a.authorId =?1";
        TypedQuery<Author> q = getEm().createQuery(jpql, Author.class);
        q.setParameter(1, new Integer(Id));
        return q.getSingleResult();
    }

    public List<Author> getAuthorList()
            throws Exception {

        String jpql = " select a from Author a";
        TypedQuery<Author> q = getEm().createQuery(jpql, Author.class);
        q.setMaxResults(500);

        return q.getResultList();
    }

}
