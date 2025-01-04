package controller;

import model.ModelFacade;
import model.done.Equipment;
import model.done.Rental;
import model.done.User;

import java.util.Date;
import java.util.List;

public class ControllerFacade {
    private ModelFacade modelFacade;
    private AuthController authController;

    public ControllerFacade(ModelFacade modelFacade, AuthController authController) {
        this.modelFacade = modelFacade;
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
        return modelFacade.getUserRentalHistory(userId);
    }

    public List<Rental> getUserRentalHistory() {
        return modelFacade.getUserRentalHistory(modelFacade.getLoggedUser().getId());
    }

    public boolean isEquipmentAvailable(int equipmentId) {
        return modelFacade.isEquipmentAvailable(equipmentId);
    }

    public double calculateRentalCost(int equipmentId, Date startDate, Date endDate) {
        return modelFacade.calculateRentalCost( equipmentId,  startDate,  endDate);
    }

    public boolean rentEquipment(int equipmentId, Date startDate, Date endDate) {
        modelFacade.rentEquipment()
    }
}
