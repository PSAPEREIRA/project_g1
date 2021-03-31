package pt.ipp.isep.dei.project1.controllers.device;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import pt.ipp.isep.dei.project1.dto.housedto.HouseGridDto;
import pt.ipp.isep.dei.project1.dto.housedto.ListOfHouseGridsDto;
import pt.ipp.isep.dei.project1.mapper.MapperListOfHouseGrids;
import pt.ipp.isep.dei.project1.model.house.HouseGrid;
import pt.ipp.isep.dei.project1.model.repositories.HouseGridRepo;
import pt.ipp.isep.dei.project1.services.RoomHouseGridService;

@Controller
public class TotalNominalPowerConnectedToHouseGridController {

    private final HouseGridRepo houseGridRepo;
    private final RoomHouseGridService roomHouseGridService;

    @Autowired
    public TotalNominalPowerConnectedToHouseGridController(HouseGridRepo houseGridRepo,RoomHouseGridService roomHouseGridService) {
        this.houseGridRepo = houseGridRepo;
        this.roomHouseGridService = roomHouseGridService;
    }

    public ListOfHouseGridsDto getListOfHouseGrids() {
        return MapperListOfHouseGrids.toDto(houseGridRepo.getListOfHouseGrids());
    }

    public double getTotalNominalPowerOfHouseGrid(HouseGridDto houseGridDto) {
        HouseGrid houseGrid1 = houseGridRepo.getHouseGridByCode(houseGridDto.getCode());
        return roomHouseGridService.getNominalPower(houseGrid1.getCode());
    }


}