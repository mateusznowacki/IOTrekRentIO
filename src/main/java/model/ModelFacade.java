package model;

import model.done.*;

import java.util.Calendar;
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





    // Logowanie użytkownika
    public User loginUser(String email) {
        for (User user : storage.getUsers()) {
            if (user.getEmail().equalsIgnoreCase(email)) {
                return user;
            }
        }
        return null;
    }

    public boolean rentEquipment(int equipmentId, Date startDate, Date endDate) {
        User loggedUser = getLoggedUser();
        if (loggedUser == null) {
            throw new IllegalStateException("Brak zalogowanego użytkownika.");
        }

        Equipment equipment = getEquipmentById(equipmentId);
        if (equipment == null || !equipment.isAvailable()) {
            throw new IllegalArgumentException("Wybrany sprzęt nie jest dostępny.");
        }

        // Wybór fabryki na podstawie roli użytkownika
        RentalFactory factory = loggedUser.isEmployee() ? new EmployeeRentalFactory() : new RegularRentalFactory();

        Rental rental = factory.createRental(
                storage.getRentals().size() + 1,
                loggedUser.getId(),
                equipment,
                startDate,
                endDate
        );

        storage.getRentals().add(rental);
        equipment.setAvailable(false);
        return true;
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


    public boolean checkAndExtendRental(int rentalId, int additionalDays) {
        Rental rental = storage.getRentals().stream()
                .filter(r -> r.getId() == rentalId)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Wypożyczenie o podanym ID nie istnieje."));

        // Obliczenie nowej daty zakończenia
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(rental.getEndDate());
        calendar.add(Calendar.DAY_OF_MONTH, additionalDays);
        Date newEndDate = calendar.getTime();

        // Sprawdzenie kolizji z innymi wypożyczeniami
        for (Rental otherRental : storage.getRentals()) {
            if (otherRental.getEquipment().equals(rental.getEquipment()) &&
                    !otherRental.equals(rental) &&
                    newEndDate.after(otherRental.getStartDate()) &&
                    rental.getStartDate().before(otherRental.getEndDate())) {
                return false; // Kolizja
            }
        }

        // Przedłużenie wypożyczenia
        rental.extendRental(additionalDays);
        return true;
    }





}
