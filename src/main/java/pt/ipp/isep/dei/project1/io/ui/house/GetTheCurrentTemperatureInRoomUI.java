package pt.ipp.isep.dei.project1.io.ui.house;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pt.ipp.isep.dei.project1.controllers.house.GetTheCurrentTemperatureInRoomController;
import pt.ipp.isep.dei.project1.dto.housedto.ListOfRoomsDto;
import pt.ipp.isep.dei.project1.io.ui.utils.Validations;

@Component
public class GetTheCurrentTemperatureInRoomUI {

    @Autowired
    private GetTheCurrentTemperatureInRoomController mGetTheCurrentTemperatureInRoomController;


    /**
     * ReaderOfSensorReadings to select a room from one list and choose a name from him
     */

    public void run() {

        ListOfRoomsDto listOfRooms = mGetTheCurrentTemperatureInRoomController.getListOfRooms();
        if (Validations.printRoomListAsMenu(listOfRooms)) {

            int option2 = Validations.verifyIntegerInputsWithLimits(0, listOfRooms.getRoomDto().size() - 1);

            if (mGetTheCurrentTemperatureInRoomController.checkIfListOfSensorsInRoomIsEmpty(listOfRooms.getRoomDto().get(option2)))
                System.out.println("This room doesn't have any sensors yet");
            else {
                String[] result = mGetTheCurrentTemperatureInRoomController.getCurrentRoomTemperature(listOfRooms.getRoomDto().get(option2));
                if ("There are no temperature sensors in this room".equals(result[0]))
                    System.out.println("There are no temperature sensors in this room");
                else if ("There are no readings yet".equals(result[0]))
                    System.out.println("There are no readings yet");
                else
                    System.out.println("The current temperature is: " + result[0] + " " + result[1]);
            }
        }
    }
}
