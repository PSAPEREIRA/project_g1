package pt.ipp.isep.dei.project1.dto;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project1.dto.device.DeviceDto;
import pt.ipp.isep.dei.project1.dto.device.ListOfDevicesDto;
import pt.ipp.isep.dei.project1.mapper.MapperDevice;
import pt.ipp.isep.dei.project1.mapper.MapperListOfDevice;
import pt.ipp.isep.dei.project1.model.device.ListOfDevices;
import pt.ipp.isep.dei.project1.model.device.dishwasher.Dishwasher;
import pt.ipp.isep.dei.project1.model.device.dishwasher.DishwasherSpec;
import pt.ipp.isep.dei.project1.model.device.dishwasher.DishwasherType;
import pt.ipp.isep.dei.project1.model.device.lamp.Lamp;
import pt.ipp.isep.dei.project1.model.device.lamp.LampSpec;
import pt.ipp.isep.dei.project1.model.device.lamp.LampType;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DeviceDtoTest {

    @Test
    void testGetSetId() {
        DeviceDto dto = new DeviceDto();
        dto.setId(20);
        long result = dto.getId();

        long expectedResult = 20;

        assertEquals(expectedResult,result);
    }

    @Test
    void testGetName() {
        Dishwasher dishwasher = new Dishwasher("dish1",new DishwasherSpec(), new DishwasherType());
        dishwasher.setName("dish1");
        String expectedResult = "dish1";
        String result = dishwasher.getName();
        assertEquals(expectedResult,result);
    }

    @Test
    void getDeviceSpecs() {

        DishwasherSpec dishwasherSpec = new DishwasherSpec();
        dishwasherSpec.setAttributeValue("nominal power" ,100.0);
        dishwasherSpec.setAttributeValue("capacity" ,80.0);

        Dishwasher d1 = new Dishwasher("dish",dishwasherSpec,new DishwasherType());

        List<String> expectedResult = new ArrayList<>();

        expectedResult.add("Nominal Power - 100.0");
        expectedResult.add("Capacity - 80.0");

        List<String> result = d1.getDeviceSpecs().getAttributeNamesAndValues();

        assertEquals(expectedResult, result);
    }


    @Test
    void getTypeCode() {
        DishwasherType dishwasherType = new DishwasherType();
        String result = dishwasherType.getTypeCode();
        String expectedResult = "DISW";

        assertEquals(expectedResult,result);
    }

    @Test
    void getSpecs() {
        Dishwasher d1 = new Dishwasher("Dish", new DishwasherSpec(),new DishwasherType());
        d1.getDeviceSpecs().setAttributeValue("nominal power", 20);
        DeviceDto deviceDto = MapperDevice.toDto(d1);
        assertEquals(20,deviceDto.getDeviceSpecs().getAttributeValue("nominal power"));
    }



}