package pl.pwr.model.done;

import java.util.Date;

public interface RentalFactory   {
    
    Rental createRental(Equipment equipment, Date startDate, Date endDate);
}
