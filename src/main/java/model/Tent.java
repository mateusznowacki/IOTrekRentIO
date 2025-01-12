package model;

public class Tent extends Equipment {
    private final int capacity;

    public Tent(String name, String description, double pricePerDay, int quantity, int capacity) {
        super(name, description, pricePerDay, quantity);
        this.capacity = capacity;
    }

    public int getCapacity() {
        return capacity;
    }

    @Override
    public String toString() {
        return "Tent{" +
                "name='" + getName() + '\'' +
                ", description='" + getDescription() + '\'' +
                ", pricePerDay=" + getPricePerDay() +
                ", capacity=" + capacity +
                '}';
    }
}
