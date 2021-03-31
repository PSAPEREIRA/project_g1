package pt.ipp.isep.dei.project1.io.ui.geographicarea;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pt.ipp.isep.dei.project1.controllers.geographicarea.CreateSaveNewGeographicAreaController;
import pt.ipp.isep.dei.project1.dto.geographicareadto.ListOfGeographicAreaTypesDto;
import pt.ipp.isep.dei.project1.io.ui.utils.Validations;

import java.util.Scanner;


@Component
public class CreateSaveNewGeographicAreaUI {

    @Autowired
    private CreateSaveNewGeographicAreaController mCreateSaveNewGeographicAreaController;


    /**
     * ReaderOfSensorReadings to choose type of Geographic Area Type
     */

    public void run() {
        ListOfGeographicAreaTypesDto listOfGeographicAreasTypesDTO = mCreateSaveNewGeographicAreaController.getListOfGeographicAreasTypes();
        do {
            if (printStringList(listOfGeographicAreasTypesDTO)) {
                int option = Validations.verifyIntegerInputsWithLimits(0, listOfGeographicAreasTypesDTO.getListOfGATypesDto().size() - 1);
                Scanner read = new Scanner(System.in);
                System.out.println("Please enter the Geographic Area id:");
                String idOfGA = read.nextLine();
                System.out.println("Please enter the description of Geographic Area:");
                String description = read.nextLine();
                System.out.println("Please enter the Location of Geographic Area:");
                double[] coordinates = new double[3];
                System.out.println("Enter latitude:");
                coordinates[0] = Validations.verifyDoubleInputs();
                System.out.println("Enter longitude:");
                coordinates[1] = Validations.verifyDoubleInputs();
                System.out.println("Enter altitude:");
                coordinates[2] = Validations.verifyDoubleInputs();
                System.out.println("Enter the width:");
                double width = Validations.verifyDoubleInputsPositive();
                System.out.println("Enter the lenght:");
                double length = Validations.verifyDoubleInputsPositive();
                if (mCreateSaveNewGeographicAreaController.createNewGeographicArea(idOfGA, description, coordinates, width, length,
                        listOfGeographicAreasTypesDTO.getListOfGATypesDto().get(option))) {
                    System.out.println("Created");
                    System.out.println("Area: " + idOfGA);
                    System.out.println("Added on type: " + listOfGeographicAreasTypesDTO.getListOfGATypesDto().get(option).getName());
                } else System.out.println("Impossible to create this Geographic Area");

            } else {
                break;
            }
        }

        while (Validations.checkContinueOrBreak());
    }


    public static boolean printStringList(ListOfGeographicAreaTypesDto geographicAreaTypesDto) {
        if (geographicAreaTypesDto.getListOfGATypesDto().isEmpty()) {
            System.out.println("No types found");
            return false;
        } else {
            System.out.println("Choose the Geographic Area Type you want: ");
            for (int i = 0; i < geographicAreaTypesDto.getListOfGATypesDto().size(); i++)
                System.out.println(i + " - " + geographicAreaTypesDto.getListOfGATypesDto().get(i).getName());
        }
        return true;
    }


}



