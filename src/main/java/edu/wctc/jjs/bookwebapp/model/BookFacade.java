/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.jjs.bookwebapp.model;

import java.util.List;
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

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public BookFacade() {
        super(Book.class);
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
    List results = em.createNamedQuery("Author.findByAuthorId")
    .setParameter("authorId", Integer.parseInt(id)).getResultList();
        Book b = new Book();
        b.setTitle(title);
        b.setIsbn(isbn);
        b.setAuthorId((Author)results.get(0));
   
    
    
    
    this.create(b);
    
    }
    
     public int deleteById(String id){
        Integer iId=Integer.parseInt(id);
        String jpql = "delete from Book a where a.bookId = :id";
        Query q = this.getEntityManager().createQuery(jpql);
        q.setParameter("id", iId);
        return q.executeUpdate();
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
