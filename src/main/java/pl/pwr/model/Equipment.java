package pl.pwr.model;

public class Equipment {
    private static int idCounter = 0;

    private int id;
    private String name;
    private String description;
    private double pricePerDay;
    private boolean available;
    private int quantity; // Ilość sprzętu

    // Konstruktor
    public Equipment(String name, String description, double pricePerDay) {
        this.id = ++idCounter;
        this.name = name;
        this.description = description;
        this.pricePerDay = pricePerDay;
        this.quantity = quantity;
        this.available = quantity > 0;
    }

    public static int getIdCounter() {
        return idCounter;
    }

    public static void setIdCounter(int idCounter) {
        Equipment.idCounter = idCounter;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPricePerDay() {
        return pricePerDay;
    }

    public void setPricePerDay(double pricePerDay) {
        this.pricePerDay = pricePerDay;
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
    // Walidacja danych sprzętu
    public boolean isValid() {
        if (name == null || name.isEmpty()) {
            return false;
        }
        if (description == null || description.isEmpty()) {
            return false;
        }
        if (pricePerDay <= 0) {
            return false;
        }
        if (quantity <= 0) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Equipment{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", pricePerDay=" + pricePerDay +
                ", quantity=" + quantity +
                ", available=" + available +
                '}';
    }
}













