package pt.ipp.isep.dei.project1.mapper;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project1.dto.geographicareadto.GeographicAreaTypeDto;
import pt.ipp.isep.dei.project1.model.geographicarea.GeographicAreaType;

import static junit.framework.TestCase.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

class MapperGeographicAreaTypeTest {

    @Test
    void testConstructor() {
        try {
            MapperGeographicAreaType mapperGeographicAreaType = new MapperGeographicAreaType();
        }
        catch (IllegalStateException e){
            String message = "Utility class";
            assertEquals(message, e.getMessage());
        }
    }

    @Test
    void testToDto() {
        //Arrange
        GeographicAreaType geographicAreaType = new GeographicAreaType("geo");
        //Act
        GeographicAreaTypeDto result = MapperGeographicAreaType.toDto(geographicAreaType);
        //Assert
        assertEquals(geographicAreaType.getType(),result.getName());

    }

}