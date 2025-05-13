package com.example.lab3.service;

import com.example.lab3.model.Menu;
import com.example.lab3.model.MenuItem;
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
