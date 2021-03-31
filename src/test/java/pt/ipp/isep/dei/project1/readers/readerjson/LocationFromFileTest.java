package pt.ipp.isep.dei.project1.readers.readerjson;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LocationFromFileTest {

    @Test
    void checkIfToString() {


        LocationFromFile location = new LocationFromFile();


        double latitude = 41.179230;

        double longitude = -8.606409;

        double altitude = 125;


        location.setLatitude(latitude);

        location.setLongitude(longitude);

        location.setAltitude(altitude);


        // String expectedResult = "41.17923 -8.606409 125.0";


        String expectedResult = String.valueOf(latitude) + " " + String.valueOf(longitude) + " " + String.valueOf(altitude);


        String result = location.toString();


        assertEquals(expectedResult, result);


    }
}