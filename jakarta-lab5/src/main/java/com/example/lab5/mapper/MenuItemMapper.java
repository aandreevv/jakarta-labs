package com.example.lab5.mapper;

import com.example.lab5.dto.MenuItemDTO;
import com.example.lab5.entity.MenuItem;

public class MenuItemMapper {
    public static MenuItemDTO toDto(MenuItem menuItem) {
        MenuItemDTO dto = new MenuItemDTO();
        dto.setId(menuItem.getId());
        dto.setName(menuItem.getName());
        dto.setDescription(menuItem.getDescription());
        dto.setPrice(menuItem.getPrice());
        return dto;
    }

    public static MenuItem toEntity(MenuItemDTO dto) {
        MenuItem menuItem = new MenuItem();
        menuItem.setName(dto.getName());
        menuItem.setDescription(dto.getDescription());
        menuItem.setPrice(dto.getPrice());
        return menuItem;
    }
}
