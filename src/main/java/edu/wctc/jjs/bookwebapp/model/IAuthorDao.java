/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.jjs.bookwebapp.model;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author jstra
 */
public interface IAuthorDao {

    List<Author> getAuthorList(String tableName, int maxRecord) throws ClassNotFoundException, SQLException;

    DbAccessor getDb();

    String getDriverClass();

    String getPwd();

    String getUrl();

    String getUserName();

    void setDb(DbAccessor db);

    void setDriverClass(String driverClass);

    void setPwd(String pwd);

    void setUrl(String url);

    void setUserName(String userName);
    
}
