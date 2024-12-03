package pl.pwr.controller;

import pl.pwr.model.ModelFacade;

public class ControllerFacade {
    private RentControllerFactory rentControllerFactory;


    public ControllerFacade(RentControllerFactory rentControllerFactory) {
        this.rentControllerFactory = rentControllerFactory;

    }

    public void handleRent(int equipmentId, int days, RentalStrategy strategy, ModelFacade modelFacade) {
        RentController rentController = rentControllerFactory.createController(strategy, modelFacade);
        rentController.rentEquipment(equipmentId, days);
    }

    public void handleAddEquipment(String name, String description, int quantity, double pricePerDay, ModelFacade modelFacade) {
        AddEquipmentController addEquipmentController = new AddEquipmentController(modelFacade);
        addEquipmentController.addEquipment(name, description, quantity, pricePerDay);
    }




}