package oms.backend.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import oms.backend.models.Order;
import oms.backend.repos.OrderRepo;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderRepo repo;

    @Override
    public boolean updateOrder(Order order) {
        Optional<Order> exists = repo.findById(order.getOrderID());
        if (exists.isPresent()) {
            repo.save(order);
            return true;
        }
        return false;
    }
}
