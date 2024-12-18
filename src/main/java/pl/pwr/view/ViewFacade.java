package pl.pwr.view;

import pl.pwr.model.Equipment;
import pl.pwr.model.ModelFacade;
import pl.pwr.model.User;

import java.util.List;

public class ViewFacade {
    private  ModelFacade modelFacade;
    private  EquipmentDetailsView equipmentDetailsView;
    private  AddEquipmentView addEquipmentView;
    private UserAuthView userAuthView;
    private EquipmentCatalogView equipmentCatalogView;
    private ExtendRentalView extendRentalView;
    private RentView rentView;

    public ViewFacade(UserAuthView userAuthView, EquipmentCatalogView equipmentCatalogView,
                      AddEquipmentView addEquipmentView, ExtendRentalView extendRentalView,
                      EquipmentDetailsView equipmentDetailsView, ModelFacade modelFacade, RentView rentView) {
        this.userAuthView = userAuthView;
        this.equipmentCatalogView = equipmentCatalogView;
        this.addEquipmentView = addEquipmentView;
        this.extendRentalView = extendRentalView;
        this.equipmentDetailsView = equipmentDetailsView;
        this.modelFacade = modelFacade;
        this.rentView = rentView;
    }

    public ViewFacade(UserAuthView userAuthView, EquipmentCatalogView equipmentCatalogView, ExtendRentalView extendRentalView) {
        this.userAuthView = userAuthView;
        this.equipmentCatalogView = equipmentCatalogView;
        this.extendRentalView = extendRentalView;
    }
    public void displayAddEquipmentForm() {
        addEquipmentView.displayAddEquipmentForm();
    }

    public User registerUser() {
        return userAuthView.registerUser();
    }

    public User loginUser(List<User> registeredUsers) {
        return userAuthView.loginUser(registeredUsers);
    }

    public void displayCatalogue(List<Equipment> availableEquipment) {
        equipmentCatalogView.displayCatalogue(availableEquipment);
    }

    public void displayRentalHistory(int userId) {
        extendRentalView.displayRentalHistory(userId);
    }

    public void displayAndExtendRentalHistory(int userId) {
        extendRentalView.displayAndExtendRentalHistory(userId);
    }

//    public void displayRentForm(int userId, ModelFacade modelFacade) {
//        rentView.displayRentForm(userId, modelFacade);
//
//
//    }
    public void displayRentForm(int userId, ModelFacade modelFacade, EquipmentCatalogView equipmentCatalogView) {
        rentView.displayRentForm(userId, modelFacade,equipmentCatalogView);
    }
}
