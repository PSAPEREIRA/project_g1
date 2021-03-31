package pt.ipp.isep.dei.project1.controllersweb.house;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pt.ipp.isep.dei.project1.dto.geographicareadto.GeographicAreaDto;
import pt.ipp.isep.dei.project1.model.geographicarea.Location;
import pt.ipp.isep.dei.project1.model.repositories.GeographicAreaDomainService;
import pt.ipp.isep.dei.project1.model.repositories.HouseDomainService;

@RestController
@RequestMapping(value = "/house-configuration")
@CrossOrigin(origins = {"http://localhost:3000", "http://smarthomeg1.azurewebsites.net"}, maxAge = 3600)
public class ConfigureTheLocationOfTheHouseControllerRest {

    private final GeographicAreaDomainService geographicAreaDomainService;
    private final HouseDomainService houseDomainService;

    @Autowired
    public ConfigureTheLocationOfTheHouseControllerRest(GeographicAreaDomainService geographicAreaDomainService, HouseDomainService houseDomainService) {
        this.geographicAreaDomainService = geographicAreaDomainService;
        this.houseDomainService = houseDomainService;
    }

    @GetMapping(value = "/location")
    public ResponseEntity<Object> getGeoLocationOfHouse() {
        String geoOfHouse = houseDomainService.getGeographicAreaID();

        if (geoOfHouse==null){
            return new ResponseEntity<>("House location is not set yet", HttpStatus.OK);
        }

        return new ResponseEntity<>("House location is currently set in " + geoOfHouse + "!", HttpStatus.OK);
    }


    @PutMapping(value = "/location")
    public ResponseEntity<Object> changeLocationOfTheHouse(@RequestBody Location location, @RequestParam("geo-name")
            String geographicAreaName) {
        GeographicAreaDto geographicAreaDto = geographicAreaDomainService.getGeographicAreaByNameDTO(geographicAreaName);
        if (geographicAreaDto == null)
            return new ResponseEntity<>(("Geographic area with name " + geographicAreaName
                    + " not found!"), HttpStatus.NOT_FOUND);
        if (!geographicAreaDomainService.checkOccupationAreaLimits(geographicAreaName, location))
            return new ResponseEntity<>("Outside limits of " + geographicAreaName + "!", HttpStatus.OK);
        houseDomainService.setLocationOfHouse(location);
        houseDomainService.setGeographicAreaID(geographicAreaName);
        return new ResponseEntity<>("House location set in " + geographicAreaName + "!", HttpStatus.OK);
    }
}

