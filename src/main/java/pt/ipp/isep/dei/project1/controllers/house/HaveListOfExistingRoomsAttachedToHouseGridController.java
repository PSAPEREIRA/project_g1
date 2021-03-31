package pt.ipp.isep.dei.project1.controllers.house;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import pt.ipp.isep.dei.project1.dto.housedto.HouseGridDto;
import pt.ipp.isep.dei.project1.dto.housedto.ListOfHouseGridsDto;
import pt.ipp.isep.dei.project1.dto.housedto.ListOfRoomsDto;
import pt.ipp.isep.dei.project1.mapper.MapperListOfHouseGrids;
import pt.ipp.isep.dei.project1.mapper.MapperListOfRooms;
import pt.ipp.isep.dei.project1.model.house.HouseGrid;
import pt.ipp.isep.dei.project1.model.repositories.HouseGridRepo;
import pt.ipp.isep.dei.project1.services.RoomHouseGridService;

@Controller
public class HaveListOfExistingRoomsAttachedToHouseGridController {

    private final HouseGridRepo houseGridRepo;
    private final RoomHouseGridService roomHouseGridService;

    @Autowired
    public HaveListOfExistingRoomsAttachedToHouseGridController(HouseGridRepo houseGridRepo,RoomHouseGridService roomHouseGridService) {
        this.houseGridRepo = houseGridRepo;
        this.roomHouseGridService = roomHouseGridService;
    }

    public ListOfHouseGridsDto getListOfHouseGrids() {
        return MapperListOfHouseGrids.toDto(houseGridRepo.getListOfHouseGrids());
    }

    public ListOfRoomsDto getListOfRoomsAttachedToHouseGrid(HouseGridDto houseGridDto) {
        HouseGrid houseGrid = houseGridRepo.getHouseGridByCode(houseGridDto.getCode());
        return MapperListOfRooms.toDto(roomHouseGridService.getRoomsConnectedToHouseGrid(houseGrid.getRoomsList()));
    }
}

