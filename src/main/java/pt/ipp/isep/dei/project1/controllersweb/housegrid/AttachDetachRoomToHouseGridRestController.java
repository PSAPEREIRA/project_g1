package pt.ipp.isep.dei.project1.controllersweb.housegrid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pt.ipp.isep.dei.project1.controllersweb.room.RoomRestController;
import pt.ipp.isep.dei.project1.dto.housedto.HouseGridDto;
import pt.ipp.isep.dei.project1.dto.housedto.RoomDto;
import pt.ipp.isep.dei.project1.model.repositories.HouseGridRepo;
import pt.ipp.isep.dei.project1.services.RoomHouseGridService;
import pt.ipp.isep.dei.project1.model.repositories.RoomDomainService;

import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@RequestMapping(value = "/house-grid-configuration")
@CrossOrigin(origins={"http://localhost:3000","http://smarthomeg1.azurewebsites.net"}, maxAge = 3600)
public class AttachDetachRoomToHouseGridRestController {

    private final HouseGridRepo houseGridRepo;
    private final RoomDomainService roomDomainService;
    private final RoomHouseGridService roomHouseGridService;
    private static final String NOT_FOUND = " not found!";
    private static final String NO_HOUSE_GRIDS = "There are no House Grids registered in this app.";


    @Autowired
    public AttachDetachRoomToHouseGridRestController(HouseGridRepo houseGridRepo, RoomDomainService roomDomainService, RoomHouseGridService roomHouseGridService) {
        this.houseGridRepo = houseGridRepo;
        this.roomDomainService = roomDomainService;
        this.roomHouseGridService = roomHouseGridService;
    }

    @GetMapping(value = "/house-grids")
    public ResponseEntity<Object> getListOfHouseGrids() {

      if (houseGridRepo.getHouseGridDtoList().isEmpty())
          return new ResponseEntity<>(NO_HOUSE_GRIDS, HttpStatus.NOT_ACCEPTABLE);

        List<HouseGridDto> gridDtoList = houseGridRepo.getHouseGridDtoList();

        for (HouseGridDto houseGridDto : gridDtoList) {
            Link selfLink = linkTo(methodOn(HouseGridRestController.class).getHouseGrid(houseGridDto.getCode())).withSelfRel();
            houseGridDto.add(selfLink);
        }

        return new ResponseEntity<>(gridDtoList, HttpStatus.OK);
    }


    @GetMapping(value = "/house-grids/{house-grid-code}/rooms")
    public ResponseEntity<Object> getListOfRoomsAttachedToHouseGrid(@PathVariable("house-grid-code") String houseGridCode) {
        HouseGridDto houseGridDto = houseGridRepo.getHouseGridByCodeDto(houseGridCode);
        if (houseGridDto == null)
            return new ResponseEntity<>(("HouseGrid with id " + houseGridCode
                    + NOT_FOUND), HttpStatus.NOT_FOUND);
        List<String> result = houseGridDto.getRoomList();

        if (result.isEmpty())
            return new ResponseEntity<>(("This house-grid (" + houseGridCode
                    + ") does not have rooms attached to it"), HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping(value = "/rooms-without-grid")
    public ResponseEntity<Object> getListOfRoomsWithoutHouseGrid() {
        List<RoomDto> roomDtoList = roomDomainService.getRoomsWithoutHouseGridDTO().getRoomDto();

        if (roomDtoList.isEmpty()) {
            return new ResponseEntity<>("There are no rooms without house-grids.", HttpStatus.NOT_FOUND);
        }

        for (RoomDto roomDto : roomDtoList) {
            Link selfLink = linkTo(methodOn(RoomRestController.class).getRoom(roomDto.getName())).withSelfRel();
            roomDto.add(selfLink);
        }

        return new ResponseEntity<>(roomDtoList, HttpStatus.OK);
    }

    @PostMapping(value = "/house-grids/{house-grid-code}/rooms")
    public ResponseEntity<Object> attachRoomToHouseGrid(@PathVariable("house-grid-code") String houseGridCode, @RequestParam("room-name") String roomName) {

        if (houseGridRepo.getListOfHouseGrids().isEmpty())
            return new ResponseEntity<>((NO_HOUSE_GRIDS), HttpStatus.NOT_ACCEPTABLE);

        HouseGridDto houseGridDto = houseGridRepo.getHouseGridByCodeDto(houseGridCode);
        if (houseGridDto == null)
            return new ResponseEntity<>(("HouseGrid with code " + houseGridCode
                    + NOT_FOUND), HttpStatus.NOT_FOUND);

        RoomDto roomDto = roomDomainService.getRoomByNameDto(roomName);

        if (roomDto == null)
            return new ResponseEntity<>(("Room with name " + roomName
                    + NOT_FOUND), HttpStatus.NOT_FOUND);

        if (!roomDto.getHouseGrid().equals(""))
            return new ResponseEntity<>(("This room: " + roomName
                    + " is already attached to a house-grid (" + roomDto.getHouseGrid() + ")."), HttpStatus.NOT_ACCEPTABLE);

        roomHouseGridService.addRoomToHouseGridIds(houseGridCode, roomName);

        return new ResponseEntity<>(("Successfully attached room: " + roomName + " to house-grid: " + houseGridCode), HttpStatus.OK);
    }

    @DeleteMapping(value = "/house-grids/{house-grid-code}/rooms")
    public ResponseEntity<Object> detachRoomFromHouseGrid(@PathVariable("house-grid-code") String houseGridCode, @RequestParam("room-name") String roomName) {
        HouseGridDto houseGridDto = houseGridRepo.getHouseGridByCodeDto(houseGridCode);

        if (houseGridRepo.getListOfHouseGrids().isEmpty())
            return new ResponseEntity<>((NO_HOUSE_GRIDS), HttpStatus.NOT_ACCEPTABLE);

        if (houseGridDto == null)
            return new ResponseEntity<>(("HouseGrid with code " + houseGridCode
                    + NOT_FOUND), HttpStatus.NOT_FOUND);
        RoomDto roomDto = roomDomainService.getRoomByNameDto(roomName);
        if (roomDto == null)
            return new ResponseEntity<>(("Room with name " + roomName
                    + NOT_FOUND), HttpStatus.NOT_FOUND);

        if (!houseGridDto.getRoomList().contains(roomDto.getName())) {
            return new ResponseEntity<>(("Room " + roomName
                    + " is not attached to this house-grid (" + houseGridCode + ")"), HttpStatus.NOT_ACCEPTABLE);
        }

        roomHouseGridService.removeRoomFromHouseGridIds(houseGridCode, roomName);

        return new ResponseEntity<>(("Successfully detached room: " + roomName + " from house-grid: " + houseGridCode), HttpStatus.OK);
    }

}
