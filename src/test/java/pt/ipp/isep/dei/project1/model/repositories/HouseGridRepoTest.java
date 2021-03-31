package pt.ipp.isep.dei.project1.model.repositories;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pt.ipp.isep.dei.project1.dto.housedto.HouseGridDto;
import pt.ipp.isep.dei.project1.mapper.MapperHouseGrid;
import pt.ipp.isep.dei.project1.mapper.MapperListOfHouseGrids;
import pt.ipp.isep.dei.project1.model.geographicarea.GeographicArea;
import pt.ipp.isep.dei.project1.model.geographicarea.GeographicAreaType;
import pt.ipp.isep.dei.project1.model.geographicarea.Location;
import pt.ipp.isep.dei.project1.model.geographicarea.OccupationArea;
import pt.ipp.isep.dei.project1.model.house.HouseGrid;
import pt.ipp.isep.dei.project1.model.repositories.HouseGridRepo;
import pt.ipp.isep.dei.project1.model.repositories.HouseGridRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class HouseGridRepoTest {


    @Mock
    private HouseGridRepository houseGridRepository;
    @Mock
    private HouseGridRepo houseGridRepo;


    @BeforeEach
    void initUseCase() {
        houseGridRepo = new HouseGridRepo(houseGridRepository);
    }


    @Test
    void getHouseGridDtoByCode(){
        //ARRANGE
        HouseGrid hg1 = new HouseGrid("xpto", 100);
        HouseGrid hg2 = new HouseGrid("xpto1", 100);
        List<HouseGrid> listOfHouseGrids = new ArrayList<>();
        listOfHouseGrids.add(hg1);
        listOfHouseGrids.add(hg2);
        houseGridRepo.setListOfHouseGrids(listOfHouseGrids);
        HouseGridDto result = houseGridRepo.getHouseGridByCodeDto("xpto");
        HouseGridDto expectedResult = MapperHouseGrid.toDto(hg1);
        assertEquals(expectedResult, result);
    }

    @Test
    void getHouseGridDtoByCodeNotFound(){
        //ARRANGE
        HouseGrid hg1 = new HouseGrid("xpto", 100);
        HouseGrid hg2 = new HouseGrid("xpto1", 100);
        List<HouseGrid> listOfHouseGrids = new ArrayList<>();
        listOfHouseGrids.add(hg1);
        listOfHouseGrids.add(hg2);
        houseGridRepo.setListOfHouseGrids(listOfHouseGrids);
        HouseGridDto result = houseGridRepo.getHouseGridByCodeDto("xpto3");
        assertEquals(null, result);
    }

    @Test
    void getHouseGridDtoByCodeEmpty(){
        //ARRANGE
        HouseGridDto result = houseGridRepo.getHouseGridByCodeDto("xpto1");
        assertEquals(null, result);
    }


    @Test
    void getListOfHouseGridsTest(){
        //ARRANGE
        HouseGrid hg1 = new HouseGrid("xpto", 100);
        HouseGrid hg2 = new HouseGrid("xpto1", 100);
        List<HouseGrid> listOfHouseGrids = new ArrayList<>();
        listOfHouseGrids.add(hg1);
        listOfHouseGrids.add(hg2);
        houseGridRepo.setListOfHouseGrids(listOfHouseGrids);
        List<HouseGrid> result = houseGridRepo.getListOfHouseGrids();
        assertEquals(listOfHouseGrids, result);
    }

    @Test
    void getListOfHouseGridsDtoTest(){
        //ARRANGE
        HouseGrid hg1 = new HouseGrid("xpto", 100);
        HouseGrid hg2 = new HouseGrid("xpto1", 100);
        List<HouseGrid> listOfHouseGrids = new ArrayList<>();
        listOfHouseGrids.add(hg1);
        listOfHouseGrids.add(hg2);
        houseGridRepo.setListOfHouseGrids(listOfHouseGrids);
        List<HouseGridDto> result = houseGridRepo.getHouseGridDtoList();
        List<HouseGridDto> expectedResult = MapperListOfHouseGrids.toDtoList(listOfHouseGrids);
        assertEquals(expectedResult, result);
    }

    @Test
    void getListOfHouseGridsDtoEmptyTest(){
        //ARRANGE
        List<HouseGridDto> result = houseGridRepo.getHouseGridDtoList();
        assertEquals(Collections.emptyList(), result);
    }


    @Test
    void testAddHouseGridTrue() throws Exception {
        //Arrange
        HouseGrid hg1 = new HouseGrid("xpto", 100);
        //Act
        boolean result = houseGridRepo.addHouseGrid(hg1);
        //Assert
        assertTrue(result);

    }

    @Test
    void testAddHouseGridFalse() throws Exception {
        //Arrange
        HouseGrid hg1 = new HouseGrid("xpto", 100);
        houseGridRepo.addHouseGrid(hg1);
        //Act
        boolean result = houseGridRepo.addHouseGrid(hg1);
        //Assert
        assertFalse(result);

    }


    @Test
    void getHouseGridByID() throws Exception {
        HouseGrid hg1 = new HouseGrid("1", 100);
        hg1.setCode("1");
        houseGridRepo.addHouseGrid(hg1);
        HouseGrid expectedResult = hg1;
        HouseGrid result = houseGridRepo.getHouseGridByCode("1");
        assertEquals(expectedResult, result);
    }

    @Test
    void getHouseGridByIDNull() throws Exception {
        GeographicArea geographicArea = new GeographicArea("Porto", "City", new OccupationArea
                (new Location(10, 20, 30), 0.5, 0.5),
                new GeographicAreaType("City"));
        HouseGrid hg1 = new HouseGrid("1", 100);
        hg1.setCode("1");
        houseGridRepo.addHouseGrid(hg1);
        HouseGrid expectedResult = null;
        HouseGrid result = houseGridRepo.getHouseGridByCode("22");
        assertEquals(expectedResult, result);
    }

    @Test
    void testNewHouseGridTrue() {
        HouseGrid hg1 = new HouseGrid("1", 100);
        boolean result = houseGridRepo.newHouseGrid(MapperHouseGrid.toDto(hg1));
        assertTrue(result);
    }

    @Test
    void testNewHouseGridFalse() {
        HouseGrid hg1 = new HouseGrid("1", 100);
        houseGridRepo.addHouseGrid(hg1);
        boolean result = houseGridRepo.newHouseGrid(MapperHouseGrid.toDto(hg1));
        assertFalse(result);
    }

    @Test
    void testNewHouseGridFalse2() {
        HouseGrid hg1 = new HouseGrid("1", 100);
        hg1.setCode("");
        boolean result = houseGridRepo.newHouseGrid(MapperHouseGrid.toDto(hg1));
        assertFalse(result);
    }

}




