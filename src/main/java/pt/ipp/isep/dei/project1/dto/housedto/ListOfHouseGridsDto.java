package pt.ipp.isep.dei.project1.dto.housedto;

import lombok.Getter;
import pt.ipp.isep.dei.project1.mapper.MapperHouseGrid;
import pt.ipp.isep.dei.project1.model.house.HouseGrid;
import java.util.ArrayList;
import java.util.List;

public class ListOfHouseGridsDto {

    @Getter
    private final List<HouseGridDto> listOfHouseGridDto = new ArrayList<>();

    public void setListOfHouseGridDto(List<HouseGrid> listOfHouseGrid) {
        for (HouseGrid houseGrid:listOfHouseGrid)
            listOfHouseGridDto.add(MapperHouseGrid.toDto(houseGrid));
    }

}
