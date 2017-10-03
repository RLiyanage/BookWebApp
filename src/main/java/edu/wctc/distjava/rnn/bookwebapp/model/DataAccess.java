/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.distjava.rnn.bookwebapp.model;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Rasika
 */
public interface DataAccess {

    void closeConnection() throws SQLException;

    List<Map<String, Object>> getAllRecords(String tableName, int maxRecords) throws SQLException, ClassNotFoundException;
   public int deleteRecords(String tableName,String colName, int primaryKey) throws ClassNotFoundException, SQLException; 

    String getDriverClass();

    String getPassWord();

    String getUrl();

    String getUserName();

    void openConnection() throws ClassNotFoundException, SQLException;

    void setDriverClass(String driverClass);

    void setPassWord(String password);

    void setUrl(String url);

    void setUserName(String userName);
    
}
