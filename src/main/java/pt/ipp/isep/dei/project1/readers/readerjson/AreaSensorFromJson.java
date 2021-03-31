package pt.ipp.isep.dei.project1.readers.readerjson;

import pt.ipp.isep.dei.project1.readers.xml.SensorFromFile;

public class AreaSensorFromJson {

    private SensorFromFile sensor;
    private LocationFromFile location;

    public SensorFromFile getSensor() {
        return sensor;
    }

    public LocationFromFile getLocation() {
        return location;
    }

    public void setSensor(SensorFromFile sensor) {
        this.sensor = sensor;
    }

    public void setLocation(LocationFromFile location) {
        this.location = location;
    }

    @Override
    public String toString(){
        return sensor +"\n"+location;
    }
}
