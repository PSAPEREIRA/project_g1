package pt.ipp.isep.dei.project1.controllers.device;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import pt.ipp.isep.dei.project1.dto.device.ListOfDevicesDto;
import pt.ipp.isep.dei.project1.dto.housedto.ListOfRoomsDto;
import pt.ipp.isep.dei.project1.dto.housedto.RoomDto;
import pt.ipp.isep.dei.project1.mapper.MapperListOfDevice;
import pt.ipp.isep.dei.project1.mapper.MapperListOfRooms;
import pt.ipp.isep.dei.project1.model.house.Room;
import pt.ipp.isep.dei.project1.model.repositories.RoomDomainService;

@Controller
public class GetAListOfAllDevicesInARoomToBePossibleToConfigureThemController {

    private final RoomDomainService roomDomainService;

    @Autowired
    public GetAListOfAllDevicesInARoomToBePossibleToConfigureThemController(RoomDomainService roomDomainService) {
        this.roomDomainService = roomDomainService;
    }

    public ListOfRoomsDto getListOfRooms() {
        return MapperListOfRooms.toDto(roomDomainService.getListOfRooms());
    }

    public ListOfDevicesDto getListOfDevicesInARoom(RoomDto roomDto) {
        Room room = roomDomainService.getRoomByName(roomDto.getName());
        return MapperListOfDevice.toDto(room.getListOfDevices().getDeviceList());
    }



}
