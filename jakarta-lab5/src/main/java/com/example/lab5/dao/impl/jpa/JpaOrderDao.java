package com.example.lab5.dao.impl.jpa;

import com.example.lab5.dao.OrderDao;
import com.example.lab5.entity.Order;
import jakarta.ejb.Stateless;

@Stateless
public class JpaOrderDao extends JpaAbstractDao<Order> implements OrderDao {
    public JpaOrderDao() {
        super(Order.class);
    }
}
