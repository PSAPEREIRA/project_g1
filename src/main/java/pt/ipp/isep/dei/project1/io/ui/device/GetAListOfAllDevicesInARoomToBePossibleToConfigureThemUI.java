package pt.ipp.isep.dei.project1.io.ui.device;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pt.ipp.isep.dei.project1.controllers.device.GetAListOfAllDevicesInARoomToBePossibleToConfigureThemController;
import pt.ipp.isep.dei.project1.dto.device.ListOfDevicesDto;
import pt.ipp.isep.dei.project1.dto.housedto.ListOfRoomsDto;
import pt.ipp.isep.dei.project1.io.ui.utils.Validations;

@Component
public class GetAListOfAllDevicesInARoomToBePossibleToConfigureThemUI {

    @Autowired
    private GetAListOfAllDevicesInARoomToBePossibleToConfigureThemController getAListOfAllDevicesInARoomToBePossibleToConfigureThemController;

    public void run() {
        ListOfRoomsDto listOfRooms = getAListOfAllDevicesInARoomToBePossibleToConfigureThemController.getListOfRooms();
        if (printRoomListAsMenu(listOfRooms)) {
            int userRoomInput = Validations.verifyIntegerInputsWithLimits(0, listOfRooms.getRoomDto().size() - 1);
            ListOfDevicesDto listOfDevices = getAListOfAllDevicesInARoomToBePossibleToConfigureThemController.getListOfDevicesInARoom(listOfRooms.getRoomDto().get(userRoomInput));
            printDeviceListAsMenu(listOfDevices);
        }
    }

    public static boolean printRoomListAsMenu(ListOfRoomsDto inputList) {
        if (inputList.getRoomDto().isEmpty()) {
            System.out.println("No room found");
            return false;
        } else {
            System.out.println("Choose the room you want: ");
            for (int i = 0; i < inputList.getRoomDto().size(); i++)
                System.out.println(i + " - " + inputList.getRoomDto().get(i));
        }
        return true;
    }


    public static boolean printDeviceListAsMenu(ListOfDevicesDto inputList) {
        if (inputList.getListOfDevices().isEmpty()) {
            System.out.println("No rooms found");
            return false;
        } else {
            System.out.println("Choose the room you want: ");
            for (int i = 0; i < inputList.getListOfDevices().size(); i++)
                System.out.println(i + " - " + inputList.getListOfDevices().get(i));
        }
        return true;
    }

}


