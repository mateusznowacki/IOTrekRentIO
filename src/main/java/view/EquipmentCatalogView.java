package view;

import controller.ControllerFacade;
import model.done.Equipment;

import java.util.List;
import java.util.Scanner;

public class EquipmentCatalogView {
    private ControllerFacade controllerFacade;
    private ViewFacade viewFacade;
    private Scanner scanner;

    public EquipmentCatalogView(ControllerFacade controllerFacade, ViewFacade viewFacade) {
        this.controllerFacade = controllerFacade;
        this.viewFacade = viewFacade;
        this.scanner = new Scanner(System.in);
    }

    public void displayCatalogue() {
        List<Equipment> availableEquipment = controllerFacade.getAvailableEquipment();

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

        // Opcje użytkownika
        boolean running = true;
        while (running) {
            System.out.println("\nCo chcesz zrobić?");
            System.out.println("1. Wypożycz sprzęt");
            System.out.println("2. Powrót do menu głównego");
            System.out.print("Wybierz opcję: ");

            int choice = getValidInput(); // Pobierz poprawny wybór od użytkownika

            switch (choice) {
                case 1 -> viewFacade.displayRentForm();
                case 2 -> {
                    System.out.println("Powrót do menu głównego...");
                    running = false;
                   return;
                }
                default -> System.out.println("Nieprawidłowa opcja. Spróbuj ponownie.");
            }
        }
    }

    // Metoda pomocnicza do walidacji wyboru użytkownika
    private int getValidInput() {
        while (true) {
            if (scanner.hasNextInt()) {
                int choice = scanner.nextInt();
                scanner.nextLine(); // Konsumowanie nowej linii
                return choice; // Zwraca poprawny wybór
            } else {
                System.out.println("Nieprawidłowy wybór. Wpisz numer opcji.");
                scanner.nextLine(); // Konsumowanie błędnego wejścia
            }
        }
    }
}
