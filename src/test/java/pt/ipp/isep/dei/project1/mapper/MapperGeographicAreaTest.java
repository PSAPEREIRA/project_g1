package pt.ipp.isep.dei.project1.mapper;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project1.dto.geographicareadto.GeographicAreaDto;
import pt.ipp.isep.dei.project1.model.geographicarea.GeographicArea;
import pt.ipp.isep.dei.project1.model.geographicarea.GeographicAreaType;
import pt.ipp.isep.dei.project1.model.geographicarea.Location;
import pt.ipp.isep.dei.project1.model.geographicarea.OccupationArea;

import static junit.framework.TestCase.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

class MapperGeographicAreaTest {


    @Test
    void testConstructor() {
        try {
            MapperGeographicArea mapperGeographicArea = new MapperGeographicArea();
        }
        catch (IllegalStateException e){
            String message = "Utility class";
            assertEquals(message, e.getMessage());
        }
    }

    @Test
    void testToDto() {
        //Arrange
        GeographicArea geographicArea = new GeographicArea("geo","po",new OccupationArea(new Location(10,10,10),10,10),new GeographicAreaType("city"));
        //Act
        GeographicAreaDto result = MapperGeographicArea.toDto(geographicArea);
        //Assert
        assertEquals(geographicArea.getName(),result.getName());

    }

}