package pt.ipp.isep.dei.project1.controllersweb.room;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pt.ipp.isep.dei.project1.dto.housedto.RoomDto;
import pt.ipp.isep.dei.project1.dto.sensordto.SensorTypeDto;
import pt.ipp.isep.dei.project1.model.repositories.RoomDomainService;
import pt.ipp.isep.dei.project1.model.repositories.SensorTypeDomainService;

import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@RequestMapping(value = "/room-configuration")
@CrossOrigin(origins={"http://localhost:3000","http://smarthomeg1.azurewebsites.net"}, maxAge = 3600)
public class RoomRestController {

    private final RoomDomainService roomDomainService;
    private final SensorTypeDomainService sensorTypeDomainService;


    @Autowired
    public RoomRestController(RoomDomainService roomDomainService, SensorTypeDomainService sensorTypeDomainService) {
        this.roomDomainService = roomDomainService;
        this.sensorTypeDomainService = sensorTypeDomainService;
    }

    @GetMapping(value = "/rooms")
    public ResponseEntity<Object> getListOfRooms() {
        List<RoomDto> listOfRoomsDto = roomDomainService.getRoomsDTO().getRoomDto();
        for (RoomDto roomDto : listOfRoomsDto) {
            Link selfLink = linkTo(methodOn(RoomRestController.class).getRoom(roomDto.getName())).withSelfRel();
            Link sensorsLink = linkTo(methodOn(GetListOfAllSensorsInARoomRestController.class).getListOfAllSensorsInARoom(roomDto.getName())).withSelfRel();
            roomDto.add(selfLink);
            roomDto.add(sensorsLink);
        }
        return new ResponseEntity<>(listOfRoomsDto, HttpStatus.OK);
    }

    @GetMapping(value = "/{room-name}")
    public ResponseEntity<Object> getRoom(@PathVariable("room-name") String name) {
        RoomDto roomDto = roomDomainService.getRoomByNameDto(name);
        if (roomDto != null)
            return new ResponseEntity<>(roomDto, HttpStatus.OK);
        return new ResponseEntity<>("Room not found!", HttpStatus.NO_CONTENT);
    }

    @GetMapping(value = "/sensor-types")
    public ResponseEntity<Object> getListOfSensorTypes() {
        List<SensorTypeDto> result = sensorTypeDomainService.getListOfSensorTypesDTO();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }


}
