package pt.ipp.isep.dei.project1.controllers.house;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import pt.ipp.isep.dei.project1.model.sensor.SensorType;
import pt.ipp.isep.dei.project1.model.repositories.GeographicAreaDomainService;
import pt.ipp.isep.dei.project1.model.repositories.HouseDomainService;

import java.time.LocalDate;

@Controller
public class GetTotalAndAverageRainfallInHouseAreaForAGivenDayController {

    private final GeographicAreaDomainService geographicAreaDomainService;
    private final HouseDomainService houseDomainService;
    private static final String RAINFALL = "rainfall";


    @Autowired
    public GetTotalAndAverageRainfallInHouseAreaForAGivenDayController(HouseDomainService houseDomainService, GeographicAreaDomainService geographicAreaDomainService) {
        this.geographicAreaDomainService = geographicAreaDomainService;
        this.houseDomainService = houseDomainService;
    }

    public String getGeographicAreaOfTheHouse() {
        return houseDomainService.getGeographicAreaID();
    }

    public boolean checkIfHaveSensorsOnHouseAreaWithTypeAndReadings() {
        return geographicAreaDomainService.checkIfHaveSensorsOnHouseAreaWithTypeAndReadings(houseDomainService.getGeographicAreaID(), houseDomainService.getLocation(), new SensorType(RAINFALL));
    }

    public double sumOfRainfallInDay(LocalDate dateInput) {
        return geographicAreaDomainService.getSumOfValueOnSensorInCertainDay(houseDomainService.getGeographicAreaID(), houseDomainService.getLocation(),new SensorType(RAINFALL),dateInput);
    }

    public double getAverageRainfall(LocalDate initialDate, LocalDate finalDate) {
        return geographicAreaDomainService.getAverageRainfallInOneAG(houseDomainService.getGeographicAreaID(), houseDomainService.getLocation(),new SensorType(RAINFALL),initialDate, finalDate);
    }
}
