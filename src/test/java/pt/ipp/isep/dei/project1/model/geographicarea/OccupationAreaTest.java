package pt.ipp.isep.dei.project1.model.geographicarea;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project1.model.sensor.Reading;

import static org.junit.jupiter.api.Assertions.*;

class OccupationAreaTest {

    @Test
    public void createOccupationAreaValidLengthNaN() {
        try {
            Location location = new Location(80, 90, 0);
            OccupationArea OA1 = new OccupationArea(location, 40, Double.NaN);
            fail("Fail. An exception must be thrown");
        } catch (RuntimeException e) {
            assertEquals("Insert a valid Length", e.getMessage());
        }
    }

    @Test
    public void createOccupationAreaValidLength() {
        try {
            Location location = new Location(80, 90, 0);
            OccupationArea OA1 = new OccupationArea(location, 40, 0);
            fail("Fail. An exception must be thrown");
        } catch (RuntimeException e) {
            assertEquals("Insert a valid Length", e.getMessage());
        }
    }

    @Test
    public void createOccupationAreaValidWidthNaN() {
        try {
            Location location = new Location(80, 90, 0);
            OccupationArea OA1 = new OccupationArea(location, Double.NaN, 40);
            fail("Fail. An exception must be thrown");
        } catch (RuntimeException e) {
            assertEquals("Insert a valid Width", e.getMessage());
        }
    }

    @Test
    public void createOccupationAreaValidWidth() {
        try {
            Location location = new Location(80, 90, 0);
            OccupationArea OA1 = new OccupationArea(location, 0, 40);
            fail("Fail. An exception must be thrown");
        } catch (RuntimeException e) {
            assertEquals("Insert a valid Width", e.getMessage());
        }
    }


    @Test
    public void testIfIsInsideOATrue() {
        //ARRANGE
        boolean result;
        Location location = new Location(80, 90, 0);
        Location location1 = new Location(80, 100, 0);
        OccupationArea OA1 = new OccupationArea(location, 40, 40);
        result = OA1.occupationAreaLimits(location1);
        //ASSERT
        assertTrue(result);
    }

    @Test
    public void testIfIsInsideOATrue2() {
        //ARRANGE
        boolean result;
        Location location = new Location(80, 90, 0);
        Location location1 = new Location(79.8, 90, 0);
        OccupationArea oA1 = new OccupationArea();
        oA1.setCenterLocation(location);
        oA1.setWidth(40);
        oA1.setLength(40);
        result = oA1.occupationAreaLimits(location1);
        //ASSERT
        assertTrue(result);
    }

    @Test
    public void testIfIsInsideOATrue3() {
        //ARRANGE
        boolean result;
        Location location = new Location(80, 90, 0);
        Location location1 = new Location(79.8, 80, 0);
        OccupationArea OA1 = new OccupationArea(location, 40, 40);
        result = OA1.occupationAreaLimits(location1);
        //ASSERT
        assertTrue(result);
    }

    @Test
    public void testIfIsInsideOATrue4() {
        //ARRANGE
        boolean result;
        Location location = new Location(80, 90, 0);
        Location location1 = new Location(80.1, 80, 0);
        OccupationArea OA1 = new OccupationArea(location, 40, 40);
        result = OA1.occupationAreaLimits(location1);
        //ASSERT
        assertTrue(result);
    }

    @Test
    public void testIfIsInsideOATrue5() {
        //ARRANGE
        boolean result;
        Location location = new Location(80, 90, 0);
        Location location1 = new Location(80.36174869318285, 164.2435450231298, 0);
        OccupationArea OA1 = new OccupationArea(location, 40, 40);
        result = OA1.occupationAreaLimits(location1);
        //ASSERT
        assertTrue(result);
    }


    @Test
    public void testIfIsInsideOATrue6() {
        //ARRANGE
        boolean result;
        Location location = new Location(80, 90, 0);
        Location location1 = new Location(79.63825130681715, 15.756454976870216, 0);
        OccupationArea OA1 = new OccupationArea(location, 40, 40);
        result = OA1.occupationAreaLimits(location1);
        //ASSERT
        assertTrue(result);
    }

    @Test
    public void testIfIsInsideOAFalse() {
        //ARRANGE
        boolean result;
        Location location = new Location(80.3516, -73.9864, 0);
        Location location1 = new Location(20.659, -73.9864, 0);
        OccupationArea OA1 = new OccupationArea(location, 40, 40);
        result = OA1.occupationAreaLimits(location1);
        //ASSERT
        assertFalse(result);
    }

    @Test
    public void testIfIsInsideOAFalse2() {
        //ARRANGE
        boolean result;
        Location location = new Location(80.3516, 50, 0);
        Location location1 = new Location(80.3516, -25, 0);
        OccupationArea OA1 = new OccupationArea(location, 40, 40);
        result = OA1.occupationAreaLimits(location1);
        //ASSERT
        assertFalse(result);
    }


    @Test
    public void testIfIsInsideOAFalse5() {
        //ARRANGE
        boolean result;
        Location location = new Location(41.178553, -8.608035, 0);
        Location location1 = new Location(41.178553, -8.608035, 0);
        OccupationArea OA1 = new OccupationArea(location, 0.261, 0.249);
        result = OA1.occupationAreaLimits(location1);
        //ASSERT
        assertTrue(result);
    }

    @Test
    public void testIfIsInsideOAFalse3() {
        //ARRANGE
        boolean result;
        Location location = new Location(80.3516, 50, 0);
        Location location1 = new Location(80.30, 140, 0);
        OccupationArea OA1 = new OccupationArea(location, 40, 40);
        result = OA1.occupationAreaLimits(location1);
        //ASSERT
        assertFalse(result);
    }

    @Test
    public void testIfIsInsideOAFalse4() {
        //ARRANGE
        boolean result;
        Location location = new Location(80.3516, 50, 0);
        Location location1 = new Location(-40, 25, 0);
        OccupationArea OA1 = new OccupationArea(location, 40, 40);
        result = OA1.occupationAreaLimits(location1);
        //ASSERT
        assertFalse(result);
    }

    @Test
    public void testIfRefusesValueOverMAxLatitudePositiveofOccupationAreaLocation() {
        //ARRANGE
        boolean result;
        Location location = new Location(91, 90, 0);
        Location location1 = new Location(80, 100, 0);
        OccupationArea OA1 = new OccupationArea(location, 40, 40);
        result = OA1.occupationAreaLimits(location1);
        //ASSERT
        assertFalse(result);
    }

    @Test
    public void testIfRefusesValueOverMAxLatitudeNegativeofOccupationAreaLocation() {
        //ARRANGE
        boolean result;
        Location location = new Location(-91, 179, 0);
        Location location1 = new Location(80, 100, 0);
        OccupationArea OA1 = new OccupationArea(location, 40, 40);
        result = OA1.occupationAreaLimits(location1);
        //ASSERT
        assertFalse(result);
    }

    @Test
    public void testIfRefusesValueOverMAxLongitudePositiveOfOccupationAreaLocation() {
        //ARRANGE
        boolean result;
        Location location = new Location(90, 181, 0);
        Location location1 = new Location(80, 100, 0);
        OccupationArea OA1 = new OccupationArea(location, 40, 40);
        result = OA1.occupationAreaLimits(location1);
        //ASSERT
        assertFalse(result);
    }

    @Test
    public void testIfRefusesValueOverMAxLongitudeNegativeofOccupationAreaLocation() {
        //ARRANGE
        boolean result;
        Location location = new Location(89, -181, 0);
        Location location1 = new Location(80, 100, 0);
        OccupationArea OA1 = new OccupationArea(location, 40, 40);
        result = OA1.occupationAreaLimits(location1);
        //ASSERT
        assertFalse(result);
    }

    @Test
    public void testIfRefusesValueOverMAxLatitudePositiveOfReferencePointLocation() {
        //ARRANGE
        boolean result;
        Location location = new Location(80, 90, 0);
        Location location1 = new Location(91, 100, 0);
        OccupationArea OA1 = new OccupationArea(location, 40, 40);
        result = OA1.occupationAreaLimits(location1);
        //ASSERT
        assertFalse(result);
    }

    @Test
    public void testIfRefusesValueOverMAxLatitudeNegativeOfReferencePointLocation() {
        //ARRANGE
        boolean result;
        Location location = new Location(80, 179, 0);
        Location location1 = new Location(-91, 100, 0);
        OccupationArea OA1 = new OccupationArea(location, 40, 40);
        result = OA1.occupationAreaLimits(location1);
        //ASSERT
        assertFalse(result);
    }

    @Test
    public void testIfRefusesValueOverMAxLongitudePositiveOfReferencePointLocation() {
        //ARRANGE
        boolean result;
        Location location = new Location(90, 100, 0);
        Location location1 = new Location(80, 181, 0);
        OccupationArea OA1 = new OccupationArea(location, 40, 40);
        result = OA1.occupationAreaLimits(location1);
        //ASSERT
        assertFalse(result);
    }

    @Test
    public void testIfRefusesValueOverMAxLongitudeNegativeOfReferenceLocation() {
        //ARRANGE
        boolean result;
        Location location = new Location(89, 100, 0);
        Location location1 = new Location(80, -181, 0);
        OccupationArea OA1 = new OccupationArea(location, 40, 40);
        result = OA1.occupationAreaLimits(location1);
        //ASSERT
        assertFalse(result);
    }


    @Test
    public void testocupationAreaLimitsTrue2() {
        //ARRANGE
        boolean result;
        double latitude1 = (50 / 110.574);
        double latitude2 = (50 / 110.574);
        double longitude1 = (111.320 * Math.cos(40));
        double longitude2 = (111.320 * Math.cos(40));

        Location location = new Location(latitude1, longitude1, 0);
        Location location1 = new Location(latitude2, longitude2, 0);
        OccupationArea OA1 = new OccupationArea(location, 50, 40);
        result = OA1.occupationAreaLimits(location1);
        //ASSERT
        assertTrue(result);
    }

    @Test
    public void testToString() {
        //ARRANGE
        Location location = new Location(89, 100, 0);
        OccupationArea OA1 = new OccupationArea(location, 40, 40);
        //ACT
        String result = OA1.toString();
        String expectedResult = "\n{Center point the Area: "+OA1.getCenterLocation() +"\n" + " Width: " + OA1.getWidth() +"\n" + " Length: " + OA1.getLength()+"}";
        //ASSERT
        assertEquals(expectedResult,result);
    }



}