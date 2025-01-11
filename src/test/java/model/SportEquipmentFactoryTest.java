package model;

import mockit.Expectations;
import mockit.Verifications;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SportEquipmentFactoryTest {

    @Test
    void testCreateBike() {
        String name = "Mountain Bike";
        String description = "High-performance bike for rough terrains";
        double pricePerDay = 50.0;
        int gearCount = 21;
        int quantity = 10;

        SportEquipmentFactory factory = new SportEquipmentFactory();
        Equipment bike = factory.createBike(name, description, pricePerDay, gearCount, quantity);

        assertNotNull(bike);
        assertEquals(name, bike.getName());
        assertEquals(description, bike.getDescription());
        assertEquals(pricePerDay * 1.2, bike.getPricePerDay());
        assertEquals(gearCount, ((Bike) bike).getGearCount());
        assertEquals(quantity, bike.getQuantity());
    }


    @Test
    void testCreateTent() {
        String name = "Camping Tent";
        String description = "Compact and lightweight tent for 2 people";
        double pricePerDay = 30.0;
        int capacity = 2;
        int quantity = 5;

        SportEquipmentFactory factory = new SportEquipmentFactory();
        Equipment tent = factory.createTent(name, description, pricePerDay, capacity, quantity);

        assertNotNull(tent);
        assertEquals(name, tent.getName());
        assertEquals(description, tent.getDescription());
        assertEquals(pricePerDay * 1.2, tent.getPricePerDay());
        assertEquals(capacity, ((Tent) tent).getCapacity());
        assertEquals(quantity, tent.getQuantity());
    }


    @Test
    void testCreateBackpack() {
        String name = "Hiking Backpack";
        String description = "Spacious backpack with multiple compartments";
        double pricePerDay = 20.0;
        int volume = 40;
        int quantity = 15;

        SportEquipmentFactory factory = new SportEquipmentFactory();
        Equipment backpack = factory.createBackpack(name, description, pricePerDay, volume, quantity);

        assertNotNull(backpack);
        assertEquals(name, backpack.getName());
        assertEquals(description, backpack.getDescription());
        assertEquals(pricePerDay * 1.2, backpack.getPricePerDay());
        assertEquals(volume, ((Backpack) backpack).getVolume());
        assertEquals(quantity, backpack.getQuantity());
    }

}
