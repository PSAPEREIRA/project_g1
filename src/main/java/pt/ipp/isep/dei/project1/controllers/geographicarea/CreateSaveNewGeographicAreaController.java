package pt.ipp.isep.dei.project1.controllers.geographicarea;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import pt.ipp.isep.dei.project1.dto.geographicareadto.GeographicAreaTypeDto;
import pt.ipp.isep.dei.project1.dto.geographicareadto.ListOfGeographicAreaTypesDto;
import pt.ipp.isep.dei.project1.mapper.MapperListOfGeographicAreaType;
import pt.ipp.isep.dei.project1.model.geographicarea.*;
import pt.ipp.isep.dei.project1.model.repositories.GeographicAreaDomainService;
import pt.ipp.isep.dei.project1.model.repositories.GeographicAreaTypeRepo;

@Controller
public class CreateSaveNewGeographicAreaController {

    private final GeographicAreaTypeRepo geographicAreaTypeRepo;
    private final GeographicAreaDomainService geographicAreaDomainService;

    @Autowired
    public CreateSaveNewGeographicAreaController(GeographicAreaDomainService geographicAreaDomainService, GeographicAreaTypeRepo geographicAreaTypeRepo) {
        this.geographicAreaDomainService = geographicAreaDomainService;
        this.geographicAreaTypeRepo = geographicAreaTypeRepo;
    }

    /**
     * @return
     */

    public ListOfGeographicAreaTypesDto getListOfGeographicAreasTypes() {
        return MapperListOfGeographicAreaType.toDto(geographicAreaTypeRepo.getListOfGeographicAreaType());
    }


    public boolean createNewGeographicArea(String nameOfGA, String description,
                                           double[] coordinates, double width,
                                           double length, GeographicAreaTypeDto geographicAreaTypeDto) {

        Location location = new Location(coordinates[0], coordinates[1], coordinates[2]);
        OccupationArea occupationArea = new OccupationArea(location, width, length);

        GeographicAreaType geographicAreaType = geographicAreaTypeRepo.getTypeByName(geographicAreaTypeDto.getName());

        GeographicArea newGA = new GeographicArea(nameOfGA, description, occupationArea, geographicAreaType);
        return geographicAreaDomainService.add(newGA);
    }


}
