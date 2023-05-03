package com.flatrcok.order.service.external.product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class Product {
    private Long id;
    private String name;
    private BigDecimal price;
    private Integer quantity;
    private Long sellerId;
    private String sellerEmail;


}
