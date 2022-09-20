package oms.backend.omsbackend.service;
import java.util.List;
import oms.backend.omsbackend.model.OrderItems;

public interface OrderItemsSerivce {
    List<OrderItems> getAllItemsInOrder(int id);
    boolean updateOrderItem(int orderid, String upc, int updatedQuanity);
}
