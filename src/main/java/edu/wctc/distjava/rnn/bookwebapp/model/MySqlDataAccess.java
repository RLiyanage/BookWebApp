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
public final class MySqlDataAccess implements DataAccess {

    private final int ALL_RECORDS = 0;

    private Connection conn;
    private Statement stmt;
    private ResultSet rs;
    private String driverClass;
    private String url;
    private String userName;
    private String passWord;

    public MySqlDataAccess(String driverClass, String url, String userName, String passWord) {
        setDriverClass(driverClass);
        setUrl(url);
        setUserName(userName);
        setPassWord(passWord);
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

    /**
     * Returns reccords from a table.Requires and open connection.
     *
     * @param tableName
     * @param maxRecords
     * @return
     * @throws SQLException
     */
    @Override
    public List<Map<String, Object>> getAllRecords(String tableName, int maxRecords)
            throws SQLException, ClassNotFoundException {
        List<Map<String, Object>> rawData = new Vector<>();
        String sql = "";
        if (maxRecords > ALL_RECORDS) {
            sql = "select * from " + tableName + "limit " + maxRecords;
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

    @Override
    public String getDriverClass() {
        return driverClass;
    }

    @Override
    public void setDriverClass(String driverClass) {
        this.driverClass = driverClass;
    }

    @Override
    public String getUrl() {
        return url;
    }

    @Override
    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String getUserName() {
        return userName;
    }

    @Override
    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String getPassWord() {
        return passWord;
    }

    @Override
    public void setPassWord(String password) {
        this.passWord = password;
    }

    @Override
    public int deleteRecords(String tableName,String colName, int primaryKey) throws ClassNotFoundException, SQLException {
        String sql = "";
        if (tableName != null&& colName!= null && primaryKey > 0) {
            sql = "delete *from " + tableName  + "Where" + colName+" = 10";
        } else {

        }
        openConnection();
        stmt = conn.createStatement();
        int updateCount = stmt.executeUpdate(sql);
        closeConnection();
        return updateCount;
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        DataAccess db = new MySqlDataAccess(
                "org.apache.derby.jdbc.ClientDriver",
                "jdbc:derby://localhost:1527/sample",
                "app", "app"
        );

        List<Map<String, Object>> list = db.getAllRecords("CUSTOMER", 0);
        list.forEach((Map<String, Object> rec) -> {
            System.out.println(rec);

        });
    }
}
