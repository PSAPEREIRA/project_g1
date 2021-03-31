package pt.ipp.isep.dei.project1.readers.readerjson;

import com.fasterxml.jackson.databind.ObjectMapper;
import pt.ipp.isep.dei.project1.dto.sensordto.ReadingDto;
import pt.ipp.isep.dei.project1.readers.FileReader;
import pt.ipp.isep.dei.project1.readers.xml.AreaSensorsFromXml;
import pt.ipp.isep.dei.project1.readers.xml.SensorFromFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class JsonReader implements FileReader {

    /**private ReadingsListFromJson readings;


    /***  private CoreProgramFromJson coreProgramFromJson; */

    public String getReaderType() {
        return "JSON";
    }

    @Override
    public List<GeographicAreaFromFile> importGaFromInputPath(String path) throws IOException {
        File file = new File(path);
        ObjectMapper objectMapper = new ObjectMapper();

        CoreProgramFromJson core = objectMapper.readValue(file, CoreProgramFromJson.class);

        List<GeographicAreaFromFile> listOfGeosFromCore = core.getGeographicalAreaList().getGeographicalArea();
        convertDummyClassesFromJsonToStandardFromFile(listOfGeosFromCore);

        return listOfGeosFromCore;
    }

    @Override
    public HouseFromJsonFile importHouseFromInputPath(String path) throws IOException {
        File file = new File(path);
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(file, HouseFromJsonFile.class);
    }

    private static void convertDummyClassesFromJsonToStandardFromFile(List<GeographicAreaFromFile> dummyListToBeConverted) {

        for (GeographicAreaFromFile geo : dummyListToBeConverted) {

            List<SensorFromFile> convertedSensorsToSet = convertAreaSensJsonInListOfSens(geo.getAreaSensorJsonFormat());

            AreaSensorsFromXml area = new AreaSensorsFromXml();
            area.setSensor(convertedSensorsToSet);

            geo.setAreaSensorXmlFormat(area);
        }
    }

    private static List<SensorFromFile> convertAreaSensJsonInListOfSens(List<AreaSensorFromJson> dummyAreaToBeConverted) {

        List<SensorFromFile> newSensorMadeFromAreaJson = new ArrayList<>();

        for (AreaSensorFromJson area : dummyAreaToBeConverted) {
            area.getSensor().setLocation(area.getLocation());
            newSensorMadeFromAreaJson.add(area.getSensor());
        }
        return newSensorMadeFromAreaJson;
    }

    @Override
    public List<ReadingDto> importReadingsOfSensor(String path) {
        ReadingsListFromJson readings;
        ObjectMapper objectMapper = new ObjectMapper();
        int count = 0;
        File file = new File(path);
        try {
            readings = objectMapper.readValue(file, ReadingsListFromJson.class);
        } catch (IOException e) {
            return Collections.emptyList();
        }
        List<ReadingDto> listOfReadings = new ArrayList<>();
        for (int i = 0; i < readings.getReadings().size(); i++) {
            ReadingFromJson readingJson = readings.getReadings().get(i);
            int index = readingJson.getDateTime().indexOf('+');
            LocalDateTime localDateTime;
            try {
                localDateTime = parserDate(index, readingJson.getDateTime());
            }
            catch (DateTimeParseException e){
                continue;
            }
            count++;
            ReadingDto readingDto = new ReadingDto(readingJson.getIdOfSensor(), localDateTime, readingJson.getValue(), readingJson.getUnit(), count);
            listOfReadings.add(readingDto);
        }
        return listOfReadings;
    }

    public LocalDateTime parserDate(int index, String date) {
        LocalDateTime localDateTime;
        if (index == -1) {
            LocalDate localDate = LocalDate.parse(date, DateTimeFormatter.ISO_LOCAL_DATE);
            localDateTime = localDate.atStartOfDay();
        } else {
            localDateTime = LocalDateTime.parse(date, DateTimeFormatter.ISO_OFFSET_DATE_TIME);
        }
        return localDateTime;
    }

    /**
     *    public static List<GeographicAreaFromFile> importListGeoAreaFromJsonFileToCore(String path) throws Exception {
     *         File file = new File(path);
     *         ObjectMapper objectMapper = new ObjectMapper();
     *
     *         CoreProgramFromJson core = objectMapper.readValue(file, CoreProgramFromJson.class);
     *
     *
     *         return core.getGeographicalAreaList().getGeographicalArea();
     *     }
     * public List<String> importGaFromInputPath(ListOfGeographicArea listOfGeographicArea, String path, ListOfGeographicAreasTypes listOfGeographicAreasTypes) throws Exception {
     * <p>
     * List<GeographicAreaFromFile> core = importListGeoAreaFromJsonFileToCore(path);
     * <p>
     * List<GeographicArea> listGeoAreas = createListOfGeoAreasFromCoreJson(core).getAllSensors();
     * <p>
     * List<String> namesOfAddedGeos = new ArrayList<>();
     * <p>
     * for (GeographicArea geoArea : listGeoAreas) {
     * boolean checkIfAddGeo = listOfGeographicArea.add(geoArea);
     * if (checkIfAddGeo) {
     * namesOfAddedGeos.add(geoArea.getType());
     * listOfGeographicAreasTypes.add(geoArea.getType());
     * }
     * }
     * return namesOfAddedGeos;
     * }
     * <p>
     * private ListOfGeographicArea createListOfGeoAreasFromCoreJson(List<GeographicAreaFromFile> core) {
     * <p>
     * ListOfGeographicArea listOfGeographicArea = new ListOfGeographicArea();
     * for (int i = 0; i < core.size(); i++) {
     * <p>
     * GeographicAreaFromFile geoDto = core.get(i);
     * <p>
     * ListOfSensors importedListOfSensors = createListOfSensorsFromGeoJson(geoDto);
     * <p>
     * Location importedGeoLocation = new Location(geoDto.getLocation().getLatitude(), geoDto.getLocation().getLongitude(), geoDto.getLocation().getAltitude());
     * GeographicArea importedGeo = new GeographicArea(geoDto.getId(), geoDto.getDescription(), new OccupationArea(importedGeoLocation, geoDto.getWidth(), geoDto.getLength()), new GeographicAreaType(geoDto.getType()));
     * <p>
     * importedGeo.setListOfAreaSensors(importedListOfSensors);
     * <p>
     * listOfGeographicArea.add(importedGeo);
     * }
     * <p>
     * return listOfGeographicArea;
     * }
     * <p>
     * private ListOfSensors createListOfSensorsFromGeoJson(GeographicAreaFromFile geographicAreaFromFile) {
     * <p>
     * ListOfSensors listOfSensors = new ListOfSensors();
     * <p>
     * for (int i = 0; i < geographicAreaFromFile.getAreaSensorJsonFormat().size(); i++) {
     * <p>
     * SensorFromFile importedDtoSensor = geographicAreaFromFile.getAreaSensorJsonFormat().get(i).getSensor();
     * LocationFromFile sensorLocationDto = geographicAreaFromFile.getAreaSensorJsonFormat().get(i).getLocation();
     * Location sensorLocation = new Location(sensorLocationDto.getLatitude(), sensorLocationDto.getLongitude(), sensorLocationDto.getAltitude());
     * <p>
     * AreaSensor importedSensor = new AreaSensor(importedDtoSensor.getId(), importedDtoSensor.getType(), sensorLocation, new SensorType(importedDtoSensor.getType()), importedDtoSensor.getStartDate(), importedDtoSensor.getUnits());
     * <p>
     * <p>
     * listOfSensors.addSensorToList(importedSensor);
     * }
     * <p>
     * return listOfSensors;
     * }
     */
}
