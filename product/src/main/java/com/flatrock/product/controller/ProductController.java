package com.flatrock.product.controller;


import com.flatrock.product.model.Product;
import com.flatrock.product.service.facade.ProductServiceFacade;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/product")
public class ProductController {
    private final ProductServiceFacade productServiceFacade;

    @GetMapping("/filter")
    public ResponseEntity<List<Product>> filter(@RequestParam(required = false) List<Long> ids) {
        return ResponseEntity.ok(productServiceFacade.getProducts(ids));
    }


    @GetMapping
    public ResponseEntity<List<Product>> getAll() {
        return ResponseEntity.ok(productServiceFacade.getAllProducts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable @Min(1) Long id) {
        return ResponseEntity.ok(productServiceFacade.getProduct(id));
    }

}
