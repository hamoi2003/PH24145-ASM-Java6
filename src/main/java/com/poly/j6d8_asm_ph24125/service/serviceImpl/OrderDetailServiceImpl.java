package com.poly.j6d8_asm_ph24125.service.serviceImpl;

import com.poly.j6d8_asm_ph24125.dao.OrderDetailDao;
import com.poly.j6d8_asm_ph24125.entity.OrderDetail;
import com.poly.j6d8_asm_ph24125.service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderDetailServiceImpl implements OrderDetailService {
    @Autowired
    OrderDetailDao orderDetailDao;
    @Override
    public List<OrderDetail> getAllByOrder(Long id) {
        return orderDetailDao.getAllByOrder(id);
    }
}
