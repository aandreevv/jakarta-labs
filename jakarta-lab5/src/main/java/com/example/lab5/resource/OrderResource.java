package com.example.lab5.resource;

import com.example.lab5.dto.OrderDTO;
import com.example.lab5.dto.OrderUpdateDTO;
import com.example.lab5.entity.MenuItem;
import com.example.lab5.entity.Order;
import com.example.lab5.entity.OrderItem;
import com.example.lab5.mapper.OrderMapper;
import com.example.lab5.service.MenuItemService;
import com.example.lab5.service.OrderService;
import jakarta.ejb.EJB;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.stream.Collectors;

@Path("/orders")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class OrderResource {

    @EJB
    OrderService orderService;

    @EJB
    MenuItemService menuItemService;

    @GET
    public Response getOrders(@QueryParam("processed") Boolean processed,
                              @QueryParam("page") @DefaultValue("1") int page,
                              @QueryParam("size") @DefaultValue("10") int size) {
        List<Order> orders = orderService.findFiltered(processed, page, size);
        List<OrderDTO> dtos = orders.stream()
                .map(OrderMapper::toDto)
                .collect(Collectors.toList());
        return Response.ok(dtos).build();
    }

    @GET
    @Path("/{orderId}")
    public Response getOrder(@PathParam("orderId") int id) {
        Order order = orderService.findById(id);
        if (order == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(OrderMapper.toDto(order)).build();
    }

    @POST
    public Response createOrder() {
        Order order = new Order();
        orderService.create(order);
        return Response.status(Response.Status.CREATED).entity(order).build();
    }

    @POST
    @Path("/{orderId}/items/{menuItemId}")
    public Response addItemToOrder(@PathParam("orderId") int orderId, @PathParam("menuItemId") int menuItemId) {
        Order order = orderService.findById(orderId);
        if (order == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        if (order.isProcessed()) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Can not modify already processed order!").build();
        }

        MenuItem menuItem = menuItemService.findById(menuItemId);
        if (menuItem == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        orderService.addItemToOrder(order, menuItem);
        OrderDTO dto = OrderMapper.toDto(order);
        return Response.status(Response.Status.CREATED).entity(dto).build();
    }

    @DELETE
    @Path("/{orderId}/items/{menuItemId}")
    public Response removeItemFromOrder(@PathParam("orderId") int orderId, @PathParam("menuItemId") int menuItemId) {
        Order order = orderService.findById(orderId);
        if (order == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        if (order.isProcessed()) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Can not modify already processed order!").build();
        }

        MenuItem menuItem = menuItemService.findById(menuItemId);
        if (menuItem == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        OrderItem orderItem = order.getItems().stream()
                .filter(item -> item.getMenuItem().equals(menuItem))
                .findFirst()
                .orElse(null);

        if (orderItem == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("The order does not contain this menu item.").build();
        }

        orderService.removeItemFromOrder(orderItem);
        OrderDTO dto = OrderMapper.toDto(order);
        return Response.status(Response.Status.OK).entity(dto).build();
    }

    @PATCH
    @Path("/{orderId}")
    public Response processOrder(@PathParam("orderId") int orderId, OrderUpdateDTO orderUpdateDTO) {
        Order order = orderService.findById(orderId);
        if (order == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        order.setProcessed(orderUpdateDTO.isProcessed());
        orderService.edit(order);
        OrderDTO dto = OrderMapper.toDto(order);
        return Response.ok(dto).build();
    }

    @DELETE
    @Path("/{orderId}")
    public Response deleteOrder(@PathParam("orderId") int id) {
        Order order = orderService.findById(id);
        if (order == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        if (order.isProcessed()) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Can not delete already processed order!").build();
        }

        orderService.delete(id);
        return Response.ok().build();
    }
}