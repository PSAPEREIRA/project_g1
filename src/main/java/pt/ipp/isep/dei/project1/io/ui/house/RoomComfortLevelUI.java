package pt.ipp.isep.dei.project1.io.ui.house;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pt.ipp.isep.dei.project1.controllers.house.RoomComfortLevelCTRL;
import pt.ipp.isep.dei.project1.dto.housedto.ListOfRoomsDto;
import pt.ipp.isep.dei.project1.io.ui.utils.Validations;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Component
public class RoomComfortLevelUI {

    @Autowired
    private RoomComfortLevelCTRL roomComfortLevelCTRL;

    public void runUS440(String option) {

        if (roomComfortLevelCTRL.checkGeographicAreaOfTheHouse())
            System.out.println("The Geographic Area of the house it's not configured yet!");
        else {
            if (!roomComfortLevelCTRL.checkTemperatureSensorsOfHouseArea())
                System.out.println("There are no temperature sensors installed in house area or sensors don't have readings yet!");
            else {
                us440(option);
            }
        }
    }

    private void us440(String option) {

        ListOfRoomsDto listOfRoomsDto = roomComfortLevelCTRL.getListOfRoomsDto();
        System.out.println("Please choose the room that you want?");
        Validations.printRoomListAsMenu(listOfRoomsDto);
        int roomOption = Validations.verifyIntegerInputsWithLimits(0, listOfRoomsDto.getRoomDto().size() - 1);
        if (!roomComfortLevelCTRL.checkTemperatureSensorsOfRoom(listOfRoomsDto.getRoomDto().get(roomOption)))
            System.out.println("There are no temperature sensors installed in this room!");
        else {
            System.out.println("Please choose the category that you want:");
            System.out.println("-----------------------------------------");
            System.out.println("1) Category I");
            System.out.println("2) Category II");
            System.out.println("3) Category III");
            int cat = Validations.verifyIntegerInputsWithLimits(1, 3);
            System.out.println("Please enter the initial date");
            LocalDate initialDate = Validations.checkLocalDateInput();
            System.out.println("Please enter the final date");
            LocalDate finalDate = Validations.checkLocalDateInput();
            List<LocalDateTime> result = roomComfortLevelCTRL.getInstancesWithTemperatureHigherLowerComfortLevel(
                    listOfRoomsDto.getRoomDto().get(roomOption), cat, option, initialDate, finalDate);
            if (result.equals(Collections.emptyList()))
                System.out.println("There are no instances!");
            for (LocalDateTime localDateTime : result)
                System.out.println(localDateTime.toString());
            System.out.println("-----------------------------------------");
            System.out.println("Total: " + result.size() + " instances!");
        }
    }
}