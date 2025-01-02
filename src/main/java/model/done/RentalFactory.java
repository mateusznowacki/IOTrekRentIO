package model.done;

import java.util.Date;

public interface RentalFactory   {
    Rental createRental(int id, int userId, Equipment equipment,
                        Date startDate, Date endDate);
}
