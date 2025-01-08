package model;

import java.util.List;

public class UserService {

    private LocalStorage storage;

    public UserService(LocalStorage localStorage) {
        this.storage = localStorage;
    }

    // Logowanie użytkownika
    public User loginUser(String email) {
        for (User user : storage.getUsers()) {
            if (user.getEmail().equalsIgnoreCase(email)) {
                return user;
            }
        }
        return null;
    }

    public User getUserById(int userId) {
        return storage.getUsers().stream()
                .filter(user -> user.getId() == userId)
                .findFirst()
                .orElse(null);
    }

    public List<User> getUsers() {
        return storage.getUsers();
    }

    public User getLoggedUser() {
        return storage.getLoggedUser();
    }

    public void setLoggedUser(User user) {
        storage.setLoggedUser(user);
    }


    public void addUser(User newUser) {
        storage.addUser(newUser);
    }

    public int generateUserId() {
        return storage.generateUserId();
    }


    public boolean checkCredentials() {
        return storage.getLoggedUser().isEmployee();
    }

    public boolean removeUser(int userId) {
        return storage.removeUser(userId);
    }

    public boolean editUserRole(int userId, String newRole) {
        return storage.editUserRole(userId, newRole);
    }



}
