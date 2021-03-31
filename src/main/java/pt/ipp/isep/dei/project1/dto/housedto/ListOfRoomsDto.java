package pt.ipp.isep.dei.project1.dto.housedto;

import lombok.Getter;
import pt.ipp.isep.dei.project1.mapper.MapperRoom;
import pt.ipp.isep.dei.project1.model.house.Room;

import java.util.ArrayList;
import java.util.List;

public class ListOfRoomsDto {

    @Getter
    private final List<RoomDto> roomDto = new ArrayList<>();

    public void setRoomDto(List<Room> roomList) {
        for (Room room:roomList)
            roomDto.add(MapperRoom.toDto(room));
    }

}
