package pt.ipp.isep.dei.project1.controllers.house;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pt.ipp.isep.dei.project1.dto.sensordto.ListOfAreaSensorsDto;
import pt.ipp.isep.dei.project1.dto.sensordto.ReadingDto;
import pt.ipp.isep.dei.project1.mapper.MapperListOfAreaSensor;
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

class HouseAreaTemperatureControllerTest {

    @Mock
    private HouseDomainService houseDomainService;
    @Mock
    private HouseRepository houseRepository;
    @Mock
    private GeographicAreaDomainService geographicAreaDomainService;
    @Mock
    private GeographicAreaRepository geographicAreaRepository;

    private HouseAreaTemperatureController ctrl;



    @BeforeEach
    void initUseCase() {
        houseDomainService = new HouseDomainService(houseRepository);
        geographicAreaDomainService = new GeographicAreaDomainService(geographicAreaRepository);
        ctrl = new HouseAreaTemperatureController(houseDomainService, geographicAreaDomainService);
    }


    @Test
    void testCheckIfThereIsTemperatureSensorsInListTrue() throws Exception {
        //Arrange
        GeographicArea geographicArea = new GeographicArea("Porto", "City", new OccupationArea
                (new Location(10, 20, 30), 0.5, 0.5),
                new GeographicAreaType("City"));
        House h1 = new House("Casa", new Location(40.7486, -73.9864, 0), geographicArea.getName());
        AreaSensor s1 = new AreaSensor("1","sensor Teste", new Location(10, 20, 30), new SensorType("temperature"), LocalDate.of(2018, 12, 10), "l/m2");
        //Act
        geographicArea.addSensorToList(s1);
        geographicAreaDomainService.add(geographicArea);
        houseDomainService.add(h1);
        HouseAreaTemperatureController houseAreaTemperatureController = new HouseAreaTemperatureController(houseDomainService, geographicAreaDomainService);
        boolean result = houseAreaTemperatureController.checkIfHaveSensorsOnHouseAreaWithTypeAndReadings();
        //Assert
        assertFalse(result);
    }

    @Test
    void testCheckIfThereIsTemperatureSensorsInListFalse() throws Exception {
        //Arrange
        GeographicArea geographicArea = new GeographicArea("Porto", "City", new OccupationArea
                (new Location(10, 20, 30), 0.5, 0.5),
                new GeographicAreaType("City"));
        House h1 = new House("Casa", new Location(40.7486, -73.9864, 0), geographicArea.getName());
        HouseAreaTemperatureController houseAreaTemperatureController = new HouseAreaTemperatureController(houseDomainService, geographicAreaDomainService);
        AreaSensor s1 = new AreaSensor("1","sensor Teste", new Location(10, 20, 30), new SensorType("rainfall"), LocalDate.of(2018, 12, 10), "l/m2");
        //Act
        geographicArea.addSensorToList(s1);
        ListOfAreaSensorsDto expectedResult = MapperListOfAreaSensor.toDto(geographicArea.getListOfAreaSensors());
        geographicAreaDomainService.add(geographicArea);
        houseDomainService.add(h1);
        boolean result = houseAreaTemperatureController.checkIfHaveSensorsOnHouseAreaWithTypeAndReadings();
        //Assert
        assertFalse(result);
    }

    @Test
    void testCheckIfThereIsTemperatureSensorsInListFalseEmptyList() throws Exception {
        //Arrange
        GeographicArea geographicArea = new GeographicArea("Porto", "City", new OccupationArea
                (new Location(10, 20, 30), 0.5, 0.5),
                new GeographicAreaType("City"));
        House h1 = new House("Casa", new Location(40.7486, -73.9864, 0), geographicArea.getName());
        HouseAreaTemperatureController houseAreaTemperatureController = new HouseAreaTemperatureController(houseDomainService, geographicAreaDomainService);
        //Act
        ListOfAreaSensorsDto expectedResult = MapperListOfAreaSensor.toDto(geographicArea.getListOfAreaSensors());
        geographicAreaDomainService.add(geographicArea);
        houseDomainService.add(h1);
        boolean result = houseAreaTemperatureController.checkIfHaveSensorsOnHouseAreaWithTypeAndReadings();
        //Assert
        assertFalse(result);
    }


    @Test
    void testGetFirstHottestDayInGivenPeriodNoSensorsOfTemperature() throws Exception {
        //Arrange
        GeographicArea geographicArea = new GeographicArea("Porto", "City", new OccupationArea
                (new Location(10, 20, 30), 0.5, 0.5),
                new GeographicAreaType("City"));
        House house = new House("House", new Location(40.7486, -73.9864, 0), geographicArea.getName());
        AreaSensor areaSensor = new AreaSensor("1","Test AreaSensor", new Location(10, 20, 30),
                new SensorType("Rainfall"), LocalDate.of(2018, 12, 10), "l/m2");
        ListOfReadings listOfReadings = new ListOfReadings();
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
        geographicArea.addSensorToList(areaSensor);
        listOfReadings.addReading(r1);
        listOfReadings.addReading(r2);
        listOfReadings.addReading(r3);
        listOfReadings.addReading(r4);
        listOfReadings.addReading(r5);
        listOfReadings.addReading(r6);
        areaSensor.setListOfReadings(listOfReadings);
        geographicAreaDomainService.add(geographicArea);
        houseDomainService.add(house);
        HouseAreaTemperatureController houseAreaTemperatureController =
                new HouseAreaTemperatureController(houseDomainService, geographicAreaDomainService);
        ReadingDto expectedResult = null;
        ReadingDto result = houseAreaTemperatureController.getFirstHottestDayInGivenPeriodDto
                (date1.toLocalDate(), date6.toLocalDate());
        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetFirstHottestDayInGivenPeriodEmptyPeriod() throws Exception {
        //Arrange
        GeographicArea geographicArea = new GeographicArea("Porto", "City", new OccupationArea
                (new Location(10, 20, 30), 0.5, 0.5),
                new GeographicAreaType("City"));
        House house = new House("House", new Location(40.7486, -73.9864, 0), geographicArea.getName());
        AreaSensor areaSensor = new AreaSensor("1","Test AreaSensor", new Location(10, 20, 30),
                new SensorType("temperature"), LocalDate.of(2018, 12, 10), "l/m2");
        ListOfReadings listOfReadings = new ListOfReadings();
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
        geographicArea.addSensorToList(areaSensor);
        listOfReadings.addReading(r1);
        listOfReadings.addReading(r2);
        listOfReadings.addReading(r3);
        listOfReadings.addReading(r4);
        listOfReadings.addReading(r5);
        listOfReadings.addReading(r6);
        areaSensor.setListOfReadings(listOfReadings);

        geographicAreaDomainService.add(geographicArea);
        houseDomainService.add(house);
        HouseAreaTemperatureController houseAreaTemperatureController =
                new HouseAreaTemperatureController(houseDomainService, geographicAreaDomainService);
        int expectedResult = 1;
        ReadingDto result = houseAreaTemperatureController.getFirstHottestDayInGivenPeriodDto
                (date7.toLocalDate(), date8.toLocalDate());
        //Assert
        assertEquals(expectedResult, result.getStatus());
    }


    @Test
    void testGetFirstHottestDayInGivenPeriod() throws Exception {
        //Arrange
        GeographicArea geographicArea = new GeographicArea("Porto", "City", new OccupationArea
                (new Location(10, 20, 30), 0.5, 0.5),
                new GeographicAreaType("City"));
        House house = new House("House", new Location(40.7486, -73.9864, 0),
                geographicArea.getName());
        AreaSensor areaSensor = new AreaSensor("1","Test AreaSensor", new Location(10, 20, 30),
                new SensorType("temperature"), LocalDate.of(2018, 12, 10), "l/m2");
        ListOfReadings listOfReadings = new ListOfReadings();
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
        geographicArea.addSensorToList(areaSensor);
        listOfReadings.addReading(r1);
        listOfReadings.addReading(r2);
        listOfReadings.addReading(r3);
        listOfReadings.addReading(r4);
        listOfReadings.addReading(r5);
        listOfReadings.addReading(r6);
        areaSensor.setListOfReadings(listOfReadings);

        geographicAreaDomainService.add(geographicArea);
        houseDomainService.add(house);
        HouseAreaTemperatureController houseAreaTemperatureController =
                new HouseAreaTemperatureController(houseDomainService, geographicAreaDomainService);
        ReadingDto expectedResultReading = MapperReading.toDto(r1);
        ReadingDto resultReading = houseAreaTemperatureController.getFirstHottestDayInGivenPeriodDto(
                date1.toLocalDate(), date1.toLocalDate());
        //Assert
        double expectedResult = expectedResultReading.getValue();
        double result = resultReading.getValue();

        assertEquals(expectedResult, result);
    }

    @Test
    void testGetFirstHottestDayInGivenPeriodNoGeoArea() throws Exception {
        //Arrange
        GeographicArea geographicArea = new GeographicArea("Porto", "City", new OccupationArea
                (new Location(10, 20, 30), 0.5, 0.5),
                new GeographicAreaType("City"));
        House house = new House("House", new Location(40.7486, -73.9864, 0),
                geographicArea.getName());
        AreaSensor areaSensor = new AreaSensor("1","Test AreaSensor", new Location(10, 20, 30),
                new SensorType("temperature"), LocalDate.of(2018, 12, 10), "l/m2");
        ListOfReadings listOfReadings = new ListOfReadings();
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
        geographicArea.addSensorToList(areaSensor);
        listOfReadings.addReading(r1);
        listOfReadings.addReading(r2);
        listOfReadings.addReading(r3);
        listOfReadings.addReading(r4);
        listOfReadings.addReading(r5);
        listOfReadings.addReading(r6);
        areaSensor.setListOfReadings(listOfReadings);

      //  geographicAreaRepo.add(geographicArea);
        houseDomainService.add(house);
        HouseAreaTemperatureController houseAreaTemperatureController =
                new HouseAreaTemperatureController(houseDomainService, geographicAreaDomainService);
        ReadingDto expectedResultReading = MapperReading.toDto(r1);
        ReadingDto resultReading = houseAreaTemperatureController.getFirstHottestDayInGivenPeriodDto(
                date1.toLocalDate(), date1.toLocalDate());
        //Assert
        double expectedResult = Double.NaN;

        assertNull(resultReading);
    }

    @Test
    void checkIfGetGeographicAreaOfTheHouse(){
        GeographicArea geographicArea = new GeographicArea("Porto", "city", new OccupationArea(new Location(10, 20, 30), 0.5, 0.5), new GeographicAreaType("city"));
        House house = new House("house1", new Location(10, 6, 0), geographicArea.getName());

        houseDomainService.add(house);

        String result = ctrl.getGeographicAreaOfTheHouse();

        String expectedResult = geographicArea.getName();
        assertEquals(expectedResult,result);
    }
}