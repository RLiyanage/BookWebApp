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
import java.util.StringJoiner;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MySqlDataAccess implements DataAccess {

    private final int ALL_RECORDS = 0;

    private Connection conn;
    private Statement stmt;
    private PreparedStatement pstmt;
    private ResultSet rs;
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

    /**
     *
     * @param tableName
     * @param colNames
     * @param colValues
     * @return
     * @throws SQLException
     */
    @Override
    public int createRecord(String tableName, List<String> colNames,
            List<Object> colValues) throws SQLException {
        String sql = "INSERT INTO " + tableName + " ";
        StringJoiner sj = new StringJoiner(", ", "(", ")");
        for (String col : colNames) {
            sj.add(col);
        }

        sql += sj.toString();
        sql += "VALUES";

        sj = new StringJoiner(", ", "(", ")");

        for (Object value : colValues) {
            sj.add("?");
        }
        sql += sj.toString();
        if (DEBUG) {
            System.out.println(sql);
            pstmt = conn.prepareStatement(sql);
        }

        for (int i = 1; i <= colValues.size(); i++) {
            pstmt.setObject(i, colValues.get(i - 1));
        }
        return pstmt.executeUpdate();
    }
    @Override
    public  int updateRecord(String tableName, List<String> colNames, 
            List<Object> colValues,String pkColName,Object pkValue) throws SQLException{
               
    //"UPDATE " + tableName + " SET"colNames + "= ?" + "where "+ colNames +" = ?";
    //UPDATE author SET author_name = ?, date_added = ? where author_id = ?
    String sql = "UPDATE " + tableName + " SET ";
     
    StringJoiner sj = new StringJoiner(", ");
    for( String col:colNames){
      sj.add(col + " = ?");
    }         
    sql += sj + " where " + pkColName + " = ?";
    
    if(DEBUG){
        System.out.println(sql);
        pstmt =conn.prepareStatement(sql);
    }
    for(int i = 1; i <= colValues.size(); i++){
        pstmt.setObject(i, colValues.get(i-1));
    }
    
//    for(int i = 0; i < colValues.size(); i++){
//        pstmt.setObject(i + 1, colValues.get(i));
//    }
    
    pstmt.setObject(colValues.size() + 1, pkValue);
    return pstmt.executeUpdate();
            }

    @Override
    public int deleteRecordById(String tableName, String pkColName,
            Object pkValue) throws SQLException {

        String sql = "DELETE FROM " + tableName + " WHERE "
                + pkColName + " = ?";

        pstmt = conn.prepareStatement(sql);
        //pstmt.setString(1, pkColName);
        pstmt.setObject(1, pkValue);
        int recsDeleted = pstmt.executeUpdate();

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
        String sql;

        if (maxRecords > ALL_RECORDS) {
            sql = "select * from " + tableName + " limit " + maxRecords;
        } else {
            sql = "select * from " + tableName;
        }

        stmt = conn.createStatement();
        rs = stmt.executeQuery(sql);

        ResultSetMetaData rsmd = rs.getMetaData();
        int colCount = rsmd.getColumnCount();
        Map<String, Object> record;

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
        DataAccess db = new MySqlDataAccess();
        db.openConnection("com.mysql.jdbc.Driver",
                "jdbc:mysql://localhost:3306/book",
                "root", "admin");

        int recsDeleted = db.deleteRecordById("author", "author_id", 5);
        db.updateRecord("author", Arrays.asList("author_name", "date_added"), Arrays.asList("Senudhi", "2017-10-10"),"author_id",6);
        db.createRecord("author", Arrays.asList("author_name", "date_added"), Arrays.asList("Sanuth", "2017-10-06"));
        System.out.println("No. of Recs Deleted: " + recsDeleted);
        List<Map<String, Object>> list = db.getAllRecords("author", 0);

        for (Map<String, Object> rec : list) {
            System.out.println(rec);
        }
        db.closeConnection();
    }

    
}
