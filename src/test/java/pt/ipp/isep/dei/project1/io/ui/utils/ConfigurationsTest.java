package pt.ipp.isep.dei.project1.io.ui.utils;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ConfigurationsTest {


    @Test
    public void checkIfImportPackFromProperties() throws IOException {

        String parameter = "readerOfGaExtensions";
        List<String> result = Configurations.importPackFromProperties(parameter);

        List<String> expectedResult = new ArrayList<>();

        String expectedResult1 = "pt.ipp.isep.dei.project1.readers.readerjson.JsonReader";
        String expectedResult2 = "pt.ipp.isep.dei.project1.readers.xml.XmlReader";
        expectedResult.add(expectedResult1);
        expectedResult.add(expectedResult2);

        assertEquals(expectedResult, result);
    }

}