package pt.ipp.isep.dei.project1.controllersweb.housemonitoring;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import pt.ipp.isep.dei.project1.controllersweb.housemonitoring.GetMaximumTemperatureInRoomRestController;
import pt.ipp.isep.dei.project1.dto.housedto.RoomDto;
import pt.ipp.isep.dei.project1.mapper.MapperRoom;
import pt.ipp.isep.dei.project1.model.house.Room;
import pt.ipp.isep.dei.project1.model.repositories.RoomDomainService;
import pt.ipp.isep.dei.project1.model.repositories.RoomRepository;
import pt.ipp.isep.dei.project1.model.sensor.ListOfReadings;
import pt.ipp.isep.dei.project1.model.sensor.Reading;
import pt.ipp.isep.dei.project1.model.sensor.RoomSensor;
import pt.ipp.isep.dei.project1.model.sensor.SensorType;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;


@ExtendWith(MockitoExtension.class)
public class GetMaximumTemperatureInRoomRestControllerTest {

    @Mock
    private RoomRepository roomRepository;
    @Mock
    private RoomDomainService roomDomainService;
    @Mock
    private GetMaximumTemperatureInRoomRestController controllerTest;

    @BeforeEach
    void initUseCase() throws Exception {
        roomDomainService = new RoomDomainService(roomRepository);
        controllerTest = new GetMaximumTemperatureInRoomRestController(roomDomainService);
    }


    @Test
    void testGetMaximumRoomTemperature(){

        ArrayList<Double> dimension = new ArrayList<>();
        dimension.add(3.0);
        dimension.add(2.0);
        dimension.add(3.0);
        Room r1 = new Room("Living Room", "room", 2, dimension.get(0),dimension.get(1),dimension.get(2));
        Room r2 = new Room("Kitchen", "room", 1,dimension.get(0),dimension.get(1),dimension.get(2));
        List<Room> listOfRooms = new ArrayList<>();
        RoomSensor roomSensor = new RoomSensor("1", "roomSensor",
                new SensorType("temperature"), LocalDate.of(2018, 12, 10), "ºC");

        LocalDateTime date1 = LocalDateTime.of(2019, 01, 03, 9, 30);
        LocalDateTime date2 = LocalDateTime.of(2019, 01, 03, 9, 31);

        ListOfReadings lisOfReading = new ListOfReadings();
        lisOfReading.addReading(new Reading(date1, 14.0));
        lisOfReading.addReading(new Reading(date2, 12.0));

        roomSensor.setListOfReadings(lisOfReading);

        roomDomainService.addRoom(r1);
        roomDomainService.addRoom(r2);

        r1.addSensor(roomSensor);

        listOfRooms.add(r1);
        listOfRooms.add(r2);
        RoomDto roomDto = MapperRoom.toDto(r1);
        ResponseEntity <Object> result = controllerTest.getMaximumRoomTemperature(roomDto.getName(), LocalDate.of(2019, 1, 03));

        HttpStatus expectedResult = HttpStatus.OK;
        assertEquals(expectedResult, result.getStatusCode());
    }


    @Test
    void testGetMaximumRoomTemperatureNoSensors(){

        ArrayList<Double> dimension = new ArrayList<>();
        dimension.add(3.0);
        dimension.add(2.0);
        dimension.add(3.0);
        Room r1 = new Room("Living Room", "room", 2, dimension.get(0),dimension.get(1),dimension.get(2));
        Room r2 = new Room("Kitchen", "room", 1,dimension.get(0),dimension.get(1),dimension.get(2));
        List<Room> listOfRooms = new ArrayList<>();


        roomDomainService.addRoom(r1);
        roomDomainService.addRoom(r2);

        listOfRooms.add(r1);
        listOfRooms.add(r2);
        RoomDto roomDto = MapperRoom.toDto(r1);
        ResponseEntity <Object> result = controllerTest.getMaximumRoomTemperature(roomDto.getName(), LocalDate.of(2019, 1, 03));
        HttpStatus expectedResult = HttpStatus.CONFLICT;
        assertEquals(expectedResult, result.getStatusCode());
    }


    @Test
    void testGetMaximumRoomTemperatureWithRoomNull(){

        ArrayList<Double> dimension = new ArrayList<>();
        dimension.add(3.0);
        dimension.add(2.0);
        dimension.add(3.0);
        Room r1 = new Room("Living Room", "room", 2, dimension.get(0),dimension.get(1),dimension.get(2));
        List<Room> listOfRooms = new ArrayList<>();
        RoomSensor roomSensor = new RoomSensor("1", "roomSensor",
                new SensorType("temperature"), LocalDate.of(2018, 12, 10), "ºC");

        LocalDateTime date1 = LocalDateTime.of(2019, 01, 03, 9, 30);

        ListOfReadings lisOfReading = new ListOfReadings();
        lisOfReading.addReading(new Reading(date1, 14.0));

        roomSensor.setListOfReadings(lisOfReading);

        r1.addSensor(roomSensor);

        listOfRooms.add(r1);

        RoomDto roomDto = MapperRoom.toDto(r1);
        ResponseEntity <Object> result = controllerTest.getMaximumRoomTemperature(roomDto.getName(), LocalDate.of(2019, 1, 03));
        HttpStatus expectedResult = HttpStatus.NOT_FOUND;
        assertEquals(expectedResult, result.getStatusCode());
    }




}