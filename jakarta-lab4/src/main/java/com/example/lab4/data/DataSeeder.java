package com.example.lab4.data;

import jakarta.ejb.Local;

@Local
public interface DataSeeder {
    void seed();
}
