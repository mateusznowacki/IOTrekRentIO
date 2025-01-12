package model;

import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class RentalFactoryTest {

    @Test
    void testCreateRental() {
        // Przygotowanie danych wejściowych
        int id = 1;
        int userId = 2;
        Equipment equipment = new Equipment("Bike", "Mountain Bike", 50.0, 10);
        Date startDate = new Date();
        Date endDate = new Date(startDate.getTime() + 86400000L); // Dzień później

        // Tworzenie instancji fabryki
        RentalFactory factory = new RegularRentalFactory();

        // Wywołanie metody
        Rental rental = factory.createRental(id, userId, equipment, startDate, endDate);

        // Weryfikacja wyniku
        assertNotNull(rental, "Wypożyczenie nie powinno być null");
        assertEquals(id, rental.getId(), "Identyfikator wypożyczenia nie zgadza się");
        assertEquals(userId, rental.getUserId(), "Identyfikator użytkownika nie zgadza się");
        assertEquals(equipment, rental.getEquipment(), "Sprzęt nie zgadza się");
        assertEquals(startDate, rental.getStartDate(), "Data rozpoczęcia nie zgadza się");
        assertEquals(endDate, rental.getEndDate(), "Data zakończenia nie zgadza się");
        assertTrue(rental.getCostStrategy() instanceof RegularCostStrategy, "Strategia kosztowa powinna być typu RegularCostStrategy");
    }
}