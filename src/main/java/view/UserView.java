package view;


import controller.ControllerFacade;
import model.User;

import java.util.List;
import java.util.Scanner;

public class UserView {
    private final ControllerFacade controllerFacade; // Komunikacja z kontrolerem
    private final Scanner scanner = new Scanner(System.in);

    // Konstruktor z wstrzykiwaniem zależności
    public UserView(ControllerFacade controllerFacade) {
        this.controllerFacade = controllerFacade;
    }

    // Rejestracja użytkownika
    public void registerUser() {
        System.out.println("\n=== REJESTRACJA UŻYTKOWNIKA ===");

        try {
            // Automatyczne generowanie ID
            int userId = controllerFacade.generateUserId();

            System.out.print("Podaj nazwę użytkownika: ");
            String userName = scanner.nextLine();

            System.out.print("Podaj adres e-mail: ");
            String email = scanner.nextLine();

            if (!isValidEmail(email)) {
                System.out.println("Nieprawidłowy adres e-mail.");
                return;
            }

            System.out.print("Podaj rolę użytkownika (EMPLOYEE / CUSTOMER): ");
            String role = scanner.nextLine().toUpperCase();
            if (!role.equals("EMPLOYEE") && !role.equals("CUSTOMER")) {
                System.out.println("Nieprawidłowa rola. Domyślna rola ustawiona na CUSTOMER.");
                role = "CUSTOMER";
            }

            System.out.print("Podaj hasło: ");
            String password = scanner.nextLine();

            boolean success = controllerFacade.registerUser(userId, userName, email, role, password);
            if (success) {
                System.out.println("Rejestracja zakończona pomyślnie! \nNadane ID: " + userId +
                        " nazwa użytkownika: " + userName + " email: " + email + " rola: " + role);
            } else {
                System.out.println("Rejestracja nie powiodła się.");
            }
        } catch (Exception e) {
            System.out.println("Błąd podczas rejestracji: " + e.getMessage());
            scanner.nextLine(); // Reset skanera
        }
    }

    // Logowanie użytkownika
    public void loginUser() {
        System.out.println("\n=== LOGOWANIE UŻYTKOWNIKA ===");

        try {
            System.out.print("Podaj ID użytkownika: ");
            int userId = scanner.nextInt();
            scanner.nextLine(); // Konsumowanie znaku nowej linii

            System.out.print("Podaj hasło: ");
            String password = scanner.nextLine();

            User user = controllerFacade.loginUser(userId, password);
            if (user != null) {
                System.out.println("Zalogowano pomyślnie jako: " + user.getName() + " (Rola: " + user.getRole() + ")");
            } else {
                System.out.println("Nieprawidłowe ID lub hasło.");
            }
        } catch (Exception e) {
            System.out.println("Błąd podczas logowania: " + e.getMessage());
            scanner.nextLine(); // Reset skanera
        }
    }

    // Wylogowanie użytkownika
    public void logoutUser() {
        if (controllerFacade.logoutUser()) {
            System.out.println("Wylogowano pomyślnie.");
        } else {
            System.out.println("Nie było zalogowanego użytkownika.");
        }
    }

    // Wyświetlenie listy użytkowników
    public void displayUserList() {
        List<User> users = controllerFacade.getAllUsers();
        if (users.isEmpty()) {
            System.out.println("Brak użytkowników w systemie.");
            return;
        }

        System.out.println("\n=== LISTA UŻYTKOWNIKÓW ===");
        for (User user : users) {
            System.out.println("ID: " + user.getId() + ", Nazwa: " + user.getName() + ", Rola: " + user.getRole() + ", Email: " + user.getEmail());
        }
    }

    // Walidacja adresu e-mail
    private boolean isValidEmail(String email) {
        return email.contains("@") && email.contains(".");
    }
}
