package pt.ipp.isep.dei.project1.controllersweb.housemonitoring;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import pt.ipp.isep.dei.project1.model.geographicarea.GeographicArea;
import pt.ipp.isep.dei.project1.model.geographicarea.GeographicAreaType;
import pt.ipp.isep.dei.project1.model.geographicarea.Location;
import pt.ipp.isep.dei.project1.model.geographicarea.OccupationArea;
import pt.ipp.isep.dei.project1.model.house.Address;
import pt.ipp.isep.dei.project1.model.house.House;
import pt.ipp.isep.dei.project1.model.repositories.GeographicAreaDomainService;
import pt.ipp.isep.dei.project1.model.repositories.GeographicAreaRepository;
import pt.ipp.isep.dei.project1.model.repositories.HouseDomainService;
import pt.ipp.isep.dei.project1.model.repositories.HouseRepository;
import pt.ipp.isep.dei.project1.model.sensor.AreaSensor;
import pt.ipp.isep.dei.project1.model.sensor.ListOfReadings;
import pt.ipp.isep.dei.project1.model.sensor.Reading;
import pt.ipp.isep.dei.project1.model.sensor.SensorType;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;



@ExtendWith(MockitoExtension.class)
class GetTheFirstHottestDayInGivenPeriodRestControllerTest {

    @Mock
    private HouseDomainService houseDomainService;
    @Mock
    private HouseRepository houseRepository;
    @Mock
    private GeographicAreaDomainService geographicAreaDomainService;
    @Mock
    private GeographicAreaRepository geographicAreaRepository;

    private GetTheFirstHottestDayInGivenPeriodRestController ctrl;

    @BeforeEach
    void initUseCase() {
        houseDomainService = new HouseDomainService(houseRepository);
        geographicAreaDomainService = new GeographicAreaDomainService(geographicAreaRepository);
        ctrl = new GetTheFirstHottestDayInGivenPeriodRestController(houseDomainService, geographicAreaDomainService);
    }


    @Test
    void getFirstHottestDayInGivenPeriodDtoTest(){
        //Arrange
        GeographicArea geographicArea = new GeographicArea("Porto", "City", new OccupationArea
                (new Location(10, 20, 30), 0.5, 0.5),
                new GeographicAreaType("City"));
        House h1 = new House("Casa", new Location(40.7486, -73.9864, 0), geographicArea.getName());
        houseDomainService.add(h1);
        AreaSensor s1 = new AreaSensor("1","sensor Test", new Location(10, 20, 30), new SensorType("temperature"), LocalDate.of(2018, 12, 10), "l/m2");
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
        //ACT
        geographicArea.addSensorToList(s1);
        lRead1.addReading(r1);
        lRead1.addReading(r2);
        lRead1.addReading(r3);
        lRead1.addReading(r4);
        lRead1.addReading(r5);
        lRead1.addReading(r6);
        s1.setListOfReadings(lRead1);
        geographicAreaDomainService.add(geographicArea);
        ResponseEntity<Object> result = ctrl.getFirstHottestDayInGivenPeriodDto(startDate, finalDate);
        //ASSERT
        assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    @Test
    void getFirstHottestDayInGivenPeriodNoContent(){
        //Arrange
        GeographicArea geographicArea = new GeographicArea("Porto", "City", new OccupationArea
                (new Location(10, 20, 30), 0.5, 0.5),
                new GeographicAreaType("City"));
        House h1 = new House("Casa", new Location(40.7486, -73.9864, 0), geographicArea.getName());
        houseDomainService.add(h1);
        AreaSensor s1 = new AreaSensor("1","sensor Test", new Location(10, 20, 30), new SensorType("temperature"), LocalDate.of(2018, 12, 10), "l/m2");
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
        //ACT
        geographicArea.addSensorToList(s1);
        lRead1.addReading(r1);
        lRead1.addReading(r2);
        lRead1.addReading(r3);
        lRead1.addReading(r4);
        lRead1.addReading(r5);
        lRead1.addReading(r6);
        s1.setListOfReadings(lRead1);
        geographicAreaDomainService.add(geographicArea);
        ResponseEntity<Object> result = ctrl.getFirstHottestDayInGivenPeriodDto(startDate, finalDate);
        //ASSERT
        assertEquals(HttpStatus.OK, result.getStatusCode());
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
        ResponseEntity<Object> result  = ctrl.getFirstHottestDayInGivenPeriodDto(startDate, finalDate);
        //ASSERT
        assertEquals(HttpStatus.NOT_ACCEPTABLE, result.getStatusCode());
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
        //ACT
        geographicArea.addSensorToList(s1);
        lRead1.addReading(r1);
        lRead1.addReading(r2);
        lRead1.addReading(r3);
        lRead1.addReading(r4);
        lRead1.addReading(r5);
        lRead1.addReading(r6);
        s1.setListOfReadings(lRead1);
        ResponseEntity<Object> result  = ctrl.getFirstHottestDayInGivenPeriodDto(startDate, finalDate);
        //ASSERT
        assertEquals(HttpStatus.NOT_ACCEPTABLE, result.getStatusCode());
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
        ResponseEntity<Object> result  = ctrl.getFirstHottestDayInGivenPeriodDto(startDate, finalDate);
        double expectedResult = 10;
        //ASSERT
        assertEquals(HttpStatus.OK, result.getStatusCode());
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
        ResponseEntity<Object> result  = ctrl.getFirstHottestDayInGivenPeriodDto(startDate, finalDate);
        //ASSERT
        assertEquals(HttpStatus.NOT_ACCEPTABLE, result.getStatusCode());
    }

    @Test
    void getHighestTemperatureAmplitudeNoGeoArea() throws Exception {
        //Arrange
        GeographicArea geographicArea = new GeographicArea("Porto", "City", new OccupationArea
                (new Location(10, 20, 30), 0.5, 0.5),
                new GeographicAreaType("City"));
        House h1 = new House("Casa", new Location(40.7486, -73.9864, 0), null);
        houseDomainService.add(h1);
        AreaSensor s1 = new AreaSensor("1","sensor Teste", new Location(10, 20, 30), new SensorType("temperature"), LocalDate.of(2018, 12, 10), "l/m2");
        ListOfReadings lRead1 = new ListOfReadings();
        LocalDate startDate = LocalDate.of(2019, 01, 20);
        LocalDate finalDate = LocalDate.of(2019, 01, 22);
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
        ResponseEntity<Object> result  = ctrl.getFirstHottestDayInGivenPeriodDto(startDate, finalDate);
        double expectedResult = Double.NaN;
        //ASSERT
        assertEquals(HttpStatus.NOT_ACCEPTABLE, result.getStatusCode());
    }



    @Test
    void initLocalDate(){
        LocalDate result = ctrl.initLocalDate();
        LocalDate expectedResult = LocalDate.now();
        assertEquals(expectedResult.getYear(),result.getYear());
    }

    @Test
    void testGetLastColdestDayInGivenPeriodNoSensors() throws Exception {
        //Arrange
        GeographicArea geographicArea = new GeographicArea("Porto", "City", new OccupationArea
                (new Location(10, 20, 30), 0.5, 0.5),
                new GeographicAreaType("City"));
        Location l1 = new Location(40.7486, -73.9864, 0);
        House house = new House("aaaa", l1, "Porto", new Address("1", "1", "1", "123", "Portugal"));        AreaSensor s1 = new AreaSensor("1","sensor Teste",new Location(10, 20, 30),new SensorType("temperature"), LocalDate.of(2018,12,10),"l/m2");
        LocalDateTime date1 = LocalDateTime.of(2019, 1, 16, 12, 00);

        LocalDateTime date6 = LocalDateTime.of(2019, 1, 22, 12, 10);
        houseDomainService.add(house);

        ResponseEntity result = ctrl.getFirstHottestDayInGivenPeriodDto(date1.toLocalDate(),date6.toLocalDate());
        //Assert
        assertEquals(HttpStatus.NOT_ACCEPTABLE,result.getStatusCode());
    }

    @Test
    void testGetLastColdestDayInGivenPeriodNoGA() throws Exception {
        //Arrange
        House house = new House();
        LocalDateTime date1 = LocalDateTime.of(2019, 1, 16, 12, 00);

        LocalDateTime date6 = LocalDateTime.of(2019, 1, 22, 12, 10);
        houseDomainService.add(house);

        ResponseEntity result = ctrl.getFirstHottestDayInGivenPeriodDto(date1.toLocalDate(),date6.toLocalDate());
        //Assert
        assertEquals(HttpStatus.NOT_ACCEPTABLE,result.getStatusCode());
    }


}