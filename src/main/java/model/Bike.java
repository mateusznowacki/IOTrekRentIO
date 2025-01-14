package model;

public class Bike extends Equipment {
    private final int gearCount;

    public Bike(String name, String description, double pricePerDay, int quantity, int gearCount) {
        super(name, description, pricePerDay, quantity);
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
