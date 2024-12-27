package controller;

import model.ModelFacade;

public class AddEquipmentController {
    private ModelFacade modelFacade;

    public AddEquipmentController(ModelFacade modelFacade) {
        this.modelFacade = modelFacade;
    }

    // Funkcja weryfikująca dane sprzętu
    public boolean validateEquipmentData(String name, String description, double pricePerDay, int quantity) {
        return !(name == null || name.isEmpty() || description == null || description.isEmpty() || pricePerDay <= 0 || quantity <= 0);
    }

    // Funkcja dodająca sprzęt do modelu
    public String addEquipment(String name, String description, double pricePerDay, int quantity) {
        if (!validateEquipmentData(name, description, pricePerDay, quantity)) {
            return "Nieprawidłowe dane sprzętu.";
        }
        return modelFacade.addEquipment(name, description, pricePerDay, quantity);
    }
}
