package com.example.lab5.dao.impl.jpa;

import com.example.lab5.dao.DaoFactory;
import com.example.lab5.dao.MenuDao;
import com.example.lab5.dao.MenuItemDao;
import com.example.lab5.dao.OrderDao;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;

@Stateless
public class JpaDaoFactory implements DaoFactory {
    @EJB
    MenuItemDao menuItemDao;

    @EJB
    OrderDao orderDao;

    @EJB
    MenuDao menuDao;

    @Override
    public MenuItemDao getMenuItemDao() {
        return menuItemDao;
    }

    @Override
    public OrderDao getOrderDao() {
        return orderDao;
    }

    @Override
    public MenuDao getMenuDao() {
        return menuDao;
    }
}
