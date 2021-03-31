package pt.ipp.isep.dei.project1.io.ui.sensor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pt.ipp.isep.dei.project1.controllers.sensor.CreateNewSensorAndAssociateToGeographicAreaController;
import pt.ipp.isep.dei.project1.dto.geographicareadto.ListGeographicAreaDto;
import pt.ipp.isep.dei.project1.dto.sensordto.ListOfSensorTypesDto;
import pt.ipp.isep.dei.project1.io.ui.utils.Validations;

import java.time.LocalDate;
import java.util.List;

@Component
public class CreateNewSensorAndAssociateToGeographicAreaUI {


    @Autowired
    private CreateNewSensorAndAssociateToGeographicAreaController mCreateNewSensorAndAssociateToGeographicAreaController;


    public void run() {
        ListOfSensorTypesDto listOfSensorType = mCreateNewSensorAndAssociateToGeographicAreaController.getListOfSensorsType();
        int option1 = -1;
        int option2 = -1;
        ListGeographicAreaDto listGeographicAreaDto = mCreateNewSensorAndAssociateToGeographicAreaController.getListOfGeographicArea();
        if (Validations.printGAListAsMenu(listGeographicAreaDto)) {
            option1 = Validations.verifyIntegerInputsWithLimits(0, listGeographicAreaDto.getListOfGADto().size() - 1);
            if (Validations.printSensorTypeListAsMenu(listOfSensorType))
                option2 = Validations.verifyIntegerInputsWithLimits(0, listOfSensorType.getSensorTypeDtos().size() - 1);
        }
        if (option1 != -1 && option2 != -1) {
            System.out.println("Introduce the name of new sensor:");
            String nameOfSensor = Validations.readString();
            System.out.println("Introduce the ID of new sensor:");
            String id = Validations.readString();
            List<Double> location = Validations.defineLocation();
            if (mCreateNewSensorAndAssociateToGeographicAreaController.checkLocationOfTheSensor(location, listGeographicAreaDto.getListOfGADto().get(option1))) {
                System.out.println("Introduce the start date of new sensor:");
                LocalDate localDate = Validations.checkLocalDateInput();
                System.out.println("Introduce the unit of new sensor:");
                String unit = Validations.readString();
                if (mCreateNewSensorAndAssociateToGeographicAreaController.createNewSensorWithLocation(id,
                        nameOfSensor, location, listOfSensorType.getSensorTypeDtos().get(option2), localDate, unit, listGeographicAreaDto.getListOfGADto().get(option1))) {
                    System.out.println("Success");
                    System.out.println("sensor: " + nameOfSensor);
                    System.out.println("Insert in Geographic Area: " + listGeographicAreaDto.getListOfGADto().get(option1).getName());
                } else {
                    System.out.println("Cannot create or add the sensor!");
                }
            } else System.out.println("This location is outside of limits of occupation area!");

        }
    }
}

