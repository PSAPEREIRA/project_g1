package pt.ipp.isep.dei.project1.mapper;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project1.dto.device.DeviceDto;
import pt.ipp.isep.dei.project1.model.device.freezer.Freezer;
import pt.ipp.isep.dei.project1.model.device.freezer.FreezerSpec;
import pt.ipp.isep.dei.project1.model.device.freezer.FreezerType;
import pt.ipp.isep.dei.project1.model.interfaces.Device;

import static junit.framework.TestCase.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

class MapperDeviceTest {

    @Test
    void testConstructor() {
        try {
            MapperDevice mapperDevice = new MapperDevice();
        }
        catch (IllegalStateException e){
            String message = "Utility class";
            assertEquals(message, e.getMessage());
        }
    }

    @Test
    void testToDto() {
        //Arrange
        Device device = new Freezer("freezer",new FreezerSpec(),new FreezerType());
        //Act
        DeviceDto result = MapperDevice.toDto(device);
        //Assert
        assertEquals(device.getName(),result.getName());

    }
}