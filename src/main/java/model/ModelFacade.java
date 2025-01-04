package model;

import model.done.*;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class ModelFacade {

    private LocalStorage storage;
    private EquipmentFactory sportEquipmentFactory;
    private EquipmentFactory regularEquipmentFactory;
    private RentalFactory regularRentalFactory;
    private RentalFactory discountedRentalFactory;

    public ModelFacade(EquipmentFactory sportEquipmentFactory, EquipmentFactory regularEquipmentFactory,
                       RentalFactory regularRentalFactory, RentalFactory discountedRentalFactory) {
        this.sportEquipmentFactory = sportEquipmentFactory;
        this.regularEquipmentFactory = regularEquipmentFactory;
        this.regularRentalFactory = regularRentalFactory;
        this.discountedRentalFactory = discountedRentalFactory;
        this.storage = LocalStorage.getInstance();
    }



    public List<Equipment> getAvailableEquipment() {
        // Filtruj tylko dostępny sprzęt
        return storage.getEquipments().stream()
                .filter(Equipment::isAvailable)
                .collect(Collectors.toList());
    }





//    // Rejestracja klienta
//    public String registerUser(String name, String email, String role) {
//        for (User user : storage.getUsers()) {
//            if (user.getEmail().equalsIgnoreCase(email)) {
//                return "Użytkownik z podanym adresem e-mail już istnieje.";
//            }
//        }
//        User newUser = new User(storage.getUsers().size() + 1, name, email, role);
//        storage.getUsers().add(newUser);
//        return "Rejestracja zakończona sukcesem dla użytkownika: " + name;
//    }

    // Logowanie użytkownika
    public User loginUser(String email) {
        for (User user : storage.getUsers()) {
            if (user.getEmail().equalsIgnoreCase(email)) {
                return user;
            }
        }
        return null;
    }

    // Wypożyczenie sprzętu
    public Rental rentEquipment(int userId, int equipmentId, Date startDate, Date endDate, boolean isEmployee) {
        Equipment equipment = getEquipmentById(equipmentId);
        if (equipment == null || !equipment.isAvailable()) {
            throw new IllegalArgumentException("Sprzęt jest niedostępny.");
        }
        RentalFactory factory = isEmployee ? discountedRentalFactory : regularRentalFactory;
        Rental rental = factory.createRental(storage.getRentals().size() + 1, userId, equipment, startDate, endDate);
        storage.getRentals().add(rental);
        equipment.setAvailable(false);
        return rental;
    }

    // Przedłużenie wypożyczenia
    public void extendRental(int rentalId, int additionalDays) {
        for (Rental rental : storage.getRentals()) {
            if (rental.getId() == rentalId) {
                rental.extendRental(additionalDays);
                return;
            }
        }
        throw new IllegalArgumentException("Wypożyczenie o podanym ID nie istnieje.");
    }

    // Przeglądanie katalogu sprzętu
    public List<Equipment> getEquipmentCatalog() {
        return storage.getEquipments();
    }

    // Dodawanie nowego sprzętu
    public String addEquipment(String name, String description, double pricePerDay, int quantity) {
        for (Equipment equipment : storage.getEquipments()) {
            if (equipment.getName().equalsIgnoreCase(name)) {
                return "Sprzęt o podanej nazwie już istnieje.";
            }
        }
        Equipment newEquipment = new Equipment(name, description, pricePerDay, quantity);
        storage.getEquipments().add(newEquipment);
        return "Dodano sprzęt: " + name;
    }

    // Usuwanie sprzętu
    public boolean removeEquipment(int equipmentId) {
        return storage.getEquipments().removeIf(equipment -> equipment.getId() == equipmentId);
    }

    // Przeglądanie historii wypożyczeń użytkownika
    public List<Rental> getUserRentalHistory(int userId) {
        return storage.getRentals().stream()
                .filter(rental -> rental.getUserId() == userId)
                .toList();
    }

    // Pobieranie sprzętu po ID
    public Equipment getEquipmentById(int equipmentId) {
        return storage.getEquipments().stream()
                .filter(equipment -> equipment.getId() == equipmentId)
                .findFirst()
                .orElse(null);
    }

    public User getUserById(int userId) {
        return storage.getUsers().stream()
                .filter(user -> user.getId() == userId)
                .findFirst()
                .orElse(null);
    }


    public List<User> getUsers() {
        return storage.getUsers();
    }


    public User getLoggedUser() {
        return storage.getLoggedUser();
    }

    public boolean isEquipmentAvailable(int equipmentId) {
        Equipment equipment = getEquipmentById(equipmentId);
        return equipment != null && equipment.isAvailable();
    }

    public double calculateRentalCost(int equipmentId, Date startDate, Date endDate) {
        Equipment equipment = getEquipmentById(equipmentId);
        if (equipment == null || !equipment.isAvailable()) {
            throw new IllegalArgumentException("Sprzęt jest niedostępny.");
        }

        // Pobranie zalogowanego użytkownika
        User loggedUser = getLoggedUser();
        if (loggedUser == null) {
            throw new IllegalStateException("Brak zalogowanego użytkownika.");
        }

        // Wybór fabryki na podstawie roli użytkownika
        RentalFactory factory = loggedUser.isEmployee() ? discountedRentalFactory : regularRentalFactory;

        // Tworzenie tymczasowego wypożyczenia za pomocą fabryki
        Rental temporaryRental = factory.createRental(0, loggedUser.getId(), equipment, startDate, endDate);

        // Obliczenie kosztu wynajmu za pomocą strategii kosztów
        return temporaryRental.calculateCost();
    }

}
