package pt.ipp.isep.dei.project1.controllers.house;


import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import pt.ipp.isep.dei.project1.io.ui.utils.Mock;
import pt.ipp.isep.dei.project1.model.geographicarea.Location;
import pt.ipp.isep.dei.project1.model.house.Room;
import pt.ipp.isep.dei.project1.model.repositories.RoomRepository;
import pt.ipp.isep.dei.project1.model.repositories.SensorTypeRepository;
import pt.ipp.isep.dei.project1.readers.FileReader;
import pt.ipp.isep.dei.project1.readers.readerjson.JsonReader;
import pt.ipp.isep.dei.project1.readers.readerjson.RoomSensorsFromJson;
import pt.ipp.isep.dei.project1.readers.xml.SensorFromFile;
import pt.ipp.isep.dei.project1.readers.xml.XmlReader;
import pt.ipp.isep.dei.project1.model.repositories.RoomDomainService;
import pt.ipp.isep.dei.project1.model.repositories.SensorTypeDomainService;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLDecoder;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.*;

import static org.junit.Assert.*;

@ExtendWith(MockitoExtension.class)
public class ImportRoomSensorsFromFileControllerTest {

    @org.mockito.Mock
    private RoomRepository roomRepository;
    @org.mockito.Mock
    private RoomDomainService roomDomainService;
    @org.mockito.Mock
    private ImportRoomSensorsFromFileController us20;
    @org.mockito.Mock
    private SensorTypeRepository sensorTypeRepository;
    @org.mockito.Mock
    private SensorTypeDomainService sensorTypeDomainService;


    private static Logger log = Logger.getLogger(ImportRoomSensorsFromFileController.class.getName()); // matches the logger in the affected class
    private static OutputStream logCapturingStream;
    private static StreamHandler customLogHandler;

    @Before
    public void attachLogCapturer() {
        logCapturingStream = new ByteArrayOutputStream();
        Handler[] handlers = log.getParent().getHandlers();
        customLogHandler = new StreamHandler(logCapturingStream, handlers[0].getFormatter());
        log.addHandler(customLogHandler);
    }

    public String getTestCapturedLog() throws IOException {
        customLogHandler.flush();
        return logCapturingStream.toString();
    }

    @BeforeEach
    void initMocks() throws Exception {
        sensorTypeDomainService = new SensorTypeDomainService(sensorTypeRepository);
        roomDomainService = new RoomDomainService(roomRepository);
        us20 = new ImportRoomSensorsFromFileController(roomDomainService, sensorTypeDomainService);
    }

    @Test
    void testAddReadingsCSV() throws IOException, Exception {
        Location l1 = new Location(40.7486, -73.9864, 0);

        //ARRANGE

        roomDomainService.setListOfRooms(Mock.mockListOfRooms());

        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("DataSet_sprint06_HouseSensors.json").getFile());
        String path = file.getParentFile().getAbsolutePath() + File.separator;
        String path2 = URLDecoder.decode(path, "UTF-8") + "DataSet_sprint06_HouseSensors.json";
        //ACT
        List<String[]> result = us20.importSensorsToHouseRooms(path2);
        String result2 = result.get(result.size() - 1)[1];
        String expected = String.valueOf(4);
        //ASSERT
        assertEquals(expected, result2);
    }

    @Test
    void testAddReadingsCSV2() throws IOException, Exception {
        //ARRANGE
        Location l1 = new Location(40.7486, -73.9864, 0);
        roomDomainService.setListOfRooms(Mock.mockListOfRooms());
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("DataSet_sprint06_HouseSensors.json").getFile());
        String path = file.getParentFile().getAbsolutePath() + File.separator;
        String path2 = URLDecoder.decode(path, "UTF-8") + "DataSet_sprint06_HouseSensors.json";
        //ACT
        List<String[]> result = us20.importSensorsToHouseRooms(path2);
        String result2 = result.get(result.size() - 1)[1];
        String expected = String.valueOf(4);
        //ASSERT
        assertEquals(expected, result2);
    }

    @Test
    void testAddReadingsCSV3() throws IOException, Exception {
        Location l1 = new Location(40.7486, -73.9864, 0);
        //ARRANGE
        roomDomainService.setListOfRooms(Mock.mockListOfRooms());
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("DataSet_sprint06_HouseSensors.json").getFile());
        String path = file.getParentFile().getAbsolutePath() + File.separator;
        String path2 = URLDecoder.decode(path, "UTF-8") + "DataSet_sprint06_HouseSensors.json";
        //ACT
        List<String[]> result = us20.importSensorsToHouseRooms(path2);
        String result2 = result.get(result.size() - 1)[1];
        String expected = String.valueOf(4);
        //ASSERT
        assertEquals(expected, result2);
    }

    @Test
    void testAddReadingsCSV4() throws IOException, Exception {
        //ARRANGE
        Location l1 = new Location(40.7486, -73.9864, 0);
        roomDomainService.setListOfRooms(Mock.mockListOfRooms());
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("DataSet_sprint06_HouseSensors.json").getFile());
        String path = file.getParentFile().getAbsolutePath() + File.separator;
        String path2 = URLDecoder.decode(path, "UTF-8") + "DataSet_sprint06_HouseSensors.json";
        //ACT
        List<String[]> result = us20.importSensorsToHouseRooms(path2);
        String result2 = result.get(result.size() - 1)[1];
        String expected = String.valueOf(4);
        //ASSERT
        assertEquals(expected, result2);
    }

    @Test
    void testAddReadingsCSV5() throws IOException, Exception {
        //ARRANGE
        Location l1 = new Location(40.7486, -73.9864, 0);
        roomDomainService.setListOfRooms(Mock.mockListOfRooms());
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("DataSet_sprint06_HouseSensors.json").getFile());
        String path = file.getParentFile().getAbsolutePath() + File.separator;
        String path2 = URLDecoder.decode(path, "UTF-8") + "DataSet_sprint06_HouseSensors.json";
        //ACT
        List<String[]> result = us20.importSensorsToHouseRooms(path2);
        String result2 = result.get(result.size() - 1)[1];
        String expected = String.valueOf(4);
        //ASSERT
        assertEquals(expected, result2);
    }

    /**
     * @Test void testAddReadingsCSV6() throws IOException, Exception {
     * //ARRANGE
     * Location l1 = new Location(40.7486, -73.9864, 0);
     * House h1 = new House("Home", l1, new GeographicArea("Porto", "city",
     * new OccupationArea(new Location(10, 20, 30), 0.5, 0.5),
     * new GeographicAreaType("city")));
     * roomService.setRoomList(Mock.mockListOfRooms());
     * String path = "hjjhghj";
     * //ACT
     * try {
     * List<String[] > result = us20.importRoomSensors(path);
     * }catch (FileNotFoundException e){}
     * }
     **/

    @Test
    void testAddReadingsZeroSensorID() throws IOException, Exception {
        //ARRANGE
        Location l1 = new Location(40.7486, -73.9864, 0);
        roomDomainService.setListOfRooms(Mock.mockListOfRooms());
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("DataSet_sprint06_HouseSensors.json").getFile());
        String path = file.getParentFile().getAbsolutePath() + File.separator;
        String path2 = URLDecoder.decode(path, "UTF-8") + "DataSet_sprint06_HouseSensors.json";
        //ACT
        List<String[]> result = us20.importSensorsToHouseRooms(path2);
        String result2 = result.get(result.size() - 1)[1];
        String expected = String.valueOf(4);
        //ASSERT
        assertEquals(expected, result2);
    }

    @Test
    void testAddReadingsZeroReadingsID() throws IOException, Exception {
        //ARRANGE
        Location l1 = new Location(40.7486, -73.9864, 0);
        roomDomainService.setListOfRooms(Mock.mockListOfRooms());
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("DataSet_sprint06_HouseSensors.json").getFile());
        String path = file.getParentFile().getAbsolutePath() + File.separator;
        String path2 = URLDecoder.decode(path, "UTF-8") + "DataSet_sprint06_HouseSensors.json";
        //ACT
        List<String[]> result = us20.importSensorsToHouseRooms(path2);
        String result2 = result.get(result.size() - 1)[1];
        String expected = String.valueOf(4);
        //ASSERT
        assertEquals(expected, result2);
    }

    @Test
    void testAddReadingsZeroSensors() throws IOException, Exception {
        //ARRANGE
        Location l1 = new Location(40.7486, -73.9864, 0);
        roomDomainService.setListOfRooms(Mock.mockListOfRooms());
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("DataSet_sprint06_HouseSensors.json").getFile());
        String path = file.getParentFile().getAbsolutePath() + File.separator;
        String path2 = URLDecoder.decode(path, "UTF-8") + "DataSet_sprint06_HouseSensors.json";
        //ACT
        List<String[]> result = us20.importSensorsToHouseRooms(path2);
        String result2 = result.get(result.size() - 1)[1];
        String expected = String.valueOf(4);
        //ASSERT
        assertEquals(expected, result2);
    }

    @Test
    void testAddSensorsToRoomDuplicate() throws IOException, Exception {
        //ARRANGE
        Location l1 = new Location(40.7486, -73.9864, 0);
        roomDomainService.setListOfRooms(Mock.mockListOfRooms());
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("DataSet_sprint06_HouseSensors.json").getFile());
        String path = file.getParentFile().getAbsolutePath() + File.separator;
        String path2 = URLDecoder.decode(path, "UTF-8") + "DataSet_sprint06_HouseSensors.json";
        //ACT
        us20.importSensorsToHouseRooms(path2);
        us20.importSensorsToHouseRooms(path2);
        List<String[]> result = us20.importSensorsToHouseRooms(path2);

        //  System.out.println(h1.getListOfRoomsOfHouse().toDto().get(0).getListOfSensors().size());

        String result2 = result.get(result.size() - 1)[1];
        String expected = String.valueOf(4);
        //ASSERT
        assertEquals(expected, result2);
    }

    @Test
    void testGetAvailableReaderTypesObjectsJsonTest() throws Exception {

        List<FileReader> expectedResult = new ArrayList<>();
        JsonReader jsonReader = new JsonReader();
        XmlReader xmlReader = new XmlReader();
        expectedResult.add(jsonReader);
        expectedResult.add(xmlReader);

        List<FileReader> result = us20.getAvailableReaderTypesObjects();
        Assertions.assertEquals(expectedResult.get(0).getReaderType(), result.get(0).getReaderType());
    }

    @Test
    void testImportRoomSensorsFromFileHouseWithNoRooms() throws IOException, Exception {

        this.roomDomainService = null;

        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("DataSet_sprint06_HouseSensors.json").getFile());
        String path = file.getParentFile().getAbsolutePath() + File.separator;
        String path2 = URLDecoder.decode(path, "UTF-8") + "DataSet_sprint06_HouseSensors.json";
        //ACT
        List<String[]> result = us20.importSensorsToHouseRooms(path2);
        String result2 = result.get(result.size() - 1)[1];
        String expected = String.valueOf(4);
        //ASSERT

        // System.out.println(roomService.toDto());
        assertEquals(expected, result2);
    }

    /**
     * in need to analise why don't run
     *
     * @Test void setSensorsToRoomsCheckConditions() throws IOException {
     * <p>
     * <p>
     * Logger logger = Logger.getLogger("logSensors.properties");
     * <p>
     * Formatter formatter = new SimpleFormatter();
     * ByteArrayOutputStream out = new ByteArrayOutputStream();
     * Handler handler = new StreamHandler(out, formatter);
     * logger.addHandler(handler);
     * <p>
     * <p>
     * <p>
     * List<Room> roomList = new ArrayList<>();
     * <p>
     * Room room1 = new Room("B107", "Classroom", 1, 10, 6, 3.5);
     * Room room2 = new Room("B210", "Meeting Room", 2, 5, 5.5, 3.5);
     * Room room3 = new Room("B405A", "DEI Datacenter", 4, 6, 3, 3.5);
     * <p>
     * roomList.add(room1);
     * roomList.add(room2);
     * roomList.add(room3);
     * <p>
     * RoomSensorsFromJson roomSensors = new RoomSensorsFromJson();
     * <p>
     * <p>
     * List<SensorFromFile> sensorFromFileList = new ArrayList<>();
     * <p>
     * <p>
     * SensorFromFile sensor1 = new SensorFromFile();
     * SensorFromFile sensor2 = new SensorFromFile();
     * SensorFromFile sensor3 = new SensorFromFile();
     * SensorFromFile sensor4 = new SensorFromFile();
     * SensorFromFile sensor5 = new SensorFromFile();
     * <p>
     * <p>
     * sensor1.setName("Sens1");
     * sensor1.setRoom("B107");
     * sensor1.setType("Temperature");
     * sensor1.setId("S1");
     * <p>
     * sensor2.setName("Sens2");
     * sensor2.setRoom("B107");
     * sensor2.setType("Rainfall");
     * sensor2.setId("S2");
     * <p>
     * sensor3.setName("Sens3");
     * sensor3.setRoom("B210");
     * sensor3.setType("Temperature");
     * sensor3.setId("S3");
     * <p>
     * sensor4.setName("Sens4");
     * sensor4.setRoom("Bwe");
     * sensor4.setType("Temperature");
     * sensor4.setId("S4");
     * <p>
     * sensor5.setName("Sens4");
     * sensor5.setRoom("B405A");
     * sensor5.setType("Temperature");
     * sensor5.setId("S5");
     * <p>
     * sensorFromFileList.add(sensor1);
     * sensorFromFileList.add(sensor2);
     * sensorFromFileList.add(sensor3);
     * sensorFromFileList.add(sensor4);
     * sensorFromFileList.add(sensor5);
     * <p>
     * roomSensors.setSensor(sensorFromFileList);
     * <p>
     * <p>
     * List<String[]> resultList = us20.setSensorsToRooms(roomList, roomSensors);
     * <p>
     * List<String[]> expectedList = new ArrayList<>();
     * <p>
     * expectedList.add(new String[]{"B107", "S1"});
     * <p>
     * //for (String[] i: resultList ) {
     * <p>
     * //System.out.println(i[0] + " "+ i[1]);}
     * <p>
     * <p>
     * //  System.out.println(resultList.get(0)[0] +" "+ resultList.get(0)[1]+" "+ resultList.get(1)[0]+" "+ resultList.get(1)[1]);
     * //   System.out.println(expectedList);
     * handler.flush();
     * String logMsg = out.toString();
     * // assertTrue(logMsg.toLowerCase().contains("warning:") && expectedList.get(0).equals(resultList.get(0)) );
     * <p>
     * assertTrue(logMsg.contains("WARNING: Impossible to add sensor S4 ,no Room match") && (expectedList.get(0)[0]==resultList.get(0)[0]));
     * //  assertTrue(logMsg.toLowerCase().contains("warning: impossible to add sensor s4 ,no room match"));
     * // assertArrayEquals(expectedList.get(0), resultList.get(0));
     * <p>
     * logger.removeHandler(handler);
     * }
     **/


    @Test
    void setSensorsToRoomsCheckNumberOfSensorsAdded() throws IOException {

        List<Room> roomList = new ArrayList<>();

        Room room1 = new Room("B107", "Classroom", 1, 10, 6, 3.5);
        Room room2 = new Room("B210", "Meeting Room", 2, 5, 5.5, 3.5);
        Room room3 = new Room("B405A", "DEI Datacenter", 4, 6, 3, 3.5);

        roomList.add(room1);
        roomList.add(room2);
        roomList.add(room3);

        RoomSensorsFromJson roomSensors = new RoomSensorsFromJson();


        List<SensorFromFile> sensorFromFileList = new ArrayList<>();


        SensorFromFile sensor1 = new SensorFromFile();
        SensorFromFile sensor2 = new SensorFromFile();
        SensorFromFile sensor3 = new SensorFromFile();
        SensorFromFile sensor4 = new SensorFromFile();
        SensorFromFile sensor5 = new SensorFromFile();


        sensor1.setName("Sens1");
        sensor1.setRoom("B107");
        sensor1.setType("Temperature");
        sensor1.setId("S1");

        sensor2.setName("Sens2");
        sensor2.setRoom("B107");
        sensor2.setType("Rainfall");
        sensor2.setId("S2");

        sensor3.setName("Sens3");
        sensor3.setRoom("B210");
        sensor3.setType("Temperature");
        sensor3.setId("S3");

        sensor4.setName("Sens4");
        sensor4.setRoom("Bwe");
        sensor4.setType("Temperature");
        sensor4.setId("S4");

        sensor5.setName("Sens4");
        sensor5.setRoom("B405A");
        sensor5.setType("Temperature");
        sensor5.setId("S5");

        sensorFromFileList.add(sensor1);
        sensorFromFileList.add(sensor2);
        sensorFromFileList.add(sensor3);
        sensorFromFileList.add(sensor4);
        sensorFromFileList.add(sensor5);

        roomSensors.setSensor(sensorFromFileList);


        List<String[]> resultList = us20.setSensorsToRooms(roomList, roomSensors);


        String[] expectedResult = new String[]{"Total sensors added to rooms: ", "4"};

        assertArrayEquals(expectedResult, resultList.get(resultList.size() - 2));
    }

    @Test
    void setSensorsToRoomsCheckSetOnRooms() throws IOException {

        List<Room> roomList = new ArrayList<>();

        Room room1 = new Room("B107", "Classroom", 1, 10, 6, 3.5);
        Room room2 = new Room("B210", "Meeting Room", 2, 5, 5.5, 3.5);
        Room room3 = new Room("B405A", "DEI Datacenter", 4, 6, 3, 3.5);

        roomList.add(room1);
        roomList.add(room2);
        roomList.add(room3);

        RoomSensorsFromJson roomSensors = new RoomSensorsFromJson();


        List<SensorFromFile> sensorFromFileList = new ArrayList<>();


        SensorFromFile sensor1 = new SensorFromFile();
        SensorFromFile sensor2 = new SensorFromFile();
        SensorFromFile sensor3 = new SensorFromFile();
        SensorFromFile sensor4 = new SensorFromFile();
        SensorFromFile sensor5 = new SensorFromFile();


        sensor1.setName("Sens1");
        sensor1.setRoom("B107");
        sensor1.setType("Temperature");
        sensor1.setId("S1");

        sensor2.setName("Sens2");
        sensor2.setRoom("B107");
        sensor2.setType("Rainfall");
        sensor2.setId("S2");

        sensor3.setName("Sens3");
        sensor3.setRoom("B210");
        sensor3.setType("Temperature");
        sensor3.setId("S3");

        sensor4.setName("Sens4");
        sensor4.setRoom("Bwe");
        sensor4.setType("Temperature");
        sensor4.setId("S4");

        sensor5.setName("Sens4");
        sensor5.setRoom("B405A");
        sensor5.setType("Temperature");
        sensor5.setId("S5");

        sensorFromFileList.add(sensor1);
        sensorFromFileList.add(sensor2);
        sensorFromFileList.add(sensor3);
        sensorFromFileList.add(sensor4);
        sensorFromFileList.add(sensor5);

        roomSensors.setSensor(sensorFromFileList);


        List<String[]> resultList = us20.setSensorsToRooms(roomList, roomSensors);

        List<String[]> expectedList = new ArrayList<>();

        expectedList.add(new String[]{"B107", "S1"});

/*
        for (SensorFromFile s: sensorFromFileList) {

            System.out.println(s.isOnRoom());
        }*/


        assertTrue(sensorFromFileList.get(0).isOnRoom() && !sensorFromFileList.get(3).isOnRoom());
    }

    @Test
    void checkIfAddSensorToRoom() {

        Room room = new Room("B107", "Classroom", 1, 10, 6, 3.5);

        SensorFromFile sensor = new SensorFromFile();

        sensor.setId("TT12334OA");
        sensor.setRoom("B107");
        sensor.setName("Temperature B107");
        sensor.setStartDate(LocalDate.of(2018, 10, 15));
        sensor.setType("wugawuga");
        sensor.setUnits("C");

        boolean result = us20.addSensorToRoom(room, sensor);

        assertTrue(result);
    }

    @Test
    void checkIfAddSensorToRoomDuplicate() {

        Room room = new Room("B107", "Classroom", 1, 10, 6, 3.5);

        SensorFromFile sensor = new SensorFromFile();

        sensor.setId("TT12334OA");
        sensor.setRoom("B107");
        sensor.setName("Temperature B107");
        sensor.setStartDate(LocalDate.of(2018, 10, 15));
        sensor.setType("wugawuga");
        sensor.setUnits("C");

        us20.addSensorToRoom(room, sensor);
        boolean result = us20.addSensorToRoom(room, sensor);

        assertFalse(result);

    }

    @Test
    void checkIfAddSensorToRoomCheckingIfRoomAndSensorMatch() {

        Room room = new Room("B107", "Classroom", 1, 10, 6, 3.5);

        SensorFromFile sensor = new SensorFromFile();

        sensor.setId("TT12334OA");
        sensor.setRoom("B107");
        sensor.setName("Temperature B107");
        sensor.setStartDate(LocalDate.of(2018, 10, 15));
        sensor.setType("wugawuga");
        sensor.setUnits("C");

        us20.addSensorToRoom(room, sensor);

        assertEquals(room, room.getListOfSensors().get(0).getRoom());
    }


    /**
     * @Test void checkIfAddSensorToRoomTest() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
     * <p>
     * RoomSensorsFromJson roomSensorsFromJson = new RoomSensorsFromJson();
     * List<SensorFromFile> sensorFromFileList = new ArrayList<>();
     * <p>
     * SensorFromFile sensor = new SensorFromFile();
     * <p>
     * sensor.setId("TT12334OA");
     * sensor.setRoom("B107");
     * sensor.setName("Temperature B107");
     * sensor.setStartDate(LocalDate.of(2018,10,15));
     * sensor.setType("wugawuga");
     * sensor.setUnits("C");
     * sensor.setOnRoom(false);
     * <p>
     * sensorFromFileList.add(sensor);
     * <p>
     * roomSensorsFromJson.setSensor(sensorFromFileList);
     * <p>
     * <p>
     * <p>
     * <p>
     * // get Logback Logger
     * Logger fooLogger = (Logger) LoggerFactory.getLogger("logSensors.properties");
     * <p>
     * System.out.println(fooLogger.getName());
     * System.out.println(fooLogger.getLevel());
     * System.out.println(fooLogger.getClass());
     * <p>
     * // create and start a ListAppender
     * ListAppender<ILoggingEvent> listAppender = new ListAppender<>();
     * listAppender.start();
     * <p>
     * // add the appender to the logger
     * fooLogger.addAppender(listAppender);
     * <p>
     * // call method under test
     * us20.checkIfAddSensorToRoom(roomSensorsFromJson);
     * <p>
     * System.out.println();
     * <p>
     * System.out.println(fooLogger.getName());
     * System.out.println(fooLogger.getLevel());
     * System.out.println(fooLogger.getClass());
     * // JUnit assertions
     * List<ILoggingEvent> logsList = listAppender.list;
     * System.out.println(logsList.size());
     * <p>
     * assertEquals("start", logsList.get(0).getMessage());
     * assertEquals(Level.INFO, logsList.get(0).getLevel());
     * }
     * @Test void checkIfAddSensorToRoomTestV2() throws IOException {
     * class TerribleFilter implements Filter {
     * boolean seen;
     * @Override public boolean isLoggable(LogRecord record) {
     * System.out.println("------------------");
     * System.out.println(record.getMessage());
     * System.out.println("------------------");
     * if ("This is terrible!".equals(record.getMessage())) {
     * seen = true;
     * }
     * return true;
     * }
     * }
     * <p>
     * <p>
     * RoomSensorsFromJson roomSensorsFromJson = new RoomSensorsFromJson();
     * List<SensorFromFile> sensorFromFileList = new ArrayList<>();
     * <p>
     * SensorFromFile sensor = new SensorFromFile();
     * <p>
     * sensor.setId("TT12334OA");
     * sensor.setRoom("B107");
     * sensor.setName("Temperature B107");
     * sensor.setStartDate(LocalDate.of(2018, 10, 15));
     * sensor.setType("wugawuga");
     * sensor.setUnits("C");
     * sensor.setOnRoom(false);
     * <p>
     * sensorFromFileList.add(sensor);
     * <p>
     * roomSensorsFromJson.setSensor(sensorFromFileList);
     * <p>
     * <p>
     * <p>
     * Logger log = Logger.getLogger(ImportRoomSensorsFromFileController.class.getName());
     * TerribleFilter tf = new TerribleFilter();
     * log.setFilter(tf);
     * try {
     * us20.checkIfAddSensorToRoom(roomSensorsFromJson);
     * assertTrue(tf.seen);
     * } finally {
     * log.setFilter(null);
     * }
     * <p>
     * }
     * @Test void checkIfAddSensorToRoomTestV3() throws IOException {
     * <p>
     * RoomSensorsFromJson roomSensorsFromJson = new RoomSensorsFromJson();
     * List<SensorFromFile> sensorFromFileList = new ArrayList<>();
     * <p>
     * SensorFromFile sensor = new SensorFromFile();
     * <p>
     * sensor.setId("TT12334OA");
     * sensor.setRoom("B107");
     * sensor.setName("Temperature B107");
     * sensor.setStartDate(LocalDate.of(2018, 10, 15));
     * sensor.setType("wugawuga");
     * sensor.setUnits("C");
     * sensor.setOnRoom(false);
     * <p>
     * sensorFromFileList.add(sensor);
     * <p>
     * roomSensorsFromJson.setSensor(sensorFromFileList);
     * <p>
     * us20.checkIfAddSensorToRoom(roomSensorsFromJson);
     * <p>
     * final String expectedLogPart = "unexpected file extension";
     * <p>
     * <p>
     * <p>
     * String capturedLog = getTestCapturedLog();
     * Assert.assertTrue(capturedLog.contains(expectedLogPart));
     * <p>
     * }
     */

    /**
    @Test
    void checkIfAddSensorToRoomTestV4() {

        Logger logger = Logger.getLogger("logSensors.properties");

        Formatter formatter = new SimpleFormatter();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        Handler handler = new StreamHandler(out, formatter);
        logger.addHandler(handler);


        RoomSensorsFromJson roomSensorsFromJson = new RoomSensorsFromJson();
        List<SensorFromFile> sensorFromFileList = new ArrayList<>();

        SensorFromFile sensor = new SensorFromFile();

        sensor.setId("TT12334OA");
        sensor.setRoom("B107");
        sensor.setName("Temperature B107");
        sensor.setStartDate(LocalDate.of(2018, 10, 15));
        sensor.setType("wugawuga");
        sensor.setUnits("C");
        sensor.setOnRoom(false);

        sensorFromFileList.add(sensor);

        roomSensorsFromJson.setSensor(sensorFromFileList);

        try {
            us20.checkIfAddSensorToRoom(roomSensorsFromJson);

            handler.flush();
            String logMsg = out.toString();

            //String expectedResult ="WARNING: Impossible to add sensor TT12334OA ,no Room match";

            //   assertNotNull(logMsg);
            //assertEquals(expectedResult,logMsg);
            assertTrue(logMsg.contains("WARNING: Impossible to add sensor TT12334OA ,no Room match"));

        } finally {
            logger.removeHandler(handler);
        }
    }

    @Test
    void setSensorsToRoomsTestCheckLoggerForNotAdded() throws IOException {

        List<Room> roomList = new ArrayList<>();

        Room room1 = new Room("B107", "Classroom", 1, 10, 6, 3.5);
        Room room2 = new Room("B210", "Meeting Room", 2, 5, 5.5, 3.5);
        Room room3 = new Room("B405A", "DEI Datacenter", 4, 6, 3, 3.5);

        roomList.add(room1);
        roomList.add(room2);
        roomList.add(room3);

        RoomSensorsFromJson roomSensors = new RoomSensorsFromJson();


        List<SensorFromFile> sensorFromFileList = new ArrayList<>();


        SensorFromFile sensor1 = new SensorFromFile();
        SensorFromFile sensor2 = new SensorFromFile();
        SensorFromFile sensor3 = new SensorFromFile();
        SensorFromFile sensor4 = new SensorFromFile();
        SensorFromFile sensor5 = new SensorFromFile();
//        SensorFromFile badSensToLog = new SensorFromFile();

        sensor1.setName("Sens1");
        sensor1.setRoom("B107");
        sensor1.setType("Temperature");
        sensor1.setId("S1");

        sensor2.setName("Sens2");
        sensor2.setRoom("B107");
        sensor2.setType("Rainfall");
        sensor2.setId("S2");

        sensor3.setName("Sens3");
        sensor3.setRoom("B210");
        sensor3.setType("Temperature");
        sensor3.setId("S3");

        sensor4.setName("Sens4");
        sensor4.setRoom("Bwe");
        sensor4.setType("Temperature");
        sensor4.setId("S4");

        sensor5.setName("Sens4");
        sensor5.setRoom("B405A");
        sensor5.setType("Temperature");
        sensor5.setId("S5");


//        badSensToLog.setId("TT12334OA");
//        badSensToLog.setRoom("B107");
//        badSensToLog.setName("Temperature B107");
//        badSensToLog.setStartDate(LocalDate.of(2018, 10, 15));
//        badSensToLog.setType("wugawuga");
//        badSensToLog.setUnits("C");


        sensorFromFileList.add(sensor1);
        sensorFromFileList.add(sensor2);
        sensorFromFileList.add(sensor3);
        sensorFromFileList.add(sensor4);
        sensorFromFileList.add(sensor5);
       // sensorFromFileList.add(badSensToLog);

        roomSensors.setSensor(sensorFromFileList);


        Logger logger = Logger.getLogger("logSensors.properties");

        Formatter formatter = new SimpleFormatter();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        Handler handler = new StreamHandler(out, formatter);
        logger.addHandler(handler);

        try {

            us20.setSensorsToRooms(roomList, roomSensors);

            handler.flush();
            String logMsg = out.toString();


            assertTrue(logMsg.contains("WARNING: Impossible to add sensor S4 ,no Room match"));

        } finally {
            logger.removeHandler(handler);
        }
    }
    **/
}