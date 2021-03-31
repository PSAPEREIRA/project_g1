package pt.ipp.isep.dei.project1.io.ui.house;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pt.ipp.isep.dei.project1.controllers.house.ConfigureTheLocationOfTheHouseController;
import pt.ipp.isep.dei.project1.dto.geographicareadto.ListGeographicAreaDto;
import pt.ipp.isep.dei.project1.io.ui.utils.Validations;
import java.util.List;


/**
 * US101 As an Administrator, I want to configure the location of the house.
 */

@Component
public class ConfigureTheLocationOfTheHouseUI {

    @Autowired
    private ConfigureTheLocationOfTheHouseController mConfigureTheLocationOfTheHouseController;


    public void run() {

        ListGeographicAreaDto listOfGeographicAreasDto = mConfigureTheLocationOfTheHouseController.getListOfGeographicAreas();
        List<Double> location;
        if (Validations.printGAListAsMenu(listOfGeographicAreasDto)) {
            int option = Validations.verifyIntegerInputsWithLimits(0, listOfGeographicAreasDto.getListOfGADto().size() - 1);
            location = Validations.defineLocation();
            if (mConfigureTheLocationOfTheHouseController.checkLocationOfTheHouse(location, listOfGeographicAreasDto.getListOfGADto().get(option))) {
            /**Address address = defineAddress();**/
                mConfigureTheLocationOfTheHouseController.changeLocationOfTheHouse(location, listOfGeographicAreasDto.getListOfGADto().get(option));
                System.out.println("Location of the house changed!");
            } else System.out.println("This location is outside of limits of occupation area!");
        }
    }

/** public static Address defineAddress() {
        System.out.println("Enter the street:");
        String street = Validations.readString();
        System.out.println("Enter the number:");
        String number = Validations.readString();
        System.out.println("Enter the ZipCode:");
        String zip = Validations.readString();
        System.out.println("Enter the Town:");
        String town = Validations.readString();
        System.out.println("Enter the Country:");
        String country = Validations.readString();
        return new Address(street,zip,town,number,country);
    }
**/

}