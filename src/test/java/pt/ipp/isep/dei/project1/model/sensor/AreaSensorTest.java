package pt.ipp.isep.dei.project1.model.sensor;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import pt.ipp.isep.dei.project1.model.geographicarea.GeographicArea;
import pt.ipp.isep.dei.project1.model.geographicarea.GeographicAreaType;
import pt.ipp.isep.dei.project1.model.geographicarea.Location;
import pt.ipp.isep.dei.project1.model.geographicarea.OccupationArea;
import pt.ipp.isep.dei.project1.model.house.Address;
import pt.ipp.isep.dei.project1.model.house.House;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class AreaSensorTest {




    @Test
    public void equalsTrueTest() {
        //ARRANGE
        AreaSensor s1 = new AreaSensor();
        s1.setIdOfAreaSensor("Sensor1");
        Object obj = new AreaSensor();
        ((AreaSensor) obj).setIdOfAreaSensor("Sensor1");
        //ACT
        boolean result = s1.equals(obj);
        //ASSERT
        assertTrue(result);
    }


    @Test
    public void equalsFalseTest1() {
        //ARRANGE
        AreaSensor s1 = new AreaSensor();
        s1.setName("Sensor1");
        Object obj = new Object();
        //ACT
        boolean result = s1.equals(obj);
        //ASSERT
        assertFalse(result);
    }


    @Test
    public void getSensorUnit() {
        //ARRANGE
        AreaSensor s1 = new AreaSensor();
        s1.setUnit("ºC");
        //ACT
        String result = s1.getUnit();
        //ASSERT
        assertEquals("ºC", result);
    }


    @Test
    public void equalsTrueTestMyself() {
        //ARRANGE
        AreaSensor s1 = new AreaSensor();
        s1.setName("Porto");
        Object obj = s1;
        boolean result = s1.equals(obj);
        //ASSERT
        assertTrue(result);
    }


    @Test
    public void equalsFalseTest() {
        //ARRANGE
        AreaSensor s1 = new AreaSensor();
        s1.setIdOfAreaSensor("Sensor1");
        Object obj = new AreaSensor();
        ((AreaSensor) obj).setIdOfAreaSensor("Sensor2");
        //ACT
        boolean result = s1.equals(obj);
        //ASSERT
        assertFalse(result);
    }

    @Test
    public void equalsFalseTestOtherClass() {
        //ARRANGE
        AreaSensor s1 = new AreaSensor();
        s1.setName("sensor");
        Object obj = new SensorType("temperature");
        //ACT
        boolean result = s1.equals(obj);
        //ASSERT
        assertFalse(result);
    }

    @Test
    public void hashCodeturntest() {
        //ARRANGE
        AreaSensor s1 = new AreaSensor();
        s1.setName("Sensor1");
        Object obj = new AreaSensor();
        ((AreaSensor) obj).setName("Sensor1");
        //ACT
        int result = s1.getName().charAt(0);
        //ASSERT
        assertEquals(s1.hashCode(), result);
    }


    @Test
    public void createHouseNonValidNameNull() throws Exception {
        try {
            AreaSensor origin = new AreaSensor("","sensor1", new Location(40.7486, -73.9864, 0), new SensorType("temperature"), LocalDate.of(2018, 12, 10), "ºC");
            fail("Fail. An exception must be thrown");
        } catch (RuntimeException e) {
            assertEquals("Insert a valid name!", e.getMessage());
        }
    }

    @Test
    public void createHouseNonValidNameEmpty() throws Exception {
        try {
            AreaSensor origin = new AreaSensor(null,"sensor1", new Location(40.7486, -73.9864, 0), new SensorType("temperature"), LocalDate.of(2018, 12, 10), "ºC");
            fail("Fail. An exception must be thrown");
        } catch (RuntimeException e) {
            assertEquals("Insert a valid name!", e.getMessage());
        }
    }

    @Test
    void testCalculateDistanceBetweenSensorToHouse() throws Exception {
        //ARRANGE
        GeographicArea geographicArea = new GeographicArea("Porto", "City", new OccupationArea
                (new Location(10, 20, 30), 0.5, 0.5),
                new GeographicAreaType("City"));
        House h1 = new House("Casa", new Location(20, -30, 0), geographicArea.getName());
        AreaSensor origin = new AreaSensor("1","sensor1", new Location(40.7486, -73.9864, 0), new SensorType("temperature"), LocalDate.of(2018, 12, 10), "ºC");
        double expectedResult = 4742.98854;
        double result;
        //ACT
        result = origin.calculateDistanceBetweenSensorToHouse(h1.getLocationOfHouse());
        //ASSERT
        assertEquals(expectedResult, result, 1);
    }


    @Test
    void testGetDistanceBetween2Sensors() {
        //ARRANGE
        AreaSensor origin = new AreaSensor("1","sensor1", new Location(40.7486, -73.9864, 0), new SensorType("temperature"), LocalDate.of(2018, 12, 10), "ºC");
        AreaSensor destination = new AreaSensor("1","sensor2", new Location(41.7486, -73.9864, 0), new SensorType("temperature"), LocalDate.of(2018, 12, 10), "ºC");
        double expectedResult = 111.2;
        double result;
        //ACT
        result = origin.getDistanceBetween2Sensors(destination);
        //ASSERT
        assertEquals(expectedResult, result, 1);
    }

    @Test
    void testGetDistanceBetween2SensorsEquals() {
        //ARRANGE
        AreaSensor origin = new AreaSensor("1","sensor1", new Location(40.7486, -73.9864, 0), new SensorType("temperature"), LocalDate.of(2018, 12, 10), "ºC");
        AreaSensor destination = new AreaSensor("1","sensor2", new Location(40.7486, -73.9864, 0), new SensorType("temperature"), LocalDate.of(2018, 12, 10), "ºC");
        double expectedResult = 0;
        double result;
        //ACT
        result = origin.getDistanceBetween2Sensors(destination);
        //ASSERT
        assertEquals(expectedResult, result, 1);
    }

    @Test
    void getAverageRainfallIntervalWithoutReadings() {
        GeographicArea GA1 = new GeographicArea("Porto", "city", new OccupationArea(new Location(10, 20, 30), 0.5, 0.5), new GeographicAreaType("city"));
        AreaSensor s1 = new AreaSensor("1","sensor1", new Location(40.7486, -73.9864, 0), new SensorType("rainfall"), LocalDate.of(2018, 12, 10), "ºC");
        ListOfReadings lisOfReading = new ListOfReadings();
        s1.setListOfReadings(lisOfReading);
        GA1.addSensorToList(s1);
        GA1.setListOfAreaSensors(GA1.getListOfAreaSensors());

        double result = s1.getAverageInOneInterval(LocalDate.of(2019, 1, 2), LocalDate.of(2019, 1, 1));

        assertEquals(-1, result, 0.00001);
    }

    @Test
    void getAverageRainfallIntervalReadingsInOtherDates() {
        GeographicArea GA1 = new GeographicArea("Porto", "city", new OccupationArea(new Location(10, 20, 30), 0.5, 0.5), new GeographicAreaType("city"));
        AreaSensor s1 = new AreaSensor("1","sensor1", new Location(40.7486, -73.9864, 0), new SensorType("rainfall"), LocalDate.of(2018, 12, 10), "ºC");
        LocalDateTime date1 = LocalDateTime.of(2018, 01, 02, 12, 30);
        LocalDateTime date2 = LocalDateTime.of(2018, 01, 03, 12, 31);
        ListOfReadings lisOfReading = new ListOfReadings();
        lisOfReading.addReading(new Reading(date1, 12.0));
        lisOfReading.addReading(new Reading(date2, 13.0));
        s1.setListOfReadings(lisOfReading);
        GA1.addSensorToList(s1);
        GA1.setListOfAreaSensors(GA1.getListOfAreaSensors());

        double result = s1.getAverageInOneInterval(LocalDate.of(2019, 1, 2), LocalDate.of(2019, 1, 1));

        assertEquals(-1, result, 0.00001);
    }


    @Test
    void checkIfGetSumOfRainfallInCertainDay() {

        AreaSensor s1 = new AreaSensor("1","sensor Teste", new Location(40.7486, -73.9864, 0), new SensorType("rainfall"), LocalDate.of(2018, 12, 10), "l/m2");
        LocalDateTime date1 = LocalDateTime.of(2018, 12, 25, 11, 30);
        LocalDateTime date2 = LocalDateTime.of(2018, 12, 25, 13, 31);
        LocalDateTime date3 = LocalDateTime.of(2018, 12, 25, 22, 0);
        ListOfReadings listOfReading = new ListOfReadings();
        listOfReading.addReading(new Reading(date1, 12.0));
        listOfReading.addReading(new Reading(date2, 13.0));
        listOfReading.addReading(new Reading(date3, 12.5));
        s1.setListOfReadings(listOfReading);

        double result = s1.getSumOfRainfallInCertainDay(s1, LocalDate.of(2018, 12, 25));
        assertEquals((12 + 13 + 12.5), result, 0.0001);
    }

    @Test
    void checkIfGetSumOfRainfallInCertainDayIfNoReadingsOnThatDay() {

        AreaSensor s1 = new AreaSensor("1","sensor Teste", new Location(40.7486, -73.9864, 0), new SensorType("rainfall"), LocalDate.of(2018, 12, 10), "l/m2");
        LocalDateTime date1 = LocalDateTime.of(2018, 12, 25, 11, 30);
        LocalDateTime date2 = LocalDateTime.of(2018, 12, 25, 13, 31);
        LocalDateTime date3 = LocalDateTime.of(2018, 12, 25, 22, 0);
        ListOfReadings listOfReading = new ListOfReadings();
        listOfReading.addReading(new Reading(date1, 12.0));
        listOfReading.addReading(new Reading(date2, 13.0));
        listOfReading.addReading(new Reading(date3, 12.5));
        s1.setListOfReadings(listOfReading);

        double result = s1.getSumOfRainfallInCertainDay(s1, LocalDate.of(2018, 12, 26));
        assertEquals((0), result, 0.0001);
    }

    @Test
    void getAverageInOneDay() {
        AreaSensor s1 = new AreaSensor("1","sensor Teste", new Location(40.7486, -73.9864, 0), new SensorType("rainfall"), LocalDate.of(2018, 12, 10), "l/m2");
        LocalDateTime date1 = LocalDateTime.of(2018, 12, 26, 11, 30);
        LocalDateTime date2 = LocalDateTime.of(2018, 12, 27, 13, 31);
        LocalDateTime date3 = LocalDateTime.of(2018, 12, 28, 22, 0);
        ListOfReadings listOfReading = new ListOfReadings();
        listOfReading.addReading(new Reading(date1, 12.0));
        listOfReading.addReading(new Reading(date2, 13.0));
        listOfReading.addReading(new Reading(date2, 15.0));
        listOfReading.addReading(new Reading(date2, 18.0));
        listOfReading.addReading(new Reading(date3, 12.5));
        s1.setListOfReadings(listOfReading);

        double result = s1.getAverageInOneDay(date2.toLocalDate());
        assertEquals(15, Math.round(result));

    }

    @Test
    void getAverageInOneDayNoReadings() {
        AreaSensor s1 = new AreaSensor("1","sensor Teste", new Location(40.7486, -73.9864, 0), new SensorType("rainfall"), LocalDate.of(2018, 12, 10), "l/m2");
        LocalDateTime date1 = LocalDateTime.of(2018, 12, 26, 11, 30);
        LocalDateTime date2 = LocalDateTime.of(2018, 12, 27, 13, 31);
        LocalDateTime date3 = LocalDateTime.of(2018, 12, 28, 22, 0);
        ListOfReadings listOfReading = new ListOfReadings();
        listOfReading.addReading(new Reading(date1, 12.0));
        listOfReading.addReading(new Reading(date3, 13.0));
        listOfReading.addReading(new Reading(date3, 15.0));
        listOfReading.addReading(new Reading(date3, 18.0));
        listOfReading.addReading(new Reading(date3, 12.5));
        s1.setListOfReadings(listOfReading);

        double result = s1.getAverageInOneDay(date2.toLocalDate());
        assertEquals(0, Math.round(result));
    }

    @Test
    void checkIfGetInstallationDate() {

        LocalDate dateTest = LocalDate.of(2018, 12, 10);

        AreaSensor s1 = new AreaSensor("1","sensor Teste", new Location(40.7486, -73.9864, 0), new SensorType("rainfall"), dateTest, "l/m2");

        LocalDate result = s1.getInstallationDate();

        LocalDate expectedResult = dateTest;

        assertEquals(expectedResult, result);

    }


    @Test
    void testGetFirstHottestDayInGivenPeriod() {

        AreaSensor areaSensor = new AreaSensor("1","Test AreaSensor", new Location(40.7486, -73.9864, 0),
                new SensorType("temperature"), LocalDate.of(2018, 12, 10), "l/m2");
        ListOfReadings listOfReadings = new ListOfReadings();
        LocalDateTime date1 = LocalDateTime.of(2019, 1, 16, 12, 00);
        Reading r1 = new Reading(date1, 4);
        LocalDateTime date2 = LocalDateTime.of(2019, 1, 19, 12, 00);
        Reading r2 = new Reading(date2, 1.9);
        LocalDateTime date3 = LocalDateTime.of(2019, 1, 21, 12, 5);
        Reading r3 = new Reading(date3, 1.3);
        LocalDateTime date4 = LocalDateTime.of(2019, 1, 21, 12, 10);
        Reading r4 = new Reading(date4, -2);
        LocalDateTime date5 = LocalDateTime.of(2019, 1, 22, 12, 10);
        Reading r5 = new Reading(date5, 2);
        LocalDateTime date6 = LocalDateTime.of(2019, 1, 22, 12, 10);
        Reading r6 = new Reading(date6, 1.5);


        listOfReadings.addReading(r1);
        listOfReadings.addReading(r2);
        listOfReadings.addReading(r3);
        listOfReadings.addReading(r4);
        listOfReadings.addReading(r5);
        listOfReadings.addReading(r6);

        areaSensor.setListOfReadings(listOfReadings);

        Reading expectedResult = r1;

        Reading result = areaSensor.getFirstHottestDayInGivenPeriod(date1.toLocalDate(), date1.toLocalDate());

        assertEquals(expectedResult, result);

    }

    @Test
    void testGetLastColdestDayInGivenPeriod() {

        AreaSensor s1 = new AreaSensor("1","sensor Teste", new Location(40.7486, -73.9864, 0), new SensorType("rainfall"), LocalDate.of(2018, 12, 10), "l/m2");
        ListOfReadings lRead1 = new ListOfReadings();
        LocalDateTime date1 = LocalDateTime.of(2019, 1, 16, 12, 00);
        Reading r1 = new Reading(date1, 4);
        LocalDateTime date2 = LocalDateTime.of(2019, 1, 19, 12, 00);
        Reading r2 = new Reading(date2, 3);
        LocalDateTime date3 = LocalDateTime.of(2019, 1, 21, 12, 5);
        Reading r3 = new Reading(date3, 1);
        LocalDateTime date4 = LocalDateTime.of(2019, 1, 21, 12, 10);
        Reading r4 = new Reading(date4, -2);
        LocalDateTime date5 = LocalDateTime.of(2019, 1, 22, 12, 10);
        Reading r5 = new Reading(date5, 2);
        LocalDateTime date6 = LocalDateTime.of(2019, 1, 22, 12, 10);
        Reading r6 = new Reading(date6, 2);


        lRead1.addReading(r1);
        lRead1.addReading(r2);
        lRead1.addReading(r3);
        lRead1.addReading(r4);
        lRead1.addReading(r5);
        lRead1.addReading(r6);

        s1.setListOfReadings(lRead1);

        Reading expectedResult = r3;

        Reading result = s1.getLastColdestDayInGivenPeriod(date1.toLocalDate(), date6.toLocalDate());

        assertEquals(expectedResult, result);

    }

    @Test
    void testGetFirstHottestDayInGivenPeriodEmptyPeriod() {

        AreaSensor areaSensor = new AreaSensor("1","Test AreaSensor", new Location(40.7486, -73.9864, 0),
                new SensorType("rainfall"), LocalDate.of(2018, 12, 10), "l/m2");
        ListOfReadings listOfReadings = new ListOfReadings();
        LocalDateTime date1 = LocalDateTime.of(2019, 1, 16, 12, 00);
        Reading r1 = new Reading(date1, 4);
        LocalDateTime date2 = LocalDateTime.of(2019, 1, 19, 12, 00);
        Reading r2 = new Reading(date2, 1.9);
        LocalDateTime date3 = LocalDateTime.of(2019, 1, 21, 12, 5);
        Reading r3 = new Reading(date3, 1.3);
        LocalDateTime date4 = LocalDateTime.of(2019, 1, 21, 12, 10);
        Reading r4 = new Reading(date4, -2);
        LocalDateTime date5 = LocalDateTime.of(2019, 1, 22, 12, 10);
        Reading r5 = new Reading(date5, 2);
        LocalDateTime date6 = LocalDateTime.of(2019, 1, 22, 12, 10);
        Reading r6 = new Reading(date6, 1.5);
        LocalDateTime date7 = LocalDateTime.of(2019, 1, 10, 12, 10);
        LocalDateTime date8 = LocalDateTime.of(2019, 1, 15, 12, 10);


        listOfReadings.addReading(r1);
        listOfReadings.addReading(r2);
        listOfReadings.addReading(r3);
        listOfReadings.addReading(r4);
        listOfReadings.addReading(r5);
        listOfReadings.addReading(r6);

        areaSensor.setListOfReadings(listOfReadings);

        Reading expectedResult = null;

        Reading result = areaSensor.getFirstHottestDayInGivenPeriod(date7.toLocalDate(), date8.toLocalDate());

        assertEquals(expectedResult, result);

    }

    @Test
    void testGetLastColdestDayInGivenPeriodEmptyPeriod() {

        AreaSensor s1 = new AreaSensor("1","sensor Teste", new Location(40.7486, -73.9864, 0),
                new SensorType("rainfall"), LocalDate.of(2018, 12, 10), "l/m2");
        ListOfReadings lRead1 = new ListOfReadings();
        LocalDateTime date1 = LocalDateTime.of(2019, 1, 16, 12, 00);
        Reading r1 = new Reading(date1, 4);
        LocalDateTime date2 = LocalDateTime.of(2019, 1, 19, 12, 00);
        Reading r2 = new Reading(date2, 1.9);
        LocalDateTime date3 = LocalDateTime.of(2019, 1, 21, 12, 5);
        Reading r3 = new Reading(date3, 1.3);
        LocalDateTime date4 = LocalDateTime.of(2019, 1, 21, 12, 10);
        Reading r4 = new Reading(date4, -2);
        LocalDateTime date5 = LocalDateTime.of(2019, 1, 22, 12, 10);
        Reading r5 = new Reading(date5, 2);
        LocalDateTime date6 = LocalDateTime.of(2019, 1, 22, 12, 10);
        Reading r6 = new Reading(date6, 1.5);
        LocalDateTime date7 = LocalDateTime.of(2019, 1, 10, 12, 10);
        LocalDateTime date8 = LocalDateTime.of(2019, 1, 15, 12, 10);


        lRead1.addReading(r1);
        lRead1.addReading(r2);
        lRead1.addReading(r3);
        lRead1.addReading(r4);
        lRead1.addReading(r5);
        lRead1.addReading(r6);

        s1.setListOfReadings(lRead1);

        Reading expectedResult = null;

        Reading result = s1.getLastColdestDayInGivenPeriod(date7.toLocalDate(), date8.toLocalDate());

        assertEquals(expectedResult, result);

    }


    @Test
    void testGetFirstHottestDayInGivenPeriodMultiplesDatesInDay() {

        AreaSensor areaSensor = new AreaSensor("1","Test AreaSensor", new Location(40.7486, -73.9864, 0),
                new SensorType("temperature"), LocalDate.of(2018, 12, 10), "l/m2");
        ListOfReadings listOfReadings = new ListOfReadings();
        LocalDateTime date1 = LocalDateTime.of(2019, 1, 16, 12, 00);
        Reading r1 = new Reading(date1, 9);
        LocalDateTime date2 = LocalDateTime.of(2019, 1, 19, 12, 00);
        Reading r2 = new Reading(date2, 8);
        LocalDateTime date3 = LocalDateTime.of(2019, 1, 21, 12, 5);
        Reading r3 = new Reading(date3, 1);
        LocalDateTime date4 = LocalDateTime.of(2019, 1, 21, 12, 10);
        Reading r4 = new Reading(date4, 6);
        LocalDateTime date5 = LocalDateTime.of(2019, 1, 21, 12, 10);
        Reading r5 = new Reading(date5, -2);
        LocalDateTime date6 = LocalDateTime.of(2019, 1, 21, 12, 10);
        Reading r6 = new Reading(date6, 5);
        LocalDateTime date7 = LocalDateTime.of(2019, 1, 21, 12, 10);
        Reading r7 = new Reading(date7, 3);
        LocalDateTime date8 = LocalDateTime.of(2019, 1, 22, 12, 10);
        Reading r8 = new Reading(date8, 7);
        LocalDateTime date9 = LocalDateTime.of(2019, 1, 22, 12, 10);
        Reading r9 = new Reading(date9, 8);


        listOfReadings.addReading(r1);
        listOfReadings.addReading(r2);
        listOfReadings.addReading(r3);
        listOfReadings.addReading(r4);
        listOfReadings.addReading(r5);
        listOfReadings.addReading(r6);
        listOfReadings.addReading(r7);
        listOfReadings.addReading(r8);
        listOfReadings.addReading(r9);


        areaSensor.setListOfReadings(listOfReadings);

        Reading expectedResult = r1;

        Reading result = areaSensor.getFirstHottestDayInGivenPeriod(date1.toLocalDate(), date9.toLocalDate());

        assertEquals(expectedResult, result);

    }


    @Test
    void testGetLastColdestDayInGivenPeriodMultiplesDatesInDay() {

        AreaSensor s1 = new AreaSensor("1","sensor Teste", new Location(40.7486, -73.9864, 0),
                new SensorType("temperature"), LocalDate.of(2018, 12, 10), "l/m2");
        ListOfReadings lRead1 = new ListOfReadings();
        LocalDateTime date1 = LocalDateTime.of(2019, 1, 16, 12, 00);
        Reading r1 = new Reading(date1, 9);
        LocalDateTime date2 = LocalDateTime.of(2019, 1, 19, 12, 00);
        Reading r2 = new Reading(date2, 8);
        LocalDateTime date3 = LocalDateTime.of(2019, 1, 21, 12, 5);
        Reading r3 = new Reading(date3, 1);
        LocalDateTime date4 = LocalDateTime.of(2019, 1, 21, 12, 10);
        Reading r4 = new Reading(date4, 6);
        LocalDateTime date5 = LocalDateTime.of(2019, 1, 21, 12, 10);
        Reading r5 = new Reading(date5, -2);
        LocalDateTime date6 = LocalDateTime.of(2019, 1, 21, 12, 10);
        Reading r6 = new Reading(date6, 5);
        LocalDateTime date7 = LocalDateTime.of(2019, 1, 21, 12, 10);
        Reading r7 = new Reading(date7, 3);
        LocalDateTime date8 = LocalDateTime.of(2019, 1, 22, 12, 10);
        Reading r8 = new Reading(date8, 7);
        LocalDateTime date9 = LocalDateTime.of(2019, 1, 22, 12, 10);
        Reading r9 = new Reading(date9, 8);


        lRead1.addReading(r1);
        lRead1.addReading(r2);
        lRead1.addReading(r3);
        lRead1.addReading(r4);
        lRead1.addReading(r5);
        lRead1.addReading(r6);
        lRead1.addReading(r7);
        lRead1.addReading(r8);
        lRead1.addReading(r9);


        s1.setListOfReadings(lRead1);

        Reading expectedResult = r4;

        Reading result = s1.getLastColdestDayInGivenPeriod(date1.toLocalDate(), date9.toLocalDate());

        assertEquals(expectedResult, result);

    }

    @Test
    public void testToString() {
        AreaSensor s1 = new AreaSensor("1","sensor Teste", new Location(40.7486, -73.9864, 0),
                new SensorType("temperature"), LocalDate.of(2018, 12, 10), "l/m2");
        String expectedResut = "\n IdLocal: "+ s1.getIdOfAreaSensor() +"\n Name: "+s1.getName()+"\n Location: "+s1.getLocation()+"\n AreaSensor Type: "+ s1.getSensorType().getType()+"\n " +
                "Installation Date: "+s1.getInstallationDate()+"\n Units: "+s1.getUnit()+"\n Readings: " +s1.getListOfReadings()+"\n";
        String result = s1.toString();
        assertEquals(expectedResut,result);
    }


    @Test
    public void testAddReading() {
        AreaSensor s1 = new AreaSensor("1","sensor Teste", new Location(40.7486, -73.9864, 0),
                new SensorType("temperature"), LocalDate.of(2018, 12, 10), "l/m2");
        LocalDateTime date1 = LocalDateTime.of(2019, 1, 16, 12, 00);
        Reading r1 = new Reading(date1, 9);
        s1.addReading(r1);
        Reading result = s1.getListOfReadings().getListOfReading().get(0);
        Reading expectedResut = r1;

        assertEquals(expectedResut,result);
    }

    @Test
    public void testAddReadingMultiples() {
        AreaSensor s1 = new AreaSensor("1","sensor Teste", new Location(40.7486, -73.9864, 0),
                new SensorType("temperature"), LocalDate.of(2018, 12, 10), "l/m2");
        LocalDateTime date1 = LocalDateTime.of(2019, 1, 16, 12, 00);
        Reading r1 = new Reading(date1, 9);
        Reading r2 = new Reading(date1, 20);
        s1.addReading(r1);
        s1.addReading(r2);
        Reading result = s1.getListOfReadings().getListOfReading().get(0);
        Reading expectedResut = r2;

        assertEquals(expectedResut,result);
    }

}
