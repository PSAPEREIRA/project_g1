package pt.ipp.isep.dei.project1.readers.readerjson;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;

import java.util.Collections;
import java.util.List;

public class ListOfGaFromFile {

    private List<GeographicAreaFromFile> geographicalArea;

    @JacksonXmlElementWrapper(useWrapping = false)
    @JsonGetter("geographical_area")
    public List<GeographicAreaFromFile> getGeographicalArea() {
        return Collections.unmodifiableList(geographicalArea);
    }

    public void setGeographicalArea(List<GeographicAreaFromFile> geographicalArea) {
        this.geographicalArea = Collections.unmodifiableList(geographicalArea);
    }

    @Override
    public String toString() {
        return geographicalArea +"\n";
    }


}
