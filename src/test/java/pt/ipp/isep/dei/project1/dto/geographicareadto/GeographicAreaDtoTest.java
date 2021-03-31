package pt.ipp.isep.dei.project1.dto.geographicareadto;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project1.dto.sensordto.ListOfAreaSensorsDto;
import pt.ipp.isep.dei.project1.dto.sensordto.AreaSensorDto;
import pt.ipp.isep.dei.project1.mapper.MapperGeographicArea;
import pt.ipp.isep.dei.project1.model.geographicarea.*;
import pt.ipp.isep.dei.project1.model.sensor.SensorType;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class GeographicAreaDtoTest {

    @Test
    void getListInsideOf() {
        GeographicArea geo1 = new GeographicArea("Porto", "city",
                new OccupationArea(new Location(10, 20, 30), 0.5, 0.5),
                new GeographicAreaType("city"));
        GeographicArea geo2 = new GeographicArea("Porto", "city",
                new OccupationArea(new Location(10, 20, 30), 0.5, 0.5),
                new GeographicAreaType("city"));
        List<GeographicArea> la = new ArrayList<>();
        la.add(geo2);
        geo1.setListInsideOf(la);
        GeographicAreaDto ladto = MapperGeographicArea.toDto(geo1);
        assertEquals(geo2.getName(),ladto.getListInsideOf().getListOfGADto().get(0).getName());
    }

    @Test
    void testGetListOfSensorsDto() {
        GeographicAreaDto geo1 = new GeographicAreaDto();
        ListOfAreaSensorsDto listOfAreaSensorsDto = new ListOfAreaSensorsDto();
        AreaSensorDto areaSensorDto = new AreaSensorDto();
        listOfAreaSensorsDto.getListOfAreaSensorDto().add(areaSensorDto);
        geo1.setListOfAreaSensorsDto(listOfAreaSensorsDto);
        ListOfAreaSensorsDto result= geo1.getListOfAreaSensorsDto();
        assertEquals(listOfAreaSensorsDto,result);
    }

    @Test
    public void equalsTrueTest() {
        //ARRANGE
        GeographicAreaDto geo1 = new GeographicAreaDto();
        geo1.setName("porto");
        Object obj = new GeographicAreaDto();
        ((GeographicAreaDto) obj).setName("porto");
        //ACT
        boolean result = geo1.equals(obj);
        //ASSERT
        assertTrue(result);
    }

    @Test
    public void equalsTrueTestMyself() {
        //ARRANGE
        GeographicAreaDto geo1 = new GeographicAreaDto();
        geo1.setName("Porto");
        Object obj = geo1;
        boolean result = geo1.equals(obj);
        //ASSERT
        assertTrue(result);
    }

    @Test
    public void equalsFalseTest() {
        //ARRANGE
        GeographicAreaDto geo1 = new GeographicAreaDto();
        geo1.setName("Porto");
        Object obj = new GeographicAreaDto();
        ((GeographicAreaDto) obj).setName("Braga");
        //ACT
        boolean result = geo1.equals(obj);
        //ASSERT
        assertFalse(result);
    }

    @Test
    public void equalsFalseTestOtherClass() {
        //ARRANGE
        GeographicAreaDto geo1 = new GeographicAreaDto();
        geo1.setName("porto");
        Object obj = new SensorType("temperature");
        //ACT
        boolean result = geo1.equals(obj);
        //ASSERT
        assertFalse(result);
    }

    @Test
    public void hashCodeTest() {
        //ARRANGE
        GeographicAreaDto geo1 = new GeographicAreaDto();
        geo1.setName("Porto");
        Object obj = new GeographicAreaDto();
        ((GeographicAreaDto) obj).setName("Porto");
        //ACT
        int result = geo1.getName().charAt(0);
        //ASSERT
        assertEquals(geo1.hashCode(), result);
    }





}