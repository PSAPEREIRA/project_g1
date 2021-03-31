package pt.ipp.isep.dei.project1.io.ui.house;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pt.ipp.isep.dei.project1.controllers.house.GetTheCurrentTemperatureInHouseAreaController;

@Component
public class GetTheCurrentTemperatureInHouseAreaUI {

    @Autowired
    private GetTheCurrentTemperatureInHouseAreaController getTheCurrentTemperatureInHouseAreaController;

    /**
     * get max temperature in house area
     */
    public void run() {
        if (getTheCurrentTemperatureInHouseAreaController.getGeographicAreaOfTheHouse() == null)
            System.out.println("The geographic area of the house it's not configured yet!");
        else {
            if (!getTheCurrentTemperatureInHouseAreaController.checkIfHaveSensorsOnHouseAreaWithTypeAndReadings())
                System.out.println("There are no sensors of temperature installed in the house area or there are no readings yet!");
            else {
                double currentTemperature = getTheCurrentTemperatureInHouseAreaController.getCurrentRoomTemperature();
                    System.out.println("The current temperature of this house is: " + currentTemperature + " ºC");
            }
        }
    }
}