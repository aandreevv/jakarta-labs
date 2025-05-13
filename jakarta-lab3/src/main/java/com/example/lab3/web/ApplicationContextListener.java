package com.example.lab3.web;

import com.example.lab3.dao.DaoFactory;
import com.example.lab3.dao.impl.inmemory.InMemoryDb;
import com.example.lab3.dao.impl.inmemory.InMemoryTestData;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class ApplicationContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        InMemoryDb db = new InMemoryDb();
        InMemoryTestData.generateTo(db);

        DaoFactory daoFactory = db.getDaoFactory();
        sce.getServletContext().setAttribute("daoFactory", daoFactory);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }
}
