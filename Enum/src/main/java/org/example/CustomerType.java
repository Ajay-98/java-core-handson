package org.example;

public enum CustomerType implements Discountable {

    REGULAR(0), SILVER(10), GOLD(20), PLATINUM(35);
    private final double discountPercentage;

    @Override
    public double getDiscountPercentage() {
        return discountPercentage;
    }

    private CustomerType(double discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    @Override
    public  double applyDiscount(double price) {
        return price *(getDiscountPercentage()/100);
    }
}

class TestCustomerDiscount{
    public static void main(String[] args) {

        System.out.println(CustomerType.PLATINUM.applyDiscount(50));
    }
}
