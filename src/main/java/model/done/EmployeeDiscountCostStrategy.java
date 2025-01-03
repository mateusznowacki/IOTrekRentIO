package model.done;


public class EmployeeDiscountCostStrategy implements RentalCostStrategy {
    private static final double DISCOUNT_RATE = 0.4; // Zniżka 40%

    @Override
    public double calculateCost(int days, double pricePerDay) {
        double totalCost = days * pricePerDay;
        return totalCost * (1 - DISCOUNT_RATE); // Zastosowanie zniżki
    }


}
