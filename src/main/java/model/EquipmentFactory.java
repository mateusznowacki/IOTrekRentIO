package model;

public interface EquipmentFactory {
    Equipment createBike(String name, String description, double pricePerDay, int gearCount, int quantity);

    Equipment createTent(String name, String description, double pricePerDay, int capacity, int quantity);

    Equipment createBackpack(String name, String description, double pricePerDay, int volume, int quantity);
}