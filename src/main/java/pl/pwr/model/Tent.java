package pl.pwr.model.done;

public class Tent extends Equipment {
    private int capacity;

    public Tent(String name, String description, double pricePerDay, int capacity) {
        super(name, description, pricePerDay);
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
