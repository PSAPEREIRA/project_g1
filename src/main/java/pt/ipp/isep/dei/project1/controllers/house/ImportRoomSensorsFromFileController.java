package pt.ipp.isep.dei.project1.controllers.house;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import pt.ipp.isep.dei.project1.model.house.Room;
import pt.ipp.isep.dei.project1.model.sensor.RoomSensor;
import pt.ipp.isep.dei.project1.model.sensor.SensorType;
import pt.ipp.isep.dei.project1.model.repositories.RoomDomainService;
import pt.ipp.isep.dei.project1.readers.FileReader;
import pt.ipp.isep.dei.project1.readers.readerjson.RoomSensorsFromJson;
import pt.ipp.isep.dei.project1.readers.xml.SensorFromFile;
import pt.ipp.isep.dei.project1.model.repositories.SensorTypeDomainService;

import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import static pt.ipp.isep.dei.project1.io.ui.utils.Configurations.importPackFromProperties;

@Controller
public class ImportRoomSensorsFromFileController {

    private final RoomDomainService roomDomainService;
    private final SensorTypeDomainService sensorTypeDomainService;
    //private static final String LOG = "logSensors.properties";

    @Autowired
    public ImportRoomSensorsFromFileController(RoomDomainService roomDomainService, SensorTypeDomainService sensorTypeDomainService) {
        this.roomDomainService = roomDomainService;
        this.sensorTypeDomainService = sensorTypeDomainService;
    }

    public List<FileReader> getAvailableReaderTypesObjects() throws Exception {

        List<FileReader> fileReaderList = new ArrayList<>();
        List<String> readersList = importPackFromProperties("roomSensorsExtension");
        for (int i = 0; i < readersList.size(); i++) {
            FileReader fileReaderObject = (FileReader) Class.forName(readersList.get(i)).newInstance();
            fileReaderList.add(fileReaderObject);
        }
        return fileReaderList;
    }


    public List<String[]> importSensorsToHouseRooms(String path) throws IOException {
        /**List<String[]> list = new ArrayList<>();**/
        List<Room> listOfRooms;
        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File(path);
        RoomSensorsFromJson roomSensorsFromJson = objectMapper.readValue(file, RoomSensorsFromJson.class);
      /**  try {*/
            listOfRooms = roomDomainService.getListOfRooms();
      /**  } catch (NullPointerException e) {
            String[] string = new String[2];                         Desde que se comecou a usar roomService já não se apanha esta exception
            string[0] = "The House doesn't have rooms yet. ";
            string[1] = "Please add rooms first!";
            list.add(string);
            return list;
        }*/
        return setSensorsToRooms(listOfRooms, roomSensorsFromJson);
    }


    public List<String[]> setSensorsToRooms(List<Room> listOfRooms, RoomSensorsFromJson roomSensorFromJson) throws IOException {
        int numberOfSensorsAdded = 0;
        List<String[]> list = new ArrayList<>();
        //FileHandler fh = getFh();
        String[] totalSensors = new String[2];
        String[] totalSensorsAdded = new String[2];
        List<SensorFromFile> sensorList = roomSensorFromJson.getSensor();
        for (SensorFromFile s : sensorList)
            for (Room room : listOfRooms)
                if (s.getRoom().equals(room.getName()) && addSensorToRoom(room, s)) {
                        String[] sensorAdded = new String[2];
                        sensorAdded[0] = room.getName();
                        sensorAdded[1] = s.getId();
                        numberOfSensorsAdded++;
                        roomDomainService.getRoomRepository().save(room);
                        s.setOnRoom(true);
                    }

        //checkIfAddSensorToRoom(roomSensorFromJson);

        totalSensorsAdded[0] = "Total sensors added to rooms: ";
        totalSensorsAdded[1] = String.valueOf(numberOfSensorsAdded);
        list.add(totalSensorsAdded);
        totalSensors[0] = "Total sensors read: ";
        totalSensors[1] = String.valueOf(roomSensorFromJson.getSensor().size());
        list.add(totalSensors);
        //fh.close();
        return list;
    }

    /**
    public void checkIfAddSensorToRoom(RoomSensorsFromJson roomSensorFromJson) {
        //Logger logger = Logger.getLogger(LOG);
        for (SensorFromFile sensor : roomSensorFromJson.getSensor()) {
            if (!sensor.isOnRoom()) {
                //logger.warning("Impossible to add sensor " + sensor.getId() + " ,no Room match");
            }
        }
    }**/


    public boolean addSensorToRoom(Room room, SensorFromFile sensorFromFile) {
        //Logger logger = Logger.getLogger(LOG);
        SensorType sensorType = new SensorType(sensorFromFile.getType());
        RoomSensor importedSensor = new RoomSensor(sensorFromFile.getId(), sensorFromFile.getName(), sensorType, sensorFromFile.getStartDate(),
                sensorFromFile.getUnits());
        if (!room.getListOfSensors().contains(importedSensor)) {
            room.addSensor(importedSensor);
            importedSensor.setRoom(room);
            sensorTypeDomainService.add(importedSensor.getSensorType());
            return true;
        } else
            //logger.warning("Impossible to add sensor " + importedSensor.getIdOfRoomSensor() + " to Room, this Sensor already exists");
        return false;
    }

/**

    public FileHandler getFh() throws IOException {
        Logger logger = Logger.getLogger(LOG);
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(logger.getName()).getFile());
        String path = file.getPath();
        FileHandler fh = new FileHandler(URLDecoder.decode(path, "UTF-8"));
        logger.addHandler(fh);
        logger.setUseParentHandlers(false);
        SimpleFormatter formatter = new SimpleFormatter();
        fh.setFormatter(formatter);
        return fh;
    }

 **/

}
