package com.example.lab3.controller;

import com.example.lab3.model.MenuItem;
import com.example.lab3.model.Order;
import com.example.lab3.service.OrderService;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
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

        if (cart != null && !cart.isEmpty()) {
            Order order = new Order(0, cart, false);
            orderService.create(order);
            session.removeAttribute("cart");
        }

        resp.sendRedirect("menu/" + menuId);
    }
}