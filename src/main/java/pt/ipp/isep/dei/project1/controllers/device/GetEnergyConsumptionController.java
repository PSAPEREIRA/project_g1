package pt.ipp.isep.dei.project1.controllers.device;

import org.springframework.stereotype.Controller;
import pt.ipp.isep.dei.project1.dto.device.DeviceDto;
import pt.ipp.isep.dei.project1.dto.device.ListOfDevicesDto;
import pt.ipp.isep.dei.project1.dto.housedto.HouseGridDto;
import pt.ipp.isep.dei.project1.dto.housedto.ListOfHouseGridsDto;
import pt.ipp.isep.dei.project1.dto.housedto.ListOfRoomsDto;
import pt.ipp.isep.dei.project1.dto.housedto.RoomDto;
import pt.ipp.isep.dei.project1.mapper.MapperListOfDevice;
import pt.ipp.isep.dei.project1.mapper.MapperListOfHouseGrids;
import pt.ipp.isep.dei.project1.mapper.MapperListOfRooms;
import pt.ipp.isep.dei.project1.model.house.HouseGrid;
import pt.ipp.isep.dei.project1.model.house.Room;
import pt.ipp.isep.dei.project1.model.interfaces.Device;
import pt.ipp.isep.dei.project1.model.interfaces.Metered;
import pt.ipp.isep.dei.project1.model.repositories.HouseGridRepo;
import pt.ipp.isep.dei.project1.model.repositories.RoomDomainService;
import pt.ipp.isep.dei.project1.services.RoomHouseGridService;

import java.time.LocalDateTime;

@Controller
public class GetEnergyConsumptionController {

    private final RoomDomainService roomDomainService;
    private final HouseGridRepo houseGridRepo;
    private final RoomHouseGridService roomHouseGridService;

    public GetEnergyConsumptionController(RoomDomainService roomDomainService, HouseGridRepo houseGridRepo, RoomHouseGridService roomHouseGridService) {
        this.roomDomainService = roomDomainService;
        this.houseGridRepo = houseGridRepo;
        this.roomHouseGridService = roomHouseGridService;
    }

    /**
     * Controller for US720,US721,US722
     */

    public ListOfRoomsDto getListOfRooms() {
        return MapperListOfRooms.toDto(roomDomainService.getListOfRooms());
    }

    public ListOfDevicesDto getListOfDevicesFromRoom(RoomDto roomDto) {
        Room room = roomDomainService.getRoomByName(roomDto.getName());
        return MapperListOfDevice.toDto(room.getListOfDevices().getDeviceList());
    }

    public ListOfHouseGridsDto getListOfHouseGrid() {
        return MapperListOfHouseGrids.toDto(houseGridRepo.getListOfHouseGrids());
    }


    public Double getEnergyConsumptionOnOneDevice(RoomDto roomDto, DeviceDto deviceDto, LocalDateTime initialDate, LocalDateTime finalDate) throws Exception {
        Room room = roomDomainService.getRoomByName(roomDto.getName());
        Device device = room.getListOfDevices().getDeviceByName(deviceDto.getName());
        return ((Metered) device).getConsumption(initialDate, finalDate);
    }

    public Double getEnergyConsumptionOnOneRoom(RoomDto roomDto, LocalDateTime initialDate, LocalDateTime finalDate) throws Exception {
        Room room = roomDomainService.getRoomByName(roomDto.getName());
        return room.getConsumption(initialDate, finalDate);
    }

    public Double getEnergyConsumptionInOneHouseGrid(HouseGridDto houseGridDto, LocalDateTime initialDate, LocalDateTime finalDate) throws Exception {
        HouseGrid houseGrid = houseGridRepo.getHouseGridByCode(houseGridDto.getCode());
        return roomHouseGridService.getConsumption(initialDate, finalDate,houseGrid.getCode());
    }


}
