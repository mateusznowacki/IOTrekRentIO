package controller;

import model.ModelFacade;
import model.Equipment;
import model.Rental;

import java.util.Date;
import java.util.List;

public class RentController {
    private final ModelFacade modelFacade;

    public RentController(ModelFacade modelFacade) {
        this.modelFacade = modelFacade;
    }

    public boolean rentEquipment(int userId, int equipmentId, Date startDate, Date endDate, boolean isEmployee) {
        Equipment equipment = modelFacade.getEquipmentById(equipmentId);

        if (equipment == null || !equipment.isAvailable()) {
            throw new IllegalArgumentException("Sprzęt jest niedostępny.");
        }

        boolean rental = modelFacade.rentEquipment( equipmentId, startDate, endDate);
        return rental;
    }


    public boolean extendRental(int rentalId, int additionalDays) {
        try {
            modelFacade.checkAndExtendRental(rentalId, additionalDays);
            return true;
        } catch (IllegalArgumentException e) {
            System.out.println("Błąd podczas przedłużania wypożyczenia: " + e.getMessage());
            return false;
        }
    }


    public boolean isEquipmentAvailable(int equipmentId) {
        return modelFacade.getEquipmentById(equipmentId).isAvailable();
    }

    public List<Rental> getUserRentalHistory(int userId) {
        return modelFacade.getUserRentalHistory(userId);
    }

    public double calculateRentalCost(int equipmentId, Date startDate, Date endDate) {
        return modelFacade.calculateRentalCost(equipmentId, startDate, endDate);
    }

    public boolean rentEquipment(int equipmentId, Date startDate, Date endDate) {
        return modelFacade.rentEquipment(equipmentId, startDate, endDate);
    }

    public Rental findOverlappingRental(int equipmentId, Date startDate, Date endDate) {
        List<Rental> userRentals = modelFacade.getUserRentalHistory(modelFacade.getLoggedUser().getId());
        return userRentals.stream()
                .filter(r -> r.getEquipment().getId() == equipmentId &&
                        startDate.before(r.getEndDate()) &&
                        endDate.after(r.getStartDate()))
                .findFirst()
                .orElse(null);
    }

}
