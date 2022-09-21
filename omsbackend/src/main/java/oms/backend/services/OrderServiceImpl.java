package oms.backend.services;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
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
    public boolean updateOrder(int id, String status) {
        Optional<Order> exists = repo.findById(id);
        if (exists.isPresent()) {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();
            switch (status) {
                case "Shipped":
                    exists.get().setDateShipped(dtf.format(now));
                    exists.get().setOrderStatus(status);
                    repo.save(exists.get());
                    return true;

                case "Delivered":
                    exists.get().setDateDelivered(dtf.format(now));
                    exists.get().setOrderStatus(status);
                    repo.save(exists.get());
                    return true;

                case "Canceled":
                    exists.get().setOrderStatus(status);
                    repo.save(exists.get());
                    return true;

                default:
                    return false;
            }
        }
        return false;
    }

    @Override
    public Optional<Order> getOrderById(int id) {
        Optional<Order> exists = repo.findById(id);
        return exists;
    }

    @Override
    public List<Order> getOrders() {
        return repo.findAll();
    }
}
