package model;

public class RegularEquipmentFactory implements EquipmentFactory {
    @Override
    public Equipment createBike(String name, String description, double pricePerDay, int gearCount, int quantity) {
        return new Bike(name,description,pricePerDay, quantity,gearCount);
    }

    @Override
    public Equipment createTent(String name, String description, double pricePerDay, int capacity, int quantity) {
        return new Tent(name,description,pricePerDay,capacity,quantity);
    }

    @Override
    public Equipment createBackpack(String name, String description, double pricePerDay, int volume, int quantity) {
     return  new Backpack(name ,description,pricePerDay,volume,quantity);
    }
}

