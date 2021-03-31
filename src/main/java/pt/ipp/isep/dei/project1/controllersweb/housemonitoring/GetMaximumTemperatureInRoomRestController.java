package pt.ipp.isep.dei.project1.controllersweb.housemonitoring;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pt.ipp.isep.dei.project1.dto.housedto.RoomDto;
import pt.ipp.isep.dei.project1.model.repositories.RoomDomainService;

import java.time.LocalDate;

import static org.springframework.beans.factory.config.YamlProcessor.MatchStatus.NOT_FOUND;

@RestController
@RequestMapping(value = "/house-configuration")
@CrossOrigin(origins={"http://localhost:3000","http://smarthomeg1.azurewebsites.net"}, maxAge = 3600)
public class GetMaximumTemperatureInRoomRestController {

    private final RoomDomainService roomDomainService;

    public GetMaximumTemperatureInRoomRestController(RoomDomainService roomDomainService) {
        this.roomDomainService = roomDomainService;
    }

    @GetMapping(value = "/maximum-temperature/{roomName}/{date}")
    public ResponseEntity<Object> getMaximumRoomTemperature(@PathVariable String roomName, @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        RoomDto roomDto = roomDomainService.getRoomByNameDto(roomName);
        if (roomDto == null)
            return new ResponseEntity<>(("Room with name" + roomName + NOT_FOUND), HttpStatus.NOT_FOUND);
        boolean result1 = roomDomainService.checkListOfSensorsEmpty(roomName);
        if (result1)
            return new ResponseEntity<>(roomDto.getName() + " don't have temperature sensors!", HttpStatus.CONFLICT);
        String[] result = roomDomainService.getMaxRoomTemperature(roomDto, date);
        return new ResponseEntity<>((result), HttpStatus.OK);
    }

}
