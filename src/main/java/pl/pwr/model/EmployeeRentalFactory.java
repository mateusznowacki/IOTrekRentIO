package pl.pwr.model;

import java.util.Date;

public class EmployeeRentalFactory implements RentalFactory {

        @Override
        public Rental createRental(Equipment equipment, Date startDate, Date endDate, int userId) {
            RentalCostStrategy strategy = new EmployeeDiscountCostStrategy();
            return new Rental(equipment, startDate, endDate, strategy, userId);
        }

}

