package pt.ipp.isep.dei.project1.readers.xml;

import com.fasterxml.jackson.annotation.JsonGetter;

public class ReadingFromXml {

    private String idOfSensor;
    private String dateTime;
    private double value;
    private String unit;

    @JsonGetter("id")
    public String getIdOfSensor() {
        return idOfSensor;
    }

    @JsonGetter("timestamp_date")
    public String getDateTime() {
        return dateTime;
    }

    public double getValue() {
        return value;
    }

    public String getUnit() {
        return unit;
    }

    @Override
    public String toString() {
        return idOfSensor + "\n" + dateTime + "\n" + value + "\n" + unit + "\n";
    }

}
