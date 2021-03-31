package pt.ipp.isep.dei.project1.model.house;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AddressTest {

    @Test
    void getStreet() {
        Address address = new Address("rua", "4000", "porto","123","portugal");
        String result = address.getStreet();
        String expectedResult = "rua";
        assertEquals(expectedResult, result);
    }

    @Test
    void getStreetWithProtectedConstruct() {
        Address address = new Address();
        address.setNumber("22");
        String result = address.getNumber();
        String expectedResult = "22";
        assertEquals(expectedResult, result);
    }

    @Test
    void setStreet() {
        Address address = new Address("rua", "4000", "porto","123","portugal");
        address.setStreet("rua nova");
        String result = address.getStreet();
        String expectedResult = "rua nova";
        assertEquals(expectedResult, result);

    }

    @Test
    void getZip() {
        Address address = new Address("rua", "4000", "porto","123","portugal");
        String result = address.getZip();
        String expectedResult = "4000";
        assertEquals(expectedResult, result);
    }

    @Test
    void setZip() {
        Address address = new Address("rua", "4000", "porto","123","portugal");
        address.setZip("5000");
        String result = address.getZip();
        String expectedResult = "5000";
        assertEquals(expectedResult, result);
    }

    @Test
    void getTown() {
        Address address = new Address("rua", "4000", "porto","123","portugal");
        String result = address.getTown();
        String expectedResult = "porto";
        assertEquals(expectedResult, result);
    }

    @Test
    void setTown() {
        Address address = new Address("rua", "4000", "porto","123","portugal");
        address.setTown("braga");
        String result = address.getTown();
        String expectedResult = "braga";
        assertEquals(expectedResult, result);
    }

    @Test
    void getNumber() {
        Address address = new Address("rua", "4000", "porto","123","portugal");
        String result = address.getNumber();
        String expectedResult = "123";
        assertEquals(expectedResult, result);
    }

    @Test
    void setNumber() {
        Address address = new Address("rua", "4000", "porto","123","portugal");
        address.setNumber("124");
        String result = address.getNumber();
        String expectedResult = "124";
        assertEquals(expectedResult, result);
    }

    @Test
    void getCountry() {
        Address address = new Address("rua", "4000", "porto","123","portugal");
        String result = address.getCountry();
        String expectedResult = "portugal";
        assertEquals(expectedResult, result);
    }

    @Test
    void setCountry() {
        Address address = new Address("rua", "4000", "porto","123","portugal");
        address.setCountry("china");
        String result = address.getCountry();
        String expectedResult = "china";
        assertEquals(expectedResult, result);
    }




}