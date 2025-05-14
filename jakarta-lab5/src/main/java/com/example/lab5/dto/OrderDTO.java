package com.example.lab5.dto;

import java.util.List;

public class OrderDTO {
    private int id;
    private boolean processed;
    private List<MenuItemDTO> items;

    public boolean isProcessed() {
        return processed;
    }

    public void setProcessed(boolean processed) {
        this.processed = processed;
    }

    public List<MenuItemDTO> getItems() {
        return items;
    }

    public void setItems(List<MenuItemDTO> items) {
        this.items = items;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
