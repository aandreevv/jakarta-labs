package com.example.lab3.service;

import com.example.lab3.dao.DaoFactory;
import jakarta.ejb.Local;

@Local
public interface DaoFactoryProviderService {
    DaoFactory getDaoFactory();
}
