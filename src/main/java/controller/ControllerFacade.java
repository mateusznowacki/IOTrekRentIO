package controller;

import model.ModelFacade;
import model.Equipment;
import model.Rental;
import model.User;

import java.util.Date;
import java.util.List;

public class ControllerFacade {
    private ModelFacade modelFacade;
    private RentController rentController;
    private UserController userController;

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
}
