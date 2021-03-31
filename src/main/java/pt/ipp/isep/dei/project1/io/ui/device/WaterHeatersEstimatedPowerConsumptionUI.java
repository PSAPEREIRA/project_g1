package pt.ipp.isep.dei.project1.io.ui.device;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pt.ipp.isep.dei.project1.controllers.device.WaterHeatersEstimatedPowerConsumptionController;
import pt.ipp.isep.dei.project1.dto.housedto.ListOfHouseGridsDto;
import pt.ipp.isep.dei.project1.dto.housedto.ListOfRoomsDto;
import pt.ipp.isep.dei.project1.io.ui.utils.Validations;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class WaterHeatersEstimatedPowerConsumptionUI {

    @Autowired
    private WaterHeatersEstimatedPowerConsumptionController mWaterHeatersEstimatedPowerConsumptionController;


    public void run() {
        ListOfHouseGridsDto houseGridList = mWaterHeatersEstimatedPowerConsumptionController.getListOfHouseGrid();
        if (Validations.printHouseGridListAsMenu(houseGridList)){
            int option = Validations.verifyIntegerInputsWithLimits(0,houseGridList.getListOfHouseGridDto().size()-1);
            ListOfRoomsDto listOfRooms = mWaterHeatersEstimatedPowerConsumptionController.getListOfRooms(houseGridList.getListOfHouseGridDto().get(option));
            if (Validations.printRoomListAsMenu(listOfRooms)) {
                if (mWaterHeatersEstimatedPowerConsumptionController.checkElectricWaterHeatersListCreation())
                    System.out.println("Don't exist any Electric Water heater");
                else {
                    System.out.println("Please enter the date for the power consumption estimation: (yyyy/mm/dd)");
                    LocalDate dateInput = Validations.checkLocalDateInput();
                    System.out.println("Please enter the volume of water to heat: (l)");
                    double volumeOfWaterToHeat = Validations.verifyDoubleInputsPositive();
                    System.out.println("Please enter the hot water temperature: (ºC)");
                    double hotWater = Validations.verifyDoubleInputs();
                    System.out.println("Please enter the cold water temperature: ");
                    double coldWater = Validations.verifyDoubleInputs();
                    List<Double> specificAttributeValues = new ArrayList<>();
                    specificAttributeValues.add(volumeOfWaterToHeat);
                    specificAttributeValues.add(hotWater);
                    specificAttributeValues.add(coldWater);
                    double result = mWaterHeatersEstimatedPowerConsumptionController.electricHeatingWaterDevicesDayConsumptionEstimation(specificAttributeValues);
                    System.out.println("The estimated Eletric Waterheaters power consumption for " + dateInput + " on the house is: " +
                            Math.round(result) + " kW");
                }
            }
        }
    }
}