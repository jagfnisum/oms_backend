package oms.backend.services;

import java.util.List;
import java.util.Optional;

import oms.backend.models.Order;

public interface OrderService {
    boolean updateOrder(int id, String status);
    Optional<Order> getOrderById(int id);
    List<Order> getOrdersUserId(int id);
    List<Order> getOrders();
    boolean createOrder(Order order);
}
