package pl.pwr.model.done;

public class Bike extends Equipment{
    private int gearCount;

    public Bike(String name, String description, double pricePerDay, int gearCount) {
        super(name, description, pricePerDay);
        this.gearCount = gearCount;
    }

    public int getGearCount() {
        return gearCount;
    }

    @Override
    public String toString() {
        return "Bike{" +
                "name='" + getName() + '\'' +
                ", description='" + getDescription() + '\'' +
                ", pricePerDay=" + getPricePerDay() +
                ", gearCount=" + gearCount +
                '}';
    }
}
