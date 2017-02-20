/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.jjs.bookwebapp.model;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 *
 * @author jstra
 */
public interface DbAccessor {

    void closeConnection() throws SQLException;

    List<Map<String, Object>> findRecordsFor(String tableName, int maxRecords) throws SQLException;

    //consider creating a custom exception
    void openConnection(String driverClass, String url, String userName, String pwd) throws ClassNotFoundException, SQLException;
    
    public void insertRecord(String tableName, List<String> colNames, List colValues) throws SQLException;
    
    public void deleteById(String tableName,String colName, Object id) throws SQLException;
    
    public void updateRecord(String tableName, List<String> colNames, List colValues, String whereColName, Object whereVal) throws SQLException;
}
