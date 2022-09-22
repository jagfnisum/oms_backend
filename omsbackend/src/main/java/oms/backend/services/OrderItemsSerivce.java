package oms.backend.services;
import java.util.List;

import oms.backend.models.OrderItems;

public interface OrderItemsSerivce {
    List<OrderItems> getAllItemsInOrder(int id);
    boolean updateOrderItem(int orderid, String upc, int updatedQuanity);

    public boolean addOrderItem(OrderItems item);
}
