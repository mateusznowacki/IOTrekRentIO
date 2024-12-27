package model.done;

import java.util.Date;

public class EmployeeRentalFactory implements RentalFactory {

    @Override
    public Rental createRental(int id, int userId, Equipment equipment, Date startDate, Date endDate) {
        EmployeeDiscountCostStrategy costStrategy = new EmployeeDiscountCostStrategy();
        return new Rental(id, userId, equipment, startDate, endDate, costStrategy);
    }
}

