package pt.ipp.isep.dei.project1.controllers.house;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pt.ipp.isep.dei.project1.model.geographicarea.GeographicArea;
import pt.ipp.isep.dei.project1.model.geographicarea.GeographicAreaType;
import pt.ipp.isep.dei.project1.model.geographicarea.Location;
import pt.ipp.isep.dei.project1.model.geographicarea.OccupationArea;
import pt.ipp.isep.dei.project1.model.house.HouseGrid;
import pt.ipp.isep.dei.project1.model.repositories.HouseGridRepository;
import pt.ipp.isep.dei.project1.model.repositories.HouseGridRepo;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CreateAHouseGridControllerTest {

    @Mock
    private HouseGridRepository houseGridRepository;
    @Mock
    private HouseGridRepo houseGridRepo;
    @Mock
    CreateAHouseGridController ctrll130;


    @BeforeEach
    void initUseCase() {
        houseGridRepo = new HouseGridRepo(houseGridRepository);
        ctrll130  = new CreateAHouseGridController(houseGridRepo);
    }

    @Test
    void createNewHouseGridTrue() throws Exception {
        //ARRANGE
        String code = "xpt01";
        double power = 100;
        GeographicArea ga1 = new GeographicArea("Porto","city",new OccupationArea(new Location(10,20,30),0.5,0.5),new GeographicAreaType("city"));
        //ACT
        boolean result = ctrll130.createNewHouseGrid(code,power);
        //ASSERT
        assertTrue(result);
    }

    @Test
    void createNewHouseGridFalse() throws Exception {
        //ARRANGE
        String code = "xpt01";
        double power = 100;
        GeographicArea ga1 = new GeographicArea("Porto","city",new OccupationArea(new Location(10,20,30),0.5,0.5),new GeographicAreaType("city"));
        List<HouseGrid> listHG = new ArrayList<>();
        HouseGrid houseGrid = new HouseGrid(code,power);
        listHG.add(houseGrid);
        houseGridRepo.setListOfHouseGrids(listHG);
        //ACT
        ctrll130.createNewHouseGrid(code,power);
        boolean result = ctrll130.createNewHouseGrid(code,power);
        //ASSERT
        assertFalse(result);
    }
}