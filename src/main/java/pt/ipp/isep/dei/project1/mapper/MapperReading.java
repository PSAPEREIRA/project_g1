package pt.ipp.isep.dei.project1.mapper;

import pt.ipp.isep.dei.project1.dto.sensordto.ReadingDto;
import pt.ipp.isep.dei.project1.model.sensor.Reading;

public final class MapperReading {

    protected MapperReading()  {
        throw new IllegalStateException("Utility class");
    }

    public static ReadingDto toDto (Reading reading) {
        ReadingDto readingDto = new ReadingDto();
        if(reading==null) {
            readingDto.setStatus(1);
            return readingDto;
        }
        readingDto.setDateTime(reading.getDateTime());
        readingDto.setValue(reading.getValue());
        return readingDto;
    }
}
