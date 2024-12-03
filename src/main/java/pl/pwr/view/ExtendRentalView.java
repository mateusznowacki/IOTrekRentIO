package pl.pwr.view;

import pl.pwr.controller.RentController;
import pl.pwr.model.Equipment;
import pl.pwr.model.User;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class ExtendRentalView {
    private RentController rentController;

    public ExtendRentalView(RentController rentController) {
        this.rentController = rentController;
    }

    public void displayExtendRentalForm(int userId) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Podaj ID sprzętu, którego wynajem chcesz przedłużyć: ");
        int equipmentId = scanner.nextInt();

        Equipment equipment = rentController.getModelFacade().getEquipmentById(equipmentId);

        if (equipment == null) {
            System.out.println("Nie znaleziono sprzętu o podanym ID.");
            return;
        }


        try {
            System.out.print("Podaj nową datę zakończenia wynajmu (yyyy-MM-dd): ");
            String endDateString = scanner.next();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date newDateEnd = dateFormat.parse(endDateString);

            User user =  new User(userId, "Jan");

            rentController.rentOrExtendEquipment(user, equipment, null, newDateEnd);

        } catch (ParseException e) {
            System.out.println("Nieprawidłowy format daty. Użyj formatu yyyy-MM-dd.");
        }
    }
}