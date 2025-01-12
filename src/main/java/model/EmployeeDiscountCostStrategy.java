package model;


public class EmployeeDiscountCostStrategy implements RentalCostStrategy {
    private static final double DISCOUNT_RATE = 0.4; // Zniżka 40%



    @Override
    public double calculateCost(int days, double pricePerDay) {
        if (days <= 0 || pricePerDay < 0) {
            throw new IllegalArgumentException("Dni i cena za dzień muszą być większe niż 0");
        }
        double totalCost = days * pricePerDay;
        return totalCost * (1 - DISCOUNT_RATE); // Zastosowanie zniżki
    }


}
