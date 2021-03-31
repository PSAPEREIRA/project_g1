package pt.ipp.isep.dei.project1.controllersweb.geographicarea;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import pt.ipp.isep.dei.project1.dto.geographicareadto.GeographicAreaDto;
import pt.ipp.isep.dei.project1.mapper.MapperAreaSensor;
import pt.ipp.isep.dei.project1.mapper.MapperGeographicArea;
import pt.ipp.isep.dei.project1.mapper.MapperListOfAreaSensor;
import pt.ipp.isep.dei.project1.mapper.MapperListOfGeographicArea;
import pt.ipp.isep.dei.project1.model.geographicarea.GeographicArea;
import pt.ipp.isep.dei.project1.model.geographicarea.GeographicAreaType;
import pt.ipp.isep.dei.project1.model.geographicarea.Location;
import pt.ipp.isep.dei.project1.model.geographicarea.OccupationArea;
import pt.ipp.isep.dei.project1.model.repositories.GeographicAreaDomainService;
import pt.ipp.isep.dei.project1.model.repositories.GeographicAreaRepository;
import pt.ipp.isep.dei.project1.model.sensor.AreaSensor;
import pt.ipp.isep.dei.project1.model.sensor.SensorType;
import pt.ipp.isep.dei.project1.model.sensor.Status;
import pt.ipp.isep.dei.project1.services.GeoAreaSensorTypeService;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class DeactivateRemoveSensorInGeographicAreaRestControllerTest {

    @Mock
    private GeoAreaSensorTypeService appService;

    @Mock
    private GeographicAreaDomainService geographicAreaDomainService;
    @Mock
    private GeographicAreaRepository geographicAreaRepository;
    @Mock
    private GeographicAreaRestController geographicAreaRestController;
    @Mock
    private DeactivateRemoveSensorInGeographicAreaRestController ctrl;

    @BeforeEach
    void initUseCase() {
        geographicAreaDomainService = new GeographicAreaDomainService(geographicAreaRepository);
        ctrl = new DeactivateRemoveSensorInGeographicAreaRestController(geographicAreaDomainService);
        appService = new GeoAreaSensorTypeService(geographicAreaDomainService,null);
        geographicAreaRestController = new GeographicAreaRestController(appService);

    }

    @Test
    void getListGeographicAreaDtoTestOk() {
        //ARRANGE
        GeographicArea g1 = new GeographicArea("Porto", "City", new OccupationArea
                (new Location(10, 20, 30), 0.5, 0.5),
                new GeographicAreaType("City"));
        GeographicArea g2 = new GeographicArea("Porto", "City", new OccupationArea
                (new Location(10, 20, 30), 0.5, 0.5),
                new GeographicAreaType("City"));
        GeographicArea g3 = new GeographicArea("Porto", "City", new OccupationArea
                (new Location(10, 20, 30), 0.5, 0.5),
                new GeographicAreaType("City"));
        geographicAreaDomainService.add(g1);
        geographicAreaDomainService.add(g2);
        geographicAreaDomainService.add(g3);
        //ACT
        MapperListOfGeographicArea.toDto(geographicAreaDomainService.getListOfGeographicArea());
        ResponseEntity<Object> result = geographicAreaRestController.getListOfGeographicAreas();
        //ASSERT
        assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    @Test
    void deactivateSensorTestOk() {
        //ARRANGE
        GeographicArea geographicArea = new GeographicArea("Porto", "city",
                new OccupationArea(new Location(10, 20, 30), 0.5, 0.5),
                new GeographicAreaType("city"));
        AreaSensor areaSensor1 = new AreaSensor("1", "s1", new Location(10.0001, 20.00001, 0),
                new SensorType("temp"), LocalDate.of(2018, 12, 10), "C");
        //ACT
        geographicAreaDomainService.add(geographicArea);
        geographicArea.addSensorToList(areaSensor1);
        MapperGeographicArea.toDto(geographicArea);
        MapperAreaSensor.toDto(areaSensor1);
        ResponseEntity<Object> result = ctrl.deactivateSensor(geographicArea.getName(), areaSensor1.getIdOfAreaSensor());
        //ASSERT
        assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    @Test
    void deactivateSensorTestGANotFound() {
        //ARRANGE
        GeographicArea geographicArea = new GeographicArea("Porto", "city",
                new OccupationArea(new Location(10, 20, 30), 0.5, 0.5),
                new GeographicAreaType("city"));
        AreaSensor areaSensor1 = new AreaSensor("1", "s1", new Location(10.0001, 20.00001, 0),
                new SensorType("temp"), LocalDate.of(2018, 12, 10), "C");
        //ACT
        ResponseEntity<Object> result = ctrl.deactivateSensor(geographicArea.getName(), areaSensor1.getIdOfAreaSensor());
        //ASSERT
        assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
    }

    @Test
    void deactivateSensorTestAreaSensorNotFound() {
        //ARRANGE
        GeographicArea geographicArea = new GeographicArea("Porto", "city",
                new OccupationArea(new Location(10, 20, 30), 0.5, 0.5),
                new GeographicAreaType("city"));
        AreaSensor areaSensor1 = new AreaSensor("1", "s1", new Location(10.0001, 20.00001, 0),
                new SensorType("temp"), LocalDate.of(2018, 12, 10), "C");
        //ACT
        geographicAreaDomainService.add(geographicArea);
        ResponseEntity<Object> result = ctrl.deactivateSensor(geographicArea.getName(), areaSensor1.getIdOfAreaSensor());
        //ASSERT
        assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
    }

    @Test
    void getListOfSensorsActivateTestNOT_ACCEPTABLE() {
        //ARRANGE
        GeographicArea geographicArea = new GeographicArea("Porto", "city",
                new OccupationArea(new Location(10, 20, 30), 0.5, 0.5),
                new GeographicAreaType("city"));
        geographicAreaDomainService.add(geographicArea);
        AreaSensor areaSensor1 = new AreaSensor("1", "s1", new Location(10.0001, 20.00001, 0),
                new SensorType("temp"), LocalDate.of(2018, 12, 10), "C");
        geographicArea.addSensorToList(areaSensor1);
        LocalDate date1 = LocalDate.of(2019, 1, 29);
        LocalDate date2 = LocalDate.of(2019, 1, 30);
        LocalDate date3 = LocalDate.now();
        Status status1 = new Status(false, date1);
        Status status2 = new Status(false, date2);
        Status status3 = new Status(false, date3);
        //ACT
        areaSensor1.getListOfStatus().addStatusToSensor(status1);
        areaSensor1.getListOfStatus().addStatusToSensor(status2);
        areaSensor1.getListOfStatus().addStatusToSensor(status3);
        ResponseEntity<Object> result = ctrl.getListOfSensorsActivate(geographicArea.getName());
        //ASSERT
        assertEquals(HttpStatus.NOT_ACCEPTABLE, result.getStatusCode());
    }

    @Test
    void getListOfSensorsActivateTestOk() {
        //ARRANGE
        GeographicArea geographicArea = new GeographicArea("Porto", "city",
                new OccupationArea(new Location(10, 20, 30), 0.5, 0.5),
                new GeographicAreaType("city"));
        AreaSensor areaSensor1 = new AreaSensor("1", "s1", new Location(10.0001, 20.00001, 0),
                new SensorType("temp"), LocalDate.of(2018, 12, 10), "C");
        LocalDate date1 = LocalDate.of(2019, 1, 29);
        LocalDate date2 = LocalDate.of(2019, 1, 30);
        Status status1 = new Status(false, date1);
        Status status2 = new Status(true, date2);
        //ACT
        areaSensor1.addStatus(status1);
        areaSensor1.addStatus(status2);
        geographicAreaDomainService.add(geographicArea);
        geographicArea.addSensorToList(areaSensor1);
        MapperListOfAreaSensor.toDto(geographicArea.getListOfAreaSensors());
        GeographicAreaDto g1dto = MapperGeographicArea.toDto(geographicArea);
        ResponseEntity<Object> result = ctrl.getListOfSensorsActivate(g1dto.getName());
        //ASSERT
        assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    @Test
    void getListOfSensorsActivateTestNotFoundGA() {
        //ARRANGE
        GeographicArea geographicArea = new GeographicArea("Porto", "city",
                new OccupationArea(new Location(10, 20, 30), 0.5, 0.5),
                new GeographicAreaType("city"));
        AreaSensor areaSensor1 = new AreaSensor("1", "s1", new Location(10.0001, 20.00001, 0),
                new SensorType("temp"), LocalDate.of(2018, 12, 10), "C");
        LocalDate date1 = LocalDate.of(2019, 1, 29);
        LocalDate date2 = LocalDate.of(2019, 1, 30);
        Status status1 = new Status(false, date1);
        Status status2 = new Status(true, date2);
        //ACT
        areaSensor1.addStatus(status1);
        areaSensor1.addStatus(status2);
        geographicAreaDomainService.add(geographicArea);
        geographicArea.addSensorToList(areaSensor1);
        MapperListOfAreaSensor.toDto(geographicArea.getListOfAreaSensors());
        GeographicAreaDto g1dto = MapperGeographicArea.toDto(geographicArea);
        ResponseEntity<Object> result = ctrl.getListOfSensorsActivate("Lisbon");
        //ASSERT
        assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
    }

    @Test
    void getListOfSensorsDtoFromGATestOk() {
        //ARRANGE
        GeographicArea g1 = new GeographicArea("Porto", "city",
                new OccupationArea(new Location(10, 20, 30), 0.5, 0.5),
                new GeographicAreaType("city"));
        AreaSensor areaSensor1 = new AreaSensor("1", "s1", new Location(10.0001, 20.00001, 0),
                new SensorType("temp"), LocalDate.of(2018, 12, 10), "C");
        AreaSensor areaSensor2 = new AreaSensor("1", "s2", new Location(10.0001, 20.00001, 0),
                new SensorType("temp"), LocalDate.of(2018, 12, 10), "C");
        //ACT
        geographicAreaDomainService.add(g1);
        g1.addSensorToList(areaSensor1);
        g1.addSensorToList(areaSensor2);
        MapperListOfAreaSensor.toDto(g1.getListOfAreaSensors());
        GeographicAreaDto g1dto = MapperGeographicArea.toDto(g1);
        ResponseEntity<Object> result = ctrl.getListOfSensorsDtoFromGA(g1dto.getName());
        //ASSERT
        assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    @Test
    void getListOfSensorsDtoFromGATestNotFound() {
        //ACT
        ResponseEntity<Object> result = ctrl.getListOfSensorsDtoFromGA("g1");
        //ASSERT
        assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
    }

    @Test
    void getListOfSensorsDtoFromGATestNotAcceptable() {
        //ARRANGE
        GeographicArea g1 = new GeographicArea("Porto", "city",
                new OccupationArea(new Location(10, 20, 30), 0.5, 0.5),
                new GeographicAreaType("city"));
        geographicAreaDomainService.add(g1);
        MapperListOfAreaSensor.toDto(g1.getListOfAreaSensors());
        GeographicAreaDto g1dto = MapperGeographicArea.toDto(g1);
        ResponseEntity<Object> result = ctrl.getListOfSensorsDtoFromGA(g1dto.getName());
        //ASSERT
        assertEquals(HttpStatus.NOT_ACCEPTABLE, result.getStatusCode());
    }

    @Test
    void removeSensorFromGATestOk() {
        //ARRANGE
        GeographicArea geographicArea = new GeographicArea("Porto", "city",
                new OccupationArea(new Location(10, 20, 30), 0.5, 0.5),
                new GeographicAreaType("city"));
        AreaSensor areaSensor1 = new AreaSensor("1", "s1", new Location(10.0001, 20.00001, 0),
                new SensorType("temp"), LocalDate.of(2018, 12, 10), "C");
        //ACT
        geographicArea.addSensorToList(areaSensor1);
        geographicAreaDomainService.add(geographicArea);
        MapperGeographicArea.toDto(geographicArea);
        MapperAreaSensor.toDto(areaSensor1);
        ResponseEntity<Object> result = ctrl.removeSensorFromGA(geographicArea.getName(), areaSensor1.getIdOfAreaSensor());
        //ASSERT
        assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    @Test
    void removeSensorFromGATestNotFoundGA() {
        //ARRANGE
        GeographicArea geographicArea = new GeographicArea("Porto", "city",
                new OccupationArea(new Location(10, 20, 30), 0.5, 0.5),
                new GeographicAreaType("city"));
        AreaSensor areaSensor1 = new AreaSensor("1", "s1", new Location(10.0001, 20.00001, 0),
                new SensorType("temp"), LocalDate.of(2018, 12, 10), "C");
        //ACT
        geographicArea.addSensorToList(areaSensor1);
        geographicAreaDomainService.add(geographicArea);
        MapperGeographicArea.toDto(geographicArea);
        MapperAreaSensor.toDto(areaSensor1);
        ResponseEntity<Object> result = ctrl.removeSensorFromGA("Lisbos", areaSensor1.getIdOfAreaSensor());
        //ASSERT
        assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
    }

    @Test
    void removeSensorFromGATestNotFoundSensor() {
        //ARRANGE
        GeographicArea geographicArea = new GeographicArea("Porto", "city",
                new OccupationArea(new Location(10, 20, 30), 0.5, 0.5),
                new GeographicAreaType("city"));
        AreaSensor areaSensor1 = new AreaSensor("1", "s1", new Location(10.0001, 20.00001, 0),
                new SensorType("temp"), LocalDate.of(2018, 12, 10), "C");
        //ACT
        geographicArea.addSensorToList(areaSensor1);
        geographicAreaDomainService.add(geographicArea);
        MapperGeographicArea.toDto(geographicArea);
        MapperAreaSensor.toDto(areaSensor1);
        ResponseEntity<Object> result = ctrl.removeSensorFromGA(geographicArea.getName(), "5656");
        //ASSERT
        assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
    }
}
