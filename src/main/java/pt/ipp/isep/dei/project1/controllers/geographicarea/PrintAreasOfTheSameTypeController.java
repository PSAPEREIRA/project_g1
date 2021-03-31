package pt.ipp.isep.dei.project1.controllers.geographicarea;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import pt.ipp.isep.dei.project1.dto.geographicareadto.GeographicAreaTypeDto;
import pt.ipp.isep.dei.project1.dto.geographicareadto.ListGeographicAreaDto;
import pt.ipp.isep.dei.project1.dto.geographicareadto.ListOfGeographicAreaTypesDto;
import pt.ipp.isep.dei.project1.mapper.MapperListOfGeographicArea;
import pt.ipp.isep.dei.project1.mapper.MapperListOfGeographicAreaType;
import pt.ipp.isep.dei.project1.model.geographicarea.GeographicAreaType;
import pt.ipp.isep.dei.project1.model.repositories.GeographicAreaDomainService;
import pt.ipp.isep.dei.project1.model.repositories.GeographicAreaTypeRepo;

@Controller
public class PrintAreasOfTheSameTypeController {

    private final GeographicAreaDomainService geographicAreaDomainService;
    private final GeographicAreaTypeRepo geographicAreaTypeRepo;

    @Autowired
    public PrintAreasOfTheSameTypeController(GeographicAreaDomainService geographicAreaDomainService, GeographicAreaTypeRepo geographicAreaTypeRepo) {
        this.geographicAreaDomainService = geographicAreaDomainService;
        this.geographicAreaTypeRepo = geographicAreaTypeRepo;
    }

    public ListOfGeographicAreaTypesDto getListOfGeographicAreaTypes() {
        return MapperListOfGeographicAreaType.toDto(geographicAreaTypeRepo.getListOfGeographicAreaType());
    }

    public ListGeographicAreaDto controlVerificationGeographicArea(GeographicAreaTypeDto geographicAreaTypeDto) {
        GeographicAreaType geographicAreaType = geographicAreaTypeRepo.getTypeByName(geographicAreaTypeDto.getName());
        return MapperListOfGeographicArea.toDto(geographicAreaDomainService.getGeographicAreaListByAGType(geographicAreaType));
    }

}



