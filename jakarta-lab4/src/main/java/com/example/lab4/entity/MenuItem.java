package com.example.lab4.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "menu_items")
public class MenuItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private String description;

    private double price;

    @ManyToOne
    @JoinColumn(name = "menu_id")
    private Menu menu;

    public MenuItem() {}

    public MenuItem(int id, String name, String description, double price, Menu menu) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.menu = menu;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        MenuItem menuItem = (MenuItem) o;
        return id == menuItem.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    public MenuItem(String name, String description, double price, Menu menu) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.menu = menu;
    }
}
