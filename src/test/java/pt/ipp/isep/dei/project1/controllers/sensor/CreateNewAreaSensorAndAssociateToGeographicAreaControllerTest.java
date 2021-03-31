package pt.ipp.isep.dei.project1.controllers.sensor;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pt.ipp.isep.dei.project1.dto.geographicareadto.GeographicAreaDto;
import pt.ipp.isep.dei.project1.dto.geographicareadto.ListGeographicAreaDto;
import pt.ipp.isep.dei.project1.dto.sensordto.ListOfSensorTypesDto;
import pt.ipp.isep.dei.project1.dto.sensordto.SensorTypeDto;
import pt.ipp.isep.dei.project1.mapper.MapperGeographicArea;
import pt.ipp.isep.dei.project1.mapper.MapperSensorType;
import pt.ipp.isep.dei.project1.model.geographicarea.GeographicArea;
import pt.ipp.isep.dei.project1.model.geographicarea.GeographicAreaType;
import pt.ipp.isep.dei.project1.model.geographicarea.Location;
import pt.ipp.isep.dei.project1.model.geographicarea.OccupationArea;
import pt.ipp.isep.dei.project1.model.repositories.GeographicAreaRepository;
import pt.ipp.isep.dei.project1.model.repositories.SensorTypeRepository;
import pt.ipp.isep.dei.project1.model.sensor.AreaSensor;
import pt.ipp.isep.dei.project1.model.sensor.SensorType;
import pt.ipp.isep.dei.project1.model.repositories.GeographicAreaDomainService;
import pt.ipp.isep.dei.project1.model.repositories.SensorTypeDomainService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CreateNewAreaSensorAndAssociateToGeographicAreaControllerTest {

    @Mock
    private GeographicAreaRepository repo;
    @Mock
    private GeographicAreaDomainService geographicAreaDomainService;
    @Mock
    private CreateNewSensorAndAssociateToGeographicAreaController ctrll6;

    @Mock
    private SensorTypeRepository sensorTypeRepository;
    @Mock
    private SensorTypeDomainService sensorTypeDomainService;


    @BeforeEach
    void initMocks() {
        geographicAreaDomainService = new GeographicAreaDomainService(repo);
        ctrll6 = new CreateNewSensorAndAssociateToGeographicAreaController(geographicAreaDomainService, sensorTypeDomainService);
        sensorTypeDomainService = new SensorTypeDomainService(sensorTypeRepository);

    }

    @Test
    void testGetListOfSensorType() {
        SensorType m1 = new SensorType("temperature");
        sensorTypeDomainService.add(m1);
        GeographicArea geographicArea = new GeographicArea("porto","city",new OccupationArea(new Location(10,20,0),10,10),new GeographicAreaType("city"));
        geographicAreaDomainService.add(geographicArea);
        ListOfSensorTypesDto result = ctrll6.getListOfSensorsType();

        assertEquals(0,result.getSensorTypeDtos().size());
    }

    @Test
    void checkLocationOfSensorTrue() {
        SensorType m1 = new SensorType("temperature");
        sensorTypeDomainService.add(m1);
        GeographicArea geographicArea = new GeographicArea("porto","city",new OccupationArea(new Location(10,20,0),10,10),new GeographicAreaType("city"));
        AreaSensor areaSensor = new AreaSensor("1","s1",new Location(10.0001,20.00001,0),new SensorType("temp"),LocalDate.of(2018,12,10),"C");
        geographicAreaDomainService.add(geographicArea);
        GeographicAreaDto geographicAreaDto = MapperGeographicArea.toDto(geographicArea);
        List<Double> doubleList = new ArrayList<>();
        doubleList.add(10.0001);
        doubleList.add(20.00001);
        doubleList.add(0.0);
        Boolean result = ctrll6.checkLocationOfTheSensor(doubleList,geographicAreaDto);
        assertTrue(result);
    }

    @Test
    void checkLocationOfSensorFalse() {
        SensorType m1 = new SensorType("temperature");
        sensorTypeDomainService.add(m1);

        GeographicArea geographicArea = new GeographicArea("porto","city",new OccupationArea(new Location(10,20,0),10,10),new GeographicAreaType("city"));
        AreaSensor areaSensor = new AreaSensor("1","s1",new Location(13.0001,23.00001,0),new SensorType("temp"),LocalDate.of(2018,12,10),"C");
        geographicAreaDomainService.add(geographicArea);
        GeographicAreaDto geographicAreaDto = MapperGeographicArea.toDto(geographicArea);
        List<Double> doubleList = new ArrayList<>();
        doubleList.add(13.0001);
        doubleList.add(23.00001);
        doubleList.add(0.0);

        Boolean result = ctrll6.checkLocationOfTheSensor(doubleList,geographicAreaDto);
        assertFalse(result);
    }

    @Test
    void createNewSensor() {
        SensorType m1 = new SensorType("temperature");
        sensorTypeDomainService.add(m1);
        GeographicArea geographicArea = new GeographicArea("porto","city",new OccupationArea(new Location(10,20,0),10,10),new GeographicAreaType("city"));
        geographicAreaDomainService.add(geographicArea);
        String nameOfSensor = "sensor1";
        LocalDate localDate = LocalDate.of(2018,12,20);
        String unit = "ºC";
        GeographicAreaDto geographicAreaDto = MapperGeographicArea.toDto(geographicArea);
        SensorTypeDto sensorTypeDto = MapperSensorType.toDto(m1);
        List<Double> doubleList = new ArrayList<>();
        doubleList.add(13.0001);
        doubleList.add(23.00001);
        doubleList.add(0.0);
        boolean result = ctrll6.createNewSensorWithLocation("s1",nameOfSensor,doubleList,sensorTypeDto,localDate,unit,geographicAreaDto);
        assertTrue(result);
    }

    @Test
    void createNewSensorGetId() {
        SensorType m1 = new SensorType("temperature");
        sensorTypeDomainService.add(m1);
        GeographicArea geographicArea = new GeographicArea("porto","city",new OccupationArea(new Location(10,20,0),10,10),new GeographicAreaType("city"));
        geographicAreaDomainService.add(geographicArea);
        String nameOfSensor = "sensor1";
        LocalDate localDate = LocalDate.of(2018,12,20);
        String unit = "ºC";
        GeographicAreaDto geographicAreaDto = MapperGeographicArea.toDto(geographicArea);
        SensorTypeDto sensorTypeDto = MapperSensorType.toDto(m1);
        List<Double> doubleList = new ArrayList<>();
        doubleList.add(13.0001);
        doubleList.add(23.00001);
        doubleList.add(0.0);
        boolean createSensor = ctrll6.createNewSensorWithLocation("s1",nameOfSensor,doubleList,sensorTypeDto,localDate,unit,geographicAreaDto);
        assertTrue(createSensor);
    }

    @Test
    void createNewSensorAlreadyExists() {
        SensorType m1 = new SensorType("temperature");
        sensorTypeDomainService.add(m1);
        AreaSensor areaSensor = new AreaSensor("1","s1",new Location(13.0001,23.00001,0),new SensorType("temp"),LocalDate.of(2018,12,10),"C");
        GeographicArea geographicArea = new GeographicArea("porto","city",new OccupationArea(new Location(10,20,0),10,10),new GeographicAreaType("city"));
        areaSensor.setGeographicArea(geographicArea);
        geographicArea.addSensorToList(areaSensor);
        Location l1 = new Location(10,20,0);
        geographicAreaDomainService.add(geographicArea);
        geographicAreaDomainService.addSensorToList(areaSensor);
        String nameOfSensor = "s1";
        LocalDate localDate = LocalDate.of(2018,12,20);
        String unit = "ºC";
        GeographicAreaDto geographicAreaDto = MapperGeographicArea.toDto(geographicArea);
        SensorTypeDto sensorTypeDto = MapperSensorType.toDto(m1);
        List<Double> doubleList = new ArrayList<>();
        doubleList.add(13.0001);
        doubleList.add(23.00001);
        doubleList.add(0.0);
        boolean result = ctrll6.createNewSensorWithLocation("1",nameOfSensor,doubleList,sensorTypeDto,localDate,unit,geographicAreaDto);
        assertFalse(result);
    }


    @Test
    void getListOfGeographicAreas() {
        GeographicArea geographicArea = new GeographicArea("porto", "city", new OccupationArea(new Location(10, 10, 10), 20, 30), new GeographicAreaType("city"));
        GeographicArea geographicArea2 = new GeographicArea("lisboa", "city", new OccupationArea(new Location(20, 20, 20), 20, 30), new GeographicAreaType("city"));
        geographicAreaDomainService.add(geographicArea);
        geographicAreaDomainService.add(geographicArea2);
        ListGeographicAreaDto result = ctrll6.getListOfGeographicArea();
        List<String> expectedResult = new ArrayList<>();
        expectedResult.add(geographicArea.getName());
        expectedResult.add(geographicArea2.getName());
        assertEquals(expectedResult.get(1), result.getListOfGADto().get(1).getName());
    }



}