package pl.pwr.model.done;

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

    public ModelFacade(
            EquipmentFactory sportEquipmentFactory,
            EquipmentFactory regularEquipmentFactory,
            RentalFactory regularRentalFactory,
            RentalFactory discountedRentalFactory
    ) {
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
    public Rental createRegularRental(Equipment equipment, Date startDate, Date endDate) {
        Rental rental = regularRentalFactory.createRental(equipment, startDate, endDate);
        rentalList.add(rental);
        equipment.setAvailable(false); // Oznacz sprzęt jako wynajęty
        return rental;
    }

    public Rental createDiscountedRental(Equipment equipment, Date startDate, Date endDate) {
        Rental rental = discountedRentalFactory.createRental(equipment, startDate, endDate);
        rentalList.add(rental);
        equipment.setAvailable(false); // Oznacz sprzęt jako wynajęty
        return rental;
    }

    // Dodatkowe metody dla kontrolera

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
}




