package pt.ipp.isep.dei.project1.readers.readerjson;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ReadingFromJson {
    @JsonProperty("id")
    private String idOfSensor;
    @JsonProperty("timestamp/date")
    private String dateTime;
    @JsonProperty("value")
    private double value;
    @JsonProperty("unit")
    private String unit;


    public String getIdOfSensor() {
        return idOfSensor;
    }

    public String  getDateTime() {
        return dateTime;
    }

    public double getValue() {
        return value;
    }

    public String getUnit() {
        return unit;
    }

    public void setIdOfSensor(String idOfSensor) {
        this.idOfSensor = idOfSensor;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    @Override public String toString(){
        return dateTime +"\n"+value +"\n";
    }


}
