package pt.ipp.isep.dei.project1.io.ui.house;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pt.ipp.isep.dei.project1.controllers.house.AddANewPowerSourceToAHouseGridController;
import pt.ipp.isep.dei.project1.dto.housedto.ListOfHouseGridsDto;
import pt.ipp.isep.dei.project1.io.ui.utils.Validations;

@Component
public class AddANewPowerSourceToAHouseGridUI {

    @Autowired
    private AddANewPowerSourceToAHouseGridController addANewPowerSourceToAHouseGridController;

    /**
     * ReaderOfSensorReadings The name, maximum power of new power source. Add a new power source to a Power Grid
     */

    public void run() {
        ListOfHouseGridsDto listOfHouseGrids = addANewPowerSourceToAHouseGridController.getListOfHouseGrids();
        if (Validations.printHouseGridListAsMenu(listOfHouseGrids)) {
            int option = Validations.verifyIntegerInputsWithLimits(0, listOfHouseGrids.getListOfHouseGridDto().size() - 1);
            System.out.println("What is the name of the new Power Source!");
            String name = Validations.readString();
            System.out.println("What is the maximum power of the new Power Source!");
            double maxPower = Validations.verifyDoubleInputsPositive();
            if (addANewPowerSourceToAHouseGridController.addPowerSourceToHouseGrid(listOfHouseGrids.getListOfHouseGridDto().get(option), name, maxPower))
                System.out.println("Power Source " + name + " added to " + listOfHouseGrids.getListOfHouseGridDto().get(option).getCode());
            else
                System.out.println("Was not possible create the Power Source!");
        }
    }
}

