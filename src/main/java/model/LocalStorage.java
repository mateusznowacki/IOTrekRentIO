package model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class LocalStorage {
    // Statyczna instancja klasy - jedyna w całym systemie
    private static LocalStorage instance;

    private List<Equipment> equipments; // Lista dostępnego sprzętu
    private List<Rental> rentals;       // Lista wynajmów
    private List<User> users;           // Lista użytkowników
    private User loggedUser;            // Zalogowany użytkownik

    // Prywatny konstruktor dla Singletona
    private LocalStorage() {
        this.equipments = new ArrayList<>();
        this.rentals = new ArrayList<>();
        this.users = new ArrayList<>();
        this.loggedUser = null;
    }

    // Metoda do pobrania jedynej instancji klasy
    public static synchronized LocalStorage getInstance() {
        if (instance == null) {
            instance = new LocalStorage();
            instance.initializeData();
        }
        return instance;
    }

    public void addUser(User user) {
        // Sprawdzenie, czy użytkownik o takim ID już istnieje
        if (users.stream().anyMatch(existingUser -> existingUser.getId() == user.getId())) {
            throw new IllegalArgumentException("Użytkownik o podanym ID już istnieje.");
        }

        // Sprawdzenie, czy adres email jest unikalny
        if (users.stream().anyMatch(existingUser -> existingUser.getEmail().equalsIgnoreCase(user.getEmail()))) {
            throw new IllegalArgumentException("Użytkownik o podanym adresie email już istnieje.");
        }

        // Sprawdzenie poprawności roli użytkownika
        if (!user.getRole().equalsIgnoreCase("customer") && !user.getRole().equalsIgnoreCase("employee")) {
            throw new IllegalArgumentException("Nieprawidłowa rola użytkownika. Dozwolone: 'customer', 'employee'.");
        }

        // Sprawdzenie poprawności hasła (np. minimalna długość)
        if (user.getPassword() == null || user.getPassword().length() < 1) {
            throw new IllegalArgumentException("Hasło musi mieć co najmniej 1 znak.");
        }

        // Dodanie użytkownika do listy
        users.add(user);
    }


    public List<User> getUsers() {
        return users;
    }

    public User getUserById(int id) {
        return users.stream()
                .filter(user -> user.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public boolean removeUser(int id) {
        return users.removeIf(user -> user.getId() == id);
    }

    // Operacje na sprzęcie
    public void addEquipment(Equipment equipment) {
        equipments.add(equipment);
    }

    public List<Equipment> getEquipments() {
        return equipments;
    }

    public Equipment getEquipmentById(int id) {
        return equipments.stream()
                .filter(equipment -> equipment.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public void removeEquipment(int id) {
        equipments.removeIf(equipment -> equipment.getId() == id);
    }

    // Operacje na wynajmach
    public void addRental(Rental rental) {
        rentals.add(rental);
    }

    public List<Rental> getRentals() {
        return rentals;
    }

    public List<Rental> getRentalsByUser(int userId) {
        List<Rental> userRentals = new ArrayList<>();
        for (Rental rental : rentals) {
            if (rental.getUserId() == userId) {
                userRentals.add(rental);
            }
        }
        return userRentals;
    }

    // Zarządzanie zalogowanym użytkownikiem
    public User getLoggedUser() {
        return loggedUser;
    }

    public void setLoggedUser(User loggedUser) {
        this.loggedUser = loggedUser;
    }


    private void initializeData() {
        // Dodawanie użytkowników
        users.add(new User(1, "Jan Kowalski", "jan.kowalski@example.com", "CUSTOMER", "1"));
        users.add(new User(2, "Anna Nowak", "anna.nowak@example.com", "EMPLOYEE", "1"));
        users.add(new User(3, "Piotr Zieliński", "piotr.zielinski@example.com", "EMPLOYEE", "1"));

        // Ustawienie zalogowanego użytkownika
        loggedUser = users.get(0); // Jan Kowalski jest domyślnie zalogowany

        // Dodawanie sprzętu
        equipments.add(new Equipment("aa", "Rower górski", 50.0, 20));
        equipments.add(new Equipment("bb", "Namiot kempingowy", 30.0, 20));
        equipments.add(new Equipment("cc", "Plecak turystyczny", 20.0, 20)); // Niedostępny

        // Dodawanie wynajmów
        RentalCostStrategy costStrategy = (days, pricePerDay) -> days * pricePerDay; // Prosta strategia kosztów

        Calendar calendar = Calendar.getInstance();

        // Wynajem dla użytkownika 1
        calendar.set(2023, Calendar.DECEMBER, 1);
        Date startDate1 = calendar.getTime();
        calendar.set(2023, Calendar.DECEMBER, 5);
        Date endDate1 = calendar.getTime();
        rentals.add(new Rental(1, users.get(0).getId(), equipments.get(0), startDate1, endDate1, costStrategy));

        // Wynajem dla użytkownika 2
        calendar.set(2023, Calendar.NOVEMBER, 20);
        Date startDate2 = calendar.getTime();
        calendar.set(2023, Calendar.NOVEMBER, 25);
        Date endDate2 = calendar.getTime();
        rentals.add(new Rental(2, users.get(1).getId(), equipments.get(1), startDate2, endDate2, costStrategy));
    }

    public int generateUserId() {
        return users.stream()
                .map(User::getId)
                .max(Integer::compareTo)
                .orElse(0) + 1;
    }

    public boolean editUserRole(int userId, String newRole) {
        User user = getUserById(userId);
        if (user == null) {
            return false;
        }

        if (!newRole.equalsIgnoreCase("employee") && !newRole.equalsIgnoreCase("customer")) {
            return false;
        }

        user.setRole(newRole);
        return true;
    }
}
