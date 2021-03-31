package pt.ipp.isep.dei.project1.controllers.house;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import pt.ipp.isep.dei.project1.dto.sensordto.ReadingDto;
import pt.ipp.isep.dei.project1.model.sensor.SensorType;
import pt.ipp.isep.dei.project1.model.repositories.GeographicAreaDomainService;
import pt.ipp.isep.dei.project1.model.repositories.HouseDomainService;

import java.time.LocalDate;

@Controller
public class HouseAreaTemperatureController {


    private final GeographicAreaDomainService geographicAreaDomainService;
    private final HouseDomainService houseDomainService;
    private static final String TEMPERATURE = "Temperature";

    @Autowired
    public HouseAreaTemperatureController(HouseDomainService houseDomainService, GeographicAreaDomainService geographicAreaDomainService) {
        this.geographicAreaDomainService = geographicAreaDomainService;
        this.houseDomainService = houseDomainService;
    }

    public String getGeographicAreaOfTheHouse() {
        return houseDomainService.getGeographicAreaID();
    }

    public boolean checkIfHaveSensorsOnHouseAreaWithTypeAndReadings() {
        return geographicAreaDomainService.checkIfHaveSensorsOnHouseAreaWithTypeAndReadings(houseDomainService.getGeographicAreaID(), houseDomainService.getLocation(), new SensorType(TEMPERATURE));
    }

    public ReadingDto getFirstHottestDayInGivenPeriodDto(LocalDate initDate, LocalDate finalDate) {
        return geographicAreaDomainService.getFirstHottestDayInGivenPeriodDto(houseDomainService.getGeographicAreaID(), houseDomainService.getLocation(), new SensorType(TEMPERATURE),initDate,finalDate);
    }

    public ReadingDto getLastColdestDayInGivenPeriodDto(LocalDate initDate, LocalDate finalDate) {
        return geographicAreaDomainService.getLastColdestDayInGivenPeriodDto(houseDomainService.getGeographicAreaID(), houseDomainService.getLocation(), new SensorType(TEMPERATURE),initDate,finalDate);
    }

}