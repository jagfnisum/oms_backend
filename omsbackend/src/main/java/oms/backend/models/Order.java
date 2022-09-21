package oms.backend.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "Orders")
@Entity
public class Order {
    @Id
    int order_id;
    int user_id;
    int address_id;
    float price;
    int credit_card_id;
    String date_ordered;
    String date_shipped;
    String date_delivered;
    String order_status;

    public String getDateOrdered() {
        return date_delivered;
    }
    public void setDateOrdered(String dateOrdered) {
        date_delivered = dateOrdered;
    }
    public int getOrderID() {
        return order_id;
    }
    public void setOrderID(int orderID) {
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
}
