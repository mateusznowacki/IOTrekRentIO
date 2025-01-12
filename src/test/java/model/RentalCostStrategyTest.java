package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RentalCostStrategyTest {


    @Test
    void testRegularCostStrategy() {
        RentalCostStrategy strategy = new RegularCostStrategy();

        // Test poprawnych danych
        double cost = strategy.calculateCost(5, 20.0); // 5 dni, 20 zł za dzień
        assertEquals(100.0, cost, 0.01, "Koszt powinien wynosić 5 * 20 = 100");

        // Test granicznych przypadków
        cost = strategy.calculateCost(1, 10.0); // 1 dzień, 10 zł za dzień
        assertEquals(10.0, cost, 0.01, "Koszt powinien wynosić 1 * 10 = 10");

        // Test nieprawidłowych danych
        assertThrows(IllegalArgumentException.class, () -> strategy.calculateCost(0, 20.0),
                "Dni = 0 powinno rzucać wyjątek");
        assertThrows(IllegalArgumentException.class, () -> strategy.calculateCost(5, -10.0),
                "Cena za dzień < 0 powinna rzucać wyjątek");
    }

    @Test
    void testEmployeeDiscountCostStrategy() {
        RentalCostStrategy strategy = new EmployeeDiscountCostStrategy();

        // Test poprawnych danych
        double cost = strategy.calculateCost(5, 20.0); // 5 dni, 20 zł za dzień, zniżka 40%
        assertEquals(60.0, cost, 0.01, "Koszt powinien wynosić 5 * 20 z 40% zniżką = 60");

        cost = strategy.calculateCost(3, 15.0); // 3 dni, 15 zł za dzień, zniżka 40%
        assertEquals(27.0, cost, 0.01, "Koszt powinien wynosić 3 * 15 z 40% zniżką = 27");

        // Test granicznych przypadków
        cost = strategy.calculateCost(1, 10.0); // 1 dzień, 10 zł za dzień, zniżka 40%
        assertEquals(6.0, cost, 0.01, "Koszt powinien wynosić 1 * 10 z 40% zniżką = 6");

        // Test nieprawidłowych danych
        assertThrows(IllegalArgumentException.class, () -> strategy.calculateCost(0, 20.0),
                "Dni = 0 powinno rzucać wyjątek");
        assertThrows(IllegalArgumentException.class, () -> strategy.calculateCost(5, -10.0),
                "Cena za dzień < 0 powinna rzucać wyjątek");
    }
}