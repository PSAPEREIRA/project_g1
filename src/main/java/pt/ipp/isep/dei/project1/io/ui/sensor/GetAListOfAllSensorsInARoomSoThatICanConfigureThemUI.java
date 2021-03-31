package pt.ipp.isep.dei.project1.io.ui.sensor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pt.ipp.isep.dei.project1.controllers.sensor.GetAListOfAllSensorsInARoomSoThatICanConfigureThemController;
import pt.ipp.isep.dei.project1.dto.housedto.ListOfRoomsDto;
import pt.ipp.isep.dei.project1.dto.sensordto.ListOfRoomSensorsDto;
import pt.ipp.isep.dei.project1.io.ui.utils.Validations;

@Component
public class GetAListOfAllSensorsInARoomSoThatICanConfigureThemUI {

    @Autowired
    private GetAListOfAllSensorsInARoomSoThatICanConfigureThemController getAListOfAllSensorsInARoomSoThatICanConfigureThemController;

    public void run() {

        ListOfRoomsDto listOfRooms = getAListOfAllSensorsInARoomSoThatICanConfigureThemController.getListOfRoomsInAHouse();
        if(Validations.printRoomListAsMenu(listOfRooms)) {
            int userRoomInput = Validations.verifyIntegerInputsWithLimits(0, listOfRooms.getRoomDto().size() - 1);

            ListOfRoomSensorsDto ls = getAListOfAllSensorsInARoomSoThatICanConfigureThemController.getListOfSensorsInARoom(listOfRooms.getRoomDto().get(userRoomInput));

            Validations.printRoomSensorListAsMenu(ls);
        }
    }
}


