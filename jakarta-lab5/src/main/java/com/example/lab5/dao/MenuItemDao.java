package com.example.lab5.dao;

import com.example.lab5.entity.Menu;
import com.example.lab5.entity.MenuItem;
import jakarta.ejb.Local;

import java.util.List;

@Local
public interface MenuItemDao extends AbstractDao<MenuItem> {
    List<MenuItem> findByName(String name);
    List<MenuItem> findByName(String name, Menu menu);
}
