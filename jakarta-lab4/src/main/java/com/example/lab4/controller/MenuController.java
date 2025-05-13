package com.example.lab4.controller;

import com.example.lab4.model.MenuItem;
import com.example.lab4.service.MenuService;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.List;

@WebServlet("/menu/*")
public class MenuController extends HttpServlet {
    @EJB
    private MenuService menuService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathInfo = req.getPathInfo();
        if (pathInfo == null || pathInfo.equals("/")) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Menu ID is missing.");
            return;
        }

        try {
            int menuId = Integer.parseInt(pathInfo.substring(1));
            List<MenuItem> menuItems = menuService.findById(menuId).getItems();
            req.setAttribute("menuItems", menuItems);
            req.setAttribute("menuId", menuId);

            HttpSession session = req.getSession();
            List<MenuItem> cart = (List<MenuItem>) session.getAttribute("cart");
            req.setAttribute("cart", cart);

            req.getRequestDispatcher("/menu.jsp").forward(req, resp);
        } catch (NumberFormatException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid menu ID.");
        }
    }
}
