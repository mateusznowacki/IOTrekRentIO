package pl.pwr.view;

import pl.pwr.model.ModelFacade;
import pl.pwr.model.Rental;

import java.util.List;
import java.util.Scanner;

public class ExtendRentalView {
    private ModelFacade modelFacade;

    public ExtendRentalView(ModelFacade modelFacade) {
        this.modelFacade = modelFacade;
    }

    public void displayRentalHistory(int userId) {
        List<Rental> rentals = modelFacade.getUserRentalHistory(userId);

        if (rentals.isEmpty()) {
            System.out.println("Brak historii wypożyczeń dla tego użytkownika.");
        } else {
            System.out.println("\nHistoria wypożyczeń:");
            for (Rental rental : rentals) {
                System.out.println("Sprzęt: " + rental.getEquipment().getName() +
                        ", Data rozpoczęcia: " + rental.getStartDate() +
                        ", Data zakończenia: " + rental.getEndDate() +
                        ", Koszt: " + rental.calculateCost());
            }
        }
    }

    public void displayAndExtendRentalHistory(int userId) {
        displayRentalHistory(userId);

        List<Rental> rentals = modelFacade.getUserRentalHistory(userId);
        if (!rentals.isEmpty()) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("\nWybierz numer wypożyczenia, które chcesz przedłużyć (0 - anuluj): ");
            int choice = scanner.nextInt();

            if (choice > 0 && choice <= rentals.size()) {
                Rental selectedRental = rentals.get(choice - 1);
                System.out.print("Podaj liczbę dni, o którą chcesz przedłużyć wypożyczenie: ");
                int additionalDays = scanner.nextInt();
                modelFacade.extendRental(selectedRental, additionalDays);
                System.out.println("Wypożyczenie zostało przedłużone. Nowa data zakończenia: " + selectedRental.getEndDate());
            } else {
                System.out.println("Anulowano przedłużenie.");
            }
        }
    }
}
