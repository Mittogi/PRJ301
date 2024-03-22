/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phat.util;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author vopha
 */
public class DBHelper implements Serializable {

    public static Connection getConnection() throws SQLException {
        Connection conn = null;
        try {
            Context context = new InitialContext();
            Context end = (Context) context.lookup("java:comp/env");
            DataSource ds = (DataSource) end.lookup("MVC2SE1851DS");
            conn = ds.getConnection();

        } catch (NamingException ex) {
            ex.printStackTrace();
        }
        return conn;

    }
}
