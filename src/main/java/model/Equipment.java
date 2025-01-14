package model;

public class Equipment {
    private static int idCounter = 0;

    private int id;
    private final String name;
    private final String description;
    private final double pricePerDay;
    private boolean available;
    private String repairDescription;
    private int quantity; // Ilość sprzętu

    public Equipment(String name, String description, double pricePerDay, int quantity) {
        this.id = ++idCounter;
        this.name = name;
        this.description = description;
        this.pricePerDay = pricePerDay;
        this.quantity = quantity;
        this.available = quantity > 0;
    }

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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
        this.available = quantity > 0;
    }

    @Override
    public String toString() {
        return "Equipment{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", pricePerDay=" + pricePerDay +
                ", available=" + available +
                ", quantity=" + quantity +
                '}';
    }

    public void setRepairDescription(String repairDescription) {
        this.repairDescription = repairDescription;

    }

    public String getRepairDescription() {
        return repairDescription;
    }

    public void setId(int i) {
        this.id = i;
    }
}
