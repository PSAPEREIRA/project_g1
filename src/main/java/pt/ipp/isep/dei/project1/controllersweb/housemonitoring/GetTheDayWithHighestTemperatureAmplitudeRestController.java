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
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping(value = "/house-monitoring")
@CrossOrigin(origins={"http://localhost:3000","http://smarthomeg1.azurewebsites.net"}, maxAge = 3600)
public class GetTheDayWithHighestTemperatureAmplitudeRestController {

    private final GeographicAreaDomainService geographicAreaDomainService;
    private final HouseDomainService houseDomainService;
    private static final String TEMPERATURE = "Temperature";

    @Autowired
    public GetTheDayWithHighestTemperatureAmplitudeRestController(HouseDomainService houseDomainService, GeographicAreaDomainService geographicAreaDomainService) {
        this.geographicAreaDomainService = geographicAreaDomainService;
        this.houseDomainService = houseDomainService;
    }

    @ModelAttribute
    LocalDate initLocalDate() {
        return LocalDate.now();
    }

    @GetMapping(value = "/house/geographic-area/sensors/dates/")
    public ResponseEntity<Object> getDayWithHighestTemperatureAmplitude(@RequestBody LocalDate initialDate, LocalDate finalDate){
        List<LocalDate> result = geographicAreaDomainService.getDayWithHighestTemperatureAmplitude(houseDomainService.getGeographicAreaID(), houseDomainService.getLocation(), new SensorType(TEMPERATURE), initialDate, finalDate);
        if (result.equals(Collections.emptyList()))
            return new ResponseEntity<>("No result found!", HttpStatus.NOT_ACCEPTABLE);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping(value = "/geographic-area/sensors/highest-temperature-amplitude")
    public ResponseEntity<Object> getHighestTemperatureAmplitude(@RequestParam(name = "s") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate initialDate,@RequestParam(name = "e") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate finalDate) {
        double result = geographicAreaDomainService.getHighestTemperatureAmplitude(houseDomainService.getGeographicAreaID(), houseDomainService.getLocation(), new SensorType(TEMPERATURE), initialDate, finalDate);
        if (Double.isNaN(result))
            return new ResponseEntity<>("No result found!", HttpStatus.NOT_ACCEPTABLE);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
