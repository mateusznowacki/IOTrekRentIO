package model;

import java.util.List;
import java.util.stream.Collectors;

public class EquipmentService {
    private final LocalStorage storage;
    private final EquipmentFactory regularEquipmentFactory;
    private final EquipmentFactory sportEquipmentFactory;

    public EquipmentService(LocalStorage storage, EquipmentFactory regularEquipmentFactory, EquipmentFactory sportEquipmentFactory) {
        this.storage = storage;
        this.regularEquipmentFactory = regularEquipmentFactory;
        this.sportEquipmentFactory = sportEquipmentFactory;
    }

    public List<Equipment> getAvailableEquipment() {
        // Filtruj tylko dostępny sprzęt
        return storage.getEquipments().stream()
                .filter(Equipment::isAvailable)
                .collect(Collectors.toList());
    }

    public boolean addBike(String name, String description, double pricePerDay, int gearCount, int quantity) {
        for (Equipment equipment : storage.getEquipments()) {
            if (equipment.getName().equalsIgnoreCase(name)) {
                return false; // Sprzęt o podanej nazwie już istnieje
            }
        }
        Equipment bike = regularEquipmentFactory.createBike(name, description, pricePerDay, gearCount, quantity);
        storage.getEquipments().add(bike);
        return true;
    }

    public boolean addTent(String name, String description, double pricePerDay, int capacity, int quantity) {
        for (Equipment equipment : storage.getEquipments()) {
            if (equipment.getName().equalsIgnoreCase(name)) {
                return false; // Sprzęt o podanej nazwie już istnieje
            }
        }
        Equipment tent = regularEquipmentFactory.createTent(name, description, pricePerDay, quantity, capacity);
        storage.getEquipments().add(tent);
        return true;
    }

    public boolean addBackpack(String name, String description, double pricePerDay, int volume, int quantity) {
        for (Equipment equipment : storage.getEquipments()) {
            if (equipment.getName().equalsIgnoreCase(name)) {
                return false; // Sprzęt o podanej nazwie już istnieje
            }
        }
        Equipment backpack = regularEquipmentFactory.createBackpack(name, description, pricePerDay, quantity, volume);
        storage.getEquipments().add(backpack);
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

    // Pobieranie sprzętu po ID
    public Equipment getEquipmentById(int equipmentId) {
        return storage.getEquipments().stream()
                .filter(equipment -> equipment.getId() == equipmentId)
                .findFirst()
                .orElse(null);
    }

    public boolean blockEquipment(int equipmentId) {
        Equipment equipment = getEquipmentById(equipmentId);
        if (equipment == null) {
            return false;
        }
        equipment.setAvailable(false);
        return true;
    }

    public boolean repairEquipment(int equipmentId, String repairDescription) {
        Equipment equipment = getEquipmentById(equipmentId);
        if (equipment == null) {
            return false;
        }
        equipment.setAvailable(true);
        equipment.setRepairDescription(repairDescription);
        return true;
    }


    public boolean addSportBike(String name, String description, double pricePerDay, int gearCount, int quantity) {
        for (Equipment equipment : storage.getEquipments()) {
            if (equipment.getName().equalsIgnoreCase(name)) {
                return false; // Sprzęt o podanej nazwie już istnieje
            }
        }
        Equipment bike = sportEquipmentFactory.createBike(name, description, pricePerDay, gearCount, quantity);
        storage.getEquipments().add(bike);
        return true;
    }

    public boolean addSportTent(String name, String description, double pricePerDay, int gearCount, int quantity) {
        for (Equipment equipment : storage.getEquipments()) {
            if (equipment.getName().equalsIgnoreCase(name)) {
                return false; // Sprzęt o podanej nazwie już istnieje
            }
        }
        Equipment tent = sportEquipmentFactory.createTent(name, description, pricePerDay, gearCount, quantity);
        storage.getEquipments().add(tent);
        return true;
    }

    public boolean addSportBackpack(String name, String description, double pricePerDay, int gearCount, int quantity) {
        for (Equipment equipment : storage.getEquipments()) {
            if (equipment.getName().equalsIgnoreCase(name)) {
                return false; // Sprzęt o podanej nazwie już istnieje
            }
        }
        Equipment backpack = sportEquipmentFactory.createBackpack(name, description, pricePerDay, gearCount, quantity);
        storage.getEquipments().add(backpack);
        return true;
    }

    public boolean checkNotEmptyEquipmentList() {
        return storage.getEquipments().size() > 0;
    }
}
