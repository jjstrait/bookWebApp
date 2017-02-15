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

/**
 *
 * @author Joshua
 */
public class AuthorService {
    private IAuthorDao dao;

    public AuthorService(IAuthorDao dao) {
        this.dao = dao;
    }
    
    
    
    
    
    
    public List getAllAuthor(String tableName, int maxRecords) throws ClassNotFoundException, SQLException{

        return dao.getAuthorList(tableName, maxRecords);
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
        List<Author> authors = service.getAllAuthor("author", 50);
        
     for(Author author: authors){
            System.out.println(author);   
        }
    }
    
}
