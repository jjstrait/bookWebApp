/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.jjs.bookwebapp.model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;

/**
 *
 * @author jstra
 */
public class ConnPoolAuthorDao implements IAuthorDao {

    private DataSource ds;
    private DbAccessor db;

    public ConnPoolAuthorDao(DataSource ds, DbAccessor db) {
        this.ds = ds;
        this.db = db;
    }

   

    @Override
    public List<Author> getAuthorList(String tableName, int maxRecord) throws ClassNotFoundException, SQLException {
        List<Author> authors = new ArrayList<>();
        db.openConnection(ds);
        List<Map<String, Object>> rawData = db.findRecordsFor(tableName, maxRecord);
        db.closeConnection();
        for (Map<String, Object> recData : rawData) {
            Author author = new Author();
            author.setAuthorId((Integer) recData.get("author_id"));
            Object objName = recData.get("author_name");
            String name = objName != null ? objName.toString() : "";
            author.setAuthorName(name);
            Object objDate = recData.get("date_added");
            Date dateAdded = objDate != null ? (Date) objDate : null;
            author.setDateAdded(dateAdded);
            authors.add(author);
        }

        return authors;
    }
        public Author getSingleAuthor(String tableName, int maxRecords,String whereColName,Object whereVal) throws SQLException, ClassNotFoundException {
        Author authors;
        db.openConnection(ds);
        Map<String, Object> rawData = db.findOneRecordFor(tableName, maxRecords, whereColName, whereVal);
        db.closeConnection();
       
            Author author = new Author();
            author.setAuthorId((Integer) rawData.get("author_id"));
            Object objName = rawData.get("author_name");
            String name = objName != null ? objName.toString() : "";
            author.setAuthorName(name);
            Object objDate = rawData.get("date_added");
            Date dateAdded = objDate != null ? (Date) objDate : null;
            author.setDateAdded(dateAdded);
            
        

        return author;
    }
    @Override
        public List<Author> getAuthorListFromSearch(String tableName, int maxRecord,String whereColName,Object whereVal) throws ClassNotFoundException, SQLException {
        List<Author> authors = new ArrayList<>();
        db.openConnection(ds);
        List<Map<String, Object>> rawData = db.findRecordsByWildCard(tableName, maxRecord, whereColName, whereVal);
        db.closeConnection();
        for (Map<String, Object> recData : rawData) {
            Author author = new Author();
            author.setAuthorId((Integer) recData.get("author_id"));
            Object objName = recData.get("author_name");
            String name = objName != null ? objName.toString() : "";
            author.setAuthorName(name);
            Object objDate = recData.get("date_added");
            Date dateAdded = objDate != null ? (Date) objDate : null;
            author.setDateAdded(dateAdded);
            authors.add(author);
        }

        return authors;
    }

    @Override
    public int deleteAuthorRecord(String tableName, String pkName, Object pkVal) throws ClassNotFoundException, SQLException {
        db.openConnection(ds);
        int status = db.deleteById(tableName, pkName, pkVal);
        db.closeConnection();
        
        return status;
    }

    @Override
    public int addAuthor(String tableName, List<String> colNames, List colValues) throws ClassNotFoundException, SQLException {
        db.openConnection(ds);
        int status = db.insertRecord(tableName, colNames, colValues);
        db.closeConnection();
        
        return status;
    }

    @Override
    public int updateAuthorRecord(String tableName, List<String> colNames, List colValues, String whereColName, Object whereVal) throws ClassNotFoundException, SQLException {
        db.openConnection(ds);
        int status = db.updateRecord(tableName, colNames, colValues, whereColName, whereVal);
        db.closeConnection();
        
        return status;
    }

  
    public DbAccessor getDb() {
        return db;
    }

    @Override
    public void setDb(DbAccessor db) {
        this.db = db;
    }

   

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
       // IAuthorDao dao = new ConnPoolAuthorDao(new MySqlDbAccessor());
        List<String> colName = new ArrayList<>();
        colName.add("author_name");
        colName.add("date_added");

        List colValues = new ArrayList<>();

        colValues.add("Test");
        colValues.add("2017-02-13");

        List colUpdateValues = new ArrayList<>();

        colUpdateValues.add("Keplar");
        colUpdateValues.add("2017-02-15");
        
        
        
        //dao.addAuthor("author", colName, colValues);
        //dao.updateAuthorRecord("Author", colName, colUpdateValues, "author_name", "test");
       // dao.deleteAuthorRecord("author", "author_id", 12);
        //List<Author> authors = dao.getAuthorList("author", 50);
        //List<Author> authors = dao.getAuthorListFromSearch("author", 50, "author_name", "h");

       // for (Author author : authors) {
         //   System.out.println(author);
        //}
    }

}
