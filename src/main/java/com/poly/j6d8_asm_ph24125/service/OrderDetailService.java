package com.poly.j6d8_asm_ph24125.service;

import com.poly.j6d8_asm_ph24125.entity.OrderDetail;

import java.util.List;

public interface OrderDetailService {
    List<OrderDetail> getAllByOrder(Long id);
}
