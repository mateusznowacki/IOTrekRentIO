package controller;

import model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ControllerFacadeTest {

    private ControllerFacade controllerFacade;
    private ModelFacade modelFacadeMock;
    private UserController userControllerMock;
    private RentController rentControllerMock;

    @BeforeEach
    void setUp() {
        modelFacadeMock = mock(ModelFacade.class);
        userControllerMock = mock(UserController.class);
        rentControllerMock = mock(RentController.class);
        controllerFacade = new ControllerFacade(modelFacadeMock, userControllerMock, rentControllerMock);
    }

    @Test
    void testRegisterUser() {
        when(userControllerMock.registerUser(1, "John", "john@example.com", "customer", "password"))
                .thenReturn(true);

        boolean result = controllerFacade.registerUser(1, "John", "john@example.com", "customer", "password");

        assertTrue(result, "Rejestracja użytkownika powinna się powieść.");
        verify(userControllerMock).registerUser(1, "John", "john@example.com", "customer", "password");
    }

    @Test
    void testLoginUser() {
        User mockUser = new User(1, "John", "john@example.com", "customer", "password");
        when(userControllerMock.loginUser(1, "password")).thenReturn(mockUser);

        User result = controllerFacade.loginUser(1, "password");

        assertEquals(mockUser, result, "Powinien zostać zwrócony zalogowany użytkownik.");
        verify(userControllerMock).loginUser(1, "password");
    }

    @Test
    void testLogoutUser() {
        when(userControllerMock.logoutUser()).thenReturn(true);

        boolean result = controllerFacade.logoutUser();

        assertTrue(result, "Wylogowanie użytkownika powinno zakończyć się sukcesem.");
        verify(userControllerMock).logoutUser();
    }

    @Test
    void testGetAvailableEquipment() {
        List<Equipment> mockEquipmentList = List.of(
                new Equipment("Bike", "Mountain bike", 50.0, 10)
        );
        when(modelFacadeMock.getAvailableEquipment()).thenReturn(mockEquipmentList);

        List<Equipment> result = controllerFacade.getAvailableEquipment();

        assertEquals(mockEquipmentList, result, "Lista dostępnego sprzętu powinna być poprawna.");
        verify(modelFacadeMock).getAvailableEquipment();
    }

    @Test
    void testRentEquipment() {
        // Przygotowanie danych
        Date startDate = new Date();
        Date endDate = new Date();

        when(rentControllerMock.rentEquipment(eq(1), eq(startDate), eq(endDate))).thenReturn(true);

        // Testowanie
        boolean result = controllerFacade.rentEquipment(1, startDate, endDate);

        // Weryfikacja
        assertTrue(result, "Wypożyczenie sprzętu powinno zakończyć się sukcesem.");
        verify(rentControllerMock).rentEquipment(eq(1), eq(startDate), eq(endDate));
    }


    @Test
    void testAddBike() {
        when(modelFacadeMock.addBike("Bike", "Mountain bike", 50.0, 21, 10)).thenReturn(true);

        boolean result = controllerFacade.addBike("Bike", "Mountain bike", 50.0, 21, 10);

        assertTrue(result, "Dodanie roweru powinno zakończyć się sukcesem.");
        verify(modelFacadeMock).addBike("Bike", "Mountain bike", 50.0, 21, 10);
    }

    @Test
    void testHandleBlockEquipment() {
        when(modelFacadeMock.blockEquipment(1)).thenReturn(true);

        boolean result = controllerFacade.handleBlockEquipment(1);

        assertTrue(result, "Zablokowanie sprzętu powinno zakończyć się sukcesem.");
        verify(modelFacadeMock).blockEquipment(1);
    }

    @Test
    void testHandleRepairEquipment() {
        when(modelFacadeMock.repairEquipment(1, "Naprawiono")).thenReturn(true);

        boolean result = controllerFacade.handleRepairEquipment(1, "Naprawiono");

        assertTrue(result, "Naprawa sprzętu powinna zakończyć się sukcesem.");
        verify(modelFacadeMock).repairEquipment(1, "Naprawiono");
    }

    @Test
    void testGetRentalHistory() {
        List<Rental> mockRentals = List.of(mock(Rental.class));
        when(rentControllerMock.getUserRentalHistory(1)).thenReturn(mockRentals);

        List<Rental> result = controllerFacade.getRentalHistory(1);

        assertEquals(mockRentals, result, "Historia wypożyczeń powinna być poprawna.");
        verify(rentControllerMock).getUserRentalHistory(1);
    }

    @Test
    void testGenerateUserId() {
        when(userControllerMock.generateUserId()).thenReturn(5);

        int result = controllerFacade.generateUserId();

        assertEquals(5, result, "ID nowego użytkownika powinno wynosić 5.");
        verify(userControllerMock).generateUserId();
    }

    @Test
    void testAddSportBike() {
        when(modelFacadeMock.addSportBike("Sport Bike", "High-end bike", 100.0, 18, 5)).thenReturn(true);

        boolean result = controllerFacade.addSportBike("Sport Bike", "High-end bike", 100.0, 18, 5);

        assertTrue(result, "Dodanie sportowego roweru powinno zakończyć się sukcesem.");
        verify(modelFacadeMock).addSportBike("Sport Bike", "High-end bike", 100.0, 18, 5);
    }
}
