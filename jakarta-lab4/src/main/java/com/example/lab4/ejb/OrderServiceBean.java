package com.example.lab4.ejb;

import com.example.lab4.dao.DaoFactory;
import com.example.lab4.entity.Order;
import com.example.lab4.entity.OrderItem;
import com.example.lab4.service.OrderService;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import jakarta.ejb.TransactionAttribute;
import jakarta.ejb.TransactionAttributeType;

import java.util.ArrayList;
import java.util.List;

@Stateless
public class OrderServiceBean implements OrderService {
    @EJB
    DaoFactory daoFactory;

    @Override
    public List<Order> findAll() {
        return new ArrayList<>(daoFactory.getOrderDao().getAll());
    }

    @Override
    public Order findById(int id) {
        return daoFactory.getOrderDao().get(id);
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void create(Order order) {
        try {
            for (OrderItem item : order.getItems()) {
                item.setOrder(order);
            }
            daoFactory.getOrderDao().create(order);
            if (order.getItems().isEmpty()) {
                throw new RuntimeException("Cannot create order without items. Rolling back transaction.");
            }
        } catch (Exception e) {
            throw new RuntimeException("Order Transaction failed", e);
        }
    }

    @Override
    public void edit(Order updatedOrder) {
        daoFactory.getOrderDao().update(updatedOrder);
    }

    @Override
    public void delete(int id) {
        Order order = daoFactory.getOrderDao().get(id);
        if (order != null) daoFactory.getOrderDao().delete(order);
    }
}
