package view;

import controller.ControllerFacade;

import java.util.Scanner;

public class EquipmentView {
    private final ControllerFacade controllerFacade;
    private final ViewFacade viewFacade;
    private final Scanner scanner = new Scanner(System.in);

    public EquipmentView(ControllerFacade controllerFacade, ViewFacade viewFacade) {
        this.controllerFacade = controllerFacade;
        this.viewFacade = viewFacade;
    }

    public void displayAddEquipmentForm() {
        displayAviableEquipmentTypesToAdd();

        int choice = scanner.nextInt();
        scanner.nextLine(); // Konsumowanie znaku nowej linii

        System.out.print("Podaj nazwę sprzętu: ");
        String name = scanner.nextLine();

        System.out.print("Podaj opis sprzętu: ");
        String description = scanner.nextLine();

        System.out.print("Podaj cenę za dzień: ");
        double pricePerDay = scanner.nextDouble();

        System.out.print("Podaj ilość sprzętu: ");
        int quantity = scanner.nextInt();

        System.out.print("Podaj typ 1. Normalny 2. Sporotwy: ");
        int type = scanner.nextInt();

        if (choice == 1) {
            {
                System.out.print("Podaj liczbę biegów w rowerze: ");
                int gearCount = scanner.nextInt();
                if (type == 1) { // Zwykły rower
                    boolean success = controllerFacade.addBike(name, description, pricePerDay, gearCount, quantity);
                    System.out.println(success ? "Rower został dodany!" : "Błąd: Rower o podanej nazwie już istnieje.");
                } else if (type == 2) { // Sportowy rower
                    boolean success = controllerFacade.addSportBike(name, description, pricePerDay, gearCount, quantity);
                    System.out.println(success ? "Rower sportowy został dodany!" : "Błąd: Rower sportowy o podanej nazwie już istnieje.");
                } else {
                    displayInvalidEquipmentType();
                }
            }
        } else if (choice == 2) {
            System.out.print("Podaj liczbę osób, które mogą spać w namiocie: ");
            int capacity = scanner.nextInt();
            if (type == 1) { // Namiot standardowy
                boolean success = controllerFacade.addTent(name, description, pricePerDay, capacity, quantity);
                System.out.println(success ? "Namiot został dodany!" : "Błąd: Namiot o podanej nazwie już istnieje.");
            } else if (type == 2) { // Namiot sportowy
                boolean success = controllerFacade.addSportTent(name, description, pricePerDay, capacity, quantity);
                System.out.println(success ? "Namiot sportowy został dodany!" : "Błąd: Namiot sportowy o podanej nazwie już istnieje.");
            } else {
                displayInvalidEquipmentType();
            }
        } else if (choice == 3) {
            System.out.print("Podaj pojemność plecaka (w litrach): ");
            int volume = scanner.nextInt();
            if (type == 1) { // Plecak turystyczny
                boolean success = controllerFacade.addBackpack(name, description, pricePerDay, volume, quantity);
                System.out.println(success ? "Plecak turystyczny został dodany!" : "Błąd: Plecak turystyczny o podanej nazwie już istnieje.");
            } else if (type == 2) { // Plecak sportowy
                boolean success = controllerFacade.addSportBackpack(name, description, pricePerDay, volume, quantity);
                System.out.println(success ? "Plecak sportowy został dodany!" : "Błąd: Plecak sportowy o podanej nazwie już istnieje.");
            } else {
                System.out.println("Nieprawidłowy typ plecaka.");
            }
        } else {
            displayInvalidEquipmentType();
        }
    }

    private static void displayInvalidEquipmentType() {
        System.out.println("Nieprawidłowy wybór typu zwykly/sprtowy.");
    }

    private void displayAviableEquipmentTypesToAdd() {
    System.out.println("\n=== Formularz Dodawania Sprzętu ===");
    System.out.println("Wybierz typ sprzętu do dodania:");
    System.out.println("1. Rower");
    System.out.println("2. Namiot");
    System.out.println("3. Plecak");
    System.out.print("Twój wybór: ");
}


public void removeEquipment() {
    viewFacade.displayEquipmentWithId();
    Scanner scanner = new Scanner(System.in);
    System.out.print("Podaj ID sprzętu do usunięcia: ");
    int equipmentId = scanner.nextInt();

    // Tutaj zaimplementuj logikę usuwania sprzętu w modelu lub bazie danych
    boolean success = controllerFacade.handleRemoveEquipment(equipmentId);
    System.out.println(success ? "Sprzęt został usunięty." : "Błąd: Nie znaleziono sprzętu o podanym ID.");
}

public void blockEquipment() {
    viewFacade.displayEquipmentWithId();
    Scanner scanner = new Scanner(System.in);
    System.out.print("Podaj ID sprzętu do zablokowania: ");
    int equipmentId = scanner.nextInt();

    // Tutaj zaimplementuj logikę blokowania sprzętu
    boolean success = controllerFacade.handleBlockEquipment(equipmentId);
    System.out.println(success ? "Sprzęt został zablokowany (isAvailable = false)." : "Błąd: Nie znaleziono sprzętu o podanym ID.");
}

public void logMaintenance() {
    viewFacade.displayEquipmentWithId();
    Scanner scanner = new Scanner(System.in);
    System.out.print("Podaj ID sprzętu: ");
    int equipmentId = scanner.nextInt();
    scanner.nextLine(); // Konsumowanie nowej linii
    System.out.print("Podaj opis naprawy: ");
    String repairDescription = scanner.nextLine();

    // Tutaj zaimplementuj logikę dodawania opisu naprawy
    boolean success = controllerFacade.handleRepairEquipment(equipmentId, repairDescription);
    System.out.println(success ? "Sprzęt został oznaczony jako naprawiony." : "Błąd: Nie znaleziono sprzętu o podanym ID.");
}

public void displayEquimentManagement() {
    Scanner scanner = new Scanner(System.in);
    boolean running = true;


    if (!controllerFacade.checkCredentials()) {
        System.out.println("Brak uprawnień do zarządzania sprzętem. Powrót do menu głównego.");
        return;
    }

    while (running) {
        System.out.println("\n=== Zarządzanie Sprzętem ===");
        System.out.println("1. Dodaj sprzęt");
        System.out.println("2. Usuń sprzęt");
        System.out.println("3. Zablokuj sprzęt ");
        System.out.println("4. Oznacz sprzęt jako naprawiony");
        //  System.out.println("5. Zwróć sprzęt");
        System.out.println("0. Powrót do głównego menu");
        System.out.print("Wybierz opcję: ");

        int choice = scanner.nextInt();
        scanner.nextLine(); // Konsumowanie nowej linii

        switch (choice) {
            case 1 -> {
                System.out.println("\nDodawanie sprzętu...");
                displayAddEquipmentForm();
            }
            case 2 -> {
                System.out.println("\nUsuwanie sprzętu...");
                removeEquipment();
            }
            case 3 -> {
                System.out.println("\nBlokowanie sprzętu...");
                blockEquipment();
            }
            case 4 -> {
                System.out.println("\nLogowanie naprawy...");
                logMaintenance();
            }
//                case 5 -> {
//                    System.out.println("\nZwracanie sprzętu...");
//                    returnEquipment();
//                }
            case 0 -> {
                System.out.println("Powrót do głównego menu...");
                running = false;
            }
            default -> System.out.println("Nieprawidłowy wybór. Spróbuj ponownie.");
        }
    }
}

private void returnEquipment() {
    viewFacade.displayAllRentals();
    Scanner scanner = new Scanner(System.in);
    System.out.print("Podaj ID wypozyczenia  do zwrotu: ");
    int rentalId = scanner.nextInt();

    // Tutaj zaimplementuj logikę usuwania sprzętu w modelu lub bazie danych
    boolean success = controllerFacade.handleReturnEquipment(rentalId);
    System.out.println(success ? "Sprzęt został usunięty." : "Błąd: Nie znaleziono sprzętu o podanym ID.");
}

}

