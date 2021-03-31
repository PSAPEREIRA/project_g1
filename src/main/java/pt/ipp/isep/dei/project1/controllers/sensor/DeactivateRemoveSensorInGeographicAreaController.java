package pt.ipp.isep.dei.project1.controllers.sensor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import pt.ipp.isep.dei.project1.dto.geographicareadto.GeographicAreaDto;
import pt.ipp.isep.dei.project1.dto.geographicareadto.ListGeographicAreaDto;
import pt.ipp.isep.dei.project1.dto.sensordto.ListOfAreaSensorsDto;
import pt.ipp.isep.dei.project1.dto.sensordto.AreaSensorDto;
import pt.ipp.isep.dei.project1.mapper.MapperListOfGeographicArea;
import pt.ipp.isep.dei.project1.mapper.MapperListOfAreaSensor;
import pt.ipp.isep.dei.project1.model.geographicarea.GeographicArea;
import pt.ipp.isep.dei.project1.model.sensor.AreaSensor;
import pt.ipp.isep.dei.project1.model.sensor.Status;
import pt.ipp.isep.dei.project1.model.repositories.GeographicAreaDomainService;

import java.time.LocalDate;
import java.util.List;

@Controller
public class DeactivateRemoveSensorInGeographicAreaController {

    private final GeographicAreaDomainService geographicAreaDomainService;

    @Autowired
    public DeactivateRemoveSensorInGeographicAreaController(GeographicAreaDomainService geographicAreaDomainService) {
        this.geographicAreaDomainService = geographicAreaDomainService;
    }

    public ListGeographicAreaDto getListGeographicAreaDto() {
        return MapperListOfGeographicArea.toDto(geographicAreaDomainService.getListOfGeographicArea());
    }

    public boolean deactivateSensor(GeographicAreaDto geographicAreaInput, AreaSensorDto sensorInputOption) {
        GeographicArea geographicArea = geographicAreaDomainService.getGeographicAreaByName(geographicAreaInput.getName());
        AreaSensor areaSensor = geographicArea.getSensorBySensorID(sensorInputOption.getIdOfSensor());
            LocalDate dateOfDeactivate = LocalDate.now();
            Status status = new Status(false, dateOfDeactivate);
            areaSensor.getListOfStatus().getStatusList().add(status);
            geographicAreaDomainService.getGeographicAreaRepository().save(geographicArea);
            return true;
    }

    public ListOfAreaSensorsDto getListOfSensorsActivate(GeographicAreaDto optionGeographicArea) {
        GeographicArea geographicArea = geographicAreaDomainService.getGeographicAreaByName(optionGeographicArea.getName());
        List<AreaSensor> listOfSensorsActivated = geographicArea.getListOfSensorsActive();
        return MapperListOfAreaSensor.toDto(listOfSensorsActivated);
    }


    public ListOfAreaSensorsDto getListOfSensorsDtoFromGA(GeographicAreaDto optionGeographicArea) {
        GeographicArea geographicArea = geographicAreaDomainService.getGeographicAreaByName(optionGeographicArea.getName());
        List<AreaSensor> listOfAreaSensors = geographicArea.getListOfAreaSensors();
        return MapperListOfAreaSensor.toDto(listOfAreaSensors);
    }


    public boolean removeSensorFromGA(GeographicAreaDto geographicAreaDto, AreaSensorDto sensorInput) {
        return geographicAreaDomainService.removeSensor(geographicAreaDto.getName(),sensorInput.getIdOfSensor());
    }
}
