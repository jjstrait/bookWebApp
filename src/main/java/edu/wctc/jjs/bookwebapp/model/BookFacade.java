/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.jjs.bookwebapp.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author jstra
 */
@Stateless
public class BookFacade extends AbstractFacade<Book> {
 
    @PersistenceContext(unitName = "edu.wctc.jjs_bookWebApp_war_1.0-SNAPSHOTPU")
    private EntityManager em;
    
    @EJB
    AuthorFacade authService;
    

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public BookFacade() {
        super(Book.class);
    }
    
    public List<Book> findBooksFor(String authorId){
    Integer iId=Integer.parseInt(authorId);
        String jpql = "SELECT Book b WHERE b.authorId = :id";
        Query q = this.getEntityManager().createQuery(jpql);
        q.setParameter("id", iId);
         //List results = q.executeUpdate();
        return null;
    }
    
    public void addNew(String title, String isbn,String id){
        if(id == null||id.isEmpty()){
               throw new IllegalArgumentException("ID is null"); 
        }
        if(id == null||id.isEmpty()){
               throw new IllegalArgumentException("ID is null"); 
        }
        if(id == null||id.isEmpty()){
               throw new IllegalArgumentException("ID is null"); 
        }
        
        Author author = authService.find(new Integer(id));
        Set<Book> bookSet = author.getBookSet();
        Book book = new Book();
        book.setIsbn(isbn);
        book.setTitle(title);
        book.setAuthorId(author);
        this.create(book);
        bookSet.add(book);
        authService.edit(author);
        
//    List results = em.createNamedQuery("Author.findByAuthorId")
//    .setParameter("authorId", Integer.parseInt(id)).getResultList();
//        Book b = new Book();
//        b.setTitle(title);
//        b.setIsbn(isbn);
//        b.setAuthorId((Author)results.get(0));
   
    
    
    
    //this.create(b);
    
    }
    
     public void deleteById(String id){
        Integer iId=Integer.parseInt(id);
        
        Book book = this.find(iId);
        
        int authorId = book.getAuthorId().getAuthorId();
        Author author = authService.find(authorId);
       Set<Book> books = author.getBookSet();
        Set newBooks = new HashSet();
        for(Book b: books){
            if(!Objects.equals(b.getBookId(), iId)){
                newBooks.add(b);
            }
        }
        
        author.setBookSet(newBooks);
        authService.edit(author);
        
        String jpql = "delete from Book a where a.bookId = :id";
        Query q = this.getEntityManager().createQuery(jpql);
        q.setParameter("id", iId);
        q.executeUpdate();
    }
    
     public void update(String title, String isbn, String id, String authorId){
         List results = em.createNamedQuery("Book.findByBookId")
    .setParameter("bookId", Integer.parseInt(id)).getResultList();
         List authorResults = em.createNamedQuery("Author.findByAuthorId")
    .setParameter("authorId", Integer.parseInt(authorId)).getResultList();
        Book b = (Book)results.get(0);
        b.setTitle(title);
        b.setIsbn(isbn);
        b.setAuthorId((Author)authorResults.get(0));
        this.edit(b);
        
        
    }
     
}
