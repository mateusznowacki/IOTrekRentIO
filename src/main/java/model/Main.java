package model;

import controller.AuthController;
import controller.ControllerFacade;
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

        ViewFacade viewFacade = new ViewFacade();



        // Tworzenie instancji
        AuthController authController = new AuthController(modelFacade);
        ControllerFacade controllerFacade = new ControllerFacade(modelFacade, authController);


        // Tworzenie widoków
        UserAuthView userAuthView = new UserAuthView(controllerFacade);
        EquipmentCatalogView equipmentCatalogView = new EquipmentCatalogView(controllerFacade,viewFacade);
        AddEquipmentView addEquipmentView = new AddEquipmentView();
        ExtendRentalView extendRentalView = new ExtendRentalView();
        EquipmentDetailsView equipmentDetailsView = new EquipmentDetailsView();
        RentView rentView = new RentView(controllerFacade);
        
        viewFacade.setAddEquipmentView(addEquipmentView);
        viewFacade.setEquipmentCatalogView(equipmentCatalogView);
        viewFacade.setRentView(rentView);
        viewFacade.setExtendRentalView(extendRentalView);
        viewFacade.setEquipmentDetailsView(equipmentDetailsView);
        viewFacade.setUserAuthView(userAuthView);
        

        // Wywołanie menu głównego
        viewFacade.displayMainMenu();
    }
}
