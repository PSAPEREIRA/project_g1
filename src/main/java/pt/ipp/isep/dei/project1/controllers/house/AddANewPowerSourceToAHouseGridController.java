package pt.ipp.isep.dei.project1.controllers.house;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import pt.ipp.isep.dei.project1.dto.housedto.HouseGridDto;
import pt.ipp.isep.dei.project1.dto.housedto.ListOfHouseGridsDto;
import pt.ipp.isep.dei.project1.mapper.MapperListOfHouseGrids;
import pt.ipp.isep.dei.project1.model.house.HouseGrid;
import pt.ipp.isep.dei.project1.model.house.PowerSource;
import pt.ipp.isep.dei.project1.model.repositories.HouseGridRepo;

@Controller
public class AddANewPowerSourceToAHouseGridController {

    private final HouseGridRepo houseGridRepo;

    @Autowired
    public AddANewPowerSourceToAHouseGridController(HouseGridRepo houseGridRepo) {
        this.houseGridRepo = houseGridRepo;
    }


    public ListOfHouseGridsDto getListOfHouseGrids() {
        return MapperListOfHouseGrids.toDto(houseGridRepo.getListOfHouseGrids());
    }


    public boolean addPowerSourceToHouseGrid(HouseGridDto houseGridDto, String newNamePowerSource, double maxPower) {
        HouseGrid houseGrid = houseGridRepo.getHouseGridByCode(houseGridDto.getCode());
        PowerSource newPowerSource = new PowerSource(newNamePowerSource, maxPower);
        if(houseGrid.addPowerSource(newPowerSource)) {
            houseGridRepo.getHouseGridRepository().save(houseGrid);
            return true;
        }
        return false;
    }

}

