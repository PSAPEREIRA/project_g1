package pt.ipp.isep.dei.project1.readers.readerjson;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project1.model.house.Address;

import static org.junit.jupiter.api.Assertions.*;

class AddressFromJsonFileTest {


    @Test
    void getStreet() {
        AddressFromJsonFile addressFromJsonFile = new AddressFromJsonFile();
        addressFromJsonFile.setStreet("R. Dr. António Bernardino de Almeida");
        String expectedResult = "R. Dr. António Bernardino de Almeida";
        String result = addressFromJsonFile.getStreet();
        assertEquals(expectedResult, result);
    }

    @Test
    void getNumber() {
        AddressFromJsonFile addressFromJsonFile = new AddressFromJsonFile();
        addressFromJsonFile.setNumber("431");
        String expectedResult = "431";
        String result = addressFromJsonFile.getNumber();
        assertEquals(expectedResult, result);
    }

    @Test
    void getZip() {
        AddressFromJsonFile addressFromJsonFile = new AddressFromJsonFile();
        addressFromJsonFile.setZip("4200-072");
        String expectedResult = "4200-072";
        String result = addressFromJsonFile.getZip();
        assertEquals(expectedResult, result);
    }


    @Test
    void getTown() {
        AddressFromJsonFile addressFromJsonFile = new AddressFromJsonFile();
        addressFromJsonFile.setTown("Porto");
        String expectedResult = "Porto";
        String result = addressFromJsonFile.getTown();
        assertEquals(expectedResult, result);
    }


    @Test
    void getCountry() {
        AddressFromJsonFile addressFromJsonFile = new AddressFromJsonFile();
        addressFromJsonFile.setCountry("Portugal");
        String expectedResult = "Portugal";
        String result = addressFromJsonFile.getCountry();
        assertEquals(expectedResult, result);
    }

}