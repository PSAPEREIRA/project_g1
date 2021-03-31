package pt.ipp.isep.dei.project1.dto.geographicareadto;

import lombok.Getter;
import pt.ipp.isep.dei.project1.mapper.MapperGeographicAreaType;
import pt.ipp.isep.dei.project1.model.geographicarea.GeographicAreaType;

import java.util.ArrayList;
import java.util.List;

public class ListOfGeographicAreaTypesDto {

    @Getter
    private final List<GeographicAreaTypeDto> listOfGATypesDto = new ArrayList<>();


    public void setListOfGATypesDTO(List<GeographicAreaType> mListOfGATypes) {
        for (GeographicAreaType geographicAreaType:mListOfGATypes)
            listOfGATypesDto.add(MapperGeographicAreaType.toDto(geographicAreaType));
    }

}

