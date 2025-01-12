package model;

import model.Backpack;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BackpackTest {

    @Test
    void testBackpackInitialization() {
        // Przygotowanie danych
        String name = "Hiking Backpack";
        String description = "Durable hiking backpack with 30L volume";
        double pricePerDay = 20.0;
        int quantity = 15;
        int volume = 30;

        // Tworzenie instancji Backpack
        Backpack backpack = new Backpack(name, description, pricePerDay, quantity, volume);

        // Weryfikacja właściwości
        assertEquals(name, backpack.getName(), "Nazwa plecaka powinna być poprawna.");
        assertEquals(description, backpack.getDescription(), "Opis plecaka powinien być poprawny.");
        assertEquals(pricePerDay, backpack.getPricePerDay(), "Cena za dzień powinna być poprawna.");
        assertEquals(quantity, backpack.getQuantity(), "Ilość plecaków powinna być poprawna.");
        assertEquals(volume, backpack.getVolume(), "Pojemność plecaka powinna być poprawna.");
        assertTrue(backpack.isAvailable(), "Plecak powinien być dostępny, jeśli ilość jest większa niż 0.");
    }

    @Test
    void testSetQuantityUpdatesAvailability() {
        // Przygotowanie danych
        Backpack backpack = new Backpack("Travel Backpack", "Compact travel backpack", 15.0, 10, 25);

        // Weryfikacja początkowej dostępności
        assertTrue(backpack.isAvailable(), "Plecak powinien być dostępny początkowo.");

        // Zmiana ilości na 0
        backpack.setQuantity(0);

        // Weryfikacja, że plecak jest niedostępny
        assertFalse(backpack.isAvailable(), "Plecak nie powinien być dostępny, jeśli ilość wynosi 0.");
    }

    @Test
    void testToString() {
        // Przygotowanie danych
        Backpack backpack = new Backpack("Camping Backpack", "Large camping backpack", 25.0, 5, 50);

        // Sprawdzenie formatu toString
        String expectedString = "Backpack{name='Camping Backpack', description='Large camping backpack', pricePerDay=25.0, volume=50}";
        assertEquals(expectedString, backpack.toString(), "Metoda toString() powinna zwracać poprawny format.");
    }
}
