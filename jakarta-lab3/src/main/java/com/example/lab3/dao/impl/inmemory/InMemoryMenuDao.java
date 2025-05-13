package com.example.lab3.dao.impl.inmemory;

import com.example.lab3.dao.MenuDao;
import com.example.lab3.model.Menu;
import com.example.lab3.model.MenuItem;

import java.util.ArrayList;

public class InMemoryMenuDao extends InMemoryAbstractDao<Menu> implements MenuDao {
    InMemoryMenuDao(InMemoryDb db) {
        super(db, db.menus, Menu::getId, Menu::setId);
    }

    @Override
    public void addToMenu(Menu menu, MenuItem menuItem) {
        if (menu.getItems() == null) {
            menu.setItems(new ArrayList<>());
        }
        menu.getItems().add(menuItem);
        update(menu);
    }

    @Override
    public void removeFromMenu(Menu menu, MenuItem menuItem) {
        if (menu.getItems() != null) {
            menu.getItems().remove(menuItem);
            update(menu);
        }
    }
}
