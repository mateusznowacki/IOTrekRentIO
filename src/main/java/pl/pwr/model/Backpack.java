package pl.pwr.model;

public class Backpack extends Equipment{
    private int volume;

    public Backpack(String name, String description, double pricePerDay, int volume) {
        super(name, description, pricePerDay);
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
