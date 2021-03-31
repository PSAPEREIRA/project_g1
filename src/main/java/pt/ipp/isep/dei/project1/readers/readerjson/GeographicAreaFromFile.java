package pt.ipp.isep.dei.project1.readers.readerjson;

import com.fasterxml.jackson.annotation.JsonGetter;
import pt.ipp.isep.dei.project1.readers.xml.AreaSensorsFromXml;

import java.util.Collections;
import java.util.List;

public class GeographicAreaFromFile {

    private String id;
    private String description;
    private String type;
    private double width;
    private double length;
    private LocationFromFile location;
    private List<AreaSensorFromJson> areaSensorJsonFormat;
    private AreaSensorsFromXml areaSensorXmlFormat;


    public String getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public String getType() {
        return type;
    }

    public double getWidth() {
        return width;
    }

    public double getLength() {
        return length;
    }

    public LocationFromFile getLocation() {
        return location;
    }

    @JsonGetter("area_sensor")
    public List<AreaSensorFromJson> getAreaSensorJsonFormat() {
        return Collections.unmodifiableList(areaSensorJsonFormat);
    }


    @JsonGetter("area_sensors")
    public AreaSensorsFromXml getAreaSensorXmlFormat() {
        return areaSensorXmlFormat;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public void setLocation(LocationFromFile location) {
        this.location = location;
    }

    public void setAreaSensorJsonFormat(List<AreaSensorFromJson> areaSensorJsonFormat) {
        this.areaSensorJsonFormat = Collections.unmodifiableList(areaSensorJsonFormat);
    }
    public void setAreaSensorXmlFormat(AreaSensorsFromXml areaSensorXmlFormat) {
        this.areaSensorXmlFormat = areaSensorXmlFormat;
    }

    @Override
    public String toString() {
        return "id = "+id + "\n" +"description = " +description + "\n" + "type = "+type + "\n" +"width = "+ width + "\n" +"length = "+  length + "\n" +"location = "+ location + "\n" +"areaSensorJsonFormat = "+ areaSensorJsonFormat + "\n" +"areaSensorXmlFormat = "+ areaSensorXmlFormat;
    }


}
