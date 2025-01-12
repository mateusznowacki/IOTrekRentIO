package controller;

import model.ModelFacade;

public class EquipmentController {
    private final ModelFacade modelFacade;

    public EquipmentController(ModelFacade modelFacade) {
        this.modelFacade = modelFacade;
    }

    public String addEquipment(String name, String description, double pricePerDay, int quantity) {
        return modelFacade.addEquipment(name, description, pricePerDay, quantity);
    }

    public boolean removeEquipment(int equipmentId) {
        return modelFacade.removeEquipment(equipmentId);

    }

    public boolean validateEquipmentData(String name, String description, double pricePerDay, int quantity) {
        return !(name == null || name.isEmpty() || description == null || description.isEmpty() || pricePerDay <= 0 || quantity <= 0);
    }
}
