package pt.ipp.isep.dei.project1.controllers.house;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import pt.ipp.isep.dei.project1.dto.housedto.ListOfRoomsDto;
import pt.ipp.isep.dei.project1.dto.housedto.RoomDto;
import pt.ipp.isep.dei.project1.mapper.MapperListOfRooms;
import pt.ipp.isep.dei.project1.model.house.Room;
import pt.ipp.isep.dei.project1.model.repositories.RoomDomainService;

@Controller
public class GetTheCurrentTemperatureInRoomController {

    private final RoomDomainService roomDomainService;

    @Autowired
    public GetTheCurrentTemperatureInRoomController(RoomDomainService roomDomainService) {
        this.roomDomainService = roomDomainService;
    }

    public ListOfRoomsDto getListOfRooms() {
        return MapperListOfRooms.toDto(roomDomainService.getListOfRooms());
    }

    public boolean checkIfListOfSensorsInRoomIsEmpty(RoomDto roomDto) {
        Room room = roomDomainService.getRoomByName(roomDto.getName());
        return room.checkListOfSensorsEmpty();
    }

    public String[] getCurrentRoomTemperature(RoomDto roomDto){
        Room room = roomDomainService.getRoomByName(roomDto.getName());
        return room.getCurrentTemperature();
    }


}
