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
public class EditConfigurationOfDeviceController {

    @Autowired
    private final RoomDomainService roomDomainService;

    public EditConfigurationOfDeviceController(RoomDomainService roomDomainService){
        this.roomDomainService = roomDomainService;
    }

    public Device chosenDeviceToEdit(RoomDto roomDto, DeviceDto deviceDto) {
        Room room = roomDomainService.getRoomByName(roomDto.getName());
        return room.getListOfDevices().getDeviceByName(deviceDto.getName());
    }

    public void editAttributeValue(Device dInput, int attInputIndex, double value) {
        dInput.getDeviceSpecs().setAttributeValue(dInput.getDeviceSpecs().getAttributeNames().get(attInputIndex), value);
    }

    public void editProgramValueByIndex( int roomIndex, int deviceIndex, int programIndex, int fieldIndex, String newValue) {
        roomDomainService.getRoomToEditProgramField(roomIndex,deviceIndex,programIndex,fieldIndex,newValue);
    }

    public void deleteProgramByIndex(int roomIndex, int deviceIndex, int programIndex){
        roomDomainService.goToRoomListToDeleteProgram(roomIndex,deviceIndex,programIndex);
    }

    public ListOfRoomsDto getListOfRoomsDto(){
        return MapperListOfRooms.toDto(roomDomainService.getListOfRooms());
    }

    public ListOfDevicesDto getListOfDevicesOfSelectedRoom(RoomDto roomDto){
        Room room = roomDomainService.getRoomByName(roomDto.getName());
        ListOfDevices listOfDevices = room.getListOfDevices();
        return MapperListOfDevice.toDto(listOfDevices.getDeviceList());
    }


}
