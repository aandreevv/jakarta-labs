package com.example.lab3.dao.impl.inmemory;

import com.example.lab3.dao.DaoFactory;
import com.example.lab3.model.Menu;
import com.example.lab3.model.MenuItem;
import com.example.lab3.model.Order;

import java.util.Map;
import java.util.TreeMap;

public class InMemoryDb {
    Map<Integer, Order> orders;
    Map<Integer, MenuItem> menuItems;
    Map<Integer, Menu> menus;

    public InMemoryDb() {
        this.orders = new TreeMap<>();
        this.menuItems = new TreeMap<>();
        this.menus = new TreeMap<>();
    }

    public DaoFactory getDaoFactory() {
        return new InMemoryDaoFactory(this);
    }
}
