package org.example;

public enum Mathoperation {
    ADD,
    SUBTRACT,
    MULTIPLY,
    DIVIDE;

    private static double n1, n2;

    public double calculate(double a, double b) {
        return switch (this) {
            case ADD -> a + b;
            case SUBTRACT -> a - b;
            case MULTIPLY -> a * b;
            case DIVIDE -> {
                if (b == 0) throw new ArithmeticException("Divide by zero");
                yield a / b;
            }
        };
    }
}

class TestMathoperationEnum{
    public static void main(String[] args) {

        System.out.println(
                "Testing 1 " +
                        Mathoperation.DIVIDE.calculate(2, 0 ));
    }
}
