package org.example;

import javax.validation.constraints.NotNull;
import java.util.List;

public record CreateOrderRequest(@NotNull Long customerId, List<String> items) {

    public CreateOrderRequest(@NotNull Long customerId, List<String> items) {
        this.customerId = customerId;
        this.items = items;
        if(items.isEmpty()) {
            throw new IllegalArgumentException("items cannot be empty");
        }
    }
}
