package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class RentalTest {

    private Rental rental;
    private Equipment equipment;
    private RentalCostStrategy costStrategy;

    @BeforeEach
    void setUp() {
        // Tworzymy przykładowy sprzęt
        equipment = new Equipment("Bike", "Mountain Bike", 50.0, 10);

        // Tworzymy przykładową strategię kosztową
        costStrategy = new RegularCostStrategy();

        // Ustawiamy przykładowe daty
        Calendar calendar = Calendar.getInstance();
        Date startDate = calendar.getTime(); // dzisiaj
        calendar.add(Calendar.DAY_OF_MONTH, 5);
        Date endDate = calendar.getTime(); // za 5 dni

        // Tworzymy wypożyczenie
        rental = new Rental(1, 1, equipment, startDate, endDate, costStrategy);
    }

    @Test
    @Order(1)
    void extendRental() {
        int additionalDays = 3;

        // Przedłużamy wypożyczenie
        rental.extendRental(additionalDays);

        // Sprawdzamy nową datę zakończenia
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(rental.getEndDate());
        calendar.add(Calendar.DAY_OF_MONTH, -additionalDays); // Cofamy 3 dni
        Date originalEndDate = calendar.getTime();

        assertNotEquals(originalEndDate, rental.getEndDate(), "Data zakończenia powinna się zmienić");
    }

    @Test
    @Order(2)
    void calculateCost() {
        // Obliczamy koszt
        double cost = rental.calculateCost();

        // Sprawdzamy poprawność obliczeń
        int expectedDays = 5; // Zakładamy 5 dni
        double expectedCost = expectedDays * equipment.getPricePerDay();
        assertEquals(expectedCost, cost, 0.01, "Koszt powinien wynosić 5 * cena za dzień");
    }

    @Test
    @Order(3)
    void getIdCounter() {
        // Ustawiamy licznik ID
        Rental.setIdCounter(100);
        assertEquals(100, Rental.getIdCounter(), "Licznik ID powinien wynosić 100");
    }

    @Test
    @Order(4)
    void setIdCounter() {
        Rental.setIdCounter(200);
        assertEquals(200, Rental.getIdCounter(), "Licznik ID powinien wynosić 200 po zmianie");
    }


    @Test
    @Order(5)
    void getId() {
        assertEquals(1, rental.getId(), "ID wypożyczenia powinno wynosić 1");
    }

    @Test
    @Order(6)
    void setId() {
        rental.setId(2);
        assertEquals(2, rental.getId(), "ID wypożyczenia powinno wynosić 2 po zmianie");
    }

    @Test
    @Order(7)
    void getUserId() {
        assertEquals(1, rental.getUserId(), "ID użytkownika powinno wynosić 1");
    }

    @Test
    @Order(8)
    void setUserId() {
        rental.setUserId(2);
        assertEquals(2, rental.getUserId(), "ID użytkownika powinno wynosić 2 po zmianie");
    }

    @Test
    @Order(9)
    void getEquipment() {
        assertEquals(equipment, rental.getEquipment(), "Sprzęt powinien być poprawnie ustawiony");
    }

    @Test
    @Order(10)
    void setEquipment() {
        Equipment newEquipment = new Equipment("Tent", "Camping Tent", 30.0, 5);
        rental.setEquipment(newEquipment);
        assertEquals(newEquipment, rental.getEquipment(), "Sprzęt powinien być zmieniony");
    }

    @Test
    @Order(11)
    void getStartDate() {
        assertNotNull(rental.getStartDate(), "Data rozpoczęcia nie powinna być null");
    }

    @Test
    @Order(12)
    void setStartDate() {
        Date newStartDate = new Date();
        rental.setStartDate(newStartDate);
        assertEquals(newStartDate, rental.getStartDate(), "Data rozpoczęcia powinna zostać zmieniona");
    }

    @Test
    @Order(13)
    void getEndDate() {
        assertNotNull(rental.getEndDate(), "Data zakończenia nie powinna być null");
    }

    @Test
    @Order(14)
    void setEndDate() {
        Date newEndDate = new Date();
        rental.setEndDate(newEndDate);
        assertEquals(newEndDate, rental.getEndDate(), "Data zakończenia powinna zostać zmieniona");
    }

    @Test
    @Order(15)
    void getCostStrategy() {
        assertEquals(costStrategy, rental.getCostStrategy(), "Strategia kosztowa powinna być poprawna");
    }

    @Test
    @Order(16)
    void setCostStrategy() {
        RentalCostStrategy newStrategy = new EmployeeDiscountCostStrategy();
        rental.setCostStrategy(newStrategy);
        assertEquals(newStrategy, rental.getCostStrategy(), "Strategia kosztowa powinna zostać zmieniona");
    }

    @Test
    @Order(17)
    void getCost() {
        rental.calculateCost();
        assertTrue(rental.getCost() > 0, "Koszt powinien być większy niż 0 po obliczeniu");
    }
}
