package pt.ipp.isep.dei.project1.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.ipp.isep.dei.project1.dto.housedto.RoomDto;
import pt.ipp.isep.dei.project1.dto.sensordto.RoomSensorDto;
import pt.ipp.isep.dei.project1.dto.sensordto.SensorTypeDto;
import pt.ipp.isep.dei.project1.model.repositories.RoomDomainService;
import pt.ipp.isep.dei.project1.model.repositories.SensorTypeDomainService;

@Service
public class RoomSensorTypeService {

    private final RoomDomainService roomDomainService;
    private final SensorTypeDomainService sensorTypeDomainService;

    /** Create new Sensor Methods*/

    @Autowired
    public RoomSensorTypeService(RoomDomainService roomDomainService, SensorTypeDomainService sensorTypeDomainService) {
        this.roomDomainService = roomDomainService;
        this.sensorTypeDomainService = sensorTypeDomainService;
    }

    public SensorTypeDto getSensorTypeByNameDTO(String sensorDesignation) {
        return sensorTypeDomainService.getSensorTypeByNameDTO(sensorDesignation);
    }

    public int createAndAddSensor(RoomSensorDto roomSensorDto, String roomName) {
       return roomDomainService.createAndAddSensor(roomSensorDto,roomName);
    }

    public RoomDto getRoomByNameDto(String roomName){
        return roomDomainService.getRoomByNameDto(roomName);
    }






}
