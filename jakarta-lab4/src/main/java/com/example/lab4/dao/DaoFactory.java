package com.example.lab4.dao;

import jakarta.ejb.Local;

@Local
public interface DaoFactory {
    MenuItemDao getMenuItemDao();
    OrderDao getOrderDao();
    MenuDao getMenuDao();
}
