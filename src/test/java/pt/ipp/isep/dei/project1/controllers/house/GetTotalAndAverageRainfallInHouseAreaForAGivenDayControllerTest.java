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


import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)

class GetTotalAndAverageRainfallInHouseAreaForAGivenDayControllerTest {

    @Mock
    private HouseDomainService houseDomainService;
    @Mock
    private HouseRepository houseRepository;
    @Mock
    private GeographicAreaDomainService geographicAreaDomainService;
    @Mock
    private GeographicAreaRepository geographicAreaRepository;

    private GetTotalAndAverageRainfallInHouseAreaForAGivenDayController ctrl;


    @BeforeEach
    void initUseCase() {
        houseDomainService = new HouseDomainService(houseRepository);
        geographicAreaDomainService = new GeographicAreaDomainService(geographicAreaRepository);
        ctrl = new GetTotalAndAverageRainfallInHouseAreaForAGivenDayController(houseDomainService, geographicAreaDomainService);
    }


    @Test
    void getSumOfRainfallInDay(){
        GeographicArea geographicArea = new GeographicArea("Porto", "City", new OccupationArea
                (new Location(10, 20, 30), 0.5, 0.5),
                new GeographicAreaType("City"));
        House house = new House("houseName", new Location(40.7486, -73.9864, 0), geographicArea.getName());
        AreaSensor s1 = new AreaSensor("12","Sensor1", new Location(40.7486, -73.9864, 0), new SensorType("rainfall"), LocalDate.of(2018, 01, 01), "ºC");
        LocalDateTime date1 = LocalDateTime.of(2019, 01, 02, 12, 30);
        ListOfReadings lisOfReading = new ListOfReadings();
        lisOfReading.addReading(new Reading(date1, 12d));
        lisOfReading.addReading(new Reading(date1, 12d));
        s1.setListOfReadings(lisOfReading);
        geographicArea.addSensorToList(s1);

        GetTotalAndAverageRainfallInHouseAreaForAGivenDayController us620 = new GetTotalAndAverageRainfallInHouseAreaForAGivenDayController(houseDomainService, geographicAreaDomainService);
        us620.getGeographicAreaOfTheHouse();
        geographicAreaDomainService.add(geographicArea);
        houseDomainService.add(house);
        double result = us620.sumOfRainfallInDay(LocalDate.of(2019, 1, 2));

        assertEquals(24, result, 0.00001);
    }


    @Test
    void getAverageRainfall(){
        GeographicArea geographicArea = new GeographicArea("Porto", "City", new OccupationArea
                (new Location(10, 20, 30), 0.5, 0.5),
                new GeographicAreaType("City"));
        House house = new House("houseName", new Location(40.7486, -73.9864, 0), geographicArea.getName());
        AreaSensor s1 = new AreaSensor("1","s1", new Location(40.7486, -73.9864, 0), new SensorType("rainfall"), LocalDate.of(2018, 12, 10), "ºC");
        LocalDateTime date1 = LocalDateTime.of(2019, 01, 02, 12, 30);
        LocalDateTime date2 = LocalDateTime.of(2019, 01, 03, 12, 31);
        ListOfReadings lisOfReading = new ListOfReadings();
        lisOfReading.addReading(new Reading(date1, 12.0));
        lisOfReading.addReading(new Reading(date2, 13.0));
        s1.setListOfReadings(lisOfReading);
        geographicArea.addSensorToList(s1);

        GetTotalAndAverageRainfallInHouseAreaForAGivenDayController us623 = new GetTotalAndAverageRainfallInHouseAreaForAGivenDayController(houseDomainService, geographicAreaDomainService);
        us623.getGeographicAreaOfTheHouse();
        geographicAreaDomainService.add(geographicArea);
        houseDomainService.add(house);
        double result = us623.getAverageRainfall(LocalDate.of(2019, 1, 2), LocalDate.of(2019, 1, 3));

        assertEquals(12.5, result, 0.00001);
    }


    @Test
    void getAverageRainfall2Sensors() {
        GeographicArea geographicArea = new GeographicArea("Porto", "City", new OccupationArea
                (new Location(10, 20, 30), 0.5, 0.5),
                new GeographicAreaType("City"));
        House house = new House("houseName", new Location(40.7486, -73.9864, 0), geographicArea.getName());
        AreaSensor s1 = new AreaSensor("1","s1", new Location(40.7486, -73.9864, 0), new SensorType("rainfall"), LocalDate.of(2018, 12, 10), "ºC");
        AreaSensor s2 = new AreaSensor("1","s2", new Location(40.7486, -73.9864, 0), new SensorType("rainfall"), LocalDate.of(2018, 12, 10), "ºC");
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
        double result = us623.getAverageRainfall(LocalDate.of(2019, 1, 2), LocalDate.of(2019, 1, 3));

        assertEquals(12.5, result, 0.00001);
    }


    @Test
    void getAverageRainfall2SensorsDifferentDate(){
        GeographicArea geographicArea = new GeographicArea("Porto", "City", new OccupationArea
                (new Location(10, 20, 30), 0.5, 0.5),
                new GeographicAreaType("City"));
        House house = new House("houseName", new Location(40.7486, -73.9864, 0), geographicArea.getName());
        AreaSensor s1 = new AreaSensor("1","s1", new Location(40.7486, -73.9864, 0), new SensorType("rainfall"), LocalDate.of(2018, 12, 10), "ºC");
        AreaSensor s2 = new AreaSensor("2","s2", new Location(40.7486, -73.9864, 0), new SensorType("rainfall"), LocalDate.of(2018, 12, 10), "ºC");
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
        double result = us623.getAverageRainfall(LocalDate.of(2019, 1, 2), LocalDate.of(2019, 1, 3));

        assertEquals(26.5, result, 0.00001);
    }

    @Test
    void getAverageRainfall2SensorsDifferentDateDifferentReadings(){
        GeographicArea geographicArea = new GeographicArea("Porto", "City", new OccupationArea
                (new Location(10, 20, 30), 0.5, 0.5),
                new GeographicAreaType("City"));
        House house = new House("houseName", new Location(40.7486, -73.9864, 0), geographicArea.getName());
        AreaSensor s1 = new AreaSensor("1","s1", new Location(40.7486, -73.9864, 0), new SensorType("rainfall"), LocalDate.of(2018, 12, 10), "ºC");
        AreaSensor s2 = new AreaSensor("2","s2", new Location(40.7486, -73.9864, 0), new SensorType("rainfall"), LocalDate.of(2018, 12, 10), "ºC");
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
        double result = us623.getAverageRainfall(LocalDate.of(2019, 1, 2), LocalDate.of(2019, 1, 3));

        assertEquals(22.50, result, 0.00001);
    }

    @Test
    void getAverageRainfall2SensorsDifferentDateDifferentReadingsDateInverted(){
        GeographicArea geographicArea = new GeographicArea("Porto", "City", new OccupationArea
                (new Location(10, 20, 30), 0.5, 0.5),
                new GeographicAreaType("City"));
        House house = new House("houseName", new Location(40.7486, -73.9864, 0), geographicArea.getName());
        AreaSensor s1 = new AreaSensor("1","s1", new Location(40.7486, -73.9864, 0), new SensorType("rainfall"), LocalDate.of(2018, 12, 10), "ºC");
        LocalDateTime date1 = LocalDateTime.of(2019, 01, 02, 12, 30);
        LocalDateTime date2 = LocalDateTime.of(2019, 01, 03, 12, 31);
        ListOfReadings lisOfReading = new ListOfReadings();
        lisOfReading.addReading(new Reading(date1, 12.0));
        lisOfReading.addReading(new Reading(date2, 13.0));
        lisOfReading.addReading(new Reading(date2, 20.0));
        s1.setListOfReadings(lisOfReading);
        geographicArea.addSensorToList(s1);
        GetTotalAndAverageRainfallInHouseAreaForAGivenDayController us623 = new GetTotalAndAverageRainfallInHouseAreaForAGivenDayController(houseDomainService, geographicAreaDomainService);
        us623.getGeographicAreaOfTheHouse();
        geographicAreaDomainService.add(geographicArea);
        houseDomainService.add(house);
        double result = us623.getAverageRainfall(LocalDate.of(2019, 1, 3), LocalDate.of(2019, 1, 2));

        assertEquals(14.25, result, 0.00001);
    }

    @Test
    void getAverageRainfall2SensorsNoAg(){
        GeographicArea geographicArea = new GeographicArea("Porto", "City", new OccupationArea
                (new Location(10, 20, 30), 0.5, 0.5),
                new GeographicAreaType("City"));
        House house = new House("houseName", new Location(40.7486, -73.9864, 0), geographicArea.getName());
        AreaSensor s1 = new AreaSensor("1","s1", new Location(40.7486, -73.9864, 0), new SensorType("rainfall"), LocalDate.of(2018, 12, 10), "ºC");
        AreaSensor s2 = new AreaSensor("2","s2", new Location(40.7486, -73.9864, 0), new SensorType("rainfall"), LocalDate.of(2018, 12, 10), "ºC");
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
        double result = us623.getAverageRainfall(LocalDate.of(2019, 1, 3), LocalDate.of(2019, 1, 2));

        assertEquals(Double.NaN, result, 0.00001);
    }


    @Test
    void getAverageRainfall2SensorsDifferentDateDifferentType(){
        GeographicArea geographicArea = new GeographicArea("Porto", "city", new OccupationArea(new Location(10, 20, 30), 0.5, 0.5), new GeographicAreaType("city"));
        House house = new House("houseName", new Location(40.7486, -73.9864, 0), geographicArea.getName());
        AreaSensor s1 = new AreaSensor("1","s1", new Location(40.7486, -73.9864, 0), new SensorType("Temperature"), LocalDate.of(2018, 12, 10), "ºC");
        AreaSensor s2 = new AreaSensor("1","s2", new Location(40.7486, -73.9864, 0), new SensorType("Temperature"), LocalDate.of(2018, 12, 10), "ºC");
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
        double result = us623.getAverageRainfall(LocalDate.of(2019, 1, 3), LocalDate.of(2019, 1, 2));

        assertEquals(Double.NaN, result, 0.00001);
    }

    @Test
    void getAverageRainfall2SensorsDifferentDateDifferentInstallationDate(){
        GeographicArea geographicArea = new GeographicArea("Porto", "city", new OccupationArea(new Location(10, 20, 30), 0.5, 0.5), new GeographicAreaType("city"));
        House house = new House("houseName", new Location(40.7486, -73.9864, 0), geographicArea.getName());
        AreaSensor s1 = new AreaSensor("1","s1", new Location(40.7486, -73.9864, 0), new SensorType("Temperature"), LocalDate.of(2019, 01, 05), "ºC");
        AreaSensor s2 = new AreaSensor("1","s2", new Location(40.7486, -73.9864, 0), new SensorType("Temperature"), LocalDate.of(2018, 01, 05), "ºC");
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
        double result = us623.getAverageRainfall(LocalDate.of(2019, 1, 3), LocalDate.of(2019, 1, 2));

        assertEquals(Double.NaN, result, 0.00001);
    }

    @Test
    void getGeographicAreaOfTheHouseFalse(){
        GeographicArea geographicArea = new GeographicArea("Porto", "city", new OccupationArea(new Location(10, 20, 30), 0.5, 0.5), new GeographicAreaType("city"));
        House house = new House("houseName", new Location(40.7486, -73.9864, 0), geographicArea.getName());

        geographicAreaDomainService.add(geographicArea);
        houseDomainService.add(house);
        GetTotalAndAverageRainfallInHouseAreaForAGivenDayController us623 = new GetTotalAndAverageRainfallInHouseAreaForAGivenDayController(houseDomainService, geographicAreaDomainService);
        String result = us623.getGeographicAreaOfTheHouse();

        assertEquals("Porto",result);
    }


    @Test
    void getGeographicAreaOfTheHouseTrue(){
        House house = new House("houseName", new Location(40.7486, -73.9864, 0), null);
        houseDomainService.add(house);

        GetTotalAndAverageRainfallInHouseAreaForAGivenDayController us623 = new GetTotalAndAverageRainfallInHouseAreaForAGivenDayController(houseDomainService, geographicAreaDomainService);
        String result = us623.getGeographicAreaOfTheHouse();

        assertEquals(null,result);
    }

    @Test
    void checkIfHaveSensorsOnHouseAreaWithTypeAndReadings() throws Exception {
        getAverageRainfall2SensorsDifferentDateDifferentReadingsDateInverted();
        boolean result = ctrl.checkIfHaveSensorsOnHouseAreaWithTypeAndReadings();

        assertTrue(result);
    }


}