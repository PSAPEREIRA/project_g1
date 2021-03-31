package pt.ipp.isep.dei.project1.io.ui.house;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pt.ipp.isep.dei.project1.controllers.house.HaveAListOfExistingRoomsController;
import pt.ipp.isep.dei.project1.dto.housedto.ListOfRoomsDto;
import pt.ipp.isep.dei.project1.io.ui.utils.Validations;

import java.util.ArrayList;

@Component
public class HaveAListOfExistingRoomsUI {

    @Autowired
    private HaveAListOfExistingRoomsController haveAListOfExistingRoomsController;

    /**
     * ReaderOfSensorReadings to select a room from one list and choose a name from him
     */

    public void run() {
        ListOfRoomsDto listOfRooms = haveAListOfExistingRoomsController.getListOfRooms();
        if (Validations.printRoomListAsMenu(listOfRooms)) {

            /** SELECT ROOM FROM LIST
             *
             */
            int option2 = Validations.verifyIntegerInputsWithLimits(0, listOfRooms.getRoomDto().size() - 1);

            /** NAME
             *
             */

            System.out.println("NAME | Set/Change room name:");
            String nameRoom = Validations.readString();

            haveAListOfExistingRoomsController.setNewRoomAttributeName
                    (listOfRooms.getRoomDto().get(option2), nameRoom);

            /** FLOOR
             *
             */

            System.out.println("FLOOR | Set the room floor:");
            double houseFloor = Validations.verifyDoubleInputs();

            haveAListOfExistingRoomsController.setNewRoomAttributeFloor
                    (listOfRooms.getRoomDto().get(option2), houseFloor);


            /** WIDTH
             *
             */

            System.out.println("DIMENSIONS - WIDTH | Set the width dimensions:");
            ArrayList<Double> houseDimension = new ArrayList<>();
            houseDimension.add(Validations.verifyDoubleInputsPositive());

            /**LENGTH
             *
             */

            System.out.println("DIMENSIONS - LENGTH | Set the Length dimensions:");
            houseDimension.add(Validations.verifyDoubleInputsPositive());

            haveAListOfExistingRoomsController.setNewRoomAttributeDimensions
                    (listOfRooms.getRoomDto().get(option2), houseDimension);

            /**HEIGHT
             *
             */
            System.out.println("DIMENSIONS - HEIGHT | Set the Length dimensions:");
            houseDimension.add(Validations.verifyDoubleInputsPositive());


            haveAListOfExistingRoomsController.setNewRoomAttributeDimensions
                    (listOfRooms.getRoomDto().get(option2), houseDimension);

            /** Result from Options
             *
             */

            System.out.println("OPTIONS RESULT | Room Definitions:");

            System.out.println("NAME: " + nameRoom);

            System.out.println("FLOOR: " + houseFloor);

            System.out.println("WIDTH: " + houseDimension.get(0)
                    + " LENGHT: " + houseDimension.get(1)
                    + " HEIGHT: " + houseDimension.get(2));
        }
    }

}
