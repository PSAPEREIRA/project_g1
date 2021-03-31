package pt.ipp.isep.dei.project1.mapper;

import pt.ipp.isep.dei.project1.dto.geographicareadto.GeographicAreaTypeDto;
import pt.ipp.isep.dei.project1.model.geographicarea.GeographicAreaType;


public final class MapperGeographicAreaType {

    protected MapperGeographicAreaType() {
        throw new IllegalStateException("Utility class");
    }

    public static GeographicAreaTypeDto toDto(GeographicAreaType geographicAreaType) {
        GeographicAreaTypeDto geographicAreaTypesDto = new GeographicAreaTypeDto();
        geographicAreaTypesDto.setName(geographicAreaType.getType());
        return geographicAreaTypesDto;
    }
}
