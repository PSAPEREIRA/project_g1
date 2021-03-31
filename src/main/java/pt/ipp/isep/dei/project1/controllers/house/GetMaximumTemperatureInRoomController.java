package pt.ipp.isep.dei.project1.controllers.house;

import org.springframework.stereotype.Controller;
import pt.ipp.isep.dei.project1.dto.housedto.ListOfRoomsDto;
import pt.ipp.isep.dei.project1.dto.housedto.RoomDto;
import pt.ipp.isep.dei.project1.mapper.MapperListOfRooms;
import pt.ipp.isep.dei.project1.model.house.Room;
import pt.ipp.isep.dei.project1.model.repositories.RoomDomainService;
import java.time.LocalDate;


@Controller
public class GetMaximumTemperatureInRoomController {

    private final RoomDomainService roomDomainService;

    public GetMaximumTemperatureInRoomController(RoomDomainService roomDomainService) {
        this.roomDomainService = roomDomainService;
    }

    public ListOfRoomsDto getListOfRooms() {
        return MapperListOfRooms.toDto(roomDomainService.getListOfRooms());
    }

    public boolean checkIfListOfSensorsInRoomIsEmpty(RoomDto roomDto) {
        Room room = roomDomainService.getRoomByName(roomDto.getName());
        return room.checkListOfSensorsEmpty();
    }

    public String[] getMaximumRoomTemperature(RoomDto roomDto, LocalDate date) {
        Room room = roomDomainService.getRoomByName(roomDto.getName());
        return room.getMaxRoomTemperature(date);
    }

}
