package com.example.lab4.dao.impl.jpa;

import com.example.lab4.dao.OrderDao;
import com.example.lab4.entity.Order;
import jakarta.ejb.Stateless;

@Stateless
public class JpaOrderDao extends JpaAbstractDao<Order> implements OrderDao {
    JpaOrderDao() {
        super(Order.class);
    }
}
