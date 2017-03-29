/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.jjs.bookwebapp.model;

import java.util.Date;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author jstra
 */
@Stateless
public class AuthorFacade extends AbstractFacade<Author> {

    @PersistenceContext(unitName = "edu.wctc.jjs_bookWebApp_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AuthorFacade() {
        super(Author.class);
    }
    
    public int deleteById(String id){
        Integer iId=Integer.parseInt(id);
        String jpql = "delete from Author a where a.authorId = :id";
        Query q = this.getEntityManager().createQuery(jpql);
        q.setParameter("id", iId);
        return q.executeUpdate();
    }
    
    public void addNew(String name){
    Author a = new Author();
    a.setAuthorName(name);
    Date createDate = new Date();
    a.setDateAdded(createDate);
    this.create(a);
    
    }
    
    public void update(String id, String name){
        Author a = this.find(Integer.parseInt(id));
        a.setAuthorName(name);
        this.edit(a);
    }
    
    public int addOrUpdate(String id, String name){
       if(id == null||id.equals("0")){
       Author a = new Author();
    a.setAuthorName(name);
    Date createDate = new Date();
    a.setDateAdded(createDate);
    this.create(a);
       }
         Integer iId=Integer.parseInt(id);
        String jpql = "UPDATE Author a SET a.authorName = :name WHERE a.authorId = :id";
        Query q = this.getEntityManager().createQuery(jpql);
        q.setParameter("id", iId);
        q.setParameter("name", name);
        return q.executeUpdate();
    }
}
