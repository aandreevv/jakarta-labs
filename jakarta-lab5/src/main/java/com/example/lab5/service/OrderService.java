package com.example.lab5.service;

import com.example.lab5.entity.MenuItem;
import com.example.lab5.entity.Order;
import com.example.lab5.entity.OrderItem;
import jakarta.ejb.Local;

import java.util.List;

@Local
public interface OrderService {
    List<Order> findAll();
    Order findById(int id);
    void create(Order order);
    void edit(Order order);
    void delete(int id);
    List<Order> findFiltered(Boolean processed, int page, int size);
    void addItemToOrder(Order order, MenuItem menuItem);
    void removeItemFromOrder(OrderItem orderItem);
}