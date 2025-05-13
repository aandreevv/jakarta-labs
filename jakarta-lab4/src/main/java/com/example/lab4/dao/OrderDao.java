package com.example.lab4.dao;

import com.example.lab4.entity.Order;
import jakarta.ejb.Local;

@Local
public interface OrderDao extends AbstractDao<Order> { }
