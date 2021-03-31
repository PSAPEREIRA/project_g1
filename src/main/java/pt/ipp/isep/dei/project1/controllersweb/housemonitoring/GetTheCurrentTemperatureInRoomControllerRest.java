package pt.ipp.isep.dei.project1.controllersweb.housemonitoring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pt.ipp.isep.dei.project1.dto.housedto.RoomDto;
import pt.ipp.isep.dei.project1.model.repositories.RoomDomainService;

import static org.springframework.beans.factory.config.YamlProcessor.MatchStatus.NOT_FOUND;

@RestController
@RequestMapping(value = "/room-configuration")
@CrossOrigin(origins={"http://localhost:3000","http://smarthomeg1.azurewebsites.net"}, maxAge = 3600)
public class GetTheCurrentTemperatureInRoomControllerRest {

    private final RoomDomainService roomDomainService;

    @Autowired
    public GetTheCurrentTemperatureInRoomControllerRest(RoomDomainService roomDomainService) {
        this.roomDomainService = roomDomainService;
    }

    @GetMapping(value = "/{room-name}/current-room-temperature")
    public ResponseEntity<Object> getCurrentRoomTemperature(@PathVariable ("room-name") String roomName) {
        RoomDto roomDto = roomDomainService.getRoomByNameDto(roomName);
        if (roomDto == null)
            return new ResponseEntity<>(("Room with name " + roomName + NOT_FOUND), HttpStatus.NOT_FOUND);
        boolean result1 = roomDomainService.checkListOfSensorsEmpty(roomName);
        if (result1)
            return new ResponseEntity<>(roomDto.getName() + " don't have temperature sensors!", HttpStatus.CONFLICT);
        String[] result = roomDomainService.getCurrentTemperature(roomName);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }


}