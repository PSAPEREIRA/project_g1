package pt.ipp.isep.dei.project1.controllers.sensor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import pt.ipp.isep.dei.project1.dto.housedto.ListOfRoomsDto;
import pt.ipp.isep.dei.project1.dto.housedto.RoomDto;
import pt.ipp.isep.dei.project1.dto.sensordto.ListOfSensorTypesDto;
import pt.ipp.isep.dei.project1.dto.sensordto.SensorTypeDto;
import pt.ipp.isep.dei.project1.mapper.MapperListOfRooms;
import pt.ipp.isep.dei.project1.mapper.MapperListOfSensorType;
import pt.ipp.isep.dei.project1.model.house.Room;
import pt.ipp.isep.dei.project1.model.sensor.RoomSensor;
import pt.ipp.isep.dei.project1.model.sensor.SensorType;
import pt.ipp.isep.dei.project1.model.repositories.RoomDomainService;
import pt.ipp.isep.dei.project1.model.repositories.SensorTypeDomainService;

import java.time.LocalDate;

@Controller
public class CreateNewSensorAndAttachToRoomController {

    private final SensorTypeDomainService sensorTypeDomainService;
    private final RoomDomainService roomDomainService;

    @Autowired
    public CreateNewSensorAndAttachToRoomController(SensorTypeDomainService sensorTypeDomainService, RoomDomainService roomDomainService) {
        this.sensorTypeDomainService = sensorTypeDomainService;
        this.roomDomainService = roomDomainService;
    }

    public ListOfRoomsDto getListOfRooms() {
        return MapperListOfRooms.toDto(roomDomainService.getListOfRooms());
    }

    public ListOfSensorTypesDto getListOfSensorsType() {
        return MapperListOfSensorType.toDto(sensorTypeDomainService.getListOfSensorsType());
    }

    public boolean createNewSensor(String id, RoomDto roomDto, String nameOfSensor, SensorTypeDto sensorType, LocalDate localDate, String unit) {
        Room room = roomDomainService.getRoomByName(roomDto.getName());
        SensorType sensorType1 = sensorTypeDomainService.getSensorTypeByName(sensorType.getDesignation());
        RoomSensor sensor = room.newSensor(id,nameOfSensor, sensorType1, localDate, unit);
        sensor.setRoom(room);
        return roomDomainService.addSensorToList(sensor);
    }
}
