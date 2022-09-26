package oms.backend.models;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@Table(name = "Orders")
@Entity
public class Order {
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer order_id;
    int user_id;
    int address_id;
    float price;
    int credit_card_id;
    String date_ordered;
    String date_shipped;
    String date_delivered;
    String order_status;
    
    @OneToMany(targetEntity = OrderItems.class, cascade = CascadeType.ALL)
    @JoinColumn(referencedColumnName = "order_id")
    List<OrderItems> orderItems;

    public Order(int order_id, int user_id, int address_id, int price, int credit_card_id, String date_ordered, String date_shipped, String date_delivered, String order_status) {
        this.order_id = order_id;
        this.user_id = user_id;
        this.address_id = address_id;
        this.price = price;
        this.credit_card_id = credit_card_id;
        this.date_ordered = date_ordered;
        this.date_shipped = date_shipped;
        this.date_delivered = date_delivered;
        this.order_status = order_status;

    }
    public List<OrderItems> getOrderItems() {
		return orderItems;
	}
	public void setOrderItems(List<OrderItems> orderItems) {
		this.orderItems = orderItems;
	}
	public String getDateOrdered() {
        return date_ordered;
    }
    public void setDateOrdered(String dateOrdered) {
        this.date_ordered = dateOrdered;
    }
    public Integer getOrderID() {
        return order_id;
    }
    public void setOrderID(Integer orderID) {
        this.order_id = orderID;
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
    public String getDateDelivered() {
        return date_delivered;
    }
    public void setDateDelivered(String dateDelivered) {
        this.date_delivered = dateDelivered;
    }
	@Override
	public String toString() {
		return "Order [order_id=" + order_id + ", user_id=" + user_id + ", address_id=" + address_id + ", price="
				+ price + ", credit_card_id=" + credit_card_id + ", date_ordered=" + date_ordered + ", date_shipped="
				+ date_shipped + ", date_delivered=" + date_delivered + ", order_status=" + order_status
				+ ", orderItems=" + orderItems + "]";
	}
    
}
