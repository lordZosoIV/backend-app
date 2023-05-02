package com.flatrock.product.service.facade;


import com.flatrock.product.model.Category;
import com.flatrock.product.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryServiceFacade {
    private final CategoryService categoryService;

    public List<Category> getCategories() {
        return categoryService.getCategories().stream().map(Category::transform).collect(Collectors.toList());
    }

    public Category getCategory(Long id) {
        return Category.transform(categoryService.getCategory(id));
    }
}
