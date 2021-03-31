package pt.ipp.isep.dei.project1.controllers.house;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import pt.ipp.isep.dei.project1.model.sensor.SensorType;
import pt.ipp.isep.dei.project1.model.repositories.GeographicAreaDomainService;
import pt.ipp.isep.dei.project1.model.repositories.HouseDomainService;

@Controller
public class GetTheCurrentTemperatureInHouseAreaController {

    private final GeographicAreaDomainService geographicAreaDomainService;
    private final HouseDomainService houseDomainService;

    @Autowired
    public GetTheCurrentTemperatureInHouseAreaController(GeographicAreaDomainService geographicAreaDomainService, HouseDomainService houseDomainService) {
        this.geographicAreaDomainService = geographicAreaDomainService;
        this.houseDomainService = houseDomainService;
    }

    public String getGeographicAreaOfTheHouse() {
        return houseDomainService.getGeographicAreaID();
    }

    public boolean checkIfHaveSensorsOnHouseAreaWithTypeAndReadings() {
        return geographicAreaDomainService.checkIfHaveSensorsOnHouseAreaWithTypeAndReadings(houseDomainService.getGeographicAreaID(), houseDomainService.getLocation(), new SensorType("Temperature"));
    }

    public double getCurrentRoomTemperature() {
        return geographicAreaDomainService.getCurrentTemperature(houseDomainService.getGeographicAreaID(), houseDomainService.getLocation(), new SensorType("Temperature"));
    }

}
