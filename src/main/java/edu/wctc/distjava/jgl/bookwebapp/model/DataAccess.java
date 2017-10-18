package edu.wctc.distjava.jgl.bookwebapp.model;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 *
 * @author jlombardo
 */
public interface DataAccess {

    void closeConnection() throws SQLException;

    /**
     * Returns records from a table. Requires and open connection.
     * @param tableName
     * @param maxRecords
     * @return
     * @throws SQLException
     */    
    public abstract int deleteRecordById(String tableName, String pkColName, 
            Object pkValue) throws SQLException;
    
    public abstract List<Map<String, Object>> getAllRecords(String tableName, int maxRecords) 
            throws SQLException;
    
    public abstract void openConnection(String driverClass, 
            String url, String userName, String password) 
            throws ClassNotFoundException, SQLException;   

    public abstract int createRecord(String tableName, List<String> colNames, 
            List<Object> colValues) throws SQLException;
    
    public abstract int updateRecord(String tableName, List<String> colNames, 
            List<Object> colValues, String pkColName,Object pkValue) throws SQLException;
    public abstract Map<String, Object> findAuthorById (String tableName,String pkColName,Object pkValue)
        throws SQLException;
}
