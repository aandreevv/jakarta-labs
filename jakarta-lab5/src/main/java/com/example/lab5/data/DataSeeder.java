package com.example.lab5.data;

import jakarta.ejb.Local;

@Local
public interface DataSeeder {
    void seed();
}
