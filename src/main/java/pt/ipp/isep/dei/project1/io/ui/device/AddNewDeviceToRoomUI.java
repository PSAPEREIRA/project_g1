package pt.ipp.isep.dei.project1.io.ui.device;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pt.ipp.isep.dei.project1.controllers.device.AddNewDeviceToRoomController;
import pt.ipp.isep.dei.project1.dto.housedto.ListOfRoomsDto;
import pt.ipp.isep.dei.project1.io.ui.utils.Validations;
import pt.ipp.isep.dei.project1.model.interfaces.Device;
import pt.ipp.isep.dei.project1.model.interfaces.DeviceType;

import java.util.List;
import java.util.Scanner;

@Component
public class AddNewDeviceToRoomUI {

    @Autowired
    private AddNewDeviceToRoomController mAddNewDeviceToRoomController;


    public void run() throws Exception {

        ListOfRoomsDto listOfRooms = mAddNewDeviceToRoomController.getListOfRooms();

        if (Validations.printRoomListAsMenu(listOfRooms)) {

            int option = Validations.verifyIntegerInputsWithLimits(0, listOfRooms.getRoomDto().size() - 1);

            System.out.println("What the type of Device?");
            List<DeviceType> listOfDeviceTypes = mAddNewDeviceToRoomController.getListOfDevices();

            for (int i = 0; i < listOfDeviceTypes.size(); i++)
                System.out.println(i + " - " + listOfDeviceTypes.get(i).getType());

            int option2 = Validations.verifyIntegerInputsWithLimits(0, listOfDeviceTypes.size() - 1);

            System.out.println("Please enter the name for device");
            String name = Validations.readString();

            Device newDevice = mAddNewDeviceToRoomController.createNewDevice(listOfDeviceTypes.get(option2), name);
            List<String> attributes = newDevice.getDeviceSpecs().getAttributeNames();

            for (int i = 0; i < attributes.size(); i++) {
                double value;
                System.out.println("- " + attributes.get(i));
                value = Validations.verifyDoubleInputs();
                newDevice.getDeviceSpecs().setAttributeValue(attributes.get(i), value);
            }
            readPrograms(newDevice);

            mAddNewDeviceToRoomController.addNewDeviceToRoom(listOfRooms.getRoomDto().get(option), newDevice);
            System.out.println("New Device: " + newDevice.getName() + " Added to Room: "
                    + listOfRooms.getRoomDto().get(option).getName());
        }
    }

    public void readPrograms(Device newDevice) {
        int input = -1;
        Scanner read = new Scanner(System.in);
        if (newDevice.checkIfIsProgrammable()) {
            System.out.println("Add new Program");
            while (input != 0) {
                System.out.println("Please insert the name of the new program");
                String programName = Validations.readString();
                System.out.println("Please insert the energy consumption");
                double programEnergy = Validations.verifyDoubleInputs();
                mAddNewDeviceToRoomController.createNewProgram(newDevice, programName, programEnergy);
                System.out.println("0 to finish | other number to add another");
                input = read.nextInt();
            }
        }
    }
}














