package com.poly.j6d8_asm_ph24125.rest.controller;


import com.fasterxml.jackson.databind.JsonNode;
import com.poly.j6d8_asm_ph24125.entity.Order;
import com.poly.j6d8_asm_ph24125.entity.OrderStatus;
import com.poly.j6d8_asm_ph24125.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/orders")
public class OrderRestController {
    @Autowired
    OrderService orderService;

    @PostMapping()
    public Order create(@RequestBody JsonNode orderData) {
        return orderService.create(orderData);
    }

    @PutMapping("/status/update/{id}")
    public Order UpdateStatus(
            @PathVariable("id")Long id,
            @RequestBody Order order
    ) {
    return orderService.updateStatus(order);
    }

    @GetMapping("/list")
    public List<Order> getAll() {
        return orderService.getAll();
    }

    @GetMapping("/list/pending")
    public List<Order> getOrderByPending() {
        return orderService.getOrderByStatus(OrderStatus.PENDING);
    }

    @GetMapping("/list/confirm")
    public List<Order> getOrderByConfirm() {
        return orderService.getOrderByStatus(OrderStatus.CONFIRMED);
    }
    @GetMapping("/list/delivering")
    public List<Order> getOrderByDelivering() {
        return orderService.getOrderByStatus(OrderStatus.DELIVERING);
    }
    @GetMapping("/list/successfull")
    public List<Order> getOrderBySuccessfull() {
        return orderService.getOrderByStatus(OrderStatus.SUCCESSFUL);
    }

    @GetMapping("/list/delete")
    public List<Order> getOrderByDelete() {
        return orderService.getOrderByStatus(OrderStatus.DELETED);
    }

}
