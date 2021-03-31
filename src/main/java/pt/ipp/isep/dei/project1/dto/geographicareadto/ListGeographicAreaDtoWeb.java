package pt.ipp.isep.dei.project1.dto.geographicareadto;

import lombok.Getter;
import pt.ipp.isep.dei.project1.mapper.MapperGeographicArea;
import pt.ipp.isep.dei.project1.model.geographicarea.GeographicArea;

import java.util.ArrayList;
import java.util.List;

@Getter
public class ListGeographicAreaDtoWeb {

    private final List<GeographicAreaDtoWeb> listOfGADto = new ArrayList<>();

    public void setListOfGADto(List<GeographicArea> mListOfGA) {
        for (GeographicArea geographicArea:mListOfGA)
            listOfGADto.add(MapperGeographicArea.toDtoWeb(geographicArea));
    }

}
