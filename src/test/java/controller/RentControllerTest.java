package controller;

import model.Equipment;
import model.ModelFacade;
import model.Rental;
import model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RentControllerTest {

    private RentController rentController;
    private ModelFacade modelFacadeMock;

    @BeforeEach
    void setUp() {
        modelFacadeMock = mock(ModelFacade.class);
        rentController = new RentController(modelFacadeMock);
    }



    @Test
    void testRentEquipmentSuccess() {
        // Przygotowanie danych
        Equipment equipment = new Equipment("Bike", "Mountain bike", 50.0, 10);
        equipment.setId(1);

        // Mockowanie metod w modelFacadeMock
        when(modelFacadeMock.getEquipmentById(eq(1))).thenReturn(equipment);
        when(modelFacadeMock.rentEquipment(eq(1), any(Date.class), any(Date.class))).thenReturn(true);

        // Testowanie
        boolean result = rentController.rentEquipment(1, 1, new Date(), new Date(), false);

        // Weryfikacja
        assertTrue(result, "Wypożyczenie powinno zakończyć się sukcesem.");
        verify(modelFacadeMock).rentEquipment(eq(1), any(Date.class), any(Date.class));
    }


    @Test
    void testRentEquipmentUnavailable() {
        // Przygotowanie danych
        Equipment equipment = new Equipment("Bike", "Mountain bike", 50.0, 0);
        equipment.setId(1);
        equipment.setAvailable(false);
        when(modelFacadeMock.getEquipmentById(1)).thenReturn(equipment);

        // Testowanie
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                rentController.rentEquipment(1, 1, new Date(), new Date(), false));

        // Weryfikacja
        assertEquals("Sprzęt jest niedostępny.", exception.getMessage());
        verify(modelFacadeMock, never()).rentEquipment(anyInt(), any(Date.class), any(Date.class));
    }

    @Test
    void testExtendRentalSuccess() {
        // Przygotowanie danych
        int rentalId = 1;
        int additionalDays = 5;

        // Mockowanie - metoda zwraca true w przypadku sukcesu
        when(modelFacadeMock.checkAndExtendRental(eq(rentalId), eq(additionalDays))).thenReturn(true);

        // Testowanie
        boolean result = rentController.extendRental(rentalId, additionalDays);

        // Weryfikacja
        assertTrue(result, "Przedłużenie wypożyczenia powinno zakończyć się sukcesem.");
        verify(modelFacadeMock).checkAndExtendRental(eq(rentalId), eq(additionalDays));
    }

    @Test
    void testExtendRentalFailure() {
        // Przygotowanie danych
        doThrow(new IllegalArgumentException("Nie można przedłużyć wypożyczenia.")).when(modelFacadeMock).checkAndExtendRental(1, 5);

        // Testowanie
        boolean result = rentController.extendRental(1, 5);

        // Weryfikacja
        assertFalse(result, "Przedłużenie wypożyczenia powinno zakończyć się niepowodzeniem.");
        verify(modelFacadeMock).checkAndExtendRental(1, 5);
    }

    @Test
    void testIsEquipmentAvailableTrue() {
        // Przygotowanie danych
        Equipment equipment = new Equipment("Tent", "Camping tent", 30.0, 5);
        equipment.setId(2);
        when(modelFacadeMock.getEquipmentById(2)).thenReturn(equipment);

        // Testowanie
        boolean result = rentController.isEquipmentAvailable(2);

        // Weryfikacja
        assertTrue(result, "Sprzęt powinien być dostępny.");
    }

    @Test
    void testIsEquipmentAvailableFalse() {
        // Przygotowanie danych
        Equipment equipment = new Equipment("Tent", "Camping tent", 30.0, 0);
        equipment.setId(2);
        equipment.setAvailable(false);
        when(modelFacadeMock.getEquipmentById(2)).thenReturn(equipment);

        // Testowanie
        boolean result = rentController.isEquipmentAvailable(2);

        // Weryfikacja
        assertFalse(result, "Sprzęt nie powinien być dostępny.");
    }

    @Test
    void testGetUserRentalHistory() {
        // Przygotowanie danych
        List<Rental> rentals = new ArrayList<>();
        when(modelFacadeMock.getUserRentalHistory(1)).thenReturn(rentals);

        // Testowanie
        List<Rental> result = rentController.getUserRentalHistory(1);

        // Weryfikacja
        assertEquals(rentals, result, "Historia wypożyczeń powinna być zwrócona poprawnie.");
        verify(modelFacadeMock).getUserRentalHistory(1);
    }

    @Test
    void testCalculateRentalCost() {
        // Mockowanie metody
        when(modelFacadeMock.calculateRentalCost(anyInt(), any(Date.class), any(Date.class))).thenReturn(100.0);

        // Testowanie
        double cost = rentController.calculateRentalCost(1, new Date(), new Date());

        // Weryfikacja
        assertEquals(100.0, cost, 0.01, "Koszt wypożyczenia powinien wynosić 100.0.");
        verify(modelFacadeMock).calculateRentalCost(anyInt(), any(Date.class), any(Date.class));
    }

    @Test
    void testFindOverlappingRental() {
        // Przygotowanie danych
        Equipment equipment = new Equipment("Bike", "Mountain bike", 50.0, 10);
        equipment.setId(1);
        Rental rental = new Rental(1, 1, equipment, new Date(System.currentTimeMillis() - 86400000), new Date(System.currentTimeMillis() + 86400000), null);
        List<Rental> rentals = List.of(rental);
        User loggedUser = new User(1, "John", "john@example.com", "customer", "password");

        when(modelFacadeMock.getLoggedUser()).thenReturn(loggedUser);
        when(modelFacadeMock.getUserRentalHistory(1)).thenReturn(rentals);

        // Testowanie
        Rental overlappingRental = rentController.findOverlappingRental(1, new Date(), new Date());

        // Weryfikacja
        assertNotNull(overlappingRental, "Powinien zostać znaleziony nakładający się wynajem.");
        assertEquals(rental, overlappingRental, "Zwrócony wynajem powinien być zgodny z oczekiwaniami.");
    }
}
