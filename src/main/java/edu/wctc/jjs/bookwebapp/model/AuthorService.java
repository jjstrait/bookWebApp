/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.jjs.bookwebapp.model;


import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Joshua
 */
public class AuthorService {
    private IAuthorDao dao;
    private final SimpleDateFormat DF = new SimpleDateFormat("yyyy-MM-dd");
    public AuthorService(IAuthorDao dao) {
        this.dao = dao;
    }
    
    
    
    
    
    
    public List getAllAuthor(String tableName, int maxRecords) throws ClassNotFoundException, SQLException{

        return dao.getAuthorList(tableName, maxRecords);
    }
    
    public int deleteAuthor(String tableName,String pkName, Object pkVal) throws ClassNotFoundException, SQLException{
        return dao.deleteAuthorRecord(tableName, pkName, pkVal);
    }
    
    public int addAuthor(String tableName, List<String> colNames, List colValues) throws ClassNotFoundException, SQLException{
        String currentDate = DF.format(new Date());
        colValues.add(currentDate);
       return dao.addAuthor(tableName, colNames, colValues);
    }
    
    public int updateAuthor(String tableName, List<String> colNames, List colValues, String whereColName, Object whereVal) throws ClassNotFoundException, SQLException {
    return dao.updateAuthorRecord(tableName, colNames, colValues, whereColName, whereVal);
    }
    public IAuthorDao getDao() {
        return dao;
    }

    public void setDao(IAuthorDao dao) {
        this.dao = dao;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        AuthorService service = new AuthorService(
        new AuthorDao(
                new MySqlDbAccessor(),
                "com.mysql.jdbc.Driver",
                "jdbc:mysql://localhost:3306/book", 
                "root","admin"
        )
        );
        
        
        
        
        List<String> colName = new ArrayList<>();
        colName.add("author_name");
        colName.add("date_added");

        List colValues = new ArrayList<>();

        colValues.add("Test");
        colValues.add("2017-02-13");

        List colUpdateValues = new ArrayList<>();

        colUpdateValues.add("Keplar");
        colUpdateValues.add("2017-02-15");
        
        
        
        //service.addAuthor("author", colName, colValues);
        //service.updateAuthor("Author", colName, colUpdateValues, "author_name", "test");
        //service.deleteAuthor("author", "author_id", 13);
        
        List<Author> authors = service.getAllAuthor("author", 50);
        
     for(Author author: authors){
            System.out.println(author);   
        }
    }
    
}
