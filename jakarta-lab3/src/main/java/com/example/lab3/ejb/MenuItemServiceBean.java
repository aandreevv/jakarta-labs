package com.example.lab3.ejb;

import com.example.lab3.model.Menu;
import com.example.lab3.model.MenuItem;
import com.example.lab3.service.DaoFactoryProviderService;
import com.example.lab3.service.MenuItemService;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;

import java.util.ArrayList;
import java.util.List;

@Stateless
public class MenuItemServiceBean implements MenuItemService {
    @EJB
    DaoFactoryProviderService daoFactoryProvider;

    @Override
    public List<MenuItem> findAll() {
        return new ArrayList<>(daoFactoryProvider.getDaoFactory().getMenuItemDao().getAll());
    }

    @Override
    public MenuItem findById(int id) {
        return daoFactoryProvider.getDaoFactory().getMenuItemDao().get(id);
    }

    @Override
    public List<MenuItem> findByName(String name) {
        return daoFactoryProvider.getDaoFactory().getMenuItemDao().findByName(name);
    }

    @Override
    public List<MenuItem> findByName(String name, Menu menu) {
        return daoFactoryProvider.getDaoFactory().getMenuItemDao().findByName(name, menu);
    }

    @Override
    public void create(MenuItem order) {
        daoFactoryProvider.getDaoFactory().getMenuItemDao().create(order);
    }

    @Override
    public void edit(MenuItem order) {
        daoFactoryProvider.getDaoFactory().getMenuItemDao().update(order);
    }

    @Override
    public void delete(int id) {
        MenuItem menuItem = daoFactoryProvider.getDaoFactory().getMenuItemDao().get(id);
        if (menuItem != null) daoFactoryProvider.getDaoFactory().getMenuItemDao().delete(menuItem);
    }
}
