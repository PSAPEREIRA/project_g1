package pt.ipp.isep.dei.project1.controllersweb.housemonitoring;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import pt.ipp.isep.dei.project1.dto.sensordto.ReadingDto;
import pt.ipp.isep.dei.project1.mapper.MapperReading;
import pt.ipp.isep.dei.project1.model.geographicarea.GeographicArea;
import pt.ipp.isep.dei.project1.model.geographicarea.GeographicAreaType;
import pt.ipp.isep.dei.project1.model.geographicarea.Location;
import pt.ipp.isep.dei.project1.model.geographicarea.OccupationArea;
import pt.ipp.isep.dei.project1.model.house.Address;
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
class GetTheLastColdestDayInGivenPeriodRestControllerTest {


    @Mock
    private HouseDomainService houseDomainService;
    @Mock
    private HouseRepository houseRepository;
    @Mock
    private GeographicAreaDomainService geographicAreaDomainService;
    @Mock
    private GeographicAreaRepository geographicAreaRepository;

    private GetTheLastColdestDayInGivenPeriodRestController ctrl;

    @BeforeEach
    void initUseCase() {
        houseDomainService = new HouseDomainService(houseRepository);
        geographicAreaDomainService = new GeographicAreaDomainService(geographicAreaRepository);
        ctrl = new GetTheLastColdestDayInGivenPeriodRestController(houseDomainService, geographicAreaDomainService);
    }

    @Test
    void testGetLastColdestDayInGivenPeriod() throws Exception {
        //Arrange
        GeographicArea geographicArea = new GeographicArea("Porto", "City", new OccupationArea
                (new Location(10, 20, 30), 0.5, 0.5),
                new GeographicAreaType("City"));
        House h1 = new House("Casa", new Location(40.7486, -73.9864, 0),geographicArea.getName());
        AreaSensor s1 = new AreaSensor("1","sensor Teste",new Location(10, 20, 30),new SensorType("temperature"), LocalDate.of(2018,12,10),"l/m2");
        ListOfReadings lRead1 = new ListOfReadings();
        LocalDateTime date1 = LocalDateTime.of(2019, 1, 16, 12, 00);
        Reading r1 = new Reading(date1, 4);
        LocalDateTime date2 = LocalDateTime.of(2019, 1, 19, 12, 00);
        Reading r2 = new Reading(date2, 2);
        LocalDateTime date3 = LocalDateTime.of(2019, 1, 21, 12, 5);
        Reading r3 = new Reading(date3, 1);
        LocalDateTime date4 = LocalDateTime.of(2019, 1, 21, 12, 10);
        Reading r4 = new Reading(date4, -2);
        LocalDateTime date5 = LocalDateTime.of(2019, 1, 22, 12, 10);
        Reading r5 = new Reading(date5, 2);
        LocalDateTime date6 = LocalDateTime.of(2019, 1, 22, 12, 10);
        Reading r6 = new Reading(date6, 2);
        //Act
        geographicArea.addSensorToList(s1);
        lRead1.addReading(r1);
        lRead1.addReading(r2);
        lRead1.addReading(r3);
        lRead1.addReading(r4);
        lRead1.addReading(r5);
        lRead1.addReading(r6);
        s1.setListOfReadings(lRead1);
        ReadingDto expectedResult = MapperReading.toDto(r3);
        geographicAreaDomainService.add(geographicArea);
        houseDomainService.add(h1);

        ResponseEntity result = ctrl.getLastColdestDayInGivenPeriodDto(date1.toLocalDate(),date6.toLocalDate());
        //Assert
        assertEquals(expectedResult.getDateTime().toLocalDate(),result.getBody());
    }

    @Test
    void testGetLastColdestDayInGivenPeriodNoContent() throws Exception {
        //Arrange
        GeographicArea geographicArea = new GeographicArea("Porto", "City", new OccupationArea
                (new Location(10, 20, 30), 0.5, 0.5),
                new GeographicAreaType("City"));
        House h1 = new House("Casa", new Location(40.7486, -73.9864, 0),geographicArea.getName());
        AreaSensor s1 = new AreaSensor("1","sensor Teste",new Location(10, 20, 30),new SensorType("temperature"), LocalDate.of(2018,12,10),"l/m2");
        ListOfReadings lRead1 = new ListOfReadings();
        LocalDateTime date1 = LocalDateTime.of(2019, 1, 16, 12, 00);
        Reading r1 = new Reading(date1, 4);
        LocalDateTime date2 = LocalDateTime.of(2019, 1, 19, 12, 00);
        Reading r2 = new Reading(date2, 1.9);
        LocalDateTime date3 = LocalDateTime.of(2019, 1, 21, 12, 5);
        Reading r3 = new Reading(date3, 1.3);
        LocalDateTime date4 = LocalDateTime.of(2019, 1, 21, 12, 10);
        Reading r4 = new Reading(date4, -2);
        LocalDateTime date5 = LocalDateTime.of(2019, 1, 22, 12, 10);
        Reading r5 = new Reading(date5, 2);
        LocalDateTime date6 = LocalDateTime.of(2019, 1, 22, 12, 10);
        Reading r6 = new Reading(date6, 1.5);
        //Act
        geographicArea.addSensorToList(s1);
        lRead1.addReading(r1);
        lRead1.addReading(r2);
        lRead1.addReading(r3);
        lRead1.addReading(r4);
        lRead1.addReading(r5);
        lRead1.addReading(r6);
        s1.setListOfReadings(lRead1);
       // ReadingDto expectedResult = MapperReading.toDto(r3);
      //  geographicAreaRepo.add(geographicArea);
        houseDomainService.add(h1);

        ResponseEntity result = ctrl.getLastColdestDayInGivenPeriodDto(date1.toLocalDate(),date6.toLocalDate());
        HttpStatus expectedResult = HttpStatus.NOT_ACCEPTABLE;
        //Assert
        assertEquals(expectedResult,result.getStatusCode());
    }

    @Test
    void testGetLastColdestDayInGivenPeriodNoSensors() throws Exception {
        //Arrange
        GeographicArea geographicArea = new GeographicArea("Porto", "City", new OccupationArea
                (new Location(10, 20, 30), 0.5, 0.5),
                new GeographicAreaType("City"));
        Location l1 = new Location(40.7486, -73.9864, 0);
        House house = new House("aaaa", l1, "Porto", new Address("1", "1", "1", "123", "Portugal"));        AreaSensor s1 = new AreaSensor("1","sensor Teste",new Location(10, 20, 30),new SensorType("temperature"), LocalDate.of(2018,12,10),"l/m2");
        LocalDateTime date1 = LocalDateTime.of(2019, 1, 16, 12, 00);

        LocalDateTime date6 = LocalDateTime.of(2019, 1, 22, 12, 10);
        houseDomainService.add(house);

        ResponseEntity result = ctrl.getLastColdestDayInGivenPeriodDto(date1.toLocalDate(),date6.toLocalDate());
        //Assert
        assertEquals(HttpStatus.NOT_ACCEPTABLE,result.getStatusCode());
    }

    @Test
    void testGetLastColdestDayInGivenPeriodNoGA() throws Exception {
        //Arrange
        House house = new House();
        LocalDateTime date1 = LocalDateTime.of(2019, 1, 16, 12, 00);

        LocalDateTime date6 = LocalDateTime.of(2019, 1, 22, 12, 10);
        houseDomainService.add(house);

        ResponseEntity result = ctrl.getLastColdestDayInGivenPeriodDto(date1.toLocalDate(),date6.toLocalDate());
        //Assert
        assertEquals(HttpStatus.NOT_ACCEPTABLE,result.getStatusCode());
    }

    @Test
    void testGetLastColdestDayInGivenPeriodNullDate() throws Exception {
        //Arrange
        GeographicArea geographicArea = new GeographicArea("Porto", "City", new OccupationArea
                (new Location(10, 20, 30), 0.5, 0.5),
                new GeographicAreaType("City"));
        House h1 = new House("Casa", new Location(40.7486, -73.9864, 0),geographicArea.getName());
        AreaSensor s1 = new AreaSensor("1","sensor Teste",new Location(10, 20, 30),new SensorType("temperature"), LocalDate.of(2018,12,10),"l/m2");
        ListOfReadings lRead1 = new ListOfReadings();
        LocalDateTime date1 = LocalDateTime.of(2019, 1, 16, 12, 00);
        Reading r1 = new Reading(date1, 4);
        LocalDateTime date2 = LocalDateTime.of(2019, 1, 19, 12, 00);
        Reading r2 = new Reading(date2, 2);
        LocalDateTime date3 = LocalDateTime.of(2019, 1, 21, 12, 5);
        Reading r3 = new Reading(date3, 1);
        LocalDateTime date4 = LocalDateTime.of(2019, 1, 21, 12, 10);
        Reading r4 = new Reading(date4, -2);
        LocalDateTime date5 = LocalDateTime.of(2019, 1, 22, 12, 10);
        Reading r5 = new Reading(date5, 2);
        LocalDateTime date6 = LocalDateTime.of(2019, 1, 22, 12, 10);
        Reading r6 = new Reading(date6, 2);
        //Act
        geographicArea.addSensorToList(s1);
        lRead1.addReading(r1);
        lRead1.addReading(r2);
        lRead1.addReading(r3);
        lRead1.addReading(r4);
        lRead1.addReading(r5);
        lRead1.addReading(r6);
        s1.setListOfReadings(lRead1);
        ReadingDto expectedResult = MapperReading.toDto(r3);
        geographicAreaDomainService.add(geographicArea);
        houseDomainService.add(h1);

        ResponseEntity result = ctrl.getLastColdestDayInGivenPeriodDto(null,null);
        //Assert
        assertEquals(HttpStatus.OK,result.getStatusCode());
    }

    @Test
    void testGetLastColdestDayInGivenPeriodOutOfPeriod() throws Exception {
        //Arrange
        GeographicArea geographicArea = new GeographicArea("Porto", "City", new OccupationArea
                (new Location(10, 20, 30), 0.5, 0.5),
                new GeographicAreaType("City"));
        House h1 = new House("Casa", new Location(40.7486, -73.9864, 0),geographicArea.getName());
        AreaSensor s1 = new AreaSensor("1","sensor Teste",new Location(10, 20, 30),new SensorType("temperature"), LocalDate.of(2018,12,10),"l/m2");
        ListOfReadings lRead1 = new ListOfReadings();
        LocalDateTime date1 = LocalDateTime.of(2019, 1, 16, 12, 00);
        Reading r1 = new Reading(date1, 4);
        LocalDateTime date2 = LocalDateTime.of(2019, 1, 19, 12, 00);
        Reading r2 = new Reading(date2, 2);
        LocalDateTime date3 = LocalDateTime.of(2019, 1, 21, 12, 5);
        Reading r3 = new Reading(date3, 1);
        LocalDateTime date4 = LocalDateTime.of(2019, 1, 21, 12, 10);
        Reading r4 = new Reading(date4, -2);
        LocalDateTime date5 = LocalDateTime.of(2019, 1, 22, 12, 10);
        Reading r5 = new Reading(date5, 2);
        LocalDateTime date6 = LocalDateTime.of(2019, 1, 22, 12, 10);
        Reading r6 = new Reading(date6, 2);
        //Act
        geographicArea.addSensorToList(s1);
        lRead1.addReading(r1);
        lRead1.addReading(r2);
        lRead1.addReading(r3);
        lRead1.addReading(r4);
        lRead1.addReading(r5);
        lRead1.addReading(r6);
        s1.setListOfReadings(lRead1);
        geographicAreaDomainService.add(geographicArea);
        houseDomainService.add(h1);
        ResponseEntity result = ctrl.getLastColdestDayInGivenPeriodDto(LocalDate.of(2018,12,8),LocalDate.of(2018,12,10));
        //Assert
        assertEquals(HttpStatus.OK,result.getStatusCode());
    }

    @Test
    void testGetLastColdestDayInGivenPeriodOutOfPeriodBodyTest() throws Exception {
        //Arrange
        GeographicArea geographicArea = new GeographicArea("Porto", "City", new OccupationArea
                (new Location(10, 20, 30), 0.5, 0.5),
                new GeographicAreaType("City"));
        House h1 = new House("Casa", new Location(40.7486, -73.9864, 0),geographicArea.getName());
        AreaSensor s1 = new AreaSensor("1","sensor Teste",new Location(10, 20, 30),new SensorType("temperature"), LocalDate.of(2018,12,10),"l/m2");
        ListOfReadings lRead1 = new ListOfReadings();
        LocalDateTime date1 = LocalDateTime.of(2019, 1, 16, 12, 00);
        Reading r1 = new Reading(date1, 4);
        LocalDateTime date2 = LocalDateTime.of(2019, 1, 19, 12, 00);
        Reading r2 = new Reading(date2, 2);
        LocalDateTime date3 = LocalDateTime.of(2019, 1, 21, 12, 5);
        Reading r3 = new Reading(date3, 1);
        LocalDateTime date4 = LocalDateTime.of(2019, 1, 21, 12, 10);
        Reading r4 = new Reading(date4, -2);
        LocalDateTime date5 = LocalDateTime.of(2019, 1, 22, 12, 10);
        Reading r5 = new Reading(date5, 2);
        LocalDateTime date6 = LocalDateTime.of(2019, 1, 22, 12, 10);
        Reading r6 = new Reading(date6, 2);
        //Act
        geographicArea.addSensorToList(s1);
        lRead1.addReading(r1);
        lRead1.addReading(r2);
        lRead1.addReading(r3);
        lRead1.addReading(r4);
        lRead1.addReading(r5);
        lRead1.addReading(r6);
        s1.setListOfReadings(lRead1);
        geographicAreaDomainService.add(geographicArea);
        houseDomainService.add(h1);
        ResponseEntity result = ctrl.getLastColdestDayInGivenPeriodDto(LocalDate.of(2018,12,8),LocalDate.of(2018,12,10));
        ResponseEntity expectedResult = new ResponseEntity<>("Please insert values for both the start of the period and the end.", HttpStatus.OK);
        //Assert
        assertEquals(expectedResult,result);
    }

}