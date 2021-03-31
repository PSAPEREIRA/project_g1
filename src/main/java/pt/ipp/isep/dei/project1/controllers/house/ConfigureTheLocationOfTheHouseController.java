package pt.ipp.isep.dei.project1.controllers.house;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import pt.ipp.isep.dei.project1.dto.geographicareadto.GeographicAreaDto;
import pt.ipp.isep.dei.project1.dto.geographicareadto.ListGeographicAreaDto;
import pt.ipp.isep.dei.project1.mapper.MapperListOfGeographicArea;
import pt.ipp.isep.dei.project1.model.geographicarea.GeographicArea;
import pt.ipp.isep.dei.project1.model.geographicarea.Location;
import pt.ipp.isep.dei.project1.model.repositories.GeographicAreaDomainService;
import pt.ipp.isep.dei.project1.model.repositories.HouseDomainService;

import java.util.List;

/**
 * US101 As an Administrator, I want to configure the location of the house.
 */

@Controller
public class ConfigureTheLocationOfTheHouseController {

    private final GeographicAreaDomainService geographicAreaDomainService;
    private final HouseDomainService houseDomainService;

    @Autowired
    public ConfigureTheLocationOfTheHouseController(GeographicAreaDomainService geographicAreaDomainService, HouseDomainService houseDomainService) {
        this.geographicAreaDomainService = geographicAreaDomainService;
        this.houseDomainService = houseDomainService;
    }

    public ListGeographicAreaDto getListOfGeographicAreas(){
        return MapperListOfGeographicArea.toDto(geographicAreaDomainService.getListOfGeographicArea());
    }

    private Location createLocationUsingListOfUserInputs(List<Double> listOfUserInputs) {
        return new Location(listOfUserInputs.get(0), listOfUserInputs.get(1), listOfUserInputs.get(2));
    }

    public boolean checkLocationOfTheHouse(List<Double> locationValues, GeographicAreaDto geographicAreaDto) {
        Location location = createLocationUsingListOfUserInputs(locationValues);
        GeographicArea geographicArea = geographicAreaDomainService.getGeographicAreaByName(geographicAreaDto.getName());
        return (geographicArea.getOccupationArea().occupationAreaLimits(location));
    }

    public void changeLocationOfTheHouse(List<Double> locationValues, GeographicAreaDto geographicAreaDto) {
        Location location = createLocationUsingListOfUserInputs(locationValues);
        String geographicAreaName = geographicAreaDomainService.getGeographicAreaByName(geographicAreaDto.getName()).getName();
        houseDomainService.setLocationOfHouse(location);
        houseDomainService.setGeographicAreaID(geographicAreaName);
    }
}
