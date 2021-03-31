package pt.ipp.isep.dei.project1.io.ui.house;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pt.ipp.isep.dei.project1.controllers.house.GetMaximumTemperatureInRoomController;
import pt.ipp.isep.dei.project1.dto.housedto.ListOfRoomsDto;
import pt.ipp.isep.dei.project1.io.ui.utils.Validations;
import java.time.LocalDate;

@Component
public class GetMaximumTemperatureInRoomUI {

    @Autowired
    private GetMaximumTemperatureInRoomController mGetMaximumTemperatureInRoomController;


    /**
     * ReaderOfSensorReadings to select a room from one list and choose a name from him
     */

    public void run() {
        ListOfRoomsDto listOfRooms = mGetMaximumTemperatureInRoomController.getListOfRooms();
        if (Validations.printRoomListAsMenu(listOfRooms)) {

            int option2 = Validations.verifyIntegerInputsWithLimits(0, listOfRooms.getRoomDto().size() - 1);

            if (mGetMaximumTemperatureInRoomController.checkIfListOfSensorsInRoomIsEmpty(listOfRooms.getRoomDto().get(option2)))
                System.out.println("This room doesn't have any sensors yet");
            else {
                LocalDate localDate = Validations.checkLocalDateInput();
                String [] result = mGetMaximumTemperatureInRoomController.getMaximumRoomTemperature(listOfRooms.getRoomDto().get(option2), localDate);
                if ("There are no temperature sensors in this room".equals(result[0]))
                    System.out.println("There are no temperature sensors in this room");
                else if ("There are no readings yet".equals(result[0]))
                    System.out.println("There are no readings yet");
                else if("There are no readings on that date".equals(result[0]))
                    System.out.println("There are no readings on that date");
                else
                    System.out.println("The max temperature is: " + result[0] + " " + result[1]);
            }
        }
    }
}
