package pt.ipp.isep.dei.project1.controllersweb.housegrid;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import pt.ipp.isep.dei.project1.dto.housedto.HouseGridDto;
import pt.ipp.isep.dei.project1.mapper.MapperHouseGrid;
import pt.ipp.isep.dei.project1.model.house.HouseGrid;
import pt.ipp.isep.dei.project1.model.repositories.HouseGridRepo;
import pt.ipp.isep.dei.project1.model.repositories.HouseGridRepository;
import static org.junit.Assert.assertEquals;


@ExtendWith(MockitoExtension.class)
class CreateAHouseGridRestControllerTest {


    @Mock
    HouseGridRepository houseGridRepository;
    @Mock
    HouseGridRepo houseGridRepo;

    CreateAHouseGridRestController testCTRL;

    @BeforeEach
    void initUseCase() {
        houseGridRepo = new HouseGridRepo(houseGridRepository);
        testCTRL = new CreateAHouseGridRestController(houseGridRepo);
    }

    @Test
    public void testUserController(){
        HouseGrid houseGrid = new HouseGrid("h1");
        houseGrid.setCode("");
        HouseGridDto houseGridDto = MapperHouseGrid.toDto(houseGrid);
        ResponseEntity responseEntity = testCTRL.addNewHouseGrid(houseGridDto);
        assertEquals(HttpStatus.CONFLICT, responseEntity.getStatusCode());

    }

    @Test
    public void testAddNewHouseGrid(){
        HouseGrid houseGrid = new HouseGrid("xpto");
        HouseGridDto houseGridDto = MapperHouseGrid.toDto(houseGrid);
        ResponseEntity responseEntity = testCTRL.addNewHouseGrid(houseGridDto);
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());

    }

    @Test
    public void testAddNewHouseGridConflict(){
        HouseGrid houseGrid = new HouseGrid("xpto");
        HouseGridDto houseGridDto = MapperHouseGrid.toDto(houseGrid);
        HouseGrid houseGrid1 = new HouseGrid("xpto");
        HouseGridDto houseGrid1Dto = MapperHouseGrid.toDto(houseGrid1);
        ResponseEntity addedHouseGrid = testCTRL.addNewHouseGrid(houseGridDto);
        ResponseEntity responseEntity = testCTRL.addNewHouseGrid(houseGrid1Dto);
        assertEquals(HttpStatus.CONFLICT, responseEntity.getStatusCode());

    }





}