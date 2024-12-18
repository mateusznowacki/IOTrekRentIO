package pl.pwr.controller;

import pl.pwr.model.Equipment;
import pl.pwr.model.ModelFacade;
import pl.pwr.model.Rental;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class RentController {
    private RentalStrategy rentalStrategy;
    private ModelFacade modelFacade;

    public RentController(RentalStrategy rentalStrategy, ModelFacade modelFacade) {
        this.rentalStrategy = rentalStrategy;
        this.modelFacade = modelFacade;
    }

    public RentController() {
    }

    public RentController(ModelFacade modelFacade) {
    }

    public static Date parseDate(String dateInput) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setLenient(false); // Zapobiega parsowaniu nieprawidłowych dat, np. 2024-02-30

        try {
            return sdf.parse(dateInput);
        } catch (ParseException e) {
            System.out.println("Nieprawidłowy format daty. Użyj formatu yyyy-MM-dd.");
            return null;
        }
    }


    public boolean isEquipmentRentedByUser(int userId, int equipmentId) {
        List<Rental> userRentals = modelFacade.getUserRentalHistory(userId);
        for (Rental rental : userRentals) {
            if (rental.getEquipment().getId() == equipmentId) {
                return true;
            }
        }
        return false;
    }

    public boolean isEquipmentAvailable(int equipmentId) {
        Equipment equipment = modelFacade.getEquipmentById(equipmentId);
        return equipment != null && equipment.isAvailable();
    }

    public double calculateRentalCost(int equipmentId, Date startDate, Date endDate) {
        Equipment equipment = modelFacade.getEquipmentById(equipmentId);
        long diffInMillies = endDate.getTime() - startDate.getTime();
        int days = (int) (diffInMillies / (1000 * 60 * 60 * 24)) + 1;
        return days * equipment.getPricePerDay();
    }


    public void rentEquipment(int userId, int equipmentId, Date startDate, Date endDate) {
        Equipment equipment = modelFacade.getEquipmentById(equipmentId);

        if (equipment == null) {
            System.out.println("Nie znaleziono sprzętu o podanym ID.");
            return;
        }

        if (!equipment.isAvailable()) {
            System.out.println("Wybrany sprzęt jest już wypożyczony.");
            return;
        }

        Rental rental = modelFacade.createRegularRental(equipment, startDate, endDate, userId);
        System.out.println("Sprzęt został pomyślnie wypożyczony: " + rental.getEquipment().getName());
    }



    public void rentEquipment(int equipmentId, int days) {
        rentalStrategy.processRental(equipmentId, days);
    }

    public ModelFacade getModelFacade() {

        return null;
    }
}