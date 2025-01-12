package model;

public class RegularCostStrategy  implements RentalCostStrategy {

    @Override
    public double calculateCost(int days, double pricePerDay) {
        if (days <= 0 || pricePerDay < 0) {
            throw new IllegalArgumentException("Dni i cena za dzień muszą być większe niż 0");
        }
        return days * pricePerDay;
    }
}

