package view;

import controller.ControllerFacade;

import java.util.Scanner;

public class  EquipmentView {
    private ControllerFacade controllerFacade;
    private ViewFacade viewFacade;
    private Scanner scanner = new Scanner(System.in);

    public EquipmentView(ControllerFacade controllerFacade, ViewFacade viewFacade) {
        this.controllerFacade = controllerFacade;
        this.viewFacade = viewFacade;
    }

      public void displayAddEquipmentForm() {
        System.out.println("\n=== Formularz Dodawania Sprzętu ===");
        System.out.println("Wybierz typ sprzętu do dodania:");
        System.out.println("1. Rower");
        System.out.println("2. Namiot");
        System.out.println("3. Plecak");
        System.out.print("Twój wybór: ");

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

        switch (choice) {
            case 1 -> {
                System.out.print("Podaj liczbę biegów w rowerze: ");
                int gearCount = scanner.nextInt();
                boolean success = controllerFacade.addBike(name, description, pricePerDay, gearCount, quantity);
                System.out.println(success ? "Rower został dodany!" : "Błąd: Rower o podanej nazwie już istnieje.");
            }
            case 2 -> {
                System.out.print("Podaj liczbę osób, które mogą spać w namiocie: ");
                int capacity = scanner.nextInt();
                boolean success = controllerFacade.addTent(name, description, pricePerDay, capacity, quantity);
                System.out.println(success ? "Namiot został dodany!" : "Błąd: Namiot o podanej nazwie już istnieje.");
            }
            case 3 -> {
                System.out.print("Podaj pojemność plecaka (w litrach): ");
                int volume = scanner.nextInt();
                boolean success = controllerFacade.addBackpack(name, description, pricePerDay, volume, quantity);
                System.out.println(success ? "Plecak został dodany!" : "Błąd: Plecak o podanej nazwie już istnieje.");
            }
            default -> System.out.println("Nieprawidłowy wybór.");
        }
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
            System.out.println("5. Powrót do głównego menu");
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
                case 5 -> {
                    System.out.println("Powrót do głównego menu...");
                    running = false;
                }
                default -> System.out.println("Nieprawidłowy wybór. Spróbuj ponownie.");
            }
        }
    }
}
