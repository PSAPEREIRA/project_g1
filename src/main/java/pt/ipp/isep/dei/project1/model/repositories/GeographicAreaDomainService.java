package pt.ipp.isep.dei.project1.model.repositories;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.ipp.isep.dei.project1.dto.geographicareadto.GeographicAreaDto;
import pt.ipp.isep.dei.project1.dto.geographicareadto.GeographicAreaDtoWeb;
import pt.ipp.isep.dei.project1.dto.sensordto.AreaSensorDto;
import pt.ipp.isep.dei.project1.dto.sensordto.ReadingDto;
import pt.ipp.isep.dei.project1.mapper.*;
import pt.ipp.isep.dei.project1.model.geographicarea.GeographicArea;
import pt.ipp.isep.dei.project1.model.geographicarea.GeographicAreaType;
import pt.ipp.isep.dei.project1.model.geographicarea.Location;
import pt.ipp.isep.dei.project1.model.sensor.AreaSensor;
import pt.ipp.isep.dei.project1.model.sensor.SensorType;
import pt.ipp.isep.dei.project1.model.sensor.Status;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@Service
public class GeographicAreaDomainService {

    @Getter
    private final List<GeographicArea> listOfGeographicArea;
    private final GeographicAreaRepository geographicAreaRepository;

    @Autowired
    public GeographicAreaDomainService(GeographicAreaRepository geographicAreaRepository) {
        this.geographicAreaRepository = geographicAreaRepository;
        this.listOfGeographicArea = geographicAreaRepository.findAll();
    }

    public GeographicAreaRepository getGeographicAreaRepository() {
        return geographicAreaRepository;
    }

    /**
     * GA Methods
     */

    public boolean add(GeographicArea ag) {
        if (!listOfGeographicArea.contains(ag)) {
            listOfGeographicArea.add(ag);
            geographicAreaRepository.save(ag);
            return true;
        }
        return false;
    }

    public boolean newGeographicArea(GeographicAreaDto geographicAreaDto) {
        GeographicArea geographicArea;
        try {
            geographicArea = new GeographicArea(geographicAreaDto.getName(), geographicAreaDto.getDescription(),
                    geographicAreaDto.getOccupationArea(), geographicAreaDto.getGeographicAreaType());
        } catch (Exception e) {
            return false;
        }
        return add(geographicArea);
    }

    public List<GeographicArea> getGeographicAreaListByAGType(GeographicAreaType aGType) {
        List<GeographicArea> listGASameType = new ArrayList<>();
        for (GeographicArea ga : listOfGeographicArea) {
            if (ga.getGeographicAreaType().equals(aGType))
                listGASameType.add(ga);
        }
        return listGASameType;
    }

    public GeographicArea getGeographicAreaByName(String name) {
        for (GeographicArea ga : listOfGeographicArea)
            if (ga.getName().equalsIgnoreCase(name))
                return ga;
        return null;
    }

    public GeographicAreaDto getGeographicAreaByNameDTO(String geographicAreaName) {
        GeographicArea geographicArea = getGeographicAreaByName(geographicAreaName);
        if (geographicArea != null)
            return MapperGeographicArea.toDto(geographicArea);
        return null;
    }

    public List<GeographicAreaDtoWeb> getListOfGeographicAreasDTO() {
        if (!listOfGeographicArea.isEmpty())
            return MapperListOfGeographicArea.toDtoWeb(listOfGeographicArea).getListOfGADto();
        return Collections.emptyList();
    }

    public boolean checkOccupationAreaLimits(String geographicAreaName, Location location) {
        GeographicArea geographicArea = getGeographicAreaByName(geographicAreaName);
        if (geographicArea != null)
            return geographicArea.getOccupationArea().occupationAreaLimits(location);
        return false;
    }

    public boolean addGeographicAreaInsideAnother(String agSonName, String agDadName) {
        GeographicArea agSon = getGeographicAreaByName(agSonName);
        GeographicArea agDad = getGeographicAreaByName(agDadName);
        return agDad.addGeographicAreaInsideAnother(agSon);
    }

    public boolean checkIfHaveSensorsOnHouseAreaWithTypeAndReadings(String geographicAreaName, Location location, SensorType sensorType) {
        GeographicArea geographicArea = getGeographicAreaByName(geographicAreaName);
        return (geographicArea != null && geographicArea.getSensorClosestForReadings(location, sensorType) != null);
    }

    public List<Double> getAverageDailyTemperatureInHouseArea(String geographicAreaName, Location location, LocalDate start, LocalDate end) {
        SensorType sensorType = new SensorType("temperature");
        List<Double> temperatureOnHouseArea = new ArrayList<>();
        GeographicArea geographicArea = getGeographicAreaByName(geographicAreaName);
        if (geographicArea != null) {
            while (start.isBefore(end.plusDays(1))) {
                temperatureOnHouseArea.add(geographicArea.getAverageDailyTemperatureInHouseArea(location, sensorType, start));
                start = start.plusDays(1);
            }
        }
        return temperatureOnHouseArea;
    }

    public double getCurrentTemperature(String geographicAreaName, Location location, SensorType sensorType) {
        GeographicArea geographicArea = getGeographicAreaByName(geographicAreaName);
        if (geographicArea != null && geographicArea.getSensorClosestForReadings(location, sensorType) != null)
            return geographicArea.getSensorClosestForReadings(location, sensorType).getCurrentTemperature();
        return Double.NaN;
    }

    public double getSumOfValueOnSensorInCertainDay(String geographicAreaName, Location location, SensorType sensorType, LocalDate date) {
        GeographicArea geographicArea = getGeographicAreaByName(geographicAreaName);
        if (geographicArea != null && geographicArea.getSensorClosestForReadings(location, sensorType) != null)
            return geographicArea.getSensorClosestForReadings(location, sensorType).getSumOfValueOnSensorInCertainDay(date);
        return Double.NaN;
    }

    public double getAverageRainfallInOneAG(String geographicAreaName, Location location, SensorType sensorType, LocalDate initialDate, LocalDate finalDate) {
        GeographicArea geographicArea = getGeographicAreaByName(geographicAreaName);
        if (geographicArea != null && geographicArea.getSensorClosestForReadings(location, sensorType) != null)
            return geographicArea.getAverageRainfallInOneAG(initialDate, finalDate, sensorType);
        return Double.NaN;
    }

    public ReadingDto getFirstHottestDayInGivenPeriodDto(String geographicAreaName, Location location, SensorType sensorType, LocalDate initDate, LocalDate finalDate) {
        GeographicArea geographicArea = getGeographicAreaByName(geographicAreaName);
        if (geographicArea != null && geographicArea.getSensorClosestForReadings(location, sensorType) != null)
            return MapperReading.toDto(geographicArea.getSensorClosestForReadings(location, sensorType).getFirstHottestDayInGivenPeriod(initDate, finalDate));
        return null;
    }

    public ReadingDto getLastColdestDayInGivenPeriodDto(String geographicAreaName, Location location, SensorType sensorType, LocalDate initDate, LocalDate finalDate) {
        GeographicArea geographicArea = getGeographicAreaByName(geographicAreaName);
        if (geographicArea != null && geographicArea.getSensorClosestForReadings(location, sensorType) != null)
            return MapperReading.toDto(geographicArea.getSensorClosestForReadings(location, sensorType).getLastColdestDayInGivenPeriod(initDate, finalDate));
        return null;
    }

    public List<LocalDate> getDayWithHighestTemperatureAmplitude(String geographicAreaName, Location location, SensorType sensorType, LocalDate initDate, LocalDate finalDate) {
        GeographicArea geographicArea = getGeographicAreaByName(geographicAreaName);
        if (geographicArea != null && geographicArea.getSensorClosestForReadings(location, sensorType) != null)
            return geographicArea.getSensorClosestForReadings(location, sensorType).getDayWithHighestTemperatureAmplitude(initDate, finalDate);
        return Collections.emptyList();
    }

    public double getHighestTemperatureAmplitude(String geographicAreaName, Location location, SensorType sensorType, LocalDate initDate, LocalDate finalDate) {
        GeographicArea geographicArea = getGeographicAreaByName(geographicAreaName);
        if (geographicArea != null && geographicArea.getSensorClosestForReadings(location, sensorType) != null)
            return geographicArea.getSensorClosestForReadings(location, sensorType).getHighestTemperatureAmplitude(initDate, finalDate);
        return Double.NaN;
    }

    /**
     * Sensor Methods
     */

    public boolean addSensorToList(AreaSensor newAreaSensor) {
        GeographicArea geographicArea = getGeographicAreaByName(newAreaSensor.getGeographicArea().getName());
        if (!geographicArea.getListOfAreaSensors().contains(newAreaSensor)) {
            geographicArea.getListOfAreaSensors().add(newAreaSensor);
            geographicAreaRepository.save(geographicArea);
            return true;
        } else
            return false;
    }

    public int createAndAddSensor(AreaSensorDto areaSensorDto, String geographicAreaName) {
        GeographicArea geographicArea = getGeographicAreaByName(geographicAreaName);
        int result = geographicArea.createAndAddSensor(areaSensorDto);
        geographicAreaRepository.save(geographicArea);
        return result;
    }


    public boolean removeSensor(String geoAreaID, String areaSensorID) {
        GeographicArea geographicArea = getGeographicAreaByName(geoAreaID);
        AreaSensor areaSensorToRemove = geographicArea.getSensorBySensorID(areaSensorID);
        if (geographicArea.getListOfAreaSensors().contains(areaSensorToRemove)) {
            geographicArea.getListOfAreaSensors().remove(areaSensorToRemove);
            geographicAreaRepository.save(geographicArea);
            return true;
        }
        return false;
    }

    public List<AreaSensor> getAllSensors() {
        List<AreaSensor> areaSensors = new ArrayList<>();
        for (GeographicArea geographicArea : listOfGeographicArea)
            areaSensors.addAll(geographicArea.getListOfAreaSensors());
        return areaSensors;
    }

    public AreaSensorDto getSensorByIdDTO(String geographicAreaName, String areaSensorID) {
        GeographicArea geographicArea = getGeographicAreaByName(geographicAreaName);
        AreaSensor areaSensor = geographicArea.getSensorBySensorID(areaSensorID);
        if (areaSensor != null)
            return MapperAreaSensor.toDto(areaSensor);
        return null;
    }

    public boolean deactivateSensor(String geographicAreaName, String areaSensorID) {
        GeographicArea geographicArea = getGeographicAreaByName(geographicAreaName);
        AreaSensor areaSensor = geographicArea.getSensorBySensorID(areaSensorID);
        if (geographicArea.getListOfAreaSensors().contains(areaSensor)) {
            LocalDate dateOfDeactivate = LocalDate.now();
            Status status = new Status(false, dateOfDeactivate);
            areaSensor.getListOfStatus().getStatusList().add(status);
            geographicAreaRepository.save(geographicArea);
            return true;
        }
        return false;
    }

    public List<AreaSensorDto> getListOfSensorsActiveDTO(String geographicAreaName) {
        GeographicArea geographicArea = getGeographicAreaByName(geographicAreaName);
        List<AreaSensor> list = geographicArea.getListOfSensorsActive();
        if (!list.equals(Collections.emptyList()))
            return MapperListOfAreaSensor.toDto(list).getListOfAreaSensorDto();
        return Collections.emptyList();
    }

    public List<AreaSensorDto> getListOfSensorsDtoFromGA(String geographicAreaName) {
        GeographicArea geographicArea = getGeographicAreaByName(geographicAreaName);
        List<AreaSensor> list = geographicArea.getListOfAreaSensors();
        if (!list.equals(Collections.emptyList()))
            return MapperListOfAreaSensor.toDto(list).getListOfAreaSensorDto();
        return Collections.emptyList();
    }


}
