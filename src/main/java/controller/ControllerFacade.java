package controller;

import model.ModelFacade;
import model.done.Equipment;
import model.done.Rental;
import model.done.User;

import java.util.Date;
import java.util.List;

public class ControllerFacade {
    private ModelFacade modelFacade;
    private RentController rentController;
    private AuthController authController;

    public ControllerFacade(ModelFacade modelFacade, AuthController authController, RentController rentController) {
        this.modelFacade = modelFacade;
        this.rentController = rentController;
        this.authController = authController;
    }

    public List<Equipment> getAvailableEquipment() {
        return modelFacade.getAvailableEquipment();
    }

    public boolean registerUser(int userId, String userName, String email, String role, String password) {
        return authController.registerUser(userId, userName, email, role, password);
    }

    public User loginUser(int userId, String password) {
        return authController.loginUser(userId, password);
    }

    public boolean logoutUser() {
        return authController.logoutUser();
    }

    public List<User> getAllUsers() {
        return authController.getAllUsers();
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
}
