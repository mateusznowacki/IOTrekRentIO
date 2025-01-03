package controller;

import model.done.User;

import java.util.List;
import java.util.Scanner;

public class AuthController {
    private User loggedInUser;

    public void registerUser(List<User> users) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Podaj nazwę użytkownika:");
        String name = scanner.nextLine();

        System.out.println("Podaj email:");
        String email = scanner.nextLine();

        System.out.println("Podaj rolę (ADMIN lub USER):");
        String role = scanner.nextLine();

        if (email.isEmpty() || name.isEmpty() || role.isEmpty()) {
            System.out.println("Nieprawidłowe dane. Rejestracja nieudana.");
            return;
        }

        for (User user : users) {
            if (user.getEmail().equalsIgnoreCase(email)) {
                System.out.println("Użytkownik z tym adresem email już istnieje.");
                return;
            }
        }

        User newUser = new User(users.size() + 1, name, email, role);
        users.add(newUser);
        System.out.println("Rejestracja zakończona sukcesem.");
    }

    public void loginUser(List<User> users) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Podaj ID użytkownika:");
        int userId = scanner.nextInt();

        for (User user : users) {
            if (user.getId() == userId) {
                loggedInUser = user;
                System.out.println("Zalogowano jako: " + user.getName());
                return;
            }
        }

        System.out.println("Nie znaleziono użytkownika o podanym ID.");
    }

    public boolean isAdmin() {
        return loggedInUser != null && "ADMIN".equalsIgnoreCase(loggedInUser.getRole());
    }

    public boolean isLoggedIn() {
        return loggedInUser != null;
    }

    public User getLoggedInUser() {
        if (loggedInUser == null) {
            throw new IllegalStateException("Nie jesteś zalogowany.");
        }
        return loggedInUser;
    }
}
