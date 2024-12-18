package pl.pwr.model.done;

public interface RentalCostStrategy {
    double calculateCost(int days, double pricePerDay);
}
