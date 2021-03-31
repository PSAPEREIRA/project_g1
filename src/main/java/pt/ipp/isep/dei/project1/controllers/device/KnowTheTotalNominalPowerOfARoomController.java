package pt.ipp.isep.dei.project1.controllers.device;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import pt.ipp.isep.dei.project1.dto.device.ListOfDevicesDto;
import pt.ipp.isep.dei.project1.dto.housedto.ListOfRoomsDto;
import pt.ipp.isep.dei.project1.dto.housedto.RoomDto;
import pt.ipp.isep.dei.project1.mapper.MapperListOfDevice;
import pt.ipp.isep.dei.project1.mapper.MapperListOfRooms;
import pt.ipp.isep.dei.project1.model.device.ListOfDevices;
import pt.ipp.isep.dei.project1.model.house.Room;
import pt.ipp.isep.dei.project1.model.repositories.RoomDomainService;

@Controller
public class KnowTheTotalNominalPowerOfARoomController {

    private final RoomDomainService roomDomainService;

    @Autowired
    public KnowTheTotalNominalPowerOfARoomController(RoomDomainService roomDomainService) {
        this.roomDomainService = roomDomainService;
    }

    public ListOfRoomsDto getListOfRooms() {
        return MapperListOfRooms.toDto(roomDomainService.getListOfRooms());
    }

    public ListOfDevicesDto getListOfDevicesDtoFromRoom(RoomDto roomInput) {
        Room room = roomDomainService.getRoomByName(roomInput.getName());
        ListOfDevices listOfDevices = room.getListOfDevices();
        return MapperListOfDevice.toDto(listOfDevices.getDeviceList());
    }


    public double getTotalNominalPowerOfRoom(RoomDto roomInput) {
        Room room = roomDomainService.getRoomByName(roomInput.getName());
        return room.getNominalPower();
    }

}
