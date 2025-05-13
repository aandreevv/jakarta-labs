package com.example.lab4.dao.impl.jpa;

import com.example.lab4.dao.MenuItemDao;
import com.example.lab4.entity.Menu;
import com.example.lab4.entity.MenuItem;
import jakarta.ejb.Stateless;

import java.util.List;

@Stateless
public class JpaMenuItemDao extends JpaAbstractDao<MenuItem> implements MenuItemDao {
    public JpaMenuItemDao() {
        super(MenuItem.class);
    }

    @Override
    public List<com.example.lab4.entity.MenuItem> findByName(String name) {
        return em.createQuery(
                "SELECT m FROM MenuItem m WHERE LOWER(m.name) LIKE LOWER(CONCAT('%', :name, '%'))",
                        MenuItem.class)
                .setParameter("name", name)
                .getResultList();
    }

    @Override
    public List<com.example.lab4.entity.MenuItem> findByName(String name, Menu menu) {
        return em.createQuery(
                "SELECT m FROM MenuItem m WHERE LOWER(m.name) LIKE LOWER(CONCAT('%', :name, '%')) AND m.menu = :menu",
                        MenuItem.class)
                .setParameter("name", name)
                .setParameter("menu", menu)
                .getResultList();
    }
}
