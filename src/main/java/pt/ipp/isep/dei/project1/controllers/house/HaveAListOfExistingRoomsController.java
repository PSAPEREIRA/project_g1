package pt.ipp.isep.dei.project1.controllers.house;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import pt.ipp.isep.dei.project1.dto.housedto.ListOfRoomsDto;
import pt.ipp.isep.dei.project1.dto.housedto.RoomDto;
import pt.ipp.isep.dei.project1.mapper.MapperListOfRooms;
import pt.ipp.isep.dei.project1.model.house.Room;
import pt.ipp.isep.dei.project1.model.repositories.RoomDomainService;

import java.util.List;

@Controller
public class HaveAListOfExistingRoomsController {

    private final RoomDomainService roomDomainService;

    @Autowired
    public HaveAListOfExistingRoomsController(RoomDomainService roomDomainService) {
        this.roomDomainService = roomDomainService;
    }

    public void setNewRoomAttributeName(RoomDto roomDto, String name) {
        Room room = roomDomainService.getRoomByName(roomDto.getName());
        room.setName(name);
        roomDomainService.getRoomRepository().save(room);
    }

    public void setNewRoomAttributeFloor(RoomDto roomDto, double houseFloor) {
        Room room = roomDomainService.getRoomByName(roomDto.getName());
        room.setHouseFloor(houseFloor);
        roomDomainService.getRoomRepository().save(room);

    }

    public void setNewRoomAttributeDimensions(RoomDto roomDto, List<Double> roomDimensions) {
        Room room = roomDomainService.getRoomByName(roomDto.getName());
        room.setHeight(roomDimensions.get(0));
        room.setWidth(roomDimensions.get(1));
        room.setLength(roomDimensions.get(2));
        roomDomainService.getRoomRepository().save(room);
    }

    public ListOfRoomsDto getListOfRooms() {
        return MapperListOfRooms.toDto(roomDomainService.getListOfRooms());
    }
}
