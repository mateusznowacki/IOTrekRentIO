package pl.pwr.model;

import java.util.Date;

public interface RentalFactory   {

    Rental createRental(Equipment equipment, Date startDate, Date endDate, int userId);
}
