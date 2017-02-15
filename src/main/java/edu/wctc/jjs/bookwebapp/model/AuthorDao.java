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

/**
 *
 * @author jstra
 */
public class AuthorDao implements IAuthorDao {
    private DbAccessor db;
    private String driverClass;
    private String url;
    private String userName;
    private String pwd;

    public AuthorDao(DbAccessor db, String driverClass, String url, String userName, String pwd) {
        this.db = db;
        this.driverClass = driverClass;
        this.url = url;
        this.userName = userName;
        this.pwd = pwd;
    }
    
    @Override
    public List<Author> getAuthorList(String tableName, int maxRecord) throws ClassNotFoundException, SQLException{
        List<Author> authors = new ArrayList<>();
        db.openConnection(driverClass, url, userName, pwd);
        List<Map<String, Object>> rawData = db.findRecordsFor(tableName, maxRecord);
        db.closeConnection();
        for(Map<String, Object> recData : rawData){
            Author author = new Author();
            author.setAuthorId((Integer)recData.get("author_id"));
            Object objName = recData.get("author_name");
            String name = objName != null ? objName.toString() : "";
            author.setAuthorName(name);
            Object objDate = recData.get("date_added");
            Date dateAdded = objDate != null ? (Date)objDate : null;
            author.setDateAdded(dateAdded);
            authors.add(author);
        }
        
        return authors;
    }   
    
    @Override
    public DbAccessor getDb() {
        return db;
    }

    @Override
    public void setDb(DbAccessor db) {
        this.db = db;
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
    public String getPwd() {
        return pwd;
    }

    @Override
    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
    
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        IAuthorDao dao = new AuthorDao(new MySqlDbAccessor(),"com.mysql.jdbc.Driver", "jdbc:mysql://localhost:3306/book", "root", "admin");
        List<Author> authors = dao.getAuthorList("author", 50);
        
     for(Author author: authors){
            System.out.println(author);   
        }
    }
    
}
