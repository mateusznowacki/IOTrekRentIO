package model.done;

public class Backpack extends Equipment{
    private int volume;

    public Backpack(String name, String description, double pricePerDay, int quantity, int volume) {
        super(name, description, pricePerDay, quantity);
        this.volume = volume;
    }

    public int getVolume() {
        return volume;
    }

    @Override
    public String toString() {
        return "Backpack{" +
                "name='" + getName() + '\'' +
                ", description='" + getDescription() + '\'' +
                ", pricePerDay=" + getPricePerDay() +
                ", volume=" + volume +
                '}';
    }
}
