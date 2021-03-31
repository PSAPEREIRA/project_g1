package pt.ipp.isep.dei.project1.io.ui.geographicarea;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pt.ipp.isep.dei.project1.controllers.geographicarea.PrintInfoIfOneGeographicAreaIsInOtherController;
import pt.ipp.isep.dei.project1.dto.geographicareadto.ListGeographicAreaDto;
import pt.ipp.isep.dei.project1.io.ui.utils.Validations;

@Component
public class PrintInfoIfOneGeographicAreaIsInOtherUI {
    @Autowired
    private PrintInfoIfOneGeographicAreaIsInOtherController mPrintInfoIfOneGeographicAreaIsInOtherController;


    /**ReaderOfSensorReadings to verify if one Geographic Area to be inside in another Geographic Area
     *
     */

    public void run() {
        ListGeographicAreaDto listGeographicAreaDto = mPrintInfoIfOneGeographicAreaIsInOtherController.getListOfGeographicAreas();
        if (Validations.printGAListAsMenu(listGeographicAreaDto)){
            int option = Validations.verifyIntegerInputsWithLimits
                    (0, listGeographicAreaDto.getListOfGADto().size() - 1);

            if (listGeographicAreaDto.getListOfGADto().get(option).getListInsideOf().getListOfGADto().isEmpty()) {
                System.out.println("Area is not included on other area");
                System.out.println();
            } else {
                System.out.println("the Area you choose " + " is inside:");
                for (int i = 0; i < listGeographicAreaDto.getListOfGADto().get(option).getListInsideOf().getListOfGADto().size(); i++)
                    System.out.println("Name of GA - " + listGeographicAreaDto.getListOfGADto().get(option).
                            getListInsideOf().getListOfGADto().get(i).getName());
                System.out.println();
            }

        }
    }
}
