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

    public int deleteAuthorRecord(String tableName, String pkName, Object pkVal) throws ClassNotFoundException, SQLException;

    public int addAuthor(String tableName, List<String> colNames, List colValues) throws ClassNotFoundException, SQLException;

    public int updateAuthorRecord(String tableName, List<String> colNames, List colValues, String whereColName, Object whereVal) throws ClassNotFoundException, SQLException;

    void setDb(DbAccessor db);

    void setDriverClass(String driverClass);

    void setPwd(String pwd);

    void setUrl(String url);

    void setUserName(String userName);

        public Author getSingleAuthor(String tableName, int maxRecords,String whereColName,Object whereVal) throws SQLException, ClassNotFoundException ;
}
