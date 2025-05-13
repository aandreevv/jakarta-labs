package com.example.lab3.dao;

public interface DaoFactory {
    MenuItemDao getMenuItemDao();
    OrderDao getOrderDao();
    MenuDao getMenuDao();
}
