package model;

import model.EmployeeDiscountCostStrategy;
import model.EmployeeRentalFactory;
import model.Equipment;
import model.Rental;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeRentalFactoryTest {

    @Test
    void testCreateRental() {

        // Przygotowanie danych
        int rentalId = 1;
        int userId = 101;
        Equipment equipment = new Equipment("Bike", "Mountain bike", 50.0, 10);
        Date startDate = new Date();
        Date endDate = new Date(startDate.getTime() + (1000 * 60 * 60 * 24 * 3)); // 3 dni później

        // Tworzenie instancji EmployeeRentalFactory
        EmployeeRentalFactory factory = new EmployeeRentalFactory();

        // Wywołanie metody createRental
        Rental rental = factory.createRental(rentalId, userId, equipment, startDate, endDate);

        // Weryfikacja
        assertNotNull(rental, "Wynajem nie powinien być null.");
        assertEquals(rentalId, rental.getId(), "ID wynajmu powinno być poprawne.");
        assertEquals(userId, rental.getUserId(), "ID użytkownika powinno być poprawne.");
        assertEquals(equipment, rental.getEquipment(), "Sprzęt powinien być przypisany poprawnie.");
        assertEquals(startDate, rental.getStartDate(), "Data rozpoczęcia powinna być poprawna.");
        assertEquals(endDate, rental.getEndDate(), "Data zakończenia powinna być poprawna.");
        assertTrue(rental.getCostStrategy() instanceof EmployeeDiscountCostStrategy,
                "Strategia kosztów powinna być instancją EmployeeDiscountCostStrategy.");
    }
}
