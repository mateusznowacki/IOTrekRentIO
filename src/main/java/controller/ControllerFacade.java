package controller;

import model.ModelFacade;

public class ControllerFacade {
    private ModelFacade modelFacade;

    public ControllerFacade(ModelFacade modelFacade) {
        this.modelFacade = modelFacade;
    }

    public void handleAddEquipment(String name, String description, double pricePerDay, int quantity) {
        AddEquipmentController addEquipmentController = new AddEquipmentController(modelFacade);
        addEquipmentController.addEquipment(name, description, pricePerDay, quantity);
    }

    public void handleRent(int userId, int equipmentId, Date startDate, Date endDate, boolean isEmployee) {
        RentController rentController = new RentController(modelFacade);
        rentController.rentEquipment(userId, equipmentId, startDate, endDate, isEmployee);
    }

    public void handleExtendRental(int rentalId, int additionalDays) {
        RentController rentController = new RentController(modelFacade);
        rentController.extendRental(rentalId, additionalDays);
    }
}
