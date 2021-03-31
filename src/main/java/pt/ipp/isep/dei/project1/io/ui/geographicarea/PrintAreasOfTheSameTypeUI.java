package pt.ipp.isep.dei.project1.io.ui.geographicarea;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pt.ipp.isep.dei.project1.controllers.geographicarea.PrintAreasOfTheSameTypeController;
import pt.ipp.isep.dei.project1.dto.geographicareadto.GeographicAreaDto;
import pt.ipp.isep.dei.project1.dto.geographicareadto.ListGeographicAreaDto;
import pt.ipp.isep.dei.project1.dto.geographicareadto.ListOfGeographicAreaTypesDto;
import pt.ipp.isep.dei.project1.io.ui.utils.Validations;

@Component
public class PrintAreasOfTheSameTypeUI {
    @Autowired
    private PrintAreasOfTheSameTypeController mPrintAreasOfTheSameTypeController;


    /**
     * ReaderOfSensorReadings to choose type of  Geographic Areas and show the types of Geographic Area
     */

    public void run() {
        ListOfGeographicAreaTypesDto listOfGeographicAreaTypes = mPrintAreasOfTheSameTypeController.getListOfGeographicAreaTypes();
        if (Validations.printGATypeListAsMenu(listOfGeographicAreaTypes)) {
            int option = Validations.verifyIntegerInputsWithLimits(0, listOfGeographicAreaTypes.getListOfGATypesDto().size() - 1);
            System.out.println("The areas are:");
            ListGeographicAreaDto listGeographicAreaDto = mPrintAreasOfTheSameTypeController.controlVerificationGeographicArea(listOfGeographicAreaTypes.getListOfGATypesDto().get(option));
            for(GeographicAreaDto g1 : listGeographicAreaDto.getListOfGADto()) {
                System.out.println(g1.getName());
            }
        }
    }
}