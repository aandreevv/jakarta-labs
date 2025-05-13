package com.example.lab4.controller;

import com.example.lab4.model.MenuItem;
import com.example.lab4.service.MenuItemService;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/menu/form-order")
public class FormOrderController extends HttpServlet {

    @EJB
    private MenuItemService menuItemService;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        String itemIdParam = request.getParameter("itemId");
        String menuIdParam = request.getParameter("menuId");

        if (itemIdParam == null) {
            response.sendRedirect("menu");
            return;
        }

        int itemId = Integer.parseInt(itemIdParam);
        HttpSession session = request.getSession();

        List<MenuItem> cart = (List<MenuItem>) session.getAttribute("cart");
        if (cart == null) {
            cart = new ArrayList<>();
        }

        if ("add".equalsIgnoreCase(action)) {
            MenuItem item = menuItemService.findById(itemId);
            if (item != null) cart.add(item);
        } else if ("remove".equalsIgnoreCase(action)) {
            cart.removeIf(i -> i.getId() == itemId);
        }

        session.setAttribute("cart", cart);
        response.sendRedirect(menuIdParam);
    }
}
