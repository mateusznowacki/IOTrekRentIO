package model.done;

import java.util.Calendar;
import java.util.Date;

public class Rental {
    private static int idCounter = 0;

    private int id;
    private int userId;
    private double cost;
    private Equipment equipment;
    private Date startDate;
    private Date endDate;
    private RentalCostStrategy costStrategy;


    public Rental(int id, int userId, Equipment equipment, Date startDate, Date endDate, RentalCostStrategy costStrategy) {
        this.id = id;
        this.userId = userId;
        this.equipment = equipment;
        this.startDate = startDate;
        this.endDate = endDate;
        this.costStrategy = costStrategy;
    }

    public void extendRental(int additionalDays) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(endDate);
        calendar.add(Calendar.DAY_OF_MONTH, additionalDays);
        endDate = calendar.getTime();
    }


    public double calculateCost() {
        long diffInMillies = endDate.getTime() - startDate.getTime();
        int days = (int) (diffInMillies / (1000 * 60 * 60 * 24));
        cost = costStrategy.calculateCost(days, equipment.getPricePerDay());
        return cost;
    }


    public static int getIdCounter() {
        return idCounter;
    }

    public static void setIdCounter(int idCounter) {
        Rental.idCounter = idCounter;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Equipment getEquipment() {
        return equipment;
    }

    public void setEquipment(Equipment equipment) {
        this.equipment = equipment;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public RentalCostStrategy getCostStrategy() {
        return costStrategy;
    }

    public void setCostStrategy(RentalCostStrategy costStrategy) {
        this.costStrategy = costStrategy;
    }

    public double getCost() {
        return cost;
    }
}
