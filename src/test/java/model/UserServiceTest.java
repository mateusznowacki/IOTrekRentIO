package model;

import mockit.Expectations;
import mockit.Mocked;
import mockit.Verifications;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {

    @Mocked
    private LocalStorage localStorage;

    @Test
    void testLoginUser() {
        // Przygotowanie danych testowych
        User user1 = new User(1, "John Doe", "john.doe@example.com", "EMPLOYEE", "password123");
        User user2 = new User(2, "Jane Doe", "jane.doe@example.com", "CUSTOMER", "password456");

        // Mockowanie zachowania LocalStorage
        new Expectations() {{
            localStorage.getUsers();
            result = Arrays.asList(user1, user2);
        }};

        UserService userService = new UserService(localStorage);

        // Test logowania istniejącego użytkownika
        User loggedUser = userService.loginUser("john.doe@example.com");
        assertNotNull(loggedUser);
        assertEquals("John Doe", loggedUser.getName());

        // Test logowania nieistniejącego użytkownika
        assertNull(userService.loginUser("nonexistent@example.com"));

        // Weryfikacja wywołania metody getUsers
        new Verifications() {{
            localStorage.getUsers();
            times = 2; // Metoda powinna być wywołana dwa razy
        }};
    }

    @Test
    void testGetUserById() {
        User user = new User(1, "John Doe", "john.doe@example.com", "EMPLOYEE", "password123");

        // Mockowanie zachowania LocalStorage
        new Expectations() {{
            localStorage.getUsers();
            result = Arrays.asList(user);
        }};

        UserService userService = new UserService(localStorage);

        // Test wyszukiwania użytkownika po ID
        User foundUser = userService.getUserById(1);
        assertNotNull(foundUser);
        assertEquals("John Doe", foundUser.getName());

        // Test dla nieistniejącego ID
        assertNull(userService.getUserById(99));
    }

    @Test
    void testAddUser() {
        User newUser = new User(3, "New User", "new.user@example.com", "CUSTOMER", "password789");

        // Mockowanie metody addUser
        new Expectations() {{
            localStorage.addUser(newUser);
        }};

        UserService userService = new UserService(localStorage);
        userService.addUser(newUser);

        // Weryfikacja wywołania metody addUser
        new Verifications() {{
            localStorage.addUser(newUser);
            times = 1;
        }};
    }

    @Test
    void testCheckCredentials() {
        User loggedUser = new User(1, "Employee User", "employee@example.com", "EMPLOYEE", "password123");

        // Mockowanie zachowania LocalStorage
        new Expectations() {{
            localStorage.setLoggedUser(loggedUser); // Oczekiwanie na wywołanie
            localStorage.getLoggedUser();
            result = loggedUser; // Zwrócenie zalogowanego użytkownika
        }};

        UserService userService = new UserService(localStorage);

        // Wywołanie setLoggedUser - musi być obecne
        userService.setLoggedUser(loggedUser);

        // Test sprawdzania uprawnień
        assertTrue(userService.checkCredentials());

        // Weryfikacja
        new Verifications() {{
            localStorage.setLoggedUser(loggedUser);
            times = 1; // Sprawdzanie, czy metoda została wywołana dokładnie raz
        }};
    }


    @Test
    void testRemoveUser() {
        // Mockowanie usuwania użytkownika
        new Expectations() {{
            localStorage.removeUser(1);
            result = true;
        }};

        UserService userService = new UserService(localStorage);
        assertTrue(userService.removeUser(1));

        // Weryfikacja wywołania metody removeUser
        new Verifications() {{
            localStorage.removeUser(1);
            times = 1;
        }};
    }
}
