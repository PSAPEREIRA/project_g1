package pt.ipp.isep.dei.project1.io.ui.house;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pt.ipp.isep.dei.project1.controllers.house.GetTheDayWithHighestTemperatureAmplitudeController;
import pt.ipp.isep.dei.project1.io.ui.utils.Validations;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.List;

@Component
public class GetTheDayWithHighestTemperatureAmplitudeUI {

    @Autowired
    private GetTheDayWithHighestTemperatureAmplitudeController getTheDayWithHighestTemperatureAmplitudeController;

    public void run() {
        if (getTheDayWithHighestTemperatureAmplitudeController.getGeographicAreaOfTheHouse() == null)
            System.out.println("The Geo Area of the house its not configured yet!");
        else {
            if (!getTheDayWithHighestTemperatureAmplitudeController.checkIfHaveSensorsOnHouseAreaWithTypeAndReadings())
                System.out.println("There are no temperature sensors installed in the house area or " +
                        " this sensors don't readings yet!");
            else {
                System.out.println("Choose the initial date you want:");
                LocalDate initialDate = Validations.checkLocalDateInput();
                System.out.println("Choose the final date you want:");
                LocalDate finalDate = Validations.checkLocalDateInput();
                List<LocalDate> listOfDaysToOutput = getTheDayWithHighestTemperatureAmplitudeController.getDayWithHighestTemperatureAmplitude(initialDate, finalDate);
                if (getTheDayWithHighestTemperatureAmplitudeController.getHighestTemperatureAmplitude(initialDate, finalDate) == Double.NaN)
                    System.out.println("No reading on selected period!");
                else {
                    DecimalFormat df = new DecimalFormat("#.0");
                    System.out.println("The highest temperature amplitude was: " + df.format(getTheDayWithHighestTemperatureAmplitudeController.getHighestTemperatureAmplitude(initialDate, finalDate)) + " ºC");
                    for (int i = 0; i < listOfDaysToOutput.size(); i++)
                        System.out.println("Registered on this day(s): " + listOfDaysToOutput.get(i));
                }
            }
        }
    }
}
