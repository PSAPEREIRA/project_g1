package pt.ipp.isep.dei.project1.controllers.house;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pt.ipp.isep.dei.project1.dto.geographicareadto.GeographicAreaDto;
import pt.ipp.isep.dei.project1.dto.geographicareadto.ListGeographicAreaDto;
import pt.ipp.isep.dei.project1.model.repositories.GeographicAreaRepository;
import pt.ipp.isep.dei.project1.mapper.MapperGeographicArea;
import pt.ipp.isep.dei.project1.model.geographicarea.*;
import pt.ipp.isep.dei.project1.model.house.Address;
import pt.ipp.isep.dei.project1.model.house.House;
import pt.ipp.isep.dei.project1.model.repositories.HouseRepository;
import pt.ipp.isep.dei.project1.model.repositories.GeographicAreaDomainService;
import pt.ipp.isep.dei.project1.model.repositories.HouseDomainService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ConfigureTheLocationOfTheHouseControllerTest {


    @Mock
    private GeographicAreaRepository repo;

    private GeographicAreaDomainService geographicAreaDomainService;
    @Mock
    private HouseDomainService houseDomainService;
    @Mock
    private HouseRepository houseRepository;

    @BeforeEach
    void initUseCase() {
        geographicAreaDomainService = new GeographicAreaDomainService(repo);
        houseDomainService = new HouseDomainService(houseRepository);
    }


    @Test
    void getListOfGA() throws Exception {
        GeographicArea geographicArea = new GeographicArea("porto", "city", new OccupationArea(new Location(10, 20, 0), 10, 10), new GeographicAreaType("city"));
        geographicAreaDomainService.add(geographicArea);
        House house = new House("h1", new Location(10.0001, 20.00001, 0), "Porto");
        houseDomainService.add(house);
        ConfigureTheLocationOfTheHouseController ctrl01 = new ConfigureTheLocationOfTheHouseController(geographicAreaDomainService, houseDomainService);
        ListGeographicAreaDto result = ctrl01.getListOfGeographicAreas();
        assertEquals(geographicArea.getName(),result.getListOfGADto().get(0).getName());
    }

    @Test
    void checkLocationOfHouseFalse() throws Exception {
        GeographicArea geographicArea = new GeographicArea("porto", "city", new OccupationArea(new Location(10, 20, 0), 10, 10), new GeographicAreaType("city"));
        geographicAreaDomainService.add(geographicArea);
        House house = new House("h1", new Location(13.0001, 23.00001, 0),"Porto");
        houseDomainService.add(house);
        List<Double> doubleList = new ArrayList<>();
        doubleList.add(10.0);
        doubleList.add(200.0);
        doubleList.add(200.0);
        ConfigureTheLocationOfTheHouseController ctrl01 = new ConfigureTheLocationOfTheHouseController(geographicAreaDomainService, houseDomainService);
        GeographicAreaDto geographicAreaDto = MapperGeographicArea.toDto(geographicArea);
        Boolean result = ctrl01.checkLocationOfTheHouse(doubleList, geographicAreaDto);
        assertFalse(result);
    }


    @Test
    void createNewHouseTestObjectHouse() throws Exception {
        //ARRANGE
        String name1 = "Yellow house";
        GeographicArea geographicArea = new GeographicArea("Porto", "city",
                new OccupationArea(new Location(10, 20, 30), 0.5, 0.5),
                new GeographicAreaType("city"));
        geographicAreaDomainService.add(geographicArea);
        Location l1 = new Location(40.7486, -73.9864, 0);
        Location l2 = new Location(50.7486, -73.9864, 0);
        Address address = new Address("rua 1","4200","porto","123","Portugal");

        House h1 = new House("Blue house", l1, "Porto", address);
        houseDomainService.add(h1);
        List<Double> doubleList = new ArrayList<>();
        doubleList.add(10.0);
        doubleList.add(20.0);
        doubleList.add(30.0);
        ConfigureTheLocationOfTheHouseController ctrl01 = new ConfigureTheLocationOfTheHouseController(geographicAreaDomainService, houseDomainService);
        GeographicAreaDto geographicAreaDto = MapperGeographicArea.toDto(geographicArea);
        //ACT
        ctrl01.changeLocationOfTheHouse(doubleList, geographicAreaDto);
        House result = h1;

        //ASSERT
        assertEquals(h1, result);
    }

    @Test
    void changeLocationHouseTest() throws Exception {
        //ARRANGE
        GeographicArea geographicArea = new GeographicArea("Porto", "city",
                new OccupationArea(new Location(10, 20, 30), 0.5, 0.5),
                new GeographicAreaType("city"));
        geographicAreaDomainService.add(geographicArea);
        Location l1 = new Location(40.7486, -73.9864, 0);
        Address address = new Address("rua 1","4200","porto","123","Portugal");

        House h1 = new House("Blue house", l1, "Porto", address);
        houseDomainService.add(h1);
        List<Double> doubleList = new ArrayList<>();
        doubleList.add(10.0);
        doubleList.add(20.0);
        doubleList.add(30.0);
        ConfigureTheLocationOfTheHouseController ctrl01 = new ConfigureTheLocationOfTheHouseController(geographicAreaDomainService, houseDomainService);
        GeographicAreaDto geographicAreaDto = MapperGeographicArea.toDto(geographicArea);
        //ACT
        ctrl01.changeLocationOfTheHouse(doubleList, geographicAreaDto);
        Location result = new Location(10, 20, 30);
        //ASSERT
        assertEquals(h1.getLocationOfHouse(), result);
    }

    @Test
    void changeLocationHouseTest1() throws Exception {
        //ARRANGE
        GeographicArea geographicArea = new GeographicArea("Porto", "city",
                new OccupationArea(new Location(10, 20, 30), 0.5, 0.5),
                new GeographicAreaType("city"));
        GeographicArea geographicArea2 = new GeographicArea("rua 2", "city",
                new OccupationArea(new Location(15, 20, 20), 0.5, 0.5),
                new GeographicAreaType("street"));
        geographicAreaDomainService.add(geographicArea2);
        Location l1 = new Location(40.7486, -73.9864, 0);
        Address address = new Address("rua 1","4200","porto","123","Portugal");

        House h1 = new House("Blue house", l1, geographicArea.getName(), address);
        houseDomainService.add(h1);
        List<Double> doubleList = new ArrayList<>();
        doubleList.add(10.0);
        doubleList.add(20.0);
        doubleList.add(30.0);
        ConfigureTheLocationOfTheHouseController ctrl01 = new ConfigureTheLocationOfTheHouseController(geographicAreaDomainService, houseDomainService);
        GeographicAreaDto geographicAreaDto = MapperGeographicArea.toDto(geographicArea2);
        GeographicArea expectedResult = geographicArea2;
        ctrl01.changeLocationOfTheHouse(doubleList,geographicAreaDto);
        //ASSERT
        assertEquals(expectedResult.getName(), h1.getGeographicAreaID());
    }

/**
    @Test
    void changeLocationHouseTest3() throws Exception {
        //ARRANGE
        GeographicArea geographicAreaID = new GeographicArea("Porto", "city",
                new OccupationArea(new Location(10, 20, 30), 0.5, 0.5),
                new GeographicAreaType("city"));
        geographicAreaService.add(geographicAreaID);
        Location l1 = new Location(40.7486, -73.9864, 0);
        Address address = new Address("rua 1","4200","porto","123","Portugal");

        House h1 = new House("Blue house", l1, new GeographicArea("Porto", "city",
                new OccupationArea(new Location(10, 20, 30), 0.5, 0.5),
                new GeographicAreaType("city")), new Address("rua2","4300","braga","123","Portugal"));
        List<Double> doubleList = new ArrayList<>();
        doubleList.add(10.0);
        doubleList.add(20.0);
        doubleList.add(30.0);
        ConfigureTheLocationOfTheHouseController ctrl01 = new ConfigureTheLocationOfTheHouseController(h1, geographicAreaService);
        GeographicAreaDto geographicAreaDto = MapperGeographicArea.toDto(geographicAreaID);
        //ACT
        ctrl01.changeLocationOfTheHouse(doubleList, geographicAreaDto);
        Address result = h1.getAddress();
        Address expectedResult = address;
        //ASSERT
        assertEquals(expectedResult, result);
    }
**/

}