package com.example.lab5.mapper;

import com.example.lab5.dto.OrderDTO;
import com.example.lab5.entity.Order;
import java.util.stream.Collectors;

public class OrderMapper {
    public static OrderDTO toDto(Order order) {
        OrderDTO dto = new OrderDTO();
        dto.setId(order.getId());
        dto.setProcessed(order.isProcessed());
        if (order.getItems() != null) {
            dto.setItems(order.getItems().stream()
                    .map(orderItem -> MenuItemMapper.toDto(orderItem.getMenuItem()))
                    .collect(Collectors.toList()));
        }
        return dto;
    }
}
