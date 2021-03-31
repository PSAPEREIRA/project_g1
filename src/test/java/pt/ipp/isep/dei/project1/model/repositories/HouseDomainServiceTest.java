package pt.ipp.isep.dei.project1.model.repositories;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import pt.ipp.isep.dei.project1.model.geographicarea.Location;
import pt.ipp.isep.dei.project1.model.house.Address;
import pt.ipp.isep.dei.project1.model.house.House;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class HouseDomainServiceTest {

    @Mock
    private HouseRepository houseRepository;

    @InjectMocks
    private HouseDomainService houseDomainService;

    @BeforeEach
    void initMocks() throws Exception {
      //  houseRepo = new HouseRepo(houseRepository);
        MockitoAnnotations.initMocks(this);

    }


    @Test
    public void validate2() {
        House h = new House("TheHouse", new Location(40.7486, -73.9864, 0),
                "Porto", new Address("1", "1", "1", "123", "Portugal"));

        House testHouse = new House();

        List<House> houseList = new ArrayList<>();
        houseList.add(h);

        when(houseRepository.findAll()).thenReturn(houseList);

        houseDomainService.validate(houseRepository);

        assertEquals("TheHouse", houseDomainService.getHouse().getNameOfHouse());
    }

    @Test
    public void validate() {
        House h = new House("TheHouse", new Location(40.7486, -73.9864, 0),
                "Porto", new Address("1", "1", "1", "123", "Portugal"));

        House testHouse = new House();

        List<House> houseList = new ArrayList<>();
        houseList.add(h);

        when(houseRepository.findAll()).thenReturn(houseList);

        houseDomainService.validate(houseRepository);

        assertEquals("TheHouse", houseDomainService.getHouse().getNameOfHouse());
    }



    @Test
    public void setAddress() {
        Address address = new Address("Street", "4000", "Porto","123","Portugal");

        houseDomainService.setAddress(address);
      //  List<House> houseList = houseRepository.findAll();
      //  System.out.println(houseList.size());
        Address result = houseDomainService.getHouse().getAddress();
        Address expectedResult = address;


        assertEquals(expectedResult,result);

    }

}