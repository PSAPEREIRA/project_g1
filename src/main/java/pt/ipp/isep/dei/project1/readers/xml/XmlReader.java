package pt.ipp.isep.dei.project1.readers.xml;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import pt.ipp.isep.dei.project1.dto.sensordto.ReadingDto;
import pt.ipp.isep.dei.project1.readers.FileReader;
import pt.ipp.isep.dei.project1.readers.readerjson.GeographicAreaFromFile;
import pt.ipp.isep.dei.project1.readers.readerjson.HouseFromJsonFile;
import pt.ipp.isep.dei.project1.readers.readerjson.ListOfGaFromFile;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class XmlReader implements FileReader {


    public String getReaderType() {
        return "XML";
    }

    @Override
    public List<GeographicAreaFromFile> importGaFromInputPath(String path) throws IOException {
        File file = new File(path);
        XmlMapper xmlMapper = new XmlMapper();

        ListOfGaFromFile list = xmlMapper.readValue(file, ListOfGaFromFile.class);

        return list.getGeographicalArea();
    }

    @Override
    public HouseFromJsonFile importHouseFromInputPath(String path) {
        return null;
    }

    @Override
    public List<ReadingDto> importReadingsOfSensor(String path) {
        ListOfReadingsFromXml readings;
        File file = new File(path);
        XmlMapper xmlMapper = new XmlMapper();

        int count = 0;
        try {
             readings = xmlMapper.readValue(file, ListOfReadingsFromXml.class);
        } catch (IOException e) {
            return Collections.emptyList();
        }
        List<ReadingDto> listOfReadings = new ArrayList<>();

        for (ReadingFromXml r : readings.getReading()){
            int index = r.getDateTime().indexOf('+');
            LocalDateTime localDateTime = parserDate(index, r.getDateTime());
            count++;
            ReadingDto readingDto = new ReadingDto(r.getIdOfSensor(), localDateTime, r.getValue(), r.getUnit(), count);
            listOfReadings.add(readingDto);
        }
        return listOfReadings;
    }

    private static LocalDateTime parserDate(int index, String date) {
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
    @Override
    public List<ReadingDto> importReadingsOfSensor(String path) {
        List<ReadingDto> readingDtoList = new ArrayList<>();
        int count = 0;

        File inputFile = new File(path);
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        Document doc;
        try {
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            dBuilder.setErrorHandler(null);
            doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();
        } catch (Exception e) {
            return Collections.emptyList();
        }
        NodeList nList = doc.getElementsByTagName("reading");
        for (int temp = 0; temp < nList.getLength(); temp++) {
            try {
                Node nNode = nList.item(temp);
                    Element eElement = (Element) nNode;
                    String idOfSensor = eElement.getElementsByTagName("id").item(0).getFirstChild().getNodeValue();
                    double valueImport = Double.parseDouble(eElement.getElementsByTagName("value").item(0).getFirstChild().getNodeValue());
                    String date = eElement.getElementsByTagName("timestamp_date").item(0).getFirstChild().getNodeValue();
                    String unit = eElement.getElementsByTagName("unit").item(0).getFirstChild().getNodeValue();
                    int index = date.indexOf('+');
                    LocalDateTime localDateTime = parserDate(index, date);
                    count++;
                    ReadingDto readingDto = new ReadingDto(idOfSensor, localDateTime, valueImport, unit, count);
                    readingDtoList.add(readingDto);
            } catch (Exception e) {
                continue;
            }
        }
        return readingDtoList;
    }


/**
 *
 *    public static List<GeographicAreaFromFile> importListGeoAreaFromXml(String path) throws IOException {
 *         File file = new File(path);
 *         XmlMapper xmlMapper = new XmlMapper();
 *
 *         ListOfGaFromFile list = xmlMapper.readValue(file, ListOfGaFromFile.class);
 *
 *         return list.getGeographicalArea();
 *
 *         /** catch (UnrecognizedPropertyException e) {
 *          // e.printStackTrace();
 *          System.out.println("CHECK UNRECOGNIZED");
 *          } catch (IOException e) {
 *          System.out.println("CHECK IOException");
 *          //  e.printStackTrace();
 *          } catch (NullPointerException e) {
 *          System.out.println("CHECK NullPointerException");
 *          //  e.printStackTrace();
 *          }
 *
 }

 public List<String> importGaFromInputPath(ListOfGeographicArea listOfGeographicArea, String pathInput, ListOfGeographicAreasTypes listOfGeographicAreasTypes) throws Exception {

 List<GeographicAreaFromFile> listOfGeoXmlDummy = importListGeoAreaFromXml(pathInput);

 List<GeographicArea> listGeoAreas = createListOfGeoAreasFromXml(listOfGeoXmlDummy).getAllSensors();

 List<String> namesOfAddedGeos = new ArrayList<>();

 for (GeographicArea geoArea : listGeoAreas) {
 boolean checkIfAddGeo = listOfGeographicArea.add(geoArea);
 if (checkIfAddGeo) {
 namesOfAddedGeos.add(geoArea.getType());
 listOfGeographicAreasTypes.add(geoArea.getType());
 }

 }
 return namesOfAddedGeos;
 }

 public ListOfGeographicArea createListOfGeoAreasFromXml(List<GeographicAreaFromFile> listOfGeoXmlDummy) {

 ListOfGeographicArea listOfGeographicArea = new ListOfGeographicArea();
 for (int i = 0; i < listOfGeoXmlDummy.size(); i++) {

 GeographicAreaFromFile geoXml = listOfGeoXmlDummy.get(i);

 ListOfSensors importedListOfSensors = createListOfSensorsFromGeoXml(geoXml);

 Location importedGeoLocation = new Location(geoXml.getLocation().getLatitude(), geoXml.getLocation().getLongitude(), geoXml.getLocation().getAltitude());
 GeographicArea importedGeo = new GeographicArea(geoXml.getId(), geoXml.getDescription(), new OccupationArea(importedGeoLocation, geoXml.getWidth(), geoXml.getLength()), new GeographicAreaType(geoXml.getType()));

 importedGeo.setListOfAreaSensors(importedListOfSensors);

 listOfGeographicArea.add(importedGeo);
 }

 return listOfGeographicArea;
 }

 public ListOfSensors createListOfSensorsFromGeoXml(GeographicAreaFromFile geoXml) {

 ListOfSensors listOfSensors = new ListOfSensors();

 for (int i = 0; i < geoXml.getAreaSensorXmlFormat().getSensor().size(); i++) {

 SensorFromFile importedXmlSens = geoXml.getAreaSensorXmlFormat().getSensor().get(i);

 LocationFromFile sensorFromXmlLocation = geoXml.getAreaSensorXmlFormat().getSensor().get(i).getLocation();
 Location sensorLocation = new Location(sensorFromXmlLocation.getLatitude(), sensorFromXmlLocation.getLongitude(), sensorFromXmlLocation.getAltitude());


 AreaSensor importedSensor = new AreaSensor(importedXmlSens.getId(), importedXmlSens.getType(), sensorLocation, new SensorType(importedXmlSens.getType()), importedXmlSens.getStartDate(), importedXmlSens.getUnits());


 listOfSensors.addSensorToList(importedSensor);
 }

 return listOfSensors;
 }
 */

}