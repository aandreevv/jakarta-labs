package com.example.lab3.dao.impl.inmemory;

import com.example.lab3.dao.MenuItemDao;
import com.example.lab3.model.Menu;
import com.example.lab3.model.MenuItem;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class InMemoryMenuItemDao extends InMemoryAbstractDao<MenuItem> implements MenuItemDao {
    InMemoryMenuItemDao(InMemoryDb db) {
        super(db, db.menuItems, MenuItem::getId, MenuItem::setId);
    }

    @Override
    public List<MenuItem> findByName(String name) {
        return filterByName(name, db.menuItems.values());
    }

    @Override
    public List<MenuItem> findByName(String name, Menu menu) {
        return filterByName(name, menu.getItems());
    }

    private List<MenuItem> filterByName(String name, Collection<MenuItem> items) {
        List<MenuItem> results = new ArrayList<>();
        for (MenuItem item : items) {
            if (item.getName().toLowerCase().contains(name.toLowerCase())) {
                results.add(item);
            }
        }
        return results;
    }

}
