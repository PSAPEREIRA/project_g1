package pt.ipp.isep.dei.project1.model.geographicarea;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import pt.ipp.isep.dei.project1.mapper.MapperAreaSensor;
import pt.ipp.isep.dei.project1.model.sensor.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class GeographicAreaTest {


    @Test
    public void equalsTrueTest() {
        //ARRANGE
        GeographicArea geo1 = new GeographicArea();
        geo1.setName("porto");
        Object obj = new GeographicArea("Porto", "city",
                new OccupationArea(new Location(10, 20, 30), 0.5, 0.5),
                new GeographicAreaType("city"));
        ((GeographicArea) obj).setName("porto");
        //ACT
        boolean result = geo1.equals(obj);
        //ASSERT
        assertTrue(result);
    }

    @Test
    public void equalsTrueTestMyself() {
        //ARRANGE
        GeographicArea geo1 = new GeographicArea("Porto", "city",
                new OccupationArea(new Location(10, 20, 30), 0.5, 0.5),
                new GeographicAreaType("city"));
        geo1.setName("Porto");
        Object obj = geo1;
        boolean result = geo1.equals(obj);
        //ASSERT
        assertTrue(result);
    }

    @Test
    public void equalsFalseTest() {
        //ARRANGE
        GeographicArea geo1 = new GeographicArea("Porto", "city",
                new OccupationArea(new Location(10, 20, 30), 0.5, 0.5),
                new GeographicAreaType("city"));
        geo1.setName("Porto");
        Object obj = new GeographicArea("Porto", "city",
                new OccupationArea(new Location(10, 20, 30), 0.5, 0.5),
                new GeographicAreaType("city"));
        ((GeographicArea) obj).setName("Braga");
        //ACT
        boolean result = geo1.equals(obj);
        //ASSERT
        assertFalse(result);
    }

    @Test
    public void equalsFalseTestOtherClass() {
        //ARRANGE
        GeographicArea geo1 = new GeographicArea("Porto", "city",
                new OccupationArea(new Location(10, 20, 30), 0.5, 0.5),
                new GeographicAreaType("city"));
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
        GeographicArea geo1 = new GeographicArea("Porto", "city",
                new OccupationArea(new Location(10, 20, 30), 0.5, 0.5),
                new GeographicAreaType("city"));
        geo1.setName("Porto");
        Object obj = new GeographicArea("Porto", "city",
                new OccupationArea(new Location(10, 20, 30), 0.5, 0.5),
                new GeographicAreaType("city"));
        ((GeographicArea) obj).setName("Porto");
        //ACT
        int result = geo1.getName().charAt(0);
        //ASSERT
        assertEquals(geo1.hashCode(), result);
    }

    @Test
    void testGetDescription() {

        //ARRANGE
        GeographicArea newAg01 = new GeographicArea("Porto", "street",
                new OccupationArea(new Location(10, 20, 30), 0.5, 0.5),
                new GeographicAreaType("city"));
        newAg01.setDescription("city");
        //ACT
        String result = newAg01.getDescription();
        //ASSERT
        assertEquals("city", result);
    }

    @Test
    public void testToString() {
        GeographicArea newAg01 = new GeographicArea("Porto", "street",
                new OccupationArea(new Location(10, 20, 30), 0.5, 0.5),
                new GeographicAreaType("city"));
        String expectedResult = "Geographic Area{" +
                ", Name: " + newAg01.getName() + "\n" +
                ", Description: " + newAg01.getDescription() + "\n" +
                ", Area of Occupation: " + newAg01.getOccupationArea() + "\n" +
                ", Geographic Area Type: " + newAg01.getGeographicAreaType() + "\n" +
                ", List of Sensors: " + newAg01.getListOfAreaSensors() + "\n" +
                //", Belongs inside: " + newAg01.getListInsideOf() + "\n" +
                '}';
        String result = newAg01.toString();
        assertEquals(expectedResult, result);
    }

    @Test
    void addAgInside() {

        //ARRANGE
        List<GeographicArea> mListInsideOf = new ArrayList<>();
        GeographicArea newAg01 = new GeographicArea("Porto", "city",
                new OccupationArea(new Location(10, 20, 30), 0.5, 0.5),
                new GeographicAreaType("city"));
        GeographicArea newAg03 = new GeographicArea("Portugal", "city",
                new OccupationArea(new Location(10, 20, 30), 0.5, 0.5),
                new GeographicAreaType("pais"));
        mListInsideOf.add(newAg03);
        newAg01.setListInsideOf(mListInsideOf);
        //ACT
        List<GeographicArea> result = newAg01.getListInsideOf();
        //ASSERT
        assertEquals(mListInsideOf, result);
    }

    @Test
    void getAverageRainfallInOneAGBeforeInstallationDate() {
        GeographicArea GA1 = new GeographicArea("Porto", "city",
                new OccupationArea(new Location(10, 20, 30), 0.5, 0.5),
                new GeographicAreaType("city"));
        AreaSensor s1 = new AreaSensor("1", "sensor1", new Location(40.7486, -73.9864, 0),
                new SensorType("rainfall"), LocalDate.of(2019, 01, 05), "ºC");
        LocalDateTime date1 = LocalDateTime.of(2019, 01, 02, 12, 30);
        LocalDateTime date2 = LocalDateTime.of(2019, 01, 03, 12, 31);
        ListOfReadings lisOfReading = new ListOfReadings();
        lisOfReading.addReading(new Reading(date1, 12.0));
        lisOfReading.addReading(new Reading(date2, 13.0));
        s1.setListOfReadings(lisOfReading);
        GA1.addSensorToList(s1);

        double result = GA1.getAverageRainfallInOneAG(LocalDate.of(2019, 1, 2),
                LocalDate.of(2019, 1, 3),new SensorType("rainfall"));

        assertEquals(-1, result, 0.00001);
    }

    @Test
    void getAverageRainfallInOneAGWithSameType() {
        GeographicArea GA1 = new GeographicArea("Porto", "city",
                new OccupationArea(new Location(10, 20, 30), 0.5, 0.5),
                new GeographicAreaType("city"));
        AreaSensor s1 = new AreaSensor("1", "sensor1", new Location(40.7486, -73.9864, 0),
                new SensorType("rainfall"), LocalDate.of(2019, 01, 05), "ºC");
        AreaSensor s2 = new AreaSensor("1", "sensor2", new Location(40.7486, -73.9864, 0),
                new SensorType("rainfall"), LocalDate.of(2019, 01, 05), "ºC");
        LocalDateTime date1 = LocalDateTime.of(2019, 01, 02, 12, 30);
        LocalDateTime date2 = LocalDateTime.of(2019, 01, 03, 12, 31);
        ListOfReadings lisOfReading = new ListOfReadings();
        lisOfReading.addReading(new Reading(date1, 12.0));
        lisOfReading.addReading(new Reading(date2, 13.0));
        s2.setListOfReadings(lisOfReading);
        GA1.addSensorToList(s1);
        GA1.addSensorToList(s2);
        double result = GA1.getAverageRainfallInOneAG(LocalDate.of(2019, 1, 2),
                LocalDate.of(2019, 1, 3),new SensorType("rainfall"));

        assertEquals(-1, result, 0.00001);
    }

    @Test
    void getAverageRainfallInOneAGWithSameType1() {
        GeographicArea GA1 = new GeographicArea("Porto", "city",
                new OccupationArea(new Location(10, 20, 30), 0.5, 0.5),
                new GeographicAreaType("city"));
        AreaSensor s1 = new AreaSensor("1", "sensor1", new Location(40.7486, -73.9864, 0),
                new SensorType("rainfall"), LocalDate.of(2019, 01, 05), "ºC");
        AreaSensor s2 = new AreaSensor("1", "sensor2", new Location(40.7486, -73.9864, 0),
                new SensorType("rainfall"), LocalDate.of(2019, 01, 05), "ºC");
        LocalDateTime date1 = LocalDateTime.of(2019, 01, 02, 12, 30);
        LocalDateTime date2 = LocalDateTime.of(2019, 01, 03, 12, 31);
        ListOfReadings lisOfReading = new ListOfReadings();
        lisOfReading.addReading(new Reading(date1, 12.0));
        lisOfReading.addReading(new Reading(date2, 13.0));
        s2.setListOfReadings(lisOfReading);
        GeographicArea geographicArea = new GeographicArea();
        GA1.addSensorToList(s1);
        GA1.addSensorToList(s2);
        double result = GA1.getAverageRainfallInOneAG(LocalDate.of(2019, 1, 2),
                LocalDate.of(2019, 1, 3),new SensorType("rainfall"));

        assertEquals(-1, result, 0.00001);
    }


    @Test
    void getAverageRainfallInOneAGWrongType() {
        GeographicArea GA1 = new GeographicArea("Porto", "city",
                new OccupationArea(new Location(10, 20, 30), 0.5, 0.5),
                new GeographicAreaType("city"));
        AreaSensor s1 = new AreaSensor("1", "sensor1", new Location(40.7486, -73.9864, 0),
                new SensorType("temperature"), LocalDate.of(2018, 12, 05), "ºC");
        LocalDateTime date1 = LocalDateTime.of(2019, 01, 02, 12, 30);
        LocalDateTime date2 = LocalDateTime.of(2019, 01, 03, 12, 31);
        ListOfReadings lisOfReading = new ListOfReadings();
        lisOfReading.addReading(new Reading(date1, 12.0));
        lisOfReading.addReading(new Reading(date2, 13.0));
        s1.setListOfReadings(lisOfReading);
        GA1.addSensorToList(s1);
        double result = GA1.getAverageRainfallInOneAG(LocalDate.of(2019, 1, 1),
                LocalDate.of(2019, 1, 1),new SensorType("rainfall"));

        assertEquals(-1, result, 0.00001);
    }

    @Test
    void getAverageRainfallInOneAGWrongTypeAndWrongDate() {
        GeographicArea GA1 = new GeographicArea("Porto", "city",
                new OccupationArea(new Location(10, 20, 30), 0.5, 0.5),
                new GeographicAreaType("city"));
        AreaSensor s1 = new AreaSensor("1", "sensor1", new Location(40.7486, -73.9864, 0),
                new SensorType("temperature"), LocalDate.of(2019, 12, 05), "ºC");
        LocalDateTime date1 = LocalDateTime.of(2019, 01, 02, 12, 30);
        LocalDateTime date2 = LocalDateTime.of(2019, 01, 03, 12, 31);
        ListOfReadings lisOfReading = new ListOfReadings();
        lisOfReading.addReading(new Reading(date1, 12.0));
        lisOfReading.addReading(new Reading(date2, 13.0));
        s1.setListOfReadings(lisOfReading);
        GA1.addSensorToList(s1);
        double result = GA1.getAverageRainfallInOneAG(LocalDate.of(2019, 1, 1),
                LocalDate.of(2019, 1, 1),new SensorType("rainfall"));

        assertEquals(-1, result, 0.00001);
    }

    @Test
    void getAverageRainfallInOneAgWrongDate() {
        GeographicArea ga1 = new GeographicArea("Porto", "city", new OccupationArea(new Location(10, 20, 30), 0.5, 0.5), new GeographicAreaType("city"));
        AreaSensor s1 = new AreaSensor("1", "s1", new Location(40.7486, -73.9864, 0), new SensorType("rainfall"), LocalDate.of(2019, 12, 10), "ºC");

        LocalDateTime date1 = LocalDateTime.of(2019, 01, 02, 12, 30);
        LocalDateTime date2 = LocalDateTime.of(2019, 01, 03, 12, 31);

        ListOfReadings lisOfReading = new ListOfReadings();
        lisOfReading.addReading(new Reading(date1, 12.0));
        lisOfReading.addReading(new Reading(date2, 13.0));

        s1.setListOfReadings(lisOfReading);
        ga1.addSensorToList(s1);


        double result = ga1.getAverageRainfallInOneAG(LocalDate.of(2019, 1, 2), LocalDate.of(2019, 1, 3),new SensorType("rainfall"));

        assertEquals(-1, result, 0.00001);

    }

    @Test
    void getAverageRainfallInOneAgAllGood() {
        GeographicArea ga1 = new GeographicArea("Porto", "city", new OccupationArea(new Location(10, 20, 30), 0.5, 0.5), new GeographicAreaType("city"));
        AreaSensor s1 = new AreaSensor("1", "s1", new Location(40.7486, -73.9864, 0), new SensorType("rainfall"), LocalDate.of(2018, 12, 10), "ºC");

        LocalDateTime date1 = LocalDateTime.of(2019, 01, 02, 12, 30);
        LocalDateTime date2 = LocalDateTime.of(2019, 01, 03, 12, 31);

        ListOfReadings lisOfReading = new ListOfReadings();
        lisOfReading.addReading(new Reading(date1, 12.0));
        lisOfReading.addReading(new Reading(date2, 13.0));

        s1.setListOfReadings(lisOfReading);
        ga1.addSensorToList(s1);


        double result = ga1.getAverageRainfallInOneAG(LocalDate.of(2019, 1, 2), LocalDate.of(2019, 1, 3),new SensorType("rainfall"));

        assertEquals(12.5, result, 0.00001);

    }


    @Test
    public void createGAValidIdEmpty() {
        try {
            GeographicArea GA1 = new GeographicArea("", "city",
                    new OccupationArea(new Location(10, 20, 30), 0.5, 0.5),
                    new GeographicAreaType("city"));
            fail("Fail. An exception must be thrown");
        } catch (RuntimeException e) {
            assertEquals("Insert a valid name of Geographic Area", e.getMessage());
        }
    }

    @Test
    public void createGAValidIdNull() {
        try {
            GeographicArea GA1 = new GeographicArea(null, "city",
                    new OccupationArea(new Location(10, 20, 30), 0.5, 0.5),
                    new GeographicAreaType("city"));
            fail("Fail. An exception must be thrown");
        } catch (RuntimeException e) {
            assertEquals("Insert a valid name of Geographic Area", e.getMessage());
        }
    }


    @Test
    void testGetSensorFromGA() {
        GeographicArea ga1 = new GeographicArea("Porto", "city", new OccupationArea(new Location(10, 20, 30), 0.5, 0.5), new GeographicAreaType("city"));
        AreaSensor s1 = new AreaSensor("1", "s1", new Location(40.7486, -73.9864, 0), new SensorType("Rainfall"), LocalDate.of(2018, 12, 10), "ºC");
        GeographicArea geographicArea = new GeographicArea();
        ga1.addSensorToList(s1);
        AreaSensor expectedResult = s1;
        AreaSensor result = ga1.gettingSensorFromGeographicArea(0);

        assertEquals(expectedResult, result);

    }

    @Test
    void testGetSensorFromGAEmpty() {
        GeographicArea ga1 = new GeographicArea("Porto", "city", new OccupationArea(new Location(10, 20, 30), 0.5, 0.5), new GeographicAreaType("city"));
        AreaSensor expectedResult = null;
        AreaSensor result = ga1.gettingSensorFromGeographicArea(0);

        assertEquals(expectedResult, result);
    }


    @Test
    void getListOfSensorsOfTheSameType() {
        //ARRANGE
        GeographicArea geographicArea = new GeographicArea("Porto", "City", new OccupationArea
                (new Location(10, 20, 30), 0.5, 0.5),
                new GeographicAreaType("City"));
        SensorType sensorType1 = new SensorType("temperature");
        AreaSensor s1 = new AreaSensor("1", "sensor1", new Location(10, 20, 30),
                sensorType1, LocalDate.of(2018, 12, 10), "ºC");
        AreaSensor s2 = new AreaSensor("2", "sensor2", new Location(12, 20, 30),
                sensorType1, LocalDate.of(2018, 12, 10), "ºC");
        AreaSensor s3 = new AreaSensor("3", "sensor3", new Location(14, 20, 30),
                new SensorType("humidity"), LocalDate.of(2018, 12, 10), "ºC");
        ListOfReadings lr1 = new ListOfReadings();
        LocalDateTime date1 = LocalDateTime.of(2018, 12, 21, 12, 30);
        Reading r1 = new Reading(date1, 12);
        lr1.addReading(r1);
        s1.setListOfReadings(lr1);
        s2.setListOfReadings(lr1);
        s3.setListOfReadings(lr1);
        geographicArea.addSensorToList(s1);
        geographicArea.addSensorToList(s2);
        geographicArea.addSensorToList(s3);
        List<AreaSensor> expectedResult = new ArrayList<>();
        expectedResult.add(s1);
        expectedResult.add(s2);
        //ACT
        List<AreaSensor> result = geographicArea.getListOfSensorsOfTheSameType(sensorType1);
        //ASSERT
        assertEquals(expectedResult, result);

    }

    @Test
    void getListOfSensorsClosestSameDistance() {
        //ARRANGE
        GeographicArea geographicArea = new GeographicArea("Porto", "City", new OccupationArea
                (new Location(10, 20, 30), 0.5, 0.5),
                new GeographicAreaType("City"));
        SensorType sensorType1 = new SensorType("temperature");
        AreaSensor s1 = new AreaSensor("1", "sensor1", new Location(12, 20, 30),
                sensorType1, LocalDate.of(2018, 12, 10), "ºC");

        ListOfReadings lr1 = new ListOfReadings();
        LocalDateTime date1 = LocalDateTime.of(2018, 12, 21, 12, 30);
        Reading r1 = new Reading(date1, 12);
        LocalDateTime date2 = LocalDateTime.of(2018, 12, 22, 12, 30);
        Reading r2 = new Reading(date2, 12);
        LocalDateTime date3 = LocalDateTime.of(2018, 12, 23, 12, 30);
        Reading r3 = new Reading(date3, 12);

        lr1.addReading(r1);
        lr1.addReading(r2);
        lr1.addReading(r3);

        s1.setListOfReadings(lr1);


        //--------------------------------------------------------------------

        AreaSensor s2 = new AreaSensor("2", "sensor2", new Location(12, 20, 30),
                sensorType1, LocalDate.of(2018, 12, 10), "ºC");


        ListOfReadings lr2 = new ListOfReadings();
        LocalDateTime date4 = LocalDateTime.of(2018, 12, 21, 12, 30);
        Reading r4 = new Reading(date4, 12);
        LocalDateTime date5 = LocalDateTime.of(2018, 12, 22, 12, 30);
        Reading r5 = new Reading(date5, 12);
        LocalDateTime date6 = LocalDateTime.of(2018, 12, 24, 12, 30);
        Reading r6 = new Reading(date6, 12);

        lr2.addReading(r4);
        lr2.addReading(r5);
        lr2.addReading(r6);


        s2.setListOfReadings(lr2);

        geographicArea.addSensorToList(s1);
        geographicArea.addSensorToList(s2);

        List<AreaSensor> expectedResult = new ArrayList<>();

        expectedResult.add(s1);
        expectedResult.add(s2);
        //ACT
        AreaSensor result = geographicArea.getSensorClosestForReadings(new Location(10, 20, 30), new SensorType("temperature"));
        //ASSERT
        assertEquals(s2.getName(), result.getName());

    }

    @Test
    void getListOfSensorsClosestSameDistance2() {
        //ARRANGE
        GeographicArea geographicArea = new GeographicArea("Porto", "City", new OccupationArea
                (new Location(10, 20, 30), 0.5, 0.5),
                new GeographicAreaType("City"));
        SensorType sensorType1 = new SensorType("temperature");
        AreaSensor s1 = new AreaSensor("1", "sensor1", new Location(12, 20, 30),
                sensorType1, LocalDate.of(2018, 12, 10), "ºC");

        ListOfReadings lr1 = new ListOfReadings();
        LocalDateTime date1 = LocalDateTime.of(2018, 12, 21, 12, 30);
        Reading r1 = new Reading(date1, 12);
        LocalDateTime date2 = LocalDateTime.of(2018, 12, 22, 12, 30);
        Reading r2 = new Reading(date2, 12);
        LocalDateTime date3 = LocalDateTime.of(2018, 12, 28, 12, 30);
        Reading r3 = new Reading(date3, 12);

        lr1.addReading(r1);
        lr1.addReading(r2);
        lr1.addReading(r3);

        s1.setListOfReadings(lr1);


        //--------------------------------------------------------------------

        AreaSensor s2 = new AreaSensor("1", "sensor2", new Location(12, 20, 30),
                sensorType1, LocalDate.of(2018, 12, 10), "ºC");


        ListOfReadings lr2 = new ListOfReadings();
        LocalDateTime date4 = LocalDateTime.of(2018, 12, 21, 12, 30);
        Reading r4 = new Reading(date4, 12);
        LocalDateTime date5 = LocalDateTime.of(2018, 12, 22, 12, 30);
        Reading r5 = new Reading(date5, 12);
        LocalDateTime date6 = LocalDateTime.of(2018, 12, 24, 12, 30);
        Reading r6 = new Reading(date6, 12);

        lr2.addReading(r4);
        lr2.addReading(r5);
        lr2.addReading(r6);


        s2.setListOfReadings(lr2);

        //--------------------------------------------------------------------

        AreaSensor s3 = new AreaSensor("1", "sensor2", new Location(12, 20, 30),
                sensorType1, LocalDate.of(2018, 12, 10), "ºC");


        ListOfReadings lr3 = new ListOfReadings();
        LocalDateTime date7 = LocalDateTime.of(2018, 12, 21, 12, 30);
        Reading r7 = new Reading(date7, 12);
        LocalDateTime date8 = LocalDateTime.of(2018, 12, 22, 12, 30);
        Reading r8 = new Reading(date8, 12);
        LocalDateTime date9 = LocalDateTime.of(2018, 12, 26, 12, 30);
        Reading r9 = new Reading(date9, 12);

        lr3.addReading(r7);
        lr3.addReading(r8);
        lr3.addReading(r9);


        s3.setListOfReadings(lr3);

        geographicArea.addSensorToList(s1);
        geographicArea.addSensorToList(s2);
        geographicArea.addSensorToList(s3);

        //ACT
        AreaSensor result = geographicArea.getSensorClosestForReadings(new Location(10, 20, 30), new SensorType("temperature"));
        //ASSERT
        assertEquals(s1.getName(), result.getName());
    }


    @Test
    void getListOfSensorsClosest() {
        //ARRANGE
        GeographicArea geographicArea = new GeographicArea("Porto", "City", new OccupationArea
                (new Location(10, 20, 30), 0.5, 0.5),
                new GeographicAreaType("City"));
        SensorType sensorType1 = new SensorType("temperature");
        AreaSensor s1 = new AreaSensor("1", "sensor1", new Location(12, 20, 30),
                sensorType1, LocalDate.of(2018, 12, 10), "ºC");

        ListOfReadings lr1 = new ListOfReadings();
        LocalDateTime date1 = LocalDateTime.of(2018, 12, 21, 12, 30);
        Reading r1 = new Reading(date1, 12);
        LocalDateTime date2 = LocalDateTime.of(2018, 12, 22, 12, 30);
        Reading r2 = new Reading(date2, 12);
        LocalDateTime date3 = LocalDateTime.of(2018, 12, 28, 12, 30);
        Reading r3 = new Reading(date3, 12);

        lr1.addReading(r1);
        lr1.addReading(r2);
        lr1.addReading(r3);

        s1.setListOfReadings(lr1);


        //--------------------------------------------------------------------

        AreaSensor s2 = new AreaSensor("2", "sensor2", new Location(10, 20, 30),
                sensorType1, LocalDate.of(2018, 12, 10), "ºC");


        ListOfReadings lr2 = new ListOfReadings();
        LocalDateTime date4 = LocalDateTime.of(2018, 12, 21, 12, 30);
        Reading r4 = new Reading(date4, 12);
        LocalDateTime date5 = LocalDateTime.of(2018, 12, 22, 12, 30);
        Reading r5 = new Reading(date5, 12);
        LocalDateTime date6 = LocalDateTime.of(2018, 12, 24, 12, 30);
        Reading r6 = new Reading(date6, 12);

        lr2.addReading(r4);
        lr2.addReading(r5);
        lr2.addReading(r6);


        s2.setListOfReadings(lr2);

        //--------------------------------------------------------------------

        AreaSensor s3 = new AreaSensor("1", "sensor2", new Location(12, 20, 30),
                sensorType1, LocalDate.of(2018, 12, 10), "ºC");


        ListOfReadings lr3 = new ListOfReadings();
        LocalDateTime date7 = LocalDateTime.of(2018, 12, 21, 12, 30);
        Reading r7 = new Reading(date7, 12);
        LocalDateTime date8 = LocalDateTime.of(2018, 12, 22, 12, 30);
        Reading r8 = new Reading(date8, 12);
        LocalDateTime date9 = LocalDateTime.of(2018, 12, 26, 12, 30);
        Reading r9 = new Reading(date9, 12);

        lr3.addReading(r7);
        lr3.addReading(r8);
        lr3.addReading(r9);


        s3.setListOfReadings(lr3);

        geographicArea.addSensorToList(s1);
        geographicArea.addSensorToList(s2);
        geographicArea.addSensorToList(s3);

        //ACT
        AreaSensor result = geographicArea.getSensorClosestForReadings(new Location(10, 20, 30), new SensorType("temperature"));
        //ASSERT
        assertEquals(s2.getName(), result.getName());
    }


    @Test
    void checkIfcheckInstallationDateTrue() {

        GeographicArea geographicArea = new GeographicArea("Porto", "City", new OccupationArea
                (new Location(10, 20, 30), 0.5, 0.5),
                new GeographicAreaType("City"));
        SensorType sensorType1 = new SensorType("temperature");

        AreaSensor s1 = new AreaSensor("1", "sensor1", new Location(12, 20, 30), sensorType1, LocalDate.of(2018, 12, 10), "ºC");
        AreaSensor s2 = new AreaSensor("1", "sensor1", new Location(12, 20, 30), sensorType1, LocalDate.of(2018, 12, 11), "ºC");
        AreaSensor s3 = new AreaSensor("1", "sensor1", new Location(12, 20, 30), sensorType1, LocalDate.of(2018, 12, 12), "ºC");
        AreaSensor s4 = new AreaSensor("1", "sensor1", new Location(12, 20, 30), sensorType1, LocalDate.of(2018, 12, 13), "ºC");

        geographicArea.addSensorToList(s1);
        geographicArea.addSensorToList(s2);
        geographicArea.addSensorToList(s3);
        geographicArea.addSensorToList(s4);

        LocalDate dateTest = LocalDate.of(2018, 12, 21);

        boolean result = geographicArea.checkInstallationDate(dateTest);

        assertTrue(result);
    }

    @Test
    void checkIfcheckInstallationDateFalse() {

        GeographicArea geographicArea = new GeographicArea("Porto", "City", new OccupationArea
                (new Location(10, 20, 30), 0.5, 0.5),
                new GeographicAreaType("City"));
        SensorType sensorType1 = new SensorType("temperature");

        AreaSensor s1 = new AreaSensor("1", "sensor1", new Location(12, 20, 30), sensorType1, LocalDate.of(2018, 12, 10), "ºC");
        AreaSensor s2 = new AreaSensor("1", "sensor1", new Location(12, 20, 30), sensorType1, LocalDate.of(2018, 12, 11), "ºC");
        AreaSensor s3 = new AreaSensor("1", "sensor1", new Location(12, 20, 30), sensorType1, LocalDate.of(2018, 12, 12), "ºC");
        AreaSensor s4 = new AreaSensor("1", "sensor1", new Location(12, 20, 30), sensorType1, LocalDate.of(2018, 12, 13), "ºC");

        geographicArea.addSensorToList(s1);
        geographicArea.addSensorToList(s2);
        geographicArea.addSensorToList(s3);
        geographicArea.addSensorToList(s4);

        LocalDate dateTest = LocalDate.of(2018, 12, 5);

        boolean result = geographicArea.checkInstallationDate(dateTest);

        assertFalse(result);
    }


    @Test
    void getSensorByIdTest() {
        GeographicArea geographicArea = new GeographicArea();
        SensorType sensorType1 = new SensorType("temperature");

        AreaSensor s1 = new AreaSensor("1", "sensor1", new Location(12, 20, 30), sensorType1, LocalDate.of(2018, 12, 10), "ºC");
        AreaSensor s2 = new AreaSensor("1", "sensor1", new Location(12, 20, 30), sensorType1, LocalDate.of(2018, 12, 11), "ºC");
        AreaSensor s3 = new AreaSensor("1", "sensor1", new Location(12, 20, 30), sensorType1, LocalDate.of(2018, 12, 12), "ºC");
        AreaSensor s4 = new AreaSensor("1", "sensor1", new Location(12, 20, 30), sensorType1, LocalDate.of(2018, 12, 13), "ºC");

        geographicArea.addSensorToList(s1);
        geographicArea.addSensorToList(s2);
        geographicArea.addSensorToList(s3);
        geographicArea.addSensorToList(s4);

        AreaSensor expectedResult = s1;
        AreaSensor result = geographicArea.getSensorBySensorID(s1.getIdOfAreaSensor());

        assertEquals(expectedResult, result);
    }

    @Test
    void getSensorByIdNullTest() {
        GeographicArea geographicArea = new GeographicArea("Porto", "City", new OccupationArea
                (new Location(10, 20, 30), 0.5, 0.5),
                new GeographicAreaType("City"));
        SensorType sensorType1 = new SensorType("temperature");

        AreaSensor s1 = new AreaSensor("1", "sensor1", new Location(12, 20, 30), sensorType1, LocalDate.of(2018, 12, 10), "ºC");
        AreaSensor s2 = new AreaSensor("1", "sensor2", new Location(12, 20, 30), sensorType1, LocalDate.of(2018, 12, 11), "ºC");
        AreaSensor s3 = new AreaSensor("1", "sensor3", new Location(12, 20, 30), sensorType1, LocalDate.of(2018, 12, 12), "ºC");
        AreaSensor s4 = new AreaSensor("1", "sensor4", new Location(12, 20, 30), sensorType1, LocalDate.of(2018, 12, 13), "ºC");

        geographicArea.addSensorToList(s1);
        geographicArea.addSensorToList(s2);
        geographicArea.addSensorToList(s3);

        AreaSensor expectedResult = null;
        AreaSensor result = geographicArea.getSensorBySensorID(s4.getName());

        assertEquals(expectedResult, result);
    }

    @Test
    void getListOfSensorsActive() {
        GeographicArea geographicArea = new GeographicArea("Porto", "City", new OccupationArea
                (new Location(10, 20, 30), 0.5, 0.5),
                new GeographicAreaType("City"));
        SensorType sensorType1 = new SensorType("temperature");

        AreaSensor s1 = new AreaSensor("1", "sensor1", new Location(12, 20, 30), sensorType1, LocalDate.of(2018, 12, 10), "ºC");
        AreaSensor s2 = new AreaSensor("2", "sensor2", new Location(12, 20, 30), sensorType1, LocalDate.of(2018, 12, 11), "ºC");
        AreaSensor s3 = new AreaSensor("3", "sensor3", new Location(12, 20, 30), sensorType1, LocalDate.of(2018, 12, 12), "ºC");
        AreaSensor s4 = new AreaSensor("4", "sensor4", new Location(12, 20, 30), sensorType1, LocalDate.of(2018, 12, 13), "ºC");

        geographicArea.addSensorToList(s1);
        geographicArea.addSensorToList(s2);
        geographicArea.addSensorToList(s3);
        geographicArea.addSensorToList(s4);

        LocalDate dateTest = LocalDate.of(2018, 12, 21);

        Status st1 = new Status(true, dateTest);

        Status st2 = new Status(false, dateTest);

        s1.getListOfStatus().getStatusList().add(st1);
        s2.getListOfStatus().getStatusList().add(st2);
        s3.getListOfStatus().getStatusList().add(st2);
        s4.getListOfStatus().getStatusList().add(st1);

        List<AreaSensor> expectedResult = new ArrayList<>();
        expectedResult.add(s1);
        expectedResult.add(s4);

        List<AreaSensor> result = geographicArea.getListOfSensorsActive();

        assertEquals(expectedResult, result);
    }

    @Test
    void getListOfSensorsActiveEmptyList() {
        GeographicArea geographicArea = new GeographicArea("Porto", "City", new OccupationArea
                (new Location(10, 20, 30), 0.5, 0.5),
                new GeographicAreaType("City"));
        SensorType sensorType1 = new SensorType("temperature");

        AreaSensor s1 = new AreaSensor("1", "sensor1", new Location(12, 20, 30), sensorType1, LocalDate.of(2018, 12, 10), "ºC");
        AreaSensor s2 = new AreaSensor("2", "sensor2", new Location(12, 20, 30), sensorType1, LocalDate.of(2018, 12, 11), "ºC");
        AreaSensor s3 = new AreaSensor("3", "sensor3", new Location(12, 20, 30), sensorType1, LocalDate.of(2018, 12, 12), "ºC");
        AreaSensor s4 = new AreaSensor("4", "sensor4", new Location(12, 20, 30), sensorType1, LocalDate.of(2018, 12, 13), "ºC");

        geographicArea.addSensorToList(s1);
        geographicArea.addSensorToList(s2);
        geographicArea.addSensorToList(s3);
        geographicArea.addSensorToList(s4);

        List<AreaSensor> expectedResult = new ArrayList<>();
        expectedResult.add(s1);
        expectedResult.add(s2);
        expectedResult.add(s3);
        expectedResult.add(s4);
        List<AreaSensor> result = geographicArea.getListOfSensorsActive();

        assertEquals(expectedResult, result);
    }


    @Test
    void testAddSensorToListTrue() {
        GeographicArea geographicArea = new GeographicArea("Porto", "City", new OccupationArea
                (new Location(10, 20, 30), 0.5, 0.5),
                new GeographicAreaType("City"));
        AreaSensor s1 = new AreaSensor("1", "sensor1", new Location(40.7486, -73.9864, 0),
                new SensorType("rainfall"), LocalDate.of(2019, 01, 05), "ºC");
        geographicArea.addSensorToList(s1);
        assertTrue(true);
    }


    @Test
    void testAddSensorToListTrueAndFalse() {
        GeographicArea geographicArea = new GeographicArea("Porto", "City", new OccupationArea
                (new Location(10, 20, 30), 0.5, 0.5),
                new GeographicAreaType("City"));
        AreaSensor s1 = new AreaSensor("1", "sensor1", new Location(40.7486, -73.9864, 0),
                new SensorType("rainfall"), LocalDate.of(2019, 01, 05), "ºC");
        List<AreaSensor> areaSensorList = new ArrayList<>();

        geographicArea.setListOfAreaSensors(areaSensorList);
        boolean resultTrue = geographicArea.addSensorToList(s1);
        boolean resultFalse = geographicArea.addSensorToList(s1);

        assertTrue(resultTrue && !resultFalse);
    }

    @Test
    void getDailyTemperatureInHouseArea() {
        GeographicArea geographicArea = new GeographicArea("Porto", "City", new OccupationArea
                (new Location(11, 20, 30), 0.5, 0.5),
                new GeographicAreaType("City"));

        SensorType sensorType1 = new SensorType("temperature");

        AreaSensor s1 = new AreaSensor("1", "sensor1", new Location(11, 20, 30), sensorType1, LocalDate.of(2019, 04, 28), "ºC");
        AreaSensor s2 = new AreaSensor("2", "sensor2", new Location(10, 20, 30), sensorType1, LocalDate.of(2019, 04, 28), "ºC");

        geographicArea.addSensorToList(s1);
        geographicArea.addSensorToList(s2);

        ListOfReadings lr1 = new ListOfReadings();
        LocalDateTime date1 = LocalDateTime.of(2019, 04, 28, 12, 30);
        Reading r1 = new Reading(date1, 12);
        LocalDateTime date2 = LocalDateTime.of(2019, 04, 28, 12, 31);
        Reading r2 = new Reading(date2, 13);
        LocalDateTime date3 = LocalDateTime.of(2019, 04, 28, 12, 32);
        Reading r3 = new Reading(date3, 14);

        lr1.addReading(r1);
        lr1.addReading(r2);
        lr1.addReading(r3);

        s1.setListOfReadings(lr1);

        //---------------------------------------------------
        ListOfReadings lr2 = new ListOfReadings();
        Reading r4 = new Reading(date1, 12);
        Reading r5 = new Reading(date2, 13);
        Reading r6 = new Reading(date3, 14);

        lr2.addReading(r4);
        lr2.addReading(r5);
        lr2.addReading(r6);


        s2.setListOfReadings(lr2);
        //-------------------------------------------------------

        double expectedResult = 13.0;


        Location location1 = new Location(11, 20, 30);
        LocalDate localDate = LocalDate.of(2019, 04, 28);

        double result = geographicArea.getAverageDailyTemperatureInHouseArea(location1,sensorType1,localDate);

        assertEquals(expectedResult,result);
    }

    @Test
    void testCreateAndAddSensorToGa() {
        GeographicArea geographicArea = new GeographicArea("Porto", "City", new OccupationArea
                (new Location(11, 20, 30), 0.5, 0.5),
                new GeographicAreaType("City"));

        SensorType sensorType1 = new SensorType("temperature");

        AreaSensor s1 = new AreaSensor("1", "sensor1", new Location(11, 20, 30), sensorType1, LocalDate.of(2019, 04, 28), "ºC");

        int expectedResult = 1;

        int result = geographicArea.createAndAddSensor(MapperAreaSensor.toDto(s1));

        assertEquals(expectedResult,result);
    }

    @Test
    void testCreateAndAddSensorToGaImpossible() {
        GeographicArea geographicArea = new GeographicArea("Porto", "City", new OccupationArea
                (new Location(11, 20, 30), 0.5, 0.5),
                new GeographicAreaType("City"));

        SensorType sensorType1 = new SensorType("temperature");

        AreaSensor s1 = new AreaSensor("1", "sensor1", new Location(11, 20, 30), sensorType1, LocalDate.of(2019, 04, 28), "ºC");
        s1.setIdOfAreaSensor("");
        int expectedResult = -1;

        int result = geographicArea.createAndAddSensor(MapperAreaSensor.toDto(s1));

        assertEquals(expectedResult,result);
    }


    @Test
    void testCreateAndAddSensorToGaAlreadyExists() {
        GeographicArea geographicArea = new GeographicArea("Porto", "City", new OccupationArea
                (new Location(11, 20, 30), 0.5, 0.5),
                new GeographicAreaType("City"));

        SensorType sensorType1 = new SensorType("temperature");

        AreaSensor s1 = new AreaSensor("1", "sensor1", new Location(11, 20, 30), sensorType1, LocalDate.of(2019, 04, 28), "ºC");

        int expectedResult = -2;

        geographicArea.createAndAddSensor(MapperAreaSensor.toDto(s1));

        int result = geographicArea.createAndAddSensor(MapperAreaSensor.toDto(s1));

        assertEquals(expectedResult,result);
    }

    @Test
    void testCreateAndAddSensorToGa2() {
        GeographicArea geographicArea = new GeographicArea("Porto", "City", new OccupationArea
                (new Location(11, 20, 30), 0.5, 0.5),
                new GeographicAreaType("City"));

        SensorType sensorType1 = new SensorType("temperature");

        AreaSensor s1 = new AreaSensor("1", "sensor1", new Location(11, 20, 30), sensorType1, LocalDate.of(2019, 04, 28), "ºC");

        geographicArea.createAndAddSensor(MapperAreaSensor.toDto(s1));

        assertEquals(geographicArea.getListOfAreaSensors().get(0).getGeographicArea(),geographicArea);
    }

    @Test
    void checkLocationOfSensorFalse() {
        SensorType m1 = new SensorType("temperature");
        Location location = new Location(40.7486, 73.9864, 0);
        AreaSensor areaSensor = new AreaSensor("1", "s1", location,
                m1, LocalDate.of(2018, 12, 10), "C");
        AreaSensor areaSensor2 = new AreaSensor("12", "s12", location,
                m1, LocalDate.of(2018, 12, 10), "C");
        GeographicArea geographicArea = new GeographicArea("porto", "city", new OccupationArea(new Location(10, 20, 0), 10, 10), new GeographicAreaType("city"));
        LocalDateTime date1 = LocalDateTime.of(2019, 01, 02, 12, 30);
        LocalDateTime date2 = LocalDateTime.of(2019, 01, 03, 12, 31);
        ListOfReadings lisOfReading = new ListOfReadings();
        lisOfReading.addReading(new Reading(date1, 12.0));
        lisOfReading.addReading(new Reading(date2, 13.0));
        areaSensor.setListOfReadings(lisOfReading);
        areaSensor2.setListOfReadings(lisOfReading);
        geographicArea.addSensorToList(areaSensor);
        geographicArea.addSensorToList(areaSensor2);
        AreaSensor result = geographicArea.getSensorClosestForReadings(location,m1);
        assertEquals(areaSensor, result);
    }



}
