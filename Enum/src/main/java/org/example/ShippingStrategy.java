package org.example;

/**
 *Build a Shipping Cost Calculator using enum Strategy pattern.
 *
 * Create enum `ShippingStrategy`:
 *   STANDARD  → base $5  + $0.5 per km
 *   EXPRESS   → base $15 + $1.0 per km
 *   OVERNIGHT → base $25 + $2.0 per km
 *   DRONE     → base $10 + free under 10km, $3.0/km after
 *
 * Each strategy implements:
 *   double calculateCost(double weightKg, double distanceKm)
 *   String estimatedDelivery()
 *
 * Write:
 *   bestStrategyUnder(double budget, double weight, double distance)
 *   → returns cheapest ShippingStrategy within budget
 *   → returns Optional.empty() if none fit budget
 *
 * Example:
 *   bestStrategyUnder(20, 2.0, 15) → Optional[STANDARD]
 */
public enum ShippingStrategy {
    DRONE(10, 3.0) {
        @Override
        double calculateCost(double weightKg, double distanceKm) {

            return (((distanceKm - 10) * this.getPerKmRate()) + this.getBase());
        }

        @Override
        String estimatedDelivery() {
            return "3 - 4 hrs on Same day of Order";
        }
    }, EXPRESS(15, 1.0) {
        @Override
        double calculateCost(double weightKg, double distanceKm) {
            return ((distanceKm * this.getPerKmRate()) + this.getBase());
        }

        @Override
        String estimatedDelivery() {
            return "Same Day Delivery";
        }
    }, OVERNIGHT(25, 2.0) {
        @Override
        double calculateCost(double weightKg, double distanceKm) {
            return ((distanceKm * this.getPerKmRate()) + this.getBase());
        }

        @Override
        String estimatedDelivery() {
            return "Night Delivery";
        }
    }, STANDARD(5, 0.5) {
        @Override
        double calculateCost(double weightKg, double distanceKm) {
            return (distanceKm * this.getPerKmRate()) + this.getBase();
        }

        @Override
        String estimatedDelivery() {
            return "2-3 days delivered";
        }
    };

    public int getBase() {
        return base;
    }

    public double getPerKmRate() {
        return perKmRate;
    }

    private final int base;
    private final double perKmRate;
    private ShippingStrategy(int base, double perKmRate) {
        this.perKmRate = perKmRate;
        this.base = base;
    }

    abstract double calculateCost(double weightKg, double distanceKm);

    abstract String estimatedDelivery();
}

class TestShippgingStrategy{
    public static void main(String[] args) {

        System.out.println("Testing Shipping Strategy" );
        System.out.println(bestStrategyUnder(20, 2.0, 15));


    }

    private static ShippingStrategy bestStrategyUnder(double budget, double weight, double distanceInKm)
    {
        double bestSoFar = 0;
        ShippingStrategy bestStrategyUnder = ShippingStrategy.STANDARD;
        for (ShippingStrategy strategy : ShippingStrategy.values())
        {
            double currentCost = strategy.calculateCost(weight, distanceInKm);
            if (bestSoFar > (budget - currentCost))
            {
                bestSoFar = budget - currentCost;
                bestStrategyUnder = strategy;
            }

        }
        return bestStrategyUnder;
    }
}
