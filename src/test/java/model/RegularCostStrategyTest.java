package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class RegularCostStrategyTest {

    private RegularCostStrategy strategy;

    @BeforeEach
    void setUp() {
        // Inicjalizacja strategii
        strategy = new RegularCostStrategy();
    }

    @Test
    void calculateCost() {
        // Test poprawnych danych
        double cost = strategy.calculateCost(5, 20.0); // 5 dni, 20 zł za dzień
        assertEquals(100.0, cost, 0.01, "Koszt powinien wynosić 5 * 20 = 100");

        cost = strategy.calculateCost(1, 10.0); // 1 dzień, 10 zł za dzień
        assertEquals(10.0, cost, 0.01, "Koszt powinien wynosić 1 * 10 = 10");

        // Test z dużymi wartościami
        cost = strategy.calculateCost(100, 50.0); // 100 dni, 50 zł za dzień
        assertEquals(5000.0, cost, 0.01, "Koszt powinien wynosić 100 * 50 = 5000");

        // Test z granicznymi wartościami
        cost = strategy.calculateCost(1, 0.0); // 1 dzień, 0 zł za dzień
        assertEquals(0.0, cost, 0.01, "Koszt powinien wynosić 0, ponieważ cena za dzień wynosi 0");

        // Test nieprawidłowych danych
        assertThrows(IllegalArgumentException.class, () -> strategy.calculateCost(0, 20.0),
                "Dni = 0 powinno rzucać wyjątek");
        assertThrows(IllegalArgumentException.class, () -> strategy.calculateCost(-5, 20.0),
                "Dni < 0 powinno rzucać wyjątek");
        assertThrows(IllegalArgumentException.class, () -> strategy.calculateCost(5, -10.0),
                "Cena za dzień < 0 powinna rzucać wyjątek");
    }
}