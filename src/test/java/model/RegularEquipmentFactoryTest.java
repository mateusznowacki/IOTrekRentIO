package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RegularEquipmentFactoryTest {

    private RegularEquipmentFactory factory;

    @BeforeEach
    void setUp() {
        // Inicjalizacja fabryki
        factory = new RegularEquipmentFactory();
    }

    @Test
    void createBike() {
        // Dane wejściowe
        String name = "Mountain Bike";
        String description = "High-quality mountain bike";
        double pricePerDay = 50.0;
        int gearCount = 21;
        int quantity = 10;

        // Wywołanie metody
        Equipment bike = factory.createBike(name, description, pricePerDay,gearCount,quantity );

        // Weryfikacja wyniku
        assertNotNull(bike, "Rower nie powinien być null");
        assertTrue(bike instanceof Bike, "Obiekt powinien być instancją klasy Bike");
        assertEquals(name, bike.getName(), "Nazwa powinna być ustawiona poprawnie");
        assertEquals(description, bike.getDescription(), "Opis powinien być ustawiony poprawnie");
        assertEquals(pricePerDay, bike.getPricePerDay(), "Cena za dzień powinna być ustawiona poprawnie");
        assertEquals(quantity, bike.getQuantity(), "Ilość powinna być ustawiona poprawnie");

        // Weryfikacja specyficznych pól Bike
        assertEquals(gearCount, ((Bike) bike).getGearCount(), "Liczba biegów powinna być ustawiona poprawnie");
    }

    @Test
    void createTent() {
        // Dane wejściowe
        String name = "Camping Tent";
        String description = "Durable 4-person camping tent";
        double pricePerDay = 30.0;
        int capacity = 4;
        int quantity = 5;

        // Wywołanie metody
        Equipment tent = factory.createTent(name, description, pricePerDay, capacity, quantity);

        // Weryfikacja wyniku
        assertNotNull(tent, "Namiot nie powinien być null");
        assertTrue(tent instanceof Tent, "Obiekt powinien być instancją klasy Tent");
        assertEquals(name, tent.getName(), "Nazwa powinna być ustawiona poprawnie");
        assertEquals(description, tent.getDescription(), "Opis powinien być ustawiony poprawnie");
        assertEquals(pricePerDay, tent.getPricePerDay(), "Cena za dzień powinna być ustawiona poprawnie");
        assertEquals(quantity, tent.getQuantity(), "Ilość powinna być ustawiona poprawnie");

        // Weryfikacja specyficznych pól Tent
        assertEquals(capacity, ((Tent) tent).getCapacity(), "Pojemność powinna być ustawiona poprawnie");
    }

    @Test
    void createBackpack() {
        // Dane wejściowe
        String name = "Hiking Backpack";
        String description = "Spacious hiking backpack with 30L volume";
        double pricePerDay = 15.0;
        int volume = 30; // Pojemność w litrach
        int quantity = 20;

        // Wywołanie metody
        Equipment backpack = factory.createBackpack(name, description, pricePerDay,volume, quantity );

        // Weryfikacja wyniku
        assertNotNull(backpack, "Plecak nie powinien być null");
        assertTrue(backpack instanceof Backpack, "Obiekt powinien być instancją klasy Backpack");
        assertEquals(name, backpack.getName(), "Nazwa powinna być ustawiona poprawnie");
        assertEquals(description, backpack.getDescription(), "Opis powinien być ustawiony poprawnie");
        assertEquals(pricePerDay, backpack.getPricePerDay(), "Cena za dzień powinna być ustawiona poprawnie");
        assertEquals(quantity, backpack.getQuantity(), "Ilość powinna być ustawiona poprawnie");

        // Weryfikacja specyficznych pól Backpack
        assertEquals(volume, ((Backpack) backpack).getVolume(), "Pojemność powinna być ustawiona poprawnie");
    }
}