package model.done;

import java.util.Date;

public class RegularRentalFactory  implements RentalFactory
{
    @Override
    public Rental createRental(int id, int userId, Equipment equipment, Date startDate, Date endDate) {
        RegularCostStrategy costStrategy = new RegularCostStrategy();
        return new Rental(id, userId, equipment, startDate, endDate, costStrategy);
    }
}
