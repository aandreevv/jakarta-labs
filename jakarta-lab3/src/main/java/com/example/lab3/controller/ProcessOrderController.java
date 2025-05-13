package com.example.lab3.controller;

import com.example.lab3.model.Order;
import com.example.lab3.service.OrderService;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/admin/orders/process")
public class ProcessOrderController extends HttpServlet {

    @EJB
    private OrderService orderService;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String orderIdParam = request.getParameter("orderId");
        if (orderIdParam != null) {
            int orderId = Integer.parseInt(orderIdParam);
            Order order = orderService.findById(orderId);

            if (order != null && !order.isProcessed()) {
                order.setProcessed(true);
                orderService.edit(order);
            }
        }

        response.sendRedirect(request.getContextPath() + "/admin/orders");
    }
}
