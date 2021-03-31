package pt.ipp.isep.dei.project1.io.ui.house;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pt.ipp.isep.dei.project1.controllers.house.AttachDetachRoomToHouseGridController;
import pt.ipp.isep.dei.project1.dto.housedto.ListOfHouseGridsDto;
import pt.ipp.isep.dei.project1.dto.housedto.ListOfRoomsDto;
import pt.ipp.isep.dei.project1.io.ui.utils.Validations;

import java.util.List;

@Component
public class AttachDetachRoomToHouseGridUI {

    @Autowired
    private AttachDetachRoomToHouseGridController attachDetachRoomToHouseGridController;

    /**
     * ReaderOfSensorReadings to attach a room to a house grid
     */

    public void runAttach() {
        ListOfHouseGridsDto listOfHouseGrids = attachDetachRoomToHouseGridController.getListOfHouseGrids();
        if (Validations.printHouseGridListAsMenu(listOfHouseGrids)) {
            int option1 = Validations.verifyIntegerInputsWithLimits(0, listOfHouseGrids.getListOfHouseGridDto().size() - 1);
            ListOfRoomsDto listOfRooms = attachDetachRoomToHouseGridController.getListOfRooms();
            if (Validations.printRoomListAsMenu(listOfRooms)) {
                int option2 = Validations.verifyIntegerInputsWithLimits(0, listOfRooms.getRoomDto().size() - 1);
                if (attachDetachRoomToHouseGridController.attachRoomToHouseGrid(listOfHouseGrids.getListOfHouseGridDto().get(option1), listOfRooms.getRoomDto().get(option2)))
                    System.out.println("Room " + listOfRooms.getRoomDto().get(option2).getName() + " added to house Grid " + listOfHouseGrids.getListOfHouseGridDto().get(option1).getCode());
                else
                    System.out.println("Room was already added before!");
            }
        }
    }

    public void runDetach() {
        ListOfHouseGridsDto listOfHouseGrids = attachDetachRoomToHouseGridController.getListOfHouseGrids();
        if (Validations.printHouseGridListAsMenu(listOfHouseGrids)) {
            int option1 = Validations.verifyIntegerInputsWithLimits(0, listOfHouseGrids.getListOfHouseGridDto().size() - 1);
            List<String> listOfRooms = attachDetachRoomToHouseGridController.getListOfRoomsAttachedToHouseGrid(listOfHouseGrids.getListOfHouseGridDto().get(option1));
            if (Validations.printListAsMenu(listOfRooms,"room")) {
                int option2 = Validations.verifyIntegerInputsWithLimits(0, listOfRooms.size() - 1);
                String name = listOfRooms.get(option2);
                if (attachDetachRoomToHouseGridController.detachRoomFromHouseGrid(listOfHouseGrids.getListOfHouseGridDto().get(option1), listOfRooms.get(option2)))
                    System.out.println("Room " + name + " detached from house Grid " + listOfHouseGrids.getListOfHouseGridDto().get(option1).getCode());
                else
                    System.out.println("Room was already detached before");
            }
        }
    }
}