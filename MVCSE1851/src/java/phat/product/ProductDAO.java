/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phat.product;

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
public class ProductDAO implements Serializable {

    private final String TABLE_NAME = "Product";

    private List<ProductDTO> productList;
    
    public List<ProductDTO> getProductList()
            throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            //1. Connect DB
            con = DBHelper.getConnection();
            if (con != null) {
                //2. Create sql string
                String sql = "Select * From " + TABLE_NAME;

                //3. Create Statement Object
                stm = con.prepareStatement(sql);

                //4. Execute Query
                rs = stm.executeQuery();

                //5. Process Result
                while (rs.next()) {
                    //5.1 get data from Result set
                    String productID = rs.getString("productid");
                    String productName = rs.getString("name");
                    String productDescription = rs.getString("description");
                    float unitPrice = rs.getFloat("unitPrice");
                    int quantity = rs.getInt("quantity");
                    boolean status = rs.getBoolean("status");
                    
                    //5.2 set data to DTO properties
                    ProductDTO dto = new ProductDTO(productID, productName, productDescription, unitPrice, quantity, status);
                    
                    if (this.productList == null) {
                        this.productList = new ArrayList<>();
                    }//end account List has not existed
                    
                    this.productList.add(dto);
                }//product have been existed
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

        return productList;
    }
}
