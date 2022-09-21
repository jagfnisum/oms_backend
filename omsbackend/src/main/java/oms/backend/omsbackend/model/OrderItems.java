package oms.backend.omsbackend.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name="OrderItems")
@Entity
public class OrderItems {
    @Id
    private int orderitemid; //This is not the actual order item id, it is just used as a key   
    private int orderid,quantity;
    private String upc;


    /**
     * Default constructor for OrderItems
     */
    public OrderItems() {
    }
    /**
     * Constructor with all class fields
     * @param orderitemid
     * @param orderid
     * @param quantity
     * @param upc 
     */
    public OrderItems(int orderitemid, int orderid, int quantity, String upc) {
        this.orderitemid = orderitemid;
        this.orderid = orderid;
        this.quantity = quantity;
        this.upc = upc;
    }

    public int getOrderItemid() {
        return this.orderitemid;
    }

    public void setOrderItemid(int orderitemid) {
        this.orderitemid = orderitemid;
    }

    public int getOrderid() {
        return this.orderid;
    }

    public void setOrderid(int orderid) {
        this.orderid = orderid;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getUpc() {
        return this.upc;
    }

    public void setUpc(String upc) {
        this.upc = upc;
    }

    @Override
    public String toString() {
        return "{" +
            " orderitemid='" + getOrderItemid() + "'" +
            ", orderid='" + getOrderid() + "'" +
            ", quantity='" + getQuantity() + "'" +
            ", upc='" + getUpc() + "'" +
            "}";
    }

}
