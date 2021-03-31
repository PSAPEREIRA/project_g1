package pt.ipp.isep.dei.project1.readers.xml;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;

import java.util.Collections;
import java.util.List;

public class AreaSensorsFromXml {

    private List<SensorFromFile> sensor;



    @JacksonXmlElementWrapper(useWrapping = false)
    public List<SensorFromFile> getSensor() {
        return Collections.unmodifiableList(sensor);
    }

    public void setSensor(List<SensorFromFile> sensorFromFileList) {
        this.sensor = Collections.unmodifiableList(sensorFromFileList);
    }

    @Override
    public String toString(){
        return sensor +"\n";
    }
}
