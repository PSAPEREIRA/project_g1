package pt.ipp.isep.dei.project1.mapper;

import pt.ipp.isep.dei.project1.dto.housedto.RoomDto;
import pt.ipp.isep.dei.project1.model.house.Room;

public final class MapperRoom {

    protected MapperRoom() {
        throw new IllegalStateException("Utility class");
    }

    public static RoomDto toDto(Room room) {
        RoomDto roomDto = new RoomDto();
        roomDto.setName(room.getName());
        roomDto.setDescription(room.getDescription());
        roomDto.setHouseFloor(room.getHouseFloor());
        roomDto.setWidth(room.getWidth());
        roomDto.setLength(room.getLength());
        roomDto.setHeight(room.getHeight());
        roomDto.setHouseGrid(room.getHouseGrid());
        return roomDto;
    }
}
