package pt.ipp.isep.dei.project1.controllers.house;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pt.ipp.isep.dei.project1.dto.housedto.ListOfRoomsDto;
import pt.ipp.isep.dei.project1.dto.housedto.RoomDto;
import pt.ipp.isep.dei.project1.dto.sensordto.ReadingDto;
import pt.ipp.isep.dei.project1.mapper.MapperRoom;
import pt.ipp.isep.dei.project1.model.geographicarea.GeographicArea;
import pt.ipp.isep.dei.project1.model.geographicarea.GeographicAreaType;
import pt.ipp.isep.dei.project1.model.geographicarea.Location;
import pt.ipp.isep.dei.project1.model.geographicarea.OccupationArea;
import pt.ipp.isep.dei.project1.model.house.House;
import pt.ipp.isep.dei.project1.model.house.Room;
import pt.ipp.isep.dei.project1.model.repositories.*;
import pt.ipp.isep.dei.project1.model.sensor.*;
import pt.ipp.isep.dei.project1.model.repositories.GeographicAreaDomainService;
import pt.ipp.isep.dei.project1.model.repositories.HouseDomainService;
import pt.ipp.isep.dei.project1.services.RoomGeoAreaService;
import pt.ipp.isep.dei.project1.model.repositories.RoomDomainService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;


@ExtendWith(MockitoExtension.class)
public class RoomComfortLevelCTRLTest {


    private GeographicAreaDomainService geographicAreaDomainService;
    @org.mockito.Mock
    private GeographicAreaRepository geographicAreaRepository;



    private RoomDomainService roomDomainService;
    @org.mockito.Mock
    private RoomRepository roomRepository;

    @org.mockito.Mock
    private RoomGeoAreaService roomGeoAreaService;

    private RoomComfortLevelCTRL ctrl;

    @Mock
    private HouseDomainService houseDomainService;
    @Mock
    private HouseRepository houseRepository;


    @BeforeEach
    void initMocks() throws Exception {
        geographicAreaDomainService = new GeographicAreaDomainService(geographicAreaRepository);
        // sensorTypeRepo = new SensorTypeRepo(sensorTypeRepository);
        roomDomainService = new RoomDomainService(roomRepository);
        roomGeoAreaService = new RoomGeoAreaService(geographicAreaDomainService, roomDomainService);
        houseDomainService = new HouseDomainService(houseRepository);
        ctrl = new RoomComfortLevelCTRL(roomGeoAreaService, houseDomainService);
    }

    void setUpTest(){
            /** Build GA */

            Location l = new Location(41.178553, -8.608035, 111);
            OccupationArea o = new OccupationArea(l, 0.261, 0.249);
            GeographicAreaType t = new GeographicAreaType("urban area");
            GeographicArea g = new GeographicArea("ISEP", "Campus do ISEP", o, t);


            /** Set GA to house */

            houseDomainService.setGeographicAreaID(g.getName());
            houseDomainService.setLocationOfHouse(l);

            ctrl.checkGeographicAreaOfTheHouse();

            /** Build AreaSensor with Reading(s) and add it to GA*/

            SensorType sensorType = new SensorType("Temperature");
            LocalDate installationDateAreaSensor = LocalDate.of(2018, 11, 15);
            AreaSensor sensor1 = new AreaSensor("TT12346", "Meteo station ISEP - temperature", l, sensorType, installationDateAreaSensor, "C");
            ListOfReadings listOfReadings = new ListOfReadings();
            Reading rArea1 = new Reading(LocalDateTime.of(2018,12,2,0,0,0,0),14);
            listOfReadings.addReading(rArea1);
            sensor1.setListOfReadings(listOfReadings);
            g.addSensorToList(sensor1);
            geographicAreaDomainService.add(g);



            /** Build Room && RoomSensor with Reading(s)*/

            Room room = new Room("B107", "Classroom", 1, 10, 6, 3.5);
            LocalDate installationDateRoomSensor = LocalDate.of(2018, 10, 15);
            RoomSensor roomSensor = new RoomSensor("TT12334OA","Temperature B405",sensorType,installationDateRoomSensor,"C");
            Reading r1 = new Reading(LocalDateTime.of(2018,12,2,1,0,0,0),20);
            Reading r2 = new Reading(LocalDateTime.of(2018,12,2,2,0,0,0),21);
            Reading r3 = new Reading(LocalDateTime.of(2018,12,2,3,0,0,0),22);
            Reading r4 = new Reading(LocalDateTime.of(2018,12,2,4,0,0,0),23);
            roomSensor.addReading(r1);
            roomSensor.addReading(r2);
            roomSensor.addReading(r3);
            roomSensor.addReading(r4);
            room.addSensor(roomSensor);
            roomDomainService.addRoom(room);

    }

    @Test
    public void checkGeographicAreaOfTheHouseFalseIfGeoAreaInHouse() {

        //  House h = new House();

        Location l = new Location(41.178553, -8.608035, 111);

        OccupationArea o = new OccupationArea(l, 0.261, 0.249);

        GeographicAreaType t = new GeographicAreaType("urban area");

        GeographicArea g = new GeographicArea("ISEP", "Campus do ISEP", o, t);

        houseDomainService.setGeographicAreaID(g.getName());

        //   RoomComfortLevelCTRL newCtrl = new RoomComfortLevelCTRL(geographicAreaRepo, roomRepo);

        boolean result = ctrl.checkGeographicAreaOfTheHouse();

        assertFalse(result);
    }

    @Test
    public void checkGeographicAreaOfTheHouseTrueIfGeoInHouseIsNull() {

        //   House h = new House();

        //  RoomComfortLevelCTRL newCtrl = new RoomComfortLevelCTRL(geographicAreaRepo, roomRepo);

        boolean result = ctrl.checkGeographicAreaOfTheHouse();

        assertTrue(result);
    }

    @Test
    public void checkTemperatureSensorsOfHouseAreaFALSE() {

        Location l = new Location(41.178553, -8.608035, 111);

        OccupationArea o = new OccupationArea(l, 0.261, 0.249);

        GeographicAreaType t = new GeographicAreaType("urban area");

        GeographicArea g = new GeographicArea("ISEP", "Campus do ISEP", o, t);

        houseDomainService.setGeographicAreaID(g.getName());
        geographicAreaDomainService.add(g);

        ctrl.checkGeographicAreaOfTheHouse();

        boolean result = ctrl.checkTemperatureSensorsOfHouseArea();

        assertFalse(result);

    }

    /**
     * HELPER TEST - Ivo
     *
     * @Test public void checkTemperatureSensorsOfHouseArea() {
     * <p>
     * Location l = new Location(41.178553, -8.608035, 111);
     * <p>
     * OccupationArea o = new OccupationArea(l, 0.261, 0.249);
     * <p>
     * GeographicAreaType t = new GeographicAreaType("urban area");
     * <p>
     * GeographicArea g = new GeographicArea("ISEP", "Campus do ISEP", o, t);
     * <p>
     * this.house.setGeographicAreaID(g);
     * <p>
     * ctrl.checkGeographicAreaOfTheHouse(house);
     * <p>
     * int result = ctrl.check();
     * <p>
     * System.out.println(result);
     * }
     */


    @Test
    public void checkTemperatureSensorsOfHouseAreaTRUE() {

        Location l = new Location(41.178553, -8.608035, 111);

        OccupationArea o = new OccupationArea(l, 0.261, 0.249);

        GeographicAreaType t = new GeographicAreaType("urban area");

        GeographicArea g = new GeographicArea("ISEP", "Campus do ISEP", o, t);

        houseDomainService.setGeographicAreaID(g.getName());
        houseDomainService.setLocationOfHouse(l);

        ctrl.checkGeographicAreaOfTheHouse();

        SensorType sensorType = new SensorType("Temperature");
        LocalDate installationDate = LocalDate.of(2017, 1, 1);

        AreaSensor sensor1 = new AreaSensor("S1", "SensorTest", l, sensorType, installationDate, "C");

        ListOfReadings listOfReadings = new ListOfReadings();

        ReadingDto rDto = new ReadingDto("S1", LocalDateTime.of(2018, 12, 30, 2, 0, 0, 0), 14, "C", 0);

        Reading r = new Reading(LocalDateTime.of(2018, 12, 30, 2, 0, 0, 0), 14);

        listOfReadings.addReading(r);

        sensor1.setListOfReadings(listOfReadings);

        g.addSensorToList(sensor1);
        geographicAreaDomainService.add(g);

        //  geographicAreaRepo.addSensorToList(sensor1);

        boolean result = ctrl.checkTemperatureSensorsOfHouseArea();

        assertTrue(result);
    }

    @Test
    void getInstancesWithTemperatureHigherLowerComfortLevel() {
        House house = new House("Casa", new Location(40.7486, -73.9864, 0), "Porto");
        GeographicArea geographicArea = new GeographicArea("Porto", "City", new OccupationArea
                (new Location(11, 20, 30), 0.5, 0.5),
                new GeographicAreaType("City"));
        house.setGeographicAreaID(geographicArea.getName());
        houseDomainService.add(house);

        AreaSensor areaSensor1 = new AreaSensor("1","Test AreaSensor", new Location(40.7486, -73.9864, 0),
                new SensorType("temperature"), LocalDate.of(2019, 1, 15), "l/m2");





        ctrl.checkGeographicAreaOfTheHouse();



        LocalDate startDate = LocalDate.of(2019, 1, 15);
        LocalDate finalDate = LocalDate.of(2019, 1, 18);
        String option = "HIGHER";
        int cat = 1;

        Room room1 = new Room("B107", "Classroom", 1, 10.0, 6.0, 3.5);
        RoomSensor roomSensor = new RoomSensor();
        roomSensor.setName("s1");
        roomSensor.setSensorType(new SensorType("temperature"));
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

        areaSensor1.addReading(re1);
        areaSensor1.addReading(re2);
        areaSensor1.addReading(re3);
        areaSensor1.addReading(re4);
        geographicArea.addSensorToList(areaSensor1);

        RoomDto roomDto = MapperRoom.toDto(room1);
        roomDomainService.addRoom(room1);
        geographicAreaDomainService.add(geographicArea);

        roomSensor.setListOfReadings(listOfReadings);
        room1.addSensor(roomSensor);

        List<LocalDateTime> expectedResult = new ArrayList<>();
        expectedResult.add(r1.getDateTime());
        expectedResult.add(r3.getDateTime());
        expectedResult.add(r4.getDateTime());


        List<LocalDateTime> result = ctrl.getInstancesWithTemperatureHigherLowerComfortLevel(roomDto,cat, option, startDate, finalDate);
        assertEquals(expectedResult, result);

    }

    @Test
    public void testGetInstancesWithTemperatureHigherLowerComfortLevelEmptyMatch() {

        //ARRANGE
        Room room = new Room("B107", "Classroom", 1, 10.0, 6.0, 3.5);
        roomDomainService.addRoom(room);
        RoomDto roomDto = MapperRoom.toDto(room);
        int cat = 1;
        String option = "lower";
        LocalDate startDate = LocalDate.of(2019, 1, 15);
        LocalDate finalDate = LocalDate.of(2019, 2, 15);

        List<LocalDateTime> dateListResult;

        List<LocalDateTime> dateListExpectedResult = new ArrayList<>();

        Location location = new Location(41.178553, -8.608035, 111);
        OccupationArea occupation = new OccupationArea(location, 0.261, 0.249);
        GeographicAreaType gAtype = new GeographicAreaType("urban area");
        GeographicArea ga = new GeographicArea("ISEP", "Campus do ISEP", occupation, gAtype);

        House houseTest = new House("casinha",location, ga.getName());

        ctrl.checkGeographicAreaOfTheHouse();

        // --- Sensor Temp GA
        SensorType sensorType = new SensorType("Temperature");
        LocalDate installationDate = LocalDate.of(2017, 1, 1);

        AreaSensor sensorTempGa = new AreaSensor("S1", "SensorTempGa", location, sensorType, installationDate, "C");

        ListOfReadings listOfReadings = new ListOfReadings();

        Reading reading = new Reading(LocalDateTime.of(2018,12,30,2,0,0,0),14);

        listOfReadings.addReading(reading);

        sensorTempGa.setListOfReadings(listOfReadings);

        ga.addSensorToList(sensorTempGa);
        geographicAreaDomainService.add(ga);
        // ---

        // --- Sensor Temp Room
        RoomSensor roomSensor = new RoomSensor("S2", "SensorTempRoom", sensorType, installationDate, "C");

        roomSensor.addReading(reading);

        room.addSensor(roomSensor);

        roomDomainService.addRoom(room);
        // ---

        //ACT
        dateListResult = ctrl.getInstancesWithTemperatureHigherLowerComfortLevel(roomDto,cat, option, startDate, finalDate);

        //ASSERT
        assertEquals(dateListExpectedResult,dateListResult);
    }

    @Test
    public void testGetInstancesWithTemperatureHigherLowerComfortLevelEmptyRooms() {

        //ARRANGE
        Room room = new Room("B107", "Classroom", 1, 10.0, 6.0, 3.5);
        roomDomainService.addRoom(room);
        RoomDto roomDto = MapperRoom.toDto(room);
        int cat = 1;
        String option = "lower";
        LocalDate startDate = LocalDate.of(2019, 1, 15);
        LocalDate finalDate = LocalDate.of(2019, 2, 15);

        List<LocalDateTime> dateListResult;

        List<LocalDateTime> dateListExpectedResult = new ArrayList<>();

        Location location = new Location(41.178553, -8.608035, 111);
        OccupationArea occupation = new OccupationArea(location, 0.261, 0.249);
        GeographicAreaType gAtype = new GeographicAreaType("urban area");
        GeographicArea ga = new GeographicArea("ISEP", "Campus do ISEP", occupation, gAtype);

        House houseTest = new House("casinha",location, ga.getName());

        ctrl.checkGeographicAreaOfTheHouse();

        // --- Sensor Temp GA
        SensorType sensorType = new SensorType("Temperature");
        LocalDate installationDate = LocalDate.of(2017, 1, 1);

        AreaSensor sensorTempGa = new AreaSensor("S1", "SensorTempGa", location, sensorType, installationDate, "C");

        ListOfReadings listOfReadings = new ListOfReadings();

        Reading reading = new Reading(LocalDateTime.of(2018,12,30,2,0,0,0),14);

        listOfReadings.addReading(reading);

        sensorTempGa.setListOfReadings(listOfReadings);

        ga.addSensorToList(sensorTempGa);
        geographicAreaDomainService.add(ga);
        // ---

        // --- Sensor Temp Room
        RoomSensor roomSensor = new RoomSensor("S2", "SensorTempRoom", sensorType, installationDate, "C");

        roomSensor.addReading(reading);

        room.addSensor(roomSensor);

        // ---

        //ACT
        dateListResult = ctrl.getInstancesWithTemperatureHigherLowerComfortLevel(roomDto,cat, option, startDate, finalDate);

        //ASSERT
        assertEquals(Collections.emptyList(),dateListResult);
    }

    @Test
    public void checkGetInstancesWithTemperatureHigherLowerComfortLevel(){

        setUpTest();

        /**Build Parameters for function */

        RoomDto roomDto = new RoomDto();
        roomDto.setName("B107");
        LocalDate startDate = LocalDate.of(2018,1,1);
        LocalDate endDate = LocalDate.of(2019,1,1);

        List<LocalDateTime> result = ctrl.getInstancesWithTemperatureHigherLowerComfortLevel(roomDto,1,"lower",startDate,endDate);


        //cat1 lower limit : timin = 0.33*t0 + 18.8 − 2
        //ti(min) = 0.33 * 14 + 16.8 = 21.42

        List<LocalDateTime> expectedResult = new ArrayList<>();
        Reading r1 = new Reading(LocalDateTime.of(2018,12,2,1,0,0,0),20);
        Reading r2 = new Reading(LocalDateTime.of(2018,12,2,2,0,0,0),21);

        expectedResult.add(r1.getDateTime());
        expectedResult.add(r2.getDateTime());

        assertEquals(expectedResult,result);
    }

    @Test
    public void checkGetInstancesWithTemperatureHigherLowerComfortLevelInvalidOption(){

        setUpTest();

        /**Build Parameters for function */

        RoomDto roomDto = new RoomDto();
        roomDto.setName("B107");
        LocalDate startDate = LocalDate.of(2018,1,1);
        LocalDate endDate = LocalDate.of(2019,1,1);

        List<LocalDateTime> result = ctrl.getInstancesWithTemperatureHigherLowerComfortLevel(roomDto,1,"trucawuka",startDate,endDate);
        List<LocalDateTime> result2 = ctrl.getInstancesWithTemperatureHigherLowerComfortLevel(roomDto,2,"trucawuka",startDate,endDate);
        List<LocalDateTime> result3 = ctrl.getInstancesWithTemperatureHigherLowerComfortLevel(roomDto,3,"trucawuka",startDate,endDate);

        List<LocalDateTime> expectedResult = new ArrayList<>();


        assertTrue(expectedResult.equals(result)&&expectedResult.equals(result2)&&expectedResult.equals(result3));
    }



    @Test
    public void testIfGetTemperatureSensorsOnRoomFalse(){

        RoomDto roomDto = new RoomDto();
        roomDto.setName("B107");

        boolean result = ctrl.checkTemperatureSensorsOfRoom(roomDto);

        assertFalse(result);
    }


    @Test
    public void testIfGetTemperatureSensorsOnRoomFalse2(){
        //ARRANGE
        Room room = new Room("B107", "Classroom", 1, 10.0, 6.0, 3.5);
        roomDomainService.addRoom(room);
        RoomDto roomDto = new RoomDto();
        roomDto.setName("B107");

        boolean result = ctrl.checkTemperatureSensorsOfRoom(roomDto);

        assertFalse(result);
    }

    @Test
    public void testIfGetTemperatureSensorsOnRoomFalse4(){
        //ARRANGE
        Room room = new Room("B107", "Classroom", 1, 10.0, 6.0, 3.5);
        RoomSensor roomSensor = new RoomSensor("TT12334OA","Temperature B405",new SensorType("temperature"),LocalDate.of(2018,12,10),"C");
        room.addSensor(roomSensor);
        roomDomainService.addRoom(room);
        RoomDto roomDto = new RoomDto();
        roomDto.setName("B108");

        boolean result = ctrl.checkTemperatureSensorsOfRoom(roomDto);

        assertFalse(result);
    }

    @Test
    public void testIfGetTemperatureSensorsOnRoomFalse3(){
        //ARRANGE
        Room room = new Room("B107", "Classroom", 1, 10.0, 6.0, 3.5);
        RoomSensor roomSensor = new RoomSensor("TT12334OA","Temperature B405",new SensorType("rainfall"),LocalDate.of(2018,12,10),"C");
        room.addSensor(roomSensor);
        roomDomainService.addRoom(room);
        RoomDto roomDto = new RoomDto();
        roomDto.setName("B107");

        boolean result = ctrl.checkTemperatureSensorsOfRoom(roomDto);

        assertFalse(result);
    }

    @Test
    public void testIfGetTemperatureSensorsOnRoomTrue(){
        //ARRANGE
        Room room = new Room("B107", "Classroom", 1, 10.0, 6.0, 3.5);
        RoomSensor roomSensor = new RoomSensor("TT12334OA","Temperature B405",new SensorType("temperature"),LocalDate.of(2018,12,10),"C");
        room.addSensor(roomSensor);
        roomDomainService.addRoom(room);
        RoomDto roomDto = new RoomDto();
        roomDto.setName("B107");

        boolean result = ctrl.checkTemperatureSensorsOfRoom(roomDto);

        assertTrue(result);
    }

    @Test
    public void testGetListOfRoomsDto(){
        //ARRANGE
        Room room = new Room("B107", "Classroom", 1, 10.0, 6.0, 3.5);
        RoomSensor roomSensor = new RoomSensor("TT12334OA","Temperature B405",new SensorType("temperature"),LocalDate.of(2018,12,10),"C");
        room.addSensor(roomSensor);
        roomDomainService.addRoom(room);
        RoomDto roomDto = new RoomDto();
        roomDto.setName("B107");
        ListOfRoomsDto result = ctrl.getListOfRoomsDto();
        ListOfRoomsDto expectedResult = new ListOfRoomsDto();
        expectedResult.getRoomDto().add(roomDto);

        assertEquals(expectedResult.getRoomDto().get(0).getName(),result.getRoomDto().get(0).getName());
    }

    @Test
    public void testGetListOfRoomsDtoEmpty(){
        //ARRANGE

        ListOfRoomsDto result = ctrl.getListOfRoomsDto();
        ListOfRoomsDto expectedResult = new ListOfRoomsDto();

        assertEquals(expectedResult.getRoomDto(),result.getRoomDto());
    }

      }