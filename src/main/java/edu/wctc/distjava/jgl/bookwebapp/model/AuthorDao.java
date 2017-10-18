package edu.wctc.distjava.jgl.bookwebapp.model;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

/**
 *
 * @author Rasika Nanayakkara
 */
public class AuthorDao implements IAuthorDao {

    private String driverClass;
    private String url;
    private String userName;
    private String password;
    private DataAccess db;
    private final String AUTHOR_TBL = "author";
    private final String AUTHOR_PK = "author_id";
    private final String AUTHOR_NAME = "author_name";
    private final String DATE_ADDED = "date_added";
    

    public AuthorDao (String driverClass, String url,
            String userName, String password,DataAccess db) {

        setDriverClass(driverClass);
        setUrl(url);
        setUserName(userName);
        setPassword(password);
        setDb(db);
    }

    @Override
    public int addAuthor(List<Object> colValues) throws SQLException, ClassNotFoundException {
        db.openConnection(driverClass, url, userName, password);        
        int recsAdded = db.createRecord(AUTHOR_TBL,Arrays.asList(AUTHOR_NAME,DATE_ADDED), colValues);
        db.closeConnection();
        return recsAdded;
    }

    @Override
    public int updateAuthorDetails(
            List<Object> colValues, Object pkValue) throws SQLException, ClassNotFoundException {
        db.openConnection(driverClass, url, userName, password);
        int recsUpdated = db.updateRecord(AUTHOR_TBL,Arrays.asList(AUTHOR_NAME,DATE_ADDED), colValues, AUTHOR_PK, pkValue);
        db.closeConnection();
        return recsUpdated;
    }

    @Override
    public final int removeAuthorById(Integer id) throws ClassNotFoundException, SQLException {
        if (id == null || id < 1) {
            throw new IllegalArgumentException("id must be a Integer greater than 0");
        }
        db.openConnection(driverClass, url, userName, password);
        int recsDeleted = db.deleteRecordById(AUTHOR_TBL, AUTHOR_PK, id);
        db.closeConnection();
        return recsDeleted;
    }
    @Override
    public final Map<String, Object> findAuthorById(Integer id) throws ClassNotFoundException, SQLException{
        Map<String, Object> record = null;
        db.openConnection(driverClass, url, userName, password);
        record = db.findAuthorById(AUTHOR_TBL, AUTHOR_PK, id);
        db.closeConnection();
        return record;
    }

    @Override
    public final List<Author> getListOfAuthors()
            throws SQLException, ClassNotFoundException {
        db.openConnection(driverClass, url, userName, password);
        List<Author> list = new Vector<>();
        List<Map<String, Object>> rawData
                = db.getAllRecords(AUTHOR_TBL, 0);

        Author author = null;

        for (Map<String, Object> rec : rawData) {
            author = new Author();

            Object objRecId = rec.get(AUTHOR_PK);
            Integer recId = objRecId == null
                    ? 0 : Integer.parseInt(objRecId.toString());
            author.setAuthorId(recId);

            Object objName = rec.get("author_name");
            String authorName = objName == null ? "" : objName.toString();
            author.setAuthorName(authorName);

            Object objRecAdded = rec.get("date_added");
            Date recAdded = objRecAdded == null ? null : (Date) objRecAdded;
            author.setDateAdded(recAdded);

            list.add(author);
        }
        db.closeConnection();
        return list;
    }

    public DataAccess getDb() {
        return db;
    }

    public void setDb(DataAccess db) {
        this.db = db;
    }

    public String getDriverClass() {
        return driverClass;
    }

    public void setDriverClass(String driverClass) {
        this.driverClass = driverClass;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        IAuthorDao dao = new AuthorDao(
                "com.mysql.jdbc.Driver",
                "jdbc:mysql://localhost:3306/book",
                "root", "admin",
                new MySqlDataAccess()
        );
        int recsAdded = dao.addAuthor(Arrays.asList("Tom", "2016-10-10"));
        int recsUpdated = dao.updateAuthorDetails(Arrays.asList("Sanketh", "2017-10-10"),55);
        int recsDeleted = dao.removeAuthorById(20);
        List<Author> list = dao.getListOfAuthors();

        for (Author a : list) {
            System.out.println(a.getAuthorId() + ", "
                    + a.getAuthorName() + ", " + a.getDateAdded() + "\n");
        }
        Map<String,Object> rec = dao.findAuthorById(100);
        System.out.println(" Records:" + rec.get("author_name")+" "+ rec.get("date_added"));
    }

}
