package pt.ipp.isep.dei.project1.readers.readerjson;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ReadingsListFromJsonTest {

    @Test
    void testToString() {
        //Arrange
        ReadingsListFromJson readingsListFromJson = new ReadingsListFromJson();
        //Act
        String result = readingsListFromJson.toString();
        //Assert
        assertEquals(readingsListFromJson.toString(),result);
    }

}