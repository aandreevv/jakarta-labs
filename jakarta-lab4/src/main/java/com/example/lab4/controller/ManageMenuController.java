package com.example.lab4.controller;

import com.example.lab4.entity.Menu;
import com.example.lab4.entity.MenuItem;
import com.example.lab4.service.MenuItemService;
import com.example.lab4.service.MenuService;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/admin/menu/*")
public class ManageMenuController extends HttpServlet {
    @EJB
    private MenuService menuService;

    @EJB
    private MenuItemService menuItemService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathInfo = req.getPathInfo();
        if (pathInfo == null || pathInfo.equals("/")) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Menu ID is missing.");
            return;
        }

        try {
            int menuId = Integer.parseInt(pathInfo.substring(1));
            Menu menu = menuService.findById(menuId);
            if (menu == null) {
                req.setAttribute("menuItems", new ArrayList<>() );
            } else {
                req.setAttribute("menuItems", menu.getItems());
            }
            req.setAttribute("menuId", menuId);
            req.getRequestDispatcher("/admin/manageMenu.jsp").forward(req, resp);
        } catch (NumberFormatException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid menu ID.");
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String menuIdParam = req.getParameter("menuId");

        try {
            Menu menu = menuService.findById(Integer.parseInt(menuIdParam));
            String action = req.getParameter("action");
            if ("add".equalsIgnoreCase(action)) {
                String name = req.getParameter("name");
                String description = req.getParameter("description");
                String priceParam = req.getParameter("price");

                if (name != null && description != null && priceParam != null) {
                    try {
                        double price = Double.parseDouble(priceParam);
                        MenuItem newItem = new MenuItem();
                        newItem.setName(name);
                        newItem.setDescription(description);
                        newItem.setPrice(price);
                        newItem.setMenu(menu);
                        menuService.addItemToMenu(menu, newItem);
                        resp.sendRedirect(req.getContextPath() + "/admin/menu/" + menuIdParam);
                    } catch (NumberFormatException e) {
                        req.setAttribute("error", "Invalid price format. Please enter a valid number.");
                        doGet(req, resp);
                    }
                } else {
                    resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing form fields.");
                }

            } else if ("delete".equalsIgnoreCase(action)) {
                String idParam = req.getParameter("id");
                if (idParam != null) {
                    try {
                        int id = Integer.parseInt(idParam);
                        MenuItem item = menuItemService.findById(id);
                        menuService.removeItemFromMenu(menu, item);
                        resp.sendRedirect(req.getContextPath() + "/admin/menu/" + menuIdParam);
                    } catch (NumberFormatException e) {
                        resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid menu item ID.");
                    }
                } else {
                    resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing menu item ID.");
                }

            } else {
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Unsupported action.");
            }
        } catch (NumberFormatException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid menu ID.");
            return;
        }
    }
}
