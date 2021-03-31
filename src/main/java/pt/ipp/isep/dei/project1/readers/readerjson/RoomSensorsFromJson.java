package pt.ipp.isep.dei.project1.readers.readerjson;

import pt.ipp.isep.dei.project1.readers.xml.SensorFromFile;

import java.util.Collections;
import java.util.List;

public class RoomSensorsFromJson {

    private List<SensorFromFile> sensors;


    public List<SensorFromFile> getSensor() {
        return Collections.unmodifiableList(sensors);
    }

    public void setSensor(List<SensorFromFile> sensor) {
        this.sensors = Collections.unmodifiableList(sensor);
    }
}
