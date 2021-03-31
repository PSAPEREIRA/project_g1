package pt.ipp.isep.dei.project1.controllersweb.geographicarea;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import pt.ipp.isep.dei.project1.model.geographicarea.GeographicArea;
import pt.ipp.isep.dei.project1.model.geographicarea.GeographicAreaType;
import pt.ipp.isep.dei.project1.model.geographicarea.Location;
import pt.ipp.isep.dei.project1.model.geographicarea.OccupationArea;
import pt.ipp.isep.dei.project1.model.repositories.GeographicAreaDomainService;
import pt.ipp.isep.dei.project1.model.repositories.GeographicAreaRepository;
import pt.ipp.isep.dei.project1.model.sensor.AreaSensor;
import pt.ipp.isep.dei.project1.model.sensor.SensorType;
import pt.ipp.isep.dei.project1.services.GeoAreaSensorTypeService;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;


@ExtendWith(MockitoExtension.class)
class GeographicAreaRestControllerTest {

    @Mock
    private GeoAreaSensorTypeService appService;

    @Mock
    private GeographicAreaRepository repo;
    @Mock
    private GeographicAreaDomainService geographicAreaDomainService;
    @Mock
    private GeographicAreaRestController geographicAreaRestController;

    @BeforeEach
    void initMocks() {
        geographicAreaDomainService = new GeographicAreaDomainService(repo);
        appService = new GeoAreaSensorTypeService(geographicAreaDomainService,null);
        geographicAreaRestController = new GeographicAreaRestController(appService);
    }

    @Test
    void getGeographicArea() {

        SensorType sensorType = new SensorType("temperature");
        Location location = new Location(5, 15, 0);
        GeographicArea geographicArea = new GeographicArea("porto","city",new OccupationArea
                (new Location(10,20,0),10,10),new GeographicAreaType("city"));

        AreaSensor areaSensor = new AreaSensor("1","s1", location,
                sensorType, LocalDate.of(2018,12,10),"C");

        geographicArea.addSensorToList(areaSensor);
        geographicAreaDomainService.add(geographicArea);
        ResponseEntity result = geographicAreaRestController.getGeographicArea("porto");

        assertEquals(HttpStatus.OK, result.getStatusCode());

    }

    @Test
    void getGeographicAreaEmpty() {

        ResponseEntity result = geographicAreaRestController.getListOfGeographicAreas();
        assertEquals(HttpStatus.OK, result.getStatusCode());

    }

    @Test
    void getSensor() {
        SensorType sensorType = new SensorType("temperature");
        Location location = new Location(5, 15, 0);
        GeographicArea geographicArea = new GeographicArea("porto","city",new OccupationArea
                (new Location(10,20,0),10,10),new GeographicAreaType("city"));

        AreaSensor areaSensor = new AreaSensor("1","s1", location,
                sensorType, LocalDate.of(2018,12,10),"C");

        geographicArea.addSensorToList(areaSensor);
        geographicAreaDomainService.add(geographicArea);
        ResponseEntity result = geographicAreaRestController.getSensor("porto","1");

        assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    @Test
    void getSensorFalse() {
        SensorType sensorType = new SensorType("temperature");
        Location location = new Location(5, 15, 0);
        GeographicArea geographicArea = new GeographicArea("porto","city",new OccupationArea
                (new Location(10,20,0),10,10),new GeographicAreaType("city"));

        geographicAreaDomainService.add(geographicArea);
        ResponseEntity result = geographicAreaRestController.getSensor("porto","1");

        assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());
    }

}