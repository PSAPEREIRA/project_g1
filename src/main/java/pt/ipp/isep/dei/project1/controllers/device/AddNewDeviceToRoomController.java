package pt.ipp.isep.dei.project1.controllers.device;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import pt.ipp.isep.dei.project1.dto.housedto.ListOfRoomsDto;
import pt.ipp.isep.dei.project1.dto.housedto.RoomDto;
import pt.ipp.isep.dei.project1.mapper.MapperListOfRooms;
import pt.ipp.isep.dei.project1.model.house.Room;
import pt.ipp.isep.dei.project1.model.interfaces.Device;
import pt.ipp.isep.dei.project1.model.interfaces.DeviceType;
import pt.ipp.isep.dei.project1.model.interfaces.Programmable;
import pt.ipp.isep.dei.project1.model.repositories.RoomDomainService;

import java.util.List;

@Controller
public class AddNewDeviceToRoomController {

//room
    private final RoomDomainService roomDomainService;

    @Autowired
    public AddNewDeviceToRoomController(RoomDomainService roomDomainService) {
        this.roomDomainService = roomDomainService;
    }

    public ListOfRoomsDto getListOfRooms() {
        return MapperListOfRooms.toDto(roomDomainService.getListOfRooms());
    }

    public List<DeviceType> getListOfDevices() throws Exception {
        return roomDomainService.getListOfDeviceTypes();
    }

    public Device createNewDevice(DeviceType newDevice, String name) {
        return newDevice.createNewDevice(name);
    }

    public boolean addNewDeviceToRoom(RoomDto roomDto, Device newDevice) {
        Room room = roomDomainService.getRoomByName(roomDto.getName());
        return room.getListOfDevices().addDeviceToList(newDevice);
    }

    public void createNewProgram(Device newDevice, String programName, double programEnergy) {
        ((Programmable) newDevice).createProgramAndAddTo(programName, programEnergy);
    }

}
