package com.flatrcok.order.model.request;

import com.flatrcok.order.model.OrderItem;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderRequest {
    @NotNull
    @Size(min = 1)
    @Valid
    private List<OrderItem> orderItems;
}
