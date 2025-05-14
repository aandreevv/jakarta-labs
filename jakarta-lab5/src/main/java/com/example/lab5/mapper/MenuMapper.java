package com.example.lab5.mapper;

import com.example.lab5.dto.MenuDTO;
import com.example.lab5.entity.Menu;

import java.util.stream.Collectors;

public class MenuMapper {
    public static MenuDTO toDto(Menu menu) {
        MenuDTO dto = new MenuDTO();
        dto.setId(menu.getId());
        dto.setName(menu.getName());
        dto.setCreatedAt(menu.getCreatedAt());
        if (menu.getItems() != null) {
            dto.setItems(menu.getItems().stream()
                    .map(MenuItemMapper::toDto)
                    .collect(Collectors.toList()));
        }
        return dto;
    }

    public static Menu toEntity(MenuDTO dto) {
        Menu menu = new Menu();
        menu.setName(dto.getName());
        return menu;
    }
}
