package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TentTest {

    @Test
    void testConstructorAndGetters() {
        // Przygotowanie obiektu Tent
        Tent tent = new Tent("Family Tent", "Large tent for 4 people", 50.0, 10, 4);

        // Sprawdzanie, czy konstruktor poprawnie ustawił wartości
        assertEquals("Family Tent", tent.getName());
        assertEquals("Large tent for 4 people", tent.getDescription());
        assertEquals(50.0, tent.getPricePerDay());
        assertEquals(10, tent.getQuantity());
        assertEquals(4, tent.getCapacity());
    }

    @Test
    void testSetCapacity() {
        // Przygotowanie obiektu Tent
        Tent tent = new Tent("Family Tent", "Large tent for 4 people", 50.0, 10, 4);

        // Zmiana pojemności
        tent.setQuantity(15);

        // Sprawdzanie zmiany ilości
        assertEquals(15, tent.getQuantity());
    }

    @Test
    void testToString() {
        // Przygotowanie obiektu Tent
        Tent tent = new Tent("Camping Tent", "Compact tent for 2 people", 30.0, 5, 2);

        // Sprawdzanie metody toString
        String expected = "Tent{name='Camping Tent', description='Compact tent for 2 people', pricePerDay=30.0, capacity=2}";
        assertEquals(expected, tent.toString());
    }
}
