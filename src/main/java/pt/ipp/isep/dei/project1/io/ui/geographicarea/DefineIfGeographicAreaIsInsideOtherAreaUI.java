package pt.ipp.isep.dei.project1.io.ui.geographicarea;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pt.ipp.isep.dei.project1.controllers.geographicarea.DefineIfGeographicAreaIsInsideOtherAreaController;
import pt.ipp.isep.dei.project1.dto.geographicareadto.ListGeographicAreaDto;
import pt.ipp.isep.dei.project1.io.ui.utils.Validations;
@Component
public class DefineIfGeographicAreaIsInsideOtherAreaUI {

    @Autowired
    private DefineIfGeographicAreaIsInsideOtherAreaController mDefineIfGeographicAreaIsInsideOtherAreaController;


    public void run() {
        do {
            ListGeographicAreaDto listGeographicAreaDto = mDefineIfGeographicAreaIsInsideOtherAreaController.getListOfGeographicAreas();
            if (listGeographicAreaDto.getListOfGADto().size() < 2)
                System.out.println("The list of Geographic Area need to have 2 Geographic Areas");
            else {
                System.out.println("Choose the first Area:");
                for (int i = 0; i < listGeographicAreaDto.getListOfGADto().size(); i++)
                    System.out.println(i + " - " + listGeographicAreaDto.getListOfGADto().get(i).getName());

                int option1 = Validations.verifyIntegerInputsWithLimits(0, listGeographicAreaDto.getListOfGADto().size() - 1);

                int option2 = getAreaToInclude(option1, listGeographicAreaDto);

                if (mDefineIfGeographicAreaIsInsideOtherAreaController.geographicAreaIsInsideOfOtherGeographicArea(
                        listGeographicAreaDto.getListOfGADto().get(option1), listGeographicAreaDto.getListOfGADto().get(option2))) {
                    System.out.println("Added");
                    System.out.println("Area: " + listGeographicAreaDto.getListOfGADto().get(option1).getName());
                    System.out.println("Inserted on Area: " + listGeographicAreaDto.getListOfGADto().get(option2).getName());
                } else
                    System.out.println("Cannot add!");

            }
        }
        while (Validations.checkContinueOrBreak());
    }


    public int getAreaToInclude(int option1, ListGeographicAreaDto listGeographicAreaDto) {
        System.out.println("Choose the area to include:");
        for (int i = 0; i < listGeographicAreaDto.getListOfGADto().size(); i++)
            System.out.println(i + " - " + listGeographicAreaDto.getListOfGADto().get(i).getName());

        int option2;

        do {
            option2 = Validations.verifyIntegerInputsWithLimits(0, listGeographicAreaDto.getListOfGADto().size() - 1);
            if (option2 == option1)
                System.out.println("Cannot add the area on the same area:");
        } while (option2 == option1);
        return option2;
    }
}
