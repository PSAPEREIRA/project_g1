package pt.ipp.isep.dei.project1.io.ui.geographicarea;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pt.ipp.isep.dei.project1.controllers.geographicarea.PrintListOfGeographicAreaTypesController;
import pt.ipp.isep.dei.project1.dto.geographicareadto.ListOfGeographicAreaTypesDto;

@Component
public class PrintListOfGeographicAreaTypesUI {

    @Autowired
    private PrintListOfGeographicAreaTypesController mPrintListOfGeographicAreaTypesController;


    public void run() {
        ListOfGeographicAreaTypesDto dto = mPrintListOfGeographicAreaTypesController.getListOfGeographicAreasTypes();
        if (dto.getListOfGATypesDto().isEmpty())
            System.out.println("List of Geographic Areas Types empty");
        else {
            System.out.println("Types of Geographic Areas:");
            for (int index = 0; index < dto.getListOfGATypesDto().size(); index++) {
                System.out.println(dto.getListOfGATypesDto().get(index).getName());
            }
        }
    }
}
