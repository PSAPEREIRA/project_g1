package pt.ipp.isep.dei.project1.mapper;

import pt.ipp.isep.dei.project1.dto.housedto.HouseGridDto;
import pt.ipp.isep.dei.project1.dto.housedto.ListOfHouseGridsDto;
import pt.ipp.isep.dei.project1.model.house.HouseGrid;

import java.util.ArrayList;
import java.util.List;

public final class MapperListOfHouseGrids {

    protected MapperListOfHouseGrids() {
        throw new IllegalStateException("Utility class");
    }

    public static ListOfHouseGridsDto toDto(List<HouseGrid> houseGridList) {
        ListOfHouseGridsDto listOfHouseGridsDto = new ListOfHouseGridsDto();
        listOfHouseGridsDto.setListOfHouseGridDto(houseGridList);
        return listOfHouseGridsDto;
    }

    public static List<HouseGridDto> toDtoList(List<HouseGrid> houseGrids){
        List<HouseGridDto> dtoList = new ArrayList<>();

        for (HouseGrid hg : houseGrids){
            dtoList.add(MapperHouseGrid.toDto(hg));
        }
        return dtoList;
    }


}
