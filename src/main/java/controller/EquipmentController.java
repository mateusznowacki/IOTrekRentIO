package controller;

import model.ModelFacade;

import java.util.Scanner;

public class EquipmentController {
    private final ModelFacade modelFacade;

    public EquipmentController(ModelFacade modelFacade) {
        this.modelFacade = modelFacade;
    }

    public void addEquipment() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Podaj nazwę sprzętu:");
        String name = scanner.nextLine();

        System.out.println("Podaj opis sprzętu:");
        String description = scanner.nextLine();

        System.out.println("Podaj cenę za dzień:");
        double pricePerDay = scanner.nextDouble();

        System.out.println("Podaj ilość sprzętu:");
        int quantity = scanner.nextInt();

        if (!validateEquipmentData(name, description, pricePerDay, quantity)) {
            System.out.println("Nieprawidłowe dane sprzętu.");
            return;
        }

        String result = modelFacade.addEquipment(name, description, pricePerDay, quantity);
        System.out.println(result);
    }

    private boolean validateEquipmentData(String name, String description, double pricePerDay, int quantity) {
        return !(name == null || name.isEmpty() || description == null || description.isEmpty() || pricePerDay <= 0 || quantity <= 0);
    }
}
