package org.example.Records;

import java.math.BigDecimal;
import java.util.Objects;

public record Money(BigDecimal amount, String currency) {
    public Money{
        Objects.requireNonNull(currency, "Currency cannot be null");
        Objects.requireNonNull(amount, "Amount cannot be null");

        if(amount.compareTo(BigDecimal.ZERO) < 0){
            throw new IllegalArgumentException("Amount cannot be negative");
        }
    }
}
