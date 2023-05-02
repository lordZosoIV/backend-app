package com.flatrock.product.model;

import com.flatrock.product.entity.CategoryEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Category {
    private Long id;
    private String name;

    public static Category transform(CategoryEntity category) {
        return new Category(category.getId(), category.getName());
    }
}
