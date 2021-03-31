package pt.ipp.isep.dei.project1.controllersweb.geographicarea;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pt.ipp.isep.dei.project1.dto.geographicareadto.GeographicAreaDto;
import pt.ipp.isep.dei.project1.model.repositories.GeographicAreaDomainService;
import pt.ipp.isep.dei.project1.model.repositories.GeographicAreaTypeRepo;
import java.util.Collections;


@RestController
@RequestMapping(value = "/geographic-area-configuration")
@CrossOrigin(origins={"http://localhost:3000","http://smarthomeg1.azurewebsites.net"}, maxAge = 3600)
public class CreateSaveNewGeographicAreaRestController {

    private final GeographicAreaTypeRepo geographicAreaTypeRepo;
    private final GeographicAreaDomainService geographicAreaDomainService;

    @Autowired
    public CreateSaveNewGeographicAreaRestController(GeographicAreaDomainService geographicAreaDomainService, GeographicAreaTypeRepo geographicAreaTypeRepo) {
        this.geographicAreaDomainService = geographicAreaDomainService;
        this.geographicAreaTypeRepo = geographicAreaTypeRepo;
    }

    @GetMapping(value = "/geographic-area-types")
    public ResponseEntity<Object> getListOfGeographicAreasTypes() {
        if (!geographicAreaTypeRepo.getListOfAreaTypesDTO().equals(Collections.emptyList()))
            return new ResponseEntity<>(geographicAreaTypeRepo.getListOfAreaTypesDTO(), HttpStatus.OK);
        return new ResponseEntity<>("List of types empty!", HttpStatus.NOT_ACCEPTABLE);
    }

    @PostMapping(value = "/new")
    public ResponseEntity<Object> createNewGeographicArea(@RequestBody GeographicAreaDto geographicAreaDto) {
        boolean result = geographicAreaDomainService.newGeographicArea(geographicAreaDto);
        if (result)
            return new ResponseEntity<>(geographicAreaDto.getName() + " added!", HttpStatus.CREATED);
        return new ResponseEntity<>("Impossible to create or add!", HttpStatus.CONFLICT);
    }


}
