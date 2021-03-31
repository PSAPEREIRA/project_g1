package pt.ipp.isep.dei.project1.controllers.geographicarea;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import pt.ipp.isep.dei.project1.dto.geographicareadto.ListGeographicAreaDto;
import pt.ipp.isep.dei.project1.mapper.MapperListOfGeographicArea;
import pt.ipp.isep.dei.project1.model.repositories.GeographicAreaDomainService;

@Controller
public class PrintInfoIfOneGeographicAreaIsInOtherController {

    private final GeographicAreaDomainService geographicAreaDomainService;

    @Autowired
    public PrintInfoIfOneGeographicAreaIsInOtherController(GeographicAreaDomainService geographicAreaDomainService) {
        this.geographicAreaDomainService = geographicAreaDomainService;
    }

    public ListGeographicAreaDto getListOfGeographicAreas(){
        return MapperListOfGeographicArea.toDto(geographicAreaDomainService.getListOfGeographicArea());
    }

}
