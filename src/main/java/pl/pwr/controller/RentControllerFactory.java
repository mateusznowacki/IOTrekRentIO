package pl.pwr.controller;

import pl.pwr.model.ModelFacade;

public class RentControllerFactory {

    public RentController createController(RentalStrategy strategy, ModelFacade modelFacade) {
        return new RentController(strategy, modelFacade);
    }
}