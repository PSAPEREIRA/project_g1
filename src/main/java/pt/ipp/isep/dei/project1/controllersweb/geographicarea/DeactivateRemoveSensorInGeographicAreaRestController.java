package pt.ipp.isep.dei.project1.controllersweb.geographicarea;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pt.ipp.isep.dei.project1.dto.geographicareadto.GeographicAreaDto;
import pt.ipp.isep.dei.project1.dto.sensordto.AreaSensorDto;
import pt.ipp.isep.dei.project1.model.repositories.GeographicAreaDomainService;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping(value = "/geographic-area-configuration")
@CrossOrigin(origins={"http://localhost:3000","http://smarthomeg1.azurewebsites.net"}, maxAge = 3600)
public class DeactivateRemoveSensorInGeographicAreaRestController {

    private final GeographicAreaDomainService geographicAreaDomainService;
    private static final String GEOGRAPHIC_AREA_NAME = "Geographic area with name ";
    private static final String NOT_FOUND = "not found !";


    @Autowired
    public DeactivateRemoveSensorInGeographicAreaRestController(GeographicAreaDomainService geographicAreaDomainService) {
        this.geographicAreaDomainService = geographicAreaDomainService;
    }

    @GetMapping(value = "/geographic_areas/{geographic-area-name}/active-sensors")
    public ResponseEntity<Object> getListOfSensorsActivate(@PathVariable("geographic-area-name") String geographicAreaName) {
        GeographicAreaDto geographicAreaDto = geographicAreaDomainService.getGeographicAreaByNameDTO(geographicAreaName);
        if (geographicAreaDto == null)
            return new ResponseEntity<>((GEOGRAPHIC_AREA_NAME + geographicAreaName
                    + NOT_FOUND), HttpStatus.NOT_FOUND);
        List<AreaSensorDto> listOfSensorsActivated = geographicAreaDomainService.getListOfSensorsActiveDTO(geographicAreaName);
        if (!listOfSensorsActivated.equals(Collections.emptyList()))
            return new ResponseEntity<>(listOfSensorsActivated, HttpStatus.OK);
        return new ResponseEntity<>("Do not exist any activated sensor!", HttpStatus.NOT_ACCEPTABLE);
    }

    @PatchMapping(value = "geographic_areas/{geographic-area-name}/Sensors/{idOfSensor}")
    public ResponseEntity<Object> deactivateSensor(@PathVariable("geographic-area-name") String geographicAreaName, @PathVariable("idOfSensor") String idOfSensor) {
        GeographicAreaDto geographicAreaDto = geographicAreaDomainService.getGeographicAreaByNameDTO(geographicAreaName);
        if (geographicAreaDto == null)
            return new ResponseEntity<>((GEOGRAPHIC_AREA_NAME + geographicAreaName
                    + NOT_FOUND), HttpStatus.NOT_FOUND);
        AreaSensorDto areaSensorDto = geographicAreaDomainService.getSensorByIdDTO(geographicAreaName, idOfSensor);
        if (areaSensorDto == null)
            return new ResponseEntity<>(("Sensor with name " + idOfSensor
                    + NOT_FOUND), HttpStatus.NOT_FOUND);
        geographicAreaDomainService.deactivateSensor(geographicAreaName, idOfSensor);
        return new ResponseEntity<>("Sensor deactivate!", HttpStatus.OK);
    }

    @GetMapping(value = "/geographic_areas/{geographic-area-name}/sensors")
    public ResponseEntity<Object> getListOfSensorsDtoFromGA(@PathVariable("geographic-area-name") String geographicAreaName) {
        GeographicAreaDto geographicAreaDto = geographicAreaDomainService.getGeographicAreaByNameDTO(geographicAreaName);
        if (geographicAreaDto == null)
            return new ResponseEntity<>((GEOGRAPHIC_AREA_NAME + geographicAreaName
                    + NOT_FOUND), HttpStatus.NOT_FOUND);
        List<AreaSensorDto> listOfAreaSensors = geographicAreaDomainService.getListOfSensorsDtoFromGA(geographicAreaName);
        if (!listOfAreaSensors.equals(Collections.emptyList()))
            return new ResponseEntity<>(listOfAreaSensors, HttpStatus.OK);
        return new ResponseEntity<>("Dont exist any sensor yet!", HttpStatus.NOT_ACCEPTABLE);
    }

    @DeleteMapping(value = "/geographic_areas/{geographic-area-name}/sensors/{idOfSensor}")
    public ResponseEntity<Object> removeSensorFromGA(@PathVariable("geographic-area-name") String geographicAreaName, @PathVariable("idOfSensor") String idOfSensor) {
        GeographicAreaDto geographicAreaDto = geographicAreaDomainService.getGeographicAreaByNameDTO(geographicAreaName);
        if (geographicAreaDto == null)
            return new ResponseEntity<>((GEOGRAPHIC_AREA_NAME + geographicAreaName
                    + NOT_FOUND), HttpStatus.NOT_FOUND);
        AreaSensorDto areaSensorDto = geographicAreaDomainService.getSensorByIdDTO(geographicAreaName, idOfSensor);
        if (areaSensorDto == null)
            return new ResponseEntity<>(("Sensor with name " + idOfSensor
                    + NOT_FOUND), HttpStatus.NOT_FOUND);
        geographicAreaDomainService.removeSensor(geographicAreaName, idOfSensor);
        return new ResponseEntity<>(areaSensorDto.getIdOfSensor(), HttpStatus.OK);
    }
}
