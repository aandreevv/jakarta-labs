package com.example.lab3.dao;

import com.example.lab3.model.Menu;
import com.example.lab3.model.MenuItem;

import java.util.List;

public interface MenuItemDao extends AbstractDao<MenuItem> {
    List<MenuItem> findByName(String name);
    List<MenuItem> findByName(String name, Menu menu);
}
