package pl.pwr.view;

import pl.pwr.model.Equipment;
import pl.pwr.model.ModelFacade;

import java.util.Scanner;
public class EquipmentCatalogView {

        private ModelFacade modelFacade;
        private EquipmentDetailsView equipmentDetailsView;

        public EquipmentCatalogView(ModelFacade modelFacade, EquipmentDetailsView equipmentDetailsView) {
            this.modelFacade = modelFacade;
            this.equipmentDetailsView = equipmentDetailsView;
        }

        public void displayCatalogue() {
            System.out.println("Katalog dostępnego sprzętu:");
            modelFacade.getAvailableEquipment().forEach(equipment -> {
                System.out.println("ID: " + equipment.getId() + ", Nazwa: " + equipment.getName() +
                        ", Cena za dobę: " + equipment.getPricePerDay());
            });

            System.out.print("Wpisz ID sprzętu, aby zobaczyć szczegóły (lub -1, aby zakończyć): ");
            Scanner scanner = new Scanner(System.in);
            int equipmentId = scanner.nextInt();

            if (equipmentId == -1) return;

            Equipment equipment = modelFacade.getEquipmentById(equipmentId);
            if (equipment != null) {
                equipmentDetailsView.displayDetails(equipment);
            } else {
                System.out.println("Sprzęt o podanym ID nie istnieje.");
            }
        }
    }


