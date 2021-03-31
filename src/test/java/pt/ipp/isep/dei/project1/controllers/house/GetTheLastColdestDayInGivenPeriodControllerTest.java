package pt.ipp.isep.dei.project1.controllers.house;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pt.ipp.isep.dei.project1.dto.sensordto.ReadingDto;
import pt.ipp.isep.dei.project1.mapper.MapperReading;
import pt.ipp.isep.dei.project1.model.geographicarea.GeographicArea;
import pt.ipp.isep.dei.project1.model.geographicarea.GeographicAreaType;
import pt.ipp.isep.dei.project1.model.geographicarea.Location;
import pt.ipp.isep.dei.project1.model.geographicarea.OccupationArea;
import pt.ipp.isep.dei.project1.model.house.House;
import pt.ipp.isep.dei.project1.model.repositories.GeographicAreaRepository;
import pt.ipp.isep.dei.project1.model.repositories.HouseRepository;
import pt.ipp.isep.dei.project1.model.sensor.AreaSensor;
import pt.ipp.isep.dei.project1.model.sensor.ListOfReadings;
import pt.ipp.isep.dei.project1.model.sensor.Reading;
import pt.ipp.isep.dei.project1.model.sensor.SensorType;
import pt.ipp.isep.dei.project1.model.repositories.GeographicAreaDomainService;
import pt.ipp.isep.dei.project1.model.repositories.HouseDomainService;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)

class GetTheLastColdestDayInGivenPeriodControllerTest {

    @Mock
    private HouseDomainService houseDomainService;
    @Mock
    private HouseRepository houseRepository;
    @Mock
    private GeographicAreaDomainService geographicAreaDomainService;
    @Mock
    private GeographicAreaRepository geographicAreaRepository;

    @BeforeEach
    void initUseCase() {
        houseDomainService = new HouseDomainService(houseRepository);
        geographicAreaDomainService = new GeographicAreaDomainService(geographicAreaRepository);
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
        Reading r2 = new Reading(date2, 3);
        LocalDateTime date3 = LocalDateTime.of(2019, 1, 21, 12, 5);
        Reading r3 = new Reading(date3, 1);
        LocalDateTime date4 = LocalDateTime.of(2019, 1, 21, 12, 10);
        Reading r4 = new Reading(date4, -2);
        LocalDateTime date5 = LocalDateTime.of(2019, 1, 22, 12, 10);
        Reading r5 = new Reading(date5, 2);
        LocalDateTime date6 = LocalDateTime.of(2019, 1, 22, 12, 10);
        Reading r6 = new Reading(date6, 1);
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
        HouseAreaTemperatureController getTheLastColdestDayInGivenPeriodController = new HouseAreaTemperatureController(houseDomainService, geographicAreaDomainService);
        ReadingDto result = getTheLastColdestDayInGivenPeriodController.getLastColdestDayInGivenPeriodDto(date1.toLocalDate(),date6.toLocalDate());
        //Assert
        assertEquals(expectedResult.getValue(),result.getValue());

    }

    @Test
    void testGetLastColdestDayInGivenPeriodNoSensorsOfTemperature() throws Exception {
        //Arrange
        GeographicArea geographicArea = new GeographicArea("Porto", "City", new OccupationArea
                (new Location(10, 20, 30), 0.5, 0.5),
                new GeographicAreaType("City"));
        House h1 = new House("Casa", new Location(40.7486, -73.9864, 0),geographicArea.getName());
        AreaSensor s1 = new AreaSensor("1","sensor Teste",new Location(10, 20, 30),new SensorType("rainfall"), LocalDate.of(2018,12,10),"l/m2");
        ListOfReadings lRead1 = new ListOfReadings();
        LocalDateTime date1 = LocalDateTime.of(2019, 1, 16, 12, 00);
        Reading r1 = new Reading(date1, 4);
        LocalDateTime date2 = LocalDateTime.of(2019, 1, 19, 12, 00);
        Reading r2 = new Reading(date2, 3);
        LocalDateTime date3 = LocalDateTime.of(2019, 1, 21, 12, 5);
        Reading r3 = new Reading(date3, 1);
        LocalDateTime date4 = LocalDateTime.of(2019, 1, 21, 12, 10);
        Reading r4 = new Reading(date4, -2);
        LocalDateTime date5 = LocalDateTime.of(2019, 1, 22, 12, 10);
        Reading r5 = new Reading(date5, 2);
        LocalDateTime date6 = LocalDateTime.of(2019, 1, 22, 12, 10);
        Reading r6 = new Reading(date6, 2);
        HouseAreaTemperatureController getTheLastColdestDayInGivenPeriodController = new HouseAreaTemperatureController(houseDomainService, geographicAreaDomainService);
        //Act
        geographicArea.addSensorToList(s1);
        lRead1.addReading(r1);
        lRead1.addReading(r2);
        lRead1.addReading(r3);
        lRead1.addReading(r4);
        lRead1.addReading(r5);
        lRead1.addReading(r6);
        s1.setListOfReadings(lRead1);
        ReadingDto expectedResult = null;
        geographicAreaDomainService.add(geographicArea);
        houseDomainService.add(h1);
        ReadingDto result = getTheLastColdestDayInGivenPeriodController.getLastColdestDayInGivenPeriodDto(date1.toLocalDate(),date6.toLocalDate());
        //Assert
        assertEquals(expectedResult,result);
    }



    @Test
    void testGetLastColdestDayInGivenPeriodEmptyPeriod() throws Exception {
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
        LocalDateTime date7 = LocalDateTime.of(2019, 1, 10, 12, 10);
        LocalDateTime date8 = LocalDateTime.of(2019, 1, 15, 12, 10);
        //Act
        geographicArea.addSensorToList(s1);
        lRead1.addReading(r1);
        lRead1.addReading(r2);
        lRead1.addReading(r3);
        lRead1.addReading(r4);
        lRead1.addReading(r5);
        lRead1.addReading(r6);
        s1.setListOfReadings(lRead1);
        int expectedResult = 1;
        geographicAreaDomainService.add(geographicArea);
        houseDomainService.add(h1);
        HouseAreaTemperatureController getTheLastColdestDayInGivenPeriodController = new HouseAreaTemperatureController(houseDomainService, geographicAreaDomainService);
        ReadingDto result = getTheLastColdestDayInGivenPeriodController.getLastColdestDayInGivenPeriodDto(date7.toLocalDate(),date8.toLocalDate());
        //Assert
        assertEquals(expectedResult,result.getStatus());
    }

    @Test
    void testGetLastColdestDayInGivenPeriodNoGeoArea() throws Exception {
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
        ReadingDto expectedResult = MapperReading.toDto(r3);
       // geographicAreaRepo.add(geographicArea);
        houseDomainService.add(h1);
        HouseAreaTemperatureController getTheLastColdestDayInGivenPeriodController = new HouseAreaTemperatureController(houseDomainService, geographicAreaDomainService);
        ReadingDto result = getTheLastColdestDayInGivenPeriodController.getLastColdestDayInGivenPeriodDto(date1.toLocalDate(),date6.toLocalDate());
        //Assert
        assertNull(result);

    }

}