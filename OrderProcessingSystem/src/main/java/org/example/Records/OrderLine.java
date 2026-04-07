package org.example.Records;

public record OrderLine(ProductId productId, int quantity, Money money) {

    public Money lineTotal() {
        return this.money;
    }
}
