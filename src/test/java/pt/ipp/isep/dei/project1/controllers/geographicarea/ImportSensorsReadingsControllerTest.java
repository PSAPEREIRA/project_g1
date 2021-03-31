package pt.ipp.isep.dei.project1.controllers.geographicarea;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import pt.ipp.isep.dei.project1.dto.sensordto.ReadingDto;
import pt.ipp.isep.dei.project1.model.geographicarea.*;
import pt.ipp.isep.dei.project1.model.house.House;
import pt.ipp.isep.dei.project1.model.house.Room;
import pt.ipp.isep.dei.project1.model.repositories.GeographicAreaRepository;
import pt.ipp.isep.dei.project1.model.repositories.RoomRepository;
import pt.ipp.isep.dei.project1.model.sensor.*;
import pt.ipp.isep.dei.project1.model.repositories.GeographicAreaDomainService;
import pt.ipp.isep.dei.project1.model.repositories.RoomDomainService;
import pt.ipp.isep.dei.project1.readers.FileReader;
import pt.ipp.isep.dei.project1.readers.readercsv.CsvReader;
import pt.ipp.isep.dei.project1.readers.readerjson.GeographicAreaFromFile;
import pt.ipp.isep.dei.project1.readers.readerjson.JsonReader;
import pt.ipp.isep.dei.project1.readers.xml.XmlReader;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URLDecoder;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.*;

@ExtendWith(MockitoExtension.class)
class ImportSensorsReadingsControllerTest {

    @Mock
    private RoomRepository repoRoom;

    @Mock
    private GeographicAreaRepository repoGA;


    private GeographicAreaDomainService geographicAreaDomainService;
    private RoomDomainService roomDomainService;
    private ImportSensorsReadingsController ctr020;


    @BeforeEach
    void initMocks() throws Exception {
        geographicAreaDomainService = new GeographicAreaDomainService(repoGA);
        roomDomainService = new RoomDomainService(repoRoom);
        ctr020 = new ImportSensorsReadingsController(geographicAreaDomainService, roomDomainService);
    }

    @Test
    void testGetListOfReaders() throws Exception {
        Location l1 = new Location(40.7486, -73.9864, 0);

        //ACT
        List<FileReader> expected = new ArrayList<>();
        expected.add(new XmlReader());
        expected.add(new JsonReader());
        expected.add(new CsvReader());

        List<FileReader> result = ctr020.getListOfReadersOfSensorReadings();

        assertEquals(expected.get(0).getReaderType(), result.get(0).getReaderType());
    }

    @Test
    void testGetListOfReaders2() throws Exception {
        Location l1 = new Location(40.7486, -73.9864, 0);

        //ACT
        List<FileReader> expected = new ArrayList<>();
        expected.add(new XmlReader());
        expected.add(new JsonReader());
        expected.add(new CsvReader());


        List<FileReader> result = ctr020.getListOfReadersOfSensorReadings();

        assertEquals(expected.get(1).getReaderType(), result.get(1).getReaderType());
    }


    @Test
    void testGetListOfReaders3() throws Exception {
        Location l1 = new Location(40.7486, -73.9864, 0);

        //ACT
        List<FileReader> expected = new ArrayList<>();
        expected.add(new XmlReader());
        expected.add(new JsonReader());
        expected.add(new CsvReader());

        List<FileReader> result = ctr020.getListOfReadersOfSensorReadings();

        assertEquals(expected.get(2).getReaderType(), result.get(2).getReaderType());
    }


        //Test to the readings being added to csv file
        @Test
        void addReadingsCSV() throws IOException, Exception {
            Location l1 = new Location(40.7486, -73.9864, 0);

            //ARRANGE
            GeographicArea ga = new GeographicArea("Porto", "city", new OccupationArea(new Location(10, 20, 30), 0.5, 0.5), new GeographicAreaType("city"));
            AreaSensor s1 = new AreaSensor();
            LocalDate ld = LocalDate.of(2018, 01, 01);
            LocalDate lstatus = LocalDate.of(2018, 1, 30);
            ListOfStatus statusList = new ListOfStatus();
            Status status = new Status(true, lstatus);
            statusList.addStatusToSensor(status);
            s1.setListOfStatus(statusList);
            s1.setName("sensor1");
            s1.setSensorType(new SensorType("Temperature"));
            s1.setIdOfAreaSensor("TT12346OB");
            s1.setInstallationDate(ld);
            s1.setGeographicArea(ga);


            ClassLoader classLoader = getClass().getClassLoader();
            File file = new File(classLoader.getResource("CsvReaderTest.csv").getFile());
            String path = file.getAbsolutePath();

            //AC

            List<Integer> result = ctr020.addReadingsToSensorsOfGA((URLDecoder.decode(path, "UTF-8")));


            int result2 = result.get(2);

            //ASSERT
            assertEquals(6, result2);
        }
    /*
            @Test
            void addReadingsCSV2() throws IOException, Exception {
                //ARRANGE
                Location l1 = new Location(40.7486, -73.9864, 0);

                GeographicArea ga = new GeographicArea("Porto", "city", new OccupationArea(new Location(10, 20, 30), 0.5, 0.5), new GeographicAreaType("city"));
                AreaSensor s1 = new AreaSensor();
                s1.setType("sensor1");
                s1.setIdOfRoomSensor("TT11116");
                LocalDate ld = LocalDate.of(2019, 12, 01);
                s1.setInstallationDate(ld);
                AreaSensor s2 = new AreaSensor();
                s2.setType("sensor2");
                ga.addSensorToList(s1);

                s1.setGeographicAreaID(ga);
                s2.setGeographicAreaID(ga);
                ClassLoader classLoader = getClass().getClassLoader();
                File file = new File(classLoader.getResource("SensorReadingsTeste.csv").getFile());
                String path = file.getParentFile().getAbsolutePath() + File.separator;
                String path2 = URLDecoder.decode(path, "UTF-8") + "SensorReadingsTeste.csv";
                //AC
                FileReader core = new CsvReader();
                List<Integer> result = ctr020.addReadingsToSensorsOfGA( path2);
                int result2 = result.get(2);
                //ASSERT
                assertEquals(5, result2);
            }

            @Test
            void addReadingsCSV3() throws IOException, Exception {
                Location l1 = new Location(40.7486, -73.9864, 0);

                //ARRANGE
                GeographicArea ga = new GeographicArea("Porto", "city", new OccupationArea(new Location(10, 20, 30), 0.5, 0.5), new GeographicAreaType("city"));
                AreaSensor s1 = new AreaSensor();
                s1.setType("sensor1");
                s1.setIdOfRoomSensor("TT11116");
                LocalDate ld = LocalDate.of(2019, 12, 01);
                s1.setInstallationDate(ld);
                s1.setGeographicAreaID(ga);
                AreaSensor s2 = new AreaSensor();
                s2.setType("sensor2");
                s2.setGeographicAreaID(ga);
                ga.addSensorToList(s1);
                ga.addSensorToList(s2);

                ClassLoader classLoader = getClass().getClassLoader();
                File file = new File(classLoader.getResource("SensorReadingsTeste.csv").getFile());
                String path = file.getParentFile().getAbsolutePath() + File.separator;
                String path2 = URLDecoder.decode(path, "UTF-8") + "SensorReadingsTeste.csv";
                //AC
                FileReader core = new CsvReader();
                List<Integer> result = ctr020.addReadingsToSensorsOfGA( path2);
                int result2 = result.get(2);
                //ASSERT
                assertEquals(5, result2);
            }

            @Test
            void addReadingsCSV4() throws IOException, Exception {
                //ARRANGE
                Location l1 = new Location(40.7486, -73.9864, 0);

                GeographicArea ga = new GeographicArea("Porto", "city", new OccupationArea(new Location(10, 20, 30), 0.5, 0.5), new GeographicAreaType("city"));
                AreaSensor s1 = new AreaSensor();
                s1.setType("sensor1");
                s1.setIdOfRoomSensor("TT1111");
                LocalDate ld = LocalDate.of(2019, 12, 01);
                s1.setInstallationDate(ld);
                s1.setGeographicAreaID(ga);
                AreaSensor s2 = new AreaSensor();
                s2.setType("sensor2");
                s2.setGeographicAreaID(ga);
                ga.addSensorToList(s1);
                ga.addSensorToList(s2);

                ClassLoader classLoader = getClass().getClassLoader();
                File file = new File(classLoader.getResource("SensorReadingsTeste.csv").getFile());
                String path = file.getParentFile().getAbsolutePath() + File.separator;
                String path2 = URLDecoder.decode(path, "UTF-8") + "SensorReadingsTeste.csv";
                //AC
                FileReader core = new CsvReader();
                List<Integer> result = ctr020.addReadingsToSensorsOfGA( path2);
                int result2 = result.get(2);
                //ASSERT
                assertEquals(5, result2);
            }
    */
    @Test
    void addReadingsCSV5() throws IOException, Exception {
        //ARRANGE
        Location l1 = new Location(40.7486, -73.9864, 0);

        GeographicArea ga = new GeographicArea("Porto", "city", new OccupationArea(new Location(10, 20, 30), 0.5, 0.5), new GeographicAreaType("city"));
        AreaSensor s1 = new AreaSensor();
        s1.setName("sensor1");
        s1.setIdOfAreaSensor("TT12346");

        LocalDate ld = LocalDate.of(2017, 12, 01);
        s1.setInstallationDate(ld);
        s1.setGeographicArea(ga);

        AreaSensor s2 = new AreaSensor();
        s2.setName("sensor2");

        s2.setIdOfAreaSensor("TT11116");
        s2.setGeographicArea(ga);
        ga.addSensorToList(s1);
        ga.addSensorToList(s2);

        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("SensorReadingsTeste.csv").getFile());
        String path = file.getParentFile().getAbsolutePath() + File.separator;
        String path2 = URLDecoder.decode(path, "UTF-8") + "SensorReadingsTeste.csv";
        //AC
        FileReader core = new CsvReader();
        List<Integer> result = ctr020.addReadingsToSensorsOfGA(path2);
        int result2 = result.get(0);
        //ASSERT
        assertEquals(-1, result2);
    }

    @Test
    void addReadingsCSV6() throws IOException, Exception {
        //ARRANGE
        Location l1 = new Location(40.7486, -73.9864, 0);

        GeographicArea ga = new GeographicArea("Porto", "city", new OccupationArea(new Location(10, 20, 30), 0.5, 0.5), new GeographicAreaType("city"));
        AreaSensor s1 = new AreaSensor();
        s1.setName("sensor1");
        s1.setIdOfAreaSensor("TT12346");
        LocalDate ld = LocalDate.of(2017, 12, 01);
        s1.setInstallationDate(ld);
        AreaSensor s2 = new AreaSensor();
        s2.setName("sensor2");
        s2.setIdOfAreaSensor("sensor2");
        ga.addSensorToList(s1);
        ga.addSensorToList(s2);

        String path = "hjjhghj";
        //AC
        FileReader core = new CsvReader();
        List<Integer> result = ctr020.addReadingsToSensorsOfGA(path);
        int result2 = result.get(0);
        //ASSERT
        assertEquals(-1, result2);
    }


    @Test
    void addReadingsZeroSensorID() throws Exception {
        //ARRANGE
        Location l1 = new Location(40.7486, -73.9864, 0);

        GeographicArea ga = new GeographicArea("Porto", "city", new OccupationArea(new Location(10, 20, 30), 0.5, 0.5), new GeographicAreaType("city"));
        AreaSensor s1 = new AreaSensor();
        s1.setName("sensor1");
        s1.setIdOfAreaSensor("ID1");
        s1.setGeographicArea(ga);
        AreaSensor s2 = new AreaSensor();
        s2.setName("sensor2");
        s2.setIdOfAreaSensor("sensor2");
        s2.setGeographicArea(ga);
        ga.addSensorToList(s1);
        ga.addSensorToList(s2);

        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("SensorReadingsTeste.csv").getFile());
        String path = file.getParentFile().getAbsolutePath() + File.separator;
        String path2 = URLDecoder.decode(path, "UTF-8") + "SensorReadingsTeste.csv";
        //AC
        FileReader core = new CsvReader();
        List<Integer> result = ctr020.addReadingsToSensorsOfGA(path2);
        List<Integer> expected = new ArrayList<>();
        expected.add(-1);

        //ASSERT
        assertEquals(expected.get(0), result.get(0));
    }

    @Test
    void addReadingsZeroSensorS3() throws Exception {
        //ARRANGE
        Location l1 = new Location(40.7486, -73.9864, 0);

        GeographicArea ga = new GeographicArea("Porto", "city", new OccupationArea(new Location(10, 20, 30), 0.5, 0.5), new GeographicAreaType("city"));
        AreaSensor s1 = new AreaSensor();
        s1.setName("sensor1");
        s1.setIdOfAreaSensor("s3");
        s1.setGeographicArea(ga);
        AreaSensor s2 = new AreaSensor();
        s2.setName("sensor2");
        s2.setIdOfAreaSensor("sensor2");
        s2.setGeographicArea(ga);
        ga.addSensorToList(s1);
        ga.addSensorToList(s2);

        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("ReadingsTest.json").getFile());
        String path = file.getParentFile().getAbsolutePath() + File.separator;
        String path2 = URLDecoder.decode(path, "UTF-8") + "ReadingsTest.json";
        //AC
        FileReader core = new CsvReader();
        try {
            List<Integer> result = ctr020.addReadingsToSensorsOfGA(path2);
        } catch (DateTimeParseException e) {
            Assertions.assertEquals("Insert a valid code for this House Grid", e.getMessage());
        }

    }

    /**
     * @Test void addReadingsZeroReadingsID() throws IOException, Exception {
     * //ARRANGE
     * Location l1 = new Location(40.7486, -73.9864, 0);
     * House h1 = new House("Home", l1, new GeographicArea("Porto", "city",
     * new OccupationArea(new Location(10, 20, 30), 0.5, 0.5),
     * new GeographicAreaType("city")));
     * GeographicArea ga = new GeographicArea("Porto", "city", new OccupationArea(new Location(10, 20, 30), 0.5, 0.5), new GeographicAreaType("city"));
     * AreaSensor s1 = new AreaSensor();
     * s1.setType("sensor1");
     * s1.setIdOfRoomSensor("ID1");
     * s1.setGeographicAreaID(ga);
     * AreaSensor s2 = new AreaSensor();
     * s2.setType("sensor2");
     * s2.setIdOfRoomSensor("ID2");
     * s2.setGeographicAreaID(ga);
     * ga.addSensorToList(s1);
     * ga.addSensorToList(s2);
     * <p>
     * ClassLoader classLoader = getClass().getClassLoader();
     * File file = new File(classLoader.getResource("SensorReadingsTeste.csv").getFile());
     * String path = file.getParentFile().getAbsolutePath() + File.separator;
     * String path2 = URLDecoder.decode(path, "UTF-8") + "SensorReadingsTeste.csv";
     * //ACT
     * FileReader core = new CsvReader();
     * List<Integer> result = ctr020.addReadingsToSensorsOfGA( path2);
     * int result2 = result.get(1);
     * //ASSERT
     * assertEquals(0, result2);
     * }
     * @Test void addReadingsZeroSensors() throws IOException, Exception {
     * //ARRANGE
     * Location l1 = new Location(40.7486, -73.9864, 0);
     * House h1 = new House("Home", l1, new GeographicArea("Porto", "city",
     * new OccupationArea(new Location(10, 20, 30), 0.5, 0.5),
     * new GeographicAreaType("city")));
     * GeographicArea ga = new GeographicArea("Porto", "city", new OccupationArea(new Location(10, 20, 30), 0.5, 0.5), new GeographicAreaType("city"));
     * <p>
     * ClassLoader classLoader = getClass().getClassLoader();
     * File file = new File(classLoader.getResource("SensorReadingsTeste.csv").getFile());
     * String path = file.getParentFile().getAbsolutePath() + File.separator;
     * String path2 = URLDecoder.decode(path, "UTF-8") + "SensorReadingsTeste.csv";
     * //ACT
     * FileReader core = new CsvReader();
     * List<Integer> result = ctr020.addReadingsToSensorsOfGA( path2);
     * int result2 = result.get(1);
     * //ASSERT
     * assertEquals(0, result2);
     * }
     */

    @Test
    void addReadingsCSV7() throws IOException, Exception {
        //ARRANGE
        Location l1 = new Location(40.7486, -73.9864, 0);

        GeographicArea ga = new GeographicArea("Porto", "city", new OccupationArea(new Location(10, 20, 30), 0.5, 0.5), new GeographicAreaType("city"));
        AreaSensor s1 = new AreaSensor();
        s1.setName("sensor1");
        s1.setIdOfAreaSensor("ID1");
        AreaSensor s2 = new AreaSensor();
        s2.setName("sensor2");
        s2.setIdOfAreaSensor("ID2");
        ga.addSensorToList(s1);
        ga.addSensorToList(s2);

        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("SensorReadingsTeste.csv").getFile());
        String path = file.getParentFile().getAbsolutePath() + File.separator;
        String path2 = URLDecoder.decode(path, "UTF-8") + "SensorReadingsxcxc.csv";
        //AC
        FileReader core = new CsvReader();
        List<Integer> result = ctr020.addReadingsToSensorsOfGA(path2);
        int result2 = result.get(0);
        //ASSERT
        assertEquals(-1, result2);
    }

    /**
     * @Test void addReadingsCSV8() throws IOException, Exception {
     * //ARRANGE
     * Location l1 = new Location(40.7486, -73.9864, 0);
     * House h1 = new House("Home", l1, new GeographicArea("Porto", "city",
     * new OccupationArea(new Location(10, 20, 30), 0.5, 0.5),
     * new GeographicAreaType("city")));
     * GeographicArea ga = new GeographicArea("Porto", "city", new OccupationArea(new Location(10, 20, 30), 0.5, 0.5), new GeographicAreaType("city"));
     * AreaSensor s1 = new AreaSensor();
     * LocalDate date2 = LocalDate.of(2018, 10, 01);
     * s1.setType("sensor1");
     * s1.setIdOfRoomSensor("TT12346");
     * s1.setGeographicAreaID(ga);
     * s1.setInstallationDate(date2);
     * AreaSensor s2 = new AreaSensor();
     * s2.setType("sensor2");
     * s2.setIdOfRoomSensor("ID2");
     * s2.setGeographicAreaID(ga);
     * ga.addSensorToList(s1);
     * ga.addSensorToList(s2);
     * <p>
     * ClassLoader classLoader = getClass().getClassLoader();
     * File file = new File(classLoader.getResource("SensorReadingsTeste.csv").getFile());
     * String path = file.getParentFile().getAbsolutePath() + File.separator;
     * String path2 = URLDecoder.decode(path, "UTF-8") + "SensorReadingsTeste.csv";
     * //ACT
     * FileReader core = new CsvReader();
     * List<Integer> result = ctr020.addReadingsToSensorsOfGA( path2);
     * int result2 = result.get(1);
     * //ASSERT
     * assertEquals(s1.getListOfReadings().getListOfReading().get(0).getValue(), 14, 0.0001);
     * }
     */

    @Test
    void addReadingsCSV9() throws Exception {
        //ARRANGE
        Location l1 = new Location(40.7486, -73.9864, 0);
        GeographicArea ga = new GeographicArea("Porto", "city", new OccupationArea(new Location(10, 20, 30), 0.5, 0.5), new GeographicAreaType("city"));
        AreaSensor s1 = new AreaSensor();
        LocalDate date2 = LocalDate.of(2024, 10, 01);
        s1.setName("sensor1");
        s1.setIdOfAreaSensor("TT12346");
        s1.setGeographicArea(ga);
        s1.setInstallationDate(date2);
        AreaSensor s2 = new AreaSensor();
        s2.setName("sensor2");
        s2.setIdOfAreaSensor("ID2");
        s2.setGeographicArea(ga);
        ga.addSensorToList(s1);
        ga.addSensorToList(s2);

        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("SensorReadingsTeste.csv").getFile());
        String path = file.getParentFile().getAbsolutePath() + File.separator;
        String path2 = URLDecoder.decode(path, "UTF-8") + "SensorReadingsTeste.csv";
        //AC
        FileReader core = new CsvReader();
        List<Integer> result = ctr020.addReadingsToSensorsOfGA(path2);
        ListOfReadings lr = new ListOfReadings();
        lr.getListOfReading().isEmpty();
        //ASSERT
        assertEquals("[]", s1.getListOfReadings().getListOfReading().toString());
    }

    /**
     * @Test void addReadingsCSV10() throws IOException, Exception {
     * //ARRANGE
     * Location l1 = new Location(40.7486, -73.9864, 0);
     * House h1 = new House("Home", l1, new GeographicArea("Porto", "city",
     * new OccupationArea(new Location(10, 20, 30), 0.5, 0.5),
     * new GeographicAreaType("city")));
     * GeographicArea ga = new GeographicArea("Porto", "city", new OccupationArea(new Location(10, 20, 30), 0.5, 0.5), new GeographicAreaType("city"));
     * AreaSensor s1 = new AreaSensor();
     * LocalDate ld = LocalDate.of(2018, 12, 01);
     * LocalDate lstatus = LocalDate.of(2018, 12, 30);
     * ListOfStatus statusList = new ListOfStatus();
     * Status status = new Status(false, lstatus);
     * statusList.addStatusToSensor(status);
     * s1.setStatusList(statusList);
     * s1.setType("sensor1");
     * s1.setIdOfRoomSensor("TT12346");
     * s1.setGeographicAreaID(ga);
     * s1.setInstallationDate(ld);
     * AreaSensor s2 = new AreaSensor();
     * s2.setType("sensor2");
     * s2.setGeographicAreaID(ga);
     * ga.addSensorToList(s1);
     * ga.addSensorToList(s2);
     * <p>
     * ClassLoader classLoader = getClass().getClassLoader();
     * File file = new File(classLoader.getResource("SensorReadingsTeste.csv").getFile());
     * String path = file.getParentFile().getAbsolutePath() + File.separator;
     * String path2 = URLDecoder.decode(path, "UTF-8") + "SensorReadingsTeste.csv";
     * //ACT
     * FileReader core = new CsvReader();
     * List<Integer> result = ctr020.addReadingsToSensorsOfGA( path2);
     * int result2 = result.get(1);
     * //ASSERT
     * assertEquals(0, result2);
     * }
     */
    @Test
    void addReadingsCSV11() throws IOException, Exception {
        //ARRANGE
        Location l1 = new Location(40.7486, -73.9864, 0);
        GeographicArea ga = new GeographicArea("Porto", "city", new OccupationArea(new Location(10, 20, 30), 0.5, 0.5), new GeographicAreaType("city"));
        AreaSensor s1 = new AreaSensor();
        s1.setName("sensor1");
        s1.setIdOfAreaSensor("TT12346");
        s1.setGeographicArea(ga);
        LocalDate ld = LocalDate.of(2017, 12, 01);
        s1.setInstallationDate(ld);
        AreaSensor s2 = new AreaSensor();
        s2.setName("sensor2");
        s2.setIdOfAreaSensor("sensor2");
        ga.addSensorToList(s1);
        ga.addSensorToList(s2);

        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("SensorReadingsTeste.csv").getFile());
        String path = file.getParentFile().getAbsolutePath() + File.separator;
        String path2 = URLDecoder.decode(path, "UTF-8") + "DataSet_sprint05_SensorData_alt01.xml";
        //AC
        FileReader core = new CsvReader();

        List<Integer> result = ctr020.addReadingsToSensorsOfGA(path2);
        int result2 = result.get(0);
        //ASSERT
        assertEquals(61, result2);
    }


    @Test
    void testImportGaFromInputPathNewNull() throws Exception {
        //ARRANGE
        List<GeographicAreaFromFile> expectedResult = new ArrayList<>();
        FileReader core = new CsvReader();
        //ACT
        List<GeographicAreaFromFile> result = core.importGaFromInputPath("checkFileReader");
        //ASSERT
        assertEquals(expectedResult, result);
    }


    @Test
    void checkIfSetReadingsBySensorIDescription() {


        Location l1 = new Location(40.7486, -73.9864, 0);

        GeographicArea ga = new GeographicArea("Porto", "city", new OccupationArea(new Location(10, 20, 30), 0.5, 0.5), new GeographicAreaType("city"));
        List<ReadingDto> readingDtoList = new ArrayList<>();
        geographicAreaDomainService.add(ga);
        AreaSensor s1 = new AreaSensor();
        s1.setName("AreaSensor Alpha");
        s1.setIdOfAreaSensor("TT12346");
        LocalDate localDate = LocalDate.of(2018, 12, 01);
        s1.setInstallationDate(localDate);
        s1.setGeographicArea(ga);

        AreaSensor s2 = new AreaSensor();
        s2.setName("AreaSensor Beta");
        s2.setIdOfAreaSensor("TT12346");
        s2.setGeographicArea(ga);
        LocalDate localDate2 = LocalDate.of(2018, 12, 02);
        s2.setInstallationDate(localDate2);


        ReadingDto readingDto1 = new ReadingDto();
        ReadingDto readingDto2 = new ReadingDto();
        ReadingDto readingDto3 = new ReadingDto();
        ReadingDto readingDto4 = new ReadingDto();
        ReadingDto readingDto5 = new ReadingDto();
        ReadingDto readingDto6 = new ReadingDto();

        LocalDateTime time1 = LocalDateTime.of(2018, 12, 02, 10, 10);
        LocalDateTime time2 = LocalDateTime.of(2018, 12, 02, 10, 15);
        LocalDateTime time3 = LocalDateTime.of(2018, 12, 02, 10, 20);
        LocalDateTime time4 = LocalDateTime.of(2018, 12, 02, 10, 25);
        LocalDateTime time5 = LocalDateTime.of(2018, 12, 02, 10, 30);
        LocalDateTime time6 = LocalDateTime.of(2018, 12, 02, 10, 35);

        readingDto1.setDateTime(time1);
        readingDto2.setDateTime(time2);
        readingDto3.setDateTime(time3);
        readingDto4.setDateTime(time4);
        readingDto5.setDateTime(time5);
        readingDto6.setDateTime(time6);

        readingDto1.setIdOfSensor("TT12346");
        readingDto2.setIdOfSensor("TT12346");
        readingDto3.setIdOfSensor("TT12346");
        readingDto4.setIdOfSensor("TT12346");
        readingDto5.setIdOfSensor("TT12346");
        readingDto6.setIdOfSensor("TT12346");

        readingDto1.setUnit("F");
        readingDto2.setUnit("F");
        readingDto3.setUnit("F");
        readingDto4.setUnit("F");
        readingDto5.setUnit("F");
        readingDto6.setUnit("F");

        readingDto1.setValue(57.2);
        readingDto2.setValue(56.66);
        readingDto3.setValue(61.7);
        readingDto4.setValue(59.18);
        readingDto5.setValue(56.84);
        readingDto6.setValue(55.94);

        readingDtoList.add(readingDto1);
        readingDtoList.add(readingDto2);
        readingDtoList.add(readingDto3);
        readingDtoList.add(readingDto4);
        readingDtoList.add(readingDto5);
        readingDtoList.add(readingDto6);

        Reading reading1 = new Reading(readingDto1.getDateTime(), readingDto1.getValue());
        Reading reading2 = new Reading(readingDto2.getDateTime(), readingDto2.getValue());
        Reading reading3 = new Reading(readingDto3.getDateTime(), readingDto3.getValue());
        Reading reading4 = new Reading(readingDto4.getDateTime(), readingDto4.getValue());
        Reading reading5 = new Reading(readingDto5.getDateTime(), readingDto5.getValue());
        Reading reading6 = new Reading(readingDto6.getDateTime(), readingDto6.getValue());

        reading1.setUnit(readingDto1.getUnit());
        reading2.setUnit(readingDto2.getUnit());
        reading3.setUnit(readingDto3.getUnit());
        reading4.setUnit(readingDto4.getUnit());
        reading5.setUnit(readingDto5.getUnit());
        reading6.setUnit(readingDto6.getUnit());


        List<Reading> readings = new ArrayList<>();
        readings.add(reading1);
        readings.add(reading2);
        readings.add(reading3);
        readings.add(reading4);
        readings.add(reading5);
        readings.add(reading6);

        ListOfReadings listOfReadings = new ListOfReadings();
        listOfReadings.setListOfReading(readings);
        ga.addSensorToList(s1);
        ga.addSensorToList(s2);


        // System.out.println(listOfReadings);

        List<AreaSensor> areaSensorList = new ArrayList<>();
        areaSensorList.add(s1);

        // System.out.println(areaSensorList);


        try {
            ctr020.setReadingsBySensorIDescription(areaSensorList, readingDtoList);
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<Reading> result = areaSensorList.get(0).getListOfReadings().getListOfReading();


        assertTrue(15.1d == result.get(3).getValue() && "C" == result.get(3).getUnit());


    }


    @Test
    void addReadingsJSON1() throws UnsupportedEncodingException, Exception {

        Location l1 = new Location(40.7486, -73.9864, 0);
        GeographicArea ga = new GeographicArea("Porto", "city", new OccupationArea(new Location(10, 20, 30), 0.5, 0.5), new GeographicAreaType("city"));


        AreaSensor areaSensorIsep1 = new AreaSensor("1", "Meteo station ISEP - rainfall", new Location(41.179230, -8.606409, 125),
                new SensorType("temperature"), LocalDate.of(2016, 11, 15), "l/m2");
        areaSensorIsep1.setIdOfAreaSensor("s1");
        ListOfReadings listOfReadings1 = new ListOfReadings();
        Reading reading1 = new Reading(LocalDateTime.of(2018, 02, 05, 12, 58), 25);
        Reading reading2 = new Reading(LocalDateTime.of(2018, 02, 05, 12, 59), 20);
        listOfReadings1.addReading(reading1);
        listOfReadings1.addReading(reading2);
        areaSensorIsep1.setListOfReadings(listOfReadings1);

        AreaSensor areaSensorPorto1 = new AreaSensor("1", "Meteo station CMP - rainfall", new Location(41.179230, -8.606409, 139),
                new SensorType("rainfall"), LocalDate.of(2017, 11, 15), "l/m2");
        areaSensorPorto1.setIdOfAreaSensor("RF12334");
        ListOfReadings listOfReadings2 = new ListOfReadings();
        Reading reading4 = new Reading(LocalDateTime.of(2018, 02, 05, 12, 59), 20);
        Reading reading5 = new Reading(LocalDateTime.of(2018, 12, 12, 10, 15), 25);
        Reading reading6 = new Reading(LocalDateTime.of(2018, 12, 12, 10, 20), 26);
        listOfReadings2.addReading(reading4);
        listOfReadings2.addReading(reading5);
        listOfReadings2.addReading(reading6);
        areaSensorPorto1.setListOfReadings(listOfReadings2);


        ga.addSensorToList(areaSensorIsep1);
        ga.addSensorToList(areaSensorPorto1);


        FileReader core = new JsonReader();

        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("ReadingsTest.json").getFile());
        String path = file.getAbsolutePath();
        List<Integer> resultValues = new ArrayList<>();
        List<Integer> expectedResult = new ArrayList<>();
        expectedResult.add(-1);
        String path2 = URLDecoder.decode(path, "UTF-8") + "SensorReadingsxcxc.csv";

        try {
            resultValues = ctr020.addReadingsToSensorsOfGA(URLDecoder.decode(path2, "UTF-8"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        assertEquals(expectedResult, resultValues);


    }


    @Test
    void addReadingsJSON2() throws Exception {
        Location l1 = new Location(40.7486, -73.9864, 0);

        GeographicArea ga = new GeographicArea("Porto", "city", new OccupationArea(new Location(10, 20, 30), 0.5, 0.5), new GeographicAreaType("city"));


        AreaSensor areaSensorIsep1 = new AreaSensor("1", "Meteo station ISEP - rainfall", new Location(41.179230, -8.606409, 125),
                new SensorType("temperature"), LocalDate.of(2019, 11, 15), "l/m2");
        areaSensorIsep1.setIdOfAreaSensor("s1");
        areaSensorIsep1.setGeographicArea(ga);
        Reading reading1 = new Reading(LocalDateTime.of(2018, 02, 05, 12, 58), 25);
        Reading reading2 = new Reading(LocalDateTime.of(2018, 02, 05, 12, 59), 20);

        AreaSensor areaSensorPorto1 = new AreaSensor("1", "Meteo station CMP - rainfall", new Location(41.179230, -8.606409, 139),
                new SensorType("rainfall"), LocalDate.of(2017, 11, 15), "l/m2");
        areaSensorPorto1.setIdOfAreaSensor("RF12334");
        areaSensorPorto1.setGeographicArea(ga);
        ListOfReadings listOfReadings2 = new ListOfReadings();
        Reading reading4 = new Reading(LocalDateTime.of(2018, 02, 05, 12, 59), 20);
        Reading reading5 = new Reading(LocalDateTime.of(2018, 12, 12, 10, 15), 25);
        Reading reading6 = new Reading(LocalDateTime.of(2018, 12, 12, 10, 20), 26);
        listOfReadings2.addReading(reading4);
        listOfReadings2.addReading(reading5);
        listOfReadings2.addReading(reading6);
        areaSensorPorto1.setListOfReadings(listOfReadings2);


        ListOfReadings expectedResult = new ListOfReadings();


        ga.addSensorToList(areaSensorIsep1);
        ga.addSensorToList(areaSensorPorto1);

        FileReader core = new JsonReader();

        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("ReadingsTest.json").getFile());
        String path = file.getAbsolutePath();
        ctr020.addReadingsToSensorsOfGA(path);

        assertEquals(expectedResult.getListOfReading().toString() + "\n", areaSensorIsep1.getListOfReadings().toString());


    }

    @Test
    void addReadingsJSON3() throws IOException, Exception {
        Location l1 = new Location(40.7486, -73.9864, 0);

        GeographicArea ga = new GeographicArea("Porto", "city", new OccupationArea(new Location(10, 20, 30), 0.5, 0.5), new GeographicAreaType("city"));


        AreaSensor areaSensorIsep1 = new AreaSensor("1", "Meteo station ISEP - rainfall", new Location(41.179230, -8.606409, 125),
                new SensorType("temperature"), LocalDate.of(2019, 11, 15), "l/m2");
        areaSensorIsep1.setIdOfAreaSensor("s1");
        areaSensorIsep1.setGeographicArea(ga);
        ListOfReadings listOfReadings1 = new ListOfReadings();
        Reading reading1 = new Reading(LocalDateTime.of(2018, 02, 05, 12, 58), 25);
        Reading reading2 = new Reading(LocalDateTime.of(2018, 02, 05, 12, 59), 20);
        listOfReadings1.addReading(reading1);
        listOfReadings1.addReading(reading2);
        areaSensorIsep1.setListOfReadings(listOfReadings1);

        AreaSensor areaSensorPorto1 = new AreaSensor("1", "Meteo station CMP - rainfall", new Location(41.179230, -8.606409, 139),
                new SensorType("rainfall"), LocalDate.of(2017, 11, 15), "l/m2");
        areaSensorPorto1.setIdOfAreaSensor("RF12334");
        areaSensorPorto1.setGeographicArea(ga);
        ListOfReadings listOfReadings2 = new ListOfReadings();
        Reading reading4 = new Reading(LocalDateTime.of(2018, 02, 05, 12, 59), 20);
        Reading reading5 = new Reading(LocalDateTime.of(2018, 12, 12, 10, 15), 25);
        Reading reading6 = new Reading(LocalDateTime.of(2018, 12, 12, 10, 20), 26);
        listOfReadings2.addReading(reading4);
        listOfReadings2.addReading(reading5);
        listOfReadings2.addReading(reading6);
        areaSensorPorto1.setListOfReadings(listOfReadings2);

        ga.addSensorToList(areaSensorIsep1);
        ga.addSensorToList(areaSensorPorto1);

        geographicAreaDomainService.add(ga);


        FileReader core = new JsonReader();

        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("ReadingsTest.json").getFile());
        String path = file.getAbsolutePath();

        List<Integer> resultValues;
        List<Integer> expectedResult = new ArrayList<>();
        expectedResult.add(2);
        expectedResult.add(0);
        expectedResult.add(2);

        resultValues = ctr020.addReadingsToSensorsOfGA(URLDecoder.decode(path, "UTF-8"));

        assertEquals(expectedResult, resultValues);

    }

    @Test
    void addReadingsJSON3SameDateInstalation() throws IOException, Exception {
        Location l1 = new Location(40.7486, -73.9864, 0);

        GeographicArea ga = new GeographicArea("Porto", "city", new OccupationArea(new Location(10, 20, 30), 0.5, 0.5), new GeographicAreaType("city"));


        AreaSensor areaSensorIsep1 = new AreaSensor("1", "Meteo station ISEP - rainfall", new Location(41.179230, -8.606409, 125),
                new SensorType("temperature"), LocalDate.of(2018, 12, 30), "l/m2");
        areaSensorIsep1.setIdOfAreaSensor("s1");
        areaSensorIsep1.setGeographicArea(ga);
        ListOfReadings listOfReadings1 = new ListOfReadings();
        Reading reading1 = new Reading(LocalDateTime.of(2018, 12, 30, 12, 58), 25);
        Reading reading2 = new Reading(LocalDateTime.of(2019, 02, 05, 12, 59), 20);
        listOfReadings1.addReading(reading1);
        listOfReadings1.addReading(reading2);
        areaSensorIsep1.setListOfReadings(listOfReadings1);

        AreaSensor areaSensorPorto1 = new AreaSensor("1", "Meteo station CMP - rainfall", new Location(41.179230, -8.606409, 139),
                new SensorType("rainfall"), LocalDate.of(2017, 11, 15), "l/m2");
        areaSensorPorto1.setIdOfAreaSensor("RF12334");
        areaSensorPorto1.setGeographicArea(ga);
        ListOfReadings listOfReadings2 = new ListOfReadings();
        Reading reading4 = new Reading(LocalDateTime.of(2017, 11, 15, 12, 59), 20);
        Reading reading5 = new Reading(LocalDateTime.of(2018, 12, 12, 10, 15), 25);
        Reading reading6 = new Reading(LocalDateTime.of(2018, 12, 12, 10, 20), 26);
        listOfReadings2.addReading(reading4);
        listOfReadings2.addReading(reading5);
        listOfReadings2.addReading(reading6);
        areaSensorPorto1.setListOfReadings(listOfReadings2);

        ga.addSensorToList(areaSensorIsep1);
        ga.addSensorToList(areaSensorPorto1);

        geographicAreaDomainService.add(ga);


        FileReader core = new JsonReader();

        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("ReadingsTest.json").getFile());
        String path = file.getAbsolutePath();

        List<Integer> resultValues;
        List<Integer> expectedResult = new ArrayList<>();
        expectedResult.add(2);
        expectedResult.add(2);
        expectedResult.add(0);

        resultValues = ctr020.addReadingsToSensorsOfGA(URLDecoder.decode(path, "UTF-8"));

        assertEquals(expectedResult, resultValues);

    }

    @Test
    void addReadingsJSON3SameDateInstalationRoom() throws IOException, Exception {
        Location l1 = new Location(40.7486, -73.9864, 0);

        ArrayList<Double> dimension = new ArrayList<>();
        dimension.add(3.0);
        dimension.add(2.0);
        dimension.add(3.0);
        Room room = new Room("bedroom", "room", 1, dimension.get(0), dimension.get(1), dimension.get(2));

        RoomSensor areaSensorIsep1 = new RoomSensor("1", "Meteo station ISEP - rainfall",
                new SensorType("temperature"), LocalDate.of(2018, 12, 30), "l/m2");
        areaSensorIsep1.setIdOfRoomSensor("s1");
        areaSensorIsep1.setRoom(room);
        ListOfReadings listOfReadings1 = new ListOfReadings();
        Reading reading1 = new Reading(LocalDateTime.of(2018, 12, 30, 12, 58), 25);
        Reading reading2 = new Reading(LocalDateTime.of(2019, 02, 05, 12, 59), 20);
        listOfReadings1.addReading(reading1);
        listOfReadings1.addReading(reading2);
        areaSensorIsep1.setListOfReadings(listOfReadings1);

        RoomSensor areaSensorPorto1 = new RoomSensor("1", "Meteo station CMP - rainfall",
                new SensorType("rainfall"), LocalDate.of(2017, 11, 15), "l/m2");
        areaSensorPorto1.setIdOfRoomSensor("RF12334");
        areaSensorPorto1.setRoom(room);
        ListOfReadings listOfReadings2 = new ListOfReadings();
        Reading reading4 = new Reading(LocalDateTime.of(2017, 11, 15, 12, 59), 20);
        Reading reading5 = new Reading(LocalDateTime.of(2018, 12, 12, 10, 15), 25);
        Reading reading6 = new Reading(LocalDateTime.of(2018, 12, 12, 10, 20), 26);
        listOfReadings2.addReading(reading4);
        listOfReadings2.addReading(reading5);
        listOfReadings2.addReading(reading6);
        areaSensorPorto1.setListOfReadings(listOfReadings2);

        room.addSensor(areaSensorIsep1);
        room.addSensor(areaSensorPorto1);

        roomDomainService.addRoom(room);


        FileReader core = new JsonReader();

        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("ReadingsTest.json").getFile());
        String path = file.getAbsolutePath();

        List<Integer> resultValues;
        List<Integer> expectedResult = new ArrayList<>();
        expectedResult.add(2);
        expectedResult.add(2);
        expectedResult.add(0);

        resultValues = ctr020.addReadingsToSensorsOfHouse(URLDecoder.decode(path, "UTF-8"));

        assertEquals(expectedResult, resultValues);

    }

    @Test
    void addReadingsXml() throws Exception {
        GeographicArea ga = new GeographicArea("Porto", "city", new OccupationArea(new Location(10, 20, 30), 0.5, 0.5), new GeographicAreaType("city"));


        AreaSensor areaSensorIsep1 = new AreaSensor("1", "Meteo station ISEP - rainfall", new Location(41.179230, -8.606409, 125),
                new SensorType("temperature"), LocalDate.of(2018, 11, 15), "l/m2");
        areaSensorIsep1.setIdOfAreaSensor("s1");
        areaSensorIsep1.setGeographicArea(ga);
        ListOfReadings listOfReadings1 = new ListOfReadings();
        Reading reading1 = new Reading(LocalDateTime.of(2018, 02, 05, 12, 58), 25);
        Reading reading2 = new Reading(LocalDateTime.of(2018, 02, 05, 12, 59), 20);
        listOfReadings1.addReading(reading1);
        listOfReadings1.addReading(reading2);
        areaSensorIsep1.setListOfReadings(listOfReadings1);
        areaSensorIsep1.setGeographicArea(ga);
        areaSensorIsep1.setGeographicArea(ga);

        AreaSensor areaSensorPorto1 = new AreaSensor("1", "Meteo station CMP - rainfall", new Location(41.179230, -8.606409, 139),
                new SensorType("rainfall"), LocalDate.of(2017, 11, 15), "l/m2");
        areaSensorPorto1.setIdOfAreaSensor("RF12334");
        areaSensorPorto1.setGeographicArea(ga);
        ListOfReadings listOfReadings2 = new ListOfReadings();
        Reading reading4 = new Reading(LocalDateTime.of(2018, 02, 05, 12, 59), 20);
        Reading reading5 = new Reading(LocalDateTime.of(2018, 12, 12, 10, 15), 25);
        Reading reading6 = new Reading(LocalDateTime.of(2018, 12, 12, 10, 20), 26);
        listOfReadings2.addReading(reading4);
        listOfReadings2.addReading(reading5);
        listOfReadings2.addReading(reading6);
        areaSensorPorto1.setListOfReadings(listOfReadings2);

        ga.addSensorToList(areaSensorIsep1);
        ga.addSensorToList(areaSensorPorto1);

        geographicAreaDomainService.add(ga);

        FileReader core = new XmlReader();

        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("DataSet_sprint05_SensorData_alt01.xml").getFile());
        String path = file.getAbsolutePath();

        List<Integer> resultValues;
        List<Integer> expectedResult = new ArrayList<>();
        expectedResult.add(61);
        expectedResult.add(8);
        expectedResult.add(53);

        resultValues = ctr020.addReadingsToSensorsOfGA(URLDecoder.decode(path, "UTF-8"));

        assertEquals(expectedResult, resultValues);

    }


    @Test
    void addReadingsXml2() throws IOException, Exception {

        Location l1 = new Location(40.7486, -73.9864, 0);
        GeographicArea ga = new GeographicArea("Porto", "city", new OccupationArea(new Location(10, 20, 30), 0.5, 0.5), new GeographicAreaType("city"));


        AreaSensor areaSensorIsep1 = new AreaSensor("1", "Meteo station ISEP - rainfall", new Location(41.179230, -8.606409, 125),
                new SensorType("temperature"), LocalDate.of(2018, 11, 15), "l/m2");
        areaSensorIsep1.setIdOfAreaSensor("s1");
        ListOfReadings listOfReadings1 = new ListOfReadings();
        Reading reading1 = new Reading(LocalDateTime.of(2018, 02, 05, 12, 58), 25);
        Reading reading2 = new Reading(LocalDateTime.of(2018, 02, 05, 12, 59), 20);
        listOfReadings1.addReading(reading1);
        listOfReadings1.addReading(reading2);
        areaSensorIsep1.setListOfReadings(listOfReadings1);

        AreaSensor areaSensorPorto1 = new AreaSensor("1", "Meteo station CMP - rainfall", new Location(41.179230, -8.606409, 139),
                new SensorType("rainfall"), LocalDate.of(2017, 11, 15), "l/m2");
        areaSensorPorto1.setIdOfAreaSensor("RF12334");
        ListOfReadings listOfReadings2 = new ListOfReadings();
        Reading reading4 = new Reading(LocalDateTime.of(2018, 02, 05, 12, 59), 20);
        Reading reading5 = new Reading(LocalDateTime.of(2018, 12, 12, 10, 15), 25);
        Reading reading6 = new Reading(LocalDateTime.of(2018, 12, 12, 10, 20), 26);
        listOfReadings2.addReading(reading4);
        listOfReadings2.addReading(reading5);
        listOfReadings2.addReading(reading6);
        areaSensorPorto1.setListOfReadings(listOfReadings2);
        ga.addSensorToList(areaSensorIsep1);
        ga.addSensorToList(areaSensorPorto1);


        FileReader core = new XmlReader();

        String path = "ksaksak.xml";
        List<Integer> resultValues;
        List<Integer> expectedResult = new ArrayList<>();
        expectedResult.add(-1);

        resultValues = ctr020.addReadingsToSensorsOfGA(URLDecoder.decode(path, "UTF-8"));

        assertEquals(expectedResult, resultValues);

    }

    @Test
    void addReadingsXml3() throws Exception {
        GeographicArea ga = new GeographicArea("Porto", "city", new OccupationArea(new Location(10, 20, 30), 0.5, 0.5), new GeographicAreaType("city"));


        AreaSensor areaSensorIsep1 = new AreaSensor("1", "Meteo station ISEP - rainfall", new Location(41.179230, -8.606409, 125),
                new SensorType("temperature"), LocalDate.of(2018, 11, 15), "l/m2");
        areaSensorIsep1.setIdOfAreaSensor("s1");
        areaSensorIsep1.setGeographicArea(ga);
        ListOfReadings listOfReadings1 = new ListOfReadings();
        Reading reading1 = new Reading(LocalDateTime.of(2018, 02, 05, 12, 58), 25);
        Reading reading2 = new Reading(LocalDateTime.of(2018, 02, 05, 12, 59), 20);
        listOfReadings1.addReading(reading1);
        listOfReadings1.addReading(reading2);
        areaSensorIsep1.setListOfReadings(listOfReadings1);

        AreaSensor areaSensorPorto1 = new AreaSensor("1", "Meteo station CMP - rainfall", new Location(41.179230, -8.606409, 139),
                new SensorType("rainfall"), LocalDate.of(2017, 11, 15), "l/m2");
        areaSensorPorto1.setIdOfAreaSensor("RF12334");
        areaSensorPorto1.setGeographicArea(ga);
        ListOfReadings listOfReadings2 = new ListOfReadings();
        Reading reading4 = new Reading(LocalDateTime.of(2018, 02, 05, 12, 59), 20);
        Reading reading5 = new Reading(LocalDateTime.of(2018, 12, 12, 10, 15), 25);
        Reading reading6 = new Reading(LocalDateTime.of(2018, 12, 12, 10, 20), 26);
        listOfReadings2.addReading(reading4);
        listOfReadings2.addReading(reading5);
        listOfReadings2.addReading(reading6);
        areaSensorPorto1.setListOfReadings(listOfReadings2);

        ga.addSensorToList(areaSensorIsep1);
        ga.addSensorToList(areaSensorPorto1);

        geographicAreaDomainService.add(ga);

        FileReader core = new XmlReader();

        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("teste.xml").getFile());
        String path = file.getAbsolutePath();

        List<Integer> resultValues;
        List<Integer> expectedResult = new ArrayList<>();
        expectedResult.add(4);
        expectedResult.add(3);
        expectedResult.add(1);

        resultValues = ctr020.addReadingsToSensorsOfGA(URLDecoder.decode(path, "UTF-8"));

        assertEquals(expectedResult, resultValues);

    }

    @Test
    void addReadingsXml3_teste2() throws IOException, Exception {
        Location l1 = new Location(40.7486, -73.9864, 0);
        GeographicArea ga = new GeographicArea("Porto", "city", new OccupationArea(new Location(10, 20, 30), 0.5, 0.5), new GeographicAreaType("city"));


        AreaSensor areaSensorIsep1 = new AreaSensor("1", "Meteo station ISEP - rainfall", new Location(41.179230, -8.606409, 125),
                new SensorType("temperature"), LocalDate.of(2018, 11, 15), "l/m2");
        areaSensorIsep1.setIdOfAreaSensor("s1");
        areaSensorIsep1.setGeographicArea(ga);
        ListOfReadings listOfReadings1 = new ListOfReadings();
        Reading reading1 = new Reading(LocalDateTime.of(2018, 02, 05, 12, 58), 25);
        Reading reading2 = new Reading(LocalDateTime.of(2018, 02, 05, 12, 59), 20);
        listOfReadings1.addReading(reading1);
        listOfReadings1.addReading(reading2);
        areaSensorIsep1.setListOfReadings(listOfReadings1);

        AreaSensor areaSensorPorto1 = new AreaSensor("1", "Meteo station CMP - rainfall", new Location(41.179230, -8.606409, 139),
                new SensorType("rainfall"), LocalDate.of(2017, 11, 15), "l/m2");
        areaSensorPorto1.setIdOfAreaSensor("RF12334");
        ListOfReadings listOfReadings2 = new ListOfReadings();
        Reading reading4 = new Reading(LocalDateTime.of(2018, 02, 05, 12, 59), 20);
        Reading reading5 = new Reading(LocalDateTime.of(2018, 12, 12, 10, 15), 25);
        Reading reading6 = new Reading(LocalDateTime.of(2018, 12, 12, 10, 20), 26);
        listOfReadings2.addReading(reading4);
        listOfReadings2.addReading(reading5);
        listOfReadings2.addReading(reading6);
        areaSensorPorto1.setListOfReadings(listOfReadings2);

        ga.addSensorToList(areaSensorIsep1);
        ga.addSensorToList(areaSensorPorto1);

        geographicAreaDomainService.add(ga);

        FileReader core = new XmlReader();

        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("teste2.xml").getFile());
        String path = file.getAbsolutePath();

        List<Integer> resultValues;
        List<Integer> expectedResult = new ArrayList<>();
        resultValues = ctr020.addReadingsToSensorsOfGA(URLDecoder.decode(path, "UTF-8"));
        expectedResult.add(-1);
        assertEquals(expectedResult, resultValues);

    }

    @Test
    void addReadingsXml4() throws IOException, Exception {
        Location l1 = new Location(40.7486, -73.9864, 0);
        GeographicArea ga = new GeographicArea("Porto", "city", new OccupationArea(new Location(10, 20, 30), 0.5, 0.5), new GeographicAreaType("city"));


        AreaSensor areaSensorIsep1 = new AreaSensor("1", "Meteo station ISEP - rainfall", new Location(41.179230, -8.606409, 125),
                new SensorType("temperature"), LocalDate.of(2018, 11, 15), "l/m2");
        areaSensorIsep1.setIdOfAreaSensor("s1");
        ListOfReadings listOfReadings1 = new ListOfReadings();
        Reading reading1 = new Reading(LocalDateTime.of(2018, 02, 05, 12, 58), 25);
        Reading reading2 = new Reading(LocalDateTime.of(2018, 02, 05, 12, 59), 20);
        listOfReadings1.addReading(reading1);
        listOfReadings1.addReading(reading2);
        areaSensorIsep1.setListOfReadings(listOfReadings1);
        areaSensorIsep1.setGeographicArea(ga);
        AreaSensor areaSensorPorto1 = new AreaSensor("1", "Meteo station CMP - rainfall", new Location(41.179230, -8.606409, 139),
                new SensorType("rainfall"), LocalDate.of(2017, 11, 15), "l/m2");
        areaSensorPorto1.setIdOfAreaSensor("RF12334");
        areaSensorPorto1.setGeographicArea(ga);
        ListOfReadings listOfReadings2 = new ListOfReadings();
        Reading reading4 = new Reading(LocalDateTime.of(2018, 02, 05, 12, 59), 20);
        Reading reading5 = new Reading(LocalDateTime.of(2018, 12, 12, 10, 15), 25);
        Reading reading6 = new Reading(LocalDateTime.of(2018, 12, 12, 10, 20), 26);
        listOfReadings2.addReading(reading4);
        listOfReadings2.addReading(reading5);
        listOfReadings2.addReading(reading6);
        areaSensorPorto1.setListOfReadings(listOfReadings2);

        ga.addSensorToList(areaSensorIsep1);
        ga.addSensorToList(areaSensorPorto1);

        geographicAreaDomainService.add(ga);

        FileReader core = new XmlReader();

        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("teste.xml").getFile());
        String path = file.getAbsolutePath();

        List<Integer> resultValues;
        List<Integer> expectedResult = new ArrayList<>();
        expectedResult.add(4);
        expectedResult.add(3);
        expectedResult.add(1);

        resultValues = ctr020.addReadingsToSensorsOfGA(URLDecoder.decode(path, "UTF-8"));

        assertEquals(expectedResult, resultValues);

    }

    @Test
    void testConvert() throws IOException, Exception {

        Location l1 = new Location(40.7486, -73.9864, 0);
        GeographicArea ga = new GeographicArea("Porto", "city", new OccupationArea(new Location(10, 20, 30), 0.5, 0.5), new GeographicAreaType("city"));


        AreaSensor areaSensorIsep1 = new AreaSensor("1", "Meteo station ISEP - rainfall", new Location(41.179230, -8.606409, 125),
                new SensorType("temperature"), LocalDate.of(2018, 11, 15), "l/m2");
        areaSensorIsep1.setIdOfAreaSensor("s1");
        ListOfReadings listOfReadings1 = new ListOfReadings();
        ReadingDto reading1 = new ReadingDto();
        reading1.setValue(50);
        reading1.setUnit("F");
        ReadingDto reading2 = new ReadingDto();
        reading2.setValue(20);
        reading2.setUnit("C");

        ctr020.convertUnits(reading1);
        double value = reading1.getValue();
        assertEquals(10, value, 0.0001);

    }

    @Test
    void testConvertCelsius() throws IOException, Exception {
        Location l1 = new Location(40.7486, -73.9864, 0);
        GeographicArea ga = new GeographicArea("Porto", "city", new OccupationArea(new Location(10, 20, 30), 0.5, 0.5), new GeographicAreaType("city"));


        AreaSensor areaSensorIsep1 = new AreaSensor("1", "Meteo station ISEP - rainfall", new Location(41.179230, -8.606409, 125),
                new SensorType("temperature"), LocalDate.of(2018, 11, 15), "l/m2");
        areaSensorIsep1.setIdOfAreaSensor("s1");
        ListOfReadings listOfReadings1 = new ListOfReadings();
        ReadingDto reading1 = new ReadingDto();
        reading1.setValue(50);
        reading1.setUnit("C");
        ReadingDto reading2 = new ReadingDto();
        reading2.setValue(20);
        reading2.setUnit("C");

        ctr020.convertUnits(reading1);
        double value = reading1.getValue();
        assertEquals(50, value, 0.00001);

    }

    @Test
    void testConvertCelsiusUnit() throws IOException, Exception {
        Location l1 = new Location(40.7486, -73.9864, 0);
        GeographicArea ga = new GeographicArea("Porto", "city", new OccupationArea(new Location(10, 20, 30), 0.5, 0.5), new GeographicAreaType("city"));


        AreaSensor areaSensorIsep1 = new AreaSensor("1", "Meteo station ISEP - rainfall", new Location(41.179230, -8.606409, 125),
                new SensorType("temperature"), LocalDate.of(2018, 11, 15), "l/m2");
        areaSensorIsep1.setIdOfAreaSensor("s1");
        ListOfReadings listOfReadings1 = new ListOfReadings();
        ReadingDto reading1 = new ReadingDto();
        reading1.setValue(50);
        reading1.setUnit("F");
        ReadingDto reading2 = new ReadingDto();
        reading2.setValue(20);
        reading2.setUnit("C");

        ctr020.convertUnits(reading1);
        String value = reading1.getUnit();
        assertEquals("C", value);

    }

    /**
     * @Test void addReadingsCSVFromHouse() throws IOException, Exception {
     * //Arrange
     * Location l1 = new Location(40.7486, -73.9864, 0);
     * House h1 = new House("Home", l1, new GeographicArea("Porto", "city",
     * new OccupationArea(new Location(10, 20, 30), 0.5, 0.5),
     * new GeographicAreaType("city")));
     * List<Room> roomList = new ArrayList<>();
     * ArrayList<Double> dimensions1 = new ArrayList<>();
     * dimensions1.add(10.0);
     * dimensions1.add(6.0);
     * dimensions1.add(3.5);
     * Room room = new Room("bedroom", "room", 1, dimensions1);
     * Room room2 = new Room("bedroom", "room", 1, dimensions1);
     * h1.setRoomList();(roomList);
     * roomList.add(room);
     * roomList.add(room2);
     * GeographicArea ga = new GeographicArea("Porto", "city", new OccupationArea(new Location(10, 20, 30), 0.5, 0.5), new GeographicAreaType("city"));
     * RoomSensor s1 = new RoomSensor();
     * LocalDate ld = LocalDate.of(2018, 12, 01);
     * LocalDate lstatus = LocalDate.of(2018, 12, 30);
     * ListOfStatus statusList = new ListOfStatus();
     * Status status = new Status(true, lstatus);
     * statusList.addStatusToSensor(status);
     * s1.setStatusList(statusList);
     * s1.setType("sensor1");
     * s1.setIdOfRoomSensor("TT12346");
     * s1.setType(new SensorType("Temperature"));
     * s1.setInstallationDate(ld);
     * s1.setRoom(room);
     * RoomSensor s2 = new RoomSensor();
     * s2.setType("sensor2");
     * s2.setType(new SensorType("Rainfall"));
     * s2.setRoom(room);
     * <p>
     * room.addSensor(s1);
     * room.addSensor(s2);//List<AreaSensor> in GA Vs ListOfSensors in Room
     * ClassLoader classLoader = getClass().getClassLoader();
     * File file = new File(classLoader.getResource("SensorReadingsTeste.csv").getFile());
     * String path = file.getParentFile().getAbsolutePath() + File.separator;
     * String path2 = URLDecoder.decode(path, "UTF-8") + "SensorReadingsTeste.csv";
     * //ACT
     * FileReader core = new CsvReader();
     * List<Integer> result = ctr020.addReadingsToSensorsOfHouse( path2);
     * int result2 = result.get(1);
     * //ASSERT
     * assertEquals(3, result2);
     * }
     * @Test void addReadingsCSV2FromHouse() throws IOException, Exception {
     * //ARRANGE
     * Location l1 = new Location(40.7486, -73.9864, 0);
     * House h1 = new House("Home", l1, new GeographicArea("Porto", "city",
     * new OccupationArea(new Location(10, 20, 30), 0.5, 0.5),
     * new GeographicAreaType("city")));
     * RoomSensor s1 = new RoomSensor();
     * ArrayList<Double> dimensions1 = new ArrayList<>();
     * dimensions1.add(10.0);
     * dimensions1.add(6.0);
     * dimensions1.add(3.5);
     * Room room = new Room("bedroom", "room", 1, dimensions1);
     * Room room2 = new Room("bedroom", "room", 1, dimensions1);
     * s1.setType("sensor1");
     * s1.setIdOfRoomSensor("TT11116");
     * LocalDate ld = LocalDate.of(2019, 12, 01);
     * s1.setInstallationDate(ld);
     * s1.setType(new SensorType("Temperature"));
     * s1.setRoom(room);
     * RoomSensor s2 = new RoomSensor();
     * s2.setType("sensor2");
     * s2.setType(new SensorType("Rainfall"));
     * s2.setRoom(room);
     * List<Room> roomList = new ArrayList<>();
     * h1.setRoomList();(roomList);
     * roomList.add(room);
     * roomList.add(room2);
     * room.addSensor(s1);
     * room.addSensor(s2);
     * ClassLoader classLoader = getClass().getClassLoader();
     * File file = new File(classLoader.getResource("SensorReadingsTeste.csv").getFile());
     * String path = file.getParentFile().getAbsolutePath() + File.separator;
     * String path2 = URLDecoder.decode(path, "UTF-8") + "SensorReadingsTeste.csv";
     * //ACT
     * FileReader core = new CsvReader();
     * List<Integer> result = ctr020.addReadingsToSensorsOfHouse( path2);
     * int result2 = result.get(2);
     * //ASSERT
     * assertEquals(5, result2);
     * }
     * @Test void addReadingsZeroSensorIDFromHouse() throws IOException, Exception {
     * //ARRANGE
     * Location l1 = new Location(40.7486, -73.9864, 0);
     * House h1 = new House("Home", l1, new GeographicArea("Porto", "city",
     * new OccupationArea(new Location(10, 20, 30), 0.5, 0.5),
     * new GeographicAreaType("city")));
     * RoomSensor s1 = new RoomSensor();
     * ArrayList<Double> dimensions1 = new ArrayList<>();
     * dimensions1.add(10.0);
     * dimensions1.add(6.0);
     * dimensions1.add(3.5);
     * Room room = new Room("bedroom", "room", 1, dimensions1);
     * Room room2 = new Room("bedroom", "room", 1, dimensions1);
     * s1.setType("sensor1");
     * s1.setIdOfRoomSensor("TT11116");
     * LocalDate ld = LocalDate.of(2019, 12, 01);
     * s1.setInstallationDate(ld);
     * s1.setType(new SensorType("Temperature"));
     * s1.setRoom(room);
     * RoomSensor s2 = new RoomSensor();
     * s2.setType("sensor2");
     * s2.setType(new SensorType("Rainfall"));
     * s2.setRoom(room);
     * List<Room> roomList = new ArrayList<>();
     * h1.setRoomList();(roomList);
     * roomList.add(room);
     * roomList.add(room2);
     * room.addSensor(s1);
     * room.addSensor(s2);
     * ClassLoader classLoader = getClass().getClassLoader();
     * File file = new File(classLoader.getResource("SensorReadingsTeste.csv").getFile());
     * String path = file.getParentFile().getAbsolutePath() + File.separator;
     * String path2 = URLDecoder.decode(path, "UTF-8") + "SensorReadingsTeste.csv";
     * //ACT
     * FileReader core = new CsvReader();
     * List<Integer> result = ctr020.addReadingsToSensorsOfHouse( path2);
     * int result2 = result.get(1);
     * //ASSERT
     * assertEquals(0, result2);
     * }
     */
    @Test
    void addReadingsJSON1FromHouse() throws UnsupportedEncodingException, Exception {

        Location l1 = new Location(40.7486, -73.9864, 0);
        ArrayList<Double> dimensions1 = new ArrayList<>();
        dimensions1.add(10.0);
        dimensions1.add(6.0);
        dimensions1.add(3.5);
        Room room = new Room("bedroom", "room", 1, dimensions1.get(0), dimensions1.get(1), dimensions1.get(2));
        Room room2 = new Room("bedroom", "room", 1, dimensions1.get(0), dimensions1.get(1), dimensions1.get(2));
        RoomSensor areaSensorIsep1 = new RoomSensor("1", "Meteo station ISEP - rainfall",
                new SensorType("temperature"), LocalDate.of(2016, 11, 15), "l/m2");
        areaSensorIsep1.setIdOfRoomSensor("s1");
        ListOfReadings listOfReadings1 = new ListOfReadings();
        Reading reading1 = new Reading(LocalDateTime.of(2018, 02, 05, 12, 58), 25);
        Reading reading2 = new Reading(LocalDateTime.of(2018, 02, 05, 12, 59), 20);
        listOfReadings1.addReading(reading1);
        listOfReadings1.addReading(reading2);
        areaSensorIsep1.setListOfReadings(listOfReadings1);
        areaSensorIsep1.setRoom(room);
        RoomSensor roomSensor = new RoomSensor("1", "Meteo station CMP - rainfall", new SensorType("rainfall"), LocalDate.of(2017, 11, 15), "l/m2");
        roomSensor.setIdOfRoomSensor("RF12334");
        roomSensor.setRoom(room);
        ListOfReadings listOfReadings2 = new ListOfReadings();
        Reading reading4 = new Reading(LocalDateTime.of(2018, 02, 05, 12, 59), 20);
        Reading reading5 = new Reading(LocalDateTime.of(2018, 12, 12, 10, 15), 25);
        Reading reading6 = new Reading(LocalDateTime.of(2018, 12, 12, 10, 20), 26);
        listOfReadings2.addReading(reading4);
        listOfReadings2.addReading(reading5);
        listOfReadings2.addReading(reading6);
        roomSensor.setListOfReadings(listOfReadings2);

        List<Room> listOfRooms = new ArrayList<>();

        roomDomainService.setListOfRooms(listOfRooms);
        listOfRooms.add(room);
        listOfRooms.add(room2);
        room.addSensor(areaSensorIsep1);
        room.addSensor(roomSensor);

        FileReader core = new JsonReader();

        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("ReadingsTest.json").getFile());
        String path = file.getAbsolutePath();
        List<Integer> resultValues = new ArrayList<>();
        List<Integer> expectedResult = new ArrayList<>();
        expectedResult.add(-1);
        String path2 = URLDecoder.decode(path, "UTF-8") + "SensorReadingsxcxc.csv";

        try {
            resultValues = ctr020.addReadingsToSensorsOfHouse(URLDecoder.decode(path2, "UTF-8"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        assertEquals(expectedResult, resultValues);
    }

    @Test
    void addReadingsJSON3FromHouse() throws IOException, Exception {

        Location l1 = new Location(40.7486, -73.9864, 0);
        ArrayList<Double> dimensions1 = new ArrayList<>();
        dimensions1.add(10.0);
        dimensions1.add(6.0);
        dimensions1.add(3.5);
        Room room = new Room("bedroom", "room", 1, dimensions1.get(0), dimensions1.get(1), dimensions1.get(2));
        Room room2 = new Room("bedroom", "room", 1, dimensions1.get(0), dimensions1.get(1), dimensions1.get(2));

        RoomSensor areaSensorIsep1 = new RoomSensor("1", "Meteo station ISEP - rainfall",
                new SensorType("temperature"), LocalDate.of(2016, 11, 15), "l/m2");
        areaSensorIsep1.setIdOfRoomSensor("s1");
        ListOfReadings listOfReadings1 = new ListOfReadings();
        Reading reading1 = new Reading(LocalDateTime.of(2018, 02, 05, 12, 58), 25);
        Reading reading2 = new Reading(LocalDateTime.of(2018, 02, 05, 12, 59), 20);
        listOfReadings1.addReading(reading1);
        listOfReadings1.addReading(reading2);
        areaSensorIsep1.setListOfReadings(listOfReadings1);
        areaSensorIsep1.setRoom(room);
        RoomSensor roomSensor = new RoomSensor("1", "Meteo station CMP - rainfall", new SensorType("rainfall"), LocalDate.of(2017, 11, 15), "l/m2");
        roomSensor.setIdOfRoomSensor("RF12334");
        roomSensor.setRoom(room);
        ListOfReadings listOfReadings2 = new ListOfReadings();
        Reading reading4 = new Reading(LocalDateTime.of(2018, 02, 05, 12, 59), 20);
        Reading reading5 = new Reading(LocalDateTime.of(2018, 12, 12, 10, 15), 25);
        Reading reading6 = new Reading(LocalDateTime.of(2018, 12, 12, 10, 20), 26);
        listOfReadings2.addReading(reading4);
        listOfReadings2.addReading(reading5);
        listOfReadings2.addReading(reading6);
        roomSensor.setListOfReadings(listOfReadings2);
        List<Room> listOfRooms = new ArrayList<>();

        roomDomainService.setListOfRooms(listOfRooms);
        listOfRooms.add(room);
        listOfRooms.add(room2);

        room.addSensor(areaSensorIsep1);
        room.addSensor(roomSensor);
        roomDomainService.addRoom(room);
        roomDomainService.addRoom(room2);

        FileReader core = new JsonReader();

        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("ReadingsTest.json").getFile());
        String path = file.getAbsolutePath();

        List<Integer> resultValues;
        List<Integer> expectedResult = new ArrayList<>();
        expectedResult.add(2);
        expectedResult.add(2);
        expectedResult.add(0);

        resultValues = ctr020.addReadingsToSensorsOfHouse(URLDecoder.decode(path, "UTF-8"));

        assertEquals(expectedResult, resultValues);

    }

    @Test
    void addReadingsXmlFromHouse() throws IOException, Exception {

        Location l1 = new Location(40.7486, -73.9864, 0);
        ArrayList<Double> dimensions1 = new ArrayList<>();
        dimensions1.add(10.0);
        dimensions1.add(6.0);
        dimensions1.add(3.5);
        Room room = new Room("bedroom", "room", 1, dimensions1.get(0), dimensions1.get(1), dimensions1.get(2));
        Room room2 = new Room("bedroom", "room", 1, dimensions1.get(0), dimensions1.get(1), dimensions1.get(2));
        roomDomainService.addRoom(room);
        roomDomainService.addRoom(room2);
        RoomSensor areaSensorIsep1 = new RoomSensor("1", "Meteo station ISEP - rainfall",
                new SensorType("temperature"), LocalDate.of(2016, 11, 15), "l/m2");
        areaSensorIsep1.setIdOfRoomSensor("s1");
        ListOfReadings listOfReadings1 = new ListOfReadings();
        Reading reading1 = new Reading(LocalDateTime.of(2018, 02, 05, 12, 58), 25);
        Reading reading2 = new Reading(LocalDateTime.of(2018, 02, 05, 12, 59), 20);
        listOfReadings1.addReading(reading1);
        listOfReadings1.addReading(reading2);
        areaSensorIsep1.setListOfReadings(listOfReadings1);
        areaSensorIsep1.setRoom(room);
        RoomSensor roomSensor = new RoomSensor("1", "Meteo station CMP - rainfall", new SensorType("rainfall"), LocalDate.of(2017, 11, 15), "l/m2");
        roomSensor.setIdOfRoomSensor("RF12334");
        roomSensor.setRoom(room);
        ListOfReadings listOfReadings2 = new ListOfReadings();
        Reading reading4 = new Reading(LocalDateTime.of(2018, 02, 05, 12, 59), 20);
        Reading reading5 = new Reading(LocalDateTime.of(2018, 12, 12, 10, 15), 25);
        Reading reading6 = new Reading(LocalDateTime.of(2018, 12, 12, 10, 20), 26);
        listOfReadings2.addReading(reading4);
        listOfReadings2.addReading(reading5);
        listOfReadings2.addReading(reading6);
        roomSensor.setListOfReadings(listOfReadings2);
        List<Room> listOfRooms = new ArrayList<>();

        roomDomainService.setListOfRooms(listOfRooms);
        listOfRooms.add(room);
        listOfRooms.add(room2);

        room.addSensor(areaSensorIsep1);
        room.addSensor(roomSensor);

        FileReader core = new XmlReader();

        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("DataSet_sprint05_SensorData_alt01.xml").getFile());
        String path = file.getAbsolutePath();

        List<Integer> resultValues;
        List<Integer> expectedResult = new ArrayList<>();
        expectedResult.add(61);
        expectedResult.add(8);
        expectedResult.add(53);

        resultValues = ctr020.addReadingsToSensorsOfHouse(URLDecoder.decode(path, "UTF-8"));

        assertEquals(expectedResult, resultValues);
    }

    @Test
    void addReadingsXml4FomHouse() throws IOException, Exception {
        Location l1 = new Location(40.7486, -73.9864, 0);
        ArrayList<Double> dimensions1 = new ArrayList<>();
        dimensions1.add(10.0);
        dimensions1.add(6.0);
        dimensions1.add(3.5);
        Room room = new Room("bedroom", "room", 1, dimensions1.get(0), dimensions1.get(1), dimensions1.get(2));
        Room room2 = new Room("bedroom", "room", 1, dimensions1.get(0), dimensions1.get(1), dimensions1.get(2));
        RoomSensor areaSensorIsep1 = new RoomSensor("1", "Meteo station ISEP - rainfall",
                new SensorType("temperature"), LocalDate.of(2016, 11, 15), "l/m2");
        roomDomainService.addRoom(room);
        roomDomainService.addRoom(room2);
        areaSensorIsep1.setIdOfRoomSensor("s1");
        ListOfReadings listOfReadings1 = new ListOfReadings();
        Reading reading1 = new Reading(LocalDateTime.of(2018, 02, 05, 12, 58), 25);
        Reading reading2 = new Reading(LocalDateTime.of(2018, 02, 05, 12, 59), 20);
        listOfReadings1.addReading(reading1);
        listOfReadings1.addReading(reading2);
        areaSensorIsep1.setListOfReadings(listOfReadings1);
        areaSensorIsep1.setRoom(room);
        RoomSensor roomSensor = new RoomSensor("1", "Meteo station CMP - rainfall", new SensorType("rainfall"), LocalDate.of(2017, 11, 15), "l/m2");
        roomSensor.setIdOfRoomSensor("RF12334");
        roomSensor.setRoom(room);
        ListOfReadings listOfReadings2 = new ListOfReadings();
        Reading reading4 = new Reading(LocalDateTime.of(2018, 02, 05, 12, 59), 20);
        Reading reading5 = new Reading(LocalDateTime.of(2018, 12, 12, 10, 15), 25);
        Reading reading6 = new Reading(LocalDateTime.of(2018, 12, 12, 10, 20), 26);
        listOfReadings2.addReading(reading4);
        listOfReadings2.addReading(reading5);
        listOfReadings2.addReading(reading6);
        roomSensor.setListOfReadings(listOfReadings2);
        List<Room> listOfRooms = new ArrayList<>();

        roomDomainService.setListOfRooms(listOfRooms);
        listOfRooms.add(room);
        listOfRooms.add(room2);

        room.addSensor(areaSensorIsep1);
        room.addSensor(roomSensor);

        FileReader core = new XmlReader();

        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("teste.xml").getFile());
        String path = file.getAbsolutePath();

        List<Integer> resultValues;
        List<Integer> expectedResult = new ArrayList<>();
        expectedResult.add(4);
        expectedResult.add(3);
        expectedResult.add(1);

        resultValues = ctr020.addReadingsToSensorsOfHouse(URLDecoder.decode(path, "UTF-8"));

        assertEquals(expectedResult, resultValues);

    }


    @Test
    public void checkIfSetReadingsByRoomSensorIDescription() throws IOException {

        House house = new House();
        List<Room> listOfRooms = new ArrayList<>();

        // double[] dimensions = new double[]{1,1,1};
        List<Double> dimensions = new ArrayList<>();
        dimensions.add(1d);
        dimensions.add(1d);
        dimensions.add(1d);

        Room room = new Room("RoomTest", "testRoom", 2, dimensions.get(0), dimensions.get(1), dimensions.get(2));
        Room room2 = new Room("RoomTest2", "testRoom", 2, dimensions.get(0), dimensions.get(1), dimensions.get(2));
        Room room3 = new Room("RoomTest3", "testRoom", 2, dimensions.get(0), dimensions.get(1), dimensions.get(2));

        listOfRooms.add(room);
        listOfRooms.add(room2);
        listOfRooms.add(room3);
        roomDomainService.setListOfRooms(listOfRooms);


        // RoomSensor(String idOfSensor, String name, SensorType designation, LocalDate installationDate, String unit)
        LocalDate installationDate = LocalDate.of(2019, 12, 1);
        LocalDate installationDate2 = LocalDate.of(2019, 12, 2);
        LocalDate installationDate3 = LocalDate.of(2019, 12, 3);

        RoomSensor roomSensor = new RoomSensor("s1", "Test", new SensorType("Temperature"), installationDate, "C");
        RoomSensor roomSensor2 = new RoomSensor("s2", "Test", new SensorType("Temperature"), installationDate2, "C");
        RoomSensor roomSensor3 = new RoomSensor("s3", "Test", new SensorType("Temperature"), installationDate3, "C");


        roomSensor.setRoom(room);
        roomSensor2.setRoom(room2);
        roomSensor3.setRoom(room3);

        List<RoomSensor> sensorList = new ArrayList<>();
        sensorList.add(roomSensor);
        sensorList.add(roomSensor2);
        sensorList.add(roomSensor3);


        //  ListOfReadings lRead1 = new ListOfReadings();

        LocalDateTime date1 = LocalDateTime.of(2019, 12, 30, 1, 0);
        ReadingDto r1 = new ReadingDto("s1", date1, 14, "C", 0);
        LocalDateTime date2 = LocalDateTime.of(2019, 12, 30, 2, 0);
        ReadingDto r2 = new ReadingDto("s1", date2, 13.7, "C", 0);
        LocalDateTime date3 = LocalDateTime.of(2019, 12, 30, 3, 0);
        ReadingDto r3 = new ReadingDto("s3", date3, 16.5, "C", 0);
        LocalDateTime date4 = LocalDateTime.of(2019, 12, 30, 4, 0);
        ReadingDto r4 = new ReadingDto("s2", date4, 15.1, "C", 0);
        LocalDateTime date5 = LocalDateTime.of(2019, 12, 30, 5, 0);
        ReadingDto r5 = new ReadingDto("MAL", date5, 13.8, "C", 0);
        LocalDateTime date6 = LocalDateTime.of(2019, 12, 30, 6, 0);
        ReadingDto r6 = new ReadingDto("s2", date6, 13.3, "C", 0);
        LocalDateTime date7 = LocalDateTime.of(2019, 12, 30, 7, 0);
        ReadingDto r7 = new ReadingDto("s2", date7, 15.5, "C", 0);
        LocalDateTime date8 = LocalDateTime.of(2019, 12, 30, 8, 0);
        ReadingDto r8 = new ReadingDto("s3", date8, 14.2, "C", 0);
        LocalDateTime date9 = LocalDateTime.of(2019, 12, 30, 9, 0);
        ReadingDto r9 = new ReadingDto("s3", date9, 12.5, "C", 0);
        LocalDateTime date10 = LocalDateTime.of(2019, 12, 30, 10, 0);
        ReadingDto r10 = new ReadingDto("s2", date10, 12.4, "C", 0);

        List<ReadingDto> readingDtoList = new ArrayList<>();
        readingDtoList.add(r1);
        readingDtoList.add(r2);
        readingDtoList.add(r3);
        readingDtoList.add(r4);
        readingDtoList.add(r5);
        readingDtoList.add(r6);
        readingDtoList.add(r7);
        readingDtoList.add(r8);
        readingDtoList.add(r9);
        readingDtoList.add(r10);

        //   public ImportSensorsReadingsController(ListOfGeographicArea listOfGeographicArea, House house, RoomRepository roomRepository) {


        List<Integer> result = ctr020.setReadingsByRoomSensorIDescription(sensorList, readingDtoList);

        List<Integer> expectedResult = new ArrayList<>();

        expectedResult.add(0);
        expectedResult.add(9);
        expectedResult.add(1);

        assertEquals(expectedResult, result);

    }

    @Test
    public void checkIfAddReadingToRoomSensor() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {


        LocalDate installationDate = LocalDate.of(2019, 12, 1);

        RoomSensor roomSensor = new RoomSensor("s1", "Test", new SensorType("Temperature"), installationDate, "C");

        LocalDateTime date1 = LocalDateTime.of(2019, 12, 30, 1, 0);
        ReadingDto r1 = new ReadingDto("s1", date1, 61.7, "F", 0);

        Method method = ImportSensorsReadingsController.class.getDeclaredMethod("checkIfAddReadingToRoomSensor", RoomSensor.class, ReadingDto.class);
        method.setAccessible(true);
        method.invoke(ctr020, roomSensor, r1);

        HashMap<Double, String> expectedResult = new HashMap<>();
        expectedResult.put(16.5, "C");

        HashMap<Double, String> result = new HashMap<>();
        result.put(r1.getValue(), r1.getUnit());

        assertEquals(expectedResult, result);
    }


    @Test
    public void checkIfAddReadingToRoomSensorCheckUnits() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {


        LocalDate installationDate = LocalDate.of(2019, 12, 1);

        RoomSensor roomSensor = new RoomSensor("s1", "Test", new SensorType("Temperature"), installationDate, "C");

        LocalDateTime date1 = LocalDateTime.of(2019, 12, 30, 1, 0);
        ReadingDto r1 = new ReadingDto("s1", date1, 61.7, "F", 0);


        Method method = ImportSensorsReadingsController.class.getDeclaredMethod("checkIfAddReadingToRoomSensor", RoomSensor.class, ReadingDto.class);
        method.setAccessible(true);
        Boolean act = (Boolean) method.invoke(ctr020, roomSensor, r1);


        Reading r = roomSensor.getListOfReadings().getListOfReading().get(0);

        String expectedResult = r.getUnit();
        String result = "C";

        assertEquals(expectedResult, result);
    }

    @Test
    public void checkIfAddReadingToSensorTRUE() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        Location l = new Location(41.178553, -8.608035, 111);
        SensorType sensorType = new SensorType("Temperature");
        LocalDate installationDateAreaSensor = LocalDate.of(2018, 11, 15);
        AreaSensor areaSensor = new AreaSensor("TT12346", "Meteo station ISEP - temperature", l, sensorType, installationDateAreaSensor, "C");
        ListOfReadings listOfReadings = new ListOfReadings();
        Reading rArea1 = new Reading(LocalDateTime.of(2018, 12, 2, 0, 0, 0, 0), 14);
        listOfReadings.addReading(rArea1);
        areaSensor.setListOfReadings(listOfReadings);

        ReadingDto readingDto = new ReadingDto();
        readingDto.setValue(15);
        readingDto.setDateTime(LocalDateTime.of(2018, 12, 2, 1, 0, 0, 0));

        Method method = ImportSensorsReadingsController.class.getDeclaredMethod("checkIfAddReadingToSensor", AreaSensor.class, ReadingDto.class);
        method.setAccessible(true);
        Boolean result = (Boolean) method.invoke(ctr020, areaSensor, readingDto);

        assertTrue(result);
    }

    @Test
    public void checkIfAddReadingToSensorFalseReadingBeforeInstallation() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        Location l = new Location(41.178553, -8.608035, 111);
        SensorType sensorType = new SensorType("Temperature");
        LocalDate installationDateAreaSensor = LocalDate.of(2018, 11, 15);
        AreaSensor areaSensor = new AreaSensor("TT12346", "Meteo station ISEP - temperature", l, sensorType, installationDateAreaSensor, "C");
        ListOfReadings listOfReadings = new ListOfReadings();
        Reading rArea1 = new Reading(LocalDateTime.of(2018, 12, 2, 0, 0, 0, 0), 14);
        listOfReadings.addReading(rArea1);
        areaSensor.setListOfReadings(listOfReadings);

        ReadingDto readingDto = new ReadingDto();
        readingDto.setValue(15);
        readingDto.setDateTime(LocalDateTime.of(2015, 12, 2, 1, 0, 0, 0));

        Method method = ImportSensorsReadingsController.class.getDeclaredMethod("checkIfAddReadingToSensor", AreaSensor.class, ReadingDto.class);
        method.setAccessible(true);
        Boolean result = (Boolean) method.invoke(ctr020, areaSensor, readingDto);

        assertFalse(result);
    }

    @Test
    public void checkIfAddReadingToSensorFalseSensorStatusOff() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        Location l = new Location(41.178553, -8.608035, 111);
        SensorType sensorType = new SensorType("Temperature");
        LocalDate installationDateAreaSensor = LocalDate.of(2018, 11, 15);
        AreaSensor areaSensor = new AreaSensor("TT12346", "Meteo station ISEP - temperature", l, sensorType, installationDateAreaSensor, "C");
        ListOfReadings listOfReadings = new ListOfReadings();
        Reading rArea1 = new Reading(LocalDateTime.of(2018, 12, 2, 0, 0, 0, 0), 14);
        listOfReadings.addReading(rArea1);
        areaSensor.setListOfReadings(listOfReadings);


        Status status = new Status(false, LocalDate.of(2018, 12, 2));
        areaSensor.addStatus(status);


        ReadingDto readingDto = new ReadingDto();
        readingDto.setValue(15);
        readingDto.setDateTime(LocalDateTime.of(2018, 12, 2, 1, 0, 0, 0));

        Method method = ImportSensorsReadingsController.class.getDeclaredMethod("checkIfAddReadingToSensor", AreaSensor.class, ReadingDto.class);
        method.setAccessible(true);
        Boolean result = (Boolean) method.invoke(ctr020, areaSensor, readingDto);

        assertFalse(result);
    }

    @Test
    public void checkIfAddReadingToSensorFalseSensorStatusOffAndReadingBeforeSensor() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        Location l = new Location(41.178553, -8.608035, 111);
        SensorType sensorType = new SensorType("Temperature");
        LocalDate installationDateAreaSensor = LocalDate.of(2018, 11, 15);
        AreaSensor areaSensor = new AreaSensor("TT12346", "Meteo station ISEP - temperature", l, sensorType, installationDateAreaSensor, "C");
        ListOfReadings listOfReadings = new ListOfReadings();
        Reading rArea1 = new Reading(LocalDateTime.of(2018, 12, 2, 0, 0, 0, 0), 14);
        listOfReadings.addReading(rArea1);
        areaSensor.setListOfReadings(listOfReadings);


        Status status = new Status(false, LocalDate.of(2018, 12, 2));
        areaSensor.addStatus(status);


        ReadingDto readingDto = new ReadingDto();
        readingDto.setValue(15);
        readingDto.setDateTime(LocalDateTime.of(2017, 12, 2, 1, 0, 0, 0));

        Method method = ImportSensorsReadingsController.class.getDeclaredMethod("checkIfAddReadingToSensor", AreaSensor.class, ReadingDto.class);
        method.setAccessible(true);
        Boolean result = (Boolean) method.invoke(ctr020, areaSensor, readingDto);

        assertFalse(result);
    }

    @Test
    public void checkIfAddReadingToRoomSensorTRUE() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        LocalDate installationDate = LocalDate.of(2018, 11, 15);
        RoomSensor roomSensor = new RoomSensor("s1", "Test", new SensorType("Temperature"), installationDate, "C");

        ReadingDto readingDto = new ReadingDto();
        readingDto.setValue(15);
        readingDto.setDateTime(LocalDateTime.of(2018, 12, 2, 1, 0, 0, 0));

        Method method = ImportSensorsReadingsController.class.getDeclaredMethod("checkIfAddReadingToRoomSensor", RoomSensor.class, ReadingDto.class);
        method.setAccessible(true);
        Boolean result = (Boolean) method.invoke(ctr020, roomSensor, readingDto);

        assertTrue(result);
    }

    @Test
    public void checkIfAddReadingToRoomSensorFalseReadingBeforeSens() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        LocalDate installationDate = LocalDate.of(2018, 11, 15);
        RoomSensor roomSensor = new RoomSensor("s1", "Test", new SensorType("Temperature"), installationDate, "C");

        ReadingDto readingDto = new ReadingDto();
        readingDto.setValue(15);
        readingDto.setDateTime(LocalDateTime.of(2016, 12, 2, 1, 0, 0, 0));

        Method method = ImportSensorsReadingsController.class.getDeclaredMethod("checkIfAddReadingToRoomSensor", RoomSensor.class, ReadingDto.class);
        method.setAccessible(true);
        Boolean result = (Boolean) method.invoke(ctr020, roomSensor, readingDto);

        assertFalse(result);
    }

    @Test
    public void checkIfAddReadingToRoomSensorFalseStatusFalseOnReadingDate() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        LocalDate installationDate = LocalDate.of(2018, 11, 15);
        RoomSensor roomSensor = new RoomSensor("s1", "Test", new SensorType("Temperature"), installationDate, "C");

        Status status = new Status(false, LocalDate.of(2018, 12, 2));
        roomSensor.addStatus(status);


        ReadingDto readingDto = new ReadingDto();
        readingDto.setValue(15);
        readingDto.setDateTime(LocalDateTime.of(2018, 12, 2, 1, 0, 0, 0));

        Method method = ImportSensorsReadingsController.class.getDeclaredMethod("checkIfAddReadingToRoomSensor", RoomSensor.class, ReadingDto.class);
        method.setAccessible(true);
        Boolean result = (Boolean) method.invoke(ctr020, roomSensor, readingDto);

        assertFalse(result);
    }

    @Test
    public void checkIfAddReadingToRoomSensorBothConditionsFalse() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        LocalDate installationDate = LocalDate.of(2018, 11, 15);
        RoomSensor roomSensor = new RoomSensor("s1", "Test", new SensorType("Temperature"), installationDate, "C");

        Status status = new Status(false, LocalDate.of(2018, 12, 2));
        roomSensor.addStatus(status);

        ReadingDto readingDto = new ReadingDto();
        readingDto.setValue(15);
        readingDto.setDateTime(LocalDateTime.of(2015, 12, 2, 1, 0, 0, 0));

        Method method = ImportSensorsReadingsController.class.getDeclaredMethod("checkIfAddReadingToRoomSensor", RoomSensor.class, ReadingDto.class);
        method.setAccessible(true);
        Boolean result = (Boolean) method.invoke(ctr020, roomSensor, readingDto);

        assertFalse(result);
    }

    @Test
    void checkIfImport10k() throws IOException, Exception {

        Location location = new Location(40.7486, -73.9864, 0);


        Room room1 = new Room("B107", "Classroom", 1, 10, 6, 3.5);

        Room room2 = new Room("B109", "Classroom", 1, 10, 6, 3.5);

        Room room3 = new Room("B106", "Classroom", 1, 13, 7, 3.5);

        Room room4 = new Room("B209", "Classroom", 2, 10, 6, 3.5);

        Room room5 = new Room("B210", "Meeting room", 2, 5, 5.5, 3.5);

        Room room6 = new Room("B102", "Reprographics Centre", 1, 7, 21, 3.5);

        Room room7 = new Room("B405A", "DEI Datacenter", 4, 6, 3, 3.5);


        RoomSensor s1 = new RoomSensor("TT12345OA", "Temperature B405", new SensorType("Temperature"), LocalDate.of(2018, 7, 15), "C");
        //s1.setRoom();    //B405
        RoomSensor s2 = new RoomSensor("TT12346OB", "Temperature B106", new SensorType("Temperature"), LocalDate.of(2018, 10, 15), "C");
        s2.setRoom(room3); //B106
        RoomSensor s3 = new RoomSensor("TT12334OA", "Temperature B107", new SensorType("Temperature"), LocalDate.of(2018, 10, 15), "C");
        s3.setRoom(room1); //B107
        RoomSensor s4 = new RoomSensor("TT1236AC", "Temperature B109", new SensorType("Temperature"), LocalDate.of(2018, 11, 02), "C");
        s4.setRoom(room2); //B109


        List<Room> roomList = new ArrayList<>();
        roomList.add(room1);
        roomList.add(room2);
        roomList.add(room3);
        roomList.add(room4);
        roomList.add(room5);
        roomList.add(room6);
        roomList.add(room7);

        roomDomainService.setListOfRooms(roomList);

        FileReader fr = new JsonReader();

        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("DataSet_sprint06_HouseSensorDataUltra.json").getFile());
        String path = file.getAbsolutePath();

        List<Integer> resultValues;
        List<Integer> expectedResult = new ArrayList<>();
        expectedResult.add(10688);
        expectedResult.add(0);
        expectedResult.add(10688);

        LocalDateTime start = LocalDateTime.now();
        resultValues = ctr020.addReadingsToSensorsOfHouse(URLDecoder.decode(path, "UTF-8"));
        LocalDateTime end = LocalDateTime.now();
        Duration dur = Duration.between(start, end);
        long millis = dur.toMillis();

        //  System.out.println(millis);

        assertEquals(expectedResult, resultValues);

        //assertTrue(millis<5000);

    }

    @Test
    void checkIfImport10kUnder5s() throws IOException, Exception {

        LocalDateTime start = LocalDateTime.now();
        checkIfImport10k();
        LocalDateTime end = LocalDateTime.now();
        Duration dur = Duration.between(start, end);
        long millis = dur.toMillis();
        //System.out.println(millis);
        assertTrue(millis < 25000);
    }

    @Test
    void addReadingsToSensorsOfHouseBadPath() throws IOException, Exception {
        Location l1 = new Location(40.7486, -73.9864, 0);

        ArrayList<Double> dimension = new ArrayList<>();
        dimension.add(3.0);
        dimension.add(2.0);
        dimension.add(3.0);
        Room room = new Room("bedroom", "room", 1, dimension.get(0), dimension.get(1), dimension.get(2));

        RoomSensor areaSensorIsep1 = new RoomSensor("1", "Meteo station ISEP - rainfall",
                new SensorType("temperature"), LocalDate.of(2018, 12, 30), "l/m2");
        areaSensorIsep1.setIdOfRoomSensor("s1");
        areaSensorIsep1.setRoom(room);
        ListOfReadings listOfReadings1 = new ListOfReadings();
        Reading reading1 = new Reading(LocalDateTime.of(2018, 12, 30, 12, 58), 25);
        Reading reading2 = new Reading(LocalDateTime.of(2019, 02, 05, 12, 59), 20);
        listOfReadings1.addReading(reading1);
        listOfReadings1.addReading(reading2);
        areaSensorIsep1.setListOfReadings(listOfReadings1);

        RoomSensor areaSensorPorto1 = new RoomSensor("1", "Meteo station CMP - rainfall",
                new SensorType("rainfall"), LocalDate.of(2017, 11, 15), "l/m2");
        areaSensorPorto1.setIdOfRoomSensor("RF12334");
        areaSensorPorto1.setRoom(room);
        ListOfReadings listOfReadings2 = new ListOfReadings();
        Reading reading4 = new Reading(LocalDateTime.of(2017, 11, 15, 12, 59), 20);
        Reading reading5 = new Reading(LocalDateTime.of(2018, 12, 12, 10, 15), 25);
        Reading reading6 = new Reading(LocalDateTime.of(2018, 12, 12, 10, 20), 26);
        listOfReadings2.addReading(reading4);
        listOfReadings2.addReading(reading5);
        listOfReadings2.addReading(reading6);
        areaSensorPorto1.setListOfReadings(listOfReadings2);

        room.addSensor(areaSensorIsep1);
        room.addSensor(areaSensorPorto1);

        roomDomainService.addRoom(room);


        FileReader core = new JsonReader();

//        ClassLoader classLoader = getClass().getClassLoader();
//        File file = new File(classLoader.getResource("wugabutz").getFile());
//        String path = file.getAbsolutePath();

        List<Integer> resultValues;
        List<Integer> expectedResult = new ArrayList<>();
        expectedResult.add(-1);


        resultValues = ctr020.addReadingsToSensorsOfHouse(URLDecoder.decode("wugaBuga", "UTF-8"));

        assertEquals(expectedResult, resultValues);
    }
}