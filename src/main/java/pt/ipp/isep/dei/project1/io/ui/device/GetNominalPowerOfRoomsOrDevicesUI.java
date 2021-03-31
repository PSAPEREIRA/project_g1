package pt.ipp.isep.dei.project1.io.ui.device;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pt.ipp.isep.dei.project1.controllers.device.GetNominalPowerOfRoomsOrDevicesController;
import pt.ipp.isep.dei.project1.dto.device.ListOfDevicesDto;
import pt.ipp.isep.dei.project1.dto.housedto.HouseGridDto;
import pt.ipp.isep.dei.project1.dto.housedto.ListOfHouseGridsDto;
import pt.ipp.isep.dei.project1.io.ui.utils.Validations;


import java.util.ArrayList;
import java.util.List;

@Component
public class GetNominalPowerOfRoomsOrDevicesUI {
    @Autowired
    private GetNominalPowerOfRoomsOrDevicesController mGetNominalPowerOfRoomsOrDevicesController;

    public void run() {
        List<Double> totalByRoom;
        List<Double> finalResult = new ArrayList<>();
        HouseGridDto houseGridDto = new HouseGridDto();
        ListOfHouseGridsDto listOfHouseGrids=mGetNominalPowerOfRoomsOrDevicesController.getListOfHouseGrids();
         if (Validations.printHouseGridListAsMenu(listOfHouseGrids)) {
            int option = Validations.verifyIntegerInputsWithLimits(0, listOfHouseGrids.getListOfHouseGridDto().size() - 1);
             houseGridDto =listOfHouseGrids.getListOfHouseGridDto().get(option);
         }
         if (houseGridDto.getRoomList().isEmpty())
            System.out.println("List Of rooms is empty");

         else {
            for (int i = 0; i < houseGridDto.getRoomList().size(); i++) {
                System.out.println("\n");
                System.out.println("Room");
                System.out.println(mGetNominalPowerOfRoomsOrDevicesController.getRoomFromHouseGrid(houseGridDto, i));
                System.out.println("--------------------------------------------------------------");
                System.out.println("Devices");
                System.out.println("1 - All devices");
                ListOfDevicesDto listOfDevicesDto = mGetNominalPowerOfRoomsOrDevicesController.getListOfDevicesDto(houseGridDto.getRoomList().get(i));

                for (int j = 0; j < listOfDevicesDto.getListOfDevices().size(); j++)
                    System.out.println((j + 2) + " - " + mGetNominalPowerOfRoomsOrDevicesController.getDeviceFromRoom(houseGridDto, i, j).getName());
                System.out.println("\n");
                totalByRoom = optionChoosing(houseGridDto, i);
                finalResult.add(mGetNominalPowerOfRoomsOrDevicesController.totalNominalPower(totalByRoom));
            }
            double result = mGetNominalPowerOfRoomsOrDevicesController.totalNominalPower(finalResult);
            System.out.println("Total nominal power of rooms/devices is: " + result + " kW");
            System.out.println("\n");
        }
    }


    public List<Double> optionChoosing(HouseGridDto houseGrid, int i) {
        List<Double> totalByRoom = new ArrayList<>();
        int option2;
        boolean test = true;
        List<Integer> optionsAlreadyChosen = new ArrayList<>();
        totalByRoom.clear();
        while (test) {
            System.out.println("Select the device | 0 continue to next room  | 1 select all devices");
            String room = houseGrid.getRoomList().get(i);
            ListOfDevicesDto listOfDevicesDto = mGetNominalPowerOfRoomsOrDevicesController.getListOfDevicesDto(room);
            option2 = Validations.verifyIntegerInputsWithLimits(0, listOfDevicesDto.getListOfDevices().size() + 1);
            if (option2 == 0) {
                break;
            } else if (option2 == 1) {
                totalByRoom.clear();
                totalByRoom.add(mGetNominalPowerOfRoomsOrDevicesController.getNominalPowerOfRoom(houseGrid, houseGrid.getRoomList().get(i)));
                return totalByRoom;
            } else {
                if (!optionsAlreadyChosen.contains(option2)) {
                    totalByRoom.add(mGetNominalPowerOfRoomsOrDevicesController.getNominalPowerOfDevice(houseGrid,houseGrid.getRoomList().get(i),listOfDevicesDto.getListOfDevices().get(option2)));
                    optionsAlreadyChosen.add(option2);
                    test=false;
                } else {
                    System.out.println("This device is already in the list");
                    test=false;
                }
            }
        }
        return totalByRoom;
    }

}

