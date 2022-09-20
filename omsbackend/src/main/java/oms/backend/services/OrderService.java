package oms.backend.services;

import oms.backend.models.Order;

public interface OrderService {
    boolean updateOrder(Order order);
}
