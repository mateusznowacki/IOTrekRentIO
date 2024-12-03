package pl.pwr.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ModelFacade {

    private EquipmentFactory sportEquipmentFactory;
    private EquipmentFactory regularEquipmentFactory;
    private RentalFactory regularRentalFactory;
    private RentalFactory discountedRentalFactory;
    private List<Equipment> equipmentList = new ArrayList<>();
    private List<Rental> rentalList = new ArrayList<>();

    public ModelFacade(EquipmentFactory sportEquipmentFactory, EquipmentFactory regularEquipmentFactory,
                       RentalFactory regularRentalFactory, RentalFactory discountedRentalFactory) {
        this.sportEquipmentFactory = sportEquipmentFactory;
        this.regularEquipmentFactory = regularEquipmentFactory;
        this.regularRentalFactory = regularRentalFactory;
        this.discountedRentalFactory = discountedRentalFactory;
    }

    // Metody do tworzenia sprzętu
    public Equipment createSportBike(String name, String description, double pricePerDay, int gearCount) {
        Equipment bike = sportEquipmentFactory.createBike(name, description, pricePerDay, gearCount);
        equipmentList.add(bike);
        return bike;
    }

    public Equipment createRegularTent(String name, String description, double pricePerDay, int capacity) {
        Equipment tent = regularEquipmentFactory.createTent(name, description, pricePerDay, capacity);
        equipmentList.add(tent);
        return tent;
    }

    public Equipment createRegularBackpack(String name, String description, double pricePerDay, int volume) {
        Equipment backpack = regularEquipmentFactory.createBackpack(name, description, pricePerDay, volume);
        equipmentList.add(backpack);
        return backpack;
    }

    // Metody do wynajmów
    public Rental createRegularRental(Equipment equipment, Date startDate, Date endDate, int userId) {
        Rental rental = regularRentalFactory.createRental(equipment, startDate, endDate, userId);
        rentalList.add(rental);
        equipment.setAvailable(false); // Oznacz sprzęt jako wynajęty
        return rental;
    }

    public Rental createDiscountedRental(Equipment equipment, Date startDate, Date endDate, int userId) {
        Rental rental = discountedRentalFactory.createRental(equipment, startDate, endDate, userId);
        rentalList.add(rental);
        equipment.setAvailable(false); // Oznacz sprzęt jako wynajęty
        return rental;
    }

    // Dodatkowe metody dla kontrolera

    // Dodanie nowego sprzętu do listy
    public String addEquipment(String name, String description, double pricePerDay, int quantity) {
        Equipment equipment = new Equipment(name, description, pricePerDay);

        // Walidacja danych na poziomie modelu
        if (!equipment.isValid()) {
            return "Nieprawidłowe dane sprzętu.";
        }

        equipmentList.add(equipment);
        return "Nowy sprzęt został dodany: " + equipment;
    }

    // Pobieranie wszystkich sprzętów
    public List<Equipment> getAllEquipment() {
        return new ArrayList<>(equipmentList);
    }

    // Pobieranie dostępnych sprzętów
    public List<Equipment> getAvailableEquipment() {
        List<Equipment> availableEquipment = new ArrayList<>();
        for (Equipment equipment : equipmentList) {
            if (equipment.isAvailable()) {
                availableEquipment.add(equipment);
            }
        }
        return availableEquipment;
    }

    // Pobieranie wszystkich wynajmów
    public List<Rental> getAllRentals() {
        return new ArrayList<>(rentalList);
    }

    // Zwrot sprzętu
    public void returnEquipment(Rental rental) {
        rental.getEquipment().setAvailable(true); // Oznacz sprzęt jako dostępny
        rentalList.remove(rental); // Opcjonalnie usuń wynajem z listy aktywnych wynajmów
    }

    // Sprawdzanie dostępności sprzętu
    public boolean isEquipmentAvailable(Equipment equipment) {
        return equipment.isAvailable();
    }

    public List<Rental> getRentalsByUser(int id) {
        List<Rental> userRentals = new ArrayList<>();
        for (Rental rental : rentalList) {
            if (rental.getUserId() == id) {
                userRentals.add(rental);
            }
        }
        return userRentals;
    }

    public void addRental(Rental rental) {
    }

    public Equipment generateRentalId() {
        return null;
    }

    public Equipment getEquipmentById(int equipmentId) {

        return null;
    }
}