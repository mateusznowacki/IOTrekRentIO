package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EquipmentTest {

    @Test
    void testConstructor() {
        // Testowanie konstruktora
        Equipment equipment = new Equipment("Bike", "Mountain bike", 50.0, 10);

        assertEquals("Bike", equipment.getName(), "Nazwa sprzętu powinna być poprawna.");
        assertEquals("Mountain bike", equipment.getDescription(), "Opis sprzętu powinien być poprawny.");
        assertEquals(50.0, equipment.getPricePerDay(), "Cena za dzień powinna być poprawna.");
        assertEquals(10, equipment.getQuantity(), "Ilość sprzętu powinna być poprawna.");
        assertTrue(equipment.isAvailable(), "Sprzęt powinien być dostępny.");
    }

    @Test
    void testSetQuantity() {
        // Testowanie zmiany ilości sprzętu
        Equipment equipment = new Equipment("Bike", "Mountain bike", 50.0, 10);

        equipment.setQuantity(5);
        assertEquals(5, equipment.getQuantity(), "Ilość sprzętu powinna wynosić 5.");
        assertTrue(equipment.isAvailable(), "Sprzęt powinien być dostępny.");

        equipment.setQuantity(0);
        assertEquals(0, equipment.getQuantity(), "Ilość sprzętu powinna wynosić 0.");
        assertFalse(equipment.isAvailable(), "Sprzęt nie powinien być dostępny.");
    }

    @Test
    void testSetAvailable() {
        // Testowanie ustawiania dostępności
        Equipment equipment = new Equipment("Bike", "Mountain bike", 50.0, 10);

        equipment.setAvailable(false);
        assertFalse(equipment.isAvailable(), "Sprzęt powinien być niedostępny.");

        equipment.setAvailable(true);
        assertTrue(equipment.isAvailable(), "Sprzęt powinien być dostępny.");
    }

    @Test
    void testSetRepairDescription() {
        // Testowanie ustawiania opisu naprawy
        Equipment equipment = new Equipment("Bike", "Mountain bike", 50.0, 10);

        equipment.setRepairDescription("Wymieniono oponę.");
        assertEquals("Wymieniono oponę.", equipment.getRepairDescription(), "Opis naprawy powinien być poprawny.");
    }

    @Test
    void testSetId() {
        // Testowanie ustawiania ID
        Equipment equipment = new Equipment("Bike", "Mountain bike", 50.0, 10);

        equipment.setId(100);
        assertEquals(100, equipment.getId(), "ID sprzętu powinno wynosić 100.");
    }

    @Test
    void testToString() {
        // Testowanie metody toString
        Equipment equipment = new Equipment("Bike", "Mountain bike", 50.0, 10);

        String expected = "Equipment{id=" + equipment.getId() +
                ", name='Bike', description='Mountain bike', pricePerDay=50.0, available=true, quantity=10}";
        assertEquals(expected, equipment.toString(), "Reprezentacja tekstowa sprzętu powinna być poprawna.");
    }
}

