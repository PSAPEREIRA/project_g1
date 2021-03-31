package pt.ipp.isep.dei.project1.io.ui.house;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pt.ipp.isep.dei.project1.controllers.house.AddNewRoomToTheHouseController;
import pt.ipp.isep.dei.project1.io.ui.utils.Validations;

import java.util.ArrayList;

/**
US105 As an Administrator, I want to add a new room to the house, in order to configure
        it (name, house floor and dimensions).
*/
@Component
public class AddNewRoomToTheHouseUI {

    @Autowired
    private AddNewRoomToTheHouseController addNewRoomToTheHouseController;

    /**
     * ReaderOfSensorReadings to choose a house From the List of house and insert room details
     */

    public void run() {
        do {
            System.out.println("Insert room details:");
            System.out.println("Type room name");
            String name = Validations.readString();
            System.out.println("Type room description");
            String description = Validations.readString();
            System.out.println("Indicate room floor");
            double floor = Validations.verifyDoubleInputs();
            System.out.println("Specify room length in meters");
            double length = Validations.verifyDoubleInputsPositive();
            System.out.println("Specify room width in meters");
            double width = Validations.verifyDoubleInputsPositive();
            System.out.println("Specify room height in meters");
            double height = Validations.verifyDoubleInputsPositive();
            ArrayList<Double> dimensions = new ArrayList<>();
            dimensions.add(length);
            dimensions.add(width);
            dimensions.add(height);
            if (addNewRoomToTheHouseController.createNewRoom(description, name, floor, dimensions))
                System.out.println("New room created!");
            else
                System.out.println("Impossible to create!");

        }
        while (Validations.checkContinueOrBreak());
    }

}
