/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package orderdetail;

import java.io.Serializable;

/**
 *
 * @author vopha
 */
public class OrderDetailDTO implements Serializable {

    private String orderDetailId;
    private String productId;
    private float unitPrice;
    private int quantity;
    private float total;
    private String orderId;

    public OrderDetailDTO() {
    }

    public OrderDetailDTO(String orderDetailId, String productId, float unitPrice, int quantity, float total, String orderId) {
        this.orderDetailId = orderDetailId;
        this.productId = productId;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
        this.total = total;
        this.orderId = orderId;
    }

    public String getOrderDetailId() {
        return orderDetailId;
    }

    public void setOrderDetailId(String orderDetailId) {
        this.orderDetailId = orderDetailId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public float getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(float unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
}
