package oms.backend.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import oms.backend.models.Order;
import oms.backend.services.OrderService;

@RestController
@RequestMapping("/api/order")
public class OrderController {
    @Autowired
    OrderService service;

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

    @PutMapping("/updateOrder/delivered/{id}")
    public ResponseEntity<String> deliveredOrder(@PathVariable("id") int id) {
        boolean success = service.updateOrder(id, "Delivered");
        if (success) {
            return ResponseEntity.status(HttpStatus.OK).body(id + " Delivered successfully");
        }
        else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @GetMapping("/getOrders/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable("id") int id){
        Optional <Order> order = service.getOrderById(id);
        if(order.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(order.get());
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }

    @GetMapping("/getOrders")
    public List<Order> getOrders(){
        return service.getOrders();
    }
    
    @PostMapping("/createOrder")
    public ResponseEntity<String> createOrder(@RequestBody Order order){
    	boolean success = service.createOrder(order);
    	if(success) {
    		return ResponseEntity.status(HttpStatus.OK).body("Order created");
    	} else {
    		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    	}
    }
}