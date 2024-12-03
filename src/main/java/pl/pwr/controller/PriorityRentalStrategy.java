package pl.pwr.controller;

public class PriorityRentalStrategy implements RentalStrategy {

    @Override
    public void processRental(int equipmentId, int days) {
        System.out.println("Processing priority rental for equipment ID " + equipmentId + " for " + days + " days.");
        // Implementacja logiki priorytetowego wynajmu
    }
}