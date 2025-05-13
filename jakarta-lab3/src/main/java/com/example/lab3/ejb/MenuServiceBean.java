package com.example.lab3.ejb;

import com.example.lab3.model.Menu;
import com.example.lab3.model.MenuItem;
import com.example.lab3.service.DaoFactoryProviderService;
import com.example.lab3.service.MenuService;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;

import java.util.ArrayList;
import java.util.List;


@Stateless
public class MenuServiceBean implements MenuService {
    @EJB
    DaoFactoryProviderService daoFactoryProvider;

    @Override
    public void create(Menu menu) {
        daoFactoryProvider.getDaoFactory().getMenuDao().create(menu);
    }

    @Override
    public void delete(int id) {
        Menu menu = daoFactoryProvider.getDaoFactory().getMenuDao().get(id);
        if (menu != null) daoFactoryProvider.getDaoFactory().getMenuDao().delete(menu);
    }

    @Override
    public List<Menu> findAll() {
        return new ArrayList<Menu>(daoFactoryProvider.getDaoFactory().getMenuDao().getAll());
    }

    @Override
    public Menu findById(int id) {
        return daoFactoryProvider.getDaoFactory().getMenuDao().get(id);
    }

    @Override
    public void addItemToMenu(Menu menu, MenuItem menuItem) {
        daoFactoryProvider.getDaoFactory().getMenuDao().addToMenu(menu, menuItem);
    }

    @Override
    public void removeItemFromMenu(Menu menu, MenuItem menuItem) {
        daoFactoryProvider.getDaoFactory().getMenuDao().removeFromMenu(menu, menuItem);
    }
}
