package com.example.lab3.dao.impl.inmemory;

import com.example.lab3.dao.OrderDao;
import com.example.lab3.model.Order;

public class InMemoryOrderDao extends InMemoryAbstractDao<Order> implements OrderDao {
    InMemoryOrderDao(InMemoryDb db) {
        super(db, db.orders, Order::getId, Order::setId);
    }
}
