package com.example.lab4.ejb;

import com.example.lab4.dao.DaoFactory;
import com.example.lab4.entity.Menu;
import com.example.lab4.entity.MenuItem;
import com.example.lab4.service.MenuItemService;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;

import java.util.ArrayList;
import java.util.List;

@Stateless
public class MenuItemServiceBean implements MenuItemService {
    @EJB
    DaoFactory daoFactory;

    @Override
    public List<MenuItem> findAll() {
        return new ArrayList<>(daoFactory.getMenuItemDao().getAll());
    }

    @Override
    public MenuItem findById(int id) {
        return daoFactory.getMenuItemDao().get(id);
    }

    @Override
    public List<MenuItem> findByName(String name) {
        return daoFactory.getMenuItemDao().findByName(name);
    }

    @Override
    public List<MenuItem> findByName(String name, Menu menu) {
        return daoFactory.getMenuItemDao().findByName(name, menu);
    }

    @Override
    public void create(MenuItem menuItem) {
        daoFactory.getMenuItemDao().create(menuItem);
    }

    @Override
    public void edit(MenuItem menuItem) {
        daoFactory.getMenuItemDao().update(menuItem);
    }

    @Override
    public void delete(int id) {
        MenuItem menuItem = daoFactory.getMenuItemDao().get(id);
        if (menuItem != null) daoFactory.getMenuItemDao().delete(menuItem);
    }
}
