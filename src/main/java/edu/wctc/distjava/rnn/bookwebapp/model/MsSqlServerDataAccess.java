/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.distjava.rnn.bookwebapp.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

/**
 *
 * @author Rasika
 */
public class MsSqlServerDataAccess implements DataAccess {
    private final int ALL_RECORDS = 0;
    private Connection conn;
    private Statement stmt;
    private ResultSet rs;
    private String driverClass;
    private String url;
    private String userName;
    private String password;

    public DataAccess getDb() {
        return db;
    }

    public void setDb(DataAccess db) {
        this.db = db;
    }
    private String passWord;
    private DataAccess db;
    
    public MsSqlServerDataAccess(String driverClass, String url, String userName, String passWord,DataAccess db)
    {
        setDriverClass(driverClass);
        setUrl(url);
        setUserName(userName);
        setPassWord(passWord);
        setDb(db);
    }
      @Override
    public void openConnection()
            throws ClassNotFoundException, SQLException {
        Class.forName(driverClass);
        conn = DriverManager.getConnection(url, userName, passWord);
    }

    @Override
    public void closeConnection() throws SQLException {
        if (conn != null) {
            conn.close();
        }
    }

    @Override
    public List<Map<String, Object>> getAllRecords(String tableName, int maxRecords)
            throws SQLException, ClassNotFoundException {
        List<Map<String, Object>> rawData = new Vector<>();
        String sql = "";
        if (maxRecords > ALL_RECORDS) {
            sql = "select Top" + maxRecords +"* from " + tableName;
        } else {
            sql = "select * from   " + tableName;
        }
        openConnection();
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
        closeConnection();
        return rawData;
    }
    

    public Connection getConn() {
        return conn;
    }

    public void setConn(Connection conn) {
        this.conn = conn;
    }

    public Statement getStmt() {
        return stmt;
    }

    public void setStmt(Statement stmt) {
        this.stmt = stmt;
    }

    public ResultSet getRs() {
        return rs;
    }

    public void setRs(ResultSet rs) {
        this.rs = rs;
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

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

     public static void main(String[] args) throws SQLException, ClassNotFoundException {
        DataAccess db = new MySqlDataAccess(
//                "com.mysql.jdbc.Driver",
//                "jdbc:mysql://bit.glassfish.wctc.edu:3306/sakila",
//                "advjava","advjava"
                
//                "org.apache.derby.jdbc.ClientDriver",
//                "jdbc:derby://localhost:1527/sample",
//                "app", "app"

                "com.mysql.jdbc.Driver",
                "jdbc:mysql://localhost:3306/sakila",
                "root","admin"
        );
        
        List<Map<String, Object>> list = db.getAllRecords("actor", 0);
        list.forEach((Map<String, Object> rec) -> {
        System.out.println(rec);
        
        });
    }

    @Override
    public int deleteRecords(String tableName, String colName, int primaryKey) throws ClassNotFoundException, SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
