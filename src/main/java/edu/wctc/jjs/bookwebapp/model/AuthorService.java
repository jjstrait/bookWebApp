/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.jjs.bookwebapp.model;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Joshua
 */
public class AuthorService {
    public List getAuthorList(){
    List<Author> authorList = new ArrayList();
    
    authorList.add(new Author(1,"H.G. Wells", new Date()));
    authorList.add(new Author(2,"Mark Tawin", new Date()));
    authorList.add(new Author(3,"Dr. Suess", new Date()));
    
    return authorList;
    }
}
