package com.example.lab4.controller;

import com.example.lab4.entity.Order;

import com.example.lab4.service.OrderService;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.List;

@WebServlet("/admin/orders")
public class OrdersController extends HttpServlet {

    @EJB
    private OrderService orderService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Order> orders =  orderService.findAll();
        req.setAttribute("orders", orders);
        req.getRequestDispatcher("/admin/orders.jsp").forward(req, resp);
    }
}
