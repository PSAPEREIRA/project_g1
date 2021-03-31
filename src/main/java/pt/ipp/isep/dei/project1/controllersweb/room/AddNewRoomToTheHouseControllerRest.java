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
public class AddNewRoomToTheHouseControllerRest {
    private final RoomDomainService roomDomainService;

    @Autowired
    public AddNewRoomToTheHouseControllerRest(RoomDomainService roomDomainService) {
        this.roomDomainService = roomDomainService;
    }

    @PostMapping(value = "/new")
    public ResponseEntity<Object> createNewRoom( @RequestBody RoomDto newRoomDto) {
        boolean result = roomDomainService.newRoom(newRoomDto);
        if (result)
            return new ResponseEntity<>(newRoomDto.getName() + " added!", HttpStatus.CREATED);
        return new ResponseEntity<>("Impossible to create or add!", HttpStatus.CONFLICT);
    }


}
