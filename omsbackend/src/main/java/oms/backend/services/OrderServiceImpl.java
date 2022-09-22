package oms.backend.services;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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
                    if (exists.get().getOrderStatus().equals("Ordered")) {
                        exists.get().setDateShipped(dtf.format(now));
                        exists.get().setOrderStatus(status);
                        repo.save(exists.get());
                        return true;
                    }
                    return false;

                case "Delivered":
                    if (exists.get().getOrderStatus().equals("Shipped")) {
                        exists.get().setDateDelivered(dtf.format(now));
                        exists.get().setOrderStatus(status);
                        repo.save(exists.get());
                        return true;
                    }
                    return false;

                case "Canceled":
                    if (exists.get().getOrderStatus().equals("Ordered")) {
                        System.out.println(exists.get().getOrderStatus());
                        exists.get().setOrderStatus(status);
                        repo.save(exists.get());
                        return true;
                    }
                    return false;

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

	@Override
	public boolean createOrder(Order order) {
		order.setOrderStatus("Pending");
		Order newlyCreatedOrder = repo.save(order);
		if(newlyCreatedOrder != null) {
			return true;
		} else {
			return false;
		}		
	}
    @Override
    public List<Order> getOrdersUserId(int id) {
        List<Order> orders = new ArrayList<Order>();
        List<Order> exist = repo.findAll();
        if(exist.isEmpty()){
            return exist;
        }else{
            for(Order order:exist){
                if(order.getUserId()==id){
                    orders.add(order);
                }
            }
        }
        return orders;
    }
}
