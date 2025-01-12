package model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ModelFacadeTest {
//TODO POPRAWIC TO
    private ModelFacade facade;
    private LocalStorage storage;
//
//    @BeforeEach
//    void setUp() {
//        // Inicjalizacja zależności
//        EquipmentFactory sportEquipmentFactory = new SportEquipmentFactory();
//        EquipmentFactory regularEquipmentFactory = new RegularEquipmentFactory();
//        RentalFactory regularRentalFactory = new RegularRentalFactory();
//        RentalFactory discountedRentalFactory = new EmployeeRentalFactory();
//
//        // Używamy prawdziwego LocalStorage dla uproszczenia
//        storage = LocalStorage.getInstance();
//
//        facade = new ModelFacade(sportEquipmentFactory, regularEquipmentFactory,
//                regularRentalFactory, discountedRentalFactory);
//    }
//
//    @AfterEach
//    void tearDown() {
//        // Usuwanie sprzętu po każdym teście
//        for (Equipment equipment : facade.getEquipmentCatalog()) {
//            facade.removeEquipment(equipment.getId());
//        }
//    }
//
//    @Test
//    void testAddBike() {
//        // Dodanie roweru
//        boolean result = facade.addBike("Rowerek", "Test bike", 50.0, 21, 10);
//
//        // Weryfikacja sukcesu dodania
//        assertTrue(result, "Dodanie roweru powinno zakończyć się sukcesem");
//
//        // Sprawdzenie katalogu
//        List<Equipment> catalog = facade.getEquipmentCatalog();
//        assertEquals(1, catalog.size(), "Katalog powinien zawierać jeden element");
//        Equipment bike = catalog.get(0);
//
//        // Weryfikacja szczegółów dodanego sprzętu
//        assertEquals("Rowerek", bike.getName(), "Nazwa sprzętu powinna być poprawna");
//        assertEquals("Test bike", bike.getDescription(), "Opis sprzętu powinien być poprawny");
//        assertEquals(50.0, bike.getPricePerDay(), 0.01, "Cena za dzień powinna być poprawna");
//        assertEquals(21, ((Bike) bike).getGearCount(), "Liczba biegów powinna być poprawna");
//        assertEquals(10, bike.getQuantity(), "Ilość sprzętu powinna być poprawna");
//    }
//
//
//    @Test
//    void testRentEquipment() {
//        // Dodanie roweru
//        facade.addBike("Bike", "Test bike", 50.0, 21, 10);
//
//        // Wynajem sprzętu
//        boolean result = facade.rentEquipment(1, new Date(), new Date());
//
//        // Weryfikacja
//        assertTrue(result, "Wynajem sprzętu powinien zakończyć się sukcesem");
//    }
//
//    @Test
//    void testGetAvailableEquipment() {
//        // Dodanie sprzętu
//        facade.addBike("Bike", "Test bike", 50.0, 21, 10);
//        facade.addTent("Tent", "Camping tent", 30.0, 4, 5);
//
//        // Pobranie dostępnego sprzętu
//        List<Equipment> availableEquipment = facade.getAvailableEquipment();
//
//        // Weryfikacja
//        assertEquals(2, availableEquipment.size(), "Dostępny sprzęt powinien zawierać dwa elementy");
//
//    }
//
//    @Test
//    void testGetUserRentalHistory() {
//        // Dodanie użytkownika
//        User user = new User(1, "John Doe", "john@example.com", "CUSTOMER", "password");
//        facade.addUser(user);
//
//        // Wynajem sprzętu
//        facade.addBike("Bike", "Test bike", 50.0, 21, 10);
//        facade.rentEquipment(1, new Date(), new Date());
//
//        // Pobranie historii wypożyczeń
//        List<Rental> history = facade.getUserRentalHistory(1);
//
//        // Weryfikacja
//        assertEquals(1, history.size(), "Historia wypożyczeń powinna zawierać jeden element");
//    }
//
//    @Test
//    void testCalculateRentalCost() {
//        // Dodanie użytkownika i ustawienie go jako zalogowanego
//        User testUser = new User(1, "Test User", "test@example.com", "CUSTOMER", "password");
//        facade.addUser(testUser);
//        facade.setLoggedUser(testUser);
//
//        // Dodanie roweru
//        facade.addBike("Bike", "Test bike", 50.0, 21, 10);
//
//        // Wynajem roweru
//        boolean rentResult = facade.rentEquipment(1, new Date(), new Date());
//        assertTrue(rentResult, "Wynajem roweru powinien zakończyć się sukcesem");
//
//        // Obliczenie kosztu wynajmu
//        double cost = facade.calculateRentalCost(1, new Date(), new Date());
//
//        // Weryfikacja
//        assertEquals(50.0, cost, 0.01, "Koszt wynajmu powinien wynosić 50.0 dla jednodniowego wynajmu z ceną 50.0 za dzień");
//    }


}
