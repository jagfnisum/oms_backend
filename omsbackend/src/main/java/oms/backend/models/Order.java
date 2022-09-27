package oms.backend.models;

import lombok.*;
import java.util.List;
import javax.persistence.*;


@NoArgsConstructor
@Table(name = "Orders")
@Entity
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int order_id;
    int user_id;
    int address_id;
    float price;
    int credit_card_id;
    String date_ordered;
    String date_shipped;
    String order_status;
    
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    List<OrderItems> orderItems;

    public Order() {
    	
    }

    public Order(int order_id, int user_id, int address_id, int price, int credit_card_id, String date_ordered, String date_shipped, String order_status) {
        this.order_id = order_id;
        this.user_id = user_id;
        this.address_id = address_id;
        this.price = price;
        this.credit_card_id = credit_card_id;
        this.date_ordered = date_ordered;
        this.date_shipped = date_shipped;
        this.order_status = order_status;

    }

    public List<OrderItems> getOrderItems() {
		return orderItems;
	}
	public void setOrderItems(List<OrderItems> orderItems) {
		this.orderItems = orderItems;
		for(OrderItems i: this.orderItems) {
			i.setOrder(this);
		}
	}
	public String getDateOrdered() {
        return date_ordered;
    }
    public void setDateOrdered(String dateOrdered) {
        this.date_ordered = dateOrdered;
    }
    public int getOrderID() {
        return order_id;
    }
    public void setOrderID(int orderid) {
        this.order_id = orderid;
    }
    public int getUserId() {
        return user_id;
    }
    public void setUserId(int userId) {
        this.user_id = userId;
    }
    public int getAddressID() {
        return address_id;
    }
    public void setAddressID(int addressID) {
        this.address_id = addressID;
    }
    public float getPrice() {
        return price;
    }
    public void setPrice(float price) {
        this.price = price;
    }
    public int getCreditCardID() {
        return credit_card_id;
    }
    public void setCreditCardID(int creditCardID) {
        this.credit_card_id = creditCardID;
    }
    public String getOrderStatus() {
        return order_status;
    }
    public void setOrderStatus(String orderStatus) {
        this.order_status = orderStatus;
    }
    public String getDateShipped() {
        return date_shipped;
    }
    public void setDateShipped(String dateShipped) {
        this.date_shipped = dateShipped;
    }

    @Override
	public String toString() {
		return "Order [order_id=" + order_id + ", user_id=" + user_id + ", address_id=" + address_id + ", price="
				+ price + ", credit_card_id=" + credit_card_id + ", date_ordered=" + date_ordered + ", date_shipped="
				+ date_shipped + ", order_status=" + order_status
				+ ", orderItems=" + orderItems + "]";
	}
    
}
