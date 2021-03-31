package pt.ipp.isep.dei.project1.mapper;

import pt.ipp.isep.dei.project1.dto.geographicareadto.ListOfGeographicAreaTypesDto;
import pt.ipp.isep.dei.project1.model.geographicarea.GeographicAreaType;

import java.util.List;

public final class MapperListOfGeographicAreaType {

    protected MapperListOfGeographicAreaType()  {
        throw new IllegalStateException("Utility class");
    }


    public static ListOfGeographicAreaTypesDto toDto(List<GeographicAreaType> geographicAreaTypeList) {
        ListOfGeographicAreaTypesDto listOfGeographicAreaTypesDto = new ListOfGeographicAreaTypesDto();
        listOfGeographicAreaTypesDto.setListOfGATypesDTO(geographicAreaTypeList);
        return listOfGeographicAreaTypesDto;
    }

    

}
