package edu.wctc.distjava.jgl.bookwebapp.model;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.swing.text.DateFormatter;

/**
 *
 * @author jlombardo
 */
public class AuthorService {

    private IAuthorDao authorDao;
    private final String AUTHOR_TBL = "author";
    private final String AUTHOR_PK = "author_id";

    public AuthorService(IAuthorDao authorDao) {
        setAuthorDao(authorDao);
    }

    public final int addAuthor(List<Object> colValues) throws SQLException, ClassNotFoundException {
        int recsAdded = authorDao.addAuthor(colValues);
        return recsAdded;
    }

    public final int updateAuthorDetails(List<Object> colValues, Object pkValue)
            throws SQLException, ClassNotFoundException {
        int recsUpdated = authorDao.updateAuthorDetails(colValues, pkValue);
        return recsUpdated;
    }

    public final int removeAuthorById(String id)
            throws ClassNotFoundException, SQLException,
            NumberFormatException {

        if (id == null || id.equals("")) {
            throw new IllegalArgumentException("id must be a Integer greater than 0");
        }

        Integer value = Integer.parseInt(id);

        return authorDao.removeAuthorById(value);
    }

    public String getCurrentDate() {
        LocalDate date = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String dateFormatted = formatter.format(date);
        return dateFormatted;
    }

    public final Map<String, Object> findAuthorById(String Id) throws ClassNotFoundException, SQLException,
            NumberFormatException {
        int IdValue = Integer.parseInt(Id);
        Map<String, Object> rec = authorDao.findAuthorById(IdValue);
        return rec;
    }

    public final List<Author> getAuthorList()
            throws SQLException, ClassNotFoundException {

        return authorDao.getListOfAuthors();
    }

    public IAuthorDao getAuthorDao() {
        return authorDao;
    }

    public void setAuthorDao(IAuthorDao authorDao) {
        this.authorDao = authorDao;
    }

    public static void main(String[] args)
            throws SQLException, ClassNotFoundException {

        IAuthorDao dao = new AuthorDao(
                "com.mysql.jdbc.Driver",
                "jdbc:mysql://localhost:3306/book",
                "root", "admin",
                new MySqlDataAccess()
        );

        AuthorService authorService
                = new AuthorService(dao);

        List<Author> list = authorService.getAuthorList();

        for (Author a : list) {
            System.out.println(a.getAuthorId() + ", "
                    + a.getAuthorName() + ", " + a.getDateAdded() + "\n");
        }

        int recsDeleted = authorService.removeAuthorById("55");

        if (recsDeleted >= 1) {
            System.out.println("Record successfully deleted");
        } else {
            System.out.println("Record was not found. So, it was not deleted");
        }
        int recsAdded = authorService.addAuthor(Arrays.asList("Jedan", "2017/10/05"));
        int recsUpdated = authorService.updateAuthorDetails(Arrays.asList("Aidan", "2012-08-30"), 60);
        Map<String, Object> rec = authorService.findAuthorById("101");
        System.out.println("Records:" + " " + rec.get("author_name") + " " + rec.get("date_added"));
        System.out.println("Date: " + authorService.getCurrentDate());
    }
}
