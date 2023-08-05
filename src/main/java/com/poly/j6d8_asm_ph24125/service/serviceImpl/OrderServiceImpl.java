package com.poly.j6d8_asm_ph24125.service.serviceImpl;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.poly.j6d8_asm_ph24125.dao.OrderDAO;
import com.poly.j6d8_asm_ph24125.dao.OrderDetailDao;
import com.poly.j6d8_asm_ph24125.entity.Order;
import com.poly.j6d8_asm_ph24125.entity.OrderDetail;
import com.poly.j6d8_asm_ph24125.entity.OrderStatus;
import com.poly.j6d8_asm_ph24125.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderDAO orderDAO;
    @Autowired
    OrderDetailDao orderDetailDao;
    @Override
    public Order create(JsonNode orderData) {
        ObjectMapper mapper = new ObjectMapper();

        Order order = mapper.convertValue(orderData, Order.class);
        orderDAO.save(order);

        TypeReference<List<OrderDetail>> type = new TypeReference<List<OrderDetail>>() {};
        List<OrderDetail> details = mapper.convertValue(orderData.get("orderDetails"), type)
                .stream().peek(d -> d.setOrder(order)).collect(Collectors.toList());
        orderDetailDao.saveAll(details);
        return order;
    }

    @Override
    public Order findById(Long id) {
        return orderDAO.findById(id).get();
    }

    @Override
    public List<Order> findByUserName(String username) {
        return orderDAO.findByUsername(username);
    }

    @Override
    public List<Order> getAll() {
        return orderDAO.findAll();
    }

    @Override
    public List<Order> getOrderByStatus(OrderStatus orderStatus) {
        return orderDAO.getOrderByStatus(orderStatus);
    }

    @Override
    public Order updateStatus(Order order) {
        return orderDAO.save(order);
    }
}
