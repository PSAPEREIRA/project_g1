package pt.ipp.isep.dei.project1.controllers.house;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pt.ipp.isep.dei.project1.model.geographicarea.GeographicArea;
import pt.ipp.isep.dei.project1.model.geographicarea.GeographicAreaType;
import pt.ipp.isep.dei.project1.model.geographicarea.Location;
import pt.ipp.isep.dei.project1.model.geographicarea.OccupationArea;
import pt.ipp.isep.dei.project1.model.house.House;
import pt.ipp.isep.dei.project1.model.repositories.GeographicAreaRepository;
import pt.ipp.isep.dei.project1.model.repositories.HouseRepository;
import pt.ipp.isep.dei.project1.model.sensor.*;
import pt.ipp.isep.dei.project1.model.repositories.GeographicAreaDomainService;
import pt.ipp.isep.dei.project1.model.repositories.HouseDomainService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class GetTheDayWithHighestTemperatureAmplitudeControllerTest {

    @Mock
    private HouseDomainService houseDomainService;
    @Mock
    private HouseRepository houseRepository;
    @Mock
    private GeographicAreaDomainService geographicAreaDomainService;
    @Mock
    private GeographicAreaRepository geographicAreaRepository;

    private GetTheDayWithHighestTemperatureAmplitudeController ctrl;

    @BeforeEach
    void initUseCase() {
        houseDomainService = new HouseDomainService(houseRepository);
        geographicAreaDomainService = new GeographicAreaDomainService(geographicAreaRepository);
        ctrl = new GetTheDayWithHighestTemperatureAmplitudeController(houseDomainService, geographicAreaDomainService);
    }


    @Test
    void getDayWithHighestTemperatureAmplitude() throws Exception {
        //Arrange
        GeographicArea geographicArea = new GeographicArea("Porto", "City", new OccupationArea
                (new Location(10, 20, 30), 0.5, 0.5),
                new GeographicAreaType("City"));
        House h1 = new House("Casa", new Location(40.7486, -73.9864, 0), geographicArea.getName());
        houseDomainService.add(h1);
        AreaSensor s1 = new AreaSensor("1","sensor Teste", new Location(10, 20, 30), new SensorType("temperature"), LocalDate.of(2018, 12, 10), "l/m2");
        ListOfReadings lRead1 = new ListOfReadings();
        LocalDate startDate = LocalDate.of(2019, 01, 13);
        LocalDate finalDate = LocalDate.of(2019, 01, 18);
        LocalDateTime date1 = LocalDateTime.of(2019, 1, 15, 12, 00);
        Reading r1 = new Reading(date1, 18);
        LocalDateTime date2 = LocalDateTime.of(2019, 1, 15, 12, 3);
        Reading r2 = new Reading(date2, 15);
        LocalDateTime date3 = LocalDateTime.of(2019, 1, 17, 12, 5);
        Reading r3 = new Reading(date3, 22);
        LocalDateTime date4 = LocalDateTime.of(2019, 1, 17, 12, 6);
        Reading r4 = new Reading(date4, 23);
        LocalDateTime date5 = LocalDateTime.of(2019, 1, 14, 12, 10);
        Reading r5 = new Reading(date5, 15);
        LocalDateTime date6 = LocalDateTime.of(2019, 1, 14, 12, 15);
        Reading r6 = new Reading(date6, 25);
        GetTheDayWithHighestTemperatureAmplitudeController getTheDayWithHighestTemperatureAmplitudeController = new GetTheDayWithHighestTemperatureAmplitudeController(houseDomainService, geographicAreaDomainService);
        //ACT
        geographicArea.addSensorToList(s1);
        lRead1.addReading(r1);
        lRead1.addReading(r2);
        lRead1.addReading(r3);
        lRead1.addReading(r4);
        lRead1.addReading(r5);
        lRead1.addReading(r6);
        s1.setListOfReadings(lRead1);
        List<LocalDate> localDates;
        geographicAreaDomainService.add(geographicArea);
        localDates = getTheDayWithHighestTemperatureAmplitudeController.getDayWithHighestTemperatureAmplitude(startDate, finalDate);
        LocalDate expectedResult = LocalDate.of(2019, 1, 14);
        //ASSERT
        assertEquals(expectedResult, localDates.get(0));
    }


    @Test
    void getDayWithHighestTemperatureAmplitudeWhenSensorIsNotOfTemperature() throws Exception {
        //Arrange
        GeographicArea geographicArea = new GeographicArea("Porto", "City", new OccupationArea
                (new Location(10, 20, 30), 0.5, 0.5),
                new GeographicAreaType("City"));
        House h1 = new House("Casa", new Location(40.7486, -73.9864, 0), geographicArea.getName());
        houseDomainService.add(h1);
        AreaSensor s1 = new AreaSensor("1","sensor Teste", new Location(10, 20, 30), new SensorType("rainfall"), LocalDate.of(2018, 12, 10), "l/m2");
        ListOfReadings lRead1 = new ListOfReadings();
        LocalDate startDate = LocalDate.of(2019, 01, 13);
        LocalDate finalDate = LocalDate.of(2019, 01, 18);
        LocalDateTime date1 = LocalDateTime.of(2019, 1, 15, 12, 00);
        Reading r1 = new Reading(date1, 18);
        LocalDateTime date2 = LocalDateTime.of(2019, 1, 15, 12, 3);
        Reading r2 = new Reading(date2, 15);
        LocalDateTime date3 = LocalDateTime.of(2019, 1, 17, 12, 5);
        Reading r3 = new Reading(date3, 22);
        LocalDateTime date4 = LocalDateTime.of(2019, 1, 17, 12, 6);
        Reading r4 = new Reading(date4, 23);
        LocalDateTime date5 = LocalDateTime.of(2019, 1, 14, 12, 10);
        Reading r5 = new Reading(date5, 15);
        LocalDateTime date6 = LocalDateTime.of(2019, 1, 14, 12, 15);
        Reading r6 = new Reading(date6, 25);
        GetTheDayWithHighestTemperatureAmplitudeController getTheDayWithHighestTemperatureAmplitudeController = new GetTheDayWithHighestTemperatureAmplitudeController(houseDomainService, geographicAreaDomainService);
        //ACT
        geographicArea.addSensorToList(s1);

        lRead1.addReading(r1);
        lRead1.addReading(r2);
        lRead1.addReading(r3);
        lRead1.addReading(r4);
        lRead1.addReading(r5);
        lRead1.addReading(r6);
        s1.setListOfReadings(lRead1);
        List<LocalDate> localDates;
        geographicAreaDomainService.add(geographicArea);
        localDates = getTheDayWithHighestTemperatureAmplitudeController.getDayWithHighestTemperatureAmplitude(startDate, finalDate);
        //ASSERT
        assertEquals(Collections.emptyList(), localDates);
    }

    @Test
    void getDayWithHighestTemperatureAmplitudeNoGeoArea() throws Exception {
        //Arrange
        GeographicArea geographicArea = new GeographicArea("Porto", "City", new OccupationArea
                (new Location(10, 20, 30), 0.5, 0.5),
                new GeographicAreaType("City"));
        House h1 = new House("Casa", new Location(40.7486, -73.9864, 0), geographicArea.getName());
        houseDomainService.add(h1);
        AreaSensor s1 = new AreaSensor("1","sensor Teste", new Location(10, 20, 30), new SensorType("temperature"), LocalDate.of(2018, 12, 10), "l/m2");
        ListOfReadings lRead1 = new ListOfReadings();
        LocalDate startDate = LocalDate.of(2019, 01, 13);
        LocalDate finalDate = LocalDate.of(2019, 01, 18);
        LocalDateTime date1 = LocalDateTime.of(2019, 1, 15, 12, 00);
        Reading r1 = new Reading(date1, 18);
        LocalDateTime date2 = LocalDateTime.of(2019, 1, 15, 12, 3);
        Reading r2 = new Reading(date2, 15);
        LocalDateTime date3 = LocalDateTime.of(2019, 1, 17, 12, 5);
        Reading r3 = new Reading(date3, 22);
        LocalDateTime date4 = LocalDateTime.of(2019, 1, 17, 12, 6);
        Reading r4 = new Reading(date4, 23);
        LocalDateTime date5 = LocalDateTime.of(2019, 1, 14, 12, 10);
        Reading r5 = new Reading(date5, 15);
        LocalDateTime date6 = LocalDateTime.of(2019, 1, 14, 12, 15);
        Reading r6 = new Reading(date6, 25);
        GetTheDayWithHighestTemperatureAmplitudeController getTheDayWithHighestTemperatureAmplitudeController = new GetTheDayWithHighestTemperatureAmplitudeController(houseDomainService, geographicAreaDomainService);
        //ACT
        geographicArea.addSensorToList(s1);
        lRead1.addReading(r1);
        lRead1.addReading(r2);
        lRead1.addReading(r3);
        lRead1.addReading(r4);
        lRead1.addReading(r5);
        lRead1.addReading(r6);
        s1.setListOfReadings(lRead1);
        List<LocalDate> localDates;
      //  geographicAreaRepo.add(geographicArea);
        localDates = getTheDayWithHighestTemperatureAmplitudeController.getDayWithHighestTemperatureAmplitude(startDate, finalDate);
        LocalDate expectedResult = LocalDate.of(2019, 1, 14);
        //ASSERT
        assertTrue(localDates.isEmpty());
    }

    @Test
    void getHighestTemperatureAmplitude() throws Exception {
        //Arrange
        GeographicArea geographicArea = new GeographicArea("Porto", "City", new OccupationArea
                (new Location(10, 20, 30), 0.5, 0.5),
                new GeographicAreaType("City"));
        House h1 = new House("Casa", new Location(40.7486, -73.9864, 0), geographicArea.getName());
        houseDomainService.add(h1);
        AreaSensor s1 = new AreaSensor("1","sensor Teste", new Location(10, 20, 30), new SensorType("temperature"), LocalDate.of(2018, 12, 10), "l/m2");
        ListOfReadings lRead1 = new ListOfReadings();
        LocalDate startDate = LocalDate.of(2019, 01, 13);
        LocalDate finalDate = LocalDate.of(2019, 01, 18);
        LocalDateTime date1 = LocalDateTime.of(2019, 1, 15, 12, 00);
        Reading r1 = new Reading(date1, 18);
        LocalDateTime date2 = LocalDateTime.of(2019, 1, 15, 12, 3);
        Reading r2 = new Reading(date2, 15);
        LocalDateTime date3 = LocalDateTime.of(2019, 1, 17, 12, 5);
        Reading r3 = new Reading(date3, 22);
        LocalDateTime date4 = LocalDateTime.of(2019, 1, 17, 12, 6);
        Reading r4 = new Reading(date4, 23);
        LocalDateTime date5 = LocalDateTime.of(2019, 1, 14, 12, 10);
        Reading r5 = new Reading(date5, 15);
        LocalDateTime date6 = LocalDateTime.of(2019, 1, 14, 12, 15);
        Reading r6 = new Reading(date6, 25);
        GetTheDayWithHighestTemperatureAmplitudeController getTheDayWithHighestTemperatureAmplitudeController = new GetTheDayWithHighestTemperatureAmplitudeController(houseDomainService, geographicAreaDomainService);
        //Act
        geographicArea.addSensorToList(s1);

        lRead1.addReading(r1);
        lRead1.addReading(r2);
        lRead1.addReading(r3);
        lRead1.addReading(r4);
        lRead1.addReading(r5);
        lRead1.addReading(r6);
        s1.setListOfReadings(lRead1);
        geographicAreaDomainService.add(geographicArea);
        double result = getTheDayWithHighestTemperatureAmplitudeController.getHighestTemperatureAmplitude(startDate, finalDate);
        double expectedResult = 10;
        //ASSERT
        assertEquals(expectedResult, result, 0.0001);
    }


    @Test
    void getHighestTemperatureAmplitudeWhenSensorIsNotOfTemperature() throws Exception {
        //Arrange
        GeographicArea geographicArea = new GeographicArea("Porto", "City", new OccupationArea
                (new Location(10, 20, 30), 0.5, 0.5),
                new GeographicAreaType("City"));
        House h1 = new House("Casa", new Location(40.7486, -73.9864, 0), geographicArea.getName());
        houseDomainService.add(h1);
        AreaSensor s1 = new AreaSensor("1","sensor Teste", new Location(10, 20, 30), new SensorType("rainfall"), LocalDate.of(2018, 12, 10), "l/m2");
        ListOfReadings lRead1 = new ListOfReadings();
        LocalDate startDate = LocalDate.of(2019, 01, 13);
        LocalDate finalDate = LocalDate.of(2019, 01, 18);
        LocalDateTime date1 = LocalDateTime.of(2019, 1, 15, 12, 00);
        Reading r1 = new Reading(date1, 18);
        LocalDateTime date2 = LocalDateTime.of(2019, 1, 15, 12, 3);
        Reading r2 = new Reading(date2, 15);
        LocalDateTime date3 = LocalDateTime.of(2019, 1, 17, 12, 5);
        Reading r3 = new Reading(date3, 22);
        LocalDateTime date4 = LocalDateTime.of(2019, 1, 17, 12, 6);
        Reading r4 = new Reading(date4, 23);
        LocalDateTime date5 = LocalDateTime.of(2019, 1, 14, 12, 10);
        Reading r5 = new Reading(date5, 15);
        LocalDateTime date6 = LocalDateTime.of(2019, 1, 14, 12, 15);
        Reading r6 = new Reading(date6, 25);
        GetTheDayWithHighestTemperatureAmplitudeController getTheDayWithHighestTemperatureAmplitudeController = new GetTheDayWithHighestTemperatureAmplitudeController(houseDomainService, geographicAreaDomainService);
        //Act
        geographicArea.addSensorToList(s1);
        lRead1.addReading(r1);
        lRead1.addReading(r2);
        lRead1.addReading(r3);
        lRead1.addReading(r4);
        lRead1.addReading(r5);
        lRead1.addReading(r6);
        s1.setListOfReadings(lRead1);
        geographicAreaDomainService.add(geographicArea);
        double expectedResult = -1;
        double result = getTheDayWithHighestTemperatureAmplitudeController.getHighestTemperatureAmplitude(startDate, finalDate);
        //ASSERT
        assertEquals(Double.NaN, result, 0.0001);
    }

    @Test
    void getHighestTemperatureAmplitudeNoGeoArea() throws Exception {
        //Arrange
        GeographicArea geographicArea = new GeographicArea("Porto", "City", new OccupationArea
                (new Location(10, 20, 30), 0.5, 0.5),
                new GeographicAreaType("City"));
        House h1 = new House("Casa", new Location(40.7486, -73.9864, 0), geographicArea.getName());
        houseDomainService.add(h1);
        AreaSensor s1 = new AreaSensor("1","sensor Teste", new Location(10, 20, 30), new SensorType("temperature"), LocalDate.of(2018, 12, 10), "l/m2");
        ListOfReadings lRead1 = new ListOfReadings();
        LocalDate startDate = LocalDate.of(2019, 01, 13);
        LocalDate finalDate = LocalDate.of(2019, 01, 18);
        LocalDateTime date1 = LocalDateTime.of(2019, 1, 15, 12, 00);
        Reading r1 = new Reading(date1, 18);
        LocalDateTime date2 = LocalDateTime.of(2019, 1, 15, 12, 3);
        Reading r2 = new Reading(date2, 15);
        LocalDateTime date3 = LocalDateTime.of(2019, 1, 17, 12, 5);
        Reading r3 = new Reading(date3, 22);
        LocalDateTime date4 = LocalDateTime.of(2019, 1, 17, 12, 6);
        Reading r4 = new Reading(date4, 23);
        LocalDateTime date5 = LocalDateTime.of(2019, 1, 14, 12, 10);
        Reading r5 = new Reading(date5, 15);
        LocalDateTime date6 = LocalDateTime.of(2019, 1, 14, 12, 15);
        Reading r6 = new Reading(date6, 25);
        GetTheDayWithHighestTemperatureAmplitudeController getTheDayWithHighestTemperatureAmplitudeController = new GetTheDayWithHighestTemperatureAmplitudeController(houseDomainService, geographicAreaDomainService);
        //Act
        geographicArea.addSensorToList(s1);

        lRead1.addReading(r1);
        lRead1.addReading(r2);
        lRead1.addReading(r3);
        lRead1.addReading(r4);
        lRead1.addReading(r5);
        lRead1.addReading(r6);
        s1.setListOfReadings(lRead1);
        //geographicAreaRepo.add(geographicArea);
        double result = getTheDayWithHighestTemperatureAmplitudeController.getHighestTemperatureAmplitude(startDate, finalDate);
        double expectedResult = Double.NaN;
        //ASSERT
        assertEquals(expectedResult, result, 0.0001);
    }

    @Test
    void checkIfGetGeographicAreaOfTheHouse(){
        GeographicArea geographicArea = new GeographicArea("Porto", "city", new OccupationArea(new Location(10, 20, 30), 0.5, 0.5), new GeographicAreaType("city"));
        House house = new House("house1", new Location(10, 6, 0), geographicArea.getName());

        houseDomainService.add(house);

        String result = ctrl.getGeographicAreaOfTheHouse();

        String expectedResult = geographicArea.getName();
        assertEquals(expectedResult,result);
    }

    @Test
    void checkIfHaveSensorsOnHouseAreaWithTypeAndReadings() throws Exception {
        getHighestTemperatureAmplitude();
        boolean result = ctrl.checkIfHaveSensorsOnHouseAreaWithTypeAndReadings();

        assertTrue(result);
    }


}

