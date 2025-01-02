package controller;

import model.done.RegularRentalFactory;
import model.done.User;
import model.done.RegularEquipmentFactory;
import model.done.SportEquipmentFactory;
import view.*;
import model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static List<User> registeredUsers = new ArrayList<>();
    private static AuthController authController = new AuthController();

    public static void main(String[] args) {
        startApp();
    }

    public static void startApp() {
        ModelFacade modelFacade = new ModelFacade(
                new SportEquipmentFactory(),
                new RegularEquipmentFactory(),
                new RegularRentalFactory(),
                new RegularRentalFactory()
        );

        EquipmentCatalogView equipmentCatalogView = new EquipmentCatalogView(modelFacade);
        AddEquipmentView addEquipmentView = new AddEquipmentView(new AddEquipmentController(modelFacade));
        ExtendRentalView extendRentalView = new ExtendRentalView(modelFacade);
        EquipmentDetailsView equipmentDetailsView = new EquipmentDetailsView(new RentController());
        UserAuthView userAuthView = new UserAuthView();
        RentController rentController = new RentController(modelFacade) ;
        RentView rentView = new RentView(rentController);

        // Inicjalizacja fasady widoków
        ViewFacade viewFacade = new ViewFacade(
                userAuthView,
                equipmentCatalogView,
                addEquipmentView,
                extendRentalView,
                equipmentDetailsView,
                modelFacade,
                rentView
        );

        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\n=== MENU GŁÓWNE ===");
            System.out.println("1. Zaloguj się");
            System.out.println("2. Wynajmij sprzęt");
            System.out.println("3. Przedłuż wynajem");
            System.out.println("4. Wyświetl katalog sprzętu");
            System.out.println("5. Wyświetl historię wypożyczeń");
            System.out.println("6. Zarejestruj się");
            System.out.println("7. Dodaj sprzęt");    //odoaj 7 case
            System.out.println("0. Wyjdź");

            System.out.print("Wybierz opcję: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Podaj ID użytkownika: ");
                    int userId = scanner.nextInt();
                    authController.loginUser(registeredUsers, userId);
                    break;
                case 2:
                    if (authController.isLoggedIn()) {
                        viewFacade.displayRentForm(authController.getLoggedInUser().getId(), modelFacade, equipmentCatalogView);
                    } else {
                        System.out.println("Musisz się zalogować, aby wynająć sprzęt.");
                    }
                    break;
                case 3:
                    if (authController.isLoggedIn()) {
                        extendRentalView.displayAndExtendRentalHistory(authController.getLoggedInUser().getId());
                    } else {
                        System.out.println("Musisz się zalogować, aby przedłużyć wynajem.");
                    }
                    break;
                case 4:
                    viewFacade.displayCatalogue( modelFacade.getAvailableEquipment());
                    break;
                case 5:
                    if (authController.isLoggedIn()) {
                        viewFacade.displayRentalHistory(authController.getLoggedInUser().getId());
                    } else {
                        System.out.println("Musisz się zalogować, aby wyświetlić historię wypożyczeń.");
                    }
                    break;
                case 6:
                    User newUser = userAuthView.registerUser();
                    registeredUsers.add(newUser);
                    break;
                case 7:
                    if (authController.isLoggedIn() && authController.isAdmin()) {
                        viewFacade.displayAddEquipmentForm();
                    } else {
                        System.out.println("Musisz być zalogowany jako ADMIN, aby dodać nowy sprzęt.");
                    }
                    break;

                case 0:
                    System.out.println("Zamykanie aplikacji...");
                    running = false;
                    break;
                default:
                    System.out.println("Nieprawidłowy wybór, spróbuj ponownie.");
            }
        }

        scanner.close();
    }
}
