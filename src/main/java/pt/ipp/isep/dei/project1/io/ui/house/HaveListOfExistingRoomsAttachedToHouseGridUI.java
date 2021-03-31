package pt.ipp.isep.dei.project1.io.ui.house;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pt.ipp.isep.dei.project1.controllers.house.HaveListOfExistingRoomsAttachedToHouseGridController;
import pt.ipp.isep.dei.project1.dto.housedto.ListOfHouseGridsDto;
import pt.ipp.isep.dei.project1.dto.housedto.ListOfRoomsDto;
import pt.ipp.isep.dei.project1.io.ui.utils.Validations;

@Component
public class HaveListOfExistingRoomsAttachedToHouseGridUI {

    @Autowired
    private HaveListOfExistingRoomsAttachedToHouseGridController haveListOfExistingRoomsAttachedToHouseGridController;


    /**ReaderOfSensorReadings to Select a house grid and then i want to have a list of existing rooms attached
     *
     */

    public void run() {
        ListOfHouseGridsDto listOfHouseGrids = haveListOfExistingRoomsAttachedToHouseGridController.getListOfHouseGrids();
        if (Validations.printHouseGridListAsMenu(listOfHouseGrids)){
            int optionHG = Validations.verifyIntegerInputsWithLimits(0, listOfHouseGrids.getListOfHouseGridDto().size() - 1);
            ListOfRoomsDto listOfRoomsAttachedToHouseGrid = haveListOfExistingRoomsAttachedToHouseGridController.getListOfRoomsAttachedToHouseGrid(listOfHouseGrids.getListOfHouseGridDto().get(optionHG));
            if (listOfRoomsAttachedToHouseGrid.getRoomDto().isEmpty())
                System.out.println("There isn't any room in the list");
            else {
                System.out.println("The rooms within the specified house Grid are:");
                for (int index = 0; index < listOfRoomsAttachedToHouseGrid.getRoomDto().size(); index++) {
                    System.out.println(listOfRoomsAttachedToHouseGrid.getRoomDto().get(index).getName());
                }
            }
        }
    }

}
