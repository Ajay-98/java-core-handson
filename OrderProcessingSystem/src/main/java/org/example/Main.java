package org.example;

import org.example.PatternMatching.Orderservice;
import org.example.Records.Money;
import org.example.Records.OrderLine;
import org.example.Records.ProductId;
import org.example.SealedClass.OrderStatus;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;


class Main {
    public static void main(String[] args) {

        List<OrderLine> orderLines = new ArrayList<>();
        orderLines.add(new OrderLine(new ProductId(), 2, new Money(new BigDecimal("2.0"), "RUP")));

        orderLines.add(new OrderLine(new ProductId(), 4, new Money(new BigDecimal("3.0"), "RUP")));

        Orderservice os = new Orderservice();
        System.out.println(os.describeStatus(new OrderStatus.Confirmed("AJ", Instant.now())));
    }
}