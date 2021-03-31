package pt.ipp.isep.dei.project1.controllersweb.housemonitoring;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import pt.ipp.isep.dei.project1.controllers.house.GetTotalAndAverageRainfallInHouseAreaForAGivenDayController;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
public class RainfallInHouseAreaRestCtrlTest {

    @Mock
    private HouseDomainService houseDomainService;
    @Mock
    private HouseRepository houseRepository;
    @Mock
    private GeographicAreaDomainService geographicAreaDomainService;
    @Mock
    private GeographicAreaRepository geographicAreaRepository;

    private RainfallInHouseAreaRestCtrl ctrl;


    @BeforeEach
    void initUseCase() {
        houseDomainService = new HouseDomainService(houseRepository);
        geographicAreaDomainService = new GeographicAreaDomainService(geographicAreaRepository);
        ctrl = new RainfallInHouseAreaRestCtrl(houseDomainService, geographicAreaDomainService);
    }




    @Test
    void getAverageRainfall() {
        GeographicArea geographicArea = new GeographicArea("Porto", "City", new OccupationArea
                (new Location(10, 20, 30), 0.5, 0.5),
                new GeographicAreaType("City"));
        House house = new House("houseName", new Location(40.7486, -73.9864, 0), geographicArea.getName());
        AreaSensor s1 = new AreaSensor("1", "s1", new Location(40.7486, -73.9864, 0), new SensorType("rainfall"), LocalDate.of(2018, 12, 10), "ºC");
        LocalDateTime date1 = LocalDateTime.of(2019, 01, 02, 12, 30);
        LocalDateTime date2 = LocalDateTime.of(2019, 01, 03, 12, 31);
        ListOfReadings lisOfReading = new ListOfReadings();
        lisOfReading.addReading(new Reading(date1, 1.1));
        lisOfReading.addReading(new Reading(date2, 13.124531));
        s1.setListOfReadings(lisOfReading);
        geographicArea.addSensorToList(s1);

        geographicAreaDomainService.add(geographicArea);
        houseDomainService.add(house);

        ResponseEntity result = ctrl.avgOfRainfallInTimeInterval(date1.toLocalDate(),date1.toLocalDate());
        assertEquals(1.1 + " mm", result.getBody());
    }


    @Test
    void getAverageRainfall2Sensors() {
        GeographicArea geographicArea = new GeographicArea("Porto", "City", new OccupationArea
                (new Location(10, 20, 30), 0.5, 0.5),
                new GeographicAreaType("City"));
        House house = new House("houseName", new Location(40.7486, -73.9864, 0), geographicArea.getName());
        AreaSensor s1 = new AreaSensor("1", "s1", new Location(40.7486, -73.9864, 0), new SensorType("Rainfall"), LocalDate.of(2018, 12, 10), "ºC");
        AreaSensor s2 = new AreaSensor("1", "s2", new Location(40.7486, -73.9864, 0), new SensorType("Rainfall"), LocalDate.of(2018, 12, 10), "ºC");
        LocalDateTime date1 = LocalDateTime.of(2019, 01, 02, 12, 30);
        LocalDateTime date2 = LocalDateTime.of(2019, 01, 03, 12, 31);
        ListOfReadings lisOfReading = new ListOfReadings();
        lisOfReading.addReading(new Reading(date1, 12.0));
        lisOfReading.addReading(new Reading(date2, 13.0));
        s1.setListOfReadings(lisOfReading);
        s2.setListOfReadings(lisOfReading);
        geographicArea.addSensorToList(s1);
        geographicArea.addSensorToList(s2);
        GetTotalAndAverageRainfallInHouseAreaForAGivenDayController us623 = new GetTotalAndAverageRainfallInHouseAreaForAGivenDayController(houseDomainService, geographicAreaDomainService);
        us623.getGeographicAreaOfTheHouse();
        geographicAreaDomainService.add(geographicArea);
        houseDomainService.add(house);

        ResponseEntity result = ctrl.avgOfRainfallInTimeInterval(date1.toLocalDate(), date2.toLocalDate());

        assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    @Test
    void getAverageRainfall2SensorsDifferentDate() {
        GeographicArea geographicArea = new GeographicArea("Porto", "City", new OccupationArea
                (new Location(10, 20, 30), 0.5, 0.5),
                new GeographicAreaType("City"));
        House house = new House("houseName", new Location(40.7486, -73.9864, 0), geographicArea.getName());
        AreaSensor s1 = new AreaSensor("1", "s1", new Location(40.7486, -73.9864, 0), new SensorType("Rainfall"), LocalDate.of(2018, 12, 10), "ºC");
        AreaSensor s2 = new AreaSensor("2", "s2", new Location(40.7486, -73.9864, 0), new SensorType("Rainfall"), LocalDate.of(2018, 12, 10), "ºC");
        LocalDateTime date1 = LocalDateTime.of(2019, 01, 02, 12, 30);
        LocalDateTime date2 = LocalDateTime.of(2019, 01, 03, 12, 31);
        ListOfReadings lisOfReadingS1 = new ListOfReadings();
        lisOfReadingS1.addReading(new Reading(date1, 13.0));
        lisOfReadingS1.addReading(new Reading(date2, 20.0));
        s1.setListOfReadings(lisOfReadingS1);
        ListOfReadings lisOfReadingS2 = new ListOfReadings();
        lisOfReadingS2.addReading(new Reading(date2, 20.0));
        s2.setListOfReadings(lisOfReadingS2);
        geographicArea.addSensorToList(s1);
        geographicArea.addSensorToList(s2);

        GetTotalAndAverageRainfallInHouseAreaForAGivenDayController us623 = new GetTotalAndAverageRainfallInHouseAreaForAGivenDayController(houseDomainService, geographicAreaDomainService);
        us623.getGeographicAreaOfTheHouse();
        geographicAreaDomainService.add(geographicArea);
        houseDomainService.add(house);
        ResponseEntity result = ctrl.avgOfRainfallInTimeInterval(date1.toLocalDate(),date2.toLocalDate());

        assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    @Test
    void getAverageRainfall2SensorsDifferentDateDifferentReadings() {
        GeographicArea geographicArea = new GeographicArea("Porto", "City", new OccupationArea
                (new Location(10, 20, 30), 0.5, 0.5),
                new GeographicAreaType("City"));
        House house = new House("houseName", new Location(40.7486, -73.9864, 0), geographicArea.getName());
        AreaSensor s1 = new AreaSensor("1", "s1", new Location(40.7486, -73.9864, 0), new SensorType("Rainfall"), LocalDate.of(2018, 12, 10), "ºC");
        AreaSensor s2 = new AreaSensor("2", "s2", new Location(40.7486, -73.9864, 0), new SensorType("Rainfall"), LocalDate.of(2018, 12, 10), "ºC");
        LocalDateTime date1 = LocalDateTime.of(2019, 01, 02, 12, 30);
        LocalDateTime date2 = LocalDateTime.of(2019, 01, 03, 12, 31);
        ListOfReadings lisOfReading = new ListOfReadings();
        ListOfReadings lisOfReading2 = new ListOfReadings();
        lisOfReading.addReading(new Reading(date1, 12.0));
        lisOfReading.addReading(new Reading(date2, 13.0));
        lisOfReading2.addReading(new Reading(date2, 20.0));
        s1.setListOfReadings(lisOfReading);
        s2.setListOfReadings(lisOfReading2);
        geographicArea.addSensorToList(s1);
        geographicArea.addSensorToList(s2);
        GetTotalAndAverageRainfallInHouseAreaForAGivenDayController us623 = new GetTotalAndAverageRainfallInHouseAreaForAGivenDayController(houseDomainService, geographicAreaDomainService);
        us623.getGeographicAreaOfTheHouse();
        geographicAreaDomainService.add(geographicArea);
        houseDomainService.add(house);
        ResponseEntity result = ctrl.avgOfRainfallInTimeInterval(date1.toLocalDate(),date2.toLocalDate());

        assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    @Test
    void getAverageRainfall2SensorsDifferentDateDifferentReadingsDateInverted() {
        GeographicArea geographicArea = new GeographicArea("Porto", "City", new OccupationArea
                (new Location(10, 20, 30), 0.5, 0.5),
                new GeographicAreaType("City"));
        House house = new House("houseName", new Location(40.7486, -73.9864, 0), geographicArea.getName());
        AreaSensor s1 = new AreaSensor("1", "s1", new Location(40.7486, -73.9864, 0), new SensorType("Rainfall"), LocalDate.of(2018, 12, 10), "ºC");
        AreaSensor s2 = new AreaSensor("2", "s2", new Location(40.7486, -73.9864, 0), new SensorType("Rainfall"), LocalDate.of(2018, 12, 10), "ºC");
        LocalDateTime date1 = LocalDateTime.of(2019, 01, 02, 12, 30);
        LocalDateTime date2 = LocalDateTime.of(2019, 01, 03, 12, 31);
        ListOfReadings lisOfReading = new ListOfReadings();
        ListOfReadings lisOfReading2 = new ListOfReadings();
        lisOfReading.addReading(new Reading(date1, 12.0));
        lisOfReading.addReading(new Reading(date2, 13.0));
        lisOfReading2.addReading(new Reading(date2, 20.0));
        s1.setListOfReadings(lisOfReading);
        s2.setListOfReadings(lisOfReading2);
        geographicArea.addSensorToList(s1);
        geographicArea.addSensorToList(s2);
        GetTotalAndAverageRainfallInHouseAreaForAGivenDayController us623 = new GetTotalAndAverageRainfallInHouseAreaForAGivenDayController(houseDomainService, geographicAreaDomainService);
        us623.getGeographicAreaOfTheHouse();
        geographicAreaDomainService.add(geographicArea);
        houseDomainService.add(house);


        ResponseEntity result = ctrl.avgOfRainfallInTimeInterval(date1.toLocalDate(),date2.toLocalDate());

        assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    @Test
    void getAverageRainfall2SensorsNoAg() {
        GeographicArea geographicArea = new GeographicArea("Porto", "City", new OccupationArea
                (new Location(10, 20, 30), 0.5, 0.5),
                new GeographicAreaType("City"));
        House house = new House("houseName", new Location(40.7486, -73.9864, 0), geographicArea.getName());
        AreaSensor s1 = new AreaSensor("1", "s1", new Location(40.7486, -73.9864, 0), new SensorType("Rainfall"), LocalDate.of(2018, 12, 10), "ºC");
        AreaSensor s2 = new AreaSensor("2", "s2", new Location(40.7486, -73.9864, 0), new SensorType("Rainfall"), LocalDate.of(2018, 12, 10), "ºC");
        LocalDateTime date1 = LocalDateTime.of(2019, 01, 02, 12, 30);
        LocalDateTime date2 = LocalDateTime.of(2019, 01, 03, 12, 31);
        ListOfReadings lisOfReading = new ListOfReadings();
        ListOfReadings lisOfReading2 = new ListOfReadings();
        lisOfReading.addReading(new Reading(date1, 12.0));
        lisOfReading.addReading(new Reading(date2, 13.0));
        lisOfReading2.addReading(new Reading(date2, 20.0));
        s1.setListOfReadings(lisOfReading);
        s2.setListOfReadings(lisOfReading2);
        geographicArea.addSensorToList(s1);
        geographicArea.addSensorToList(s2);
        GetTotalAndAverageRainfallInHouseAreaForAGivenDayController us623 = new GetTotalAndAverageRainfallInHouseAreaForAGivenDayController(houseDomainService, geographicAreaDomainService);
        us623.getGeographicAreaOfTheHouse();
        //   geographicAreaRepo.add(geographicArea);
        houseDomainService.add(house);

        ResponseEntity result = ctrl.avgOfRainfallInTimeInterval(date1.toLocalDate(),date2.toLocalDate());

        assertEquals(HttpStatus.NOT_ACCEPTABLE, result.getStatusCode());
    }


    @Test
    void getAverageRainfall2SensorsDifferentDateDifferentType() {
        GeographicArea geographicArea = new GeographicArea("Porto", "city", new OccupationArea(new Location(10, 20, 30), 0.5, 0.5), new GeographicAreaType("city"));
        House house = new House("houseName", new Location(40.7486, -73.9864, 0), geographicArea.getName());
        AreaSensor s1 = new AreaSensor("1", "s1", new Location(40.7486, -73.9864, 0), new SensorType("Temperature"), LocalDate.of(2018, 12, 10), "ºC");
        AreaSensor s2 = new AreaSensor("1", "s2", new Location(40.7486, -73.9864, 0), new SensorType("Temperature"), LocalDate.of(2018, 12, 10), "ºC");
        LocalDateTime date1 = LocalDateTime.of(2019, 01, 02, 12, 30);
        LocalDateTime date2 = LocalDateTime.of(2019, 01, 03, 12, 31);
        ListOfReadings lisOfReading = new ListOfReadings();
        ListOfReadings lisOfReading2 = new ListOfReadings();
        lisOfReading.addReading(new Reading(date1, 12.0));
        lisOfReading.addReading(new Reading(date2, 13.0));
        lisOfReading2.addReading(new Reading(date2, 20.0));
        geographicArea.addSensorToList(s1);
        geographicArea.addSensorToList(s2);
        GetTotalAndAverageRainfallInHouseAreaForAGivenDayController us623 = new GetTotalAndAverageRainfallInHouseAreaForAGivenDayController(houseDomainService, geographicAreaDomainService);
        us623.getGeographicAreaOfTheHouse();
        geographicAreaDomainService.add(geographicArea);
        houseDomainService.add(house);


        ResponseEntity result = ctrl.avgOfRainfallInTimeInterval(date1.toLocalDate(),date2.toLocalDate());

        assertEquals(HttpStatus.NOT_ACCEPTABLE, result.getStatusCode());
    }

    @Test
    void getAverageRainfall2SensorsDifferentDateDifferentInstallationDate() {
        GeographicArea geographicArea = new GeographicArea("Porto", "city", new OccupationArea(new Location(10, 20, 30), 0.5, 0.5), new GeographicAreaType("city"));
        House house = new House("houseName", new Location(40.7486, -73.9864, 0), geographicArea.getName());
        AreaSensor s1 = new AreaSensor("1", "s1", new Location(40.7486, -73.9864, 0), new SensorType("Temperature"), LocalDate.of(2019, 01, 05), "ºC");
        AreaSensor s2 = new AreaSensor("1", "s2", new Location(40.7486, -73.9864, 0), new SensorType("Temperature"), LocalDate.of(2018, 01, 05), "ºC");
        LocalDateTime date1 = LocalDateTime.of(2019, 01, 02, 12, 30);
        LocalDateTime date2 = LocalDateTime.of(2019, 01, 03, 12, 31);
        ListOfReadings lisOfReading = new ListOfReadings();
        ListOfReadings lisOfReading2 = new ListOfReadings();
        lisOfReading.addReading(new Reading(date1, 12.0));
        lisOfReading.addReading(new Reading(date2, 13.0));
        lisOfReading2.addReading(new Reading(date2, 20.0));
        geographicArea.addSensorToList(s1);
        geographicArea.addSensorToList(s2);
        GetTotalAndAverageRainfallInHouseAreaForAGivenDayController us623 = new GetTotalAndAverageRainfallInHouseAreaForAGivenDayController(houseDomainService, geographicAreaDomainService);
        geographicAreaDomainService.add(geographicArea);
        houseDomainService.add(house);
        ResponseEntity result = ctrl.avgOfRainfallInTimeInterval(date1.toLocalDate(),date2.toLocalDate());

        assertEquals(HttpStatus.NOT_ACCEPTABLE, result.getStatusCode());
    }

    @Test
    void getGeographicAreaOfTheHouseFalse() {
        GeographicArea geographicArea = new GeographicArea("Porto", "city", new OccupationArea(new Location(10, 20, 30), 0.5, 0.5), new GeographicAreaType("city"));
        House house = new House("houseName", new Location(40.7486, -73.9864, 0), geographicArea.getName());

        geographicAreaDomainService.add(geographicArea);
        houseDomainService.add(house);
        GetTotalAndAverageRainfallInHouseAreaForAGivenDayController us623 = new GetTotalAndAverageRainfallInHouseAreaForAGivenDayController(houseDomainService, geographicAreaDomainService);
        String result = us623.getGeographicAreaOfTheHouse();

        assertEquals("Porto", result);
    }


    @Test
    void testGetLastColdestDayInGivenPeriodNoSensors() throws Exception {
        //Arrange
        GeographicArea geographicArea = new GeographicArea("Porto", "City", new OccupationArea
                (new Location(10, 20, 30), 0.5, 0.5),
                new GeographicAreaType("City"));
        Location l1 = new Location(40.7486, -73.9864, 0);
        House house = new House("aaaa", l1, "Porto", new Address("1", "1", "1", "123", "Portugal"));
        LocalDateTime date1 = LocalDateTime.of(2019, 1, 16, 12, 00);

        houseDomainService.add(house);

        ResponseEntity result = ctrl.sumOfRainfallInDay(date1.toLocalDate());
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

        ResponseEntity result = ctrl.sumOfRainfallInDay(date6.toLocalDate());
        //Assert
        assertEquals(HttpStatus.NOT_ACCEPTABLE,result.getStatusCode());
    }

    @Test
    void testSumOfRainfallInADayHappy() throws Exception {
        //ARRANGE
        Location l1 = new Location(40.7486, -73.9864, 0);
        House house = new House("aaaa", l1, "Porto", new Address("1", "1", "1", "123", "Portugal"));
        GeographicArea geographicArea = new GeographicArea("Porto", "city", new OccupationArea(new Location(10, 20, 30), 0.5, 0.5), new GeographicAreaType("city"));
        AreaSensor s1 = new AreaSensor("1", "s1", new Location(40.7486, -73.9864, 0), new SensorType("rainfall"), LocalDate.of(2019, 01, 05), "ºC");
        LocalDateTime date1 = LocalDateTime.of(2019, 01, 02, 12, 30);
        LocalDate localDate = LocalDate.of(2019, 01, 03);
        ListOfReadings lisOfReading = new ListOfReadings();
        lisOfReading.addReading(new Reading(date1, 12.0));
        lisOfReading.addReading(new Reading(date1, 13.0));
        s1.setListOfReadings(lisOfReading);
        geographicArea.addSensorToList(s1);
        geographicAreaDomainService.add(geographicArea);
        houseDomainService.add(house);
        ResponseEntity result = ctrl.sumOfRainfallInDay(localDate);
        //Assert
        assertEquals("It did not rain on this day",result.getBody());
    }

    @Test
    void testSumOfRainfallInADayHappy2nd() throws Exception {
        //ARRANGE
        Location l1 = new Location(40.7486, -73.9864, 0);
        House house = new House("aaaa", l1, "Porto", new Address("1", "1", "1", "123", "Portugal"));
        GeographicArea geographicArea = new GeographicArea("Porto", "city", new OccupationArea(new Location(10, 20, 30), 0.5, 0.5), new GeographicAreaType("city"));
        AreaSensor s1 = new AreaSensor("1", "s1", new Location(40.7486, -73.9864, 0), new SensorType("rainfall"), LocalDate.of(2019, 01, 05), "ºC");
        LocalDateTime date1 = LocalDateTime.of(2019, 01, 02, 12, 30);
        LocalDate localDate = LocalDate.of(2019, 01, 02);
        ListOfReadings lisOfReading = new ListOfReadings();
        lisOfReading.addReading(new Reading(date1, 12.0));
        lisOfReading.addReading(new Reading(date1, 13.0));
        s1.setListOfReadings(lisOfReading);
        geographicArea.addSensorToList(s1);
        geographicAreaDomainService.add(geographicArea);
        houseDomainService.add(house);
        ResponseEntity result = ctrl.sumOfRainfallInDay(localDate);
        //Assert
        assertEquals(25.0+" mm",result.getBody());
    }

    @Test
    void testSumOfRainfallInADayHappy3rd() throws Exception {
        //ARRANGE
        Location l1 = new Location(40.7486, -73.9864, 0);
        House house = new House("aaaa", l1, "Porto", new Address("1", "1", "1", "123", "Portugal"));
        GeographicArea geographicArea = new GeographicArea("Porto", "city", new OccupationArea(new Location(10, 20, 30), 0.5, 0.5), new GeographicAreaType("city"));
        AreaSensor s1 = new AreaSensor("1", "s1", new Location(40.7486, -73.9864, 0), new SensorType("rainfall"), LocalDate.of(2019, 01, 05), "ºC");
        LocalDateTime date1 = LocalDateTime.of(2019, 01, 02, 12, 30);
        LocalDate localDate = LocalDate.of(2019, 01, 02);
        ListOfReadings lisOfReading = new ListOfReadings();
        lisOfReading.addReading(new Reading(date1, 12.0));
        lisOfReading.addReading(new Reading(date1, 13.0));
        s1.setListOfReadings(lisOfReading);
        geographicArea.addSensorToList(s1);
        geographicAreaDomainService.add(geographicArea);
        houseDomainService.add(house);
        ResponseEntity result = ctrl.sumOfRainfallInDay(localDate);
        //Assert
        assertEquals(HttpStatus.OK,result.getStatusCode());
    }



    @Test
    void NoGeoAreaDefined() {
        GeographicArea geographicArea = new GeographicArea("Porto", "City", new OccupationArea
                (new Location(10, 20, 30), 0.5, 0.5),
                new GeographicAreaType("City"));
        House house = new House("houseName", new Location(40.7486, -73.9864, 0), null);
        AreaSensor s1 = new AreaSensor("1", "s1", new Location(40.7486, -73.9864, 0), new SensorType("Rainfall"), LocalDate.of(2018, 12, 10), "ºC");
        AreaSensor s2 = new AreaSensor("1", "s2", new Location(40.7486, -73.9864, 0), new SensorType("Rainfall"), LocalDate.of(2018, 12, 10), "ºC");
        LocalDateTime date1 = LocalDateTime.of(2019, 01, 02, 12, 30);
        LocalDateTime date2 = LocalDateTime.of(2019, 01, 03, 12, 31);
        ListOfReadings lisOfReading = new ListOfReadings();
        lisOfReading.addReading(new Reading(date1, 12.0));
        lisOfReading.addReading(new Reading(date2, 13.0));
        s1.setListOfReadings(lisOfReading);
        s2.setListOfReadings(lisOfReading);
        geographicArea.addSensorToList(s1);
        geographicArea.addSensorToList(s2);
        GetTotalAndAverageRainfallInHouseAreaForAGivenDayController us623 = new GetTotalAndAverageRainfallInHouseAreaForAGivenDayController(houseDomainService, geographicAreaDomainService);
        us623.getGeographicAreaOfTheHouse();
      //  geographicAreaDomainService.add(geographicArea);
        houseDomainService.add(house);

        ResponseEntity result = ctrl.avgOfRainfallInTimeInterval(date1.toLocalDate(), date2.toLocalDate());

        assertEquals("No Geographic Area defined!", result.getBody());
    }

}