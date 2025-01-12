package model;

import model.Bike;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BikeTest {

    @Test
    void testBikeInitialization() {
        // Przygotowanie danych
        String name = "Mountain Bike";
        String description = "High-quality mountain bike";
        double pricePerDay = 50.0;
        int quantity = 10;
        int gearCount = 21;

        // Tworzenie instancji Bike
        Bike bike = new Bike(name, description, pricePerDay, quantity, gearCount);

        // Weryfikacja właściwości
        assertEquals(name, bike.getName(), "Nazwa roweru powinna być poprawna.");
        assertEquals(description, bike.getDescription(), "Opis roweru powinien być poprawny.");
        assertEquals(pricePerDay, bike.getPricePerDay(), "Cena za dzień powinna być poprawna.");
        assertEquals(quantity, bike.getQuantity(), "Ilość rowerów powinna być poprawna.");
        assertEquals(gearCount, bike.getGearCount(), "Liczba biegów powinna być poprawna.");
        assertTrue(bike.isAvailable(), "Rower powinien być dostępny, jeśli ilość jest większa niż 0.");
    }

    @Test
    void testSetQuantityUpdatesAvailability() {
        // Przygotowanie danych
        Bike bike = new Bike("Road Bike", "Fast and lightweight bike", 40.0, 5, 18);

        // Weryfikacja początkowej dostępności
        assertTrue(bike.isAvailable(), "Rower powinien być dostępny początkowo.");

        // Zmiana ilości na 0
        bike.setQuantity(0);

        // Weryfikacja, że rower jest niedostępny
        assertFalse(bike.isAvailable(), "Rower nie powinien być dostępny, jeśli ilość wynosi 0.");
    }

    @Test
    void testToString() {
        // Przygotowanie danych
        Bike bike = new Bike("Hybrid Bike", "Versatile bike for all terrains", 60.0, 8, 24);

        // Sprawdzenie formatu toString
        String expectedString = "Bike{name='Hybrid Bike', description='Versatile bike for all terrains', pricePerDay=60.0, gearCount=24}";
        assertEquals(expectedString, bike.toString(), "Metoda toString() powinna zwracać poprawny format.");
    }
}
