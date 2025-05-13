package com.example.lab4.service;

import com.example.lab4.entity.Menu;
import com.example.lab4.entity.MenuItem;
import jakarta.ejb.Local;

import java.util.List;

@Local
public interface MenuItemService {
    List<MenuItem> findAll();
    MenuItem findById(int id);
    List<MenuItem> findByName(String name);
    List<MenuItem> findByName(String name, Menu menu);
    void create(MenuItem order);
    void edit(MenuItem order);
    void delete(int id);
}
