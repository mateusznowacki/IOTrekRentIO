package view;


import controller.RentController;
import model.ModelFacade;

import java.util.Date;
import java.util.Scanner;

public class RentView {
    private RentController rentController;

    public RentView(RentController rentController) {
        this.rentController = rentController;
    }

    public void displayRentForm(int userId, ModelFacade modelFacade, EquipmentCatalogView equipmentCatalogView) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== MENU WYPOŻYCZENIA SPRZĘTU ===");
            System.out.println("1. Wybierz sprzęt do wypożyczenia");
            System.out.println("2. Powrót do menu głównego");
            System.out.print("Wybierz opcję: ");
            int choice = scanner.nextInt();

            if (choice == 2) {
                System.out.println("Powrót do menu głównego...");
                return;
            }

            if (choice == 1) {
                // Wyświetlenie katalogu sprzętu
                equipmentCatalogView.displayCatalogue(modelFacade.getAvailableEquipment());

                System.out.print("Podaj ID sprzętu do wypożyczenia: ");
                int equipmentId = scanner.nextInt();

                // Sprawdzenie, czy sprzęt jest już wypożyczony przez klienta
                if (rentController.isEquipmentRentedByUser(userId, equipmentId)) {
                    System.out.println("Ten sprzęt jest już w Twoich wypożyczeniach.");
                    continue;
                }

                // Sprawdzenie dostępności sprzętu
                if (!rentController.isEquipmentAvailable(equipmentId)) {
                    System.out.println("Wybrany sprzęt nie jest dostępny.");
                    continue;
                }

                System.out.print("Podaj datę rozpoczęcia wypożyczenia (yyyy-MM-dd): ");
                String startDateInput = scanner.next();
                System.out.print("Podaj datę zakończenia wypożyczenia (yyyy-MM-dd): ");
                String endDateInput = scanner.next();

                try {
                    Date startDate = RentController.parseDate(startDateInput);
                    Date endDate = RentController.parseDate(endDateInput);

                    double cost = rentController.calculateRentalCost(equipmentId, startDate, endDate);
                    System.out.println("Całkowity koszt wypożyczenia: " + cost + " PLN");

                    System.out.print("Czy potwierdzasz wypożyczenie? (tak/nie): ");
                    String confirmation = scanner.next();

                    if (confirmation.equalsIgnoreCase("tak")) {
                        rentController.rentEquipment(userId, equipmentId, startDate, endDate);
                        System.out.println("Wypożyczenie zakończone sukcesem!");
                        return;
                    } else {
                        System.out.println("Anulowano wypożyczenie.");
                    }

                } catch (Exception e) {
                    System.out.println("Błąd podczas parsowania daty. Upewnij się, że format to yyyy-MM-dd.");
                }
            } else {
                System.out.println("Nieprawidłowy wybór, spróbuj ponownie.");
            }
        }
    }
}