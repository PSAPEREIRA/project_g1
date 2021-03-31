package pt.ipp.isep.dei.project1.readers.readerjson;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Collections;
import java.util.List;

public class ReadingsListFromJson {

    @JsonProperty
    private List<ReadingFromJson> readings;

    @Override
    public String toString() {
        return readings + "\n";
    }

    public List<ReadingFromJson> getReadings() {
        return Collections.unmodifiableList(readings);
    }
}
