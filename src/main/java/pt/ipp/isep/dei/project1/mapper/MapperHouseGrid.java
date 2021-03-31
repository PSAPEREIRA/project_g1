package pt.ipp.isep.dei.project1.mapper;

import pt.ipp.isep.dei.project1.dto.housedto.HouseGridDto;
import pt.ipp.isep.dei.project1.model.house.HouseGrid;

public final class MapperHouseGrid {

    protected MapperHouseGrid() {
        throw new IllegalStateException("Utility class");
    }


    public static HouseGridDto toDto(HouseGrid houseGrid) {
        HouseGridDto houseGridDto = new HouseGridDto();
        houseGridDto.setCode(houseGrid.getCode());
        houseGridDto.setRoomList(houseGrid.getRoomsList());
        return houseGridDto;
    }
}
