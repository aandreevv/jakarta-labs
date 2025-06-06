package com.example.lab4.service;

import com.example.lab4.entity.Order;
import jakarta.ejb.Local;

import java.util.List;

@Local
public interface OrderService {
    List<Order> findAll();
    Order findById(int id);
    void create(Order order);
    void edit(Order order);
    void delete(int id);
}