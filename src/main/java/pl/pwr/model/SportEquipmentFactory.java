package pl.pwr.model.done;

public class SportEquipmentFactory implements EquipmentFactory {

    @Override
    public Equipment createBike(String name, String description, double pricePerDay, int gearCount) {
        return new Bike(name + " (Sport)", description, pricePerDay * 1.5, gearCount); // Wyższa cena dla sportowego sprzętu
    }

    @Override
    public Equipment createTent(String name, String description, double pricePerDay, int capacity) {
        return new Tent(name + " (Sport)", description, pricePerDay * 1.5, capacity);
    }

    @Override
    public Equipment createBackpack(String name, String description, double pricePerDay, int volume) {
        return new Backpack(name + " (Sport)", description, pricePerDay * 1.5, volume);
    }
}
