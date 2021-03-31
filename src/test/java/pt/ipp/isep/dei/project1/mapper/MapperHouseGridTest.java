package pt.ipp.isep.dei.project1.mapper;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project1.dto.housedto.HouseGridDto;
import pt.ipp.isep.dei.project1.model.house.HouseGrid;

import static junit.framework.TestCase.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

class MapperHouseGridTest {


    @Test
    void testConstructor() {
        try {
            MapperHouseGrid mapperHouseGrid = new MapperHouseGrid();
        }
        catch (IllegalStateException e){
            String message = "Utility class";
            assertEquals(message, e.getMessage());
        }
    }

    @Test
    void testToDto() {
        //Arrange
        HouseGrid houseGrid = new HouseGrid("hg",100);
        //Act
        HouseGridDto result = MapperHouseGrid.toDto(houseGrid);
        //Assert
        assertEquals(houseGrid.getCode(),result.getCode());

    }

}