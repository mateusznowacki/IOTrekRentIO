package controller;

import model.ModelFacade;
import model.done.*;
import view.*;

public class Main {

    public static void main(String[] args) {
        startApp();
    }

    public static void startApp() {
        // Tworzenie instancji ModelFacade
        ModelFacade modelFacade = new ModelFacade(
                new SportEquipmentFactory(),
                new RegularEquipmentFactory(),
                new RegularRentalFactory(),
                new RegularRentalFactory()
        );

        // Tworzenie instancji AuthController


        // Tworzenie widoków
        UserAuthView userAuthView = new UserAuthView();
        EquipmentCatalogView equipmentCatalogView = new EquipmentCatalogView();
        AddEquipmentView addEquipmentView = new AddEquipmentView();
        ExtendRentalView extendRentalView = new ExtendRentalView();
        EquipmentDetailsView equipmentDetailsView = new EquipmentDetailsView();
        RentView rentView = new RentView();
         AuthController authController = new AuthController();

        // Tworzenie ViewFacade z przekazaniem zależności
        ViewFacade viewFacade = new ViewFacade(
                userAuthView,
                equipmentCatalogView,
                addEquipmentView,
                extendRentalView,
                equipmentDetailsView,
                rentView,
                modelFacade,
                authController
        );

        // Wywołanie menu głównego
        viewFacade.displayMainMenu();
    }
}
