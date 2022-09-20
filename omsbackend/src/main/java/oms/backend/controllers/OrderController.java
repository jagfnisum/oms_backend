package oms.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import oms.backend.models.Order;
import oms.backend.services.OrderService;

@RestController
public class OrderController {
    @Autowired
    OrderService service;

    public ResponseEntity<Order> updateOrder(Order order){
        boolean success = service.updateOrder(order);
        if (success) {
            return ResponseEntity.status(HttpStatus.OK).body(order);
        }
        else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }
}
