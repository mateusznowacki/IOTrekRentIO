package model.done;

import java.util.ArrayList;
import java.util.List;

public class LocalStorage {
    // Statyczna instancja klasy - jedyna w całym systemie
    private static LocalStorage instance;
    private List<Equipment> equipments;
    private List<Rental> rentals;
    private List<User> users;
    private User loggedUser;



    // Prywatny konstruktor zapobiegający bezpośredniemu tworzeniu instancji
    private LocalStorage() {
       List<Equipment> equipments = new ArrayList<>();
       List<Rental> rentals = new ArrayList<>();
       List<User> users = new ArrayList<>();
    }

    public User getLoggedUser() {
        return loggedUser;
    }

    public void setLoggedUser(User loggedUser) {
        this.loggedUser = loggedUser;
    }

    // Metoda statyczna do uzyskiwania instancji Singletona
    public static synchronized LocalStorage getInstance() {
        if (instance == null) {
            instance = new LocalStorage();
        }
        return instance;
    }

    public List<Equipment> getEquipments() {
        return equipments;
    }

    public void setEquipments(List<Equipment> equipments) {
        this.equipments = equipments;
    }

    public List<Rental> getRentals() {
        return rentals;
    }

    public void setRentals(List<Rental> rentals) {
        this.rentals = rentals;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<User> getUsers() {
        return users;
    }


}
