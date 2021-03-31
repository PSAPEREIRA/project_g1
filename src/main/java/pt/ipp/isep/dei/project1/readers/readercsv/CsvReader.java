package pt.ipp.isep.dei.project1.readers.readercsv;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

import pt.ipp.isep.dei.project1.dto.sensordto.ReadingDto;
import pt.ipp.isep.dei.project1.readers.FileReader;
import pt.ipp.isep.dei.project1.readers.readerjson.GeographicAreaFromFile;
import pt.ipp.isep.dei.project1.readers.readerjson.HouseFromJsonFile;

import java.io.File;
import java.io.IOException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CsvReader implements FileReader {

    @Override
    public String getReaderType() {
        return "CSV";
    }



    @Override
    public List<ReadingDto> importReadingsOfSensor(String path)  {
         File file = new File(path);

        CsvSchema bootstrapSchema = CsvSchema.emptySchema().withHeader();
        CsvMapper mapper = new CsvMapper();

        MappingIterator<ReadingFromCSV> mappingIterator;
        try {
            mappingIterator = mapper.readerFor(ReadingFromCSV.class).with(bootstrapSchema).readValues(file);
        } catch (IOException e) {
            return Collections.emptyList();
        }

        List<ReadingFromCSV> rList;
        try {
            rList = mappingIterator.readAll();
        } catch (IOException e) {
            return Collections.emptyList();
        }
        List<ReadingDto> rDtoList = new ArrayList<>();
        LocalDateTime localDateTimeParser;

        for (ReadingFromCSV r : rList) {
            String dTime = r.getDate();
            int index = dTime.indexOf('+');
            localDateTimeParser = parserDate(index, dTime);
            ReadingDto readingDto = new ReadingDto(r.getId(), localDateTimeParser, r.getValue(), r.getUnit(), 0);
            rDtoList.add(readingDto);
        }
        return rDtoList;



        /**List<ReadingDto> readingDtoList = new ArrayList<>();
        CSVParser csvParser;
        int count = 0;
        Path pathToFile;
        Reader reader;
        pathToFile = Paths.get(path);
        reader = readFile(pathToFile);
        if (reader == null)
            return Collections.emptyList();
        csvParser = new CSVParser(reader, CSVFormat.DEFAULT);
        for (CSVRecord csvRecord : csvParser) {
            String idOfSensor;
            String date;
            String value;
            String unit;
            double valueParser;
            LocalDateTime localDateTimeParser;
            try {
                idOfSensor = csvRecord.get(0);
                date = csvRecord.get(1);
                value = csvRecord.get(2);
                unit = csvRecord.get(3);
            } catch (ArrayIndexOutOfBoundsException e) {
                return Collections.emptyList();
            }
            try {
                valueParser = parserValue(value);
                int index = date.indexOf('+');
                localDateTimeParser = parserDate(index, date);
            } catch (java.time.DateTimeException | NumberFormatException e) {
                continue;
            }
            count++;
            ReadingDto readingDto = new ReadingDto(idOfSensor, localDateTimeParser, valueParser,unit, count);
            readingDtoList.add(readingDto);
        }
        return readingDtoList;**/
    }


    @Override
    public List<GeographicAreaFromFile> importGaFromInputPath(String path) {
        return Collections.emptyList();
    }

    @Override
    public HouseFromJsonFile importHouseFromInputPath(String path){
        return null;
    }

    public static LocalDateTime parserDate(int index, String date) {
        LocalDateTime localDateTime;
        if (index == -1) {
            DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate localDate = LocalDate.parse(date, formatter1);
            localDateTime = localDate.atStartOfDay();
        } else {
            localDateTime = LocalDateTime.parse(date, DateTimeFormatter.ISO_OFFSET_DATE_TIME);
        }
        return localDateTime;
    }
}
