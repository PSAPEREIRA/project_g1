package pt.ipp.isep.dei.project1.io.ui.device;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import pt.ipp.isep.dei.project1.controllers.device.EditConfigurationOfDeviceController;
import pt.ipp.isep.dei.project1.dto.device.ListOfDevicesDto;
import pt.ipp.isep.dei.project1.dto.housedto.ListOfRoomsDto;
import pt.ipp.isep.dei.project1.io.ui.utils.Validations;
import pt.ipp.isep.dei.project1.model.interfaces.Device;
import pt.ipp.isep.dei.project1.model.interfaces.Programmable;

@Controller
public class EditConfigurationOfDeviceUI {

    private static final String ACTION_1 = "1 to edit other spec | 0 to finish";

    @Autowired
    private EditConfigurationOfDeviceController mEditConfigurationOfDeviceController;

    public void run() {
        ListOfRoomsDto listOfRooms = mEditConfigurationOfDeviceController.getListOfRoomsDto();
        if(Validations.printRoomListAsMenu(listOfRooms)) {
            int roomInputIndex = Validations.verifyIntegerInputsWithLimits(0, listOfRooms.getRoomDto().size() - 1);
            ListOfDevicesDto listOfDevices = mEditConfigurationOfDeviceController.getListOfDevicesOfSelectedRoom(listOfRooms.getRoomDto().get(roomInputIndex));

            if (Validations.printDeviceListAsMenu(listOfDevices)) {

                int deviceInputIndex = Validations.verifyIntegerInputsWithLimits
                        (0, listOfDevices.getListOfDevices().size()-1);

                Device selectedDevice = mEditConfigurationOfDeviceController.chosenDeviceToEdit(listOfRooms.getRoomDto().get(roomInputIndex), listOfDevices.getListOfDevices().get(deviceInputIndex));

                System.out.println("Selected device = " + selectedDevice.getName());
                System.out.println("Type - " + selectedDevice.getDeviceType().getTypeCode());
                System.out.println("Device specs: " + selectedDevice.getDeviceSpecs().getAttributeNamesAndValues());
                System.out.println();

                System.out.println("Please select the spec to edit");

                showOptions(selectedDevice, roomInputIndex, deviceInputIndex);

            }
        }

    }

    public void showOptions(Device selectedDevice, int roomInputIndex, int deviceInputIndex) {
        int input = -1;
        while (input != 0) {
            showListSpecs(selectedDevice);

            int specInputIndex = Validations.verifyIntegerInputsWithLimits(0, selectedDevice.getDeviceSpecs().getAttributeNames().size());

            if (specInputIndex == selectedDevice.getDeviceSpecs().getAttributeNames().size()) {

                System.out.println("What you wanna do?");
                System.out.println("1 - Edit a program");
                System.out.println("2 - Create a new program");
                System.out.println("3 - Delete a program");

                int progOption = Validations.verifyIntegerInputsWithLimits(1, 3);

                if (progOption == 1) {
                    showListPrograms(selectedDevice);
                    int progInputEdit = Validations.verifyIntegerInputsWithLimits(1, ((Programmable) selectedDevice).getListOfPrograms().size());
                    String[] progSelected = ((Programmable) selectedDevice).getListOfPrograms().get(progInputEdit - 1);

                    System.out.println("Program Selected - " + ((Programmable) selectedDevice).getListOfPrograms().get(progInputEdit - 1)[0]);
                    showProgramFields(progSelected);


                    int fieldInput = Validations.verifyIntegerInputsWithLimits(1, ((Programmable) selectedDevice).getListOfPrograms().get(progInputEdit - 1).length);
                    System.out.println("Current value - " + ((Programmable) selectedDevice).getListOfPrograms().get(progInputEdit - 1)[fieldInput - 1]);
                    String valueInput = Validations.readString();

                    mEditConfigurationOfDeviceController.editProgramValueByIndex(roomInputIndex, deviceInputIndex, progInputEdit - 1, fieldInput - 1, valueInput);
                    System.out.println("The new value is " + valueInput);
                    System.out.println();
                    System.out.println(ACTION_1);
                    input = Validations.verifyIntegerInputsWithLimits(0, 1);
                } else if (progOption == 2) {
                    System.out.println("Please insert the name of the new program");
                    String nameProgInput = Validations.readString();
                    System.out.println("Please insert the energy consumption");
                    double energyConsInput = Validations.verifyDoubleInputsPositive();

                    ((Programmable) selectedDevice).createProgramAndAddTo(nameProgInput, energyConsInput);

                    Validations.printListOfStringArray(((Programmable) selectedDevice).getListOfPrograms());
                    System.out.println();
                    System.out.println(ACTION_1);
                    input = Validations.verifyIntegerInputsWithLimits(0, 1);


                } else if (progOption == 3) {
                    showListPrograms(selectedDevice);
                    System.out.println("Please select a program to delete");
                    int progInputDelete = Validations.verifyIntegerInputsWithLimits(1, ((Programmable) selectedDevice).getListOfPrograms().size());
                    mEditConfigurationOfDeviceController.deleteProgramByIndex(roomInputIndex, deviceInputIndex, progInputDelete-1);
                    System.out.println(ACTION_1);
                    input = Validations.verifyIntegerInputsWithLimits(0, 1);

                }

            } else {
                System.out.println("Please insert a new value for the " + selectedDevice.getDeviceSpecs().getAttributeNames().get(specInputIndex));
                System.out.println("Current value - " + selectedDevice.getDeviceSpecs().getAttributeValue(selectedDevice.getDeviceSpecs().getAttributeNames().get(specInputIndex)));
                double valueInput = Validations.verifyDoubleInputsPositive();

                mEditConfigurationOfDeviceController.editAttributeValue(selectedDevice, specInputIndex, valueInput);

                System.out.println("The new value for " + selectedDevice.getDeviceSpecs().getAttributeNames().get(specInputIndex) + " is " + valueInput);
                System.out.println(ACTION_1);
                input = Validations.verifyIntegerInputsWithLimits(0, 1);
            }
        }

    }


    public void showListSpecs(Device selectedDevice) {
        int j = 0;
        for (int i = 0; i < selectedDevice.getDeviceSpecs().getAttributeNames().size(); i++) {
            /** if (!"list of programs".equals(mDevice.getDeviceSpecs().getAttributeNames().get(i)))**/
            System.out.println(i + " - " + selectedDevice.getDeviceSpecs().getAttributeNames().get(i));
            j=i;
        }
        if (selectedDevice.checkIfIsProgrammable()) {
            System.out.println(j+1 + " - " + "list of programs");
        }
        }


    public void showListPrograms(Device selectedDevice) {

        System.out.println();
        System.out.println("Please select one of the programs to to edit");

        for (int i = 0; i < ((Programmable) selectedDevice).getListOfPrograms().size(); i++) {
            System.out.println((i + 1) + ": " + ((Programmable) selectedDevice).getListOfPrograms().get(i)[0] + " - " + ((Programmable) selectedDevice).getListOfPrograms().get(i)[1]);
        }
    }


    public void showProgramFields(String[] selectedProgram) {
        System.out.println("Select one of the fields to edit");
        for (int i = 0; i < selectedProgram.length; i++) {
            System.out.println((i + 1) + ": " + selectedProgram[i]);
        }
    }


}
