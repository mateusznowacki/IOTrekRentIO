package view;

import controller.ControllerFacade;
import model.Equipment;
import model.Rental;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class RentView {
    private final ControllerFacade controllerFacade;
    private ViewFacade viewFacade;
    private final Scanner scanner;

    public RentView(ControllerFacade controllerFacade) {
        this.controllerFacade = controllerFacade;
        this.scanner = new Scanner(System.in);
    }


    public void displayRentForm() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

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
                // Wyświetlenie katalogu sprzętu za pomocą ControllerFacade
                List<Equipment> availableEquipment = controllerFacade.getAvailableEquipment();
                if (availableEquipment.isEmpty()) {
                    System.out.println("Brak dostępnego sprzętu.");
                    return;
                }

                System.out.println("\nDostępny sprzęt:");
                for (Equipment equipment : availableEquipment) {
                    System.out.println("ID: " + equipment.getId() + ", Nazwa: " + equipment.getName() +
                            ", Cena za dobę: " + equipment.getPricePerDay());
                }

                System.out.print("Podaj ID sprzętu do wypożyczenia: ");
                int equipmentId = scanner.nextInt();

                // Sprawdzenie dostępności sprzętu za pomocą ControllerFacade
                if (!controllerFacade.isEquipmentAvailable(equipmentId)) {
                    System.out.println("Wybrany sprzęt nie jest dostępny.");
                    continue;
                }

                System.out.print("Podaj datę rozpoczęcia wypożyczenia (yyyy-MM-dd): ");
                String startDateInput = scanner.next();
                System.out.print("Podaj datę zakończenia wypożyczenia (yyyy-MM-dd): ");
                String endDateInput = scanner.next();

                try {
                    Date startDate = dateFormat.parse(startDateInput);
                    Date endDate = dateFormat.parse(endDateInput);

                    if (endDate.before(startDate)) {
                        System.out.println("Data zakończenia musi być późniejsza niż data rozpoczęcia.");
                        continue;
                    }

                    // Sprawdzenie czy użytkownik ma już sprzęt wypożyczony na ten okres

                    Rental overlappingRental = controllerFacade.checkOverlappingRental(equipmentId, startDate, endDate);

                    if (overlappingRental != null) {
                        // Przedłużenie istniejącego wypożyczenia
                        System.out.println("Sprzęt jest już wypożyczony w tym okresie.");
                        System.out.print("Czy chcesz przedłużyć istniejące wypożyczenie? (tak/nie): ");
                        String confirmation = scanner.next();
                        if (confirmation.equalsIgnoreCase("tak")) {
                            int additionalDays = (int) ((endDate.getTime() - overlappingRental.getEndDate().getTime()) / (1000 * 60 * 60 * 24));
                            boolean extended = controllerFacade.extendRental(overlappingRental.getId(), additionalDays);
                            if (extended) {
                                System.out.println("Wypożyczenie zostało przedłużone o " + additionalDays + " dni. Jego całkowity koszt to " + overlappingRental.calculateCost() + " PLN.");
                            } else {
                                System.out.println("Nie udało się przedłużyć wypożyczenia. Spróbuj ponownie.");
                            }
                        } else {
                            System.out.println("Anulowano wypożyczenie.");
                        }
                        return;
                    }

                    // Obliczanie kosztów dla nowego wypożyczenia
                    double cost = controllerFacade.calculateRentalCost(equipmentId, startDate, endDate);
                    System.out.println("Całkowity koszt wypożyczenia: " + cost + " PLN");

                    System.out.print("Czy potwierdzasz wypożyczenie? (tak/nie): ");
                    String confirmation = scanner.next();

                    if (confirmation.equalsIgnoreCase("tak")) {
                        if (controllerFacade.rentEquipment(equipmentId, startDate, endDate)) {
                            System.out.println("Wypożyczenie zakończone sukcesem!");
                            return;
                        } else {
                            System.out.println("Nie udało się wypożyczyć sprzętu. Spróbuj ponownie.");
                        }
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


    public void displayRentalHistory(int userId) {
        List<Rental> rentals = controllerFacade.getRentalHistory(userId);
    }

    public void displayUserRentalHistory() {
        List<Rental> rentals = controllerFacade.getUserRentalHistory();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

        if (rentals.isEmpty()) {
            System.out.println("Brak historii wypożyczeń.");
            return ;
        }

        System.out.println("=== Historia Wypożyczeń ===");
        System.out.printf("%-5s %-20s %-20s %-20s %-10s\n", "LP", "Sprzęt", "Data Wypożyczenia", "Data Zwrotu", "Koszt");
        System.out.println("--------------------------------------------------------------------------");

        int lp = 1;
        for (Rental rental : rentals) {
            String startDate = dateFormat.format(rental.getStartDate());
            String endDate = dateFormat.format(rental.getEndDate());
            double cost = rental.calculateCost();

            System.out.printf("%-5d %-20s %-20s %-20s %-10.2f\n",
                    lp,
                    rental.getEquipment().getName(),
                    startDate,
                    endDate,
                    cost);
            lp++;
        }
    }


    public void displayHistoryAndExtendRental() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        List<Rental> rentals = controllerFacade.getUserRentalHistory();

        if (rentals.isEmpty()) {
            System.out.println("Brak historii wypożyczeń.");
            return;
        }else {
            displayUserRentalHistory();
        }

        // Wybranie wypożyczenia do przedłużenia
        System.out.print("\nWybierz numer wypożyczenia, które chcesz przedłużyć (0 - anuluj): ");
        int choice = scanner.nextInt();
        if (choice > 0 && choice <= rentals.size()) {
            Rental selectedRental = rentals.get(choice - 1);
            System.out.print("Podaj liczbę dni, o którą chcesz przedłużyć wypożyczenie: ");
            int additionalDays = scanner.nextInt();

            // Sprawdzenie dostępności przedłużenia
            boolean success = controllerFacade.extendRental(selectedRental.getId(), additionalDays);
            if (success) {
                System.out.println("Wypożyczenie zostało przedłużone.");
            } else {
                System.out.println("Nie udało się przedłużyć wypożyczenia. Wybrany termin koliduje z innym wypożyczeniem.");
            }
        } else {
            System.out.println("Anulowano przedłużenie.");
        }
    }
}



