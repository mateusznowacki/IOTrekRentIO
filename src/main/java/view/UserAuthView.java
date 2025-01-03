package view;

import controller.AuthController;
import model.done.User;

import java.util.List;
import java.util.Scanner;

public class UserAuthView {
    private User loggedInUser;
    private Scanner scanner = new Scanner(System.in);

    // Metoda do rejestracji użytkownika
    public User registerUser() {
        System.out.println("\n=== REJESTRACJA UŻYTKOWNIKA ===");

        System.out.print("Podaj ID użytkownika: ");
        int userId = scanner.nextInt();
        scanner.nextLine(); // Konsumowanie znaku nowej linii

        System.out.print("Podaj nazwę użytkownika: ");
        String userName = scanner.nextLine();

        System.out.print("Podaj adres e-mail: ");
        String email = scanner.nextLine();

        System.out.print("Podaj rolę użytkownika (ADMIN / CUSTOMER): ");
        String role = scanner.nextLine().toUpperCase();

        if (!role.equals("ADMIN") && !role.equals("CUSTOMER")) {
            System.out.println("Nieprawidłowa rola. Domyślna rola ustawiona na CUSTOMER.");
            role = "CUSTOMER";
        }

        User newUser = new User(userId, userName, email, role);
        System.out.println("Rejestracja zakończona pomyślnie! Możesz teraz się zalogować.");
        return newUser;
    }

    // Metoda do logowania użytkownika
    public User loginUser() {
        if (registeredUsers.isEmpty()) {
            System.out.println("Brak zarejestrowanych użytkowników. Najpierw się zarejestruj.");
            return null;
        }

        System.out.println("\n=== LOGOWANIE UŻYTKOWNIKA ===");
        System.out.print("Podaj ID użytkownika: ");
        int userId = scanner.nextInt();
        scanner.nextLine(); // Konsumowanie znaku nowej linii

        for (User user : registeredUsers) {
            if (user.getId() == userId) {
                loggedInUser = user;
                System.out.println("Zalogowano pomyślnie jako: " + loggedInUser.getName() + " (Rola: " + loggedInUser.getRole() + ")");
                return loggedInUser;
            }
        }

        System.out.println("Nie znaleziono użytkownika o podanym ID. Spróbuj ponownie lub zarejestruj się.");
        return null;
    }

    // Sprawdzanie, czy użytkownik jest zalogowany
    public boolean isLoggedIn() {
        if (loggedInUser == null) {
            System.out.println("Nie jesteś zalogowany.");
            return false;
        }
        return true;
    }

    // Wylogowanie użytkownika
    public void logoutUser() {
        if (loggedInUser != null) {
            System.out.println("Wylogowano użytkownika: " + loggedInUser.getName());
            loggedInUser = null;
        } else {
            System.out.println("Brak zalogowanego użytkownika.");
        }
    }

    // Ustawienie zalogowanego użytkownika
    public void setLoggedInUser(User user) {
        this.loggedInUser = user;
    }

    // Pobranie zalogowanego użytkownika
    public User getLoggedInUser() {
        return loggedInUser;
    }

    public void blockUser(AuthController authController, List<User> users) {
    }

    public void editUserRole(AuthController authController, List<User> users) {
    }

    public void displayUserList(List<User> users) {
    }
}
