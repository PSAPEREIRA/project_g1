package pt.ipp.isep.dei.project1.controllersweb.geographicarea;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import pt.ipp.isep.dei.project1.dto.geographicareadto.GeographicAreaDto;
import pt.ipp.isep.dei.project1.model.repositories.GeographicAreaDomainService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/geographic-area-configuration")
@CrossOrigin(origins={"http://localhost:3000","http://smarthomeg1.azurewebsites.net"}, maxAge = 3600)
public class DefineIfGeographicAreaIsInsideOtherAreaRestController {

    private final GeographicAreaDomainService geographicAreaDomainService;

    @Autowired
    public DefineIfGeographicAreaIsInsideOtherAreaRestController(GeographicAreaDomainService geographicAreaDomainService) {
        this.geographicAreaDomainService = geographicAreaDomainService;
    }

    @PutMapping(value = "/ga_inside_other/son/{agSon}/dad/{agDad}")
    public ResponseEntity<Object> geographicAreaIsInsideOfOtherGeographicArea(@PathVariable("agSon") String agSonName, @PathVariable("agDad") String agDadName) {

        GeographicAreaDto agSon = geographicAreaDomainService.getGeographicAreaByNameDTO(agSonName);
        if (agSon == null)
            return new ResponseEntity<>(("Geographic area with name " + agSonName
                    + " not found!"), HttpStatus.NOT_FOUND);

        GeographicAreaDto agDad = geographicAreaDomainService.getGeographicAreaByNameDTO(agDadName);
        if (agDad == null)
            return new ResponseEntity<>(("Geographic Area with name " + agDadName
                    + " not found!"), HttpStatus.NOT_FOUND);

        if (geographicAreaDomainService.addGeographicAreaInsideAnother(agSonName,agDadName))
            return new ResponseEntity<>("Added!", HttpStatus.CREATED);
        return new ResponseEntity<>("Impossible to add!", HttpStatus.CONFLICT);
    }
}