package pt.ipp.isep.dei.project1.model.repositories;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pt.ipp.isep.dei.project1.dto.geographicareadto.GeographicAreaDto;
import pt.ipp.isep.dei.project1.dto.geographicareadto.GeographicAreaDtoWeb;
import pt.ipp.isep.dei.project1.dto.sensordto.AreaSensorDto;
import pt.ipp.isep.dei.project1.mapper.MapperAreaSensor;
import pt.ipp.isep.dei.project1.mapper.MapperGeographicArea;
import pt.ipp.isep.dei.project1.model.geographicarea.GeographicArea;
import pt.ipp.isep.dei.project1.model.geographicarea.GeographicAreaType;
import pt.ipp.isep.dei.project1.model.geographicarea.Location;
import pt.ipp.isep.dei.project1.model.geographicarea.OccupationArea;
import pt.ipp.isep.dei.project1.model.house.House;
import pt.ipp.isep.dei.project1.model.sensor.AreaSensor;
import pt.ipp.isep.dei.project1.model.sensor.ListOfReadings;
import pt.ipp.isep.dei.project1.model.sensor.Reading;
import pt.ipp.isep.dei.project1.model.sensor.SensorType;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class GeographicAreaDomainServiceTest {

    private GeographicAreaDomainService geographicAreaDomainService;

    @org.mockito.Mock
    private GeographicAreaRepository geographicAreaRepository;

    @Mock
    private HouseDomainService houseDomainService;
    @Mock
    private HouseRepository houseRepository;


    @BeforeEach
    void initMocks() {
        geographicAreaDomainService = new GeographicAreaDomainService(geographicAreaRepository);
        houseDomainService = new HouseDomainService(houseRepository);
    }

    @Test
    void getListOfAreaSensors() {
        GeographicArea geographicArea = new GeographicArea("Porto", "City", new OccupationArea
                (new Location(10, 20, 30), 0.5, 0.5),
                new GeographicAreaType("City"));
        geographicAreaDomainService.add(geographicArea);
        AreaSensor as1 = new AreaSensor("1", "sensor1", new Location(40.7486, -73.9864, 0), new SensorType("temperature"), LocalDate.of(2018, 12, 10), "ºC");
        AreaSensor as2 = new AreaSensor("2", "sensor2", new Location(41.7486, -73.9864, 0), new SensorType("temperature"), LocalDate.of(2018, 12, 10), "ºC");
        as1.setGeographicArea(geographicArea);
        as2.setGeographicArea(geographicArea);
        geographicAreaDomainService.addSensorToList(as1);
        geographicAreaDomainService.addSensorToList(as2);
        List<AreaSensor> result = geographicAreaDomainService.getAllSensors();
        List<AreaSensor> expectedResult = new ArrayList<>();
        expectedResult.add(as1);
        expectedResult.add(as2);
        assertEquals(expectedResult, result);
    }

    @Test
    void addSensorToListFalse() {
        GeographicArea geographicArea = new GeographicArea("Porto", "City", new OccupationArea
                (new Location(10, 20, 30), 0.5, 0.5),
                new GeographicAreaType("City"));
        geographicAreaDomainService.add(geographicArea);
        AreaSensor as1 = new AreaSensor("1", "sensor1", new Location(40.7486, -73.9864, 0), new SensorType("temperature"), LocalDate.of(2018, 12, 10), "ºC");
        AreaSensor as2 = new AreaSensor("2", "sensor2", new Location(41.7486, -73.9864, 0), new SensorType("temperature"), LocalDate.of(2018, 12, 10), "ºC");
        as1.setGeographicArea(geographicArea);
        as2.setGeographicArea(geographicArea);
        geographicAreaDomainService.addSensorToList(as1);
        geographicAreaDomainService.addSensorToList(as2);
        boolean result = geographicAreaDomainService.addSensorToList(as1);
        assertFalse(result);
    }

    @Test
    void addSensorToList() {
        GeographicArea geographicArea = new GeographicArea("Porto", "City", new OccupationArea
                (new Location(10, 20, 30), 0.5, 0.5),
                new GeographicAreaType("City"));
        geographicAreaDomainService.add(geographicArea);
        AreaSensor as1 = new AreaSensor("1", "sensor1", new Location(40.7486, -73.9864, 0), new SensorType("temperature"), LocalDate.of(2018, 12, 10), "ºC");
        AreaSensor as2 = new AreaSensor("2", "sensor2", new Location(41.7486, -73.9864, 0), new SensorType("temperature"), LocalDate.of(2018, 12, 10), "ºC");
        as1.setGeographicArea(geographicArea);
        as2.setGeographicArea(geographicArea);
        geographicAreaDomainService.addSensorToList(as2);
        boolean result = geographicAreaDomainService.addSensorToList(as1);
        assertTrue(result);
    }

    @Test
    void checkIfRemoveSensorTrue() {

        GeographicArea geographicArea = new GeographicArea("Porto", "City", new OccupationArea
                (new Location(10, 20, 30), 0.5, 0.5),
                new GeographicAreaType("City"));
        SensorType sensorType1 = new SensorType("temperature");
        geographicAreaDomainService.add(geographicArea);
        AreaSensor s1 = new AreaSensor("1", "sensor1", new Location(12, 20, 30), sensorType1, LocalDate.of(2018, 12, 10), "ºC");
        AreaSensor s2 = new AreaSensor("2", "sensor2", new Location(12, 20, 30), sensorType1, LocalDate.of(2018, 12, 11), "ºC");
        AreaSensor s3 = new AreaSensor("3", "sensor3", new Location(12, 20, 30), sensorType1, LocalDate.of(2018, 12, 12), "ºC");
        AreaSensor s4 = new AreaSensor("4", "sensor4", new Location(12, 20, 30), sensorType1, LocalDate.of(2018, 12, 13), "ºC");
        s1.setGeographicArea(geographicArea);
        s2.setGeographicArea(geographicArea);
        s3.setGeographicArea(geographicArea);
        s4.setGeographicArea(geographicArea);
        geographicAreaDomainService.addSensorToList(s1);
        geographicAreaDomainService.addSensorToList(s2);
        geographicAreaDomainService.addSensorToList(s3);
        geographicAreaDomainService.addSensorToList(s4);

        boolean result = geographicAreaDomainService.removeSensor(geographicArea.getName(), "1");

        assertTrue(result);
    }


    @Test
    void checkIfRemoveSensorFalse() {

        GeographicArea geographicArea = new GeographicArea("Porto", "City", new OccupationArea
                (new Location(10, 20, 30), 0.5, 0.5),
                new GeographicAreaType("City"));
        SensorType sensorType1 = new SensorType("temperature");
        geographicAreaDomainService.add(geographicArea);
        AreaSensor s1 = new AreaSensor("1", "sensor1", new Location(12, 20, 30), sensorType1, LocalDate.of(2018, 12, 10), "ºC");
        AreaSensor s2 = new AreaSensor("2", "sensor2", new Location(12, 20, 30), sensorType1, LocalDate.of(2018, 12, 11), "ºC");
        AreaSensor s3 = new AreaSensor("3", "sensor3", new Location(12, 20, 30), sensorType1, LocalDate.of(2018, 12, 12), "ºC");
        AreaSensor s4 = new AreaSensor("4", "sensor4", new Location(12, 20, 30), sensorType1, LocalDate.of(2018, 12, 13), "ºC");
        s1.setGeographicArea(geographicArea);
        s2.setGeographicArea(geographicArea);
        s3.setGeographicArea(geographicArea);
        s4.setGeographicArea(geographicArea);
        geographicAreaDomainService.addSensorToList(s2);
        geographicAreaDomainService.addSensorToList(s3);
        geographicAreaDomainService.addSensorToList(s4);

        boolean result = geographicAreaDomainService.removeSensor(geographicArea.getName(), "1");

        assertFalse(result);
    }


    @Test
    void getGeographicAreaDtoByName() {
        GeographicArea geographicArea = new GeographicArea("Porto", "City", new OccupationArea
                (new Location(10, 20, 30), 0.5, 0.5),
                new GeographicAreaType("City"));
        geographicAreaDomainService.add(geographicArea);
        GeographicAreaDto result = geographicAreaDomainService.getGeographicAreaByNameDTO("Porto");
        GeographicAreaDto expectedResult = MapperGeographicArea.toDto(geographicArea);
        assertEquals(expectedResult.getName(), result.getName());
    }


    @Test
    void getGeographicAreaDtoByNameNotExist() {
        GeographicArea geographicArea = new GeographicArea("Porto", "City", new OccupationArea
                (new Location(10, 20, 30), 0.5, 0.5),
                new GeographicAreaType("City"));
        geographicAreaDomainService.add(geographicArea);
        GeographicAreaDto result = geographicAreaDomainService.getGeographicAreaByNameDTO("Braga");
        GeographicAreaDto expectedResult = null;
        assertEquals(expectedResult, result);
    }

    @Test
    void getSensorClosestToHouse() {
        GeographicArea geographicArea = new GeographicArea("Porto", "City", new OccupationArea
                (new Location(10, 20, 30), 0.5, 0.5),
                new GeographicAreaType("City"));
        AreaSensor s1 = new AreaSensor("1", "sensor1", new Location(10, 20, 30), new SensorType("temperature"), LocalDate.of(2018, 12, 10), "ºC");
        Reading reading = new Reading(LocalDateTime.of(2018, 12, 10, 0, 0), 10);
        s1.addReading(reading);
        geographicArea.addSensorToList(s1);
        geographicAreaDomainService.add(geographicArea);
        Location location = new Location(10, 10, 20);
        Boolean result = geographicAreaDomainService.checkIfHaveSensorsOnHouseAreaWithTypeAndReadings("Porto", location, new SensorType("Temperature"));
        assertTrue(result);
    }

    @Test
    void getSensorClosestToHouseNoReadingsOnSensor() {
        GeographicArea geographicArea = new GeographicArea("Porto", "City", new OccupationArea
                (new Location(10, 20, 30), 0.5, 0.5),
                new GeographicAreaType("City"));
        AreaSensor s1 = new AreaSensor("1", "sensor1", new Location(10, 20, 30), new SensorType("temperature"), LocalDate.of(2018, 12, 10), "ºC");
        geographicArea.addSensorToList(s1);
        geographicAreaDomainService.add(geographicArea);
        Location location = new Location(10, 10, 20);
        Boolean result = geographicAreaDomainService.checkIfHaveSensorsOnHouseAreaWithTypeAndReadings("Porto", location, new SensorType("Temperature"));
        assertFalse(result);
    }


    @Test
    void getSensorClosestToHouseManySensors() {
        GeographicArea geographicArea = new GeographicArea("Porto", "City", new OccupationArea
                (new Location(10, 20, 30), 10, 10),
                new GeographicAreaType("City"));
        AreaSensor s1 = new AreaSensor("1", "sensor1", new Location(11, 20, 30), new SensorType("temperature"), LocalDate.of(2018, 12, 10), "ºC");
        AreaSensor s2 = new AreaSensor("1", "sensor1", new Location(12, 20, 30), new SensorType("temperature"), LocalDate.of(2018, 12, 10), "ºC");
        AreaSensor s3 = new AreaSensor("1", "sensor1", new Location(15, 20, 30), new SensorType("temperature"), LocalDate.of(2018, 12, 10), "ºC");
        Reading reading = new Reading(LocalDateTime.of(2018, 12, 10, 0, 0), 10);
        s1.addReading(reading);
        s2.addReading(reading);
        s3.addReading(reading);
        geographicArea.addSensorToList(s1);
        geographicArea.addSensorToList(s2);
        geographicArea.addSensorToList(s3);
        geographicAreaDomainService.add(geographicArea);
        Location location = new Location(10, 10, 20);
        Boolean result = geographicAreaDomainService.checkIfHaveSensorsOnHouseAreaWithTypeAndReadings("Porto", location, new SensorType("Temperature"));
        assertTrue(result);
    }


    @Test
    void getSensorClosestToHouseNoTemperatureSensors() {
        GeographicArea geographicArea = new GeographicArea("Porto", "City", new OccupationArea
                (new Location(10, 20, 30), 0.5, 0.5),
                new GeographicAreaType("City"));
        AreaSensor s1 = new AreaSensor("1", "sensor1", new Location(10, 20, 30), new SensorType("rainfall"), LocalDate.of(2018, 12, 10), "ºC");
        Reading reading = new Reading(LocalDateTime.of(2018, 12, 10, 0, 0), 10);
        s1.addReading(reading);
        geographicArea.addSensorToList(s1);
        geographicAreaDomainService.add(geographicArea);
        Location location = new Location(10, 10, 20);
        Boolean result = geographicAreaDomainService.checkIfHaveSensorsOnHouseAreaWithTypeAndReadings("Porto", location, new SensorType("Temperature"));
        assertFalse(result);
    }

    @Test
    void getSensorClosestToHouseNoGeographicArea() {
        GeographicArea geographicArea = new GeographicArea("Porto", "City", new OccupationArea
                (new Location(10, 20, 30), 0.5, 0.5),
                new GeographicAreaType("City"));
        AreaSensor s1 = new AreaSensor("1", "sensor1", new Location(10, 20, 30), new SensorType("rainfall"), LocalDate.of(2018, 12, 10), "ºC");
        Reading reading = new Reading(LocalDateTime.of(2018, 12, 10, 0, 0), 10);
        s1.addReading(reading);
        geographicArea.addSensorToList(s1);
        geographicAreaDomainService.add(geographicArea);
        Location location = new Location(10, 10, 20);
        Boolean result = geographicAreaDomainService.checkIfHaveSensorsOnHouseAreaWithTypeAndReadings("Braga", location, new SensorType("Temperature"));
        assertFalse(result);
    }


    @Test
    void testGetDailyTemperatureInHouseArea() {
        //Arrange
        GeographicArea geographicArea = new GeographicArea("Porto", "City", new OccupationArea
                (new Location(10, 20, 30), 0.5, 0.5),
                new GeographicAreaType("City"));
        AreaSensor s1 = new AreaSensor("1", "sensor1", new Location(10, 20, 30), new SensorType("temperature"), LocalDate.of(2018, 12, 10), "ºC");
        LocalDate start = LocalDate.of(2018, 12, 10);
        LocalDate end = LocalDate.of(2018, 12, 13);
        List<Double> expectedResult = new ArrayList<>();
        Reading reading = new Reading(LocalDateTime.of(2018, 12, 10, 0, 0), 10);
        //Act
        s1.addReading(reading);
        geographicArea.addSensorToList(s1);
        geographicAreaDomainService.add(geographicArea);
        Location location = new Location(10, 10, 20);
        expectedResult.add(10.0);
        expectedResult.add(0.0);
        expectedResult.add(0.0);
        expectedResult.add(0.0);
        List<Double> result = geographicAreaDomainService.getAverageDailyTemperatureInHouseArea("Porto", location, start, end);
        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetDailyTemperatureInHouseAreaManyReadings() {
        //Arrange
        GeographicArea geographicArea = new GeographicArea("Porto", "City", new OccupationArea
                (new Location(10, 20, 30), 0.5, 0.5),
                new GeographicAreaType("City"));
        AreaSensor s1 = new AreaSensor("1", "sensor1", new Location(10, 20, 30), new SensorType("temperature"), LocalDate.of(2018, 12, 10), "ºC");
        LocalDate start = LocalDate.of(2018, 12, 10);
        LocalDate end = LocalDate.of(2018, 12, 13);
        List<Double> expectedResult = new ArrayList<>();
        Reading reading = new Reading(LocalDateTime.of(2018, 12, 10, 2, 0), 10);
        Reading reading2 = new Reading(LocalDateTime.of(2018, 12, 11, 3, 0), 12);
        Reading reading3 = new Reading(LocalDateTime.of(2018, 12, 12, 2, 0), 13);
        Reading reading4 = new Reading(LocalDateTime.of(2018, 12, 13, 3, 0), 14);
        Reading reading5 = new Reading(LocalDateTime.of(2018, 12, 14, 3, 0), 15);
        //Act
        s1.addReading(reading);
        s1.addReading(reading2);
        s1.addReading(reading3);
        s1.addReading(reading4);
        s1.addReading(reading5);
        geographicArea.addSensorToList(s1);
        geographicAreaDomainService.add(geographicArea);
        Location location = new Location(10, 10, 20);
        expectedResult.add(10.0);
        expectedResult.add(12.0);
        expectedResult.add(13.0);
        expectedResult.add(14.0);
        List<Double> result = geographicAreaDomainService.getAverageDailyTemperatureInHouseArea("Porto", location, start, end);
        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetDailyTemperatureInHouseNoGeo() {
        //Arrange
        GeographicArea geographicArea = new GeographicArea("Porto", "City", new OccupationArea
                (new Location(10, 20, 30), 0.5, 0.5),
                new GeographicAreaType("City"));
        AreaSensor s1 = new AreaSensor("1", "sensor1", new Location(10, 20, 30), new SensorType("temperature"), LocalDate.of(2018, 12, 10), "ºC");
        LocalDate start = LocalDate.of(2018, 12, 10);
        LocalDate end = LocalDate.of(2018, 12, 13);
        List<Double> expectedResult = new ArrayList<>();
        Reading reading = new Reading(LocalDateTime.of(2018, 12, 10, 2, 0), 10);
        Reading reading2 = new Reading(LocalDateTime.of(2018, 12, 11, 3, 0), 12);
        Reading reading3 = new Reading(LocalDateTime.of(2018, 12, 12, 2, 0), 13);
        Reading reading4 = new Reading(LocalDateTime.of(2018, 12, 13, 3, 0), 14);
        Reading reading5 = new Reading(LocalDateTime.of(2018, 12, 14, 3, 0), 15);
        //Act
        s1.addReading(reading);
        s1.addReading(reading2);
        s1.addReading(reading3);
        s1.addReading(reading4);
        s1.addReading(reading5);
        geographicArea.addSensorToList(s1);
        geographicAreaDomainService.add(geographicArea);
        Location location = new Location(10, 10, 20);
        List<Double> result = geographicAreaDomainService.getAverageDailyTemperatureInHouseArea("Braga", location, start, end);
        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    void checkIfGetGeographicAreaByNameDTO() {

        GeographicArea porto = new GeographicArea("Porto", "City", new OccupationArea
                (new Location(10, 20, 30), 0.5, 0.5),
                new GeographicAreaType("City"));

        GeographicArea lisbon = new GeographicArea("Lisbon", "City", new OccupationArea
                (new Location(10, 20, 30), 0.5, 0.5),
                new GeographicAreaType("City"));

        geographicAreaDomainService.add(porto);
        geographicAreaDomainService.add(lisbon);

        GeographicAreaDto result = geographicAreaDomainService.getGeographicAreaByNameDTO("Porto");

        GeographicAreaDto expectedResult = new GeographicAreaDto();
        expectedResult.setName("Porto");

        assertEquals(expectedResult.getName(), result.getName());
    }

    @Test
    void checkIfGetGeographicAreaByNameDTONullReturn() {

        GeographicArea porto = new GeographicArea("Porto", "City", new OccupationArea
                (new Location(10, 20, 30), 0.5, 0.5),
                new GeographicAreaType("City"));

        GeographicArea lisbon = new GeographicArea("Lisbon", "City", new OccupationArea
                (new Location(10, 20, 30), 0.5, 0.5),
                new GeographicAreaType("City"));

        geographicAreaDomainService.add(porto);
        geographicAreaDomainService.add(lisbon);

        GeographicAreaDto result = geographicAreaDomainService.getGeographicAreaByNameDTO("Aveiro");

        GeographicAreaDto expectedResult = null;

        assertNull(result);
    }


    @Test
    void checkIfGetSumOfValueOnSensorInCertainDay() {

        GeographicArea geographicArea = new GeographicArea("Porto", "City", new OccupationArea
                (new Location(10, 20, 30), 0.5, 0.5),
                new GeographicAreaType("City"));
        House house = new House("houseName", new Location(40.7486, -73.9864, 0), geographicArea.getName());
        AreaSensor s1 = new AreaSensor("12", "Sensor1", new Location(40.7486, -73.9864, 0), new SensorType("rainfall"), LocalDate.of(2018, 01, 01), "ºC");
        LocalDateTime date1 = LocalDateTime.of(2019, 05, 01, 12, 30);

        ListOfReadings lisOfReading = new ListOfReadings();
        lisOfReading.addReading(new Reading(date1, 12d));
        lisOfReading.addReading(new Reading(date1, 12d));
        s1.setListOfReadings(lisOfReading);
        geographicArea.addSensorToList(s1);

        geographicAreaDomainService.add(geographicArea);

        Location location = new Location(10, 20, 30);

        SensorType sensorType = new SensorType("Rainfall");

        LocalDate date = LocalDate.of(2019, 05, 01);

        double result = geographicAreaDomainService.getSumOfValueOnSensorInCertainDay("Porto", location, sensorType, date);

        assertEquals(24, result, 0.00001);

    }

    @Test
    void checkIfGetSumOfValueOnSensorInCertainDayNoGeoArea() {

        GeographicArea geographicArea = new GeographicArea("Porto", "City", new OccupationArea
                (new Location(10, 20, 30), 0.5, 0.5),
                new GeographicAreaType("City"));
        House house = new House("houseName", new Location(40.7486, -73.9864, 0), geographicArea.getName());
        AreaSensor s1 = new AreaSensor("12", "Sensor1", new Location(40.7486, -73.9864, 0), new SensorType("rainfall"), LocalDate.of(2018, 01, 01), "ºC");
        LocalDateTime date1 = LocalDateTime.of(2019, 05, 01, 12, 30);

        ListOfReadings lisOfReading = new ListOfReadings();
        lisOfReading.addReading(new Reading(date1, 12d));
        lisOfReading.addReading(new Reading(date1, 12d));
        s1.setListOfReadings(lisOfReading);
        geographicArea.addSensorToList(s1);


        Location location = new Location(10, 20, 30);

        SensorType sensorType = new SensorType("Rainfall");

        LocalDate date = LocalDate.of(2019, 05, 01);

        double result = geographicAreaDomainService.getSumOfValueOnSensorInCertainDay("Porto", location, sensorType, date);

        assertEquals(Double.NaN, result, 0.00001);
    }

    @Test
    void checkIfGetSumOfValueOnSensorInCertainDayNoGeoAreaNeitherSensor() {

        GeographicArea geographicArea = new GeographicArea("Porto", "City", new OccupationArea
                (new Location(10, 20, 30), 0.5, 0.5),
                new GeographicAreaType("City"));
        House house = new House("houseName", new Location(40.7486, -73.9864, 0), geographicArea.getName());
        LocalDateTime date1 = LocalDateTime.of(2019, 05, 01, 12, 30);

        ListOfReadings lisOfReading = new ListOfReadings();
        lisOfReading.addReading(new Reading(date1, 12d));
        lisOfReading.addReading(new Reading(date1, 12d));


        Location location = new Location(10, 20, 30);

        SensorType sensorType = new SensorType("Rainfall");

        LocalDate date = LocalDate.of(2019, 05, 01);

        double result = geographicAreaDomainService.getSumOfValueOnSensorInCertainDay("Porto", location, sensorType, date);

        assertEquals(Double.NaN, result, 0.00001);

    }

    @Test
    void checkIfGetSumOfValueOnSensorInCertainDayNoSensors() {

        GeographicArea geographicArea = new GeographicArea("Porto", "City", new OccupationArea
                (new Location(10, 20, 30), 0.5, 0.5),
                new GeographicAreaType("City"));
        House house = new House("houseName", new Location(40.7486, -73.9864, 0), geographicArea.getName());
        LocalDateTime date1 = LocalDateTime.of(2019, 05, 01, 12, 30);

        ListOfReadings lisOfReading = new ListOfReadings();
        lisOfReading.addReading(new Reading(date1, 12d));
        lisOfReading.addReading(new Reading(date1, 12d));


        geographicAreaDomainService.add(geographicArea);

        Location location = new Location(10, 20, 30);

        SensorType sensorType = new SensorType("Rainfall");

        LocalDate date = LocalDate.of(2019, 05, 01);

        double result = geographicAreaDomainService.getSumOfValueOnSensorInCertainDay("Porto", location, sensorType, date);

        assertEquals(Double.NaN, result, 0.00001);

    }

    @Test
    void newGeographicAreaFalse() {

        GeographicArea geographicArea = new GeographicArea("Porto", "City", new OccupationArea
                (new Location(10, 20, 30), 0.5, 0.5),
                new GeographicAreaType("City"));
        GeographicAreaDto geographicArea1 = MapperGeographicArea.toDto(geographicArea);
        geographicArea1.setName("");

        Boolean result = geographicAreaDomainService.newGeographicArea(geographicArea1);

        assertFalse(result);

    }

    @Test
    void newGeographicAreaTrue() {

        GeographicArea geographicArea = new GeographicArea("Porto", "City", new OccupationArea
                (new Location(10, 20, 30), 0.5, 0.5),
                new GeographicAreaType("City"));
        GeographicAreaDto geographicArea1 = MapperGeographicArea.toDto(geographicArea);

        Boolean result = geographicAreaDomainService.newGeographicArea(geographicArea1);

        assertTrue(result);

    }

    @Test
    void getListOfGeographicAreasDTOTrue() {

        GeographicArea geographicArea = new GeographicArea("Porto", "City", new OccupationArea
                (new Location(10, 20, 30), 0.5, 0.5),
                new GeographicAreaType("City"));
        GeographicArea geographicArea2 = new GeographicArea("Lisboa", "City", new OccupationArea
                (new Location(10, 20, 30), 0.5, 0.5),
                new GeographicAreaType("City"));

        geographicAreaDomainService.add(geographicArea);
        geographicAreaDomainService.add(geographicArea2);

        List<GeographicAreaDtoWeb> list = geographicAreaDomainService.getListOfGeographicAreasDTO();

        assertEquals("Porto", list.get(0).getName());

    }

    @Test
    void getListOfGeographicAreasDTOFalse() {

        GeographicArea geographicArea = new GeographicArea("Porto", "City", new OccupationArea
                (new Location(10, 20, 30), 0.5, 0.5),
                new GeographicAreaType("City"));
        GeographicArea geographicArea2 = new GeographicArea("Lisboa", "City", new OccupationArea
                (new Location(10, 20, 30), 0.5, 0.5),
                new GeographicAreaType("City"));

        List<GeographicAreaDtoWeb> list = geographicAreaDomainService.getListOfGeographicAreasDTO();

        assertEquals(Collections.emptyList(), list);

    }

    @Test
    void occupationAreaLimitsTrue() {

        GeographicArea geographicArea = new GeographicArea("Porto", "City", new OccupationArea
                (new Location(10, 20, 30), 0.5, 0.5),
                new GeographicAreaType("City"));
        GeographicArea geographicArea2 = new GeographicArea("Lisboa", "City", new OccupationArea
                (new Location(10, 20, 30), 0.5, 0.5),
                new GeographicAreaType("City"));

        geographicAreaDomainService.add(geographicArea);
        geographicAreaDomainService.add(geographicArea2);

        Boolean list = geographicAreaDomainService.checkOccupationAreaLimits("Porto", new Location(10, 20, 30));

        assertTrue(list);

    }

    @Test
    void occupationAreaLimitsFalse() {

        GeographicArea geographicArea = new GeographicArea("Porto", "City", new OccupationArea
                (new Location(10, 20, 30), 0.5, 0.5),
                new GeographicAreaType("City"));
        GeographicArea geographicArea2 = new GeographicArea("Lisboa", "City", new OccupationArea
                (new Location(10, 20, 30), 0.5, 0.5),
                new GeographicAreaType("City"));

        Boolean list = geographicAreaDomainService.checkOccupationAreaLimits("Porto", new Location(20, 20, 30));

        assertFalse(list);

    }

    @Test
    void newSensorFalse() {

        GeographicArea geographicArea = new GeographicArea("Porto", "City", new OccupationArea
                (new Location(10, 20, 30), 0.5, 0.5),
                new GeographicAreaType("City"));
        geographicAreaDomainService.add(geographicArea);
        AreaSensor s1 = new AreaSensor("12", "Sensor1", new Location(40.7486, -73.9864, 0), new SensorType("rainfall"), LocalDate.of(2018, 01, 01), "ºC");
        AreaSensorDto areaSensor = MapperAreaSensor.toDto(s1);
        areaSensor.setIdOfSensor("");

        int result = geographicAreaDomainService.createAndAddSensor(areaSensor, "Porto");

        assertEquals(-1,result);

    }

    @Test
    void newSensorTrue() {

        GeographicArea geographicArea = new GeographicArea("Porto", "City", new OccupationArea
                (new Location(10, 20, 30), 0.5, 0.5),
                new GeographicAreaType("City"));
        geographicAreaDomainService.add(geographicArea);
        AreaSensor s1 = new AreaSensor("12", "Sensor1", new Location(40.7486, -73.9864, 0), new SensorType("rainfall"), LocalDate.of(2018, 01, 01), "ºC");
        AreaSensorDto areaSensor = MapperAreaSensor.toDto(s1);

        int result = geographicAreaDomainService.createAndAddSensor(areaSensor, "Porto");

        assertEquals(1,result);

    }

    @Test
    void getSensorByIdDTOTrue() {

        GeographicArea geographicArea = new GeographicArea("Porto", "City", new OccupationArea
                (new Location(10, 20, 30), 0.5, 0.5),
                new GeographicAreaType("City"));

        AreaSensor s1 = new AreaSensor("12", "Sensor1", new Location(40.7486, -73.9864, 0), new SensorType("rainfall"), LocalDate.of(2018, 01, 01), "ºC");
        geographicArea.addSensorToList(s1);
        geographicAreaDomainService.add(geographicArea);
        AreaSensorDto result = geographicAreaDomainService.getSensorByIdDTO("Porto", "12");

        assertEquals(s1.getName(), result.getName());
    }

    @Test
    void getSensorByIdDTONull2() {

        GeographicArea geographicArea = new GeographicArea("Porto", "City", new OccupationArea
                (new Location(10, 20, 30), 0.5, 0.5),
                new GeographicAreaType("City"));
        geographicAreaDomainService.add(geographicArea);
        AreaSensor s1 = new AreaSensor("12", "Sensor1", new Location(40.7486, -73.9864, 0), new SensorType("rainfall"), LocalDate.of(2018, 01, 01), "ºC");
        AreaSensorDto areaSensor = MapperAreaSensor.toDto(s1);

        AreaSensorDto result = geographicAreaDomainService.getSensorByIdDTO("Porto", "12");

        assertEquals(null, result);

    }

    @Test
    void deactivateSensorTrue() {

        GeographicArea geographicArea = new GeographicArea("Porto", "City", new OccupationArea
                (new Location(10, 20, 30), 0.5, 0.5),
                new GeographicAreaType("City"));

        AreaSensor s1 = new AreaSensor("12", "Sensor1", new Location(40.7486, -73.9864, 0), new SensorType("rainfall"), LocalDate.of(2018, 01, 01), "ºC");
        geographicArea.addSensorToList(s1);
        geographicAreaDomainService.add(geographicArea);
        Boolean result = geographicAreaDomainService.deactivateSensor("Porto", "12");

        assertTrue(result);
    }

    @Test
    void deactivateSensorFalse() {

        GeographicArea geographicArea = new GeographicArea("Porto", "City", new OccupationArea
                (new Location(10, 20, 30), 0.5, 0.5),
                new GeographicAreaType("City"));
        geographicAreaDomainService.add(geographicArea);
        AreaSensor s1 = new AreaSensor("12", "Sensor1", new Location(40.7486, -73.9864, 0), new SensorType("rainfall"), LocalDate.of(2018, 01, 01), "ºC");
        geographicArea.addSensorToList(s1);
        geographicAreaDomainService.add(geographicArea);
        Boolean result = geographicAreaDomainService.deactivateSensor("Porto", "13");

        assertFalse(result);

    }

    @Test
    void getListOfSensorsActiveDTOTrue() {

        GeographicArea geographicArea = new GeographicArea("Porto", "City", new OccupationArea
                (new Location(10, 20, 30), 0.5, 0.5),
                new GeographicAreaType("City"));

        AreaSensor s1 = new AreaSensor("12", "Sensor1", new Location(40.7486, -73.9864, 0), new SensorType("rainfall"), LocalDate.of(2018, 01, 01), "ºC");
        geographicArea.addSensorToList(s1);
        geographicAreaDomainService.add(geographicArea);
        List<AreaSensorDto> result = geographicAreaDomainService.getListOfSensorsActiveDTO("Porto");

        assertEquals(s1.getName(),result.get(0).getName());
    }

    @Test
    void getListOfSensorsActiveDTOFalse() {

        GeographicArea geographicArea = new GeographicArea("Porto", "City", new OccupationArea
                (new Location(10, 20, 30), 0.5, 0.5),
                new GeographicAreaType("City"));
        geographicAreaDomainService.add(geographicArea);
        geographicAreaDomainService.add(geographicArea);
        List<AreaSensorDto> result = geographicAreaDomainService.getListOfSensorsActiveDTO("Porto");

        assertEquals(Collections.emptyList(),result);

    }


    @Test
    void getListOfSensorsActiveDTOAllTrue() {

        GeographicArea geographicArea = new GeographicArea("Porto", "City", new OccupationArea
                (new Location(10, 20, 30), 0.5, 0.5),
                new GeographicAreaType("City"));

        AreaSensor s1 = new AreaSensor("12", "Sensor1", new Location(40.7486, -73.9864, 0), new SensorType("rainfall"), LocalDate.of(2018, 01, 01), "ºC");
        geographicArea.addSensorToList(s1);
        geographicAreaDomainService.add(geographicArea);
        List<AreaSensorDto> result = geographicAreaDomainService.getListOfSensorsDtoFromGA("Porto");

        assertEquals(s1.getName(),result.get(0).getName());
    }

    @Test
    void getListOfSensorsActiveDTOAllFalse() {

        GeographicArea geographicArea = new GeographicArea("Porto", "City", new OccupationArea
                (new Location(10, 20, 30), 0.5, 0.5),
                new GeographicAreaType("City"));
        geographicAreaDomainService.add(geographicArea);
        geographicAreaDomainService.add(geographicArea);
        List<AreaSensorDto> result = geographicAreaDomainService.getListOfSensorsDtoFromGA("Porto");

        assertEquals(Collections.emptyList(),result);
    }

    @Test
    void testAddGeoInsideAnother() {
        GeographicArea geographicArea = new GeographicArea("Porto", "City", new OccupationArea
                (new Location(10, 20, 30), 0.5, 0.5),
                new GeographicAreaType("City"));
        GeographicArea geographicArea2 = new GeographicArea("Portugal", "City", new OccupationArea
                (new Location(10, 20, 30), 0.5, 0.5),
                new GeographicAreaType("City"));
        geographicAreaDomainService.add(geographicArea);
        geographicAreaDomainService.add(geographicArea2);
        boolean result = geographicAreaDomainService.addGeographicAreaInsideAnother(geographicArea.getName(),geographicArea2.getName());

        assertTrue(result);
    }

    @Test
    void testAddGeoInsideAnotherFalse() {
        GeographicArea geographicArea = new GeographicArea("Porto", "City", new OccupationArea
                (new Location(10, 20, 30), 0.5, 0.5),
                new GeographicAreaType("City"));
        GeographicArea geographicArea2 = new GeographicArea("Portugal", "City", new OccupationArea
                (new Location(10, 20, 30), 0.5, 0.5),
                new GeographicAreaType("City"));
        geographicAreaDomainService.add(geographicArea);
        geographicAreaDomainService.add(geographicArea2);
        geographicAreaDomainService.addGeographicAreaInsideAnother(geographicArea.getName(),geographicArea2.getName());
        boolean result = geographicAreaDomainService.addGeographicAreaInsideAnother(geographicArea.getName(),geographicArea2.getName());

        assertFalse(result);
    }


    @Test
    void testCreateAndAddNewSensor() {
        GeographicArea geographicArea = new GeographicArea("Porto", "City", new OccupationArea
                (new Location(10, 20, 30), 0.5, 0.5),
                new GeographicAreaType("City"));
        AreaSensor s1 = new AreaSensor("12", "Sensor1", new Location(40.7486, -73.9864, 0), new SensorType("rainfall"), LocalDate.of(2018, 01, 01), "ºC");
        geographicAreaDomainService.add(geographicArea);
        int result = geographicAreaDomainService.createAndAddSensor(MapperAreaSensor.toDto(s1),geographicArea.getName());
        assertEquals(1,result);
    }

    @Test
    void testCreateAndAddNewSensorFalse() {
        GeographicArea geographicArea = new GeographicArea("Porto", "City", new OccupationArea
                (new Location(10, 20, 30), 0.5, 0.5),
                new GeographicAreaType("City"));
        AreaSensor s1 = new AreaSensor("12", "Sensor1", new Location(40.7486, -73.9864, 0), new SensorType("rainfall"), LocalDate.of(2018, 01, 01), "ºC");
        geographicAreaDomainService.add(geographicArea);
        geographicAreaDomainService.createAndAddSensor(MapperAreaSensor.toDto(s1),geographicArea.getName());
        assertEquals(1, geographicAreaDomainService.getGeographicAreaByName(geographicArea.getName()).getListOfAreaSensors().size());
    }


    @Test
    void testCreateAndAddNewSensorAlreadyExists() {
        GeographicArea geographicArea = new GeographicArea("Porto", "City", new OccupationArea
                (new Location(10, 20, 30), 0.5, 0.5),
                new GeographicAreaType("City"));
        AreaSensor s1 = new AreaSensor("12", "Sensor1", new Location(40.7486, -73.9864, 0), new SensorType("rainfall"), LocalDate.of(2018, 01, 01), "ºC");
        geographicAreaDomainService.add(geographicArea);
        geographicAreaDomainService.createAndAddSensor(MapperAreaSensor.toDto(s1),geographicArea.getName());
        int result = geographicAreaDomainService.createAndAddSensor(MapperAreaSensor.toDto(s1),geographicArea.getName());
        assertEquals(-2,result);
    }

}