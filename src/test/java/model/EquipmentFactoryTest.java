package model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EquipmentFactoryTest  {

    @Test
    void testCreateBike() {
        EquipmentFactory factory = new RegularEquipmentFactory();
        Equipment bike = factory.createBike("Regular Bike", "A standard bike", 30.0, 18, 5);

        assertNotNull(bike, "Rower nie powinien być null.");
        assertEquals("Regular Bike", bike.getName(), "Nazwa roweru powinna być poprawna.");
        assertEquals("A standard bike", bike.getDescription(), "Opis roweru powinien być poprawny.");
        assertEquals(30.0, bike.getPricePerDay(), "Cena za dzień powinna być poprawna.");
        assertEquals(5, bike.getQuantity(), "Ilość powinna być poprawna.");
    }

    @Test
    void testCreateTent() {
        EquipmentFactory factory = new RegularEquipmentFactory();
        Equipment tent = factory.createTent("Regular Tent", "A standard tent", 25.0, 4, 10);

        assertNotNull(tent, "Namiot nie powinien być null.");
        assertEquals("Regular Tent", tent.getName(), "Nazwa namiotu powinna być poprawna.");
        assertEquals("A standard tent", tent.getDescription(), "Opis namiotu powinien być poprawny.");
        assertEquals(25.0, tent.getPricePerDay(), "Cena za dzień powinna być poprawna.");
        assertEquals(10, tent.getQuantity(), "Ilość powinna być poprawna.");
    }

    @Test
    void testCreateBackpack() {
        EquipmentFactory factory = new RegularEquipmentFactory();
        Equipment backpack = factory.createBackpack("Regular Backpack", "A standard backpack", 15.0, 30, 8);

        assertNotNull(backpack, "Plecak nie powinien być null.");
        assertEquals("Regular Backpack", backpack.getName(), "Nazwa plecaka powinna być poprawna.");
        assertEquals("A standard backpack", backpack.getDescription(), "Opis plecaka powinien być poprawny.");
        assertEquals(15.0, backpack.getPricePerDay(), "Cena za dzień powinna być poprawna.");
        assertEquals(8, backpack.getQuantity(), "Ilość powinna być poprawna.");
    }


    @Test
    void testCreateSportBike() {
        EquipmentFactory factory = new SportEquipmentFactory();
        Equipment bike = factory.createBike("Sport Bike", "A high-end sport bike", 100.0, 21, 3);

        assertNotNull(bike, "Rower sportowy nie powinien być null.");
        assertEquals("Sport Bike", bike.getName(), "Nazwa roweru sportowego powinna być poprawna.");
        assertEquals("A high-end sport bike", bike.getDescription(), "Opis roweru sportowego powinien być poprawny.");
        assertEquals(120.0, bike.getPricePerDay(), "Cena za dzień powinna być poprawna.");
        assertEquals(3, bike.getQuantity(), "Ilość powinna być poprawna.");
    }

    @Test
    void testCreateSportTent() {
        EquipmentFactory factory = new SportEquipmentFactory();
        Equipment tent = factory.createTent("Sport Tent", "A high-quality sport tent", 80.0, 2, 5);

        assertNotNull(tent, "Namiot sportowy nie powinien być null.");
        assertEquals("Sport Tent", tent.getName(), "Nazwa namiotu sportowego powinna być poprawna.");
        assertEquals("A high-quality sport tent", tent.getDescription(), "Opis namiotu sportowego powinien być poprawny.");
        assertEquals(96.0, tent.getPricePerDay(), "Cena za dzień powinna być poprawna.");
        assertEquals(5, tent.getQuantity(), "Ilość powinna być poprawna.");
    }

    @Test
    void testCreateSportBackpack() {
        EquipmentFactory factory = new SportEquipmentFactory();
        Equipment backpack = factory.createBackpack("Sport Backpack", "A high-end sport backpack", 60.0, 40, 7);

        assertNotNull(backpack, "Plecak sportowy nie powinien być null.");
        assertEquals("Sport Backpack", backpack.getName(), "Nazwa plecaka sportowego powinna być poprawna.");
        assertEquals("A high-end sport backpack", backpack.getDescription(), "Opis plecaka sportowego powinien być poprawny.");
        assertEquals(72.0, backpack.getPricePerDay(), "Cena za dzień powinna być poprawna.");
        assertEquals(7, backpack.getQuantity(), "Ilość powinna być poprawna.");
    }




}