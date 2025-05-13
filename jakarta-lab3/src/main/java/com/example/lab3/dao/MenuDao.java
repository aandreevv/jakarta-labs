package com.example.lab3.dao;

import com.example.lab3.model.Menu;
import com.example.lab3.model.MenuItem;

public interface MenuDao extends AbstractDao<Menu> {
    void addToMenu(Menu menu, MenuItem menuItem);
    void removeFromMenu(Menu menu, MenuItem menuItem);
}
