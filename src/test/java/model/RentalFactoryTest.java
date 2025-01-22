package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class RentalFactoryTest {


    private RentalFactory factory;
    private Equipment equipment;
    private Date startDate;
    private Date endDate;


    @BeforeEach
    void setUp() {
        factory = new RegularRentalFactory();
        equipment = new Equipment("Bike", "Mountain Bike", 50.0, 10);
        startDate = new Date();
        endDate = new Date(startDate.getTime() + 86400000L); // Dzień później
    }


    @Test
    void testCreateRental() {
        // Przygotowanie danych wejściowych
        int id = 1;
        int userId = 2;

        // Wywołanie metody
        Rental rental = factory.createRental(id, userId, equipment, startDate, endDate);

        // Weryfikacja wyniku
        assertNotNull(rental, "Wypożyczenie nie powinno być null");
        assertEquals(id, rental.getId(), "Identyfikator wypożyczenia nie zgadza się");
        assertEquals(userId, rental.getUserId(), "Identyfikator użytkownika nie zgadza się");
        assertEquals(equipment, rental.getEquipment(), "Sprzęt nie zgadza się");
        assertEquals(startDate, rental.getStartDate(), "Data rozpoczęcia nie zgadza się");
        assertEquals(endDate, rental.getEndDate(), "Data zakończenia nie zgadza się");
        assertTrue(rental.getCostStrategy() instanceof RegularCostStrategy,
                "Strategia kosztowa powinna być typu RegularCostStrategy");
    }

    @ParameterizedTest
    @CsvSource({
            "1, 2, Bike, Mountain Bike, 50.0, 10",
            "3, 4, Ski, Alpine Ski, 70.0, 5"
    })
    void testCreateRentalWithDifferentInputs(int id, int userId, String name, String type, double price, int stock) {
        // Przygotowanie sprzętu na podstawie danych wejściowych
        Equipment paramEquipment = new Equipment(name, type, price, stock);

        // Wywołanie metody
        Rental rental = factory.createRental(id, userId, paramEquipment, startDate, endDate);

        // Weryfikacja wyniku
        assertNotNull(rental, "Wypożyczenie nie powinno być null");
        assertEquals(id, rental.getId(), "Identyfikator wypożyczenia nie zgadza się");
        assertEquals(userId, rental.getUserId(), "Identyfikator użytkownika nie zgadza się");
        assertEquals(paramEquipment, rental.getEquipment(), "Sprzęt nie zgadza się");
    }

    @Test
    void testCreateRentalWithNullEquipment() {
        // Przygotowanie danych wejściowych
        int id = 1;
        int userId = 2;

        // Wywołanie metody i sprawdzenie wyjątku
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> factory.createRental(id, userId, null, startDate, endDate),
                "Powinien zostać rzucony wyjątek IllegalArgumentException");

        // Weryfikacja wiadomości wyjątku (opcjonalne)
        assertEquals("Equipment cannot be null", exception.getMessage());
    }

    @Test
    void testCreateRentalWithInvalidDates() {
        // Przygotowanie danych wejściowych
        Date invalidEndDate = new Date(startDate.getTime() - 86400000L); // Dzień wcześniej

        // Wywołanie metody i sprawdzenie wyjątku
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> factory.createRental(1, 2, equipment, startDate, invalidEndDate),
                "Powinien zostać rzucony wyjątek IllegalArgumentException");

        // Weryfikacja wiadomości wyjątku (opcjonalne)
        assertEquals("End date must be after start date", exception.getMessage());
    }
}