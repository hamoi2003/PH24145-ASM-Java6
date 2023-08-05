package com.poly.j6d8_asm_ph24125.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.poly.j6d8_asm_ph24125.entity.Order;
import com.poly.j6d8_asm_ph24125.entity.OrderStatus;

import java.util.List;

public interface OrderService {
    Order create(JsonNode orderData);

    Order findById(Long id);
    List<Order> findByUserName(String username);

    List<Order> getAll();

    List<Order> getOrderByStatus(OrderStatus orderStatus);

    Order updateStatus(Order order);
}
