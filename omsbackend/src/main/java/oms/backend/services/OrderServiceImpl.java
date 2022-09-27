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
    

    /**
     * This method allows us to update the status of an order that
     * is already in the database. The status should be in one 
     * of three status: "Ordered" "Shipped" "Canceled"
     * It will also update the date status if an order has been changed.
     * @param id the id of the order to be updated
     * @param status the status we want to update our current order to
     * @return true or false depending on whether or not
     * the order was able to be updated to our new status
     */
    @Override
    public boolean updateOrder(int id, String status) {
        Optional<Order> exists = repo.findById(id);
        if (exists.isPresent()) {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();
            switch (status) {
                case "Ordered":
                    if (exists.get().getOrderStatus().equals("Pending")){
                        exists.get().setDateOrdered(dtf.format(now));
                        exists.get().setOrderStatus(status);
                        repo.save(exists.get());
                        return true;
                    }
                    return false;

                case "Shipped":
                    if (exists.get().getOrderStatus().equals("Pending")) {
                        exists.get().setDateShipped(dtf.format(now));
                        exists.get().setOrderStatus(status);
                        repo.save(exists.get());
                        return true;
                    }
                    return false;
                    
                case "Canceled":
                    if (exists.get().getOrderStatus().equals("Pending")) {
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

    /**
     * This order returns an optional that will contain an order
     * if the order of the given id exists
     * @param id the id of the order to find
     * @return an optional. if the order exists,
     */
    @Override
    public Optional<Order> getOrderById(int id) {
        Optional<Order> exists = repo.findById(id);
        return exists;
    }

    /**
     * This method returns a list with all orders from the database
     * @return a list with all orders in the order database
     */
    @Override
    public List<Order> getOrders() {
        return repo.findAll();
    }

    /**
     * This method will insert an order into the database and
     * set the default status of the order to pending.
     * @param order an order item that is to be added to our database
     * @return boolean true or false depending on whnether or not the
     * new order was able to created
     */
	@Override
	public boolean createOrder(Order order) {		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();       
        order.setDateOrdered(dtf.format(now));
        order.setOrderStatus("Pending");
		order.setDateShipped(null);		
		System.out.println(order);
		Order newlyCreatedOrder = repo.save(order);
		if(newlyCreatedOrder != null) {
			return true;
		} else {
			return false;
		}		
	}
    /**
     * This method takes in a user id and returns all orders
     * that the user of that id has made
     * @param id the id of the userid we want to finder orders for
     * @return a list of orders that this user has made
     */
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
