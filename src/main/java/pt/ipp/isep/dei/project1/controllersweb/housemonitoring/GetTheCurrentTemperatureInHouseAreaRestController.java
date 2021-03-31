package pt.ipp.isep.dei.project1.controllersweb.housemonitoring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pt.ipp.isep.dei.project1.dto.sensordto.ReadingForResponseEntity;
import pt.ipp.isep.dei.project1.model.repositories.GeographicAreaDomainService;
import pt.ipp.isep.dei.project1.model.repositories.HouseDomainService;
import pt.ipp.isep.dei.project1.model.sensor.SensorType;

@RestController
@RequestMapping(value = "/house-monitoring")
@CrossOrigin(origins={"http://localhost:3000","http://smarthomeg1.azurewebsites.net"}, maxAge = 3600)
public class GetTheCurrentTemperatureInHouseAreaRestController {

    private final GeographicAreaDomainService geographicAreaDomainService;
    private final HouseDomainService houseDomainService;

    @Autowired
    public GetTheCurrentTemperatureInHouseAreaRestController(GeographicAreaDomainService geographicAreaDomainService, HouseDomainService houseDomainService) {
        this.geographicAreaDomainService = geographicAreaDomainService;
        this.houseDomainService = houseDomainService;
    }

    @GetMapping(value = "/geographic-area/current-temperature")
    public ResponseEntity<Object> getCurrentRoomTemperature() {
        double currentTemperature = geographicAreaDomainService.getCurrentTemperature(houseDomainService.getGeographicAreaID(), houseDomainService.getLocation(), new SensorType("Temperature"));
        if (Double.isNaN(currentTemperature))
            return new ResponseEntity<>(Double.NaN, HttpStatus.NOT_ACCEPTABLE);
        ReadingForResponseEntity reading = new ReadingForResponseEntity();
        reading.setValue(currentTemperature);
        return new ResponseEntity<>(reading, HttpStatus.OK);
    }

}
