package pt.ipp.isep.dei.project1.readers.readerjson;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ListOfGaFromFileTest {

    @Test
    void checkIfToString() {


        ListOfGaFromFile listOfGaFromFile = new ListOfGaFromFile();


        List<GeographicAreaFromFile> listOfGa = new ArrayList<>();


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


        listOfGa.add(geographicAreaFromFile);


        listOfGaFromFile.setGeographicalArea(listOfGa);


        String result = listOfGaFromFile.toString();


        String expectedResult = "[id = " + id + "\n" + "description = " + description + "\n" + "type = " + type + "\n" + "width = " + width + "\n" + "length = " + length + "\n" + "location = " + null + "\n" + "areaSensorJsonFormat = " + null + "\n" + "areaSensorXmlFormat = " + null + "]" + "\n";


        assertEquals(expectedResult, result);

    }
}