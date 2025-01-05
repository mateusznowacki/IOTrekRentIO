package view;

import controller.RentController;
import model.ModelFacade;

public class EquipmentDetailsView {
    private RentController rentController;

    public EquipmentDetailsView(ModelFacade modelFacade) {
        this.rentController = new RentController(modelFacade);
    }

    public EquipmentDetailsView() {

    }


}