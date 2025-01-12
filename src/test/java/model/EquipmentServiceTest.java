package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EquipmentServiceTest {

    private EquipmentService equipmentService;
    private LocalStorage storageMock;
    private EquipmentFactory regularFactoryMock;
    private EquipmentFactory sportFactoryMock;

    @BeforeEach
    void setUp() {
        // Mockowanie zależności
        storageMock = mock(LocalStorage.class);
        regularFactoryMock = mock(EquipmentFactory.class);
        sportFactoryMock = mock(EquipmentFactory.class);

        // Inicjalizacja testowanej klasy z mockami
        equipmentService = new EquipmentService(storageMock, regularFactoryMock, sportFactoryMock);
    }

    @Test
    void testGetAvailableEquipment() {
        // Przygotowanie danych testowych
        Equipment availableEquipment = new Equipment("Bike", "Mountain bike", 50.0, 10);
        availableEquipment.setAvailable(true);
        Equipment unavailableEquipment = new Equipment("Tent", "Camping tent", 30.0, 5);
        unavailableEquipment.setAvailable(false);

        when(storageMock.getEquipments()).thenReturn(List.of(availableEquipment, unavailableEquipment));

        // Testowanie
        List<Equipment> available = equipmentService.getAvailableEquipment();

        // Weryfikacja
        assertEquals(1, available.size(), "Powinna być dostępna tylko jedna pozycja.");
        assertTrue(available.contains(availableEquipment), "Dostępny sprzęt powinien zawierać rower.");
    }

    @Test
    void testAddBike() {
        // Przygotowanie danych
        EquipmentFactory regularFactoryMock = mock(EquipmentFactory.class);
        Equipment bike = new Equipment("Bike", "Mountain bike", 50.0, 10);
        bike.setId(1); // Ustawienie ID dla testowego roweru

        // Mockowanie metody createBike w fabryce
        when(regularFactoryMock.createBike("Bike", "Mountain bike", 50.0, 21, 10)).thenReturn(bike);

        // Mockowanie zachowania LocalStorage
        List<Equipment> equipmentList = new ArrayList<>(); // Modyfikowalna lista
        when(storageMock.getEquipments()).thenReturn(equipmentList);

        // Tworzenie obiektu EquipmentService z mockami
        EquipmentService equipmentService = new EquipmentService(storageMock, regularFactoryMock, null);

        // Testowanie
        boolean result = equipmentService.addBike("Bike", "Mountain bike", 50.0, 21, 10);

        // Weryfikacja
        assertTrue(result, "Rower powinien zostać dodany.");
        assertEquals(1, equipmentList.size(), "Lista sprzętu powinna zawierać jeden element.");
        assertEquals("Bike", equipmentList.get(0).getName(), "Nazwa sprzętu powinna być poprawna.");
        assertEquals("Mountain bike", equipmentList.get(0).getDescription(), "Opis roweru powinien być poprawny.");
        assertEquals(50.0, equipmentList.get(0).getPricePerDay(), "Cena za dzień powinna być poprawna.");
    }

    @Test
    void testAddTent() {
        // Przygotowanie danych
        EquipmentFactory regularFactoryMock = mock(EquipmentFactory.class);
        Equipment tent = new Equipment("Tent", "Camping tent", 30.0, 5);
        tent.setId(2);

        // Mockowanie metody createTent w fabryce
        when(regularFactoryMock.createTent("Tent", "Camping tent", 30.0, 5, 4)).thenReturn(tent);

        // Mockowanie LocalStorage
        List<Equipment> equipmentList = new ArrayList<>();
        when(storageMock.getEquipments()).thenReturn(equipmentList);

        // Tworzenie EquipmentService z mockami
        EquipmentService equipmentService = new EquipmentService(storageMock, regularFactoryMock, null);

        // Testowanie
        boolean result = equipmentService.addTent("Tent", "Camping tent", 30.0, 4, 5);

        // Weryfikacja
        assertTrue(result, "Namiot powinien zostać dodany.");
        assertEquals(1, equipmentList.size(), "Lista sprzętu powinna zawierać jeden element.");
        assertEquals("Tent", equipmentList.get(0).getName(), "Nazwa sprzętu powinna być poprawna.");
    }

    @Test
    void testAddBackpack() {
        // Przygotowanie danych
        EquipmentFactory regularFactoryMock = mock(EquipmentFactory.class);
        Equipment backpack = new Equipment("Backpack", "Hiking backpack", 20.0, 15);
        backpack.setId(3);

        // Mockowanie metody createBackpack w fabryce
        when(regularFactoryMock.createBackpack("Backpack", "Hiking backpack", 20.0, 15, 30)).thenReturn(backpack);

        // Mockowanie LocalStorage
        List<Equipment> equipmentList = new ArrayList<>();
        when(storageMock.getEquipments()).thenReturn(equipmentList);

        // Tworzenie EquipmentService z mockami
        EquipmentService equipmentService = new EquipmentService(storageMock, regularFactoryMock, null);

        // Testowanie
        boolean result = equipmentService.addBackpack("Backpack", "Hiking backpack", 20.0, 30, 15);

        // Weryfikacja
        assertTrue(result, "Plecak powinien zostać dodany.");
        assertEquals(1, equipmentList.size(), "Lista sprzętu powinna zawierać jeden element.");
        assertEquals("Backpack", equipmentList.get(0).getName(), "Nazwa sprzętu powinna być poprawna.");
    }



    @Test
    void testAddSportTent() {
            // Przygotowanie danych
            EquipmentFactory sportFactoryMock = mock(EquipmentFactory.class);
            Equipment sportTent = new Equipment("Sport Bike", "High-end road bike", 100.0, 8);
            sportTent.setId(6);

            // Mockowanie metody createBike w fabryce sportowej
            when(sportFactoryMock.createTent("Sport Bike", "High-end road bike", 100.0, 22, 8)).thenReturn(sportTent);

            // Mockowanie LocalStorage
            List<Equipment> equipmentList = new ArrayList<>();
            when(storageMock.getEquipments()).thenReturn(equipmentList);

            // Tworzenie EquipmentService z mockami
            EquipmentService equipmentService = new EquipmentService(storageMock, null, sportFactoryMock);

            // Testowanie
            boolean result = equipmentService.addSportTent("Sport Bike", "High-end road bike", 100.0, 22, 8);

            // Weryfikacja
            assertTrue(result, "Sportowy rower powinien zostać dodany.");
            assertEquals(1, equipmentList.size(), "Lista sprzętu powinna zawierać jeden element.");
            assertEquals("Sport Bike", equipmentList.get(0).getName(), "Nazwa sprzętu powinna być poprawna.");
        }

    

    @Test
    void testAddSportBackpack() {
                  // Przygotowanie danych
            EquipmentFactory sportFactoryMock = mock(EquipmentFactory.class);
            Equipment sportBackpack = new Equipment("Sport Bike", "High-end road bike", 100.0, 8);
            sportBackpack.setId(6);

            // Mockowanie metody createBike w fabryce sportowej
            when(sportFactoryMock.createBackpack("Sport Bike", "High-end road bike", 100.0, 22, 8)).thenReturn(sportBackpack);

            // Mockowanie LocalStorage
            List<Equipment> equipmentList = new ArrayList<>();
            when(storageMock.getEquipments()).thenReturn(equipmentList);

            // Tworzenie EquipmentService z mockami
            EquipmentService equipmentService = new EquipmentService(storageMock, null, sportFactoryMock);

            // Testowanie
            boolean result = equipmentService.addSportBackpack("Sport Bike", "High-end road bike", 100.0, 22, 8);

            // Weryfikacja
            assertTrue(result, "Sportowy rower powinien zostać dodany.");
            assertEquals(1, equipmentList.size(), "Lista sprzętu powinna zawierać jeden element.");
            assertEquals("Sport Bike", equipmentList.get(0).getName(), "Nazwa sprzętu powinna być poprawna.");
        }
        

    @Test
    void testAddSportBike() {
        // Przygotowanie danych
        EquipmentFactory sportFactoryMock = mock(EquipmentFactory.class);
        Equipment sportBike = new Equipment("Sport Bike", "High-end road bike", 100.0, 8);
        sportBike.setId(6);

        // Mockowanie metody createBike w fabryce sportowej
        when(sportFactoryMock.createBike("Sport Bike", "High-end road bike", 100.0, 22, 8)).thenReturn(sportBike);

        // Mockowanie LocalStorage
        List<Equipment> equipmentList = new ArrayList<>();
        when(storageMock.getEquipments()).thenReturn(equipmentList);

        // Tworzenie EquipmentService z mockami
        EquipmentService equipmentService = new EquipmentService(storageMock, null, sportFactoryMock);

        // Testowanie
        boolean result = equipmentService.addSportBike("Sport Bike", "High-end road bike", 100.0, 22, 8);

        // Weryfikacja
        assertTrue(result, "Sportowy rower powinien zostać dodany.");
        assertEquals(1, equipmentList.size(), "Lista sprzętu powinna zawierać jeden element.");
        assertEquals("Sport Bike", equipmentList.get(0).getName(), "Nazwa sprzętu powinna być poprawna.");
    }


    @Test
    void testRemoveEquipment() {
        // Przygotowanie danych
        Equipment equipment = new Equipment("Bike", "Mountain bike", 50.0, 10);
         // Zakładamy, że sprzęt ma ID = 1
        List<Equipment> equipmentList = new ArrayList<>();
        equipmentList.add(equipment);

        // Mockowanie
        when(storageMock.getEquipments()).thenReturn(equipmentList);

        // Testowanie
        boolean result = equipmentService.removeEquipment(equipment.getId());

        // Weryfikacja
        assertTrue(result, "Sprzęt powinien zostać usunięty.");
        assertTrue(equipmentList.isEmpty(), "Lista sprzętu powinna być pusta po usunięciu.");
    }


    @Test
    void testRepairEquipment() {
        // Przygotowanie danych
        Equipment equipment = new Equipment("Bike", "Mountain bike", 50.0, 10);
        equipment.setId(1); // Ustawienie ID dla sprzętu
        equipment.setAvailable(false); // Ustawienie sprzętu jako niedostępnego

        List<Equipment> equipmentList = new ArrayList<>();
        equipmentList.add(equipment);

        // Mockowanie
        when(storageMock.getEquipments()).thenReturn(equipmentList);

        // Testowanie
        boolean result = equipmentService.repairEquipment(1, "Naprawiono oponę.");

        // Weryfikacja
        assertTrue(result, "Naprawa sprzętu powinna się powieść.");
        assertTrue(equipment.isAvailable(), "Sprzęt powinien być dostępny po naprawie.");
        assertEquals("Naprawiono oponę.", equipment.getRepairDescription(), "Opis naprawy powinien być ustawiony.");
    }



}
