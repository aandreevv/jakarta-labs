package com.example.lab4.ejb;

import com.example.lab4.dao.DaoFactory;
import com.example.lab4.entity.Menu;
import com.example.lab4.entity.MenuItem;
import com.example.lab4.service.MenuService;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;

import java.util.ArrayList;
import java.util.List;


@Stateless
public class MenuServiceBean implements MenuService {
    @EJB
    DaoFactory daoFactory;

    @Override
    public void create(Menu menu) {
        daoFactory.getMenuDao().create(menu);
    }

    @Override
    public void delete(int id) {
        Menu menu = daoFactory.getMenuDao().get(id);
        if (menu != null) daoFactory.getMenuDao().delete(menu);
    }

    @Override
    public List<Menu> findAll() {
        return new ArrayList<Menu>(daoFactory.getMenuDao().getAll());
    }

    @Override
    public Menu findById(int id) {
        return daoFactory.getMenuDao().get(id);
    }

    @Override
    public void addItemToMenu(Menu menu, MenuItem menuItem) {
        daoFactory.getMenuDao().addToMenu(menu, menuItem);
    }

    @Override
    public void removeItemFromMenu(Menu menu, MenuItem menuItem) {
        daoFactory.getMenuDao().removeFromMenu(menu, menuItem);
    }
}
