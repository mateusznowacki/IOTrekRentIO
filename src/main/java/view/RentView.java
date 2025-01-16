package view;

import controller.ControllerFacade;
import model.Rental;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class RentView {
    private final ControllerFacade controllerFacade;
    private ViewFacade viewFacade;
    private final Scanner scanner;

    public RentView(ControllerFacade controllerFacade, ViewFacade viewFacade) {
        this.controllerFacade = controllerFacade;
        this.scanner = new Scanner(System.in);
        this.viewFacade = viewFacade;
    }

    private int getRentOrExitChoice() {
        System.out.println("\n=== Wypożyczanie Sprzętu ===");
        System.out.println("1. Wypożycz sprzęt");
        System.out.println("2. Powrót do menu głównego");
        System.out.print("Twój wybór: ");
        return scanner.nextInt();
    }


    public void displayRentForm() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate;
        Date endDate;
        int equipmentId;
        Rental overlappingRental;
        String confirmation;

        while (true) {
            int choice = getRentOrExitChoice();

            if (choice == 2) {
                System.out.println("Powrót do menu głównego...");
                return;
            }

            if (choice == 1) {
                if (!controllerFacade.checkNotEmptyEquipmentList()) {
                    System.out.println("Brak dostępnego sprzętu do wypożyczenia.");
                    return;
                } else {
                    viewFacade.displayAvailableEquipmentCatalogue();
                    System.out.print("Podaj ID sprzętu do wypożyczenia: ");
                    equipmentId = scanner.nextInt();


                    System.out.print("Podaj datę rozpoczęcia wypożyczenia (yyyy-MM-dd): ");
                    String startDateInput = scanner.next();
                    System.out.print("Podaj datę zakończenia wypożyczenia (yyyy-MM-dd): ");
                    String endDateInput = scanner.next();

                    try {
                        startDate = dateFormat.parse(startDateInput);
                        endDate = dateFormat.parse(endDateInput);
                        if (endDate.before(startDate)) {
                            System.out.println("Data zakończenia musi być późniejsza niż data rozpoczęcia.");
                            continue;
                        }

                        overlappingRental = controllerFacade.checkOverlappingRental(equipmentId, startDate, endDate);

                        if (overlappingRental != null) {
                            // Przedłużenie istniejącego wypożyczenia
                            System.out.println("Sprzęt jest już wypożyczony w tym okresie.");
                            System.out.print("Czy chcesz przedłużyć istniejące wypożyczenie? (tak/nie): ");
                            confirmation = scanner.next();

                            if (confirmation.equalsIgnoreCase("tak")) {

                                int additionalDays = controllerFacade.convertDate(endDate.getTime(), overlappingRental.getEndDate().getTime());
                                boolean extended = controllerFacade.extendRental(overlappingRental.getId(), additionalDays);

                                if (extended) {
                                    displayExtndRentSummary(additionalDays, overlappingRental.calculateCost());
                                    return;
                                } else {
                                    displayRentFailure();
                                }
                            } else if (confirmation.equalsIgnoreCase("nie")) { // dodanie nowego

                                displayRentSummary(equipmentId, startDate, endDate, startDate, endDate);

                                System.out.print("Czy potwierdzasz wypożyczenie? (tak/nie): ");
                                confirmation = scanner.next();

                                if (confirmation.equalsIgnoreCase("tak")) {
                                    if (controllerFacade.rentEquipment(equipmentId, startDate, endDate)) {
                                        System.out.println("Wypożyczenie zakończone sukcesem!");
                                        return;
                                    } else {
                                        displayRentFailure();
                                    }
                                } else {
                                    System.out.println("Anulowano wypożyczenie.");
                                }
                            }
                            return;
                        }
                    } catch (ParseException e) {
                        throw new RuntimeException(e);
                    }
                }
            } else {
                System.out.println("Nieprawidłowy wybór, spróbuj ponownie.");
            }
        }
    }

    private void displayRentFailure() {
        System.out.println("Nie udało się przedłużyć wypożyczenia. Spróbuj ponownie.");
    }

    private void displayExtndRentSummary(int additionalDays, double price) {
        System.out.println("Wypożyczenie zostało przedłużone o " + additionalDays + " dni. Jego całkowity koszt to " + price + " PLN.");
    }

    private void displayRentSummary(int equipmentId, Date startDate, Date endDate, Date startDate1, Date endDate1) {
        double cost = controllerFacade.calculateRentalCost(equipmentId, startDate, endDate);
        System.out.println("Całkowity koszt wypożyczenia: " + cost + " PLN");

    }


    public void displayRentalHistory(int userId) {
        List<Rental> rentals = controllerFacade.getRentalHistory(userId);
    }

    public void displayUserRentalHistory() {
        List<Rental> rentals = controllerFacade.getUserRentalHistory();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

        if (rentals.isEmpty()) {
            System.out.println("Brak historii wypożyczeń.");
            return;
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
        } else {
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

    public void displayAllRentals() {
        List<Rental> rentals = controllerFacade.getAllRentals();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

        if (rentals.isEmpty()) {
            System.out.println("Brak historii wypożyczeń.");
            return;
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

}



