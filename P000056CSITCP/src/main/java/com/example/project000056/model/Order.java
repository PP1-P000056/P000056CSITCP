package com.example.project000056.model;

import java.time.LocalDate;

public class Order {
    private int id;
    private String sender_name;
    private int sender_phone;
    private String sender_address;
    private String receiver_name;
    private int receiver_phone;
    private String receiver_address;
    private String product_type;
    private String product_weight;
    private LocalDate pickup_time;
    private int delivery_status;
    private int user_id;
    private int driver_id;


    public Order() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSender_name() {
        return sender_name;
    }

    public void setSender_name(String sender_name) {
        this.sender_name = sender_name;
    }

    public int getSender_phone() {
        return sender_phone;
    }

    public void setSender_phone(int sender_phone) {
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

    public int getReceiver_phone() {
        return receiver_phone;
    }

    public void setReceiver_phone(int receiver_phone) {
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

    public String getProduct_weight() {
        return product_weight;
    }

    public void setProduct_weight(String product_weight) {
        this.product_weight = product_weight;
    }

    public LocalDate getPickup_time() {
        return pickup_time;
    }

    public void setPickup_time(LocalDate pickup_time) {
        this.pickup_time = pickup_time;
    }

    public int getDelivery_status() {
        return delivery_status;
    }

    public void setDelivery_status(int delivery_status) {
        this.delivery_status = delivery_status;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getDriver_id() {
        return driver_id;
    }

    public void setDriver_id(int driver_id) {
        this.driver_id = driver_id;
    }
}