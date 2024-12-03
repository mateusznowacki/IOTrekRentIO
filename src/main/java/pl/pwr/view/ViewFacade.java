package pl.pwr.view;


import pl.pwr.model.Equipment;
import pl.pwr.model.ModelFacade;

import java.util.Scanner;

public class ViewFacade {
    private EquipmentCatalogView EquipmentCatalogView;
    private AddEquipmentView addEquipmentView;
    private ExtendRentalView extendRentalView;
    private EquipmentDetailsView equipmentDetailsView;
    private ModelFacade modelFacade;

    public ViewFacade(
            EquipmentCatalogView EquipmentCatalogView,
            AddEquipmentView addEquipmentView,
            ExtendRentalView extendRentalView,
            EquipmentDetailsView equipmentDetailsView,
            ModelFacade modelFacade
    ) {
        this.EquipmentCatalogView = EquipmentCatalogView;
        this.addEquipmentView = addEquipmentView;
        this.extendRentalView = extendRentalView;
        this.equipmentDetailsView = equipmentDetailsView;
        this.modelFacade = modelFacade;
    }

    public void displayCatalogue() {
        EquipmentCatalogView.displayCatalogue();
    }

    public void displayAddEquipmentForm() {
        addEquipmentView.displayAddEquipmentForm();
    }

    public void displayExtendRentalForm(int userId) {
        extendRentalView.displayExtendRentalForm(userId);
    }

    public void displayEquipmentDetails(int equipmentId) {
        Equipment equipment = modelFacade.getEquipmentById(equipmentId);
        if (equipment != null) {
            equipmentDetailsView.displayDetails(equipment);
        } else {
            System.out.println("Sprzęt o podanym ID nie istnieje.");
        }
    }

    public void start() {
        System.out.println("=== MENU ===");
        System.out.println("1. Wyświetl katalog sprzętu");
        System.out.println("2. Dodaj nowy sprzęt");
        System.out.println("3. Przedłuż wynajem");
        System.out.println("4. Wyświetl szczegóły sprzętu");
        System.out.println("0. Wyjście");

        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.print("Wybierz opcję: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    displayCatalogue();
                    break;
                case 2:
                    displayAddEquipmentForm();
                    break;
                case 3:
                    System.out.print("Podaj ID użytkownika: ");
                    int userId = scanner.nextInt();
                    displayExtendRentalForm(userId);
                    break;
                case 4:
                    System.out.print("Podaj ID sprzętu: ");
                    int equipmentId = scanner.nextInt();
                    displayEquipmentDetails(equipmentId);
                    break;
                case 0:
                    System.out.println("Zakończenie programu.");
                    break;
                default:
                    System.out.println("Nieprawidłowa opcja. Spróbuj ponownie.");
            }
        } while (choice != 0);
    }
}
