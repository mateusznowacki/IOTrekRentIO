package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class LocalStorageTest {

    private LocalStorage localStorage;

    @BeforeEach
    void setUp() {
        // Inicjalizacja instancji LocalStorage przed każdym testem
        localStorage = new LocalStorage();

        // Tworzenie przykładowych użytkowników
        User user1 = new User(1, "Jan Kowalski", "jan.kowalski@example.com", "customer", "password1");
        User user2 = new User(2, "Anna Nowak", "anna.nowak@example.com", "employee", "password2");

        // Dodawanie użytkowników do LocalStorage
        localStorage.addUser(user1);
        localStorage.addUser(user2);

        // Tworzenie przykładowego sprzętu
        Equipment equipment1 = new Equipment("aa", "Rower górski", 50.0, 20);
        Equipment equipment2 = new Equipment("bb", "Namiot kempingowy", 30.0, 10);

        // Dodawanie sprzętu do LocalStorage
        localStorage.addEquipment(equipment1);
        localStorage.addEquipment(equipment2);

        // Tworzenie przykładowych wynajmów
        RentalCostStrategy costStrategy = (days, pricePerDay) -> days * pricePerDay;
        Rental rental1 = new Rental(1, user1.getId(), equipment1, new Date(), new Date(), costStrategy);
        Rental rental2 = new Rental(2, user2.getId(), equipment2, new Date(), new Date(), costStrategy);

        // Dodawanie wynajmów do LocalStorage
        localStorage.addRental(rental1);
        localStorage.addRental(rental2);
    }

    @AfterEach
    void tearDown() {
        // Czyszczenie danych w LocalStorage po każdym teście
        localStorage.getUsers().clear();
        localStorage.getEquipments().clear();
        localStorage.getRentals().clear();
    }

    @Test
    void testAddUser() {
        // Dodanie nowego użytkownika
        User newUser = new User(3, "Piotr Zieliński", "piotr.zielinski@example.com", "employee", "password3");
        localStorage.addUser(newUser);

        // Sprawdzenie, czy użytkownik został dodany
        assertEquals(3, localStorage.getUsers().size(), "Powinno być 3 użytkowników w systemie.");
        assertEquals("Piotr Zieliński", localStorage.getUserById(3).getName(), "Nazwa użytkownika powinna być poprawna.");
    }

    @Test
    void testRemoveUser() {
        // Usunięcie użytkownika o ID 1
        localStorage.removeUser(1);

        // Sprawdzenie, czy użytkownik został usunięty
        assertNull(localStorage.getUserById(1), "Użytkownik powinien zostać usunięty.");
        assertEquals(1, localStorage.getUsers().size(), "Powinno pozostać 1 użytkownik.");
    }

    @Test
    void testGetEquipments() {
        // Sprawdzenie, czy liczba sprzętu jest poprawna
        assertEquals(2, localStorage.getEquipments().size(), "Powinno być 2 sprzęty.");
    }

    @Test
    void testAddEquipment() {
        // Dodanie nowego sprzętu
        Equipment newEquipment = new Equipment("Plecak turystyczny", "turystyczny", 20.0, 15);
        localStorage.addEquipment(newEquipment);

        // Sprawdzenie, czy liczba sprzętu jest poprawna po dodaniu
        assertEquals(3, localStorage.getEquipments().size(), "Powinno być 3 sprzęty.");
        assertEquals("Plecak turystyczny", localStorage.getEquipmentById(newEquipment.getId()).getName(), "Nazwa sprzętu powinna być poprawna.");
    }

    @Test
    void testGetRentals() {
        // Sprawdzenie, czy liczba wynajmów jest poprawna
        assertEquals(2, localStorage.getRentals().size(), "Powinno być 2 wynajmy.");
    }

    @Test
    void testGenerateUserId() {
        // Sprawdzenie, czy generowany ID dla nowego użytkownika jest poprawny
        int newUserId = localStorage.generateUserId();
        assertEquals(3, newUserId, "Nowy ID użytkownika powinien wynosić 3.");
    }

    @Test
    void testEditUserRole() {
        // Edytowanie roli użytkownika
        boolean success = localStorage.editUserRole(1, "employee");
        assertTrue(success, "Zmienienie roli użytkownika powinno zakończyć się sukcesem.");
        assertEquals("employee", localStorage.getUserById(1).getRole(), "Rola użytkownika powinna zostać zmieniona na 'employee'.");

        // Próba zmiany roli na nieprawidłową
        boolean failure = localStorage.editUserRole(1, "admin");
        assertFalse(failure, "Zmiana roli na 'admin' powinna zakończyć się niepowodzeniem.");
    }
}
