package pt.ipp.isep.dei.project1.controllers.geographicarea;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import pt.ipp.isep.dei.project1.dto.geographicareadto.ListOfGeographicAreaTypesDto;
import pt.ipp.isep.dei.project1.mapper.MapperListOfGeographicAreaType;
import pt.ipp.isep.dei.project1.model.repositories.GeographicAreaTypeRepo;

@Controller
public class PrintListOfGeographicAreaTypesController {

    private final GeographicAreaTypeRepo geographicAreaTypeRepo;

    @Autowired
    public PrintListOfGeographicAreaTypesController(GeographicAreaTypeRepo geographicAreaTypeRepo) {
        this.geographicAreaTypeRepo = geographicAreaTypeRepo;
    }

    public ListOfGeographicAreaTypesDto getListOfGeographicAreasTypes() {
        return MapperListOfGeographicAreaType.toDto(geographicAreaTypeRepo.getListOfGeographicAreaType());
    }
}
