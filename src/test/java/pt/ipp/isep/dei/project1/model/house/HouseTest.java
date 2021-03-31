package pt.ipp.isep.dei.project1.model.house;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import pt.ipp.isep.dei.project1.model.geographicarea.Location;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

@ExtendWith(MockitoExtension.class)
class HouseTest {

    @Test
    public void createHouseNonValidNameNull() throws Exception {
        try {
            Location l1 = new Location(40.7486, -73.9864, 0);
            House house = new House("", l1, "Porto");

            fail("Fail. An exception must be thrown");
        } catch (RuntimeException e) {
            assertEquals("Insert a valid name for House", e.getMessage());
        }
    }

    @Test
    public void createHouseNonValidNameEmpty() throws Exception {
        try {
            Location l1 = new Location(40.7486, -73.9864, 0);
            House house = new House(null, l1, "Porto");
            fail("Fail. An exception must be thrown");
        } catch (RuntimeException e) {
            assertEquals("Insert a valid name for House", e.getMessage());
        }
    }

    @Test
    public void createHouseNonValidNameNullSecondConstructor() throws Exception {
        try {
            Location l1 = new Location(40.7486, -73.9864, 0);
            House house = new House("", l1, "Porto", new Address("1", "1", "1", "123", "Portugal"));
            fail("Fail. An exception must be thrown");
        } catch (RuntimeException e) {
            assertEquals("Insert a valid name for House", e.getMessage());
        }
    }

    @Test
    public void createHouseNonValidNameEmptySecondConstructor() throws Exception {
        try {
            Location l1 = new Location(40.7486, -73.9864, 0);
            House house = new House(null, l1, "Porto", new Address("1", "1", "1", "123", "Portugal"));
            fail("Fail. An exception must be thrown");
        } catch (RuntimeException e) {
            assertEquals("Insert a valid name for House", e.getMessage());
        }
    }


    @Test
    public void createHouseNonValidName() {
        try {
            Location l1 = new Location(40.7486, -73.9864, 0);
            House house = new House("", l1, "Porto");

            fail("Fail. An exception must be thrown");
        } catch (RuntimeException e) {
            assertEquals("Insert a valid name for House", e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void setmLocationTest() throws Exception {
        String houseName = "casa1";
        Location l1 = new Location(40.7486, -73.9864, 0);
        House house = new House("Casa", new Location(40.7486, -73.9864, 0),
                "Porto");
        house.setLocationOfHouse(l1);
        assertEquals(l1, house.getLocationOfHouse());
    }

    @Test
    void createAValidHouse() throws Exception {
        String houseName = "casa1";
        Address address = new Address("1", "1", "1", "123", "Portugal");
        Location l1 = new Location(40.7486, -73.9864, 0);
        House house = new House(houseName, l1, "Porto", address);

        assertEquals(address, house.getAddress());
    }


    @Test
    void validateName() throws Exception {
        //ARRANGE
        House house = new House("Casa1", new Location(40.7486, -73.9864, 0),
       "Porto", new Address("1", "1", "1", "123", "Portugal"));
        String result = house.getNameOfHouse();
        assertEquals("Casa1", result);
    }


}






