package pt.ipp.isep.dei.project1.controllersweb.housemonitoring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pt.ipp.isep.dei.project1.model.repositories.GeographicAreaDomainService;
import pt.ipp.isep.dei.project1.model.repositories.HouseDomainService;
import pt.ipp.isep.dei.project1.model.sensor.SensorType;

import java.time.LocalDate;

@RestController
@RequestMapping(value = "/rainfall")
@CrossOrigin(origins={"http://localhost:3000","http://smarthomeg1.azurewebsites.net"}, maxAge = 3600)
public class RainfallInHouseAreaRestCtrl {

    private final GeographicAreaDomainService geographicAreaDomainService;
    private final HouseDomainService houseDomainService;
    private static final String RAINFALL = "rainfall";

    @Autowired
    public RainfallInHouseAreaRestCtrl(HouseDomainService houseDomainService, GeographicAreaDomainService geographicAreaDomainService) {
        this.geographicAreaDomainService = geographicAreaDomainService;
        this.houseDomainService = houseDomainService;
    }

    @GetMapping(value = "/sum")
    public ResponseEntity<Object> sumOfRainfallInDay(@RequestParam(name = "date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateInput) {
        if (houseDomainService.getGeographicAreaID() == null)
            return new ResponseEntity<>("No Geographic Area defined!", HttpStatus.NOT_ACCEPTABLE);
        if (!geographicAreaDomainService.checkIfHaveSensorsOnHouseAreaWithTypeAndReadings(houseDomainService.getGeographicAreaID(), houseDomainService.getLocation(), new SensorType(RAINFALL)))
            return new ResponseEntity<>("No sensors in Geographic Area!", HttpStatus.NOT_ACCEPTABLE);

        double sum = geographicAreaDomainService.getSumOfValueOnSensorInCertainDay(houseDomainService.getGeographicAreaID(), houseDomainService.getLocation(), new SensorType(RAINFALL), dateInput);

        if (sum <= 0)
            return new ResponseEntity<>("It did not rain on this day", HttpStatus.OK);

        return new ResponseEntity<>(sum+" mm", HttpStatus.OK);
    }


    @GetMapping(value = "/avg")
    public ResponseEntity<Object> avgOfRainfallInTimeInterval(@RequestParam(name = "s") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate initialDate, @RequestParam(name = "e") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate finalDate) {
        if (houseDomainService.getGeographicAreaID() == null)
            return new ResponseEntity<>("No Geographic Area defined!", HttpStatus.NOT_ACCEPTABLE);
        if (!geographicAreaDomainService.checkIfHaveSensorsOnHouseAreaWithTypeAndReadings(houseDomainService.getGeographicAreaID(), houseDomainService.getLocation(), new SensorType(RAINFALL)))
            return new ResponseEntity<>("No sensors in Geographic Area!", HttpStatus.NOT_ACCEPTABLE);

        double average = geographicAreaDomainService.getAverageRainfallInOneAG(houseDomainService.getGeographicAreaID(), houseDomainService.getLocation(), new SensorType(RAINFALL), initialDate, finalDate);
        if (average <= 0)
            return new ResponseEntity<>("No readings registered on this period", HttpStatus.OK);

        average=(Math.round(average*100d))/100d;

        return new ResponseEntity<>(average+" mm", HttpStatus.OK);
    }
}
