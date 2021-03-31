package pt.ipp.isep.dei.project1.controllersweb.geographicarea;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import pt.ipp.isep.dei.project1.dto.sensordto.AreaSensorDto;
import pt.ipp.isep.dei.project1.mapper.MapperAreaSensor;
import pt.ipp.isep.dei.project1.model.geographicarea.GeographicArea;
import pt.ipp.isep.dei.project1.model.geographicarea.GeographicAreaType;
import pt.ipp.isep.dei.project1.model.geographicarea.Location;
import pt.ipp.isep.dei.project1.model.geographicarea.OccupationArea;
import pt.ipp.isep.dei.project1.model.repositories.GeographicAreaDomainService;
import pt.ipp.isep.dei.project1.model.repositories.GeographicAreaRepository;
import pt.ipp.isep.dei.project1.model.repositories.SensorTypeDomainService;
import pt.ipp.isep.dei.project1.model.repositories.SensorTypeRepository;
import pt.ipp.isep.dei.project1.model.sensor.AreaSensor;
import pt.ipp.isep.dei.project1.model.sensor.SensorType;
import pt.ipp.isep.dei.project1.services.GeoAreaSensorTypeService;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class CreateNewAreaSensorRestControllerTest {

    @Mock
    private GeoAreaSensorTypeService appService;

    @Mock
    private GeographicAreaRepository repo;
    @Mock
    private GeographicAreaDomainService geographicAreaDomainService;
    @Mock
    private CreateNewAreaSensorRestController controller;
    @Mock
    private GeographicAreaRestController geographicAreaRestController;
    @Mock
    private SensorTypeRepository sensorTypeRepository;
    @Mock
    private SensorTypeDomainService sensorTypeDomainService;


    @BeforeEach
    void initMocks() {

        geographicAreaDomainService = new GeographicAreaDomainService(repo);
        sensorTypeDomainService = new SensorTypeDomainService(sensorTypeRepository);
        appService= new GeoAreaSensorTypeService(geographicAreaDomainService, sensorTypeDomainService);
        controller = new CreateNewAreaSensorRestController(appService);
        geographicAreaRestController = new GeographicAreaRestController(appService);

    }


    @Test
    void testGetListOfSensorType() {
        SensorType sensorType = new SensorType("temperature");
        SensorType sensorType1 = new SensorType("Rainfall");
        sensorTypeDomainService.add(sensorType);
        sensorTypeDomainService.add(sensorType1);
        GeographicArea geographicArea = new GeographicArea("porto", "city", new OccupationArea(new Location(10, 20, 0), 10, 10), new GeographicAreaType("city"));
        geographicAreaDomainService.add(geographicArea);
        ResponseEntity<Object> result = controller.getListOfSensorsType();
        assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    @Test
    void testGetListOfSensorTypeEmptyList() {
        SensorType sensorType = new SensorType("temperature");
        SensorType sensorType1 = new SensorType("Rainfall");
        // sensorTypeRepo.add(sensorType);
        // sensorTypeRepo.add(sensorType1);
        GeographicArea geographicArea = new GeographicArea("porto", "city", new OccupationArea(new Location(10, 20, 0), 10, 10), new GeographicAreaType("city"));
        geographicAreaDomainService.add(geographicArea);
        ResponseEntity<Object> result = controller.getListOfSensorsType();
        assertEquals(HttpStatus.OK, result.getStatusCode());
    }


    @Test
    void CreateNewSensor() {
        SensorType sensorType = new SensorType("temperature");
        Location location = new Location(5, 15, 0);
        sensorTypeDomainService.add(sensorType);
        GeographicArea geographicArea = new GeographicArea("porto", "city", new OccupationArea
                (new Location(5, 15, 0), 10, 10), new GeographicAreaType("city"));
        geographicAreaDomainService.add(geographicArea);
        AreaSensor areaSensor = new AreaSensor("1", "s1", location,
                sensorType, LocalDate.of(2018, 12, 10), "C");
        AreaSensorDto areaSensorDto = MapperAreaSensor.toDto(areaSensor);
        ResponseEntity<Object> result = controller.createNewSensorWithLocation(areaSensorDto, geographicArea.getName());
        assertEquals(HttpStatus.CREATED, result.getStatusCode());
    }

    @Test
    void CreateNewSensorWrongType() {
        SensorType sensorType = new SensorType("temperature");
        Location location = new Location(5, 15, 0);
        sensorTypeDomainService.add(sensorType);
        GeographicArea geographicArea = new GeographicArea("porto", "city", new OccupationArea
                (new Location(10, 20, 0), 10, 10), new GeographicAreaType("city"));
        geographicAreaDomainService.add(geographicArea);

        SensorType noTypeInDb = new SensorType("wugabuga");

        AreaSensor areaSensor = new AreaSensor("1", "s1", location,
                noTypeInDb, LocalDate.of(2018, 12, 10), "C");
        AreaSensorDto areaSensorDto = MapperAreaSensor.toDto(areaSensor);
        ResponseEntity<Object> result = controller.createNewSensorWithLocation(areaSensorDto, geographicArea.getName());
        assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
    }


    @Test
    void NotCreateNewSensor1() {
        SensorType sensorType = new SensorType("temperature");
        Location location = new Location(5, 15, 0);
        sensorTypeDomainService.add(sensorType);
        GeographicArea geographicArea = new GeographicArea("porto", "city",
                new OccupationArea(new Location(10, 10, 10), 20, 30),
                new GeographicAreaType("city"));
        geographicAreaDomainService.add(geographicArea);
        AreaSensor areaSensor = new AreaSensor("1", "s1", location,
                sensorType, LocalDate.of(2018, 12, 10), "C");
        AreaSensorDto areaSensorDto = MapperAreaSensor.toDto(areaSensor);
        ResponseEntity<Object> result = controller.createNewSensorWithLocation(areaSensorDto, geographicArea.getName());
        assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, result.getStatusCode());
    }


    @Test
    void notCreateNewSensor2() {
        SensorType sensorType = new SensorType("temperature");
        Location location = new Location(5, 15, 0);
        sensorTypeDomainService.add(sensorType);
        GeographicArea geographicArea = new GeographicArea("porto", "city",
                new OccupationArea(new Location(10, 10, 10), 20, 30),
                new GeographicAreaType("city"));
        GeographicArea geographicArea1 = new GeographicArea("Lisboa", "city",
                new OccupationArea(new Location(10, 10, 10), 20, 30),
                new GeographicAreaType("city"));
        geographicAreaDomainService.add(geographicArea);
        AreaSensor areaSensor = new AreaSensor("1", "s1", location,
                sensorType, LocalDate.of(2018, 12, 10), "C");
        AreaSensorDto areaSensorDto = MapperAreaSensor.toDto(areaSensor);
        ResponseEntity<Object> result = controller.createNewSensorWithLocation(areaSensorDto, geographicArea1.getName());
        assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
    }


    @Test
    void createNewSensor() {
        SensorType sensorType = new SensorType("temperature");
        Location location = new Location(5, 15, 0);
        sensorTypeDomainService.add(sensorType);
        GeographicArea geographicArea = new GeographicArea("porto", "city",
                new OccupationArea(location, 20, 30),
                new GeographicAreaType("city"));
        AreaSensor areaSensor = new AreaSensor("1", "s1", location,
                sensorType, LocalDate.of(2018, 12, 10), "C");
        geographicAreaDomainService.add(geographicArea);
        AreaSensorDto areaSensorDto = MapperAreaSensor.toDto(areaSensor);
        ResponseEntity<Object> result = controller.createNewSensorWithLocation(areaSensorDto, geographicArea.getName());
        assertEquals(HttpStatus.CREATED, result.getStatusCode());
    }


    @Test
    void NotCreateNewSensor2() {
        SensorType sensorType = new SensorType("temperature");
        Location location = new Location(5, 15, 0);
        sensorTypeDomainService.add(sensorType);
        GeographicArea geographicArea = new GeographicArea("porto", "city",
                new OccupationArea(new Location(10, 10, 10), 20, 30),
                new GeographicAreaType("city"));
        AreaSensor areaSensor = new AreaSensor("1", "s1", location,
                sensorType, LocalDate.of(2018, 12, 10), "C");
        areaSensor.setGeographicArea(geographicArea);
        geographicAreaDomainService.add(geographicArea);
        geographicAreaDomainService.addSensorToList(areaSensor);
        AreaSensorDto areaSensorDto = MapperAreaSensor.toDto(areaSensor);
        ResponseEntity<Object> result = controller.createNewSensorWithLocation(areaSensorDto, geographicArea.getName());
        assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, result.getStatusCode());
    }


    @Test
    void getListOfGeographicAreas() {
        GeographicArea geographicArea = new GeographicArea("porto", "city", new OccupationArea(new Location(10, 10, 10), 20, 30), new GeographicAreaType("city"));
        GeographicArea geographicArea2 = new GeographicArea("lisboa", "city", new OccupationArea(new Location(20, 20, 20), 20, 30), new GeographicAreaType("city"));
        geographicAreaDomainService.add(geographicArea);
        geographicAreaDomainService.add(geographicArea2);
        ResponseEntity<Object> result = geographicAreaRestController.getListOfGeographicAreas();
        System.out.println(result.getBody());

        assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    @Test
    void checkLocationOfSensorTrue() {
        SensorType m1 = new SensorType("temperature");
        sensorTypeDomainService.add(m1);
        Location location = new Location(10, 20, 0);
        AreaSensor areaSensor = new AreaSensor("1", "s1", location,
                m1, LocalDate.of(2018, 12, 10), "C");
        GeographicArea geographicArea = new GeographicArea("porto", "city", new OccupationArea(new Location(10, 20, 0), 10, 10), new GeographicAreaType("city"));
        geographicAreaDomainService.add(geographicArea);
        ResponseEntity<Object> result = controller.createNewSensorWithLocation(MapperAreaSensor.toDto(areaSensor), "porto");
        assertEquals(HttpStatus.CREATED, result.getStatusCode());
    }


    @Test
    void checkLocationOfSensorFalse() {
        SensorType m1 = new SensorType("temperature");
        sensorTypeDomainService.add(m1);
        Location location = new Location(40.7486, 73.9864, 0);
        AreaSensor areaSensor = new AreaSensor("1", "s1", location,
                m1, LocalDate.of(2018, 12, 10), "C");
        GeographicArea geographicArea = new GeographicArea("porto", "city", new OccupationArea(new Location(10, 20, 0), 10, 10), new GeographicAreaType("city"));
        geographicAreaDomainService.add(geographicArea);
        ResponseEntity<Object> result = controller.createNewSensorWithLocation(MapperAreaSensor.toDto(areaSensor), "porto");
        assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, result.getStatusCode());
    }

    @Test
    void checkLocationOfSensorOutsideLimits() {
        SensorType m1 = new SensorType("temperature");
        sensorTypeDomainService.add(m1);
        Location location = new Location(40, 0, 2);
        AreaSensor areaSensor = new AreaSensor("1", "s1", location,
                m1, LocalDate.of(2018, 12, 10), "C");
        GeographicArea geographicArea = new GeographicArea("porto", "city", new OccupationArea(new Location(10, 20, 0), 10, 10), new GeographicAreaType("city"));
        geographicAreaDomainService.add(geographicArea);
        ResponseEntity<Object> result = controller.createNewSensorWithLocation(MapperAreaSensor.toDto(areaSensor), "porto");
        assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, result.getStatusCode());
    }

    @Test
    void NotCreateNewSensor3() {
        SensorType sensorType = new SensorType("temperature");
        Location location = new Location(5, 15, 0);
        sensorTypeDomainService.add(sensorType);
        GeographicArea geographicArea = new GeographicArea("porto", "city",
                new OccupationArea(new Location(5, 15, 0), 20, 30),
                new GeographicAreaType("city"));
        AreaSensor areaSensor = new AreaSensor("1","s1", location,
                sensorType,LocalDate.of(2018,12,10),"C");
        areaSensor.setGeographicArea(geographicArea);
        geographicAreaDomainService.add(geographicArea);
        geographicAreaDomainService.addSensorToList(areaSensor);
        AreaSensorDto areaSensorDto = MapperAreaSensor.toDto(areaSensor);
        areaSensorDto.setIdOfSensor("");
        ResponseEntity <Object> result = controller.createNewSensorWithLocation(areaSensorDto,geographicArea.getName());
        assertEquals(HttpStatus.NOT_ACCEPTABLE, result.getStatusCode());
    }

    @Test
    void NotCreateNewSensorRepeated() {
        SensorType sensorType = new SensorType("temperature");
        Location location = new Location(5, 15, 0);
        sensorTypeDomainService.add(sensorType);
        GeographicArea geographicArea = new GeographicArea("porto", "city",
                new OccupationArea(new Location(5, 15, 0), 20, 30),
                new GeographicAreaType("city"));
        AreaSensor areaSensor = new AreaSensor("1","s1", location,
                sensorType,LocalDate.of(2018,12,10),"C");
        areaSensor.setGeographicArea(geographicArea);
        geographicArea.addSensor(areaSensor);
        geographicAreaDomainService.add(geographicArea);
        AreaSensorDto areaSensorDto = MapperAreaSensor.toDto(areaSensor);
        ResponseEntity <Object> result = controller.createNewSensorWithLocation(areaSensorDto,geographicArea.getName());
        assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, result.getStatusCode());
    }

}