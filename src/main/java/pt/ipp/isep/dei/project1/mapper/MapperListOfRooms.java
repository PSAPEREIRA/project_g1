package pt.ipp.isep.dei.project1.mapper;

import pt.ipp.isep.dei.project1.dto.housedto.ListOfRoomsDto;
import pt.ipp.isep.dei.project1.model.house.Room;
import java.util.List;

public final class MapperListOfRooms {

    protected MapperListOfRooms()  {
        throw new IllegalStateException("Utility class");
    }

    public static ListOfRoomsDto toDto(List<Room> roomsList) {
        ListOfRoomsDto listOfRoomsDto = new ListOfRoomsDto();
        listOfRoomsDto.setRoomDto(roomsList);
        return listOfRoomsDto;
    }

}
