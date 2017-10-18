package edu.wctc.distjava.jgl.bookwebapp.model;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Rasika
 */
public interface IAuthorDao {

   public abstract int removeAuthorById(Integer id) throws ClassNotFoundException, SQLException;
   public abstract Map<String, Object> findAuthorById(Integer id) throws ClassNotFoundException, SQLException;
   public abstract List<Author> getListOfAuthors() throws SQLException, ClassNotFoundException;
   public abstract int addAuthor(List<Object> colValues)throws SQLException, ClassNotFoundException;
   public abstract int updateAuthorDetails(List<Object> colValues,Object pkValue)throws SQLException, ClassNotFoundException;
}
