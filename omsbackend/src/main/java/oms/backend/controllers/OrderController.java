package oms.backend.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import oms.backend.models.Order;
import oms.backend.services.OrderService;

@CrossOrigin
@RestController
@RequestMapping("/api/order")
public class OrderController {
    @Autowired
    OrderService service;

    /**This method takes in an integer id and lets us update
     * the order to be labeled as shipped
     * @param id an integer that reprsents the orderid of the order we want to update to shipped
     * @return This method returns a response entity in the form a 
     * string that specifies the order id number and if it has shipped
     * successfully. If not, we return a bad request.
     */
    @PutMapping("/updateOrder/shipped/{id}")
    public ResponseEntity<String> shippedOrder(@PathVariable("id") int id){
        boolean success = service.updateOrder(id, "Shipped");
        if (success) {
            return ResponseEntity.status(HttpStatus.OK).body(id + " Shipped successfully");
        }
        else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }


    /**
     * This method takes in an integer id and lets us update the order to be
     * labeled as canceled
     * @param id an integer that represents the id of the order we want canceled
     * @return This method returns a response entity in the form a 
     * string that specifies the order id number and if it has been cancelled
     * successfully. If not, we return a bad request.
     */
    @PutMapping("/updateOrder/cancelled/{id}")
    public ResponseEntity<String> cancelledOrder(@PathVariable("id") int id){
        boolean success = service.updateOrder(id, "Canceled");
        if (success) {
            return ResponseEntity.status(HttpStatus.OK).body(id + " Cancelled successfully");
        }
        else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @PutMapping("/updateOrder/ordered/{id}")
    public ResponseEntity<String> orderedOrder(@PathVariable("id") int id){
        boolean success = service.updateOrder(id, "Ordered");
        if (success) {
            return ResponseEntity.status(HttpStatus.OK).body(id + " Ordered successfully");
        }
        else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }
    /**
     * This method gets orders with the specified id
     * @param id an integer that represents the id of the order we want to view
     * @return This method returns an response entity in the form of an order
     * that represents the order we want to view. If there are no orders, we return
     * a bad request.
     */
    @GetMapping("/getOrders/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable("id") int id){
        Optional <Order> order = service.getOrderById(id);
        if(order.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(order.get());
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }

    /**
     * This method gets all orders in the database and displays it
     * @return a list with all orders in our database. If there is nothing
     * in the database, it returns no content as the http status
     */
    @GetMapping("/getOrders")
    public ResponseEntity<List<Order>> getOrders(){
        List<Order> orders = service.getOrders();
        if (orders.size() > 0){
            return ResponseEntity.status(HttpStatus.OK).body(orders);
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

    /**
     * This method gets list of order from a particular user
     * @param id the id of the user
     * @return a list of orders that the user has made
     */
    @GetMapping("/getOrders/user/{id}")
    public ResponseEntity<List<Order>> getOrderInfo(@PathVariable("id") int id) {
        List<Order> orders=service.getOrdersUserId(id);
        if(orders.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.status(HttpStatus.OK).body(orders);
    }
    
    /**
     * This method allows the user to create an order and add it to the database.
     * @param order An order item that will be added to our database
     * request body should have followings: {}
     * @return a response entity that specifies if the order is created. If not,
     * we return a bad request
     */
    @PostMapping("/createOrder")
    public ResponseEntity<String> createOrder(@RequestBody Order order){
    	boolean success = service.createOrder(order);
    	if(success) {
    		return ResponseEntity.status(HttpStatus.CREATED).body("Order created");
    	} else {
    		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    	}
    }
}
