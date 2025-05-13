package com.example.lab3.dao.impl.inmemory;

import com.example.lab3.dao.DaoFactory;
import com.example.lab3.dao.MenuDao;
import com.example.lab3.dao.MenuItemDao;
import com.example.lab3.dao.OrderDao;

public class InMemoryDaoFactory implements DaoFactory {
    InMemoryDb db;

    MenuItemDao menuItemDao;
    OrderDao orderDao;
    MenuDao menuDao;

    public InMemoryDaoFactory(InMemoryDb db) {
        this.db = db;
        this.menuDao = new InMemoryMenuDao(db);
        this.menuItemDao = new InMemoryMenuItemDao(db);
        this.orderDao = new InMemoryOrderDao(db);
    }

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
