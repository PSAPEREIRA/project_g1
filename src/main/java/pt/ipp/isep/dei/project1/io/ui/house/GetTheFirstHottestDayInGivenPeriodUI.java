package pt.ipp.isep.dei.project1.io.ui.house;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import pt.ipp.isep.dei.project1.controllers.house.HouseAreaTemperatureController;
import pt.ipp.isep.dei.project1.dto.sensordto.ReadingDto;
import pt.ipp.isep.dei.project1.io.ui.utils.Validations;

import java.time.LocalDate;

@Controller
public class GetTheFirstHottestDayInGivenPeriodUI {

    @Autowired
    private HouseAreaTemperatureController houseAreaTemperatureController;


    public void run() {
        if (houseAreaTemperatureController.getGeographicAreaOfTheHouse() == null)
            System.out.println("The geographic area of the house its not configured yet!");
        else {
            if (!houseAreaTemperatureController.checkIfHaveSensorsOnHouseAreaWithTypeAndReadings())
                System.out.println("There are no temperature sensors installed in the house area or these" +
                        "sensors dont have readings yet!");
            else {
                System.out.println("Choose the first date you want:");
                LocalDate initDate = Validations.checkLocalDateInput();
                System.out.println("Choose the second date you want:");
                LocalDate finalDate = Validations.checkLocalDateInput();
                ReadingDto reading = houseAreaTemperatureController.getFirstHottestDayInGivenPeriodDto
                        (initDate, finalDate);
                if (reading.getStatus() == 1)
                    System.out.println("No reading on selected period!");
                else
                    System.out.println("The First Hottest day in house area was: " + reading.getDateTime().toLocalDate() +
                            " with the value: " + reading.getValue() + " ºC");
            }
        }
    }
}

