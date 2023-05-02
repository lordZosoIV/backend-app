package com.flatrcok.order.controller;

import com.flatrcok.order.model.request.OrderRequest;
import com.flatrcok.order.service.facade.OrderServiceFacade;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
@Validated
public class OrderController {
    private final OrderServiceFacade orderServiceFacade;

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody @Valid OrderRequest request) {
        orderServiceFacade.createOrder(request);
        return ResponseEntity.ok().build();
    }

}
