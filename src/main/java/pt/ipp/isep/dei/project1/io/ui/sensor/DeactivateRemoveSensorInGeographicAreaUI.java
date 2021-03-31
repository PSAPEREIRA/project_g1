package pt.ipp.isep.dei.project1.io.ui.sensor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pt.ipp.isep.dei.project1.controllers.sensor.DeactivateRemoveSensorInGeographicAreaController;
import pt.ipp.isep.dei.project1.dto.geographicareadto.GeographicAreaDto;
import pt.ipp.isep.dei.project1.dto.geographicareadto.ListGeographicAreaDto;
import pt.ipp.isep.dei.project1.dto.sensordto.ListOfAreaSensorsDto;
import pt.ipp.isep.dei.project1.dto.sensordto.AreaSensorDto;
import pt.ipp.isep.dei.project1.io.ui.utils.Validations;

@Component
public class DeactivateRemoveSensorInGeographicAreaUI {

    @Autowired
    private DeactivateRemoveSensorInGeographicAreaController deactivateRemoveSensorInGeographicAreaController;
    private static final String SENSOR = "AreaSensor";

    public void runDeactivation() {
        ListGeographicAreaDto listGeographicAreaDto = deactivateRemoveSensorInGeographicAreaController.getListGeographicAreaDto();
        if (Validations.printGAListAsMenu(listGeographicAreaDto)) {
            int optionGeographicArea = Validations.verifyIntegerInputsWithLimits(0, listGeographicAreaDto.getListOfGADto().size() - 1);
            ListOfAreaSensorsDto listOfAreaSensorsDto = deactivateRemoveSensorInGeographicAreaController.getListOfSensorsActivate(listGeographicAreaDto.getListOfGADto().get(optionGeographicArea));
            if (Validations.printSensorListAsMenu(listOfAreaSensorsDto)) {
                int optionSensor = Validations.verifyIntegerInputsWithLimits(0, listOfAreaSensorsDto.getListOfAreaSensorDto().size() - 1);
                checkIfDeactivated(listGeographicAreaDto.getListOfGADto().get(optionGeographicArea), listOfAreaSensorsDto.getListOfAreaSensorDto().get(optionSensor));
            }

        }
    }

    public void checkIfDeactivated(GeographicAreaDto optionGeographicArea, AreaSensorDto optionSensor) {
        if (Validations.checkWantToDeactivate()) {
            if (deactivateRemoveSensorInGeographicAreaController.deactivateSensor(optionGeographicArea, optionSensor))
                System.out.println(SENSOR + optionSensor.getName() + " deactivate.");
            else System.out.println(SENSOR + optionSensor.getName() + " already deactivated before.");

        }
    }

    public void runRemoval() {
        ListGeographicAreaDto listGeographicAreaDto = deactivateRemoveSensorInGeographicAreaController.getListGeographicAreaDto();
        if (Validations.printGAListAsMenu(listGeographicAreaDto)) {
            int optionGeographicArea = Validations.verifyIntegerInputsWithLimits(0, listGeographicAreaDto.getListOfGADto().size() - 1);
            ListOfAreaSensorsDto listOfAreaSensorsDto = listGeographicAreaDto.getListOfGADto().get(optionGeographicArea).getListOfAreaSensorsDto();
            if (Validations.printSensorListAsMenu(listOfAreaSensorsDto)) {
                int optionSensor = Validations.verifyIntegerInputsWithLimits(0, listOfAreaSensorsDto.getListOfAreaSensorDto().size() - 1);
                removeSensorTask(listGeographicAreaDto.getListOfGADto().get(optionGeographicArea), listOfAreaSensorsDto.getListOfAreaSensorDto().get(optionSensor));
            }
        }
    }

    public void removeSensorTask(GeographicAreaDto optionGeographicArea, AreaSensorDto optionSensor) {
        if (Validations.checkWantToRemove()) {
            if (deactivateRemoveSensorInGeographicAreaController.removeSensorFromGA(optionGeographicArea,optionSensor))
                System.out.println(SENSOR + optionSensor.getName() + " was removed from " + optionGeographicArea.getName() + ".");
            else System.out.println(SENSOR + optionSensor.getName() + " not found or was already removed before.");
        }
    }
}

