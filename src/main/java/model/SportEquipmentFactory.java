package model;

public class SportEquipmentFactory implements EquipmentFactory {

    @Override
    public Equipment createBike(String name, String description, double pricePerDay, int gearCount, int quantity) {
        return new Bike(name, description, pricePerDay * 1.2, gearCount, quantity);
    }

    @Override
    public Equipment createTent(String name, String description, double pricePerDay, int capacity, int quantity) {
      return  new Tent(name, description, pricePerDay * 1.2, capacity, quantity);
    }

    @Override
    public Equipment createBackpack(String name, String description, double pricePerDay, int volume, int quantity) {
       return new Backpack(name, description, pricePerDay * 1.2, volume, quantity);
    }
}
