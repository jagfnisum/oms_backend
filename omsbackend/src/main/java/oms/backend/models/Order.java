package oms.backend.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "Orders")
@Entity
public class Order {
    @Id
    int orderID;
    int userId;
    int addressID;
    float price;
    int creditCardID;
    String orderStatus;
    String dateShipped;
    String dateDelivered;
    public int getOrderID() {
        return orderID;
    }
    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }
    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }
    public int getAddressID() {
        return addressID;
    }
    public void setAddressID(int addressID) {
        this.addressID = addressID;
    }
    public float getPrice() {
        return price;
    }
    public void setPrice(float price) {
        this.price = price;
    }
    public int getCreditCardID() {
        return creditCardID;
    }
    public void setCreditCardID(int creditCardID) {
        this.creditCardID = creditCardID;
    }
    public String getOrderStatus() {
        return orderStatus;
    }
    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }
    public String getDateShipped() {
        return dateShipped;
    }
    public void setDateShipped(String dateShipped) {
        this.dateShipped = dateShipped;
    }
    public String getDateDelivered() {
        return dateDelivered;
    }
    public void setDateDelivered(String dateDelivered) {
        this.dateDelivered = dateDelivered;
    }
}
