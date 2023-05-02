package com.flatrcok.order.service;

import com.flatrcok.order.model.request.OrderRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class OrderService {

    public void createOrder(OrderRequest request) {
        log.info("creaaated");
    }

}
