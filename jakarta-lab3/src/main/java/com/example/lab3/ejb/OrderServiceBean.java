package com.example.lab3.ejb;

import com.example.lab3.model.Order;
import com.example.lab3.service.DaoFactoryProviderService;
import com.example.lab3.service.OrderService;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;

import java.util.ArrayList;
import java.util.List;

@Stateless
public class OrderServiceBean implements OrderService {
    @EJB
    DaoFactoryProviderService daoFactoryProvider;

    @Override
    public List<Order> findAll() {
        return new ArrayList<>(daoFactoryProvider.getDaoFactory().getOrderDao().getAll());
    }

    @Override
    public Order findById(int id) {
        return daoFactoryProvider.getDaoFactory().getOrderDao().get(id);
    }

    @Override
    public void create(Order order) {
        daoFactoryProvider.getDaoFactory().getOrderDao().create(order);
    }

    @Override
    public void edit(Order updatedOrder) {
        daoFactoryProvider.getDaoFactory().getOrderDao().update(updatedOrder);
    }

    @Override
    public void delete(int id) {
        Order order = daoFactoryProvider.getDaoFactory().getOrderDao().get(id);
        if (order != null) daoFactoryProvider.getDaoFactory().getOrderDao().delete(order);
    }
}
