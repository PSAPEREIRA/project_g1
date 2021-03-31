package pt.ipp.isep.dei.project1.controllersweb.geographicarea;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pt.ipp.isep.dei.project1.dto.geographicareadto.GeographicAreaDto;
import pt.ipp.isep.dei.project1.dto.sensordto.AreaSensorDto;
import pt.ipp.isep.dei.project1.dto.sensordto.SensorTypeDto;
import pt.ipp.isep.dei.project1.services.GeoAreaSensorTypeService;

import java.util.Collections;

@RestController
@RequestMapping(value = "/geographic-area-configuration")
@CrossOrigin(origins={"http://localhost:3000","http://smarthomeg1.azurewebsites.net"}, maxAge = 3600)
public class CreateNewAreaSensorRestController {

    private final GeoAreaSensorTypeService appService;
    private static final String NOT_FOUND = " not found!";


    @Autowired
    public CreateNewAreaSensorRestController(GeoAreaSensorTypeService appService) {
        this.appService = appService;
    }

    @GetMapping(value = "/sensor-types")
    public ResponseEntity<Object> getListOfSensorsType() {
        if (!appService.getListOfSensorTypesDTO().equals(Collections.emptyList()))
            return new ResponseEntity<>(appService.getListOfSensorTypesDTO(), HttpStatus.OK);
        return new ResponseEntity<>("List of sensor types empty!", HttpStatus.OK);
    }

    @PostMapping(value = "/{geographic-area-name}/new-sensor")
    public ResponseEntity<Object> createNewSensorWithLocation(@RequestBody AreaSensorDto areaSensorDto, @PathVariable("geographic-area-name") String nameGA) {
        GeographicAreaDto geographicAreaDto = appService.getGeographicAreaByNameDTO(nameGA);
        if (geographicAreaDto == null)
            return new ResponseEntity<>(("Geographic area with name " + nameGA
                    + NOT_FOUND), HttpStatus.NOT_FOUND);
        SensorTypeDto sensorTypeDto = appService.getSensorTypeByNameDTO(areaSensorDto.getSensorType().getType());
        if (sensorTypeDto == null)
            return new ResponseEntity<>(("Sensor type with name " + areaSensorDto.getSensorType().getType()
                    + NOT_FOUND), HttpStatus.NOT_FOUND);
        if (!appService.checkOccupationAreaLimits(geographicAreaDto.getName(), areaSensorDto.getLocation()))
            return new ResponseEntity<>("Sensor location is outside GeographicArea limits!", HttpStatus.UNPROCESSABLE_ENTITY);
        int result = appService.createAndAddSensor(areaSensorDto, nameGA);
        if (result == -1)
            return new ResponseEntity<>("Impossible to create sensor!", HttpStatus.NOT_ACCEPTABLE);
        else if (result == -2)
            return new ResponseEntity<>("Impossible to add, sensor with this ID already exists!", HttpStatus.UNPROCESSABLE_ENTITY);
        return new ResponseEntity<>(areaSensorDto.getIdOfSensor() + " added to " + geographicAreaDto.getName(), HttpStatus.CREATED);
    }


}
