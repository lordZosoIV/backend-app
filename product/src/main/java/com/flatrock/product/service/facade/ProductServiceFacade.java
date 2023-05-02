package com.flatrock.product.service.facade;


import com.flatrock.product.model.PurchaseRequest;
import com.flatrock.product.model.Product;
import com.flatrock.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceFacade {
    private final ProductService productService;

    public List<Product> getProducts() {
        return productService.getProducts().stream().map(Product::transform).collect(Collectors.toList());
    }

    public Product getProduct(Long id) {
        return Product.transform(productService.getProduct(id));
    }


    public void decrementQuantity(List<PurchaseRequest> products) {
        productService.decrementQuantity(products);
    }

}
