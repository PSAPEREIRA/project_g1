package pt.ipp.isep.dei.project1.controllersweb.geographicarea;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pt.ipp.isep.dei.project1.dto.geographicareadto.GeographicAreaDto;
import pt.ipp.isep.dei.project1.dto.geographicareadto.GeographicAreaDtoWeb;
import pt.ipp.isep.dei.project1.dto.sensordto.AreaSensorDto;
import pt.ipp.isep.dei.project1.services.GeoAreaSensorTypeService;

import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@RequestMapping(value = "/geographic-area-configuration")
@CrossOrigin(origins={"http://localhost:3000","http://smarthomeg1.azurewebsites.net"}, maxAge = 3600)
public class GeographicAreaRestController {

    private final GeoAreaSensorTypeService appService;

    @Autowired
    public GeographicAreaRestController(GeoAreaSensorTypeService geoAreaSensorTypeService) {
        this.appService = geoAreaSensorTypeService;
    }

    @GetMapping(value = "/geographic-areas")
    public ResponseEntity<Object> getListOfGeographicAreas() {
        List<GeographicAreaDtoWeb> list = appService.getListOfGeographicAreasDTO();
        for (GeographicAreaDtoWeb geographicAreaDto : list) {
            Link selfLink = linkTo(methodOn(GeographicAreaRestController.class).getGeographicArea(geographicAreaDto.getName())).withSelfRel();
            geographicAreaDto.add(selfLink);
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping(value = "/{geographic-area-name}")
    public ResponseEntity<Object> getGeographicArea(@PathVariable("geographic-area-name") String name) {
        GeographicAreaDto geographicAreaDto = appService.getGeographicAreaByNameDTO(name);
        List<AreaSensorDto> list = geographicAreaDto.getListOfAreaSensorsDto().getListOfAreaSensorDto();
        for (AreaSensorDto areaSensorDto : list) {
            Link selfLink = linkTo(methodOn(GeographicAreaRestController.class).getSensor(geographicAreaDto.getName(), areaSensorDto.getIdOfSensor())).withSelfRel();
            areaSensorDto.add(selfLink);
        }
        return new ResponseEntity<>(geographicAreaDto, HttpStatus.OK);
    }

    @GetMapping(value = "/{geographic-area-name}/{sensor-id}")
    public ResponseEntity<Object> getSensor(@PathVariable("geographic-area-name") String name, @PathVariable("sensor-id")
            String idOfSensor) {
        if (appService.getSensorByIdDTO(name, idOfSensor) != null)
            return new ResponseEntity<>(appService.getSensorByIdDTO(name, idOfSensor), HttpStatus.OK);
        return new ResponseEntity<>("Sensor not found!", HttpStatus.NO_CONTENT);
    }

}
