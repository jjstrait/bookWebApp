/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.jjs.bookwebapp.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

/**
 *
 * @author jstra
 */
public class MySqlDbAccessor implements DbAccessor {

    private Connection conn;
    private Statement stmt;
    private ResultSet rs;

    @Override
    public List<Map<String, Object>> findRecordsFor(String tableName, int maxRecords) throws SQLException {
        List<Map<String, Object>> results = new ArrayList<>();

        String sql = "";
        if (maxRecords > 0) {

            sql = "SELECT * FROM " + tableName + " LIMIT " + maxRecords;
        } else {
            sql = "SELECT * FROM " + tableName;
        }
        stmt = conn.createStatement();
        rs = stmt.executeQuery(sql);

        ResultSetMetaData rsmd = rs.getMetaData();
        int colCount = rsmd.getColumnCount();
        Map<String, Object> record = null;
        while (rs.next()) {
            record = new LinkedHashMap<>();
            for (int colNo = 1; colNo <= colCount; colNo++) {
                String colName = rsmd.getColumnName(colNo);
                record.put(colName, rs.getObject(colNo));
            }
            results.add(record);
        }

        return results;
    }

    @Override
    public int deleteById(String tableName, String colName, Object id) throws SQLException {
        String sId;
        if (id instanceof String) {
            sId = id.toString();
        }
        String sql = "DELETE FROM " + tableName + " WHERE " + colName + " = " + id;
       
        stmt = conn.createStatement();
        return stmt.executeUpdate(sql);
        
        

    }

    public int insertRecord(String tableName, List<String> colNames, List colValues) throws SQLException {
        String sql = "INSERT INTO " + tableName + " ";
        StringJoiner join = new StringJoiner(",", "(", ")");

        for (String s : colNames) {
            join.add(s);
        }

        sql += join.toString();
        join = new StringJoiner(",", "(", ")");
        sql += " VALUES ";

        for (Object s : colValues) {
            join.add("?");
        }

        sql += join.toString();

        PreparedStatement pstm = conn.prepareStatement(sql);

        for (int i = 0; i < colValues.size(); i++) {
            pstm.setObject(i + 1, colValues.get(i));
        }

        return pstm.executeUpdate();
        
    }

    @Override
    public int updateRecord(String tableName, List<String> colNames, List colValues, String whereColName, Object whereVal) throws SQLException {

        String sql = "UPDATE " + tableName + " SET  ";
        StringJoiner join = new StringJoiner(",");

        for (String s : colNames) {
            join.add(s + "=?");
        }

        sql+= join.toString();
        sql+=" WHERE " + whereColName+"= ?";
        
        PreparedStatement pstm = conn.prepareStatement(sql);

        for (int i = 0; i < colValues.size(); i++) {
            pstm.setObject(i + 1, colValues.get(i));
        }

        pstm.setObject(colValues.size()+1, whereVal);
        
        return pstm.executeUpdate();
    }

    //consider creating a custom exception
    @Override
    public void openConnection(String driverClass, String url, String userName, String pwd) throws ClassNotFoundException, SQLException {
        //needs validation

        Class.forName(driverClass);
        conn = DriverManager.getConnection(url, userName, pwd);

    }

    @Override
    public void closeConnection() throws SQLException {
        if (conn != null) {
            conn.close();
        }
    }

    public static void main(String[] args) throws Exception {
        DbAccessor db = new MySqlDbAccessor();
        List<String> colName = new ArrayList<>();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

        colName.add("author_name");
        colName.add("date_added");

        List colValues = new ArrayList<>();

        colValues.add("Test");
        String date = df.format(new Date());
        colValues.add(date);

        List colUpdateValues = new ArrayList<>();

        colUpdateValues.add("Ray Bradbury");
        colUpdateValues.add("2017-02-13");

        db.openConnection("com.mysql.jdbc.Driver", "jdbc:mysql://localhost:3306/book", "root", "admin");
        System.out.println(db.insertRecord("author",colName,colValues));
        //db.updateRecord("author",colName, colUpdateValues, "author_id", 5);
        //db.deleteById("author", "author_id", 4);
        List<Map<String, Object>> records = db.findRecordsFor("author", 50);
        db.closeConnection();

        for (Map<String, Object> record : records) {
            System.out.println(record);
        }
    }

}
