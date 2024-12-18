package pl.pwr.model;

import java.util.Calendar;
import java.util.Date;

public class Rental {
    private static int idCounter = 0;

    private int id;
    private int userId;
    private Equipment equipment;
    private Date startDate;
    private Date endDate;
    private RentalCostStrategy costStrategy;

    public Rental(Equipment equipment, Date startDate, Date endDate, RentalCostStrategy costStrategy) {
        this.id = ++idCounter;
        this.equipment = equipment;
        this.startDate = startDate;
        this.endDate = endDate;
        this.costStrategy = costStrategy;
    }


    public Rental(Equipment equipment, Date startDate, Date endDate, RentalCostStrategy costStrategy,int userId) {
        this.id = ++idCounter;
        this.equipment = equipment;
        this.startDate = startDate;
        this.endDate = endDate;
        this.costStrategy = costStrategy;
        this.userId = userId;
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
        return costStrategy.calculateCost(days, equipment.getPricePerDay());
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    // Gettery
    public int getId() {
        return id;
    }

    public Equipment getEquipment() {
        return equipment;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public String getDays() {
        return "";
    }
}
