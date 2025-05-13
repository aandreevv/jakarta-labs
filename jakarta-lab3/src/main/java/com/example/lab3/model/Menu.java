package com.example.lab3.model;

import java.util.List;

public class Menu {
    private Integer id;
    private List<MenuItem> items;

    public Menu(Integer id, List<MenuItem> items) {
        this.id = id;
        this.items = items;
    }

    public List<MenuItem> getItems() {
        return items;
    }

    public void setItems(List<MenuItem> items) {
        this.items = items;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
