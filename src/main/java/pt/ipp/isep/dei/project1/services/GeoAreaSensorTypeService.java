package pt.ipp.isep.dei.project1.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.ipp.isep.dei.project1.dto.geographicareadto.GeographicAreaDto;
import pt.ipp.isep.dei.project1.dto.geographicareadto.GeographicAreaDtoWeb;
import pt.ipp.isep.dei.project1.dto.sensordto.AreaSensorDto;
import pt.ipp.isep.dei.project1.dto.sensordto.SensorTypeDto;
import pt.ipp.isep.dei.project1.model.geographicarea.Location;
import pt.ipp.isep.dei.project1.model.repositories.GeographicAreaDomainService;
import pt.ipp.isep.dei.project1.model.repositories.SensorTypeDomainService;

import java.util.List;

@Service
public class GeoAreaSensorTypeService {

    private final GeographicAreaDomainService geographicAreaDomainService;
    private final SensorTypeDomainService sensorTypeDomainService;

    /** Create new Sensor Methods*/

    @Autowired
    public GeoAreaSensorTypeService(GeographicAreaDomainService geographicAreaDomainService, SensorTypeDomainService sensorTypeDomainService) {
        this.geographicAreaDomainService = geographicAreaDomainService;
        this.sensorTypeDomainService = sensorTypeDomainService;
    }

    public List<SensorTypeDto> getListOfSensorTypesDTO() {
        return sensorTypeDomainService.getListOfSensorTypesDTO();
    }

    public GeographicAreaDto getGeographicAreaByNameDTO(String geographicAreaName) {
        return geographicAreaDomainService.getGeographicAreaByNameDTO(geographicAreaName);
    }

    public SensorTypeDto getSensorTypeByNameDTO(String sensorDesignation) {
        return sensorTypeDomainService.getSensorTypeByNameDTO(sensorDesignation);
    }

    public boolean checkOccupationAreaLimits(String geographicAreaName, Location location) {
        return geographicAreaDomainService.checkOccupationAreaLimits(geographicAreaName,location);
    }

    public int createAndAddSensor(AreaSensorDto areaSensorDto, String geographicAreaName) {
       return geographicAreaDomainService.createAndAddSensor(areaSensorDto,geographicAreaName);
    }

    /** Geographic Area Rest Controller Methods*/

    public List<GeographicAreaDtoWeb> getListOfGeographicAreasDTO() {
      return geographicAreaDomainService.getListOfGeographicAreasDTO();
    }

    public AreaSensorDto getSensorByIdDTO(String geographicAreaName, String areaSensorID) {
       return geographicAreaDomainService.getSensorByIdDTO(geographicAreaName,areaSensorID);
    }



}
