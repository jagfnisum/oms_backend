package oms.backend.models;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

@Table(name="order_items")
@Entity
public class OrderItems {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int order_item_id; //This is not the actual order item id, it is just used as a key 
    private int order_id, quantity;
    private String upc;
    
    
//    @ManyToOne
//    @JoinColumn(referencedColumnName="order_id", nullable = false)

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="order_id", insertable=false, updatable=false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Order order;

    
//
//    public int getOrder() {
//
////        return order;
//    	return order.getOrderID();
//    }
//
//    public void setOrder(Order order) {
//
////        this.order = order;
//    	this.order_id = order.getOrderID(); 
//    }
    
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
        this.order_item_id = orderitemid;
        this.order_id = orderid;
        this.quantity = quantity;
        this.upc = upc;
    }

    public int getOrderItemid() {
        return this.order_item_id;
    }

    public void setOrderItemid(int orderitemid) {
        this.order_item_id = orderitemid;
    }

    public int getOrderid() {
        return this.order_id;
    }

    public void setOrderid(int orderid) {
        //this.order_id = orderid;
    	this.order_id = this.order.getOrderID(); 
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
