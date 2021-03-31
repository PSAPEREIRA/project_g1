package pt.ipp.isep.dei.project1.model.geographicarea;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pt.ipp.isep.dei.project1.model.repositories.GeographicAreaTypeRepository;
import pt.ipp.isep.dei.project1.model.sensor.SensorType;
import pt.ipp.isep.dei.project1.model.repositories.GeographicAreaTypeRepo;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class GeographicAreaTypeTest {

    @Mock
    private GeographicAreaTypeRepository geographicAreaTypeRepository;
    @Mock
    private GeographicAreaTypeRepo geographicAreaTypeRepo;

    @BeforeEach
    void initUseCase() {
        geographicAreaTypeRepo = new GeographicAreaTypeRepo(geographicAreaTypeRepository);
    }

    @Test
    public void testGetName() {
        //ARRANGE
        GeographicAreaType s1 = new GeographicAreaType();
        s1.setType("GeographicAreaType1");
        String expectedResult = "GeographicAreaType1";
        //ACT
        String result = s1.getType();
        //ASSERT
        assertEquals(expectedResult, result);
    }

    @Test
    public void equalsTrueTest() {
        //ARRANGE
        GeographicAreaType s1 = new GeographicAreaType("GeographicAreaType1");
        Object obj = new GeographicAreaType("GeographicAreaType1");
        //ACT
        boolean result = s1.equals(obj);
        //ASSERT
        assertTrue(result);
    }

    @Test
    public void equalsTrueTestMyself() {
        //ARRANGE
        GeographicAreaType s1 = new GeographicAreaType("GeographicAreaType1");
        Object obj = s1;
        boolean result = s1.equals(obj);
        //ASSERT
        assertTrue(result);
    }

    @Test
    public void equalsFalseTest() {
        //ARRANGE
        GeographicAreaType s1 = new GeographicAreaType("GeographicAreaType1");
        Object obj = new GeographicAreaType("GeographicAreaType2");
        //ACT
        boolean result = s1.equals(obj);
        //ASSERT
        assertFalse(result);
    }

    @Test
    public void equalsFalseTestOtherClass() {
        //ARRANGE
        GeographicAreaType s1 = new GeographicAreaType("GeographicAreaType1");
        Object obj = new SensorType("temperature");
        //ACT
        boolean result = s1.equals(obj);
        //ASSERT
        assertFalse(result);
    }

    @Test
    public void testHashCode() {
        //ARRANGE
        GeographicAreaType s1 = new GeographicAreaType("GeographicAreaType1");
        //ACT
        int result = s1.getType().charAt(0);
        //ASSERT
        assertEquals(s1.hashCode(), result);
    }



    @Test
    public void createGeographicAreaTypeValidNameEmpty() {
        try {
            GeographicAreaType GAT1 = new GeographicAreaType("");
            fail("Fail. An exception must be thrown");
        } catch (RuntimeException e) {
            assertEquals("Insert a valid name of Geographic Area Type", e.getMessage());
        }
    }

    @Test
    public void createGeographicAreaTypeValidNameNull() {
        try {
            GeographicAreaType GAT1 = new GeographicAreaType(null);
            fail("Fail. An exception must be thrown");
        } catch (RuntimeException e) {
            assertEquals("Insert a valid name of Geographic Area Type", e.getMessage());
        }
    }


    @Test
    public void testToString() {
        //ARRANGE
        GeographicAreaType s1 = new GeographicAreaType("GeographicAreaType1");
        String expectedResult = "GeographicAreaType1" + "\n";
        //ACT
        String result = s1.toString();
        //ASSERT
        assertEquals(expectedResult, result);
    }

    @Test
    void getGeographicAreaTypeByID() {
        GeographicAreaType s1 = new GeographicAreaType("GeographicAreaType1");
        s1.setType("GeographicAreaType1");
        assertEquals("GeographicAreaType1", s1.getType());
    }

    @Test
    void testGetType() {
        //Arrange
        GeographicAreaType geo1 = new GeographicAreaType("rua");
        GeographicAreaType geo2 = new GeographicAreaType("avenida");
        List<GeographicAreaType> expectedResult = new ArrayList<>();
        expectedResult.add(geo1);
        expectedResult.add(geo2);
        //Act
        geographicAreaTypeRepo.add(geo1);
        geographicAreaTypeRepo.add(geo2);
        List<GeographicAreaType> result = geographicAreaTypeRepo.getListOfGeographicAreaType();
        //Assert
        assertEquals(expectedResult, result);
    }


    @Test
    void testAddType() {
        //Arrange
        GeographicAreaType geo1 = new GeographicAreaType("rua");
        GeographicAreaType geo2 = new GeographicAreaType("avenida");
        List<GeographicAreaType> expectedResult = new ArrayList<>();
        expectedResult.add(geo1);
        expectedResult.add(geo2);
        //Act
        geographicAreaTypeRepo.add(geo1);
        boolean result = geographicAreaTypeRepo.add(geo2);
        //Assert
        assertTrue(result);
    }

    @Test
    void testAddTypeRepeated() {
        //Arrange
        GeographicAreaType geo1 = new GeographicAreaType("rua");
        GeographicAreaType geo2 = new GeographicAreaType("rua");
        List<GeographicAreaType> expectedResult = new ArrayList<>();
        expectedResult.add(geo1);
        expectedResult.add(geo2);
        //Act
        geographicAreaTypeRepo.add(geo1);
        boolean result = geographicAreaTypeRepo.add(geo2);
        //Assert
        assertFalse(result);
    }

    @Test
    void testGetTypeByName() {
        //Arrange
        GeographicAreaType geo1 = new GeographicAreaType("rua");
        GeographicAreaType expectedResult = geo1;
        geo1.setType("rua");
        //Act
        geographicAreaTypeRepo.add(geo1);
        GeographicAreaType result = geographicAreaTypeRepo.getTypeByName("rua");
        //Assert
        assertEquals(expectedResult,result);
    }


    @Test
    void testGetTypeByIdNull() {
        //Arrange
        GeographicAreaType geo1 = new GeographicAreaType("rua");
        GeographicAreaType expectedResult = null;
        geo1.setType("rua");
        //Act
        geographicAreaTypeRepo.add(geo1);
        GeographicAreaType result = geographicAreaTypeRepo.getTypeByName("cidade");
        //Assert
        assertEquals(expectedResult,result);
    }


}

