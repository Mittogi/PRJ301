/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phat.registration;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import phat.util.DBHelper;

/**
 *
 * @author vopha
 */
public class RegistrationDAO implements Serializable {

    public RegistrationDTO checkLogin(String username, String password)
            throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        RegistrationDTO result = null;

        try {
            //1. Get connection
            con = DBHelper.getConnection();
            if (con != null) {
                //2. Create SQL string
                String sql = "Select lastname, isAdmin "
                        + "From Registration "
                        + "Where username = ? "
                        + "And password = ?";

                //3. create Statement Object
                stm = con.prepareStatement(sql);
                stm.setString(1, username);
                stm.setString(2, password);

                //4. Execute Query
                rs = stm.executeQuery();

                //5. Process Result
                if (rs.next()) {
                    //map --> get data from rs and set data to DTO property
                    String fullName = rs.getString("lastname");
                    boolean role = rs.getBoolean("isAdmin");
                    result = new RegistrationDTO(username, null, fullName, role);
                } //username and password have been existed
            }//Connection has been available
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }

        return result;
    }

    private List<RegistrationDTO> listAccounts;

    public List<RegistrationDTO> getListAccounts() {
        return listAccounts;
    }

    public void searchLasName(String searchValue)
            throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            //1. Get connection
            con = DBHelper.getConnection();
            if (con != null) {
                //2. Create SQL string
                String sql = "Select username, password, lastname, isAdmin "
                        + "From Registration "
                        + "Where lastname like ?";

                //3. Create statement object
                stm = con.prepareStatement(sql);
                stm.setString(1, "%" + searchValue + "%");

                //4. Excecute query
                rs = stm.executeQuery();

                //5. Process result
                while (rs.next()) {
                    //5.1 Get data from Result Set 
                    String username = rs.getString("username");
                    String password = rs.getString("password");
                    String fullName = rs.getString("lastname");
                    boolean role = rs.getBoolean("isAdmin");

                    //5.2 Set data to DTO properties
                    RegistrationDTO dto = new RegistrationDTO(username, password, fullName, role);
                    if (this.listAccounts == null) {
                        this.listAccounts = new ArrayList<>();
                    }//end account has not existed
                    this.listAccounts.add(dto);
                }//end account has existed
            }//connection has been available 
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }

    public boolean deleteAccount(String username)
            throws ClassNotFoundException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        boolean result = false;

        try {
            //1. Get connection
            con = DBHelper.getConnection();
            if (con != null) {
                //2 Create SQL string
                String sql = "Delete From Registration "
                        + "Where username = ?";

                //3. Create statement object
                stm = con.prepareStatement(sql);
                stm.setString(1, username);

                //4.Execute query
                int effectRows = stm.executeUpdate();

                //5. Process result
                if (effectRows > 0) {
                    result = true;
                }
            }//connection last been available
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }

        return result;
    }

    public boolean updateAccount(String username, String password, boolean role)
            throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        boolean result = false;

        try {
            con = DBHelper.getConnection();
            if (con != null) {
                String sql = "Update Registration "
                        + "Set password = ?, isAdmin = ? "
                        + "Where username = ?";

                stm = con.prepareStatement(sql);
                stm.setString(1, password);
                stm.setBoolean(2, role);
                stm.setString(3, username);

                int effectRows = stm.executeUpdate();

                if (effectRows > 0) {
                    result = true;
                }
            }
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }

        return result;
    }

    public boolean createAccount(RegistrationDTO account)
            throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        boolean result = false;

        try {
            //1. Get connection
            con = DBHelper.getConnection();
            if (con != null) {
                //2. Create sql string
                String sql = "Insert Into Registration("
                           + "username, password, lastname, isAdmin"
                           + ") Values("
                           + "?, ?, ?, ?"
                           + ")";

                //3. Create statement Object
                stm = con.prepareStatement(sql);
                stm.setString(1, account.getUsername());
                stm.setString(2, account.getPassword());
                stm.setString(3, account.getFullName());
                stm.setBoolean(4, account.isRole());
                
                //4. Execute query
                int effectRows = stm.executeUpdate();
                
                //5. Process result
                if (effectRows > 0) {
                    result = true;
                }//account is inserted
            }//Connection has been available
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        
        return result;
    }
}
