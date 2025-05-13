package com.example.lab5.dao;

import com.example.lab5.entity.Order;
import jakarta.ejb.Local;

@Local
public interface OrderDao extends AbstractDao<Order> { }
