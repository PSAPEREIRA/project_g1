package pt.ipp.isep.dei.project1.controllers.geographicarea;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pt.ipp.isep.dei.project1.model.geographicarea.*;
import pt.ipp.isep.dei.project1.model.repositories.GeographicAreaRepository;
import pt.ipp.isep.dei.project1.model.repositories.SensorTypeRepository;
import pt.ipp.isep.dei.project1.model.sensor.AreaSensor;
import pt.ipp.isep.dei.project1.model.sensor.SensorType;
import pt.ipp.isep.dei.project1.model.repositories.GeographicAreaDomainService;
import pt.ipp.isep.dei.project1.readers.FileReader;
import pt.ipp.isep.dei.project1.readers.readerjson.JsonReader;
import pt.ipp.isep.dei.project1.readers.xml.XmlReader;
import pt.ipp.isep.dei.project1.model.repositories.GeographicAreaTypeRepo;
import pt.ipp.isep.dei.project1.model.repositories.SensorTypeDomainService;

import java.io.File;
import java.net.URLDecoder;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


@ExtendWith(MockitoExtension.class)
class ImportGeosFromFileControllerV2Test {

    @Mock
    private SensorTypeRepository sensorTypeRepository;
    @Mock
    private SensorTypeDomainService sensorTypeDomainService;
    @Mock
    private GeographicAreaRepository geographicAreaRepository;
    @Mock
    private GeographicAreaDomainService geographicAreaDomainService;
    @Mock
    private ImportGeosFromFileControllerV2 controllerV2;
    @Mock
    private GeographicAreaTypeRepo geographicAreaTypeRepo;

    @BeforeEach
    void initUseCase() {
        sensorTypeDomainService = new SensorTypeDomainService(sensorTypeRepository);
        geographicAreaDomainService = new GeographicAreaDomainService(geographicAreaRepository);
        controllerV2 = new ImportGeosFromFileControllerV2(geographicAreaDomainService, geographicAreaTypeRepo, sensorTypeDomainService);

    }


    private GeographicAreaDomainService testListOfGeos() {

        GeographicArea isep = new GeographicArea("ISEP", "Campus do ISEP",
                new OccupationArea(new Location(41.178553, -8.608035, 111), 0.261, 0.249),
                new GeographicAreaType("urban area"));

        AreaSensor areaSensorIsep1 = new AreaSensor("1", "Meteo station ISEP - rainfall", new Location(41.179230, -8.606409, 125),
                new SensorType("rainfall"), LocalDate.of(2016, 11, 15), "l/m2");
        areaSensorIsep1.setIdOfAreaSensor("RF12345");

        AreaSensor areaSensorIsep2 = new AreaSensor("1", "Meteo station ISEP - temperature", new Location(41.179230, -8.606409, 125),
                new SensorType("temperature"), LocalDate.of(2016, 11, 15), "C");

        areaSensorIsep2.setIdOfAreaSensor("TT12346");


        isep.addSensorToList(areaSensorIsep1);
        isep.addSensorToList(areaSensorIsep2);


        GeographicArea porto = new GeographicArea("Porto", "City of Porto",
                new OccupationArea(new Location(41.149935, -8.610857, 118), 10.09, 3.30),
                new GeographicAreaType("city"));

        AreaSensor areaSensorPorto1 = new AreaSensor("1", "Meteo station CMP - rainfall", new Location(41.179230, -8.606409, 139),
                new SensorType("rainfall"), LocalDate.of(2017, 11, 15), "l/m2");
        areaSensorPorto1.setIdOfAreaSensor("RF12334");

        AreaSensor areaSensorPorto2 = new AreaSensor("1", "Meteo station CMP - temperature", new Location(41.179230, -8.606409, 139),
                new SensorType("temperature"), LocalDate.of(2017, 11, 16), "C");

        areaSensorPorto2.setIdOfAreaSensor("TT1236A");


        porto.addSensorToList(areaSensorPorto1);
        porto.addSensorToList(areaSensorPorto2);


        geographicAreaTypeRepo.add(isep.getGeographicAreaType());

        geographicAreaTypeRepo.add(porto.getGeographicAreaType());

        geographicAreaDomainService.add(isep);
        geographicAreaDomainService.add(porto);

        return geographicAreaDomainService;
    }


    @Test
    void getAvailableReaderTypesObjectsJsonTest() throws Exception {

        List<FileReader> expectedResult = new ArrayList<>();
        JsonReader jsonReader = new JsonReader();
        XmlReader xmlReader = new XmlReader();
        expectedResult.add(jsonReader);
        expectedResult.add(xmlReader);

        List<FileReader> result = controllerV2.getAvailableReaderTypesObjects();
        assertEquals(expectedResult.get(0).getReaderType(), result.get(0).getReaderType());
    }

    @Test
    void getAvailableReaderTypesObjectsXmlTest() throws Exception {

        List<FileReader> expectedResult = new ArrayList<>();
        JsonReader jsonReader = new JsonReader();
        XmlReader xmlReader = new XmlReader();
        expectedResult.add(jsonReader);
        expectedResult.add(xmlReader);

        List<FileReader> result = controllerV2.getAvailableReaderTypesObjects();
        assertEquals(expectedResult.get(1).getReaderType(), result.get(1).getReaderType());
    }

    @Test
    void importFromFileJsonTest() throws Exception {

       // testListOfGeos();


        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("DataSet_sprint04_GA.json").getFile());
        String path = file.getAbsolutePath();

        JsonReader jsonReader = new JsonReader();


        List<String> result = controllerV2.importFromFile(URLDecoder.decode(path, "UTF-8"));

        List<String> expectedResult = new ArrayList<>();

        expectedResult.add("JSON file imported with Success!");
        expectedResult.add("The following areas were imported:");
        expectedResult.add("ISEP");
        expectedResult.add("Porto");
/*
        System.out.println(geographicAreaRepository.findAll());
        System.out.println(areaSensorRepository.findAll());
        System.out.println(geographicAreaService.getListOfGeographicArea().get(0));*/


        assertTrue(expectedResult.equals(result) && geographicAreaDomainService.getListOfGeographicArea().get(0).equals(geographicAreaDomainService.getListOfGeographicArea().get(0).getListOfAreaSensors().get(0).getGeographicArea() ));

       //assertEquals(expectedResult, result);
       //assertEquals(geographicAreaService.getListOfGeographicArea().get(0),geographicAreaService.getListOfGeographicArea().get(0).getListOfAreaSensors().get(0).getGeographicAreaID());
    }


    @Test
    void importFromFileXmlTest() throws Exception {

        GeographicArea isep = new GeographicArea("ISEP", "Campus do ISEP",
                new OccupationArea(new Location(41.178553, -8.608035, 111), 0.261, 0.249),
                new GeographicAreaType("urban area"));

        AreaSensor areaSensorIsep1 = new AreaSensor("1", "Meteo station ISEP - rainfall", new Location(41.179230, -8.606409, 125),
                new SensorType("rainfall"), LocalDate.of(2016, 11, 15), "l/m2");
        areaSensorIsep1.setIdOfAreaSensor("RF12345");

        AreaSensor areaSensorIsep2 = new AreaSensor("1", "Meteo station ISEP - temperature", new Location(41.179230, -8.606409, 125),
                new SensorType("temperature"), LocalDate.of(2016, 11, 15), "C");

        areaSensorIsep2.setIdOfAreaSensor("TT12346");


        isep.addSensorToList(areaSensorIsep1);
        isep.addSensorToList(areaSensorIsep2);


        GeographicArea porto = new GeographicArea("Porto", "City of Porto",
                new OccupationArea(new Location(41.149935, -8.610857, 118), 10.09, 3.30),
                new GeographicAreaType("city"));

        AreaSensor areaSensorPorto1 = new AreaSensor("1", "Meteo station CMP - rainfall", new Location(41.179230, -8.606409, 139),
                new SensorType("rainfall"), LocalDate.of(2017, 11, 15), "l/m2");
        areaSensorPorto1.setIdOfAreaSensor("RF12334");

        AreaSensor areaSensorPorto2 = new AreaSensor("1", "Meteo station CMP - temperature", new Location(41.179230, -8.606409, 139),
                new SensorType("temperature"), LocalDate.of(2017, 11, 16), "C");

        areaSensorPorto2.setIdOfAreaSensor("TT1236A");


        porto.addSensorToList(areaSensorPorto1);
        porto.addSensorToList(areaSensorPorto2);


        geographicAreaTypeRepo.add(isep.getGeographicAreaType());

        geographicAreaTypeRepo.add(porto.getGeographicAreaType());

        geographicAreaDomainService.add(isep);
        geographicAreaDomainService.add(porto);


        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("DataSet_sprint05_GA.xml").getFile());
        String path = file.getAbsolutePath();

        XmlReader xmlReader = new XmlReader();


        List<String> result = controllerV2.importFromFile(URLDecoder.decode(path, "UTF-8"));

        List<String> expectedResult = new ArrayList<>();

        expectedResult.add("All areas in this file were already imported!");

        assertEquals(expectedResult, result);
    }

    @Test
    void importFromFileAfterAlreadyImported() throws Exception {

        GeographicArea isep = new GeographicArea("ISEP", "Campus do ISEP",
                new OccupationArea(new Location(41.178553, -8.608035, 111), 0.261, 0.249),
                new GeographicAreaType("urban area"));

        AreaSensor areaSensorIsep1 = new AreaSensor("1", "Meteo station ISEP - rainfall", new Location(41.179230, -8.606409, 125),
                new SensorType("rainfall"), LocalDate.of(2016, 11, 15), "l/m2");
        areaSensorIsep1.setIdOfAreaSensor("RF12345");

        AreaSensor areaSensorIsep2 = new AreaSensor("1", "Meteo station ISEP - temperature", new Location(41.179230, -8.606409, 125),
                new SensorType("temperature"), LocalDate.of(2016, 11, 15), "C");

        areaSensorIsep2.setIdOfAreaSensor("TT12346");


        isep.addSensorToList(areaSensorIsep1);
        isep.addSensorToList(areaSensorIsep2);


        GeographicArea porto = new GeographicArea("Porto", "City of Porto",
                new OccupationArea(new Location(41.149935, -8.610857, 118), 10.09, 3.30),
                new GeographicAreaType("city"));

        AreaSensor areaSensorPorto1 = new AreaSensor("1", "Meteo station CMP - rainfall", new Location(41.179230, -8.606409, 139),
                new SensorType("rainfall"), LocalDate.of(2017, 11, 15), "l/m2");
        areaSensorPorto1.setIdOfAreaSensor("RF12334");

        AreaSensor areaSensorPorto2 = new AreaSensor("1", "Meteo station CMP - temperature", new Location(41.179230, -8.606409, 139),
                new SensorType("temperature"), LocalDate.of(2017, 11, 16), "C");

        areaSensorPorto2.setIdOfAreaSensor("TT1236A");


        porto.addSensorToList(areaSensorPorto1);
        porto.addSensorToList(areaSensorPorto2);


        geographicAreaTypeRepo.add(isep.getGeographicAreaType());

        geographicAreaTypeRepo.add(porto.getGeographicAreaType());


        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("DataSet_sprint05_GA.xml").getFile());
        String path = file.getAbsolutePath();

        //     XmlReader xmlReader = new XmlReader();


        controllerV2.importFromFile(URLDecoder.decode(path, "UTF-8"));
        List<String> result = controllerV2.importFromFile(URLDecoder.decode(path, "UTF-8"));


        List<String> expectedResult = new ArrayList<>();
        expectedResult.add("All areas in this file were already imported!");


        assertEquals(expectedResult, result);
    }

    @Test
    void importFileNotProperFile() throws Exception {


        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("teste.xml").getFile());
        String path = file.getAbsolutePath();


        List<String> result = controllerV2.importFromFile(URLDecoder.decode(path, "UTF-8"));


        List<String> expectedResult = new ArrayList<>();
        expectedResult.add("The data from the file you are importing is not compatible with this function");


        assertEquals(expectedResult, result);
    }

    @Test
    void importFileNotFoundExepct() throws Exception {
        testListOfGeos();


/*
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("qw").getFile());
        String path = file.getAbsolutePath();
*/


        List<String> result = controllerV2.importFromFile(URLDecoder.decode("wugabUga", "UTF-8"));


        List<String> expectedResult = new ArrayList<>();
        expectedResult.add("Invalid path/file!");
        expectedResult.add("Please insert a valid file name and path.");


        assertEquals(expectedResult, result);
    }

}