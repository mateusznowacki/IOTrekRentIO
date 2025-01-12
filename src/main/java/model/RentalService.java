package model;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class RentalService {

    private final LocalStorage storage;
    private final UserService userService;
    private final EquipmentService equipmentService;
    private final RentalFactory regularRentalFactory;
    private final RentalFactory discountedRentalFactory;

    public RentalService(LocalStorage storage, UserService userService, RentalFactory regularRentalFactory,
                         RentalFactory discountedRentalFactory, EquipmentService equipmentService) {
        this.storage = storage;
        this.userService = userService;
        this.regularRentalFactory = regularRentalFactory;
        this.discountedRentalFactory = discountedRentalFactory;
        this.equipmentService = equipmentService;
    }

    public boolean rentEquipment(int equipmentId, Date startDate, Date endDate) {
        User loggedUser = storage.getLoggedUser();
        if (loggedUser == null) {
            throw new IllegalStateException("Brak zalogowanego użytkownika.");
        }

        Equipment equipment = storage.getEquipmentById(equipmentId);
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


    // Przeglądanie historii wypożyczeń użytkownika
    public List<Rental> getUserRentalHistory(int userId) {
        return storage.getRentals().stream()
                .filter(rental -> rental.getUserId() == userId)
                .toList();
    }

    public boolean isEquipmentAvailable(int equipmentId) {
        Equipment equipment = equipmentService.getEquipmentById(equipmentId);
        return equipment != null && equipment.isAvailable();
    }


    public double calculateRentalCost(int equipmentId, Date startDate, Date endDate) {
        Equipment equipment = equipmentService.getEquipmentById(equipmentId);
        if (equipment == null || !equipment.isAvailable()) {
            throw new IllegalArgumentException("Sprzęt jest niedostępny.");
        }

        // Pobranie zalogowanego użytkownika
        User loggedUser = userService.getLoggedUser();
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
        // Pobranie istniejącego wypożyczenia
        Rental rental = getRentalById(rentalId);

        // Obliczenie nowej daty zakończenia
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(rental.getEndDate());
        calendar.add(Calendar.DAY_OF_MONTH, additionalDays);
        Date newEndDate = calendar.getTime();

        // Sprawdzenie kolizji z innymi wypożyczeniami
        if (checkCollision(rental, newEndDate)) {
            return false; // Kolizja
        }

        // Pobranie użytkownika przypisanego do wypożyczenia
        User user = userService.getUserById(rental.getUserId());
        if (user == null) {
            throw new IllegalStateException("Użytkownik przypisany do wypożyczenia nie istnieje.");
        }

        // Zmiana strategii kosztowej na podstawie roli użytkownika
        if (user.isEmployee()) {
            rental.setCostStrategy(new EmployeeDiscountCostStrategy());
        } else {
            rental.setCostStrategy(new RegularCostStrategy());
        }

        // Przedłużenie wypożyczenia
        rental.extendRental(additionalDays);
        return true;
    }


    public boolean checkCollision(Rental rental, Date newEndDate) {
        for (Rental otherRental : storage.getRentals()) {
            if (otherRental.getEquipment().equals(rental.getEquipment()) &&
                    !otherRental.equals(rental) &&
                    newEndDate.after(otherRental.getStartDate()) &&
                    rental.getStartDate().before(otherRental.getEndDate())) {
                return true; // Kolizja
            }
        }
        return false; // Brak kolizji
    }

    public Rental getRentalById(int rentalId) {
        return storage.getRentals().stream()
                .filter(rental -> rental.getId() == rentalId)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Wypożyczenie o podanym ID nie istnieje."));
    }

    public List<Rental> getAllRentals() {
        return storage.getRentals();
    }

    public boolean returnEquipment(int rentalId) {
        Rental rental = getRentalById(rentalId);

        rental.getEquipment().setAvailable(true);
        return true;
    }
}
