package com.example.lab3.model;

import java.util.List;

public class Order {
    private int id;
    private List<MenuItem> items;
    private boolean processed;

    public Order(int id, List<MenuItem> items, boolean processed) {
        this.id = id;
        this.items = items;
        this.processed = processed;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<MenuItem> getItems() {
        return items;
    }

    public void setItems(List<MenuItem> items) {
        this.items = items;
    }

    public boolean isProcessed() {
        return processed;
    }

    public void setProcessed(boolean processed) {
        this.processed = processed;
    }
}
