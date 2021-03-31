package pt.ipp.isep.dei.project1.controllers.house;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import pt.ipp.isep.dei.project1.model.house.HouseGrid;
import pt.ipp.isep.dei.project1.model.repositories.HouseGridRepo;

@Controller
public class CreateAHouseGridController {

    private final HouseGridRepo houseGridRepo;

    @Autowired
    public CreateAHouseGridController(HouseGridRepo houseGridRepo) {
        this.houseGridRepo = houseGridRepo;
    }

    /**
     * Method To Create a New house Grid
     *
     * @param codeHG
     * @param powerHG
     * @return
     */

    public boolean createNewHouseGrid(String codeHG, double powerHG) {
        HouseGrid newHG = new HouseGrid(codeHG, powerHG);
        return houseGridRepo.addHouseGrid(newHG);
    }

}
