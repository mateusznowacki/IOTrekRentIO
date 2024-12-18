package pl.pwr.model.done;

public interface EquipmentFactory   {
    Equipment createBike(String name, String description, double pricePerDay, int gearCount);
    Equipment createTent(String name, String description, double pricePerDay, int capacity);
    Equipment createBackpack(String name, String description, double pricePerDay, int volume);
}
