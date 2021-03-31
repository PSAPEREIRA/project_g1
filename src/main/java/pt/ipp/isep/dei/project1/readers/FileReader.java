package pt.ipp.isep.dei.project1.readers;

import pt.ipp.isep.dei.project1.dto.sensordto.ReadingDto;
import pt.ipp.isep.dei.project1.readers.readerjson.GeographicAreaFromFile;
import pt.ipp.isep.dei.project1.readers.readerjson.HouseFromJsonFile;

import java.io.IOException;
import java.util.List;

public interface FileReader {

    String getReaderType();

    List<ReadingDto> importReadingsOfSensor(String path) throws IOException;

    List<GeographicAreaFromFile> importGaFromInputPath(String path) throws IOException;

    HouseFromJsonFile importHouseFromInputPath (String path) throws IOException;

}
