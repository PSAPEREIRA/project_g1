package pt.ipp.isep.dei.project1.controllersweb.room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pt.ipp.isep.dei.project1.dto.housedto.RoomDto;
import pt.ipp.isep.dei.project1.dto.sensordto.RoomSensorDto;
import pt.ipp.isep.dei.project1.dto.sensordto.SensorTypeDto;
import pt.ipp.isep.dei.project1.services.RoomSensorTypeService;

@Controller
@RequestMapping(value = "/room-configuration")
@CrossOrigin(origins={"http://localhost:3000","http://smarthomeg1.azurewebsites.net"}, maxAge = 3600)
public class CreateNewSensorAndAttachToRoomRestController {

    private final RoomSensorTypeService appService;


    @Autowired
    public CreateNewSensorAndAttachToRoomRestController(RoomSensorTypeService appService) {
        this.appService = appService;
    }

    @PostMapping(value = "/rooms/{room-name}/new-sensor")
    public ResponseEntity<Object> createNewSensor(@PathVariable("room-name") String roomName, @RequestBody RoomSensorDto roomSensorDto) {
        RoomDto roomDto = appService.getRoomByNameDto(roomName);
        if (roomDto == null){
            return new ResponseEntity<>(("Room with name " + roomName
                    + "not found!"), HttpStatus.NOT_FOUND);}
        SensorTypeDto sensorTypeDto = appService.getSensorTypeByNameDTO(roomSensorDto.getSensorType().getType());
        if (sensorTypeDto == null){
            return new ResponseEntity<>(("Sensor Type with name " + roomSensorDto.getSensorType()
                    + " not found!"), HttpStatus.NOT_FOUND);}
        int result = appService.createAndAddSensor(roomSensorDto, roomName);
        if (result == -1)
            return new ResponseEntity<>("Impossible to create sensor!", HttpStatus.NOT_ACCEPTABLE);
        else if (result == -2)
            return new ResponseEntity<>("Impossible to add!", HttpStatus.UNPROCESSABLE_ENTITY);
        return new ResponseEntity<>(roomSensorDto.getIdOfSensor() + " added to " + roomDto.getName(), HttpStatus.CREATED);
    }
}
