package pl.pwr.controller;

import pl.pwr.model.ModelFacade;

public class AddEquipmentController {

    private ModelFacade modelFacade;

    public AddEquipmentController(ModelFacade modelFacade) {
        this.modelFacade = modelFacade;
    }

    public String addEquipment(String name, String description, int quantity, double pricePerDay) {
        return modelFacade.addEquipment(name, description, pricePerDay, quantity);
    }
}