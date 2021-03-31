package pt.ipp.isep.dei.project1.controllers.device;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import pt.ipp.isep.dei.project1.dto.device.DeviceDto;
import pt.ipp.isep.dei.project1.dto.device.ListOfDevicesDto;
import pt.ipp.isep.dei.project1.dto.housedto.HouseGridDto;
import pt.ipp.isep.dei.project1.dto.housedto.ListOfHouseGridsDto;
import pt.ipp.isep.dei.project1.mapper.MapperDevice;
import pt.ipp.isep.dei.project1.mapper.MapperListOfDevice;
import pt.ipp.isep.dei.project1.mapper.MapperListOfHouseGrids;
import pt.ipp.isep.dei.project1.model.device.ListOfDevices;
import pt.ipp.isep.dei.project1.model.house.HouseGrid;
import pt.ipp.isep.dei.project1.model.house.Room;
import pt.ipp.isep.dei.project1.model.interfaces.Device;
import pt.ipp.isep.dei.project1.model.repositories.HouseGridRepo;
import pt.ipp.isep.dei.project1.services.RoomHouseGridService;


import java.util.List;

@Controller
public class GetNominalPowerOfRoomsOrDevicesController {

    private final HouseGridRepo houseGridRepo;
    private final RoomHouseGridService roomHouseGridService;

    @Autowired
    public GetNominalPowerOfRoomsOrDevicesController(HouseGridRepo houseGridRepo,RoomHouseGridService roomHouseGridService) {
        this.houseGridRepo = houseGridRepo;
        this.roomHouseGridService = roomHouseGridService;
    }

    public ListOfHouseGridsDto getListOfHouseGrids() {
        return MapperListOfHouseGrids.toDto(houseGridRepo.getListOfHouseGrids());
    }

    public String getRoomFromHouseGrid(HouseGridDto houseGrid, int i) {
        return houseGrid.getRoomList().get(i);
    }

    public DeviceDto getDeviceFromRoom(HouseGridDto houseGrid, int i, int j) {
        Device device = roomHouseGridService.getDeviceFromRoom(houseGrid.getCode(),i,j);
        return MapperDevice.toDto(device);
    }

    public double getNominalPowerOfDevice(HouseGridDto houseGridDto, String roomDto, DeviceDto deviceDto) {
        HouseGrid houseGrid = houseGridRepo.getHouseGridByCode(houseGridDto.getCode());
        Room room = roomHouseGridService.getRoomByName(roomDto,houseGrid.getCode());
        Device device = room.getListOfDevices().getDeviceByName(deviceDto.getName());
        return device.getDeviceSpecs().getAttributeValue("nominal power");
    }

    public double getNominalPowerOfRoom(HouseGridDto houseGridDto, String roomName) {
        HouseGrid houseGrid = houseGridRepo.getHouseGridByCode(houseGridDto.getCode());
        Room room = roomHouseGridService.getRoomByName(roomName,houseGrid.getCode());
        return room.getNominalPower();
    }

    public double totalNominalPower(List<Double> finalResult) {
        double result = 0;
        for (double value : finalResult)
            result += value;
        return result;
    }

    public ListOfDevicesDto getListOfDevicesDto(String room) {
        ListOfDevices listOfDevices = roomHouseGridService.getListOfDevicesOnRoomAttachedToHouseGrid(room);
        return MapperListOfDevice.toDto(listOfDevices.getDeviceList());
    }
}