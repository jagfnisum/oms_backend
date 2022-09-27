package oms.backend.models;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Table(name="order_items")
@Entity
public class OrderItems {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int order_item_id; //This is not the actual order item id, it is just used as a key   
    private int quantity;
    private String upc;
    
  
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name="order_id")
    Order order;
    
    
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
    public OrderItems(int orderitemid, int quantity, String upc) {
        this.order_item_id = orderitemid;
        this.quantity = quantity;
        this.upc = upc;
    }

    public int getOrderItemid() {
        return this.order_item_id;
    }

    public void setOrderItemid(int orderitemid) {
        this.order_item_id = orderitemid;
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
    
    public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}

    @Override
    public String toString() {
        return "{" +
            " orderitemid='" + getOrderItemid() + "'" +
            ", orderid='" + this.getOrder().getOrderID() + "'" +
            ", quantity='" + getQuantity() + "'" +
            ", upc='" + getUpc() + "'" +
            "}";
    }
    
    

}
