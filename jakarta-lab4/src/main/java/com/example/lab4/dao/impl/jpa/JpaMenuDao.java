package com.example.lab4.dao.impl.jpa;

import com.example.lab4.dao.MenuDao;
import com.example.lab4.entity.Menu;
import jakarta.ejb.Stateless;

@Stateless
public class JpaMenuDao extends JpaAbstractDao<Menu> implements MenuDao {
    public JpaMenuDao() {
        super(Menu.class);
    }
}
