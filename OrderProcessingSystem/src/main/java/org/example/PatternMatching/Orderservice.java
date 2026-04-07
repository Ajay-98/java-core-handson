package org.example.PatternMatching;

import org.example.SealedClass.OrderStatus;

public class Orderservice {

   public String describeStatus(OrderStatus orderStatus){
        return switch (orderStatus)
        {
            case OrderStatus.Draft d -> "Order is in Draft";
            case OrderStatus.Cancelled cancelled -> "Order is in Cancelled";
            case OrderStatus.Confirmed confirmed -> "Your Order is Confirmed";
        };
    }
}
