package pt.ipp.isep.dei.project1.mapper;

import pt.ipp.isep.dei.project1.dto.geographicareadto.GeographicAreaDto;
import pt.ipp.isep.dei.project1.dto.geographicareadto.GeographicAreaDtoWeb;
import pt.ipp.isep.dei.project1.model.geographicarea.GeographicArea;

public final class MapperGeographicArea {

    protected MapperGeographicArea()  {
        throw new IllegalStateException("Utility class");
    }

    public static GeographicAreaDto toDto(GeographicArea geographicArea){
        GeographicAreaDto geographicAreaDto = new GeographicAreaDto();
        geographicAreaDto.setName(geographicArea.getName());
        geographicAreaDto.setDescription(geographicArea.getDescription());
        geographicAreaDto.setGeographicAreaType(geographicArea.getGeographicAreaType());
        geographicAreaDto.setOccupationArea(geographicArea.getOccupationArea());
        geographicAreaDto.setListInsideOf(MapperListOfGeographicArea.toDto(geographicArea.getListInsideOf()));
        geographicAreaDto.setListOfAreaSensorsDto(MapperListOfAreaSensor.toDto(geographicArea.getListOfAreaSensors()));
        return geographicAreaDto;
    }

    public static GeographicAreaDtoWeb toDtoWeb(GeographicArea geographicArea){
        GeographicAreaDtoWeb geographicAreaDto = new GeographicAreaDtoWeb();
        geographicAreaDto.setName(geographicArea.getName());
        geographicAreaDto.setDescription(geographicArea.getDescription());
        return geographicAreaDto;
    }
}
