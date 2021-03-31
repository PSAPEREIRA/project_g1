package pt.ipp.isep.dei.project1.controllers.sensor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import pt.ipp.isep.dei.project1.dto.housedto.ListOfRoomsDto;
import pt.ipp.isep.dei.project1.dto.housedto.RoomDto;
import pt.ipp.isep.dei.project1.dto.sensordto.ListOfRoomSensorsDto;
import pt.ipp.isep.dei.project1.mapper.MapperListOfRoomSensor;
import pt.ipp.isep.dei.project1.mapper.MapperListOfRooms;
import pt.ipp.isep.dei.project1.model.house.Room;
import pt.ipp.isep.dei.project1.model.repositories.RoomDomainService;

@Controller
public class GetAListOfAllSensorsInARoomSoThatICanConfigureThemController {

    private final RoomDomainService roomDomainService;

    @Autowired
    public GetAListOfAllSensorsInARoomSoThatICanConfigureThemController(RoomDomainService roomDomainService) {
        this.roomDomainService = roomDomainService;
    }

    public ListOfRoomsDto getListOfRoomsInAHouse() {
        return MapperListOfRooms.toDto(roomDomainService.getListOfRooms());
    }

    public ListOfRoomSensorsDto getListOfSensorsInARoom(RoomDto roomDto) {
        Room room = roomDomainService.getRoomByName(roomDto.getName());
        return MapperListOfRoomSensor.toDto(room.getListOfSensors());
    }


}
