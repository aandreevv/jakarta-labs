package com.example.lab4.dao;

import com.example.lab4.entity.Menu;
import com.example.lab4.entity.MenuItem;
import jakarta.ejb.Local;

import java.util.List;

@Local
public interface MenuItemDao extends AbstractDao<MenuItem> {
    List<MenuItem> findByName(String name);
    List<MenuItem> findByName(String name, Menu menu);
}
