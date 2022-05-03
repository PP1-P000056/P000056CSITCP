package com.example.project000056.controller;
import com.example.project000056.model.Order;
import com.example.project000056.model.User;
import com.example.project000056.payload.request.OrderRequest;
import com.example.project000056.payload.response.MessageResponse;
import com.example.project000056.repository.OrderRepository;
import com.example.project000056.singleton.userHolder;
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
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000" )
@RestController
@RequestMapping("/api/auth/orders")
public class OrderController {
    @Autowired
    OrderRepository orderRepository;
    private userHolder userHolder;
    private User user;
    @Autowired
    public OrderController(OrderRepository orderRepository) {

        this.orderRepository = orderRepository;
    }

    @PostMapping(value="/create",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> createOrder(@ModelAttribute OrderRequest orderRequest, @RequestParam("returnLabel") MultipartFile file) {
        try {
            // get uploaded filename
            String fileName = StringUtils.cleanPath(file.getOriginalFilename());

            // get logged in user
            userHolder = userHolder.getInstance();
            user = userHolder.getUser();
            System.out.println(user.getId());
            // then create order
            Order newOrder = new Order(orderRequest.getSender_name(),orderRequest.getSender_phone(),orderRequest.getSender_address(),
                    orderRequest.getReceiver_name(),orderRequest.getReceiver_phone(),orderRequest.getReceiver_address(),
                    orderRequest.getProduct_type(), orderRequest.getProduct_weight(), orderRequest.getPickup_date(),orderRequest.getPickup_time(),
                    fileName,file.getContentType(),file.getBytes(),user.getId());
            orderRepository.save(newOrder);
            return ResponseEntity.ok(new MessageResponse("Order created successfully!"));
        } catch (Exception e) {
            return ResponseEntity.ok(new MessageResponse("Order failed!"));
        }

    }

    //get Order by UserID
    @GetMapping("/getAll")
    List<Order> getOrderByUserId(@RequestParam(value = "userid") Long userid) {
        return orderRepository.findOrderByUserID(userid);
    }

}