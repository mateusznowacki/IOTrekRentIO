package model;

import model.EmployeeDiscountCostStrategy;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeDiscountCostStrategyTest {

    @Test
    void testCalculateCostWithValidInput() {
        // Przygotowanie danych
        EmployeeDiscountCostStrategy costStrategy = new EmployeeDiscountCostStrategy();
        int days = 5;
        double pricePerDay = 100.0; // Cena za dzień
        double expectedCost = days * pricePerDay * (1 - 0.4); // Uwzględnienie zniżki 40%

        // Wywołanie metody
        double actualCost = costStrategy.calculateCost(days, pricePerDay);

        // Weryfikacja
        assertEquals(expectedCost, actualCost, 0.01, "Koszt powinien być obliczony poprawnie z uwzględnieniem zniżki.");
    }

    @Test
    void testCalculateCostWithZeroDays() {
        // Przygotowanie danych
        EmployeeDiscountCostStrategy costStrategy = new EmployeeDiscountCostStrategy();

        // Weryfikacja wyjątku
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            costStrategy.calculateCost(0, 100.0);
        });

        assertEquals("Dni i cena za dzień muszą być większe niż 0", exception.getMessage(),
                "Wyjątek powinien być rzucony dla niepoprawnej liczby dni.");
    }

    @Test
    void testCalculateCostWithNegativePricePerDay() {
        // Przygotowanie danych
        EmployeeDiscountCostStrategy costStrategy = new EmployeeDiscountCostStrategy();

        // Weryfikacja wyjątku
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            costStrategy.calculateCost(5, -50.0);
        });

        assertEquals("Dni i cena za dzień muszą być większe niż 0", exception.getMessage(),
                "Wyjątek powinien być rzucony dla niepoprawnej ceny za dzień.");
    }

    @Test
    void testCalculateCostWithOneDay() {
        // Przygotowanie danych
        EmployeeDiscountCostStrategy costStrategy = new EmployeeDiscountCostStrategy();
        int days = 1;
        double pricePerDay = 50.0;
        double expectedCost = days * pricePerDay * (1 - 0.4); // Uwzględnienie zniżki 40%

        // Wywołanie metody
        double actualCost = costStrategy.calculateCost(days, pricePerDay);

        // Weryfikacja
        assertEquals(expectedCost, actualCost, 0.01, "Koszt dla jednego dnia powinien być obliczony poprawnie.");
    }
}
