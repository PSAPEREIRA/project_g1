package pt.ipp.isep.dei.project1.readers.readerjson;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GeographicAreaFromFileTest {

    @Test
    void checkIfToString() {


        GeographicAreaFromFile geographicAreaFromFile = new GeographicAreaFromFile();


        String id = "ISEP";

        String description = "Campus do ISEP";

        String type = "urban area";

        double width = 0.261;

        double length = 0.249;


        geographicAreaFromFile.setId(id);

        geographicAreaFromFile.setDescription(description);

        geographicAreaFromFile.setType(type);

        geographicAreaFromFile.setWidth(width);

        geographicAreaFromFile.setLength(length);


        String result = geographicAreaFromFile.toString();


        //    String expectedResult = id+" "+description+" "+type+" "+String.valueOf(width)+" "+String.valueOf(length);


        String expectedResult = "id = " + id + "\n" + "description = " + description + "\n" + "type = " + type + "\n" + "width = " + width + "\n" + "length = " + length + "\n" + "location = " + null + "\n" + "areaSensorJsonFormat = " + null + "\n" + "areaSensorXmlFormat = " + null;


        assertEquals(expectedResult, result);


    }
}