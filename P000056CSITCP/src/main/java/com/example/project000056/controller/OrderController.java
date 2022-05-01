package com.example.project000056.controller;
import com.example.project000056.model.Order;
import com.example.project000056.payload.request.OrderRequest;
import com.example.project000056.payload.response.MessageResponse;
import com.example.project000056.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

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

    @PostMapping(value="/create",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> createOrder(@ModelAttribute OrderRequest orderRequest, @RequestParam("file") MultipartFile file) {
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
        try {
            String fileName = StringUtils.cleanPath(file.getOriginalFilename());
            Order newOrder = new Order(orderRequest.getSender_name(),orderRequest.getSender_phone(),orderRequest.getSender_address(),
                    orderRequest.getReceiver_name(),orderRequest.getReceiver_phone(),orderRequest.getReceiver_address(),
                    orderRequest.getProduct_type(), orderRequest.getProduct_weight(), orderRequest.getPickup_date(),orderRequest.getPickup_time(),
                    fileName,file.getContentType(),file.getBytes());
            orderRepository.save(newOrder);
            return ResponseEntity.ok(new MessageResponse("Order created successfully!"));
        } catch (Exception e) {
            return ResponseEntity.ok(new MessageResponse("Order failed!"));
        }

    }
}