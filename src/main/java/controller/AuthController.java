package controller;

import model.ModelFacade;
import model.done.LocalStorage;
import model.done.User;

import java.util.List;

//todo poprawić localstorage na modelfacade ewentualnie

public class AuthController {
    private ModelFacade modelFacade;
    private LocalStorage localStorage;

    public AuthController(ModelFacade modelFacade) {
        this.modelFacade = modelFacade;
        localStorage = LocalStorage.getInstance();
    }

    public boolean registerUser(int id, String name, String email, String role, String password) {
        if (localStorage.getUserById(id) != null) {
            return false; // Użytkownik o podanym ID już istnieje
        }
        User newUser = new User(id, name, email, role, password);
        localStorage.addUser(newUser);
        return true;
    }

    public User loginUser(int id, String password) {
        User user = localStorage.getUserById(id);
        if (user != null && user.getPassword().equals(password)) {
            localStorage.setLoggedUser(user);
            return user;
        }
        return null; // Nieprawidłowe dane logowania
    }

    public boolean logoutUser() {
        if (localStorage.getLoggedUser() != null) {
            localStorage.setLoggedUser(null);
            return true;
        }
        return false;
    }

    public List<User> getAllUsers() {
        return localStorage.getUsers();
    }
}
