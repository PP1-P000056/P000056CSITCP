package com.example.project000056.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String sender_name;
    private String sender_phone;
    private String sender_address;
    private String receiver_name;
    private String receiver_phone;
    private String receiver_address;
    private String product_type;
    private int product_weight;
    private String pickup_time;
    private int delivery_status;
    //    private int user_id;
//    private int driver_id;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(	name = "order_user",
            joinColumns = @JoinColumn(
                    name = "order_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "user_id", referencedColumnName = "id")
    )
    Set<User> user = new HashSet<>();

//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "user_id", referencedColumnName = "id")
//    private User user;
// lombok dependency, use lombok for getters, setters, constructor, etc.

    public Order() {

    }

    public Order(String sender_name, String sender_phone) {
        this.sender_name = sender_name;
        this.sender_phone = sender_phone;
    }

    public Order(String sender_name, String sender_phone, String sender_address, String receiver_name, String receiver_phone, String receiver_address, String product_type, int product_weight, String pickup_time, int delivery_status) {
        this.sender_name = sender_name;
        this.sender_phone = sender_phone;
        this.sender_address = sender_address;
        this.receiver_name = receiver_name;
        this.receiver_phone = receiver_phone;
        this.receiver_address = receiver_address;
        this.product_type = product_type;
        this.product_weight = product_weight;
        this.pickup_time = pickup_time;
        this.delivery_status = delivery_status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSender_name() {
        return sender_name;
    }

    public void setSender_name(String sender_name) {
        this.sender_name = sender_name;
    }

    public String getSender_phone() {
        return sender_phone;
    }

    public void setSender_phone(String sender_phone) {
        this.sender_phone = sender_phone;
    }

    public String getSender_address() {
        return sender_address;
    }

    public void setSender_address(String sender_address) {
        this.sender_address = sender_address;
    }

    public String getReceiver_name() {
        return receiver_name;
    }

    public void setReceiver_name(String receiver_name) {
        this.receiver_name = receiver_name;
    }

    public String getReceiver_phone() {
        return receiver_phone;
    }

    public void setReceiver_phone(String receiver_phone) {
        this.receiver_phone = receiver_phone;
    }

    public String getReceiver_address() {
        return receiver_address;
    }

    public void setReceiver_address(String receiver_address) {
        this.receiver_address = receiver_address;
    }

    public String getProduct_type() {
        return product_type;
    }

    public void setProduct_type(String product_type) {
        this.product_type = product_type;
    }

    public int getProduct_weight() {
        return product_weight;
    }

    public void setProduct_weight(int product_weight) {
        this.product_weight = product_weight;
    }

    public String getPickup_time() {
        return pickup_time;
    }

    public void setPickup_time(String pickup_time) {
        this.pickup_time = pickup_time;
    }

    public int getDelivery_status() {
        return delivery_status;
    }

    public void setDelivery_status(int delivery_status) {
        this.delivery_status = delivery_status;
    }
//
//    public int getUser_id() {
//        return user_id;
//    }
//
//    public void setUser_id(int user_id) {
//        this.user_id = user_id;
//    }
//
//    public int getDriver_id() {
//        return driver_id;
//    }
//
//    public void setDriver_id(int driver_id) {
//        this.driver_id = driver_id;
//    }
}