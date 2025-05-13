package com.example.lab5.data;

import com.example.lab5.entity.Menu;
import com.example.lab5.entity.MenuItem;
import com.example.lab5.entity.Order;
import com.example.lab5.entity.OrderItem;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;
import jakarta.ejb.TransactionAttribute;
import jakarta.ejb.TransactionAttributeType;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Singleton
@Startup
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class JpaDataSeeder implements DataSeeder {
    @PersistenceContext(unitName = "foodPU")
    private EntityManager em;

    @PostConstruct
    public void seed() {
        if (!em.createQuery("SELECT m FROM Menu m", Menu.class).getResultList().isEmpty()) {
            return;
        }

        Menu menu = new Menu();

        List<MenuItem> menuItems = new ArrayList<>(Arrays.asList(
                new MenuItem("Margherita Pizza", "Classic pizza with tomato sauce and mozzarella", 8.99, menu),
                new MenuItem("Cheeseburger", "Beef burger with cheddar cheese and pickles", 9.49, menu),
                new MenuItem("Caesar Salad", "Romaine lettuce with Caesar dressing and croutons", 7.99, menu),
                new MenuItem("Grilled Chicken", "Chicken breast with herbs and vegetables", 10.99, menu),
                new MenuItem("Spaghetti Bolognese", "Pasta with rich meat sauce", 9.89, menu),
                new MenuItem("Veggie Wrap", "Tortilla filled with grilled vegetables and hummus", 7.49, menu),
                new MenuItem("Sushi Combo", "Assorted sushi rolls and nigiri", 12.99, menu),
                new MenuItem("Beef Tacos", "Three tacos with spicy beef and salsa", 8.59, menu),
                new MenuItem("Tomato Soup", "Warm soup with fresh tomatoes and basil", 5.99, menu),
                new MenuItem("Chocolate Cake", "Rich chocolate cake with ganache", 6.49, menu)
        ));

        menu.setItems(menuItems);
        for (MenuItem menuItem : menuItems) {
            em.persist(menuItem);
        }
        em.persist(menu);

        menu.setItems(menuItems);

        Order order1 = new Order();
        List<OrderItem> order1Items = new ArrayList<>(Arrays.asList(
                new OrderItem(menuItems.get(0), order1), // Margherita Pizza
                new OrderItem(menuItems.get(3), order1)  // Grilled Chicken
        ));
        order1.setItems(order1Items);

        Order order2 = new Order();
        List<OrderItem> order2Items = new ArrayList<>(Arrays.asList(
                new OrderItem(menuItems.get(2), order2), // Caesar Salad
                new OrderItem(menuItems.get(4), order2)  // Spaghetti Bolognese
        ));
        order2.setItems(order2Items);

        em.persist(order1);
        em.persist(order2);
    }
}
