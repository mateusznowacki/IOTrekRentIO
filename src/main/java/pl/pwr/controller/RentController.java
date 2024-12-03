package pl.pwr.controller;

import pl.pwr.model.*;

import java.util.Date;
import java.util.List;

public class RentController {
    private RentalStrategy rentalStrategy;
    private ModelFacade modelFacade;

    public RentController(RentalStrategy rentalStrategy, ModelFacade modelFacade) {
        this.rentalStrategy = rentalStrategy;
        this.modelFacade = modelFacade;
    }

    public void rentOrExtendEquipment(User user, Equipment equipment, Date startDate, Date endDate) {
        List<Rental> userRentals = modelFacade.getRentalsByUser(user.getId());
        int days = (int) ((endDate.getTime() - startDate.getTime()) / (1000 * 60 * 60 * 24));

        // Sprawdzamy, czy użytkownik już wynajął ten sprzęt
        for (Rental rental : userRentals) {
            if (rental.getEquipment().equals(equipment)) {
                System.out.println("Użytkownik już wynajął ten sprzęt. Przedłużanie wynajmu...");
                rental.extendRental(days);
                System.out.println("Wynajem przedłużony o " + days + " dni. Łączny czas wynajmu: " + rental.getDays() + " dni.");
                return;
            }
        }

        RentalCostStrategy costStrategy = new RegularCostStrategy();
        int userId = user.getId();

        // Wynajem nowego sprzętu
        System.out.println("Proces wynajmu sprzętu...");
        rentalStrategy.processRental(equipment.getId(), days);
        modelFacade.addRental(new Rental( equipment,  startDate,  endDate,  costStrategy, userId));
        System.out.println("Sprzęt został wynajęty na " + days + " dni.");
    }

    public void rentEquipment(int equipmentId, int days) {
        rentalStrategy.processRental(equipmentId, days);
    }

    public ModelFacade getModelFacade() {

    return null;
    }
}