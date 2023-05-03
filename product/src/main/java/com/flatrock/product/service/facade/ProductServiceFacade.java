package com.flatrock.product.service.facade;


import com.flatrock.product.entity.ProductEntity;
import com.flatrock.product.model.PurchaseRequest;
import com.flatrock.product.model.Product;
import com.flatrock.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceFacade {
    private final ProductService productService;

    public List<Product>  getAllProducts() {
        List<ProductEntity> products = productService.getProducts();
        return products.stream().map(Product::transform).collect(Collectors.toList());
    }

    public List<Product> getProducts(List<Long> ids) {
        List<ProductEntity> products = productService.getProducts();
        List<ProductEntity> result = new ArrayList<>();
        for(ProductEntity product : products){
            if(ids.contains(product.getId())){
                result.add(product);
            }
        }
        return result.stream().map(Product::transform).collect(Collectors.toList());
    }

    public Product getProduct(Long id) {
        return Product.transform(productService.getProduct(id));
    }


    public void decrementQuantity(List<PurchaseRequest> products) {
        productService.decrementQuantity(products);
    }


}
