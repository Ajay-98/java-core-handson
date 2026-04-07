package org.example.SealedClass;

import java.time.Instant;

public sealed interface OrderStatus permits OrderStatus.Draft, OrderStatus.Confirmed, OrderStatus.Cancelled {


    record Draft() implements OrderStatus {
    }

    record Confirmed(String confirmedBy, Instant confirmedAt) implements OrderStatus {
    }

    record Cancelled(String reason) implements OrderStatus {
    }

}