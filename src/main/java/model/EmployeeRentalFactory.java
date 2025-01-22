package model;

import java.util.Date;

public class EmployeeRentalFactory implements RentalFactory {

    @Override
    public Rental createRental(int id, int userId, Equipment equipment, Date startDate, Date endDate) {

        // Walidacja: sprzęt nie może być null
        if (equipment == null) {
            throw new IllegalArgumentException("Equipment cannot be null");
        }

        // Walidacja: daty nie mogą być null
        if (startDate == null || endDate == null) {
            throw new IllegalArgumentException("Start date and end date cannot be null");
        }

        // Walidacja: data zakończenia musi być późniejsza niż data rozpoczęcia
        if (!endDate.after(startDate)) {
            throw new IllegalArgumentException("End date must be after start date");
        }

        // Walidacja: id i userId muszą być dodatnie
        if (id <= 0) {
            throw new IllegalArgumentException("Id must be a positive number");
        }

        if (userId <= 0) {
            throw new IllegalArgumentException("User ID must be a positive number");
        }

        EmployeeDiscountCostStrategy costStrategy = new EmployeeDiscountCostStrategy();
        return new Rental(id, userId, equipment, startDate, endDate, costStrategy);
    }
}

