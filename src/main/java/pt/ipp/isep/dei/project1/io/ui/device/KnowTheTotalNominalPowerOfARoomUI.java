package pt.ipp.isep.dei.project1.io.ui.device;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pt.ipp.isep.dei.project1.controllers.device.KnowTheTotalNominalPowerOfARoomController;
import pt.ipp.isep.dei.project1.dto.device.ListOfDevicesDto;
import pt.ipp.isep.dei.project1.dto.housedto.ListOfRoomsDto;
import pt.ipp.isep.dei.project1.io.ui.utils.Validations;

@Component
public class KnowTheTotalNominalPowerOfARoomUI {

    @Autowired
    private KnowTheTotalNominalPowerOfARoomController mKnowTheTotalNominalPowerOfARoomController;

    public void run() {
        ListOfRoomsDto listOfRoomsDto = mKnowTheTotalNominalPowerOfARoomController.getListOfRooms();
        if (printListOfRooms(listOfRoomsDto)) {
            int option = Validations.verifyIntegerInputsWithLimits(0, listOfRoomsDto.getRoomDto().size() - 1);
            ListOfDevicesDto listOfDevicesDto = mKnowTheTotalNominalPowerOfARoomController.getListOfDevicesDtoFromRoom(listOfRoomsDto.getRoomDto().get(option));
            if (!listOfDevicesDto.getListOfDevices().isEmpty()) {
                double totalNominalPower = mKnowTheTotalNominalPowerOfARoomController.getTotalNominalPowerOfRoom(listOfRoomsDto.getRoomDto().get(option));
                System.out.println("\n" + "The total nominal power of room " + listOfRoomsDto.getRoomDto().get(option).getName() + " is " + totalNominalPower + " kW");
            }
            System.out.println("The room dont have devices yet");
        }
    }

    public static boolean printListOfRooms(ListOfRoomsDto listOfRoomsDto) {
        if (listOfRoomsDto.getRoomDto().isEmpty()) {
            System.out.println("No rooms found");
            return false;
        } else {
            System.out.println("Choose the room you want: ");
            for (int i = 0; i < listOfRoomsDto.getRoomDto().size(); i++)
                System.out.println(i + " - " + listOfRoomsDto.getRoomDto().get(i).getName());
        }
        return true;
    }

}