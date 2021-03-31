package pt.ipp.isep.dei.project1.model.geographicarea;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project1.model.sensor.AreaSensor;
import pt.ipp.isep.dei.project1.model.sensor.SensorType;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class LocationTest {

    @Test
    public void testHashCodeLocation() {
        //ARRANGE
        Location location = new Location(10, 11, 20);
        location.hashCode();
        //ACT
        int result = ((int) location.getLatitude());
        //ASSERT
        assertEquals(location.hashCode(), result);

    }

    @Test
    public void ensureDistanceBetweenLocationsInTheSamePosition() {
        //Arrange
        double expectedResult = 0;
        double result;
        //Act
        Location origin = new Location();
        origin.setLatitude(41.7486);
        origin.setLongitude(-73.9864);
        origin.setAltitude(0);
        Location destination = new Location(41.7486, -73.9864, 0);
        result = origin.distanceTo(destination);
        //Assert
        assertEquals(expectedResult, result, 0.1);
    }

    @Test
    public void ensureDistanceBetweenLocationsLatitudePositiveLongitudeNegative() {
        //Arrange
        double expectedResult = 111.2;
        double result;
        //Act
        Location origin = new Location(40.7486, -73.9864, 0);
        Location destination = new Location(41.7486, -73.9864, 0);
        result = origin.distanceTo(destination);
        //Assert
        assertEquals(expectedResult, result, 0.1);
    }

    @Test
    public void ensureDistanceBetweenLocationsLatitudeNegativeLongitudeNegative() {
        //Arrange
        double expectedResult = 513.7;
        double result;
        //Act
        Location origin = new Location(-40.7486, -73.9864, 0);
        Location destination = new Location(-41.7486, -79.9864, 0);
        result = origin.distanceTo(destination);
        //Assert
        assertEquals(expectedResult, result, 0.1);
    }

    @Test
    public void ensureDistanceBetweenLocationsLatitudePositiveLongitudePositive() {
        //Arrange
        double expectedResult = 595.5;
        double result;
        //Act
        Location origin = new Location(40.7486, 80.9864, 0);
        Location destination = new Location(41.7486, 73.9864, 0);
        result = origin.distanceTo(destination);
        //Assert
        assertEquals(expectedResult, result, 0.1);
    }

    @Test
    public void ensureDistanceBetweenLocationsLatitudeNegativeLongitudePositive() {
        //Arrange
        double expectedResult = 595.5;
        double result;
        //Act
        Location origin = new Location(-40.7486, 80.9864, 0);
        Location destination = new Location(-41.7486, 73.9864, 0);
        result = origin.distanceTo(destination);
        //Assert
        assertEquals(expectedResult, result, 0.1);
    }

    @Test
    public void testHashCode() {
        //ARRANGE
        Location l = new Location(10, 20, 0);
        //ACT
        int result = ((int) l.getLatitude());
        //ASSERT
        assertEquals(((int) l.getLatitude()), result);
    }


    @Test
    public void equalsTrueTest() {
        //ARRANGE
        Location l = new Location(40.7486, -73.9864, 0);
        Object obj = new Location(40.7486, -73.9864, 0);
        //ACT
        boolean result = l.equals(obj);
        //ASSERT
        assertTrue(result);
    }

    @Test
    public void equalsFalseTest() {
        //ARRANGE
        Location l = new Location(40.7486, -73.9864, 0);
        Object obj = new Location(40.7486, -74.9864, 0);
        //ACT
        boolean result = l.equals(obj);
        //ASSERT
        assertFalse(result);
    }


    @Test
    public void equalsFalseTest2() {
        //ARRANGE
        Location l = new Location(40.7486, -73.9864, 0);
        Object obj = new Location(41.7486, -73.9864, 0);
        //ACT
        boolean result = l.equals(obj);
        //ASSERT
        assertFalse(result);
    }

    @Test
    public void equalsFalseTest3() {
        //ARRANGE
        Location l = new Location(40.7486, -73.9864, 0);
        Object obj = new Location(41.7486, -74.9864, 0);
        //ACT
        boolean result = l.equals(obj);
        //ASSERT
        assertFalse(result);
    }

    @Test
    public void equalsFalseTestOtherClass() {
        //ARRANGE
        Location h = new Location(40.7486, -73.9864, 0);
        Object obj = new AreaSensor("1", "s1", new Location(40.7486, -73.9864, 0), new SensorType("Temperature"), LocalDate.of(2019, 01, 05), "ºC");
        //ACT
        boolean result = h.equals(obj);
        //ASSERT
        assertFalse(result);
    }

    @Test
    public void equalsTrueTestMyself() {
        //ARRANGE
        Location l = new Location(40.7486, -73.9864, 0);
        Object obj = l;
        boolean result = l.equals(obj);
        //ASSERT
        assertTrue(result);
    }


    @Test
    public void createLocationValidLatitudeNaN() {
        try {
            Location l = new Location(Double.NaN, -73.9864, 0);
            fail("Fail. An exception must be thrown");
        } catch (RuntimeException e) {
            assertEquals("Insert a valid Latitude", e.getMessage());
        }
    }


    @Test
    public void createLocationValidLongitudeNan() {
        try {
            Location l = new Location(40.7486, Double.NaN, 0);
            fail("Fail. An exception must be thrown");
        } catch (RuntimeException e) {
            assertEquals("Insert a valid Longitude", e.getMessage());
        }
    }


    @Test
    public void createLocationValidAltitudeNaN() {
        try {
            Location l = new Location(40.7486, -73.9864, Double.NaN);
            fail("Fail. An exception must be thrown");
        } catch (RuntimeException e) {
            assertEquals("Insert a valid Altitude", e.getMessage());
        }
    }

    @Test
    public void testToString() {
        //ARRANGE
        Location location = new Location(89, 100, 89);
        //ACT
        String result = location.toString();
        String expectedResult = "Latitude: " + location.getLatitude() + "\n" + " Longitude: " + location.getLongitude() + "\n" + "Altitude: " + location.getAltitude();
        //ASSERT
        assertEquals(expectedResult, result);
    }
}




