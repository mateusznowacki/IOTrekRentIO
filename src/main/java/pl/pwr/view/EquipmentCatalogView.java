package pl.pwr.view;

import pl.pwr.model.Equipment;
import pl.pwr.model.ModelFacade;

import java.util.List;

public class EquipmentCatalogView {
    private ModelFacade modelFacade;

    public EquipmentCatalogView(ModelFacade modelFacade) {
        this.modelFacade = modelFacade;
    }

    public void displayCatalogue( List<Equipment> availableEquipment) {
        if (availableEquipment.isEmpty()) {
            System.out.println("Brak dostępnego sprzętu.");
        } else {
            System.out.println("\nKatalog dostępnego sprzętu:");
            for (Equipment equipment : availableEquipment) {
                System.out.println("ID: " + equipment.getId() +
                        ", Nazwa: " + equipment.getName() +
                        ", Cena za dobę: " + equipment.getPricePerDay());
            }
        }
    }
}
