/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author huang
 */
public class DbConnectionUtil {
    public static Connection getConnection() 
            throws NamingException, SQLException, RuntimeException
    {
        InitialContext cxt = new InitialContext();
        if(cxt == null){
            throw new RuntimeException("Unable to create naming context");
        }
        Context dbContext = (Context) cxt.lookup("java:comp/env");
        DataSource ds = (DataSource) dbContext.lookup("jdbc/myDatasource");
        if (ds == null) {
        throw new RuntimeException("Data source not found!");
        }
        Connection con =  ds.getConnection();
        /*
        Statement stmt = null;
        try{
            stmt = con.createStatement();
            stmt.execute("USE ece356db_h53huang;");
        }finally{
            if (stmt != null) {
                stmt.close();
            }
        }
        */
        return con;
    }
}
