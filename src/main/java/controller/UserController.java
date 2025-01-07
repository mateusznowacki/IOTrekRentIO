package controller;

import model.ModelFacade;
import model.User;

import java.util.List;

public class UserController {
    private ModelFacade modelFacade;

    public UserController(ModelFacade modelFacade) {
        this.modelFacade = modelFacade;
    }

    // Rejestracja użytkownika
    public boolean registerUser(int id, String name, String email, String role, String password) {
        // Sprawdzenie, czy użytkownik z tym ID już istnieje
        if (modelFacade.getUserById(id) != null) {
            System.out.println("Użytkownik o podanym ID już istnieje.");
            return false;
        }

        // Walidacja roli
        if (!role.equalsIgnoreCase("employee") && !role.equalsIgnoreCase("customer")) {
            System.out.println("Nieprawidłowa rola. Dozwolone: 'employee', 'customer'.");
            return false;
        }

        // Walidacja hasła (przykład: minimum 1 znaków)
        if (password.length() < 1) {
            System.out.println("Hasło musi mieć co najmniej 1 znak.");
            return false;
        }

        // Tworzenie nowego użytkownika
        User newUser = new User(id, name, email, role.toLowerCase(), password);
        modelFacade.addUser(newUser);
        return true;
    }

    // Logowanie użytkownika
    public User loginUser(int id, String password) {
        User user = modelFacade.getUserById(id);

        if (user == null) {
            System.out.println("Nie znaleziono użytkownika o podanym ID.");
            return null;
        }

        if (!user.getPassword().equals(password)) {
            System.out.println("Nieprawidłowe hasło.");
            return null;
        }

        modelFacade.setLoggedUser(user);

        return user;
    }

    // Wylogowanie użytkownika
    public boolean logoutUser() {
        if (modelFacade.getLoggedUser() != null) {
            modelFacade.setLoggedUser(null);
            System.out.println("Wylogowano pomyślnie.");
            return true;
        }
        System.out.println("Brak zalogowanego użytkownika.");
        return false;
    }

    // Pobranie wszystkich użytkowników
    public List<User> getAllUsers() {
        return modelFacade.getUsers();
    }

    public int generateUserId() {
        return modelFacade.generateUserId();
    }
}
