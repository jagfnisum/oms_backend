package oms.backend.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "Orders")
@Entity
public class Order {
    @Id
    int OrderID;
    int UserID;
    int AddressID;
    float Price;
    int CreditCardID;
    String OrderStatus;
    String DateShipped;
    String DateDelivered;
    public int getOrderID() {
        return OrderID;
    }
    public void setOrderID(int orderID) {
        this.OrderID = orderID;
    }
    public int getUserId() {
        return UserID;
    }
    public void setUserId(int userId) {
        this.UserID = userId;
    }
    public int getAddressID() {
        return AddressID;
    }
    public void setAddressID(int addressID) {
        this.AddressID = addressID;
    }
    public float getPrice() {
        return Price;
    }
    public void setPrice(float price) {
        this.Price = price;
    }
    public int getCreditCardID() {
        return CreditCardID;
    }
    public void setCreditCardID(int creditCardID) {
        this.CreditCardID = creditCardID;
    }
    public String getOrderStatus() {
        return OrderStatus;
    }
    public void setOrderStatus(String orderStatus) {
        this.OrderStatus = orderStatus;
    }
    public String getDateShipped() {
        return DateShipped;
    }
    public void setDateShipped(String dateShipped) {
        this.DateShipped = dateShipped;
    }
    public String getDateDelivered() {
        return DateDelivered;
    }
    public void setDateDelivered(String dateDelivered) {
        this.DateDelivered = dateDelivered;
    }
}
