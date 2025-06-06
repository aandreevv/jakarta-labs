package com.example.lab5.service;

import com.example.lab5.entity.Menu;
import com.example.lab5.entity.MenuItem;
import jakarta.ejb.Local;

import java.util.List;

@Local
public interface MenuItemService {
    List<MenuItem> findAll();
    MenuItem findById(int id);
    List<MenuItem> findByName(String name);
    List<MenuItem> findByName(String name, Menu menu);
    void create(MenuItem menuItem);
    void edit(MenuItem menuItem);
    void delete(int id);
}
