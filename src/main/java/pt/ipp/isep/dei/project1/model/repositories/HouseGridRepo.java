package pt.ipp.isep.dei.project1.model.repositories;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.ipp.isep.dei.project1.dto.housedto.HouseGridDto;
import pt.ipp.isep.dei.project1.mapper.MapperHouseGrid;
import pt.ipp.isep.dei.project1.mapper.MapperListOfHouseGrids;
import pt.ipp.isep.dei.project1.model.house.HouseGrid;
import java.util.Collections;
import java.util.List;


@Service
public class HouseGridRepo {

    @Getter @Setter
    private List<HouseGrid> listOfHouseGrids;
    @Getter
    private final HouseGridRepository houseGridRepository;

    @Autowired
    public HouseGridRepo(HouseGridRepository houseGridRepository) {
        this.houseGridRepository = houseGridRepository;
        this.listOfHouseGrids = houseGridRepository.findAll();
    }

    public boolean addHouseGrid(HouseGrid houseGrid) {
        if (!listOfHouseGrids.contains(houseGrid)) {
            listOfHouseGrids.add(houseGrid);
            houseGridRepository.save(houseGrid);
            return true;
        } else
            return false;
    }

    public HouseGrid getHouseGridByCode (String code) {
        for (HouseGrid houseGrid : listOfHouseGrids)
            if (houseGrid.getCode().equals(code))
                return houseGrid;
        return null;
    }


    public List<HouseGridDto> getHouseGridDtoList() {
        if (!listOfHouseGrids.isEmpty())
            return MapperListOfHouseGrids.toDtoList(listOfHouseGrids);
        return Collections.emptyList();
    }

    public HouseGridDto getHouseGridByCodeDto(String code) {
        for (HouseGrid hg : listOfHouseGrids)
            if (hg.getCode().equals(code))
                return MapperHouseGrid.toDto(hg);
        return null;
    }

    public boolean newHouseGrid(HouseGridDto houseGridDto) {
        HouseGrid houseGrid;
        try {
            houseGrid = new HouseGrid(houseGridDto.getCode());
        } catch (Exception e) {
            return false;
        }
        return addHouseGrid(houseGrid);
    }

}
