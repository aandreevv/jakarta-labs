package com.example.lab3.ejb;

import com.example.lab3.dao.DaoFactory;
import com.example.lab3.dao.impl.inmemory.InMemoryDb;
import com.example.lab3.dao.impl.inmemory.InMemoryTestData;
import com.example.lab3.service.DaoFactoryProviderService;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;

@Singleton
@Startup
public class DaoFactoryProvider implements DaoFactoryProviderService {
    private DaoFactory daoFactory;

    @PostConstruct
    private void init() {
        InMemoryDb db = new InMemoryDb();
        InMemoryTestData.generateTo(db);
        daoFactory = db.getDaoFactory();
    }

    public DaoFactory getDaoFactory() {
        return daoFactory;
    }
}
