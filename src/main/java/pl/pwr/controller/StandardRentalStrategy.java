package pl.pwr.controller;

public class StandardRentalStrategy implements RentalStrategy {

    @Override
    public void processRental(int equipmentId, int days) {
        System.out.println("Processing standard rental for equipment ID " + equipmentId + " for " + days + " days.");
        // Implementacja logiki standardowego wynajmu
    }
}