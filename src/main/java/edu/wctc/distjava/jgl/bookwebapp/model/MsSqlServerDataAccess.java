package edu.wctc.distjava.jgl.bookwebapp.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

public class MsSqlServerDataAccess implements DataAccess {

    private final int ALL_RECORDS = 0;

    private Connection conn;
    private Statement stmt;
    private ResultSet rs;
    private PreparedStatement pstmt;
    private boolean DEBUG = true;

    @Override
    public void openConnection(String driverClass,
            String url, String userName, String password)
            throws ClassNotFoundException, SQLException {

        Class.forName(driverClass);
        conn = DriverManager.getConnection(url, userName, password);
    }

    @Override
    public void closeConnection() throws SQLException {
        if (conn != null) {
            conn.close();
        }
    }

    @Override
    public int deleteRecordById(String tableName, String pkColName,
            Object pkValue) throws SQLException {

        String sql = "DELETE FROM " + tableName + " WHERE "
                + pkColName + " = ?";
        

        pstmt = conn.prepareStatement(sql);
        pstmt.setObject(1, pkValue);
        int recsDeleted = pstmt.executeUpdate(sql);

        return recsDeleted;
    }

    /**
     * Returns records from a table. Requires and open connection.
     *
     * @param tableName
     * @param maxRecords
     * @return
     * @throws SQLException
     */
    @Override
    public List<Map<String, Object>> getAllRecords(String tableName, int maxRecords)
            throws SQLException {

        List<Map<String, Object>> rawData = new Vector<>();
        String sql = "";

        if (maxRecords > ALL_RECORDS) {
            sql = "select TOP " + maxRecords + " * from " + tableName;
        } else {
            sql = "select * from " + tableName;
        }

        stmt = conn.createStatement();
        rs = stmt.executeQuery(sql);

        ResultSetMetaData rsmd = rs.getMetaData();
        int colCount = rsmd.getColumnCount();
        Map<String, Object> record = null;

        while (rs.next()) {
            record = new LinkedHashMap<>();
            for (int colNum = 1; colNum <= colCount; colNum++) {
                record.put(rsmd.getColumnName(colNum), rs.getObject(colNum));
            }
            rawData.add(record);
        }

        return rawData;
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        DataAccess db = new MsSqlServerDataAccess();

        db.openConnection("org.apache.derby.jdbc.ClientDriver",
                "jdbc:derby://localhost:1527/sample",
                "app", "app");

        List<Map<String, Object>> list = db.getAllRecords("CUSTOMER", 0);

        for (Map<String, Object> rec : list) {
            System.out.println(rec);
        }
        int recsDeleted = db.deleteRecordById("CUSTOMER", "CUSTOMER_ID", 3);
        db.createRecord("author", Arrays.asList("author_name", "date_added"), Arrays.asList("Sanuth", "2017-10-06"));
        System.out.println("No. of Recs Deleted: " + recsDeleted);
        db.closeConnection();
    }

    @Override
    public int createRecord(String tableName, List<String> colNames, List<Object> colValues) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int updateRecord(String tableName, List<String> colNames, List<Object> colValues,String pkColName,Object pkValue) throws SQLException {
             throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    
    }

    @Override
    public Map<String, Object>findAuthorById(String tableName, String pkColName, Object pkValue) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
