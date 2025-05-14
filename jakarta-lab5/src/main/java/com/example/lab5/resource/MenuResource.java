package com.example.lab5.resource;

import com.example.lab5.dto.MenuDTO;
import com.example.lab5.dto.MenuItemDTO;
import com.example.lab5.dto.MenuUpdateDTO;
import com.example.lab5.entity.Menu;
import com.example.lab5.entity.MenuItem;
import com.example.lab5.mapper.MenuItemMapper;
import com.example.lab5.mapper.MenuMapper;
import com.example.lab5.service.MenuItemService;
import com.example.lab5.service.MenuService;
import jakarta.ejb.EJB;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/menus")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MenuResource {
    @EJB
    private MenuService menuService;

    @EJB
    private MenuItemService menuItemService;

    @GET
    public Response getAllMenus() {
        List<Menu> menus = menuService.findAll();
        List<MenuDTO> dtos = menus.stream().map(MenuMapper::toDto).toList();
        return Response.ok(dtos).build();
    }

    @GET
    @Path("/{menuId}")
    public Response getMenuById(@PathParam("menuId") int id) {
        Menu menu = menuService.findById(id);
        if (menu == null) return Response.status(Response.Status.NOT_FOUND).build();
        MenuDTO dto = MenuMapper.toDto(menu);
        return Response.ok(dto).build();
    }

    @POST
    public Response createMenu(MenuDTO menuDTO) {
        Menu menu = MenuMapper.toEntity(menuDTO);
        menuService.create(menu);
        MenuDTO dto = MenuMapper.toDto(menu);
        return Response.status(Response.Status.CREATED).entity(dto).build();
    }

    @DELETE
    @Path("/{menuId}")
    public Response deleteMenu(@PathParam("menuId") int id) {
        Menu menu = menuService.findById(id);
        if (menu == null) return Response.status(Response.Status.NOT_FOUND).build();
        menuService.delete(id);
        return Response.ok().build();
    }

    @PATCH
    @Path("/{menuId}")
    public Response editMenu(@PathParam("menuId") int id, MenuUpdateDTO menuUpdateDTO) {
        Menu menu = menuService.findById(id);
        if (menu == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        if (menuUpdateDTO.getName() != null) {
            menu.setName(menuUpdateDTO.getName());
        }

        menuService.edit(menu);
        MenuDTO updatedDto = MenuMapper.toDto(menu);
        return Response.ok(updatedDto).build();
    }

    @POST
    @Path("/{menuId}/items")
    public Response addMenuItem(@PathParam("menuId") int menuId, MenuItemDTO item) {
        Menu menu = menuService.findById(menuId);
        if (menu == null) return Response.status(Response.Status.NOT_FOUND).build();
        MenuItem menuItem = MenuItemMapper.toEntity(item);
        menuService.addItemToMenu(menu, menuItem);
        MenuDTO dto = MenuMapper.toDto(menu);
        return Response.status(Response.Status.CREATED).entity(dto).build();
    }

    @DELETE
    @Path("/{menuId}/items/{itemId}")
    public Response removeMenuItem(@PathParam("menuId") int menuId, @PathParam("itemId") int itemId) {
        Menu menu = menuService.findById(menuId);
        if (menu == null) return Response.status(Response.Status.NOT_FOUND).build();
        MenuItem menuItem = menuItemService.findById(itemId);
        if (menuItem == null) return Response.status(Response.Status.NOT_FOUND).build();
        menuService.removeItemFromMenu(menu, menuItem);
        MenuDTO dto = MenuMapper.toDto(menu);
        return Response.ok(dto).build();
    }
}