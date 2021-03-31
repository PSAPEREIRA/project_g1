package pt.ipp.isep.dei.project1.mapper;

import pt.ipp.isep.dei.project1.dto.geographicareadto.ListGeographicAreaDto;
import pt.ipp.isep.dei.project1.dto.geographicareadto.ListGeographicAreaDtoWeb;
import pt.ipp.isep.dei.project1.model.geographicarea.GeographicArea;

import java.util.List;

public final class MapperListOfGeographicArea {

    protected MapperListOfGeographicArea()  {
        throw new IllegalStateException("Utility class");
    }

    public static ListGeographicAreaDto toDto(List<GeographicArea> geographicAreaList) {
        ListGeographicAreaDto listGeographicAreaDto = new ListGeographicAreaDto();
        listGeographicAreaDto.setListOfGADto(geographicAreaList);
        return listGeographicAreaDto;
    }

    public static ListGeographicAreaDtoWeb toDtoWeb(List<GeographicArea> geographicAreaList) {
        ListGeographicAreaDtoWeb listGeographicAreaDto = new ListGeographicAreaDtoWeb();
        listGeographicAreaDto.setListOfGADto(geographicAreaList);
        return listGeographicAreaDto;
    }
}
