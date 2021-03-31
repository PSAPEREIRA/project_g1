package pt.ipp.isep.dei.project1.controllersweb.house;

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

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ConfigureTheLocationOfTheHouseControllerRestTest {

    @Mock
    private GeographicAreaRepository repo;

    private GeographicAreaDomainService geographicAreaDomainService;
    @Mock
    private HouseDomainService houseDomainService;
    @Mock
    private HouseRepository houseRepository;
    @Mock
    private ConfigureTheLocationOfTheHouseControllerRest controller;

    @BeforeEach
    void initUseCase() {
        geographicAreaDomainService = new GeographicAreaDomainService(repo);
        houseDomainService = new HouseDomainService(houseRepository);
        controller = new ConfigureTheLocationOfTheHouseControllerRest(geographicAreaDomainService, houseDomainService);

    }

    @Test
    void changeLocationHouseTest() {
        //ARRANGE
        GeographicArea geographicArea = new GeographicArea("Porto", "city",
                new OccupationArea(new Location(40.7486, -73.9864, 0), 0.5, 0.5),
                new GeographicAreaType("city"));
        geographicAreaDomainService.add(geographicArea);
        Location l1 = new Location(40.7486, -73.9864, 0);
        Address address = new Address("rua 1", "4200", "porto", "123", "Portugal");

        House h1 = new House("Blue house", l1, "Porto", address);
        houseDomainService.add(h1);
        List<Double> doubleList = new ArrayList<>();
        doubleList.add(10.0);
        doubleList.add(20.0);
        doubleList.add(30.0);
        ResponseEntity<Object> result = controller.changeLocationOfTheHouse(l1, "Porto");
        //ASSERT
        assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    @Test
    void changeLocationHouseTest1() {
        //ARRANGE
        GeographicArea geographicArea = new GeographicArea("Porto", "city",
                new OccupationArea(new Location(10, 20, 30), 0.5, 0.5),
                new GeographicAreaType("city"));
        GeographicArea geographicArea2 = new GeographicArea("rua 2", "city",
                new OccupationArea(new Location(15, 20, 20), 0.5, 0.5),
                new GeographicAreaType("street"));
        geographicAreaDomainService.add(geographicArea2);
        Location l1 = new Location(40.7486, -73.9864, 0);
        Address address = new Address("rua 1", "4200", "porto", "123", "Portugal");
        House h1 = new House("Blue house", l1, geographicArea.getName(), address);
        houseDomainService.add(h1);
        List<Double> doubleList = new ArrayList<>();
        doubleList.add(10.0);
        doubleList.add(20.0);
        doubleList.add(30.0);
        ResponseEntity<Object> result = controller.changeLocationOfTheHouse(l1, "Porto");
        //ASSERT
        assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
    }


    @Test
    void createNewHouseTestObjectHouse() {
        //ARRANGE
        GeographicArea geographicArea = new GeographicArea("Porto", "city",
                new OccupationArea(new Location(10, 20, 30), 0.5, 0.5),
                new GeographicAreaType("city"));
        GeographicArea geographicArea2 = new GeographicArea("rua 2", "city",
                new OccupationArea(new Location(15, 20, 20), 0.5, 0.5),
                new GeographicAreaType("street"));
        geographicAreaDomainService.add(geographicArea2);
        geographicAreaDomainService.add(geographicArea);
        Location l1 = new Location(40.7486, -73.9864, 0);
        Address address = new Address("rua 1", "4200", "porto", "123", "Portugal");
        House h1 = new House("Blue house", l1, geographicArea.getName(), address);
        houseDomainService.add(h1);
        List<Double> doubleList = new ArrayList<>();
        doubleList.add(10.0);
        doubleList.add(20.0);
        doubleList.add(30.0);
        ResponseEntity<Object> result = controller.changeLocationOfTheHouse(l1, "Porto");
        //ASSERT
        assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    @Test
    void getGeoLocationOfHouseNoGeoDefines() {

        Location l1 = new Location(40.7486, -73.9864, 0);
        Address address = new Address("rua 1", "4200", "porto", "123", "Portugal");
        House h1 = new House("Blue house", l1, null, address);
        houseDomainService.add(h1);


        ResponseEntity result = controller.getGeoLocationOfHouse();

        String expectedResult = "House location is not set yet";

        assertEquals(expectedResult,result.getBody());

    }

    @Test
    void getGeoLocationOfHouseDefinedGeo() {

        String  geoID ="PORTO" ;

        Location l1 = new Location(40.7486, -73.9864, 0);
        Address address = new Address("rua 1", "4200", "porto", "123", "Portugal");
        House h1 = new House("Blue house", l1,geoID , address);
        houseDomainService.add(h1);


        ResponseEntity result = controller.getGeoLocationOfHouse();

        String expectedResult = "House location is currently set in " + geoID + "!";

        assertEquals(expectedResult,result.getBody());

    }
}

