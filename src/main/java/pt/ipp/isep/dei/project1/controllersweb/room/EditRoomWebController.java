package pt.ipp.isep.dei.project1.controllersweb.room;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pt.ipp.isep.dei.project1.dto.housedto.RoomDto;
import pt.ipp.isep.dei.project1.model.repositories.RoomDomainService;

@RestController
@RequestMapping(value = "/room-configuration")
@CrossOrigin(origins={"http://localhost:3000","http://smarthomeg1.azurewebsites.net"}, maxAge = 3600)
public class EditRoomWebController {

    private final RoomDomainService roomDomainService;

    @Autowired
    public EditRoomWebController(RoomDomainService roomDomainService) {
        this.roomDomainService = roomDomainService;
    }

    @PostMapping(value = "/{room}/edit")
    public ResponseEntity<Object> setNewRoomAttributeName(@PathVariable("room") String name, @RequestBody RoomDto roomDto) {
        RoomDto roomDto2 = roomDomainService.getRoomByNameDto(name);
        if (roomDto2 == null)
            return new ResponseEntity<>(name, HttpStatus.NOT_FOUND);
        RoomDto roomDtoFinal= roomDomainService.editRoom(name, roomDto);
        return new ResponseEntity<>(roomDtoFinal, HttpStatus.OK);
    }
}
