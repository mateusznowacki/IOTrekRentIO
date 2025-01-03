package view;

import controller.AddEquipmentController;
import controller.AuthController;
import controller.RentController;
import model.ModelFacade;
import model.done.*;

import java.util.List;
import java.util.Scanner;

public class ViewFacade {
    private UserAuthView userAuthView;
    private EquipmentCatalogView equipmentCatalogView;
    private AddEquipmentView addEquipmentView;
    private ExtendRentalView extendRentalView;
    private EquipmentDetailsView equipmentDetailsView;
    private RentView rentView;
    private ModelFacade modelFacade;
    private AuthController authController;

    public ViewFacade(UserAuthView userAuthView, EquipmentCatalogView equipmentCatalogView,
                      AddEquipmentView addEquipmentView, ExtendRentalView extendRentalView,
                      EquipmentDetailsView equipmentDetailsView, RentView rentView,
                      ModelFacade modelFacade, AuthController authController) {
        this.userAuthView = userAuthView;
        this.equipmentCatalogView = equipmentCatalogView;
        this.addEquipmentView = addEquipmentView;
        this.extendRentalView = extendRentalView;
        this.equipmentDetailsView = equipmentDetailsView;
        this.rentView = rentView;
        this.modelFacade = modelFacade;
        this.authController = authController;
    }


    public void displayMainMenu() {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\n=== MENU GŁÓWNE ===");
            System.out.println("1. Logowanie");
            System.out.println("2. Zarządzanie użytkownikami");
            System.out.println("3. Zarządzanie wypożyczeniami");
            System.out.println("4. Zarządzanie sprzętem");
            System.out.println("0. Wyjście");

            System.out.print("Wybierz opcję: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1 -> handleLogin();
                case 2 -> displayUserManagementMenu();
                case 3 -> displayRentalManagementMenu();
                case 4 -> displayEquipmentManagementMenu();
                case 0 -> {
                    System.out.println("Zamykanie aplikacji...");
                    running = false;
                }
                default -> System.out.println("Nieprawidłowy wybór, spróbuj ponownie.");
            }
        }
        scanner.close();
    }
    private void handleLogin() {
        try {
            // Delegowanie logowania do kontrolera
            User loggedInUser = authController.loginUser();
            if (loggedInUser != null) {
                System.out.println("Zalogowano jako: " + loggedInUser.getName());
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Błąd logowania: " + e.getMessage());
        }
    }


    private void displayUserManagementMenu() {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\n=== ZARZĄDZANIE UŻYTKOWNIKAMI ===");
            System.out.println("1. Rejestracja użytkownika");
            System.out.println("2. Wyświetlanie listy użytkowników");
            System.out.println("3. Nadawanie/edycja ról");
            System.out.println("4. Blokowanie kont");
            System.out.println("0. Powrót");

            System.out.print("Wybierz opcję: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1 -> registerUser();
                case 2 -> displayUserList();
                case 3 -> editUserRole();
                case 4 -> blockUser();
                case 0 -> running = false;
                default -> System.out.println("Nieprawidłowy wybór, spróbuj ponownie.");
            }
        }
    }

    private void registerUser() {
        User newUser = userAuthView.registerUser();
        if (newUser != null) {
            LocalStorage.getInstance().getUsers().add(newUser);
            System.out.println("Rejestracja zakończona sukcesem.");
        }
    }

    private void displayUserList() {
        List<User> users = LocalStorage.getInstance().getUsers();
        userAuthView.displayUserList(users);
    }

    private void editUserRole() {
        try {
            userAuthView.editUserRole(authController, LocalStorage.getInstance().getUsers());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void blockUser() {
        try {
            userAuthView.blockUser(authController, LocalStorage.getInstance().getUsers());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }



    private void displayRentalManagementMenu() {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\n=== ZARZĄDZANIE WYPOŻYCZENIAMI ===");
            System.out.println("1. Wypożyczanie sprzętu");
            System.out.println("2. Przedłużanie wypożyczenia");
            System.out.println("3. Monitorowanie zwrotów");
            System.out.println("4. Przeglądanie historii wypożyczeń");
            System.out.println("0. Powrót");

            System.out.print("Wybierz opcję: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1 -> rentView.displayRentForm();
                case 2 -> extendRentalView.displayAndExtendRentalHistory();
                case 3 -> rentView.displayReturnMonitoring();
                case 4 -> rentView.displayRentalHistory();
                case 0 -> running = false;
                default -> System.out.println("Nieprawidłowy wybór, spróbuj ponownie.");
            }
        }
    }

    private void displayEquipmentManagementMenu() {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\n=== ZARZĄDZANIE SPRZĘTEM ===");
            System.out.println("1. Dodawanie nowego sprzętu");
            System.out.println("2. Usuwanie sprzętu");
            System.out.println("3. Blokowanie sprzętu");
            System.out.println("4. Rejestrowanie napraw i przeglądów");
            System.out.println("0. Powrót");

            System.out.print("Wybierz opcję: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1 -> addEquipmentView.displayAddEquipmentForm();
                case 2 -> addEquipmentView.removeEquipment();
                case 3 -> addEquipmentView.blockEquipment();
                case 4 -> addEquipmentView.logMaintenance();
                case 0 -> running = false;
                default -> System.out.println("Nieprawidłowy wybór, spróbuj ponownie.");
            }
        }
    }
}
