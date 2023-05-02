package com.flatrock.product.service;

import com.flatrock.product.entity.CategoryEntity;
import com.flatrock.product.repository.CategoryRepository;
import com.flatrock.product.util.HandledExceptionFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository repository;


    public List<CategoryEntity> getCategories() {
        return repository.findAll();
    }

    public CategoryEntity getCategory(Long id) {
        return repository.findById(id).orElseThrow(() -> HandledExceptionFactory.getHandledException("Category not found"));
    }
}
