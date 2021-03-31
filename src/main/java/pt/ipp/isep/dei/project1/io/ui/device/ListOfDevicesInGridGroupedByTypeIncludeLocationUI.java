package pt.ipp.isep.dei.project1.io.ui.device;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pt.ipp.isep.dei.project1.controllers.device.ListOfDevicesInGridGroupedByTypeIncludeLocationController;
import pt.ipp.isep.dei.project1.dto.device.ListOfDevicesDto;
import pt.ipp.isep.dei.project1.dto.housedto.HouseGridDto;
import pt.ipp.isep.dei.project1.dto.housedto.ListOfHouseGridsDto;
import pt.ipp.isep.dei.project1.io.ui.utils.Validations;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class ListOfDevicesInGridGroupedByTypeIncludeLocationUI {

    @Autowired
    private ListOfDevicesInGridGroupedByTypeIncludeLocationController listOfDevicesInGridGroupedByTypeIncludeLocationController;

    public void run() {

        List<String> list = new ArrayList<>();
        ListOfHouseGridsDto listOfHouseGrid = listOfDevicesInGridGroupedByTypeIncludeLocationController.getListOfHouseGrid();
        if (Validations.printHouseGridListAsMenu(listOfHouseGrid)) {
            int option = Validations.verifyIntegerInputsWithLimits(0, listOfHouseGrid.getListOfHouseGridDto().size() - 1);
            List<String> listOfRooms = listOfHouseGrid.getListOfHouseGridDto().get(option).getRoomList();
            if (Validations.printListAsMenu(listOfRooms,"room")) {
                HouseGridDto houseGridDto = listOfHouseGrid.getListOfHouseGridDto().get(option);
                getList(list, houseGridDto);
            }
        }
        if (list.isEmpty())
            System.out.println("\n There are no devices on this House Grid!");
        else {
            Collections.sort(list);
            for (String s : list)
                System.out.println(s);
        }
    }


    public void getList(List<String> list, HouseGridDto houseGridDto) {
        for (int i = 0; i < houseGridDto.getRoomList().size(); i++) {
            ListOfDevicesDto listOfDevicesDto = listOfDevicesInGridGroupedByTypeIncludeLocationController.getListOfDevicesDto(houseGridDto.getRoomList().get(i));
            for (int j = 0; j < listOfDevicesDto.getListOfDevices().size(); j++) {
                String deviceType = listOfDevicesDto.getListOfDevices().get(j).getDeviceType().getType();
                list.add("Type: " + deviceType + " | Room: " + houseGridDto.getRoomList().get(i) + " | Device: " + listOfDevicesDto.getListOfDevices().get(j).getName());
            }
        }
    }

}