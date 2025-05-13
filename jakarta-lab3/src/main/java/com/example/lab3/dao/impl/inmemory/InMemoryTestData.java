package com.example.lab3.dao.impl.inmemory;

import com.example.lab3.model.Menu;
import com.example.lab3.model.MenuItem;
import com.example.lab3.model.Order;

import java.util.ArrayList;
import java.util.List;

public class InMemoryTestData {
    public static void generateTo(InMemoryDb db) {
        db.menus.clear();
        db.menuItems.clear();
        db.orders.clear();

        List<MenuItem> menuItems = new ArrayList<>();
        menuItems.add(new MenuItem(1, "Margherita Pizza", "Classic pizza with tomato sauce and mozzarella", 8.99));
        menuItems.add(new MenuItem(2, "Cheeseburger", "Beef burger with cheddar cheese and pickles", 9.49));
        menuItems.add(new MenuItem(3, "Caesar Salad", "Romaine lettuce with Caesar dressing and croutons", 7.99));
        menuItems.add(new MenuItem(4, "Grilled Chicken", "Chicken breast with herbs and vegetables", 10.99));
        menuItems.add(new MenuItem(5, "Spaghetti Bolognese", "Pasta with rich meat sauce", 9.89));
        menuItems.add(new MenuItem(6, "Veggie Wrap", "Tortilla filled with grilled vegetables and hummus", 7.49));
        menuItems.add(new MenuItem(7, "Sushi Combo", "Assorted sushi rolls and nigiri", 12.99));
        menuItems.add(new MenuItem(8, "Beef Tacos", "Three tacos with spicy beef and salsa", 8.59));
        menuItems.add(new MenuItem(9, "Tomato Soup", "Warm soup with fresh tomatoes and basil", 5.99));
        menuItems.add(new MenuItem(10, "Chocolate Cake", "Rich chocolate cake with ganache", 6.49));

        menuItems.forEach(menuItem -> db.menuItems.put(menuItem.getId(), menuItem));

        Menu menu = new Menu(1, menuItems);
        db.menus.put(menu.getId(), menu);

        List<MenuItem> order1Items = new ArrayList<>();
        order1Items.add(menuItems.get(1));
        order1Items.add(menuItems.get(3));
        Order order1 = new Order(1, order1Items, false);

        List<MenuItem> order2Items = new ArrayList<>();
        order2Items.add(menuItems.get(2));
        order2Items.add(menuItems.get(4));
        Order order2 = new Order(2, order2Items, true);

        db.orders.put(order1.getId(), order1);
        db.orders.put(order2.getId(), order2);
    }
}