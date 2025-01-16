package controller;

import model.ModelFacade;
import model.Equipment;
import model.Rental;
import model.User;

import java.util.Date;
import java.util.List;

public class ControllerFacade {
    private final ModelFacade modelFacade;
    private final RentController rentController;
    private final UserController userController;

    public ControllerFacade(ModelFacade modelFacade, UserController userController, RentController rentController) {
        this.modelFacade = modelFacade;
        this.rentController = rentController;
        this.userController = userController;
    }

    public List<Equipment> getAvailableEquipment() {
        return modelFacade.getAvailableEquipment();
    }

    public boolean registerUser(int userId, String userName, String email, String role, String password) {
        return userController.registerUser(userId, userName, email, role, password);
    }

    public User loginUser(int userId, String password) {
        return userController.loginUser(userId, password);
    }

    public boolean logoutUser() {
        return userController.logoutUser();
    }

    public List<User> getAllUsers() {
        return userController.getAllUsers();
    }

    public List<Rental> getRentalHistory(int userId) {
        return rentController.getUserRentalHistory(userId);
    }

    public List<Rental> getUserRentalHistory() {
        return rentController.getUserRentalHistory(modelFacade.getLoggedUser().getId());
    }

    public boolean isEquipmentAvailable(int equipmentId) {
        return modelFacade.isEquipmentAvailable(equipmentId);
    }

    public double calculateRentalCost(int equipmentId, Date startDate, Date endDate) {
        return rentController.calculateRentalCost(equipmentId, startDate, endDate);
    }

    public boolean rentEquipment(int equipmentId, Date startDate, Date endDate) {
        return rentController.rentEquipment(equipmentId, startDate, endDate);
    }

    public boolean extendRental(int rentalId, int additionalDays) {
        return rentController.extendRental(rentalId, additionalDays);
    }

    public List<Rental> getUserRentalHistory(int userId) {
        return modelFacade.getUserRentalHistory(userId);
    }

    public Rental checkOverlappingRental(int equipmentId, Date startDate, Date endDate) {
        return rentController.findOverlappingRental(equipmentId, startDate, endDate);
    }


    public boolean addBike(String name, String description, double pricePerDay, int gearCount, int quantity) {
        return modelFacade.addBike(name, description, pricePerDay, gearCount, quantity);
    }

    public boolean addTent(String name, String description, double pricePerDay, int capacity, int quantity) {
        return modelFacade.addTent(name, description, pricePerDay, capacity, quantity);
    }

    public boolean addBackpack(String name, String description, double pricePerDay, int volume, int quantity) {
        return modelFacade.addBackpack(name, description, pricePerDay, volume, quantity);
    }


    public int generateUserId() {
       return userController.generateUserId();
    }

    public boolean handleRemoveEquipment(int equipmentId) {
        return modelFacade.removeEquipment(equipmentId);
    }

    public boolean handleBlockEquipment(int equipmentId) {
        return modelFacade.blockEquipment(equipmentId);
    }

    public boolean handleRepairEquipment(int equipmentId, String repairDescription) {
        return modelFacade.repairEquipment(equipmentId, repairDescription);
    }

    public boolean checkCredentials() {
        return modelFacade.checkCredentials();
    }

    public boolean removeUser(int userId) {
        return modelFacade.removeUser(userId);
    }

    public boolean editUserRole(int userId, String newRole) {
        return modelFacade.editUserRole(userId, newRole);
    }

    public boolean addSportBike(String name, String description, double pricePerDay, int gearCount, int quantity) {
        return modelFacade.addSportBike(name, description, pricePerDay, gearCount, quantity);
    }
    public boolean addSportTent(String name, String description, double pricePerDay, int gearCount, int quantity) {
        return modelFacade.addSportTent(name, description, pricePerDay, gearCount, quantity);
    }
    public boolean addSportBackpack(String name, String description, double pricePerDay, int gearCount, int quantity) {
        return modelFacade.addSportBackpack(name, description, pricePerDay, gearCount, quantity);
    }

    public List<Rental> getAllRentals() {
       return modelFacade.getAllRentals();
    }

    public boolean handleReturnEquipment(int rentalId) {
        return modelFacade.returnEquipment(rentalId);
    }

    public boolean checkNotEmptyEquipmentList() {
        return modelFacade.checkNotEmptyEquipmentList();
    }

    public int convertDate(long time, long time1) {
        return modelFacade.convertDate(time, time1);
    }
}
