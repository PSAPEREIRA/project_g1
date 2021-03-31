package pt.ipp.isep.dei.project1.controllers.sensor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import pt.ipp.isep.dei.project1.dto.geographicareadto.GeographicAreaDto;
import pt.ipp.isep.dei.project1.dto.geographicareadto.ListGeographicAreaDto;
import pt.ipp.isep.dei.project1.dto.sensordto.ListOfSensorTypesDto;
import pt.ipp.isep.dei.project1.dto.sensordto.SensorTypeDto;
import pt.ipp.isep.dei.project1.mapper.MapperListOfGeographicArea;
import pt.ipp.isep.dei.project1.mapper.MapperListOfSensorType;
import pt.ipp.isep.dei.project1.model.geographicarea.GeographicArea;
import pt.ipp.isep.dei.project1.model.geographicarea.Location;
import pt.ipp.isep.dei.project1.model.sensor.AreaSensor;
import pt.ipp.isep.dei.project1.model.sensor.SensorType;
import pt.ipp.isep.dei.project1.model.repositories.GeographicAreaDomainService;
import pt.ipp.isep.dei.project1.model.repositories.SensorTypeDomainService;

import java.time.LocalDate;
import java.util.List;

@Controller
public class CreateNewSensorAndAssociateToGeographicAreaController {


    private final GeographicAreaDomainService geographicAreaDomainService;
    private final SensorTypeDomainService sensorTypeDomainService;

    @Autowired
    public CreateNewSensorAndAssociateToGeographicAreaController(GeographicAreaDomainService geographicAreaDomainService, SensorTypeDomainService sensorTypeDomainService) {
        this.geographicAreaDomainService = geographicAreaDomainService;
        this.sensorTypeDomainService = sensorTypeDomainService;
    }


    private Location createLocationUsingListOfUserInputs(List<Double> listOfUserInputs) {
        return new Location(listOfUserInputs.get(0), listOfUserInputs.get(1), listOfUserInputs.get(2));
    }

    public boolean checkLocationOfTheSensor (List<Double> locationValues, GeographicAreaDto geographicAreaDto) {
        Location location = createLocationUsingListOfUserInputs(locationValues);
        GeographicArea geographicArea = geographicAreaDomainService.getGeographicAreaByName(geographicAreaDto.getName());
        return (geographicArea.getOccupationArea().occupationAreaLimits(location));
    }

    /**
     * Method to create a New sensor
     *
     * @param nameOfSensor
     * @return
     */

    public boolean createNewSensorWithLocation(String iD, String nameOfSensor, List<Double> listOfUserInputsLocation, SensorTypeDto sensorTypeDto, LocalDate localDate, String unit, GeographicAreaDto geographicAreaDto) {
        SensorType sensorType = sensorTypeDomainService.getSensorTypeByName(sensorTypeDto.getDesignation());
        GeographicArea geographicArea = geographicAreaDomainService.getGeographicAreaByName(geographicAreaDto.getName());
        Location location = createLocationUsingListOfUserInputs(listOfUserInputsLocation);
        AreaSensor newAreaSensor = geographicArea.newSensor(iD, nameOfSensor, location, sensorType, localDate, unit);
        newAreaSensor.setGeographicArea(geographicArea);
        if (geographicAreaDomainService.addSensorToList(newAreaSensor)) {
            geographicAreaDomainService.add(geographicArea);
            return true;
        }

        return false;
    }

    public ListOfSensorTypesDto getListOfSensorsType() {
        return MapperListOfSensorType.toDto(sensorTypeDomainService.getListOfSensorsType());
    }

    public ListGeographicAreaDto getListOfGeographicArea() {
        return MapperListOfGeographicArea.toDto(geographicAreaDomainService.getListOfGeographicArea());

    }
}
