package pt.ipp.isep.dei.project1.controllersweb.housegrid;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import pt.ipp.isep.dei.project1.controllersweb.room.RoomRestController;
import pt.ipp.isep.dei.project1.dto.housedto.HouseGridDto;
import pt.ipp.isep.dei.project1.dto.housedto.ListOfHouseGridsDto;
import pt.ipp.isep.dei.project1.dto.housedto.RoomDto;
import pt.ipp.isep.dei.project1.mapper.MapperRoom;
import pt.ipp.isep.dei.project1.model.house.HouseGrid;
import pt.ipp.isep.dei.project1.model.house.Room;
import pt.ipp.isep.dei.project1.model.repositories.HouseGridRepo;
import pt.ipp.isep.dei.project1.model.repositories.HouseGridRepository;
import pt.ipp.isep.dei.project1.model.repositories.RoomDomainService;
import pt.ipp.isep.dei.project1.model.repositories.RoomRepository;
import pt.ipp.isep.dei.project1.services.RoomHouseGridService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@ExtendWith(MockitoExtension.class)
public class AttachDetachRoomToHouseGridRestControllerTest {

    @Mock
    private HouseGridRepository houseGridRepository;
    @Mock
    private HouseGridRepo houseGridRepo;
    @Mock
    private RoomRepository roomRepository;
    @Mock
    private RoomDomainService roomDomainService;
    @Mock
    AttachDetachRoomToHouseGridRestController ctrl;
    @Mock
    private RoomHouseGridService roomHouseGridService;


    @BeforeEach
    void initUseCase() throws Exception {
        houseGridRepo = new HouseGridRepo(houseGridRepository);
        roomDomainService = new RoomDomainService(roomRepository);
        roomHouseGridService = new RoomHouseGridService(houseGridRepo, roomDomainService);
        ctrl = new AttachDetachRoomToHouseGridRestController(houseGridRepo, roomDomainService,roomHouseGridService);
    }


    @Test
    public void checkIfGetListOfHouseGrids() {

        HouseGrid houseGrid = new HouseGrid("007", 100);
        HouseGrid houseGrid2 = new HouseGrid("008", 70);
        ArrayList<Double> dimensions1 = new ArrayList<>();
        dimensions1.add(10.0);
        dimensions1.add(6.0);
        dimensions1.add(3.5);

        Room room = new Room("room", "classroom", 0, dimensions1.get(0), dimensions1.get(1), dimensions1.get(2));
        roomHouseGridService.addRoomToHouseGrid(houseGrid,room);
        houseGridRepo.addHouseGrid(houseGrid);
        houseGridRepo.addHouseGrid(houseGrid2);

        ResponseEntity result = ctrl.getListOfHouseGrids();
        ListOfHouseGridsDto listOfHouseGridsDto = new ListOfHouseGridsDto();
        List<HouseGrid> list = new ArrayList<>();
        list.add(houseGrid);
        list.add(houseGrid2);
        listOfHouseGridsDto.setListOfHouseGridDto(list);

        List<HouseGridDto> expectedHouseGridDtoList = listOfHouseGridsDto.getListOfHouseGridDto();

        for (HouseGridDto houseGridDto : expectedHouseGridDtoList) {
            Link selfLink = linkTo(methodOn(HouseGridRestController.class).getHouseGrid(houseGridDto.getCode())).withSelfRel();
            houseGridDto.add(selfLink);
        }

        ResponseEntity<Object> expectedResult = new ResponseEntity<>(expectedHouseGridDtoList, HttpStatus.OK);

        assertEquals(expectedResult,result);
    }

    @Test
    public void checkIfGetListOfHouseGridsNoHg() {

        HouseGrid houseGrid = new HouseGrid("007", 100);
        HouseGrid houseGrid2 = new HouseGrid("008", 70);
        ArrayList<Double> dimensions1 = new ArrayList<>();
        dimensions1.add(10.0);
        dimensions1.add(6.0);
        dimensions1.add(3.5);

        Room room = new Room("room", "classroom", 0, dimensions1.get(0), dimensions1.get(1), dimensions1.get(2));
        roomHouseGridService.addRoomToHouseGrid(houseGrid,room);

     //   houseGridRepo.addHouseGrid(houseGrid);
     //   houseGridRepo.addHouseGrid(houseGrid2);

        ResponseEntity result = ctrl.getListOfHouseGrids();

        ResponseEntity<Object> expectedResult = new ResponseEntity<>("There are no House Grids registered in this app.", HttpStatus.NOT_ACCEPTABLE);

        assertEquals(expectedResult,result);
    }

    @Test
    public void checkIfGetListOfRoomsAttachedToHouseGrid() {

        HouseGrid houseGrid = new HouseGrid("007", 100);
        HouseGrid houseGrid2 = new HouseGrid("008", 70);
        ArrayList<Double> dimensions1 = new ArrayList<>();
        dimensions1.add(10.0);
        dimensions1.add(6.0);
        dimensions1.add(3.5);

        Room room = new Room("room", "classroom", 0, dimensions1.get(0), dimensions1.get(1), dimensions1.get(2));
        roomHouseGridService.addRoomToHouseGrid(houseGrid,room);
        houseGridRepo.addHouseGrid(houseGrid);
        houseGridRepo.addHouseGrid(houseGrid2);

        ResponseEntity result = ctrl.getListOfRoomsAttachedToHouseGrid(houseGrid.getCode());

        List<String> expectedRoomNameList = new ArrayList<>();
       expectedRoomNameList.add(room.getName());


        ResponseEntity<Object> expectedResult = new ResponseEntity<>(expectedRoomNameList, HttpStatus.OK);

        assertEquals(expectedResult,result);
    }

    @Test
    public void checkIfGetListOfRoomsAttachedToHouseGridHgCodeNotFound() {

        HouseGrid houseGrid = new HouseGrid("007", 100);
        HouseGrid houseGrid2 = new HouseGrid("008", 70);
        ArrayList<Double> dimensions1 = new ArrayList<>();
        dimensions1.add(10.0);
        dimensions1.add(6.0);
        dimensions1.add(3.5);

        Room room = new Room("room", "classroom", 0, dimensions1.get(0), dimensions1.get(1), dimensions1.get(2));
        roomHouseGridService.addRoomToHouseGrid(houseGrid,room);
        houseGridRepo.addHouseGrid(houseGrid);
        houseGridRepo.addHouseGrid(houseGrid2);

        String badInput = "wrongCode";

        ResponseEntity result = ctrl.getListOfRoomsAttachedToHouseGrid(badInput);

       String expectedResponse = "HouseGrid with id "+badInput+" not found!";

        ResponseEntity<Object> expectedResult = new ResponseEntity<>(expectedResponse, HttpStatus.NOT_FOUND);

        assertEquals(expectedResult,result);
    }


    @Test
    public void checkIfGetListOfRoomsAttachedToHouseGridHgWithNoRooms() {

        HouseGrid houseGrid = new HouseGrid("007", 100);
        HouseGrid houseGrid2 = new HouseGrid("008", 70);
        ArrayList<Double> dimensions1 = new ArrayList<>();
        dimensions1.add(10.0);
        dimensions1.add(6.0);
        dimensions1.add(3.5);

        Room room = new Room("room", "classroom", 0, dimensions1.get(0), dimensions1.get(1), dimensions1.get(2));
        roomHouseGridService.addRoomToHouseGrid(houseGrid,room);
        houseGridRepo.addHouseGrid(houseGrid);
        houseGridRepo.addHouseGrid(houseGrid2);


        ResponseEntity result = ctrl.getListOfRoomsAttachedToHouseGrid(houseGrid2.getCode());

        String expectedResponse = "This house-grid (" + houseGrid2.getCode()+ ") does not have rooms attached to it";

        ResponseEntity<Object> expectedResult = new ResponseEntity<>(expectedResponse, HttpStatus.NOT_FOUND);

        assertEquals(expectedResult,result);
    }

    @Test
    public void checkIfGetListOfRoomsWithoutHouseGrid() {

        HouseGrid houseGrid = new HouseGrid("007", 100);
        HouseGrid houseGrid2 = new HouseGrid("008", 70);
        ArrayList<Double> dimensions1 = new ArrayList<>();
        dimensions1.add(10.0);
        dimensions1.add(6.0);
        dimensions1.add(3.5);

        Room room = new Room("room", "classroom", 0, dimensions1.get(0), dimensions1.get(1), dimensions1.get(2));
        Room room2 = new Room("room2", "saloon", 0, dimensions1.get(0), dimensions1.get(1), dimensions1.get(2));

        roomHouseGridService.addRoomToHouseGrid(houseGrid,room);
        houseGridRepo.addHouseGrid(houseGrid);
        houseGridRepo.addHouseGrid(houseGrid2);

        roomDomainService.getListOfRooms().add(room2);

        ResponseEntity result = ctrl.getListOfRoomsWithoutHouseGrid();


        List<RoomDto> roomDtoList = new ArrayList<>();
        roomDtoList.add(MapperRoom.toDto(room2));

        for (RoomDto roomDto : roomDtoList) {
            Link selfLink = linkTo(methodOn(RoomRestController.class).getRoom(roomDto.getName())).withSelfRel();
            roomDto.add(selfLink);
        }

        ResponseEntity<Object> expectedResult = new ResponseEntity<>(roomDtoList, HttpStatus.OK);

        assertEquals(expectedResult,result);
    }

    @Test
    public void checkIfGetListOfRoomsWithoutHouseGridAllRoomsWithHg() {

        HouseGrid houseGrid = new HouseGrid("007", 100);
        HouseGrid houseGrid2 = new HouseGrid("008", 70);
        ArrayList<Double> dimensions1 = new ArrayList<>();
        dimensions1.add(10.0);
        dimensions1.add(6.0);
        dimensions1.add(3.5);

        Room room = new Room("room", "classroom", 0, dimensions1.get(0), dimensions1.get(1), dimensions1.get(2));

        roomHouseGridService.addRoomToHouseGrid(houseGrid,room);
        houseGridRepo.addHouseGrid(houseGrid);
        houseGridRepo.addHouseGrid(houseGrid2);

        ResponseEntity result = ctrl.getListOfRoomsWithoutHouseGrid();

        String expectedResponse = "There are no rooms without house-grids.";

        ResponseEntity<Object> expectedResult = new ResponseEntity<>(expectedResponse, HttpStatus.NOT_FOUND);

        assertEquals(expectedResult,result);
    }

    @Test
    public void checkIfAttachRoomToHouseGrid() {

        HouseGrid houseGrid = new HouseGrid("007", 100);
        HouseGrid houseGrid2 = new HouseGrid("008", 70);
        ArrayList<Double> dimensions1 = new ArrayList<>();
        dimensions1.add(10.0);
        dimensions1.add(6.0);
        dimensions1.add(3.5);

        Room room = new Room("room", "classroom", 0, dimensions1.get(0), dimensions1.get(1), dimensions1.get(2));
        Room room2 = new Room("room2", "saloon", 0, dimensions1.get(0), dimensions1.get(1), dimensions1.get(2));

        roomHouseGridService.addRoomToHouseGrid(houseGrid,room);
        houseGridRepo.addHouseGrid(houseGrid);
        houseGridRepo.addHouseGrid(houseGrid2);

        roomDomainService.getListOfRooms().add(room2);

        ResponseEntity result = ctrl.attachRoomToHouseGrid(houseGrid.getCode(),room2.getName());

        String expectedResponse =("Successfully attached room: " + room2.getName() + " to house-grid: " + houseGrid.getCode());

        ResponseEntity<Object> expectedResult = new ResponseEntity<>(expectedResponse, HttpStatus.OK);

        assertEquals(expectedResult,result);
    }

    @Test
    public void checkIfAttachRoomToHouseGridNoRoomsNotFound() {

        HouseGrid houseGrid = new HouseGrid("007", 100);
        HouseGrid houseGrid2 = new HouseGrid("008", 70);
        ArrayList<Double> dimensions1 = new ArrayList<>();
        dimensions1.add(10.0);
        dimensions1.add(6.0);
        dimensions1.add(3.5);

        Room room = new Room("room", "classroom", 0, dimensions1.get(0), dimensions1.get(1), dimensions1.get(2));
        Room room2 = new Room("room2", "saloon", 0, dimensions1.get(0), dimensions1.get(1), dimensions1.get(2));

        roomHouseGridService.addRoomToHouseGrid(houseGrid,room);
        houseGridRepo.addHouseGrid(houseGrid);
        houseGridRepo.addHouseGrid(houseGrid2);

      //  roomRepo.getListOfRooms().add(room2);

        ResponseEntity result = ctrl.attachRoomToHouseGrid(houseGrid.getCode(),room2.getName());

        String expectedResponse =("Room with name " + room2.getName() + " not found!");

        ResponseEntity<Object> expectedResult = new ResponseEntity<>(expectedResponse, HttpStatus.NOT_FOUND);

        assertEquals(expectedResult,result);
    }

    @Test
    public void checkIfAttachRoomToHouseGridNoHgNotFound() {

        HouseGrid houseGrid = new HouseGrid("007", 100);
        HouseGrid houseGrid2 = new HouseGrid("008", 70);
        ArrayList<Double> dimensions1 = new ArrayList<>();
        dimensions1.add(10.0);
        dimensions1.add(6.0);
        dimensions1.add(3.5);

        Room room = new Room("room", "classroom", 0, dimensions1.get(0), dimensions1.get(1), dimensions1.get(2));
        Room room2 = new Room("room2", "saloon", 0, dimensions1.get(0), dimensions1.get(1), dimensions1.get(2));

        roomHouseGridService.addRoomToHouseGrid(houseGrid,room);
        houseGridRepo.addHouseGrid(houseGrid);
        //   houseGridRepo.addHouseGrid(houseGrid2);

        roomDomainService.getListOfRooms().add(room2);

        ResponseEntity result = ctrl.attachRoomToHouseGrid(houseGrid2.getCode(),room2.getName());

        String expectedResponse =("HouseGrid with code " + houseGrid2.getCode() + " not found!");

        ResponseEntity<Object> expectedResult = new ResponseEntity<>(expectedResponse, HttpStatus.NOT_FOUND);

        assertEquals(expectedResult,result);
    }

    @Test
    public void checkIfAttachRoomToHouseGridRoomAlreadyAttachedToHg() {

        HouseGrid houseGrid = new HouseGrid("007", 100);
        HouseGrid houseGrid2 = new HouseGrid("008", 70);
        ArrayList<Double> dimensions1 = new ArrayList<>();
        dimensions1.add(10.0);
        dimensions1.add(6.0);
        dimensions1.add(3.5);

        Room room = new Room("room", "classroom", 0, dimensions1.get(0), dimensions1.get(1), dimensions1.get(2));
        Room room2 = new Room("room2", "saloon", 0, dimensions1.get(0), dimensions1.get(1), dimensions1.get(2));

        roomHouseGridService.addRoomToHouseGrid(houseGrid,room);
        houseGridRepo.addHouseGrid(houseGrid);
        houseGridRepo.addHouseGrid(houseGrid2);

        roomDomainService.getListOfRooms().add(room);
        roomDomainService.getListOfRooms().add(room2);

        ResponseEntity result = ctrl.attachRoomToHouseGrid(houseGrid2.getCode(),room.getName());

        String expectedResponse =("This room: " + room.getName()+ " is already attached to a house-grid (" + houseGrid.getCode() + ").");

        ResponseEntity<Object> expectedResult = new ResponseEntity<>(expectedResponse, HttpStatus.NOT_ACCEPTABLE);

        assertEquals(expectedResult,result);
    }

    @Test
    public void checkIfAttachRoomToHouseGridNoHg() {

        HouseGrid houseGrid = new HouseGrid("007", 100);
        HouseGrid houseGrid2 = new HouseGrid("008", 70);
        ArrayList<Double> dimensions1 = new ArrayList<>();
        dimensions1.add(10.0);
        dimensions1.add(6.0);
        dimensions1.add(3.5);

        Room room = new Room("room", "classroom", 0, dimensions1.get(0), dimensions1.get(1), dimensions1.get(2));
        Room room2 = new Room("room2", "saloon", 0, dimensions1.get(0), dimensions1.get(1), dimensions1.get(2));

     //   roomHouseGridService.addRoomToHouseGrid(houseGrid,room);
     //   houseGridRepo.addHouseGrid(houseGrid);
     //   houseGridRepo.addHouseGrid(houseGrid2);

      //  roomRepo.getListOfRooms().add(room2);

        ResponseEntity result = ctrl.attachRoomToHouseGrid(houseGrid.getCode(),room2.getName());

        String expectedResponse =("There are no House Grids registered in this app.");

        ResponseEntity<Object> expectedResult = new ResponseEntity<>(expectedResponse, HttpStatus.NOT_ACCEPTABLE);

        assertEquals(expectedResult,result);
    }

    @Test
    public void checkIfDetachRoomFromHouseGrid() {

        HouseGrid houseGrid = new HouseGrid("007", 100);
        HouseGrid houseGrid2 = new HouseGrid("008", 70);
        ArrayList<Double> dimensions1 = new ArrayList<>();
        dimensions1.add(10.0);
        dimensions1.add(6.0);
        dimensions1.add(3.5);

        Room room = new Room("room", "classroom", 0, dimensions1.get(0), dimensions1.get(1), dimensions1.get(2));
        Room room2 = new Room("room2", "saloon", 0, dimensions1.get(0), dimensions1.get(1), dimensions1.get(2));

        roomHouseGridService.addRoomToHouseGrid(houseGrid,room);
        houseGridRepo.addHouseGrid(houseGrid);
        houseGridRepo.addHouseGrid(houseGrid2);

        roomDomainService.getListOfRooms().add(room);
        roomDomainService.getListOfRooms().add(room2);

        ResponseEntity result = ctrl.detachRoomFromHouseGrid(houseGrid.getCode(),room.getName());

        String expectedResponse =("Successfully detached room: " + room.getName() + " from house-grid: " + houseGrid.getCode());

        ResponseEntity<Object> expectedResult = new ResponseEntity<>(expectedResponse, HttpStatus.OK);

        assertEquals(expectedResult,result);
    }

    @Test
    public void checkIfDetachRoomFromHouseGridFAILNoHg() {

        HouseGrid houseGrid = new HouseGrid("007", 100);
        HouseGrid houseGrid2 = new HouseGrid("008", 70);
        ArrayList<Double> dimensions1 = new ArrayList<>();
        dimensions1.add(10.0);
        dimensions1.add(6.0);
        dimensions1.add(3.5);

        Room room = new Room("room", "classroom", 0, dimensions1.get(0), dimensions1.get(1), dimensions1.get(2));
        Room room2 = new Room("room2", "saloon", 0, dimensions1.get(0), dimensions1.get(1), dimensions1.get(2));

        roomHouseGridService.addRoomToHouseGrid(houseGrid,room);
       // houseGridRepo.addHouseGrid(houseGrid);
    //    houseGridRepo.addHouseGrid(houseGrid2);

        roomDomainService.getListOfRooms().add(room);
        roomDomainService.getListOfRooms().add(room2);

        ResponseEntity result = ctrl.detachRoomFromHouseGrid(houseGrid.getCode(),room.getName());

        String expectedResponse =("There are no House Grids registered in this app.");

        ResponseEntity<Object> expectedResult = new ResponseEntity<>(expectedResponse, HttpStatus.NOT_ACCEPTABLE);

        assertEquals(expectedResult,result);
    }

    @Test
    public void checkIfDetachRoomFromHouseGridWrongHG() {

        HouseGrid houseGrid = new HouseGrid("007", 100);
        HouseGrid houseGrid2 = new HouseGrid("008", 70);
        ArrayList<Double> dimensions1 = new ArrayList<>();
        dimensions1.add(10.0);
        dimensions1.add(6.0);
        dimensions1.add(3.5);

        Room room = new Room("room", "classroom", 0, dimensions1.get(0), dimensions1.get(1), dimensions1.get(2));
        Room room2 = new Room("room2", "saloon", 0, dimensions1.get(0), dimensions1.get(1), dimensions1.get(2));

        roomHouseGridService.addRoomToHouseGrid(houseGrid,room);
        //   houseGridRepo.addHouseGrid(houseGrid);
        houseGridRepo.addHouseGrid(houseGrid2);

        roomDomainService.getListOfRooms().add(room);
        roomDomainService.getListOfRooms().add(room2);

        ResponseEntity result = ctrl.detachRoomFromHouseGrid(houseGrid.getCode(),room.getName());

        String expectedResponse =("HouseGrid with code " + houseGrid.getCode() + " not found!");

        ResponseEntity<Object> expectedResult = new ResponseEntity<>(expectedResponse, HttpStatus.NOT_FOUND);

        assertEquals(expectedResult,result);
    }

    @Test
    public void checkIfDetachRoomFromHouseGridBadRoom() {

        HouseGrid houseGrid = new HouseGrid("007", 100);
        HouseGrid houseGrid2 = new HouseGrid("008", 70);
        ArrayList<Double> dimensions1 = new ArrayList<>();
        dimensions1.add(10.0);
        dimensions1.add(6.0);
        dimensions1.add(3.5);

        Room room = new Room("room", "classroom", 0, dimensions1.get(0), dimensions1.get(1), dimensions1.get(2));
        Room room2 = new Room("room2", "saloon", 0, dimensions1.get(0), dimensions1.get(1), dimensions1.get(2));

        roomHouseGridService.addRoomToHouseGrid(houseGrid,room);
        houseGridRepo.addHouseGrid(houseGrid);
        houseGridRepo.addHouseGrid(houseGrid2);

        //  roomRepo.getListOfRooms().add(room);
        roomDomainService.getListOfRooms().add(room2);

        ResponseEntity result = ctrl.detachRoomFromHouseGrid(houseGrid.getCode(),room.getName());

        String expectedResponse =("Room with name " + room.getName() + " not found!");

        ResponseEntity<Object> expectedResult = new ResponseEntity<>(expectedResponse, HttpStatus.NOT_FOUND);

        assertEquals(expectedResult,result);
    }

    @Test
    public void checkIfDetachRoomFromHouseGridRoomNotAttachedToSelectedHg() {

        HouseGrid houseGrid = new HouseGrid("007", 100);
        HouseGrid houseGrid2 = new HouseGrid("008", 70);
        ArrayList<Double> dimensions1 = new ArrayList<>();
        dimensions1.add(10.0);
        dimensions1.add(6.0);
        dimensions1.add(3.5);

        Room room = new Room("room", "classroom", 0, dimensions1.get(0), dimensions1.get(1), dimensions1.get(2));
        Room room2 = new Room("room2", "saloon", 0, dimensions1.get(0), dimensions1.get(1), dimensions1.get(2));

        roomHouseGridService.addRoomToHouseGrid(houseGrid,room);
        houseGridRepo.addHouseGrid(houseGrid);
        houseGridRepo.addHouseGrid(houseGrid2);

        roomDomainService.getListOfRooms().add(room);
        roomDomainService.getListOfRooms().add(room2);

        ResponseEntity result = ctrl.detachRoomFromHouseGrid(houseGrid.getCode(),room2.getName());

        String expectedResponse =("Room " + room2.getName() + " is not attached to this house-grid (" + houseGrid.getCode() + ")");

        ResponseEntity<Object> expectedResult = new ResponseEntity<>(expectedResponse, HttpStatus.NOT_ACCEPTABLE);

        assertEquals(expectedResult,result);
    }

}