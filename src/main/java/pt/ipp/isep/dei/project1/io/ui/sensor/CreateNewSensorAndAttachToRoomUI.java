package pt.ipp.isep.dei.project1.io.ui.sensor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pt.ipp.isep.dei.project1.controllers.sensor.CreateNewSensorAndAttachToRoomController;
import pt.ipp.isep.dei.project1.dto.housedto.ListOfRoomsDto;
import pt.ipp.isep.dei.project1.dto.sensordto.ListOfSensorTypesDto;
import pt.ipp.isep.dei.project1.io.ui.utils.Validations;

import java.time.LocalDate;

@Component
public class CreateNewSensorAndAttachToRoomUI {

    @Autowired
    private CreateNewSensorAndAttachToRoomController mCreateNewSensorAndAttachToRoomController;

    public void run() {
        int option1 = 0;
        int option2 = 0;
        ListOfRoomsDto listOfRooms = mCreateNewSensorAndAttachToRoomController.getListOfRooms();
        ListOfSensorTypesDto listOfSensorTypes = mCreateNewSensorAndAttachToRoomController.getListOfSensorsType();
        if (Validations.printRoomListAsMenu(listOfRooms))
            option1 = Validations.verifyIntegerInputsWithLimits(0, listOfRooms.getRoomDto().size() - 1);
        if (Validations.printSensorTypeListAsMenu(listOfSensorTypes))
            option2 = Validations.verifyIntegerInputsWithLimits(0, listOfSensorTypes.getSensorTypeDtos().size() - 1);
        System.out.println("Introduce the name of new sensor:");
        String nameOfSensor = Validations.readString();
        System.out.println("Introduce the ID of new sensor:");
        String id = Validations.readString();
        System.out.println("Introduce the start date of new sensor:");
        LocalDate localDate = Validations.checkLocalDateInput();
        System.out.println("Introduce the unit of new sensor:");
        String unit = Validations.readString();
        if (mCreateNewSensorAndAttachToRoomController.createNewSensor(id,listOfRooms.getRoomDto().get(option1), nameOfSensor, listOfSensorTypes.getSensorTypeDtos()
                .get(option2), localDate, unit)) {
            System.out.println("Success");
            System.out.println("sensor: " + nameOfSensor);
            System.out.println("Insert in Room: " + listOfRooms.getRoomDto().get(option1).getName());
        } else System.out.println("Cannot create or add the sensor!");
    }
}

