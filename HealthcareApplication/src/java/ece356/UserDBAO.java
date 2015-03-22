package ece356;

import java.sql.*;
import javax.naming.*;
import javax.sql.DataSource;

public class UserDBAO {
    public static final String url = "jdbc:mysql://eceweb.uwaterloo.ca:3306/";
    public static final String db = "ece356db_h53huang";
    public static final String user = "user_h53huang";
    public static final String pwd = "password";
    
    /*
    public static void testConnection()
            throws ClassNotFoundException, SQLException {
        Statement stmt;
        Connection con;
        Class.forName("com.mysql.jdbc.Driver");
        con = DriverManager.getConnection(url, user, pwd);
        stmt = con.createStatement();
        con.close();
    }
    */
    public static void testPool()
            throws SQLException, NamingException
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
        Connection con = ds.getConnection();
        //… // execute SQL statements
        
        con.close(); // this statement returns the connection back to the pool
    }
}
