package pt.ipp.isep.dei.project1.readers.readerjson;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project1.readers.xml.SensorFromFile;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RoomSensorsFromJsonTest {

    @Test
    void getSensor() {
        RoomSensorsFromJson roomSensorFromJson = new RoomSensorsFromJson();
        SensorFromFile sensorFromFile = new SensorFromFile();
        List<SensorFromFile> sensorFromFileList = new ArrayList<>();
        sensorFromFileList.add(sensorFromFile);
        roomSensorFromJson.setSensor(sensorFromFileList);
        List<SensorFromFile> expectedResult = sensorFromFileList;
        List<SensorFromFile> result = roomSensorFromJson.getSensor();
        assertEquals(expectedResult, result);
    }

}