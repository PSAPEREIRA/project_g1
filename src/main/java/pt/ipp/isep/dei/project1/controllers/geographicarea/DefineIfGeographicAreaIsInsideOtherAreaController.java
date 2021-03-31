package pt.ipp.isep.dei.project1.controllers.geographicarea;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import pt.ipp.isep.dei.project1.dto.geographicareadto.GeographicAreaDto;
import pt.ipp.isep.dei.project1.dto.geographicareadto.ListGeographicAreaDto;
import pt.ipp.isep.dei.project1.mapper.MapperListOfGeographicArea;
import pt.ipp.isep.dei.project1.model.geographicarea.GeographicArea;
import pt.ipp.isep.dei.project1.model.repositories.GeographicAreaDomainService;

@Controller
public class DefineIfGeographicAreaIsInsideOtherAreaController {

    private final GeographicAreaDomainService geographicAreaDomainService;

    @Autowired
    public DefineIfGeographicAreaIsInsideOtherAreaController(GeographicAreaDomainService geographicAreaDomainService) {
        this.geographicAreaDomainService = geographicAreaDomainService;
    }

    public ListGeographicAreaDto getListOfGeographicAreas(){
        return MapperListOfGeographicArea.toDto(geographicAreaDomainService.getListOfGeographicArea());
    }

    public boolean geographicAreaIsInsideOfOtherGeographicArea(GeographicAreaDto agSonDto, GeographicAreaDto agDadDto) {
        GeographicArea agSon = geographicAreaDomainService.getGeographicAreaByName(agSonDto.getName());
        GeographicArea agDad = geographicAreaDomainService.getGeographicAreaByName(agDadDto.getName());
        return (agSon.addGeographicAreaInsideAnother(agDad));
    }

}
