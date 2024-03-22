/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phat.order;

import java.io.Serializable;
import java.sql.Date;

/**
 *
 * @author vopha
 */
public class OrderDTO implements Serializable{
    private String orderId;
    private Date orderDate;
    private String address;
    private float total;

    public OrderDTO() {
    }

    public OrderDTO(String orderId, Date orderDate, String address, float total) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.address = address;
        this.total = total;
    }

    public OrderDTO(Date orderDate, String address, float total) {
        this.orderDate = orderDate;
        this.address = address;
        this.total = total;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }
}
