package pt.ipp.isep.dei.project1.readers.readerjson;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AreaAreaSensorFromJsonTest {

    @Test
    public void AreaSensorFromJsonToStringTest() {
        AreaSensorFromJson areaSensorFromJson = new AreaSensorFromJson();
        String expectedResut = areaSensorFromJson.getSensor() +"\n"+ areaSensorFromJson.getLocation();
        String result = areaSensorFromJson.toString();
        assertEquals(expectedResut,result);
    }
}