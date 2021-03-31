package pt.ipp.isep.dei.project1.controllersweb.room;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pt.ipp.isep.dei.project1.dto.housedto.RoomDto;
import pt.ipp.isep.dei.project1.dto.sensordto.RoomSensorDto;
import pt.ipp.isep.dei.project1.model.repositories.RoomDomainService;
import java.util.List;

@RestController
@RequestMapping(value = "/room-configuration")
@CrossOrigin(origins={"http://localhost:3000","http://smarthomeg1.azurewebsites.net"}, maxAge = 3600)
public class GetListOfAllSensorsInARoomRestController {

    private final RoomDomainService roomDomainService;

    @Autowired
    public GetListOfAllSensorsInARoomRestController(RoomDomainService roomDomainService) {
        this.roomDomainService = roomDomainService;
    }

    @GetMapping(value = "/{room-name}/sensors")
    public ResponseEntity<Object> getListOfAllSensorsInARoom(@PathVariable("room-name") String roomName) {
        RoomDto roomDto = roomDomainService.getRoomByNameDto(roomName);
        if (roomDto == null)
            return new ResponseEntity<>(("Room with name " + roomName
                    + "not found!"), HttpStatus.NOT_FOUND);
        List<RoomSensorDto> result = roomDomainService.getListOfAllSensorsInARoomDto(roomName);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
