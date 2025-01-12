package controller;

import model.ModelFacade;
import model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserControllerTest {

    private UserController userController;
    private ModelFacade modelFacadeMock;

    @BeforeEach
    void setUp() {
        modelFacadeMock = mock(ModelFacade.class);
        userController = new UserController(modelFacadeMock);
    }

    @Test
    void testRegisterUserSuccess() {
        // Mockowanie
        when(modelFacadeMock.getUserById(1)).thenReturn(null);

        // Testowanie
        boolean result = userController.registerUser(1, "Jan Kowalski", "jan.kowalski@example.com", "customer", "password");

        // Weryfikacja
        assertTrue(result, "Rejestracja powinna zakończyć się sukcesem.");
        verify(modelFacadeMock).addUser(any(User.class));
    }

    @Test
    void testRegisterUserDuplicateId() {
        // Mockowanie
        User existingUser = new User(1, "Jan Kowalski", "jan.kowalski@example.com", "customer", "password");
        when(modelFacadeMock.getUserById(1)).thenReturn(existingUser);

        // Testowanie
        boolean result = userController.registerUser(1, "Anna Nowak", "anna.nowak@example.com", "employee", "password");

        // Weryfikacja
        assertFalse(result, "Rejestracja powinna zakończyć się niepowodzeniem z powodu zduplikowanego ID.");
        verify(modelFacadeMock, never()).addUser(any(User.class));
    }

    @Test
    void testRegisterUserInvalidRole() {
        // Mockowanie
        when(modelFacadeMock.getUserById(1)).thenReturn(null);

        // Testowanie
        boolean result = userController.registerUser(1, "Anna Nowak", "anna.nowak@example.com", "invalid_role", "password");

        // Weryfikacja
        assertFalse(result, "Rejestracja powinna zakończyć się niepowodzeniem z powodu nieprawidłowej roli.");
        verify(modelFacadeMock, never()).addUser(any(User.class));
    }

    @Test
    void testLoginUserSuccess() {
        // Mockowanie
        User user = new User(1, "Jan Kowalski", "jan.kowalski@example.com", "customer", "password");
        when(modelFacadeMock.getUserById(1)).thenReturn(user);

        // Testowanie
        User loggedInUser = userController.loginUser(1, "password");

        // Weryfikacja
        assertNotNull(loggedInUser, "Użytkownik powinien zostać zalogowany.");
        assertEquals(user, loggedInUser, "Zwrócony użytkownik powinien być poprawny.");
        verify(modelFacadeMock).setLoggedUser(user);
    }

    @Test
    void testLoginUserWrongPassword() {
        // Mockowanie
        User user = new User(1, "Jan Kowalski", "jan.kowalski@example.com", "customer", "password");
        when(modelFacadeMock.getUserById(1)).thenReturn(user);

        // Testowanie
        User loggedInUser = userController.loginUser(1, "wrong_password");

        // Weryfikacja
        assertNull(loggedInUser, "Logowanie powinno zakończyć się niepowodzeniem z powodu błędnego hasła.");
        verify(modelFacadeMock, never()).setLoggedUser(any());
    }

    @Test
    void testLogoutUserSuccess() {
        // Mockowanie
        User user = new User(1, "Jan Kowalski", "jan.kowalski@example.com", "customer", "password");
        when(modelFacadeMock.getLoggedUser()).thenReturn(user);

        // Testowanie
        boolean result = userController.logoutUser();

        // Weryfikacja
        assertTrue(result, "Wylogowanie powinno zakończyć się sukcesem.");
        verify(modelFacadeMock).setLoggedUser(null);
    }

    @Test
    void testLogoutUserNoLoggedUser() {
        // Mockowanie
        when(modelFacadeMock.getLoggedUser()).thenReturn(null);

        // Testowanie
        boolean result = userController.logoutUser();

        // Weryfikacja
        assertFalse(result, "Wylogowanie powinno zakończyć się niepowodzeniem, gdy nie ma zalogowanego użytkownika.");
        verify(modelFacadeMock, never()).setLoggedUser(null);
    }

    @Test
    void testGetAllUsers() {
        // Mockowanie
        List<User> users = new ArrayList<>();
        users.add(new User(1, "Jan Kowalski", "jan.kowalski@example.com", "customer", "password"));
        users.add(new User(2, "Anna Nowak", "anna.nowak@example.com", "employee", "password2"));
        when(modelFacadeMock.getUsers()).thenReturn(users);

        // Testowanie
        List<User> result = userController.getAllUsers();

        // Weryfikacja
        assertEquals(2, result.size(), "Powinna być zwrócona lista zawierająca dwóch użytkowników.");
        assertEquals(users, result, "Lista użytkowników powinna być zgodna z oczekiwaniami.");
    }

    @Test
    void testGenerateUserId() {
        // Mockowanie
        when(modelFacadeMock.generateUserId()).thenReturn(10);

        // Testowanie
        int generatedId = userController.generateUserId();

        // Weryfikacja
        assertEquals(10, generatedId, "Wygenerowany ID użytkownika powinien wynosić 10.");
    }
}
