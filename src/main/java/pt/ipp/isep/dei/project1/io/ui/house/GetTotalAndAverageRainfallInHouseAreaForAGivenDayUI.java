package pt.ipp.isep.dei.project1.io.ui.house;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pt.ipp.isep.dei.project1.controllers.house.GetTotalAndAverageRainfallInHouseAreaForAGivenDayController;
import pt.ipp.isep.dei.project1.io.ui.utils.Validations;

import java.time.LocalDate;

@Component
public class GetTotalAndAverageRainfallInHouseAreaForAGivenDayUI {

    @Autowired
    private GetTotalAndAverageRainfallInHouseAreaForAGivenDayController getTotalAndAverageRainfallInHouseAreaForAGivenDayController;


    public void runTotal() {
        if (getTotalAndAverageRainfallInHouseAreaForAGivenDayController.getGeographicAreaOfTheHouse() == null)
            System.out.println("The Geographic Area of the house its not configured yet!");
        else {
            if (!getTotalAndAverageRainfallInHouseAreaForAGivenDayController.checkIfHaveSensorsOnHouseAreaWithTypeAndReadings())
                System.out.println("There are no Rainfall sensors installed in the house area or there are no readings yet!");
            else {
                LocalDate dateInput = Validations.checkLocalDateInput();
                System.out.println("The sum of rainfall for that day on the house is: " +
                        getTotalAndAverageRainfallInHouseAreaForAGivenDayController.sumOfRainfallInDay(dateInput) + " mm");
            }
        }
    }

    public void runAverage() {
        if (getTotalAndAverageRainfallInHouseAreaForAGivenDayController.getGeographicAreaOfTheHouse() == null)
            System.out.println("The Geographic Area of the house its not configured yet!");
        else {
            if (!getTotalAndAverageRainfallInHouseAreaForAGivenDayController.checkIfHaveSensorsOnHouseAreaWithTypeAndReadings())
                System.out.println("There are no Sensors of Rainfall installed in the house area or there are no readings yet!");
            else {
                System.out.println("Please enter the initial date");
                LocalDate initialDate = Validations.checkLocalDateInput();
                System.out.println("Please enter the final date");
                LocalDate finalDate = Validations.checkLocalDateInput();
                double average = getTotalAndAverageRainfallInHouseAreaForAGivenDayController.getAverageRainfall(initialDate, finalDate);
                if (average < 0)
                    System.out.println("Don't exist any reading for this dates");
                System.out.println("Average Daily Rainfall is:" + average + " mm");
            }
        }
    }
}

