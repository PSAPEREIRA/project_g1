package pt.ipp.isep.dei.project1.io.ui.sensor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pt.ipp.isep.dei.project1.controllers.sensor.SpecifyCharacteristicsThatSensorsCanReadController;
import pt.ipp.isep.dei.project1.dto.sensordto.ListOfSensorTypesDto;
import pt.ipp.isep.dei.project1.io.ui.utils.Validations;

@Component
public class SpecifyCharacteristicsThatSensorsCanReadUI {

    @Autowired
    private SpecifyCharacteristicsThatSensorsCanReadController mSpecifyCharacteristicsThatSensorsCanReadController;


    public void run() {
        System.out.println("Please enter the new type of sensor you want to add");
        String name = Validations.readString();
        if (mSpecifyCharacteristicsThatSensorsCanReadController.specifyTheCharacteristics(name)) {
            System.out.println("Added!");
            System.out.println("The types defined are:");
            ListOfSensorTypesDto listOfSensorTypesDto = mSpecifyCharacteristicsThatSensorsCanReadController.getListSensorTypes();
            for (int i = 0; i < listOfSensorTypesDto.getSensorTypeDtos().size(); i++)
                System.out.println(listOfSensorTypesDto.getSensorTypeDtos().get(i).getDesignation());
        } else System.out.println("Cannot be added, already exists");
    }
}
