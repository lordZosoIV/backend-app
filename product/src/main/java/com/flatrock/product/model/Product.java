package com.flatrock.product.model;

import com.flatrock.product.entity.ProductEntity;
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
    private Category category;

    public static Product transform(ProductEntity product) {
        return new Product(
                product.getId(),
                product.getName(),
                product.getPrice(),
                product.getQuantity(),
                product.getSellerId(),
                product.getSellerEmail(),
                Category.transform(product.getCategory())
        );
    }

}
