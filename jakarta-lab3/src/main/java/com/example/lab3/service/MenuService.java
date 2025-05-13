package com.example.lab3.service;

import com.example.lab3.model.Menu;
import com.example.lab3.model.MenuItem;
import jakarta.ejb.Local;

import java.util.List;

@Local
public interface MenuService {
    void create(Menu menu);
    void delete(int id);
    List<Menu> findAll();
    Menu findById(int id);
    void addItemToMenu(Menu menu, MenuItem menuItem);
    void removeItemFromMenu(Menu menu, MenuItem menuItem);
}