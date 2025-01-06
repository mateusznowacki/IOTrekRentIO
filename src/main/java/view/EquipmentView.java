package view;

import controller.ControllerFacade;

import java.util.Scanner;

public class EquipmentView {
    private ControllerFacade controllerFacade;
    private Scanner scanner = new Scanner(System.in);

    public EquipmentView(ControllerFacade controllerFacade) {
        this.controllerFacade = controllerFacade;
    }

    public EquipmentView() {

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
    }

    public void blockEquipment() {
    }

    public void logMaintenance() {
    }

}
