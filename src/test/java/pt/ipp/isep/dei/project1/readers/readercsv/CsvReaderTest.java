package pt.ipp.isep.dei.project1.readers.readercsv;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project1.dto.sensordto.ReadingDto;
import pt.ipp.isep.dei.project1.readers.readerjson.HouseFromJsonFile;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class CsvReaderTest {

    @Test
    public void importReadingsOfSensor() throws UnsupportedEncodingException {

        ReadingDto r1 = new ReadingDto("TT12346OB", LocalDateTime.of(2018,12,30,2,0,0,0),14,"C",0);
        ReadingDto r2 = new ReadingDto("TT12346OB",LocalDateTime.of(2018,12,30,8,0,0,0),13.7,"C",0);
        ReadingDto r3 = new ReadingDto("TT12346OB",LocalDateTime.of(2018,12,30,14,0,0,0),16.5,"C",0);

        List<ReadingDto> expectedResult = new ArrayList<>();
        expectedResult.add(r1);
        expectedResult.add(r2);
        expectedResult.add(r3);

        CsvReader csvReader = new CsvReader();

        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("CsvReaderTest.csv").getFile());
        String path = file.getAbsolutePath();
        List<ReadingDto> result = csvReader.importReadingsOfSensor(URLDecoder.decode(path, "UTF-8"));

        //assertThat(expectedResult,hasItem(result.get(0)));
        //  assertEquals(expectedResult.get(1),result.get(1));
        assertTrue(expectedResult.get(0).getIdOfSensor().equals(result.get(0).getIdOfSensor())
                && expectedResult.get(1).getDateTime().isEqual(result.get(1).getDateTime())
                && expectedResult.get(2).getValue()== result.get(2).getValue());
    }

    @Test
    public void importReadingsOfSensorCover() throws UnsupportedEncodingException {

        ReadingDto r1 = new ReadingDto("TT12346OB", LocalDateTime.of(2018,12,30,2,0,0,0),14,"C",0);
        ReadingDto r2 = new ReadingDto("TT12346OB",LocalDateTime.of(2018,12,30,8,0,0,0),13.7,"C",0);
        ReadingDto r3 = new ReadingDto("TT12346OB",LocalDateTime.of(2018,12,30,14,0,0,0),16.5,"C",0);

        ReadingDto r4 = new ReadingDto("TT1236AC",LocalDateTime.of(2018,12,29,0,0,0,0),1.1,"mm",0);
        ReadingDto r5 = new ReadingDto("TT1236AC",LocalDateTime.of(2018,12,30,0,0,0,0),1.8,"mm",0);
        ReadingDto r6 = new ReadingDto("TT1236AC",LocalDateTime.of(2018,12,31,0,0,0,0),1.5,"mm",0);



        List<ReadingDto> expectedResult = new ArrayList<>();
        expectedResult.add(r1);
        expectedResult.add(r2);
        expectedResult.add(r3);
        expectedResult.add(r4);
        expectedResult.add(r5);
        expectedResult.add(r6);

        CsvReader csvReader = new CsvReader();

        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("CsvReaderTest.csv").getFile());
        String path = file.getAbsolutePath();

        List<ReadingDto> result = csvReader.importReadingsOfSensor(URLDecoder.decode(path, "UTF-8"));

        assertTrue(expectedResult.get(3).getIdOfSensor().equals(result.get(3).getIdOfSensor())
                && expectedResult.get(4).getDateTime().isEqual(result.get(4).getDateTime())
                && expectedResult.get(5).getValue()== result.get(5).getValue());
    }

    @Test
    public void importHouseFromInputPath() {

        CsvReader csvReader = new CsvReader();

        HouseFromJsonFile result  = csvReader.importHouseFromInputPath("wugabuga");

        assertNull(result);
    }
}