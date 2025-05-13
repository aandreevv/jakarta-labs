package com.example.lab5.resources;

import com.example.lab5.entity.Menu;
import com.example.lab5.entity.MenuItem;
import com.example.lab5.service.MenuItemService;
import com.example.lab5.service.MenuService;
import jakarta.ejb.EJB;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/menu")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MenuResource {
    @EJB
    private MenuService menuService;

    @EJB
    private MenuItemService menuItemService;

    @GET
    @Path("/{menuId}")
    public Response getMenuById(@PathParam("menuId") int id) {
        Menu menu = menuService.findById(id);
        if (menu == null) return Response.status(Response.Status.NOT_FOUND).build();
        return Response.ok(menu).build();
    }

    @POST
    public Response createMenu(Menu menu) {
        menuService.create(menu);
        return Response.status(Response.Status.CREATED).entity(menu).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteMenu(@PathParam("id") int id) {
        menuService.delete(id);
        return Response.ok().build();
    }

    @POST
    @Path("/{menuId}/items")
    public Response addMenuItem(@PathParam("menuId") int menuId, MenuItem item) {
        Menu menu = menuService.findById(menuId);
        if (menu == null) return Response.status(Response.Status.NOT_FOUND).build();
        menuService.addItemToMenu(menu, item);
        return Response.status(Response.Status.CREATED).entity(item).build();
    }

    @GET
    @Path("/{menuId}/items")
    public Response getMenuItems(@PathParam("menuId") int menuId) {
        Menu menu = menuService.findById(menuId);
        if (menu == null) return Response.status(Response.Status.NOT_FOUND).build();
        List<MenuItem> menuItems = menu.getItems();
        return Response.ok(menuItems).build();
    }

    @DELETE
    @Path("/{menuId}/items/{itemId}")
    public Response removeMenuItem(@PathParam("menuId") int menuId, @PathParam("itemId") int itemId) {
        Menu menu = menuService.findById(menuId);
        if (menu == null) return Response.status(Response.Status.NOT_FOUND).build();
        MenuItem menuItem = menuItemService.findById(itemId);
        if (menuItem == null) return Response.status(Response.Status.NOT_FOUND).build();
        menuService.removeItemFromMenu(menu, menuItem);
        return Response.ok().build();
    }

}
