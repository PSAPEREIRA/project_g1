package pt.ipp.isep.dei.project1.model.sensor;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project1.model.geographicarea.Location;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

class RoomSensorTest {

    @Test
    void checkIfGetSetUnit(){
        RoomSensor s1 = new RoomSensor();
        s1.setUnit("mm");
        String result = s1.getUnit();
        String expectedResult = "mm";
        assertEquals(expectedResult,result);
    }


    @Test
    public void equalsTrueTestType() {
        //ARRANGE
        RoomSensor s1 = new RoomSensor();
        s1.setIdOfRoomSensor("s1");
        s1.setSensorType(new SensorType("temp"));
        Object obj = new RoomSensor();
        ((RoomSensor) obj).setSensorType(new SensorType("temp"));
        ((RoomSensor) obj).setIdOfRoomSensor("s2");
        //ACT
        boolean result = s1.equals(obj);
        //ASSERT
        assertTrue(result);
    }

    @Test
    public void equalsTrueTestIdAndType() {
        //ARRANGE
        RoomSensor s1 = new RoomSensor();
        s1.setIdOfRoomSensor("s1");
        s1.setSensorType(new SensorType("temp"));
        Object obj = new RoomSensor();
        ((RoomSensor) obj).setSensorType(new SensorType("temp"));
        ((RoomSensor) obj).setIdOfRoomSensor("s1");
        //ACT
        boolean result = s1.equals(obj);
        //ASSERT
        assertTrue(result);
    }

    @Test
    public void equalsTrueTestId() {
        //ARRANGE
        RoomSensor s1 = new RoomSensor();
        s1.setIdOfRoomSensor("s1");
        s1.setSensorType(new SensorType("rain"));
        Object obj = new RoomSensor();
        ((RoomSensor) obj).setSensorType(new SensorType("temp"));
        ((RoomSensor) obj).setIdOfRoomSensor("s1");
        //ACT
        boolean result = s1.equals(obj);
        //ASSERT
        assertTrue(result);
    }

    @Test
    public void equalsTrueTestUnit() {
        //ARRANGE
        RoomSensor s1 = new RoomSensor();
        s1.setIdOfRoomSensor("s1");
        s1.setUnit("15");
        s1.setSensorType(new SensorType("rain"));
        Object obj = new RoomSensor();
        ((RoomSensor) obj).setSensorType(new SensorType("temp"));
        ((RoomSensor) obj).setIdOfRoomSensor("s1");
        ((RoomSensor) obj).setUnit("15");
        //ACT
        boolean result = s1.equals(obj);
        //ASSERT
        assertTrue(result);
    }


    @Test
    public void equalsRoomFalseTest() {
        //ARRANGE
        RoomSensor s1 = new RoomSensor();
        s1.setName("Sensor1");
        Location location = new Location(0, 0, 0);
        //ACT
        boolean result = s1.equals(location);
        //ASSERT
        assertFalse(result);
    }

    @Test
    public void createHouseNonValidNameNull() throws Exception {
        try {
            RoomSensor origin = new RoomSensor("","sensor1", new SensorType("temperature"), LocalDate.of(2018, 12, 10), "ºC");
            fail("Fail. An exception must be thrown");
        } catch (RuntimeException e) {
            assertEquals("Insert a valid name!", e.getMessage());
        }
    }

    @Test
    public void createHouseNonValidNameEmpty() throws Exception {
        try {
            RoomSensor origin = new RoomSensor("","sensor1", new SensorType("temperature"), LocalDate.of(2018, 12, 10), "ºC");
            fail("Fail. An exception must be thrown");
        } catch (RuntimeException e) {
            assertEquals("Insert a valid name!", e.getMessage());
        }
    }

    @Test
    public void testHashCode() {
        //ARRANGE
        RoomSensor s1 = new RoomSensor();
        s1.setName("s1");
        s1.setSensorType(new SensorType("rain"));
        //ACT
        int result2 = s1.getName().hashCode();
        int result = s1.getSensorType().hashCode();
        //ASSERT
        assertEquals(s1.hashCode(), Objects.hash(result2, result));
    }

    @Test
    public void testGetInstancesWithTemperatureHigherLowerComfortHigherCat1() {
        //Arrange
        RoomSensor s1 = new RoomSensor();
        s1.setName("s1");
        s1.setSensorType(new SensorType("temperature"));
        LocalDate startDate = LocalDate.of(2019, 01, 14);
        LocalDateTime date1 = LocalDateTime.of(2019, 1, 15, 12, 00);
        Reading r1 = new Reading(date1, 28);
        LocalDateTime date2 = LocalDateTime.of(2019, 1, 15, 12, 3);
        Reading r2 = new Reading(date2, 15);
        LocalDateTime date3 = LocalDateTime.of(2019, 1, 17, 12, 5);
        Reading r3 = new Reading(date3, 33);
        LocalDateTime date4 = LocalDateTime.of(2019, 1, 17, 12, 6);
        Reading r4 = new Reading(date4, 31);
        LocalDateTime date5 = LocalDateTime.of(2019, 1, 14, 12, 10);
        Reading r5 = new Reading(date5, -15);
        LocalDateTime date6 = LocalDateTime.of(2019, 1, 14, 12, 15);
        Reading r6 = new Reading(date6, -2);
        ListOfReadings lRead1 = new ListOfReadings();
        lRead1.addReading(r1);
        lRead1.addReading(r2);
        lRead1.addReading(r3);
        lRead1.addReading(r4);
        lRead1.addReading(r5);
        lRead1.addReading(r6);
        int cat = 1;
        String option = "HIGHER";
        List<Double> listHouseAreaTemp = new ArrayList<>();
        listHouseAreaTemp.add(15.0);
        listHouseAreaTemp.add(20.0);
        listHouseAreaTemp.add(25.0);
        listHouseAreaTemp.add(30.0);
        s1.setListOfReadings(lRead1);
        List<LocalDateTime> expectedResult = new ArrayList<>();
        expectedResult.add(r1.getDateTime());
        expectedResult.add(r3.getDateTime());
        expectedResult.add(r4.getDateTime());
        //ACT
        List<LocalDateTime> result = s1.getInstancesWithTemperatureHigherLowerComfort(listHouseAreaTemp, cat, option, startDate);
        //ASSERT
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetInstancesWithTemperatureHigherLowerComfortLowerCat1() {
        //Arrange
        RoomSensor s1 = new RoomSensor();
        s1.setName("s1");
        s1.setSensorType(new SensorType("temperature"));
        LocalDate startDate = LocalDate.of(2019, 01, 14);
        LocalDateTime date1 = LocalDateTime.of(2019, 1, 15, 12, 00);
        Reading r1 = new Reading(date1, 28);
        LocalDateTime date2 = LocalDateTime.of(2019, 1, 15, 12, 3);
        Reading r2 = new Reading(date2, 15);
        LocalDateTime date3 = LocalDateTime.of(2019, 1, 17, 12, 5);
        Reading r3 = new Reading(date3, 33);
        LocalDateTime date4 = LocalDateTime.of(2019, 1, 17, 12, 6);
        Reading r4 = new Reading(date4, 31);
        LocalDateTime date5 = LocalDateTime.of(2019, 1, 14, 12, 10);
        Reading r5 = new Reading(date5, -15);
        LocalDateTime date6 = LocalDateTime.of(2019, 1, 14, 12, 15);
        Reading r6 = new Reading(date6, -2);
        ListOfReadings lRead1 = new ListOfReadings();
        lRead1.addReading(r1);
        lRead1.addReading(r2);
        lRead1.addReading(r3);
        lRead1.addReading(r4);
        lRead1.addReading(r5);
        lRead1.addReading(r6);
        int cat = 1;
        String option = "LOWER";
        List<Double> listHouseAreaTemp = new ArrayList<>();
        listHouseAreaTemp.add(15.0);
        listHouseAreaTemp.add(20.0);
        listHouseAreaTemp.add(25.0);
        listHouseAreaTemp.add(30.0);
        s1.setListOfReadings(lRead1);
        List<LocalDateTime> expectedResult = new ArrayList<>();
        expectedResult.add(r5.getDateTime());
        expectedResult.add(r6.getDateTime());
        expectedResult.add(r2.getDateTime());
        //ACT
        List<LocalDateTime> result = s1.getInstancesWithTemperatureHigherLowerComfort(listHouseAreaTemp, cat, option, startDate);
        //ASSERT
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetInstancesWithTemperatureHigherLowerComfortHigherCat2() {
        //Arrange
        RoomSensor s1 = new RoomSensor();
        s1.setName("s1");
        s1.setSensorType(new SensorType("temperature"));
        LocalDate startDate = LocalDate.of(2019, 01, 14);
        LocalDateTime date1 = LocalDateTime.of(2019, 1, 15, 12, 00);
        Reading r1 = new Reading(date1, 28);
        LocalDateTime date2 = LocalDateTime.of(2019, 1, 15, 12, 3);
        Reading r2 = new Reading(date2, 15);
        LocalDateTime date3 = LocalDateTime.of(2019, 1, 17, 12, 5);
        Reading r3 = new Reading(date3, 33);
        LocalDateTime date4 = LocalDateTime.of(2019, 1, 17, 12, 6);
        Reading r4 = new Reading(date4, 31);
        LocalDateTime date5 = LocalDateTime.of(2019, 1, 14, 12, 10);
        Reading r5 = new Reading(date5, -15);
        LocalDateTime date6 = LocalDateTime.of(2019, 1, 14, 12, 15);
        Reading r6 = new Reading(date6, -2);
        ListOfReadings lRead1 = new ListOfReadings();
        lRead1.addReading(r1);
        lRead1.addReading(r2);
        lRead1.addReading(r3);
        lRead1.addReading(r4);
        lRead1.addReading(r5);
        lRead1.addReading(r6);
        int cat = 2;
        String option = "HIGHER";
        List<Double> listHouseAreaTemp = new ArrayList<>();
        listHouseAreaTemp.add(15.0);
        listHouseAreaTemp.add(20.0);
        listHouseAreaTemp.add(25.0);
        listHouseAreaTemp.add(30.0);
        s1.setListOfReadings(lRead1);
        List<LocalDateTime> expectedResult = new ArrayList<>();

        expectedResult.add(r3.getDateTime());

        //ACT
        List<LocalDateTime> result = s1.getInstancesWithTemperatureHigherLowerComfort(listHouseAreaTemp, cat, option, startDate);
        //ASSERT
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetInstancesWithTemperatureHigherLowerComfortLowerCat2() {
        //Arrange
        RoomSensor s1 = new RoomSensor();
        s1.setName("s1");
        s1.setSensorType(new SensorType("temperature"));
        LocalDate startDate = LocalDate.of(2019, 01, 14);
        LocalDateTime date1 = LocalDateTime.of(2019, 1, 15, 12, 00);
        Reading r1 = new Reading(date1, 28);
        LocalDateTime date2 = LocalDateTime.of(2019, 1, 15, 12, 3);
        Reading r2 = new Reading(date2, 15);
        LocalDateTime date3 = LocalDateTime.of(2019, 1, 17, 12, 5);
        Reading r3 = new Reading(date3, 33);
        LocalDateTime date4 = LocalDateTime.of(2019, 1, 17, 12, 6);
        Reading r4 = new Reading(date4, 31);
        LocalDateTime date5 = LocalDateTime.of(2019, 1, 14, 12, 10);
        Reading r5 = new Reading(date5, -15);
        LocalDateTime date6 = LocalDateTime.of(2019, 1, 14, 12, 15);
        Reading r6 = new Reading(date6, -2);
        ListOfReadings lRead1 = new ListOfReadings();
        lRead1.addReading(r1);
        lRead1.addReading(r2);
        lRead1.addReading(r3);
        lRead1.addReading(r4);
        lRead1.addReading(r5);
        lRead1.addReading(r6);
        int cat = 2;
        String option = "LOWER";
        List<Double> listHouseAreaTemp = new ArrayList<>();
        listHouseAreaTemp.add(15.0);
        listHouseAreaTemp.add(20.0);
        listHouseAreaTemp.add(25.0);
        listHouseAreaTemp.add(30.0);
        s1.setListOfReadings(lRead1);
        List<LocalDateTime> expectedResult = new ArrayList<>();

        expectedResult.add(r5.getDateTime());
        expectedResult.add(r6.getDateTime());
        expectedResult.add(r2.getDateTime());

        //ACT
        List<LocalDateTime> result = s1.getInstancesWithTemperatureHigherLowerComfort(listHouseAreaTemp, cat, option, startDate);
        //ASSERT
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetInstancesWithTemperatureHigherLowerComfortHigherCat3() {
        //Arrange
        RoomSensor s1 = new RoomSensor();
        s1.setName("s1");
        s1.setSensorType(new SensorType("temperature"));
        LocalDate startDate = LocalDate.of(2019, 01, 14);
        LocalDateTime date1 = LocalDateTime.of(2019, 1, 15, 12, 00);
        Reading r1 = new Reading(date1, 28);
        LocalDateTime date2 = LocalDateTime.of(2019, 1, 15, 12, 3);
        Reading r2 = new Reading(date2, 15);
        LocalDateTime date3 = LocalDateTime.of(2019, 1, 17, 12, 5);
        Reading r3 = new Reading(date3, 33);
        LocalDateTime date4 = LocalDateTime.of(2019, 1, 17, 12, 6);
        Reading r4 = new Reading(date4, 31);
        LocalDateTime date5 = LocalDateTime.of(2019, 1, 14, 12, 10);
        Reading r5 = new Reading(date5, -15);
        LocalDateTime date6 = LocalDateTime.of(2019, 1, 14, 12, 15);
        Reading r6 = new Reading(date6, -2);
        ListOfReadings lRead1 = new ListOfReadings();
        lRead1.addReading(r1);
        lRead1.addReading(r2);
        lRead1.addReading(r3);
        lRead1.addReading(r4);
        lRead1.addReading(r5);
        lRead1.addReading(r6);
        int cat = 3;
        String option = "HIGHER";
        List<Double> listHouseAreaTemp = new ArrayList<>();
        listHouseAreaTemp.add(15.0);
        listHouseAreaTemp.add(20.0);
        listHouseAreaTemp.add(25.0);
        listHouseAreaTemp.add(30.0);
        s1.setListOfReadings(lRead1);
        List<LocalDateTime> expectedResult = new ArrayList<>();

        expectedResult.add(r3.getDateTime());

        //ACT
        List<LocalDateTime> result = s1.getInstancesWithTemperatureHigherLowerComfort(listHouseAreaTemp, cat, option, startDate);

        //ASSERT
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetInstancesWithTemperatureHigherLowerComfortLowerCat3() {
        //Arrange
        RoomSensor s1 = new RoomSensor();
        s1.setName("s1");
        s1.setSensorType(new SensorType("temperature"));
        LocalDate startDate = LocalDate.of(2019, 01, 14);
        LocalDateTime date1 = LocalDateTime.of(2019, 1, 15, 12, 00);
        Reading r1 = new Reading(date1, 28);
        LocalDateTime date2 = LocalDateTime.of(2019, 1, 15, 12, 3);
        Reading r2 = new Reading(date2, 15);
        LocalDateTime date3 = LocalDateTime.of(2019, 1, 17, 12, 5);
        Reading r3 = new Reading(date3, 33);
        LocalDateTime date4 = LocalDateTime.of(2019, 1, 17, 12, 6);
        Reading r4 = new Reading(date4, 31);
        LocalDateTime date5 = LocalDateTime.of(2019, 1, 14, 12, 10);
        Reading r5 = new Reading(date5, -15);
        LocalDateTime date6 = LocalDateTime.of(2019, 1, 14, 12, 15);
        Reading r6 = new Reading(date6, -2);
        ListOfReadings lRead1 = new ListOfReadings();
        lRead1.addReading(r1);
        lRead1.addReading(r2);
        lRead1.addReading(r3);
        lRead1.addReading(r4);
        lRead1.addReading(r5);
        lRead1.addReading(r6);
        int cat = 3;
        String option = "LOWER";
        List<Double> listHouseAreaTemp = new ArrayList<>();
        listHouseAreaTemp.add(15.0);
        listHouseAreaTemp.add(20.0);
        listHouseAreaTemp.add(25.0);
        listHouseAreaTemp.add(30.0);
        s1.setListOfReadings(lRead1);
        List<LocalDateTime> expectedResult = new ArrayList<>();

        expectedResult.add(r5.getDateTime());
        expectedResult.add(r6.getDateTime());
        expectedResult.add(r2.getDateTime());

        //ACT
        List<LocalDateTime> result = s1.getInstancesWithTemperatureHigherLowerComfort(listHouseAreaTemp, cat, option, startDate);
        //ASSERT
        assertEquals(expectedResult, result);
    }
    @Test
    public void testGetInstancesWithTemperatureHigherLowerComfortHigherCat1MutDiv() {
        //Arrange
        RoomSensor s1 = new RoomSensor();
        s1.setName("s1");
        s1.setSensorType(new SensorType("temperature"));
        LocalDate startDate = LocalDate.of(2019, 01, 14);
        LocalDateTime date1 = LocalDateTime.of(2019, 1, 15, 12, 00);
        Reading r1 = new Reading(date1, 28);
        LocalDateTime date2 = LocalDateTime.of(2019, 1, 15, 12, 3);
        Reading r2 = new Reading(date2, 15);
        LocalDateTime date3 = LocalDateTime.of(2019, 1, 17, 12, 5);
        Reading r3 = new Reading(date3, 33);
        LocalDateTime date4 = LocalDateTime.of(2019, 1, 17, 12, 6);
        Reading r4 = new Reading(date4, 21);
        LocalDateTime date5 = LocalDateTime.of(2019, 1, 14, 12, 10);
        Reading r5 = new Reading(date5, -15);
        LocalDateTime date6 = LocalDateTime.of(2019, 1, 14, 12, 15);
        Reading r6 = new Reading(date6, -2);
        ListOfReadings lRead1 = new ListOfReadings();
        lRead1.addReading(r1);
        lRead1.addReading(r2);
        lRead1.addReading(r3);
        lRead1.addReading(r4);
        lRead1.addReading(r5);
        lRead1.addReading(r6);
        int cat = 1;
        String option = "HIGHER";
        List<Double> listHouseAreaTemp = new ArrayList<>();
        listHouseAreaTemp.add(15.0);
        listHouseAreaTemp.add(20.0);
        listHouseAreaTemp.add(25.0);
        listHouseAreaTemp.add(30.0);
        s1.setListOfReadings(lRead1);
        List<LocalDateTime> expectedResult = new ArrayList<>();
        expectedResult.add(r1.getDateTime());
        expectedResult.add(r3.getDateTime());
        //ACT
        List<LocalDateTime> result = s1.getInstancesWithTemperatureHigherLowerComfort(listHouseAreaTemp, cat, option, startDate);
        //ASSERT
        assertEquals(expectedResult, result);
    }
    @Test
    public void testGetInstancesWithTemperatureHigherLowerComfortHigherCat1MutSub() {
        //Arrange
        RoomSensor s1 = new RoomSensor();
        s1.setName("s1");
        s1.setSensorType(new SensorType("temperature"));
        LocalDate startDate = LocalDate.of(2019, 01, 14);
        LocalDateTime date1 = LocalDateTime.of(2019, 1, 15, 12, 00);
        Reading r1 = new Reading(date1, 28);
        LocalDateTime date2 = LocalDateTime.of(2019, 1, 15, 12, 3);
        Reading r2 = new Reading(date2, 15);
        LocalDateTime date3 = LocalDateTime.of(2019, 1, 17, 12, 5);
        Reading r3 = new Reading(date3, 33);
        LocalDateTime date4 = LocalDateTime.of(2019, 1, 17, 12, 6);
        Reading r4 = new Reading(date4, -8);
        LocalDateTime date5 = LocalDateTime.of(2019, 1, 14, 12, 10);
        Reading r5 = new Reading(date5, -15);
        LocalDateTime date6 = LocalDateTime.of(2019, 1, 14, 12, 15);
        Reading r6 = new Reading(date6, -2);
        ListOfReadings lRead1 = new ListOfReadings();
        lRead1.addReading(r1);
        lRead1.addReading(r2);
        lRead1.addReading(r3);
        lRead1.addReading(r4);
        lRead1.addReading(r5);
        lRead1.addReading(r6);
        int cat = 1;
        String option = "HIGHER";
        List<Double> listHouseAreaTemp = new ArrayList<>();
        listHouseAreaTemp.add(15.0);
        listHouseAreaTemp.add(20.0);
        listHouseAreaTemp.add(25.0);
        listHouseAreaTemp.add(30.0);
        s1.setListOfReadings(lRead1);
        List<LocalDateTime> expectedResult = new ArrayList<>();
        expectedResult.add(r1.getDateTime());
        expectedResult.add(r3.getDateTime());
        //ACT
        List<LocalDateTime> result = s1.getInstancesWithTemperatureHigherLowerComfort(listHouseAreaTemp, cat, option, startDate);
        //ASSERT
        assertEquals(expectedResult, result);
    }
    @Test
    public void testGetInstancesWithTemperatureHigherLowerComfortLowerCat1MutDiv() {
        //Arrange
        RoomSensor s1 = new RoomSensor();
        s1.setName("s1");
        s1.setSensorType(new SensorType("temperature"));
        LocalDate startDate = LocalDate.of(2019, 01, 14);
        LocalDateTime date1 = LocalDateTime.of(2019, 1, 15, 12, 00);
        Reading r1 = new Reading(date1, 28);
        LocalDateTime date2 = LocalDateTime.of(2019, 1, 15, 12, 3);
        Reading r2 = new Reading(date2, 15);
        LocalDateTime date3 = LocalDateTime.of(2019, 1, 17, 12, 5);
        Reading r3 = new Reading(date3, 33);
        LocalDateTime date4 = LocalDateTime.of(2019, 1, 17, 12, 6);
        Reading r4 = new Reading(date4, 21);
        LocalDateTime date5 = LocalDateTime.of(2019, 1, 14, 12, 10);
        Reading r5 = new Reading(date5, -15);
        LocalDateTime date6 = LocalDateTime.of(2019, 1, 14, 12, 15);
        Reading r6 = new Reading(date6, -2);
        ListOfReadings lRead1 = new ListOfReadings();
        lRead1.addReading(r1);
        lRead1.addReading(r2);
        lRead1.addReading(r3);
        lRead1.addReading(r4);
        lRead1.addReading(r5);
        lRead1.addReading(r6);
        int cat = 1;
        String option = "LOWER";
        List<Double> listHouseAreaTemp = new ArrayList<>();
        listHouseAreaTemp.add(15.0);
        listHouseAreaTemp.add(20.0);
        listHouseAreaTemp.add(25.0);
        listHouseAreaTemp.add(30.0);
        s1.setListOfReadings(lRead1);
        List<LocalDateTime> expectedResult = new ArrayList<>();
        expectedResult.add(r5.getDateTime());
        expectedResult.add(r6.getDateTime());
        expectedResult.add(r2.getDateTime());
        expectedResult.add(r4.getDateTime());
        //ACT
        List<LocalDateTime> result = s1.getInstancesWithTemperatureHigherLowerComfort(listHouseAreaTemp, cat, option, startDate);
        //ASSERT
        assertEquals(expectedResult, result);
    }
    @Test
    public void testGetInstancesWithTemperatureHigherLowerComfortLowerCat1MutAdd() {
        //Arrange
        RoomSensor s1 = new RoomSensor();
        s1.setName("s1");
        s1.setSensorType(new SensorType("temperature"));
        LocalDate startDate = LocalDate.of(2019, 01, 14);
        LocalDateTime date1 = LocalDateTime.of(2019, 1, 15, 12, 00);
        Reading r1 = new Reading(date1, 28);
        LocalDateTime date2 = LocalDateTime.of(2019, 1, 15, 12, 3);
        Reading r2 = new Reading(date2, 15);
        LocalDateTime date3 = LocalDateTime.of(2019, 1, 17, 12, 5);
        Reading r3 = new Reading(date3, 33);
        LocalDateTime date4 = LocalDateTime.of(2019, 1, 17, 12, 6);
        Reading r4 = new Reading(date4, 30.7);
        LocalDateTime date5 = LocalDateTime.of(2019, 1, 14, 12, 10);
        Reading r5 = new Reading(date5, -15);
        LocalDateTime date6 = LocalDateTime.of(2019, 1, 14, 12, 15);
        Reading r6 = new Reading(date6, -2);
        ListOfReadings lRead1 = new ListOfReadings();
        lRead1.addReading(r1);
        lRead1.addReading(r2);
        lRead1.addReading(r3);
        lRead1.addReading(r4);
        lRead1.addReading(r5);
        lRead1.addReading(r6);
        int cat = 1;
        String option = "LOWER";
        List<Double> listHouseAreaTemp = new ArrayList<>();
        listHouseAreaTemp.add(15.0);
        listHouseAreaTemp.add(20.0);
        listHouseAreaTemp.add(25.0);
        listHouseAreaTemp.add(30.0);
        s1.setListOfReadings(lRead1);
        List<LocalDateTime> expectedResult = new ArrayList<>();

        expectedResult.add(r5.getDateTime());
        expectedResult.add(r6.getDateTime());
        expectedResult.add(r2.getDateTime());

        //ACT
        List<LocalDateTime> result = s1.getInstancesWithTemperatureHigherLowerComfort(listHouseAreaTemp, cat, option, startDate);
        //ASSERT
        assertEquals(expectedResult, result);
    }
    @Test
    public void testGetInstancesWithTemperatureHigherLowerComfortLowerCat2MutDiv() {
        //Arrange
        RoomSensor s1 = new RoomSensor();
        s1.setName("s1");
        s1.setSensorType(new SensorType("temperature"));
        LocalDate startDate = LocalDate.of(2019, 01, 14);
        LocalDateTime date1 = LocalDateTime.of(2019, 1, 15, 12, 00);
        Reading r1 = new Reading(date1, 28);
        LocalDateTime date2 = LocalDateTime.of(2019, 1, 15, 12, 3);
        Reading r2 = new Reading(date2, 15);
        LocalDateTime date3 = LocalDateTime.of(2019, 1, 17, 12, 5);
        Reading r3 = new Reading(date3, 33);
        LocalDateTime date4 = LocalDateTime.of(2019, 1, 17, 12, 6);
        Reading r4 = new Reading(date4, 21.8);
        LocalDateTime date5 = LocalDateTime.of(2019, 1, 14, 12, 10);
        Reading r5 = new Reading(date5, -15);
        LocalDateTime date6 = LocalDateTime.of(2019, 1, 14, 12, 15);
        Reading r6 = new Reading(date6, -2);
        ListOfReadings lRead1 = new ListOfReadings();
        lRead1.addReading(r1);
        lRead1.addReading(r2);
        lRead1.addReading(r3);
        lRead1.addReading(r4);
        lRead1.addReading(r5);
        lRead1.addReading(r6);
        int cat = 2;
        String option = "LOWER";
        List<Double> listHouseAreaTemp = new ArrayList<>();
        listHouseAreaTemp.add(15.0);
        listHouseAreaTemp.add(20.0);
        listHouseAreaTemp.add(25.0);
        listHouseAreaTemp.add(30.0);
        s1.setListOfReadings(lRead1);
        List<LocalDateTime> expectedResult = new ArrayList<>();

        expectedResult.add(r5.getDateTime());
        expectedResult.add(r6.getDateTime());
        expectedResult.add(r2.getDateTime());
        expectedResult.add(r4.getDateTime());
        //ACT
        List<LocalDateTime> result = s1.getInstancesWithTemperatureHigherLowerComfort(listHouseAreaTemp, cat, option, startDate);
        //ASSERT
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetInstancesWithTemperatureHigherLowerComfortHigherCat3MutSub() {
        //Arrange
        RoomSensor s1 = new RoomSensor();
        s1.setName("s1");
        s1.setSensorType(new SensorType("temperature"));
        LocalDate startDate = LocalDate.of(2019, 01, 14);
        LocalDateTime date1 = LocalDateTime.of(2019, 1, 15, 12, 00);
        Reading r1 = new Reading(date1, 28);
        LocalDateTime date2 = LocalDateTime.of(2019, 1, 15, 12, 3);
        Reading r2 = new Reading(date2, 15);
        LocalDateTime date3 = LocalDateTime.of(2019, 1, 17, 12, 5);
        Reading r3 = new Reading(date3, 33);
        LocalDateTime date4 = LocalDateTime.of(2019, 1, 17, 12, 6);
        Reading r4 = new Reading(date4, 25.03);
        LocalDateTime date5 = LocalDateTime.of(2019, 1, 14, 12, 10);
        Reading r5 = new Reading(date5, -15);
        LocalDateTime date6 = LocalDateTime.of(2019, 1, 14, 12, 15);
        Reading r6 = new Reading(date6, -2);
        ListOfReadings lRead1 = new ListOfReadings();
        lRead1.addReading(r1);
        lRead1.addReading(r2);
        lRead1.addReading(r3);
        lRead1.addReading(r4);
        lRead1.addReading(r5);
        lRead1.addReading(r6);
        int cat = 3;
        String option = "HIGHER";
        List<Double> listHouseAreaTemp = new ArrayList<>();
        listHouseAreaTemp.add(15.0);
        listHouseAreaTemp.add(20.0);
        listHouseAreaTemp.add(25.0);
        listHouseAreaTemp.add(30.0);
        s1.setListOfReadings(lRead1);
        List<LocalDateTime> expectedResult = new ArrayList<>();

        expectedResult.add(r3.getDateTime());

        //ACT
        List<LocalDateTime> result = s1.getInstancesWithTemperatureHigherLowerComfort(listHouseAreaTemp, cat, option, startDate);

        //ASSERT
        assertEquals(expectedResult, result);
    }
    @Test
    public void testGetInstancesWithTemperatureHigherLowerComfortHigherCat3MutDiv() {
        //Arrange
        RoomSensor s1 = new RoomSensor();
        s1.setName("s1");
        s1.setSensorType(new SensorType("temperature"));
        LocalDate startDate = LocalDate.of(2019, 01, 14);
        LocalDateTime date1 = LocalDateTime.of(2019, 1, 15, 12, 00);
        Reading r1 = new Reading(date1, 28);
        LocalDateTime date2 = LocalDateTime.of(2019, 1, 15, 12, 3);
        Reading r2 = new Reading(date2, 15);
        LocalDateTime date3 = LocalDateTime.of(2019, 1, 17, 12, 5);
        Reading r3 = new Reading(date3, 33);
        LocalDateTime date4 = LocalDateTime.of(2019, 1, 17, 12, 6);
        Reading r4 = new Reading(date4, 22.08);
        LocalDateTime date5 = LocalDateTime.of(2019, 1, 14, 12, 10);
        Reading r5 = new Reading(date5, -15);
        LocalDateTime date6 = LocalDateTime.of(2019, 1, 14, 12, 15);
        Reading r6 = new Reading(date6, -2);
        ListOfReadings lRead1 = new ListOfReadings();
        lRead1.addReading(r1);
        lRead1.addReading(r2);
        lRead1.addReading(r3);
        lRead1.addReading(r4);
        lRead1.addReading(r5);
        lRead1.addReading(r6);
        int cat = 3;
        String option = "HIGHER";
        List<Double> listHouseAreaTemp = new ArrayList<>();
        listHouseAreaTemp.add(15.0);
        listHouseAreaTemp.add(20.0);
        listHouseAreaTemp.add(25.0);
        listHouseAreaTemp.add(30.0);
        s1.setListOfReadings(lRead1);
        List<LocalDateTime> expectedResult = new ArrayList<>();

        expectedResult.add(r3.getDateTime());

        //ACT
        List<LocalDateTime> result = s1.getInstancesWithTemperatureHigherLowerComfort(listHouseAreaTemp, cat, option, startDate);

        //ASSERT
        assertEquals(expectedResult, result);
    }
    @Test
    public void testGetInstancesWithTemperatureHigherLowerComfortHigherCatDefault() {
        //Arrange
        RoomSensor s1 = new RoomSensor();
        s1.setName("s1");
        s1.setSensorType(new SensorType("temperature"));
        LocalDate startDate = LocalDate.of(2019, 01, 14);
        LocalDateTime date1 = LocalDateTime.of(2019, 1, 15, 12, 00);
        Reading r1 = new Reading(date1, 28);
        LocalDateTime date2 = LocalDateTime.of(2019, 1, 15, 12, 3);
        Reading r2 = new Reading(date2, 15);
        LocalDateTime date3 = LocalDateTime.of(2019, 1, 17, 12, 5);
        Reading r3 = new Reading(date3, 33);
        LocalDateTime date4 = LocalDateTime.of(2019, 1, 17, 12, 6);
        Reading r4 = new Reading(date4, 22.08);
        LocalDateTime date5 = LocalDateTime.of(2019, 1, 14, 12, 10);
        Reading r5 = new Reading(date5, -15);
        LocalDateTime date6 = LocalDateTime.of(2019, 1, 14, 12, 15);
        Reading r6 = new Reading(date6, -2);
        ListOfReadings lRead1 = new ListOfReadings();
        lRead1.addReading(r1);
        lRead1.addReading(r2);
        lRead1.addReading(r3);
        lRead1.addReading(r4);
        lRead1.addReading(r5);
        lRead1.addReading(r6);
        int cat = 0;
        String option = "HIGHER";
        List<Double> listHouseAreaTemp = new ArrayList<>();
        listHouseAreaTemp.add(15.0);
        listHouseAreaTemp.add(20.0);
        listHouseAreaTemp.add(25.0);
        listHouseAreaTemp.add(30.0);
        s1.setListOfReadings(lRead1);
        List<LocalDateTime> expectedResult = new ArrayList<>();

        //ACT
        List<LocalDateTime> result = s1.getInstancesWithTemperatureHigherLowerComfort(listHouseAreaTemp, cat, option, startDate);

        //ASSERT
        assertEquals(Collections.emptyList(), result);
    }

}





