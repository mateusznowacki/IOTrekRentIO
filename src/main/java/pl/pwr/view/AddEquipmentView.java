package pl.pwr.view;
import pl.pwr.controller.AddEquipmentController;

import java.util.Scanner;

public class AddEquipmentView {
    private AddEquipmentController controller;

    public AddEquipmentView(AddEquipmentController controller) {
        this.controller = controller;
    }

    public void displayAddEquipmentForm() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Formularz dodawania nowego sprzętu:");

        System.out.print("Nazwa sprzętu: ");
        String name = scanner.nextLine();

        System.out.print("Opis sprzętu: ");
        String description = scanner.nextLine();

        System.out.print("Dostępna ilość sprzętu: ");
        int quantity = scanner.nextInt();

        System.out.print("Cena za dobę: ");
        double pricePerDay = scanner.nextDouble();

        // Przekazanie danych do kontrolera
        String result = controller.addEquipment(name, description, quantity, pricePerDay);
        System.out.println(result);
    }
}