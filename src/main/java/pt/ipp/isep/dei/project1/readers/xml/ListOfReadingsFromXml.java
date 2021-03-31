package pt.ipp.isep.dei.project1.readers.xml;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;

import java.util.Collections;
import java.util.List;

public class ListOfReadingsFromXml {

    private List<ReadingFromXml> reading;

    @JacksonXmlElementWrapper(useWrapping = false)
    public List<ReadingFromXml> getReading() {
        return Collections.unmodifiableList(reading);
    }

    @Override
    public String toString() {
        return reading + "\n";
    }
}
