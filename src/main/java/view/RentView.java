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
    private EquipmentCatalogView equipmentCatalogView;
    private final Scanner scanner;

    public RentView(ControllerFacade controllerFacade, ViewFacade viewFacade, EquipmentCatalogView e) {
        this.controllerFacade = controllerFacade;
        this.scanner = new Scanner(System.in);
        this.viewFacade = viewFacade;
        this.equipmentCatalogView = e;
    }

    private int getRentOrExitChoice() {
        System.out.println("\n=== Wypożyczanie Sprzętu ===");
        System.out.println("1. Wypożycz sprzęt");
        System.out.println("2. Powrót do menu głównego");
        System.out.print("Twój wybór: ");
        return scanner.nextInt();
    }


    public void displayRentForm() {
        Date[] dates = new Date[2];
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
            } else if (choice == 1) {
                if (controllerFacade.checkNotEmptyEquipmentList() == false) {
                    System.out.println("Brak dostępnego sprzętu do wypożyczenia.");
                    return;
                } else {
                    equipmentCatalogView.displayAvailableEquipmentCatalogue();
                    System.out.print("Podaj ID sprzętu do wypożyczenia: ");
                    equipmentId = scanner.nextInt();

                    dates = getRentalDates();
                    startDate = dates[0];
                    endDate = dates[1];

                    overlappingRental = controllerFacade.checkOverlappingRental(equipmentId, startDate, endDate);

                    if (overlappingRental != null) {
                        // Przedłużenie istniejącego wypożyczenia
                        System.out.println("Sprzęt jest już wypożyczony w tym okresie.");
                        System.out.print("Czy chcesz przedłużyć istniejące wypożyczenie? (tak/nie): ");
                        confirmation = scanner.next();

                        if (confirmation.equalsIgnoreCase("tak")) {

                            int additionalDays = controllerFacade.convertDate(endDate.getTime(), overlappingRental.getEndDate().getTime());
                            boolean extended = controllerFacade.extendRental(overlappingRental.getId(), additionalDays);

                            if (extended == true) {
                                displayExtndRentSummary(additionalDays, overlappingRental.calculateCost());
                                return;
                            } else {
                                displayRentFailure();
                            }
                        } else { // dodanie nowego

                            displayRentSummary(equipmentId, startDate, endDate);

                            System.out.print("Czy potwierdzasz wypożyczenie? (tak/nie): ");
                            confirmation = scanner.next();

                            if (confirmation.equalsIgnoreCase("tak")) {
                                boolean success = controllerFacade.rentEquipment(equipmentId, startDate, endDate);
                                if (success == true) {
                                    displaySuccessRentInfo();
                                    return;
                                } else {
                                    displayRentFailure();
                                }
                            } else {
                                displayCanceledRentInfo();
                            }
                        }
                        return;
                    } else {
                        displayRentSummary(equipmentId, startDate, endDate);

                        System.out.print("Czy potwierdzasz wypożyczenie? (tak/nie): ");
                        confirmation = scanner.next();//

                        if (confirmation.equalsIgnoreCase("tak")) {
                            boolean success = controllerFacade.rentEquipment(equipmentId, startDate, endDate);
                            if (success == true) {
                                displaySuccessRentInfo();
                                return;
                            } else {
                                displayRentFailure();
                            }
                        } else {
                            displayCanceledRentInfo();
                        }

                    }
                }
            } else {
                System.out.println("Nieprawidłowy wybór, spróbuj ponownie.");
            }
        }
    }

    private static void displaySuccessRentInfo() {
        System.out.println("Wypożyczenie zakończone sukcesem!");
    }

    private static void displayCanceledRentInfo() {
        System.out.println("Anulowano wypożyczenie.");
    }

    private void displayRentFailure() {
        System.out.println("Nie udało się przedłużyć wypożyczenia. Spróbuj ponownie.");
    }

    private void displayExtndRentSummary(int additionalDays, double price) {
        System.out.println("Wypożyczenie zostało przedłużone o " + additionalDays + " dni. Jego całkowity koszt to " + price + " PLN.");
    }

    private void displayRentSummary(int equipmentId, Date startDate, Date endDate) {
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


    private Date[] getRentalDates() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            System.out.print("Podaj datę rozpoczęcia wypożyczenia (yyyy-MM-dd): ");
            Date startDate = dateFormat.parse(scanner.next());
            System.out.print("Podaj datę zakończenia wypożyczenia (yyyy-MM-dd): ");
            Date endDate = dateFormat.parse(scanner.next());

            if (endDate.before(startDate)) {
                System.out.println("Data zakończenia musi być późniejsza niż data rozpoczęcia.");
                return null;
            }
            return new Date[]{startDate, endDate};
        } catch (ParseException e) {
            System.out.println("Nieprawidłowy format daty. Spróbuj ponownie.");
            return null;
        }
    }


}



