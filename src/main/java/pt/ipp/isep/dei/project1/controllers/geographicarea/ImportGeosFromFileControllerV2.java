package pt.ipp.isep.dei.project1.controllers.geographicarea;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import pt.ipp.isep.dei.project1.model.geographicarea.GeographicArea;
import pt.ipp.isep.dei.project1.model.geographicarea.GeographicAreaType;
import pt.ipp.isep.dei.project1.model.geographicarea.Location;
import pt.ipp.isep.dei.project1.model.geographicarea.OccupationArea;
import pt.ipp.isep.dei.project1.model.sensor.AreaSensor;
import pt.ipp.isep.dei.project1.model.sensor.SensorType;
import pt.ipp.isep.dei.project1.readers.FileReader;
import pt.ipp.isep.dei.project1.readers.readerjson.GeographicAreaFromFile;
import pt.ipp.isep.dei.project1.readers.readerjson.LocationFromFile;
import pt.ipp.isep.dei.project1.readers.xml.SensorFromFile;
import pt.ipp.isep.dei.project1.model.repositories.GeographicAreaDomainService;
import pt.ipp.isep.dei.project1.model.repositories.GeographicAreaTypeRepo;
import pt.ipp.isep.dei.project1.model.repositories.SensorTypeDomainService;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import static pt.ipp.isep.dei.project1.io.ui.utils.Configurations.importPackFromProperties;

@Controller
public class ImportGeosFromFileControllerV2 {

    private final GeographicAreaDomainService geographicAreaDomainService;
    private final GeographicAreaTypeRepo geographicAreaTypeRepo;
    private final SensorTypeDomainService sensorTypeDomainService;

    @Autowired
    public ImportGeosFromFileControllerV2(GeographicAreaDomainService geographicAreaDomainService, GeographicAreaTypeRepo geographicAreaTypeRepo, SensorTypeDomainService sensorTypeDomainService) {
        this.geographicAreaDomainService = geographicAreaDomainService;
        this.geographicAreaTypeRepo = geographicAreaTypeRepo;
        this.sensorTypeDomainService = sensorTypeDomainService;
    }

    public List<FileReader> getAvailableReaderTypesObjects() throws Exception {

        List<FileReader> fileReaderList = new ArrayList<>();


        List<String> readersList = importPackFromProperties("readerOfGaExtensions");

        for (int i = 0; i < readersList.size(); i++) {
            FileReader fileReaderObject = (FileReader) Class.forName(readersList.get(i)).newInstance();
            fileReaderList.add(fileReaderObject);
        }

        return fileReaderList;

    }

    private List<GeographicArea> createListOfGeoAreasFromFile(List<GeographicAreaFromFile> listOfGeoDummy) {

        List<GeographicArea> listOfGeographicArea = new ArrayList<>();

        for (GeographicAreaFromFile geoDummy : listOfGeoDummy) {
            List<AreaSensor> importedListOfAreaSensors = createListOfSensorsFromGeoFile(geoDummy);

            Location importedGeoLocation = new Location(geoDummy.getLocation().getLatitude(), geoDummy.getLocation().getLongitude(), geoDummy.getLocation().getAltitude());
            GeographicArea importedGeo = new GeographicArea(geoDummy.getId(), geoDummy.getDescription(), new OccupationArea(importedGeoLocation, geoDummy.getWidth(), geoDummy.getLength()), new GeographicAreaType(geoDummy.getType()));

            for (AreaSensor areaSensor : importedListOfAreaSensors) {
                importedGeo.addSensorToList(areaSensor);
                areaSensor.setGeographicArea(importedGeo);
                sensorTypeDomainService.add(areaSensor.getSensorType());
            }

            listOfGeographicArea.add(importedGeo);
        }

        return listOfGeographicArea;
    }

    private List<AreaSensor> createListOfSensorsFromGeoFile(GeographicAreaFromFile geoDummy) {


        List<AreaSensor> areaSensorList = new ArrayList<>();
        List<SensorFromFile> sensorFromFileList = geoDummy.getAreaSensorXmlFormat().getSensor();

        for (SensorFromFile sensorFromFile : sensorFromFileList) {


            LocationFromFile sensorFromFileLocation = sensorFromFile.getLocation();
            Location sensorLocation = new Location(sensorFromFileLocation.getLatitude(), sensorFromFileLocation.getLongitude(), sensorFromFileLocation.getAltitude());


            AreaSensor importedAreaSensor = new AreaSensor(sensorFromFile.getId(), sensorFromFile.getName(), sensorLocation, new SensorType(sensorFromFile.getType()), sensorFromFile.getStartDate(), sensorFromFile.getUnits());


            areaSensorList.add(importedAreaSensor);
        }

        return areaSensorList;
    }

    private List<String> getNamesOfAddedGeos(List<GeographicArea> listGeoAreasImported) {
        List<String> namesOfAddedGeos = new ArrayList<>();

        for (GeographicArea geoArea : listGeoAreasImported) {
            boolean checkIfAddGeo = geographicAreaDomainService.add(geoArea);
            if (checkIfAddGeo) {
                namesOfAddedGeos.add(geoArea.getName());
                geographicAreaTypeRepo.add(geoArea.getGeographicAreaType());
            }
        }
        return namesOfAddedGeos;
    }


    public List<String> importFromFile(String path) throws Exception {

        List<String> geoNames = new ArrayList<>();

        List<GeographicAreaFromFile> listOfImportedDummys;


        /**  try {*/
        List<FileReader> fileReadersObjects = getAvailableReaderTypesObjects();
        /**   } catch (Exception e) {
         e.printStackTrace();
         }*/

        for (FileReader fR : fileReadersObjects) {

            try {

                listOfImportedDummys = fR.importGaFromInputPath(path);

                List<GeographicArea> listGeoAreas = createListOfGeoAreasFromFile(listOfImportedDummys);
                geoNames = getNamesOfAddedGeos(listGeoAreas);
                geoNames = checkIfImported(geoNames, fR);

            } catch (FileNotFoundException e) {

                geoNames=checkNoFile();

            } catch (JsonParseException e) {

                e.getSuppressed();

            } catch (UnrecognizedPropertyException f) {

                geoNames.add("The data from the file you are importing is not compatible with this function");

            }

        }

        return geoNames;

    }

    private List<String> checkNoFile() {


        List<String> invalidMessage = new ArrayList<>();

        invalidMessage.add("Invalid path/file!");
        invalidMessage.add("Please insert a valid file name and path.");




        return invalidMessage;
    }

    private List<String> checkIfImported(List<String> geoNames, FileReader fR) {

        if (geoNames.isEmpty()) {
            geoNames.add("All areas in this file were already imported!");
        } else {
            List<String> check = new ArrayList<>(geoNames);

            geoNames.clear();

            geoNames.add(fR.getReaderType() + " file imported with Success!");
            geoNames.add("The following areas were imported:");

            geoNames.addAll(check);

        }


        return geoNames;
    }
}