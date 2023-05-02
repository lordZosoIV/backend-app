package com.flatrock.product.service;


import com.flatrock.product.entity.ProductEntity;
import com.flatrock.product.model.PurchaseRequest;
import com.flatrock.product.repository.ProductRepository;
import com.flatrock.product.util.HandledExceptionFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository repository;

    public List<ProductEntity> getProducts() {
        return repository.findAll();
    }

    public ProductEntity getProduct(Long id) {
        return repository.findById(id).orElseThrow(() -> HandledExceptionFactory.getHandledException("product not found"));
    }


    public void decrementQuantity(List<PurchaseRequest> products) {
        for (PurchaseRequest entry : products) {
            Long productId = entry.getId();
            Integer quantity = entry.getQuantity();

            ProductEntity product = getProduct(productId);
            product.setQuantity(product.getQuantity() - quantity);
            repository.save(product);
        }
    }


}
