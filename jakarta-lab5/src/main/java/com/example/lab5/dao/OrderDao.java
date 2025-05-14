package com.example.lab5.dao;

import com.example.lab5.entity.Order;
import jakarta.ejb.Local;

import java.util.List;

@Local
public interface OrderDao extends AbstractDao<Order> {
    List<Order> findFiltered(Boolean processed, int page, int size);
}
