package org.example;

import java.util.List;

public interface OrderRepo {
    List<OrderResponse> getall();

    OrderResponse getid(Long id);
}
