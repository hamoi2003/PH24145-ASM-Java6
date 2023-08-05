package com.poly.j6d8_asm_ph24125.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "OrderDetails")
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private BigDecimal price;
    private Integer quantity;
    @ManyToOne
    @JoinColumn(name = "OrderId")
    private Order order;
    @ManyToOne
    @JoinColumn(name = "ProductId")
    private Product product;

}
