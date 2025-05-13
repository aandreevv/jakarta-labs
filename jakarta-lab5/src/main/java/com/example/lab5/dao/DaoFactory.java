package com.example.lab5.dao;

import jakarta.ejb.Local;

@Local
public interface DaoFactory {
    MenuItemDao getMenuItemDao();
    OrderDao getOrderDao();
    MenuDao getMenuDao();
}
