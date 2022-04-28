package com.example.project000056.controller;
import com.example.project000056.model.Order;
import com.example.project000056.payload.request.OrderRequest;
import com.example.project000056.payload.response.MessageResponse;
import com.example.project000056.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth/orders")
public class OrderController {
    @Autowired
    OrderRepository orderRepository;

    @Autowired
    public OrderController(OrderRepository orderRepository) {

        this.orderRepository = orderRepository;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createOrder(@RequestBody OrderRequest orderRequest) {
//        Order order = new Order(orderRequest.getSender_name(),
//                orderRequest.getSender_phone(),
//                String.valueOf(orderRequest.getSender_phone()),
//                orderRequest.getReceiver_name(),
//                orderRequest.getReceiver_phone(),
//                orderRequest.getReceiver_address(),
//                orderRequest.getProduct_type(),
//                orderRequest.getProduct_weight(),
//                orderRequest.getPickup_time(),
//                orderRequest.getDelivery_status());
                Order order = new Order(orderRequest.getSender_name(),
                orderRequest.getSender_phone());
        orderRepository.save(order);
        return ResponseEntity.ok(new MessageResponse("Order created successfully!"));
    }
}
