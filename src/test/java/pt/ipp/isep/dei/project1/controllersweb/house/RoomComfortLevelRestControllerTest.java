package pt.ipp.isep.dei.project1.controllersweb.house;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import pt.ipp.isep.dei.project1.controllers.house.RoomComfortLevelCTRL;
import pt.ipp.isep.dei.project1.model.geographicarea.GeographicArea;
import pt.ipp.isep.dei.project1.model.geographicarea.GeographicAreaType;
import pt.ipp.isep.dei.project1.model.geographicarea.Location;
import pt.ipp.isep.dei.project1.model.geographicarea.OccupationArea;
import pt.ipp.isep.dei.project1.model.house.House;
import pt.ipp.isep.dei.project1.model.house.Room;
import pt.ipp.isep.dei.project1.model.repositories.*;
import pt.ipp.isep.dei.project1.model.sensor.*;
import pt.ipp.isep.dei.project1.services.RoomGeoAreaService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@ExtendWith(MockitoExtension.class)
class RoomComfortLevelRestControllerTest {

    @Mock
    private RoomComfortLevelRestController ctrl;
    @Mock
    private HouseDomainService houseDomainService;
    @Mock
    private HouseRepository houseRepository;
    @Mock
    private RoomGeoAreaService roomGeoAreaService;
    @Mock
    private RoomDomainService roomDomainService;
    @Mock
    private RoomRepository roomRepository;
    @Mock
    private RoomComfortLevelCTRL ctrlModel;
    @Mock
    private GeographicAreaDomainService geographicAreaDomainService;
    @Mock
    private GeographicAreaRepository geographicAreaRepository;


    @BeforeEach
    void initMocks() throws Exception {
        this.houseDomainService = new HouseDomainService(houseRepository);
        this.roomDomainService = new RoomDomainService(roomRepository);
        this.geographicAreaDomainService = new GeographicAreaDomainService(geographicAreaRepository);
        this.ctrl = new RoomComfortLevelRestController(roomGeoAreaService, houseDomainService, roomDomainService);
    }

    @Test
    void getInstancesWithTemperatureHigherLowerComfortLevel() {
        GeographicArea geographicArea = new GeographicArea("Porto", "City", new OccupationArea
                (new Location(11, 20, 30), 0.5, 0.5),
                new GeographicAreaType("City"));
        AreaSensor areaSensor1 = new AreaSensor("1", "Test AreaSensor", new Location(40.7486, -73.9864, 0),
                new SensorType("temperature"), LocalDate.of(2019, 1, 15), "l/m2");
        House house = new House("Casa", new Location(40.7486, -73.9864, 0), "Porto");
        Room room1 = new Room("B107", "Classroom", 1, 10.0, 6.0, 3.5);
        RoomSensor roomSensor = new RoomSensor();
        roomSensor.setName("s1");
        roomSensor.setSensorType(new SensorType("temperature"));
        house.setGeographicAreaID(geographicArea.getName());
        geographicArea.addSensorToList(areaSensor1);
        room1.addSensor(roomSensor);
        LocalDateTime date1 = LocalDateTime.of(2019, 1, 15, 12, 00);
        Reading r1 = new Reading(date1, 28);
        LocalDateTime date2 = LocalDateTime.of(2019, 1, 15, 12, 3);
        Reading r2 = new Reading(date2, 15);
        LocalDateTime date3 = LocalDateTime.of(2019, 1, 16, 12, 5);
        Reading r3 = new Reading(date3, 33);
        LocalDateTime date4 = LocalDateTime.of(2019, 1, 16, 12, 6);
        Reading r4 = new Reading(date4, 31);
        LocalDateTime date5 = LocalDateTime.of(2019, 1, 17, 12, 10);
        Reading r5 = new Reading(date5, -15);
        LocalDateTime date6 = LocalDateTime.of(2019, 1, 17, 12, 11);
        Reading r6 = new Reading(date6, -2);
        ListOfReadings listOfReadings = new ListOfReadings();
        listOfReadings.addReading(r1);
        listOfReadings.addReading(r2);
        listOfReadings.addReading(r3);
        listOfReadings.addReading(r4);
        listOfReadings.addReading(r5);
        listOfReadings.addReading(r6);
        LocalDateTime date11 = LocalDateTime.of(2019, 1, 15, 12, 00);
        Reading re1 = new Reading(date11, 15);
        LocalDateTime date12 = LocalDateTime.of(2019, 1, 16, 12, 00);
        Reading re2 = new Reading(date12, 20);
        LocalDateTime date13 = LocalDateTime.of(2019, 1, 17, 12, 00);
        Reading re3 = new Reading(date13, 25);
        LocalDateTime date14 = LocalDateTime.of(2019, 1, 18, 12, 00);
        Reading re4 = new Reading(date14, 30);
        roomSensor.setListOfReadings(listOfReadings);
        areaSensor1.addReading(re1);
        areaSensor1.addReading(re2);
        areaSensor1.addReading(re3);
        areaSensor1.addReading(re4);
        roomDomainService.addRoom(room1);
        houseDomainService.add(house);
        geographicAreaDomainService.add(geographicArea);

        LocalDate startDate = LocalDate.of(2019, 1, 15);
        LocalDate finalDate = LocalDate.of(2019, 1, 18);
        String option = "HIGHER";
        int cat = 1;

        ResponseEntity<Object> result = ctrl.getInstancesWithTemperatureHigherLowerComfortLevel("B107", cat, option, startDate, finalDate);

        assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    @Test
    void getInstancesWithTemperatureHigherLowerComfortLevelInvalidRoom() {
        LocalDate startDate = LocalDate.of(2019, 1, 15);
        LocalDate finalDate = LocalDate.of(2019, 1, 18);
        String option = "lower";
        int cat = 1;

        ResponseEntity<Object> result = ctrl.getInstancesWithTemperatureHigherLowerComfortLevel("quarto1", cat, option, startDate, finalDate);

        assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
    }

    @Test
    void getInstancesWithTemperatureHigherLowerComfortLevelNoRoomSensors() {
        GeographicArea geographicArea = new GeographicArea("Porto", "City", new OccupationArea
                (new Location(11, 20, 30), 0.5, 0.5),
                new GeographicAreaType("City"));
        AreaSensor areaSensor1 = new AreaSensor("1", "Test AreaSensor", new Location(40.7486, -73.9864, 0),
                new SensorType("temperature"), LocalDate.of(2019, 1, 15), "l/m2");
        House house = new House("Casa", new Location(40.7486, -73.9864, 0), "Porto");
        Room room1 = new Room("B107", "Classroom", 1, 10.0, 6.0, 3.5);
        house.setGeographicAreaID(geographicArea.getName());
        geographicArea.addSensorToList(areaSensor1);
        LocalDateTime date1 = LocalDateTime.of(2019, 1, 15, 12, 00);
        LocalDateTime date11 = LocalDateTime.of(2019, 1, 15, 12, 00);
        Reading re1 = new Reading(date11, 15);
        LocalDateTime date12 = LocalDateTime.of(2019, 1, 16, 12, 00);
        Reading re2 = new Reading(date12, 20);
        LocalDateTime date13 = LocalDateTime.of(2019, 1, 17, 12, 00);
        Reading re3 = new Reading(date13, 25);
        LocalDateTime date14 = LocalDateTime.of(2019, 1, 18, 12, 00);
        Reading re4 = new Reading(date14, 30);
        areaSensor1.addReading(re1);
        areaSensor1.addReading(re2);
        areaSensor1.addReading(re3);
        areaSensor1.addReading(re4);
        roomDomainService.addRoom(room1);
        houseDomainService.add(house);
        geographicAreaDomainService.add(geographicArea);

        LocalDate startDate = LocalDate.of(2019, 1, 15);
        LocalDate finalDate = LocalDate.of(2019, 1, 18);
        String option = "HIGHER";
        int cat = 1;

        ResponseEntity<Object> result = ctrl.getInstancesWithTemperatureHigherLowerComfortLevel("B107", cat, option, startDate, finalDate);

        assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
    }

    @Test
    void getInstancesWithTemperatureHigherLowerComfortLevelNoReadings() {
        GeographicArea geographicArea = new GeographicArea("Porto", "City", new OccupationArea
                (new Location(11, 20, 30), 0.5, 0.5),
                new GeographicAreaType("City"));
        AreaSensor areaSensor1 = new AreaSensor("1", "Test AreaSensor", new Location(40.7486, -73.9864, 0),
                new SensorType("temperature"), LocalDate.of(2019, 1, 15), "l/m2");
        House house = new House("Casa", new Location(40.7486, -73.9864, 0), "Porto");
        Room room1 = new Room("B107", "Classroom", 1, 10.0, 6.0, 3.5);
        RoomSensor roomSensor = new RoomSensor();
        roomSensor.setName("s1");
        roomSensor.setSensorType(new SensorType("temperature"));
        house.setGeographicAreaID(geographicArea.getName());
        geographicArea.addSensorToList(areaSensor1);
        room1.addSensor(roomSensor);
        LocalDateTime date1 = LocalDateTime.of(2019, 1, 15, 12, 00);
        Reading r1 = new Reading(date1, 28);
        LocalDateTime date2 = LocalDateTime.of(2019, 1, 15, 12, 3);
        Reading r2 = new Reading(date2, 15);
        LocalDateTime date3 = LocalDateTime.of(2019, 1, 16, 12, 5);
        Reading r3 = new Reading(date3, 33);
        LocalDateTime date4 = LocalDateTime.of(2019, 1, 16, 12, 6);
        Reading r4 = new Reading(date4, 31);
        LocalDateTime date5 = LocalDateTime.of(2019, 1, 17, 12, 10);
        Reading r5 = new Reading(date5, -15);
        LocalDateTime date6 = LocalDateTime.of(2019, 1, 17, 12, 11);
        Reading r6 = new Reading(date6, -2);
        ListOfReadings listOfReadings = new ListOfReadings();
//        listOfReadings.addReading(r1);
//        listOfReadings.addReading(r2);
//        listOfReadings.addReading(r3);
//        listOfReadings.addReading(r4);
//        listOfReadings.addReading(r5);
//        listOfReadings.addReading(r6);
        LocalDateTime date11 = LocalDateTime.of(2019, 1, 15, 12, 00);
        Reading re1 = new Reading(date11, 15);
        LocalDateTime date12 = LocalDateTime.of(2019, 1, 16, 12, 00);
        Reading re2 = new Reading(date12, 20);
        LocalDateTime date13 = LocalDateTime.of(2019, 1, 17, 12, 00);
        Reading re3 = new Reading(date13, 25);
        LocalDateTime date14 = LocalDateTime.of(2019, 1, 18, 12, 00);
        Reading re4 = new Reading(date14, 30);
        roomSensor.setListOfReadings(listOfReadings);
//        areaSensor1.addReading(re1);
//        areaSensor1.addReading(re2);
//        areaSensor1.addReading(re3);
//        areaSensor1.addReading(re4);
        roomDomainService.addRoom(room1);
        houseDomainService.add(house);
        geographicAreaDomainService.add(geographicArea);

        LocalDate startDate = LocalDate.of(2019, 1, 15);
        LocalDate finalDate = LocalDate.of(2019, 1, 18);
        String option = "HIGHER";
        int cat = 1;

        ResponseEntity<Object> result = ctrl.getInstancesWithTemperatureHigherLowerComfortLevel("B107", cat, option, startDate, finalDate);

        List<String> expectedResult = new ArrayList<>();
        expectedResult.add("No readings registered on the selected period");
        assertEquals(expectedResult, result.getBody());
    }
    
}