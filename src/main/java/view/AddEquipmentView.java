package view;

import controller.AddEquipmentController;

import java.util.Scanner;

public class AddEquipmentView {
    private AddEquipmentController addEquipmentController;
    private Scanner scanner = new Scanner(System.in);

    public AddEquipmentView(AddEquipmentController addEquipmentController) {
        this.addEquipmentController = addEquipmentController;
    }

    // Funkcja do zbierania danych od użytkownika
    private String[] collectEquipmentData() {
        System.out.print("Podaj nazwę sprzętu: ");
        String name = scanner.nextLine();

        System.out.print("Podaj opis sprzętu: ");
        String description = scanner.nextLine();

        System.out.print("Podaj cenę za dobę: ");
        double pricePerDay = scanner.nextDouble();

        System.out.print("Podaj ilość: ");
        int quantity = scanner.nextInt();
        scanner.nextLine(); // Konsumowanie znaku nowej linii

        return new String[]{name, description, String.valueOf(pricePerDay), String.valueOf(quantity)};
    }

    // Funkcja wyświetlająca formularz dodawania sprzętu
    public void displayAddEquipmentForm() {
        System.out.println("\n=== DODAWANIE NOWEGO SPRZĘTU ===");
        String[] data = collectEquipmentData();

        String name = data[0];
        String description = data[1];
        double pricePerDay = Double.parseDouble(data[2]);
        int quantity = Integer.parseInt(data[3]);

        String result = addEquipmentController.addEquipment(name, description, pricePerDay, quantity);
        displayAddEquipmentResult(result);
    }

    // Funkcja wyświetlająca komunikat o wyniku dodawania sprzętu
    private void displayAddEquipmentResult(String result) {
        if (result.contains("pomyślnie")) {
            System.out.println("✅ " + result);
        } else {
            System.out.println("❌ " + result);
        }
    }
}
