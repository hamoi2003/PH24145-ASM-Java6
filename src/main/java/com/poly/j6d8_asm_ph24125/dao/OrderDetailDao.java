package com.poly.j6d8_asm_ph24125.dao;

import com.poly.j6d8_asm_ph24125.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderDetailDao extends JpaRepository<OrderDetail, Integer> {
    @Query("SELECT od FROM OrderDetail od WHERE od.order.id=?1")
    List<OrderDetail> getAllByOrder(Long id);
}
