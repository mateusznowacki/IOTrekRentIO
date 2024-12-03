package pl.pwr.model;

public interface RentalCostStrategy {
    double calculateCost(int days, double pricePerDay);
}
