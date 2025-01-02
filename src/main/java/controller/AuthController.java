package controller;

import model.done.User;
import java.util.List;

public class AuthController {
    private User loggedInUser;

    public User registerUser(User newUser) {
        loggedInUser = newUser;
        return loggedInUser;
    }

    public boolean isAdmin() {
        return loggedInUser != null && "ADMIN".equalsIgnoreCase(loggedInUser.getRole());
    }

    public User loginUser(List<User> registeredUsers, int userId) {
        for (User user : registeredUsers) {
            if (user.getId() == userId) {
                loggedInUser = user;
                return loggedInUser;
            }
        }
        System.out.println("Nie znaleziono użytkownika o podanym ID.");
        return null;
    }

    public boolean isLoggedIn() {
        if (loggedInUser == null) {
            System.out.println("Nie jesteś zalogowany.");
            return false;
        }
        return true;
    }

    public User getLoggedInUser() {
        return loggedInUser;
    }
}
