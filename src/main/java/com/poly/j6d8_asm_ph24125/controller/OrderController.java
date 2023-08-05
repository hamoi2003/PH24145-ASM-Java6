package com.poly.j6d8_asm_ph24125.controller;

import com.poly.j6d8_asm_ph24125.entity.Order;
import com.poly.j6d8_asm_ph24125.entity.OrderStatus;
import com.poly.j6d8_asm_ph24125.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class OrderController {
    @Autowired
    OrderService orderService;

    @RequestMapping("/order/checkout")
    public String checkout() {
        return "/order/checkout";
    }

    @RequestMapping("/order/list")
    public String list(
            Model model,
            HttpServletRequest req
    ) {
        String username = req.getRemoteUser();
        model.addAttribute("orders", orderService.findByUserName(username));
        return "/order/list";
    }

    @RequestMapping("/order/detail/{id}")
    public String detail(
            @PathVariable("id") Long id,
            Model model
    ) {
        model.addAttribute("order", orderService.findById(id));
        return "/order/detail";
    }

    @GetMapping("/order/status-update/{id}")
    public String updateStatus(@PathVariable("id") Order order) {
        order.setStatus(OrderStatus.DELETED);
        orderService.updateStatus(order);
        return "redirect:/order/list";
    }

}
