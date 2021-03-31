package pt.ipp.isep.dei.project1.controllers.device;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import pt.ipp.isep.dei.project1.dto.device.ListOfDevicesDto;
import pt.ipp.isep.dei.project1.dto.housedto.ListOfHouseGridsDto;
import pt.ipp.isep.dei.project1.mapper.MapperListOfDevice;
import pt.ipp.isep.dei.project1.mapper.MapperListOfHouseGrids;
import pt.ipp.isep.dei.project1.model.repositories.HouseGridRepo;
import pt.ipp.isep.dei.project1.services.RoomHouseGridService;

@Controller
public class ListOfDevicesInGridGroupedByTypeIncludeLocationController {

    private final HouseGridRepo houseGridRepo;
    private final RoomHouseGridService roomHouseGridService;

    @Autowired
    public ListOfDevicesInGridGroupedByTypeIncludeLocationController(HouseGridRepo houseGridRepo,RoomHouseGridService roomHouseGridService) {
        this.houseGridRepo = houseGridRepo;
        this.roomHouseGridService = roomHouseGridService;
    }

    public ListOfHouseGridsDto getListOfHouseGrid() {
        return MapperListOfHouseGrids.toDto(houseGridRepo.getListOfHouseGrids());
    }

    public ListOfDevicesDto getListOfDevicesDto(String roomName) {
        return MapperListOfDevice.toDto(roomHouseGridService.getListOfDevicesOnRoomAttachedToHouseGrid(roomName).getDeviceList());
    }
}
