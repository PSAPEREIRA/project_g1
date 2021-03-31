package pt.ipp.isep.dei.project1.controllers.house;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
class GetTheCurrentTemperatureInHouseAreaControllerTest {

    @Mock
    private HouseDomainService houseDomainService;
    @Mock
    private HouseRepository houseRepository;
    @Mock
    private GeographicAreaDomainService geographicAreaDomainService;
    @Mock
    private GeographicAreaRepository geographicAreaRepository;

    GetTheCurrentTemperatureInHouseAreaController ctrl;


    @BeforeEach
    void initUseCase() {
        houseDomainService = new HouseDomainService(houseRepository);
        geographicAreaDomainService = new GeographicAreaDomainService(geographicAreaRepository);
        ctrl = new GetTheCurrentTemperatureInHouseAreaController(geographicAreaDomainService, houseDomainService);
    }


    @Test
    void getMinDistanceBetweenSensorToHouseOneSensor() throws Exception {
        //ASSERT
        GeographicArea geographicArea = new GeographicArea("Porto", "City", new OccupationArea
                (new Location(10, 20, 30), 0.5, 0.5),
                new GeographicAreaType("City"));
        House house = new House("house1", new Location(10, 6, 0), geographicArea.getName());
        AreaSensor s1 = new AreaSensor("1", "sensor1", new Location(10, 7, 0), new SensorType("temperature"), LocalDate.of(2018, 12, 10), "ºC");
        AreaSensor s2 = new AreaSensor("1", "sensor2", new Location(10, 8, 0), new SensorType("temperature"), LocalDate.of(2018, 12, 10), "ºC");
        AreaSensor s3 = new AreaSensor("1", "sensor3", new Location(10, 30, 0), new SensorType("temperature"), LocalDate.of(2018, 12, 10), "ºC");
        AreaSensor s4 = new AreaSensor("1", "sensor4", new Location(10, 9, 0), new SensorType("temperature"), LocalDate.of(2018, 12, 10), "ºC");
        ListOfReadings listOfReadings = new ListOfReadings();
        Reading reading = new Reading(LocalDateTime.of(2018, 10, 10, 10, 20), 10);
        listOfReadings.addReading(reading);
        s1.setListOfReadings(listOfReadings);
        s2.setListOfReadings(listOfReadings);
        s3.setListOfReadings(listOfReadings);
        GetTheCurrentTemperatureInHouseAreaController ctrll600 = new GetTheCurrentTemperatureInHouseAreaController(geographicAreaDomainService, houseDomainService);
        s1.setListOfReadings(listOfReadings);
        double expectedResult = 10;
        //ACT
        geographicArea.addSensorToList(s1);
        geographicArea.addSensorToList(s2);
        geographicArea.addSensorToList(s3);
        geographicArea.addSensorToList(s4);
        geographicAreaDomainService.add(geographicArea);
        houseDomainService.add(house);
        double result = ctrll600.getCurrentRoomTemperature();

        //ARRANGE
        assertEquals(expectedResult, result);
    }

    @Test
    void getMinDistanceBetweenSensorToHouseNull() throws Exception {
        //ASSERT
        GeographicArea geographicArea = new GeographicArea("Porto", "city", new OccupationArea(new Location(10, 20, 30), 0.5, 0.5), new GeographicAreaType("city"));
        GeographicArea geographicArea2 = new GeographicArea("Braga", "city", new OccupationArea(new Location(10, 20, 30), 0.5, 0.5), new GeographicAreaType("city"));
        House house = new House("house1", new Location(10, 6, 0), geographicArea.getName());
        houseDomainService.add(house);
        AreaSensor s1 = new AreaSensor("1", "sensor1", new Location(10, 20, 0), new SensorType("temperature"), LocalDate.of(2018, 12, 10), "ºC");
        AreaSensor s2 = new AreaSensor("2", "sensor2", new Location(10, 5, 0), new SensorType("temperature"), LocalDate.of(2018, 12, 10), "ºC");
        AreaSensor s3 = new AreaSensor("3", "sensor3", new Location(10, 30, 0), new SensorType("temperature"), LocalDate.of(2018, 12, 10), "ºC");
        ListOfReadings listOfReadings = new ListOfReadings();
        Reading reading = new Reading(LocalDateTime.of(2018, 10, 10, 10, 20), 10);
        listOfReadings.addReading(reading);
        s1.setListOfReadings(listOfReadings);
        s2.setListOfReadings(listOfReadings);
        s3.setListOfReadings(listOfReadings);
        GetTheCurrentTemperatureInHouseAreaController ctrll600 = new GetTheCurrentTemperatureInHouseAreaController(geographicAreaDomainService, houseDomainService);
        s1.setListOfReadings(listOfReadings);
        //ACT
        geographicArea2.addSensorToList(s1);
        geographicArea2.addSensorToList(s2);
        geographicArea2.addSensorToList(s3);
        geographicAreaDomainService.add(geographicArea);
        double result = ctrll600.getCurrentRoomTemperature();

        //ARRANGE
        assertEquals(Double.NaN, result);
    }

    @Test
    void getCurrentTemperatureInHouseAreaWith3Sensors() throws Exception {
        //ARRANGE
        GeographicArea geographicArea = new GeographicArea("Porto", "City", new OccupationArea
                (new Location(10, 20, 30), 0.5, 0.5),
                new GeographicAreaType("City"));
        AreaSensor s1 = new AreaSensor("1", "sensor1", new Location(10, 20, 0), new SensorType("temperature"), LocalDate.of(2018, 12, 10), "ºC");
        AreaSensor s2 = new AreaSensor("1", "sensor2", new Location(20, 30, 0), new SensorType("temperature"), LocalDate.of(2018, 12, 10), "ºC");
        AreaSensor s3 = new AreaSensor("1", "sensor3", new Location(10, 15, 0), new SensorType("temperature"), LocalDate.of(2018, 12, 10), "ºC");
        geographicArea.addSensorToList(s1);
        geographicArea.addSensorToList(s2);
        geographicArea.addSensorToList(s3);
        ListOfReadings listOfReadings = new ListOfReadings();
        Reading reading = new Reading(LocalDateTime.of(2018, 10, 10, 10, 20), 10);
        listOfReadings.addReading(reading);
        s1.setListOfReadings(listOfReadings);
        s2.setListOfReadings(listOfReadings);
        s3.setListOfReadings(listOfReadings);
        House house = new House("house1", new Location(10, 15, 0), geographicArea.getName());
        GetTheCurrentTemperatureInHouseAreaController ctrll600 = new GetTheCurrentTemperatureInHouseAreaController(geographicAreaDomainService, houseDomainService);
        //ACT
        LocalDateTime date1 = LocalDateTime.of(2018, 12, 21, 10, 20);
        ListOfReadings readings = new ListOfReadings();
        readings.addReading(new Reading(date1, 10));
        readings.addReading(new Reading(date1, 10));
        readings.addReading(new Reading(date1, 10));
        readings.addReading(new Reading(date1, 20));
        readings.addReading(new Reading(date1, 15));
        s1.setListOfReadings(readings);
        s2.setListOfReadings(readings);
        s3.setListOfReadings(readings);
        houseDomainService.add(house);
        geographicAreaDomainService.add(geographicArea);
        double result = ctrll600.getCurrentRoomTemperature();
        double expectedResult = 15;
        //ASSERT
        assertEquals(expectedResult, result);
    }

    @Test
    void checkGetCurrentTemperatureNoGeoArea() throws Exception {
        //ASSERT
        GeographicArea geographicArea = new GeographicArea("Porto", "city", new OccupationArea(new Location(10, 20, 30), 0.5, 0.5), new GeographicAreaType("city"));
        House house = new House("house1", new Location(10, 6, 0), geographicArea.getName());
        houseDomainService.add(house);
        AreaSensor s1 = new AreaSensor("1", "sensor1", new Location(10, 20, 0), new SensorType("temperature"), LocalDate.of(2018, 12, 10), "ºC");
        AreaSensor s2 = new AreaSensor("2", "sensor2", new Location(10, 5, 0), new SensorType("temperature"), LocalDate.of(2018, 12, 10), "ºC");
        AreaSensor s3 = new AreaSensor("3", "sensor3", new Location(10, 30, 0), new SensorType("temperature"), LocalDate.of(2018, 12, 10), "ºC");
        ListOfReadings listOfReadings = new ListOfReadings();
        Reading reading = new Reading(LocalDateTime.of(2018, 10, 10, 10, 20), 10);
        listOfReadings.addReading(reading);
        s1.setListOfReadings(listOfReadings);
        s2.setListOfReadings(listOfReadings);
        s3.setListOfReadings(listOfReadings);
        GetTheCurrentTemperatureInHouseAreaController ctrll600 = new GetTheCurrentTemperatureInHouseAreaController(geographicAreaDomainService, houseDomainService);

        double result = ctrll600.getCurrentRoomTemperature();

        //ARRANGE
        assertEquals(Double.NaN, result);
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

    @Test
    void checkIfHaveSensorsOnHouseAreaWithTypeAndReadings() throws Exception {
        getCurrentTemperatureInHouseAreaWith3Sensors();
        boolean result = ctrl.checkIfHaveSensorsOnHouseAreaWithTypeAndReadings();

        assertTrue(result);
    }

}