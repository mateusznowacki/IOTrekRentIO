package pl.pwr.view;

import pl.pwr.controller.AddEquipmentController;
import pl.pwr.model.Equipment;

import java.util.Scanner;

public class AddEquipmentView {
    private AddEquipmentController addEquipmentController;
    private Scanner scanner = new Scanner(System.in);

    public AddEquipmentView(AddEquipmentController addEquipmentController) {
        this.addEquipmentController = addEquipmentController;
    }

    public void displayAddEquipmentForm() {
        System.out.println("\n=== DODAWANIE NOWEGO SPRZĘTU ===");

        System.out.print("Podaj nazwę sprzętu: ");
        String name = scanner.nextLine();

        System.out.print("Podaj opis sprzętu: ");
        String description = scanner.nextLine();

        System.out.print("Podaj cenę za dobę: ");
        double pricePerDay = scanner.nextDouble();

        System.out.print("Podaj ilość: ");
        int quantity = scanner.nextInt();
        scanner.nextLine(); // Konsumowanie znaku nowej linii

        // Walidacja i dodanie sprzętu
        String result = addEquipmentController.addEquipment(name, description, pricePerDay, quantity);
        System.out.println(result);
    }
}
