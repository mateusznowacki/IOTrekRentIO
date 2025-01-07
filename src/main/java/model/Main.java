package model;

import controller.UserController;
import controller.ControllerFacade;
import controller.RentController;
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
        UserController userController = new UserController(modelFacade);
        RentController rentController = new RentController(modelFacade);
        ControllerFacade controllerFacade = new ControllerFacade(modelFacade, userController, rentController);


        // Tworzenie widoków
        UserView userView = new UserView(controllerFacade, viewFacade);
        EquipmentCatalogView equipmentCatalogView = new EquipmentCatalogView(controllerFacade, viewFacade);
        EquipmentView equipmentView = new EquipmentView(controllerFacade, viewFacade);
        //ExtendRentalView extendRentalView = new ExtendRentalView(controllerFacade);
        EquipmentDetailsView equipmentDetailsView = new EquipmentDetailsView();
        RentView rentView = new RentView(controllerFacade);

        viewFacade.setAddEquipmentView(equipmentView);
        viewFacade.setEquipmentCatalogView(equipmentCatalogView);
        viewFacade.setRentView(rentView);
       // viewFacade.setExtendRentalView(extendRentalView);
        viewFacade.setEquipmentDetailsView(equipmentDetailsView);
        viewFacade.setUserAuthView(userView);


        // Wywołanie menu głównego
        viewFacade.displayMainMenu();
    }
}
