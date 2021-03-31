package pt.ipp.isep.dei.project1.controllers.house;

import org.springframework.stereotype.Controller;
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
import java.util.List;

@Controller
public class GetDataSeriesToDesignGraphController {

    private final RoomDomainService roomDomainService;
    private final HouseGridRepo houseGridRepo;
    private final RoomHouseGridService roomHouseGridService;

    public GetDataSeriesToDesignGraphController(RoomDomainService roomDomainService, HouseGridRepo houseGridRepo, RoomHouseGridService roomHouseGridService) {
        this.roomDomainService = roomDomainService;
        this.houseGridRepo = houseGridRepo;
        this.roomHouseGridService = roomHouseGridService;
    }

    /**
     * US730 As a Power User [or Administrator],
     * I want to have the data series necessary to design an energy consumption chart of the metered energy consumption
     * of a device/room/grid in a given time interval.
     */

    public ListOfDevicesDto getListOfAllDevicesInsideHouse(){
        return MapperListOfDevice.toDto(roomDomainService.getListOfAllDevicesInsideHouse().getDeviceList());
    }

    public ListOfRoomsDto getListOfRooms (){
        return MapperListOfRooms.toDto(roomDomainService.getListOfRooms());
    }

    public ListOfHouseGridsDto getListOfHouseGrid (){
        return MapperListOfHouseGrids.toDto(houseGridRepo.getListOfHouseGrids());
    }

    public List<String[]> getReadingsOfDeviceOnPeriodAsString(int deviceIndex,LocalDateTime sDate, LocalDateTime fDate){
        Device device = roomDomainService.getListOfAllDevicesInsideHouse().getDeviceList().get(deviceIndex);
        return ((Metered)device).getDataSeries(sDate,fDate).getReadingsToShowAsStringList();
    }

    public List<String[]> getReadingsOfRoomOnPeriodAsString(RoomDto roomDto, LocalDateTime sDate, LocalDateTime fDate){
        Room room = roomDomainService.getRoomByName(roomDto.getName());
        return room.getDataSeries(sDate,fDate).getReadingsToShowAsStringList();
    }

    public List<String[]> getReadingsOfHouseGridOnPeriodAsString(HouseGridDto houseGridDto, LocalDateTime sDate, LocalDateTime fDate){
        HouseGrid houseGrid = houseGridRepo.getHouseGridByCode(houseGridDto.getCode());
        return roomHouseGridService.getDataSeries(sDate,fDate,houseGrid.getCode()).getReadingsToShowAsStringList();
    }

}
