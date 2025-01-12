package model;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class ModelFacade {

    private final LocalStorage storage;
    private final EquipmentFactory sportEquipmentFactory;
    private final EquipmentFactory regularEquipmentFactory;
    private final RentalFactory regularRentalFactory;
    private final RentalFactory discountedRentalFactory;
    private final EquipmentService equipmentService;
    private final UserService userService;
    private final RentalService rentalService;

    public ModelFacade(EquipmentFactory sportEquipmentFactory, EquipmentFactory regularEquipmentFactory,
                       RentalFactory regularRentalFactory, RentalFactory discountedRentalFactory) {
        this.sportEquipmentFactory = sportEquipmentFactory;
        this.regularEquipmentFactory = regularEquipmentFactory;
        this.regularRentalFactory = regularRentalFactory;
        this.discountedRentalFactory = discountedRentalFactory;
        this.storage = new LocalStorage();
        this.equipmentService = new EquipmentService(storage, regularEquipmentFactory, sportEquipmentFactory);
        this.userService = new UserService(storage);
        this.rentalService = new RentalService(storage,userService, regularRentalFactory, discountedRentalFactory, equipmentService);
    }


    public List<Equipment> getAvailableEquipment() {
       return equipmentService.getAvailableEquipment();
    }


    public boolean addBike(String name, String description, double pricePerDay, int gearCount, int quantity) {
        return equipmentService.addBike(name, description, pricePerDay, gearCount, quantity);
    }

    public boolean addTent(String name, String description, double pricePerDay, int capacity, int quantity) {
        return equipmentService.addTent(name, description, pricePerDay, capacity, quantity);
    }

    public boolean addBackpack(String name, String description, double pricePerDay, int volume, int quantity) {
        return equipmentService.addBackpack(name, description, pricePerDay, volume, quantity);
    }


    // Logowanie użytkownika
    public User loginUser(String email) {
       return userService.loginUser(email);
    }

    public boolean rentEquipment(int equipmentId, Date startDate, Date endDate) {
       return rentalService.rentEquipment(equipmentId, startDate, endDate);
    }


    // Przeglądanie katalogu sprzętu
    public List<Equipment> getEquipmentCatalog() {
        return equipmentService.getEquipmentCatalog();
    }

    // Dodawanie nowego sprzętu
    public String addEquipment(String name, String description, double pricePerDay, int quantity) {
        return equipmentService.addEquipment(name, description, pricePerDay, quantity);
    }

    // Usuwanie sprzętu
    public boolean removeEquipment(int equipmentId) {
        return equipmentService.removeEquipment(equipmentId);
    }

    // Przeglądanie historii wypożyczeń użytkownika
    public List<Rental> getUserRentalHistory(int userId) {
        return rentalService.getUserRentalHistory(userId);
    }

    // Pobieranie sprzętu po ID
    public Equipment getEquipmentById(int equipmentId) {
        return equipmentService.getEquipmentById(equipmentId);
    }

    public User getUserById(int userId) {
        return userService.getUserById(userId);
    }


    public List<User> getUsers() {
        return userService.getUsers();
    }


    public User getLoggedUser() {
        return userService.getLoggedUser();
    }

    public boolean isEquipmentAvailable(int equipmentId) {
        return rentalService.isEquipmentAvailable(equipmentId);
    }

    public double calculateRentalCost(int equipmentId, Date startDate, Date endDate) {
       return rentalService.calculateRentalCost(equipmentId, startDate, endDate);
    }


    public boolean checkAndExtendRental(int rentalId, int additionalDays) {
        return rentalService.checkAndExtendRental(rentalId, additionalDays);
    }


    private boolean checkCollision(Rental rental, Date newEndDate) {
        return rentalService.checkCollision(rental, newEndDate);
    }

    public Rental getRentalById(int rentalId) {
        return rentalService.getRentalById(rentalId);
    }

    public void setLoggedUser(User user) {
        userService.setLoggedUser(user);
    }


    public void addUser(User newUser) {
        userService.addUser(newUser);
    }

    public int generateUserId() {
        return userService.generateUserId();
    }

    public boolean blockEquipment(int equipmentId) {

        return equipmentService.blockEquipment(equipmentId);
    }

    public boolean repairEquipment(int equipmentId, String repairDescription) {
        return equipmentService.repairEquipment(equipmentId, repairDescription);
    }

    public boolean checkCredentials() {
        return userService.checkCredentials();
    }

    public boolean removeUser(int userId) {
        return userService.removeUser(userId);
    }

    public boolean editUserRole(int userId, String newRole) {
        return userService.editUserRole(userId, newRole);
    }

    public boolean addSportBike(String name, String description, double pricePerDay, int gearCount, int quantity) {
        return equipmentService.addSportBike(name, description, pricePerDay, gearCount, quantity);
    }
    public boolean addSportTent(String name, String description, double pricePerDay, int gearCount, int quantity) {
        return equipmentService.addSportTent(name, description, pricePerDay, gearCount, quantity);
    }
    public boolean addSportBackpack(String name, String description, double pricePerDay, int gearCount, int quantity) {
        return equipmentService.addSportBackpack(name, description, pricePerDay, gearCount, quantity);
    }
}
