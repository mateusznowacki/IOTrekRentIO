package view;

import java.util.Scanner;

public class ViewFacade {
    private UserView userView;
    private EquipmentCatalogView equipmentCatalogView;
    private EquipmentView equipmentView;

    private EquipmentDetailsView equipmentDetailsView;
    private RentView rentView;

    public ViewFacade(){

    }
     public void displayRentForm(){
         rentView.displayRentForm();
     }

    public void displayMainMenu() {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\n=== MENU GŁÓWNE ===");
            System.out.println("1. Logowanie");
            System.out.println("2. Rejestracja użytkownika");
            System.out.println("3. Przeglądanie katalogu sprzętów");
            System.out.println("4. Wypożyczanie sprzętu");
            System.out.println("5. Przedłużanie wypożyczenia");
            System.out.println("6. Przeglądanie historii wypożyczeń");
            System.out.println("7. Dodawanie nowego sprzętu");

//            System.out.println("7. Monitorowanie zwrotów");
//            System.out.println("9. Usuwanie sprzętu");
//            System.out.println("10. Blokowanie sprzętu");
//            System.out.println("11. Rejestrowanie napraw i przeglądów");
//            System.out.println("12. Wyświetlanie listy użytkowników");
//            System.out.println("13. Nadawanie/edycja ról użytkowników");
//            System.out.println("14. Blokowanie kont użytkowników");
            System.out.println("0. Wyjście");

            System.out.print("Wybierz opcję: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1 -> userView.loginUser();
                case 2 -> userView.registerUser();
                case 3 -> equipmentCatalogView.displayCatalogue();
                case 4 -> rentView.displayRentForm();
                case 5 -> rentView.displayHistoryAndExtendRental();
                case 6 -> rentView.displayUserRentalHistory();
                case 7 -> equipmentView.displayAddEquipmentForm();


//                case 7 -> extendRentalView.displayAndExtendRentalHistory();
//                case 8 -> rentView.displayReturnMonitoring();
//                case 9 -> rentView.displayRentalHistory();
//                case 11 -> addEquipmentView.removeEquipment();
//                case 12 -> addEquipmentView.blockEquipment();
//                case 13 -> addEquipmentView.logMaintenance();
                case 0 -> {
                    System.out.println("Zamykanie aplikacji...");
                    running = false;
                }
                default -> System.out.println("Nieprawidłowy wybór, spróbuj ponownie.");
            }
        }
        scanner.close();
    }


    public void setUserAuthView(UserView userView) {
        this.userView = userView;
    }

    public void setEquipmentCatalogView(EquipmentCatalogView equipmentCatalogView) {
        this.equipmentCatalogView = equipmentCatalogView;
    }

    public void setAddEquipmentView(EquipmentView equipmentView) {
        this.equipmentView = equipmentView;
    }



    public void setEquipmentDetailsView(EquipmentDetailsView equipmentDetailsView) {
        this.equipmentDetailsView = equipmentDetailsView;
    }

    public void setRentView(RentView rentView) {
        this.rentView = rentView;
    }


}
