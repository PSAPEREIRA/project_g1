package pt.ipp.isep.dei.project1.controllers.sensor;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pt.ipp.isep.dei.project1.dto.geographicareadto.GeographicAreaDto;
import pt.ipp.isep.dei.project1.dto.geographicareadto.ListGeographicAreaDto;
import pt.ipp.isep.dei.project1.dto.sensordto.ListOfAreaSensorsDto;
import pt.ipp.isep.dei.project1.dto.sensordto.AreaSensorDto;
import pt.ipp.isep.dei.project1.mapper.MapperGeographicArea;
import pt.ipp.isep.dei.project1.mapper.MapperListOfGeographicArea;
import pt.ipp.isep.dei.project1.mapper.MapperListOfAreaSensor;
import pt.ipp.isep.dei.project1.mapper.MapperAreaSensor;
import pt.ipp.isep.dei.project1.model.geographicarea.*;
import pt.ipp.isep.dei.project1.model.repositories.GeographicAreaRepository;
import pt.ipp.isep.dei.project1.model.sensor.AreaSensor;
import pt.ipp.isep.dei.project1.model.sensor.SensorType;
import pt.ipp.isep.dei.project1.model.sensor.Status;
import pt.ipp.isep.dei.project1.model.repositories.GeographicAreaDomainService;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
class DeactivateRemoveAreaSensorInGeographicAreaControllerTest {

    @Mock
    private GeographicAreaRepository repo;
    @Mock
    private GeographicAreaDomainService geographicAreaDomainService;



    @BeforeEach
    void initUseCase() {
        geographicAreaDomainService = new GeographicAreaDomainService(repo);

    }

    @Test
    void testGetListGeographicAreaDto() {
        //ARRANGE
        GeographicArea geographicArea = new GeographicArea("Porto", "city",
                new OccupationArea(new Location(10, 20, 30), 0.5, 0.5),
                new GeographicAreaType("city"));
        DeactivateRemoveSensorInGeographicAreaController deactivateRemoveSensorInGeographicAreaController =
                new DeactivateRemoveSensorInGeographicAreaController(geographicAreaDomainService);
        //ACT
        geographicAreaDomainService.add(geographicArea);
        ListGeographicAreaDto expectedResult = MapperListOfGeographicArea.toDto(geographicAreaDomainService.getListOfGeographicArea());
        ListGeographicAreaDto result = deactivateRemoveSensorInGeographicAreaController.getListGeographicAreaDto();
        //Assert
        assertEquals(expectedResult.getListOfGADto().size(), result.getListOfGADto().size());
    }


    @Test
    void getListOfSensorsDtoFromGA() {
        //ARRANGE
        GeographicArea geographicArea = new GeographicArea("Porto", "city",
                new OccupationArea(new Location(10, 20, 30), 0.5, 0.5),
                new GeographicAreaType("city"));
        AreaSensor areaSensor1 = new AreaSensor("1","s1", new Location(10.0001, 20.00001, 0),
                new SensorType("temp"), LocalDate.of(2018, 12, 10), "C");
        AreaSensor areaSensor2 = new AreaSensor("1","s2", new Location(10.0001, 20.00001, 0),
                new SensorType("temp"), LocalDate.of(2018, 12, 10), "C");
        DeactivateRemoveSensorInGeographicAreaController deactivateRemoveSensorInGeographicAreaController =
                new DeactivateRemoveSensorInGeographicAreaController(geographicAreaDomainService);
        //ACT
        geographicAreaDomainService.add(geographicArea);
        geographicArea.addSensorToList(areaSensor1);
        geographicArea.addSensorToList(areaSensor2);
        ListOfAreaSensorsDto expectedResult = MapperListOfAreaSensor.toDto(geographicArea.getListOfAreaSensors());
        GeographicAreaDto geographicAreaDto = MapperGeographicArea.toDto(geographicArea);
        //ASSERT
        ListOfAreaSensorsDto result = deactivateRemoveSensorInGeographicAreaController.getListOfSensorsDtoFromGA(geographicAreaDto);
        assertEquals(expectedResult.getListOfAreaSensorDto().size(), result.getListOfAreaSensorDto().size());
    }

    @Test
    void deactivateSensorTrue() {
        GeographicArea geographicArea = new GeographicArea("Porto", "city",
                new OccupationArea(new Location(10, 20, 30), 0.5, 0.5),
                new GeographicAreaType("city"));
        AreaSensor areaSensor1 = new AreaSensor("1","s1", new Location(10.0001, 20.00001, 0),
                new SensorType("temp"), LocalDate.of(2018, 12, 10), "C");
        LocalDate date1 = LocalDate.of(2019, 1, 29);
        LocalDate date2 = LocalDate.of(2019, 1, 30);
        LocalDate date3 = LocalDate.now();
        Status status1 = new Status(false, date1);
        Status status2 = new Status(true, date2);
        Status status3 = new Status(false, date3);
        areaSensor1.addStatus(status1);
        areaSensor1.addStatus(status2);
        areaSensor1.addStatus(status3);
        geographicAreaDomainService.add(geographicArea);
        geographicArea.addSensorToList(areaSensor1);
        DeactivateRemoveSensorInGeographicAreaController deactivateRemoveSensorInGeographicAreaController =
                new DeactivateRemoveSensorInGeographicAreaController(geographicAreaDomainService);
        GeographicAreaDto listGeographicAreaDto = MapperGeographicArea.toDto(geographicArea);
        AreaSensorDto areaSensorDto = MapperAreaSensor.toDto(areaSensor1);
        boolean result = deactivateRemoveSensorInGeographicAreaController.deactivateSensor(listGeographicAreaDto, areaSensorDto);
        assertTrue(result);
    }

    @Test
    void getListOfSensorsActivate() {
        GeographicArea geographicArea = new GeographicArea("Porto", "city",
                new OccupationArea(new Location(10, 20, 30), 0.5, 0.5),
                new GeographicAreaType("city"));
        AreaSensor areaSensor1 = new AreaSensor("1","s1", new Location(10.0001, 20.00001, 0),
                new SensorType("temp"), LocalDate.of(2018, 12, 10), "C");

        LocalDate date1 = LocalDate.of(2019, 1, 29);
        LocalDate date2 = LocalDate.of(2019, 1, 30);
        LocalDate date3 = LocalDate.now();
        Status status1 = new Status(false, date1);
        Status status2 = new Status(true,  date2);
       // Status status3 = new Status(false, date3);        //     Não quebra test coverage, verificar mutaçoes - Ivo
        areaSensor1.addStatus(status1);
        areaSensor1.addStatus(status2);
    //    areaSensor1.addStatus(status3);                   //     Não quebra test coverage, verificar mutaçoes - Ivo
        geographicAreaDomainService.add(geographicArea);
        geographicArea.addSensorToList(areaSensor1);
        DeactivateRemoveSensorInGeographicAreaController deactivateRemoveSensorInGeographicAreaController =
                new DeactivateRemoveSensorInGeographicAreaController(geographicAreaDomainService);

        ListOfAreaSensorsDto expectedResult = MapperListOfAreaSensor.toDto(geographicArea.getListOfAreaSensors());
        GeographicAreaDto geographicAreaDto = MapperGeographicArea.toDto(geographicArea);
        ListOfAreaSensorsDto result = deactivateRemoveSensorInGeographicAreaController.getListOfSensorsActivate(geographicAreaDto);
        assertEquals(expectedResult.getListOfAreaSensorDto().size(), result.getListOfAreaSensorDto().size());
    }


    @Test
    void testRemoveSensorFromGA() {
        //ARRANGE
        GeographicArea geographicArea = new GeographicArea("Porto", "city",
                new OccupationArea(new Location(10, 20, 30), 0.5, 0.5),
                new GeographicAreaType("city"));
        AreaSensor areaSensor1 = new AreaSensor("1","s1", new Location(10.0001, 20.00001, 0),
                new SensorType("temp"), LocalDate.of(2018, 12, 10), "C");
        AreaSensor areaSensor2 = new AreaSensor("1","s2", new Location(10.0001, 20.00001, 0),
                new SensorType("temp"), LocalDate.of(2018, 12, 10), "C");

        areaSensor1.setGeographicArea(geographicArea);
        areaSensor2.setGeographicArea(geographicArea);
        DeactivateRemoveSensorInGeographicAreaController deactivateRemoveSensorInGeographicAreaController =
                new DeactivateRemoveSensorInGeographicAreaController(geographicAreaDomainService);

        //ACT
        geographicAreaDomainService.add(geographicArea);
        geographicAreaDomainService.addSensorToList(areaSensor1);
        geographicAreaDomainService.addSensorToList(areaSensor2);
        //ASSERT
        AreaSensorDto areaSensorDto = MapperAreaSensor.toDto(areaSensor1);
        GeographicAreaDto geographicAreaDto = MapperGeographicArea.toDto(geographicArea);
        boolean result = deactivateRemoveSensorInGeographicAreaController.removeSensorFromGA(geographicAreaDto,areaSensorDto);
        assertTrue(result);
    }
}