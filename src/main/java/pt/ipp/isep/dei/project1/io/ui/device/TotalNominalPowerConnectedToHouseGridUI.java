package pt.ipp.isep.dei.project1.io.ui.device;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pt.ipp.isep.dei.project1.controllers.device.TotalNominalPowerConnectedToHouseGridController;
import pt.ipp.isep.dei.project1.dto.housedto.ListOfHouseGridsDto;
import pt.ipp.isep.dei.project1.io.ui.utils.Validations;

@Component
public class TotalNominalPowerConnectedToHouseGridUI {

    @Autowired
    private TotalNominalPowerConnectedToHouseGridController mTotalNominalPowerConnectedToHouseGridController;

    public void run() {
        ListOfHouseGridsDto listOfHouseGrids = mTotalNominalPowerConnectedToHouseGridController.getListOfHouseGrids();
        if (Validations.printHouseGridListAsMenu(listOfHouseGrids)) {
            int option = Validations.verifyIntegerInputsWithLimits(0, listOfHouseGrids.getListOfHouseGridDto().size() - 1);
            double totalNominalPower = mTotalNominalPowerConnectedToHouseGridController.getTotalNominalPowerOfHouseGrid
                    (listOfHouseGrids.getListOfHouseGridDto().get(option));
            System.out.println("The total nominal power of house grid: " + listOfHouseGrids.getListOfHouseGridDto().get(option).getCode()
                    + " is: " + Math.round(totalNominalPower) + " kW");
            System.out.println("\n");
        }
    }
}
