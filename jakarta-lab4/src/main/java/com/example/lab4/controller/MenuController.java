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
import java.util.List;

@WebServlet("/menu/*")
public class MenuController extends HttpServlet {
    @EJB
    private MenuService menuService;

    @EJB
    private MenuItemService menuItemService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathInfo = req.getPathInfo();
        int menuId = Integer.parseInt(pathInfo.substring(1));
        String searchName = req.getParameter("searchName");
        if (pathInfo.equals("/")) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Menu ID is missing.");
            return;
        }

        try {
            Menu menu = menuService.findById(menuId);
            if (menu == null) {
                req.setAttribute("menuItems", new ArrayList<>() );
            } else {
                List<MenuItem> menuItems;
                if (searchName != null && !searchName.trim().isEmpty()) {
                    menuItems = menuItemService.findByName(searchName, menu);
                } else {
                    menuItems = menu.getItems();
                }
                req.setAttribute("menuItems", menuItems);
            }
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
