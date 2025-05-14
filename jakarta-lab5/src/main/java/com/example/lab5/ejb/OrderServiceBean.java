package com.example.lab5.ejb;

import com.example.lab5.dao.DaoFactory;
import com.example.lab5.entity.MenuItem;
import com.example.lab5.entity.Order;
import com.example.lab5.entity.OrderItem;
import com.example.lab5.service.OrderService;
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
            if (order.getItems() != null) {
                for (OrderItem item : order.getItems()) {
                    item.setOrder(order);
                }
            }
            daoFactory.getOrderDao().create(order);
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

    @Override
    public List<Order> findFiltered(Boolean processed, int page, int size) {
        return daoFactory.getOrderDao().findFiltered(processed, page, size);
    }

    @Override
    public void addItemToOrder(Order order, MenuItem menuItem) {
        OrderItem orderItem = new OrderItem();
        orderItem.setMenuItem(menuItem);
        orderItem.setOrder(order);
        order.getItems().add(orderItem);
        this.edit(order);
    }

    @Override
    public void removeItemFromOrder(OrderItem orderItem) {
        if (orderItem != null) {
            Order order = orderItem.getOrder();
            order.getItems().remove(orderItem);
            this.edit(order);
        }
    }
}
