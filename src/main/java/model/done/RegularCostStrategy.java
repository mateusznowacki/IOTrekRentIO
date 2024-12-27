package model.done;

public class RegularCostStrategy  implements RentalCostStrategy {
    @Override
    public double calculateCost(int days, double pricePerDay) {
        return days * pricePerDay;
    }
}

