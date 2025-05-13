package com.example.lab4.controller;

import com.example.lab4.entity.MenuItem;
import com.example.lab4.entity.Order;
import com.example.lab4.entity.OrderItem;
import com.example.lab4.service.OrderService;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/submit-order")
public class SubmitOrderController extends HttpServlet {

    @EJB
    private OrderService orderService;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        List<MenuItem> cart = (List<MenuItem>) session.getAttribute("cart");
        int menuId = Integer.parseInt(req.getParameter("menuId"));

        try {
            Order order = new Order();
            List<OrderItem> orderItems = new ArrayList<>();
            if (cart != null && !cart.isEmpty()) {
                cart.forEach(menuItem -> {
                    OrderItem orderItem = new OrderItem();
                    orderItem.setMenuItem(menuItem);
                    orderItem.setOrder(order);
                    orderItems.add(orderItem);
                });
            }
            order.setItems(orderItems);
            order.setProcessed(false);
            orderService.create(order);
            session.removeAttribute("cart");

            resp.sendRedirect("menu/" + menuId);
        } catch (Exception e) {
            resp.sendError(HttpServletResponse.SC_BAD_GATEWAY, e.getCause().getMessage());
        }
    }
}