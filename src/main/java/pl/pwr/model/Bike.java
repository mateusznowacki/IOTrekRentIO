package pl.pwr.model;

import java.util.Objects;

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

//    @Override
//    public boolean equals(Object o) {
//        if (o == null || getClass() != o.getClass()) return false;
//        Bike bike = (Bike) o;
//        return gearCount == bike.gearCount;
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hashCode(gearCount);
//    }
}
