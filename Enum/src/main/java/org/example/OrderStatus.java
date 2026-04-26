package org.example;

import java.util.EnumMap;
import java.util.EnumSet;
import java.util.Enumeration;
import java.util.Map;

public enum OrderStatus {

    PLACED, CONFIRMED, SHIPPED, DELIVERED, CANCELLED;

    /**
     * I need to map each Rules to set of ORderStatus
     */
    static final EnumMap<OrderStatus, EnumSet<OrderStatus>> RULES_ORDERSTATUS =
            new EnumMap<>(OrderStatus.class);

    static {
        RULES_ORDERSTATUS.put(
                PLACED, EnumSet.of(CONFIRMED, CANCELLED)
        );
        RULES_ORDERSTATUS.put(
                CONFIRMED, EnumSet.of(CANCELLED, SHIPPED)
        );
        RULES_ORDERSTATUS.put(
                SHIPPED, EnumSet.of(DELIVERED)
        );
        RULES_ORDERSTATUS.put(
                DELIVERED, EnumSet.noneOf(OrderStatus.class)
        );
        RULES_ORDERSTATUS.put(
                CANCELLED, EnumSet.noneOf(OrderStatus.class)
        );
    }

    public OrderStatus transition(OrderStatus from, OrderStatus to) throws IllegalStateException{
            if  (RULES_ORDERSTATUS.get(from).contains(to))
                return to;
            else
                throw new IllegalStateException();
    }

}
