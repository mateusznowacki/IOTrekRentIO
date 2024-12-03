package pl.pwr.model;

public class RegularEquipmentFactory implements EquipmentFactory {


    @Override
    public Equipment createBike(String name, String description, double pricePerDay, int gearCount) {
        return new Bike(name, description, pricePerDay, gearCount);
    }

    @Override
    public Equipment createTent(String name, String description, double pricePerDay, int capacity) {
        return new Tent(name, description, pricePerDay, capacity);
    }

    @Override
    public Equipment createBackpack(String name, String description, double pricePerDay, int volume) {
        return new Backpack(name, description, pricePerDay, volume);
    }
}
