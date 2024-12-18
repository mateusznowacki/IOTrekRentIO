package pl.pwr.view;

import pl.pwr.controller.RentController;
import pl.pwr.model.Equipment;
import pl.pwr.model.User;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class EquipmentDetailsView {
    private RentController rentController;

    public EquipmentDetailsView(RentController rentController) {
        this.rentController = rentController;
    }

//    public void displayDetails(Equipment equipment) {
//        System.out.println("Szczegóły sprzętu:");
//        System.out.println(equipment);
//
//        System.out.print("Wpisz ID użytkownika, aby wynająć sprzęt (lub -1, aby anulować): ");
//        Scanner scanner = new Scanner(System.in);
//        int userId = scanner.nextInt();
//
//        if (userId == -1) return;
//
//        try {
//            System.out.print("Podaj datę rozpoczęcia wynajmu (yyyy-MM-dd): ");
//            String startDateString = scanner.next();
//            System.out.print("Podaj datę zakończenia wynajmu (yyyy-MM-dd): ");
//            String endDateString = scanner.next();
//
//            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//            Date dateStart = dateFormat.parse(startDateString);
//            Date dateEnd = dateFormat.parse(endDateString);
//
//            if (dateEnd.before(dateStart)) {
//                System.out.println("Data zakończenia musi być późniejsza niż data rozpoczęcia.");
//                return;
//            }
//               User user = new User(userId, "Jan"); // TODO: pobierz użytkownika z bazy danych
//            rentController.rentOrExtendEquipment(user, equipment, dateStart, dateEnd);
//
//        } catch (ParseException e) {
//            System.out.println("Nieprawidłowy format daty. Użyj formatu yyyy-MM-dd.");
//        }
//    }
}