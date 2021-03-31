package pt.ipp.isep.dei.project1.controllersweb.housemonitoring;

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
import pt.ipp.isep.dei.project1.model.house.House;
import pt.ipp.isep.dei.project1.model.repositories.GeographicAreaDomainService;
import pt.ipp.isep.dei.project1.model.repositories.GeographicAreaRepository;
import pt.ipp.isep.dei.project1.model.repositories.HouseDomainService;
import pt.ipp.isep.dei.project1.model.repositories.HouseRepository;
import pt.ipp.isep.dei.project1.model.sensor.AreaSensor;
import pt.ipp.isep.dei.project1.model.sensor.ListOfReadings;
import pt.ipp.isep.dei.project1.model.sensor.Reading;
import pt.ipp.isep.dei.project1.model.sensor.SensorType;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class GetTheCurrentTemperatureInHouseAreaRestControllerTest {

    @Mock
    private HouseDomainService houseDomainService;
    @Mock
    private HouseRepository houseRepository;
    @Mock
    private GeographicAreaDomainService geographicAreaDomainService;
    @Mock
    private GeographicAreaRepository geographicAreaRepository;

    private GetTheCurrentTemperatureInHouseAreaRestController ctrl;

    @BeforeEach
    void initUseCase() {
        houseDomainService = new HouseDomainService(houseRepository);
        geographicAreaDomainService = new GeographicAreaDomainService(geographicAreaRepository);
        ctrl = new GetTheCurrentTemperatureInHouseAreaRestController(geographicAreaDomainService, houseDomainService);
    }

    @Test
    void testGetCurrentTemperature (){
        //Arrange
        GeographicArea geographicArea = new GeographicArea("Porto", "City", new OccupationArea
                (new Location(10, 20, 30), 0.5, 0.5),
                new GeographicAreaType("City"));
        House h1 = new House("Casa", new Location(40.7486, -73.9864, 0), geographicArea.getName());
        houseDomainService.add(h1);
        AreaSensor s1 = new AreaSensor("1","sensor Test", new Location(10, 20, 30), new SensorType("temperature"), LocalDate.of(2018, 12, 10), "l/m2");
        ListOfReadings lRead1 = new ListOfReadings();
        LocalDateTime date1 = LocalDateTime.of(2019, 1, 15, 12, 00);
        Reading r1 = new Reading(date1, 18);
        LocalDateTime date2 = LocalDateTime.of(2019, 1, 15, 12, 3);
        Reading r2 = new Reading(date2, 15);
        LocalDateTime date3 = LocalDateTime.of(2019, 1, 17, 12, 5);
        Reading r3 = new Reading(date3, 22);
        LocalDateTime date4 = LocalDateTime.of(2019, 1, 17, 12, 6);
        Reading r4 = new Reading(date4, 23);
        LocalDateTime date5 = LocalDateTime.of(2019, 1, 14, 12, 10);
        Reading r5 = new Reading(date5, 15);
        LocalDateTime date6 = LocalDateTime.of(2019, 1, 14, 12, 15);
        Reading r6 = new Reading(date6, 25);
        //ACT
        geographicArea.addSensorToList(s1);
        lRead1.addReading(r1);
        lRead1.addReading(r2);
        lRead1.addReading(r3);
        lRead1.addReading(r4);
        lRead1.addReading(r5);
        lRead1.addReading(r6);
        s1.setListOfReadings(lRead1);
        geographicAreaDomainService.add(geographicArea);
        ResponseEntity<Object> result = ctrl.getCurrentRoomTemperature();
        //ASSERT
        assertEquals(HttpStatus.OK, result.getStatusCode());

    }


    @Test
    void testGetCurrentTemperatureNoReadings (){
        //Arrange
        GeographicArea geographicArea = new GeographicArea("Porto", "City", new OccupationArea
                (new Location(10, 20, 30), 0.5, 0.5),
                new GeographicAreaType("City"));
        House h1 = new House("Casa", new Location(40.7486, -73.9864, 0), geographicArea.getName());
        houseDomainService.add(h1);
        AreaSensor s1 = new AreaSensor("1","sensor Test", new Location(10, 20, 30), new SensorType("temperature"), LocalDate.of(2018, 12, 10), "l/m2");

        //ACT
        geographicArea.addSensorToList(s1);

        geographicAreaDomainService.add(geographicArea);
        ResponseEntity<Object> result = ctrl.getCurrentRoomTemperature();
        //ASSERT
        assertEquals(HttpStatus.NOT_ACCEPTABLE, result.getStatusCode());

    }

    @Test
    void testGetCurrentTemperatureNoSensors (){
        //Arrange
        GeographicArea geographicArea = new GeographicArea("Porto", "City", new OccupationArea
                (new Location(10, 20, 30), 0.5, 0.5),
                new GeographicAreaType("City"));
        House h1 = new House("Casa", new Location(40.7486, -73.9864, 0), geographicArea.getName());
        houseDomainService.add(h1);
        //ACT
        geographicAreaDomainService.add(geographicArea);
        ResponseEntity<Object> result = ctrl.getCurrentRoomTemperature();
        //ASSERT
        assertEquals(HttpStatus.NOT_ACCEPTABLE, result.getStatusCode());

    }


}