package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class RegularRentalFactoryTest {

    private RegularRentalFactory factory;
    private Equipment equipment;

    @BeforeEach
    void setUp() {
        // Tworzenie instancji fabryki
        factory = new RegularRentalFactory();

        // Tworzenie przykładowego sprzętu
        equipment = new Equipment("Bike", "Mountain Bike", 50.0, 10);
    }

    @Test
    void createRental() {
        // Dane wejściowe do metody
        int rentalId = 1;
        int userId = 1001;
        Date startDate = new Date();
        Date endDate = new Date(startDate.getTime() + 86400000L); // Dzień później

        // Wywołanie metody
        Rental rental = factory.createRental(rentalId, userId, equipment, startDate, endDate);

        // Weryfikacja wyniku
        assertNotNull(rental, "Wypożyczenie nie powinno być null");
        assertEquals(rentalId, rental.getId(), "ID wypożyczenia powinno być ustawione poprawnie");
        assertEquals(userId, rental.getUserId(), "ID użytkownika powinno być ustawione poprawnie");
        assertEquals(equipment, rental.getEquipment(), "Sprzęt powinien być ustawiony poprawnie");
        assertEquals(startDate, rental.getStartDate(), "Data rozpoczęcia powinna być ustawiona poprawnie");
        assertEquals(endDate, rental.getEndDate(), "Data zakończenia powinna być ustawiona poprawnie");
        assertTrue(rental.getCostStrategy() instanceof RegularCostStrategy,
                "Strategia kosztowa powinna być typu RegularCostStrategy");
    }
}