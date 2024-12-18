package pl.pwr.model.done;

public class Equipment {
    private static int idCounter = 0;

    private int id;
    private String name;
    private String description;
    private double pricePerDay;
    private boolean available;

    public Equipment(String name, String description, double pricePerDay) {
        this.id = ++idCounter;
        this.name = name;
        this.description = description;
        this.pricePerDay = pricePerDay;
        this.available = true;
    }

    // Gettery i settery
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getPricePerDay() {
        return pricePerDay;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}
