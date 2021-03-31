package pt.ipp.isep.dei.project1.controllers.device;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import pt.ipp.isep.dei.project1.dto.device.DeviceDto;
import pt.ipp.isep.dei.project1.dto.device.ListOfDevicesDto;
import pt.ipp.isep.dei.project1.dto.housedto.ListOfRoomsDto;
import pt.ipp.isep.dei.project1.dto.housedto.RoomDto;
import pt.ipp.isep.dei.project1.mapper.MapperListOfDevice;
import pt.ipp.isep.dei.project1.mapper.MapperListOfRooms;
import pt.ipp.isep.dei.project1.model.device.ListOfDevices;
import pt.ipp.isep.dei.project1.model.house.Room;
import pt.ipp.isep.dei.project1.model.interfaces.Device;
import pt.ipp.isep.dei.project1.model.repositories.RoomDomainService;

@Controller
public class DeactivateRemoveDeviceFromRoomController {

    private final RoomDomainService roomDomainService;

    @Autowired
    public DeactivateRemoveDeviceFromRoomController(RoomDomainService roomDomainService) {
        this.roomDomainService = roomDomainService;
    }

    public ListOfRoomsDto getListOfRoomsDto() {
        return MapperListOfRooms.toDto(roomDomainService.getListOfRooms());
    }

    public ListOfDevicesDto getListOfDevicesDtoFromRoom(RoomDto roomInput) {
        Room room = roomDomainService.getRoomByName(roomInput.getName());
        ListOfDevices listOfDevices = room.getListOfDevices();
        return MapperListOfDevice.toDto(listOfDevices.getDeviceList());
    }


    public boolean removeDeviceFromRoom(RoomDto roomInput, DeviceDto deviceInput) {
        Room room = roomDomainService.getRoomByName(roomInput.getName());
        Device device = room.getListOfDevices().getDeviceByName(deviceInput.getName());
        return room.getListOfDevices().removeDevice(device);
    }


    public boolean deactivateDevice(RoomDto roomInput, DeviceDto deviceInput) {
        Room room = roomDomainService.getRoomByName(roomInput.getName());
        Device device = room.getListOfDevices().getDeviceByName(deviceInput.getName());
        if (!device.isActive())
            return false;
        return device.deactivateDevice();
    }

}



