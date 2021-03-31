package pt.ipp.isep.dei.project1.readers.readercsv;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ReadingFromCSV {


    @JsonAlias({"id", "idLocal"})
    private String id;
    @JsonProperty("timestamp/date")
    private String date;
    @JsonProperty("value")
    private double value;
    @JsonProperty("unit")
    private String unit;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public String getUnit() {
        return unit;
    }

    @Override
    public String toString() {
        return "ReadingFromCSV{" +
                "id='" + getId() + '\'' +
                ", date='" + getDate() + '\'' +
                ", value=" + getValue() +
                ", unit='" + getUnit() + '\'' +
                '}';
    }
}
