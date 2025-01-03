package controller;

import model.ModelFacade;
import model.done.Equipment;
import model.done.Rental;

import java.util.Date;

public class RentController {
    private ModelFacade modelFacade;

    public RentController(ModelFacade modelFacade) {
        this.modelFacade = modelFacade;
    }

    public Rental rentEquipment(int userId, int equipmentId, Date startDate, Date endDate, boolean isEmployee) {
        Equipment equipment = modelFacade.getEquipmentById(equipmentId);

        if (equipment == null || !equipment.isAvailable()) {
            throw new IllegalArgumentException("Sprzęt jest niedostępny.");
        }

        Rental rental = modelFacade.rentEquipment(userId, equipmentId, startDate, endDate, isEmployee);
        return rental;
    }

    public void extendRental(int rentalId, int additionalDays) {
        modelFacade.extendRental(rentalId, additionalDays);
    }

    public boolean isEquipmentAvailable(int equipmentId) {
        return modelFacade.getEquipmentById(equipmentId).isAvailable();
    }
}
