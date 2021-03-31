package pt.ipp.isep.dei.project1.model.house;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import pt.ipp.isep.dei.project1.mapper.MapperAreaSensor;
import pt.ipp.isep.dei.project1.mapper.MapperRoom;
import pt.ipp.isep.dei.project1.mapper.MapperRoomSensor;
import pt.ipp.isep.dei.project1.model.device.ListOfDevices;
import pt.ipp.isep.dei.project1.model.device.dishwasher.Dishwasher;
import pt.ipp.isep.dei.project1.model.device.dishwasher.DishwasherSpec;
import pt.ipp.isep.dei.project1.model.device.dishwasher.DishwasherType;
import pt.ipp.isep.dei.project1.model.device.eletricwaterheater.ElectricWaterHeater;
import pt.ipp.isep.dei.project1.model.device.eletricwaterheater.ElectricWaterHeaterType;
import pt.ipp.isep.dei.project1.model.geographicarea.GeographicArea;
import pt.ipp.isep.dei.project1.model.geographicarea.GeographicAreaType;
import pt.ipp.isep.dei.project1.model.geographicarea.Location;
import pt.ipp.isep.dei.project1.model.geographicarea.OccupationArea;
import pt.ipp.isep.dei.project1.model.interfaces.Device;
import pt.ipp.isep.dei.project1.model.sensor.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class RoomTest {


    @Test
    public void createRoomNonValidName() {
        try {
            ArrayList<Double> dimension = new ArrayList<>();
            dimension.add(3.0);
            dimension.add(2.0);
            dimension.add(3.0);
            Room room = new Room("", "room", 1, dimension.get(0),dimension.get(1),dimension.get(2));
            fail("Fail. An exception must be thrown");
        } catch (RuntimeException e) {
            assertEquals("Insert a valid name of room", e.getMessage());
        }
    }

    @Test
    public void createRoomNonValidNameNull() {
        try {
            ArrayList<Double> dimension = new ArrayList<>();
            dimension.add(3.0);
            dimension.add(2.0);
            dimension.add(3.0);
            Room room = new Room(null, "room", 1, dimension.get(0),dimension.get(1),dimension.get(2));
            fail("Fail. An exception must be thrown");
        } catch (RuntimeException e) {
            assertEquals("Insert a valid name of room", e.getMessage());
        }
    }


    @Test
    public void createRoomNonValidHouseFloor() {
        try {
            ArrayList<Double> dimension = new ArrayList<>();
            dimension.add(3.0);
            dimension.add(2.0);
            dimension.add(3.0);
            Room room = new Room("bedroom", "room", -1, dimension.get(0),dimension.get(1),dimension.get(2));
            fail("Fail. An exception must be thrown");
        } catch (RuntimeException e) {
            assertEquals("Insert a valid house floor", e.getMessage());
        }
    }

    @Test
    public void createRoomNonValidHouseFloorNaN() {
        try {
            ArrayList<Double> dimension = new ArrayList<>();
            dimension.add(3.0);
            dimension.add(2.0);
            dimension.add(3.0);
            Room room = new Room("bedroom", "room", Double.NaN, dimension.get(0),dimension.get(1),dimension.get(2));
            fail("Fail. An exception must be thrown");
        } catch (RuntimeException e) {
            assertEquals("Insert a valid house floor", e.getMessage());
        }
    }

    @Test
    public void createRoomNonValidDimensions() {
        try {
            ArrayList<Double> dimension = new ArrayList<>();
            dimension.add(-3.0);
            dimension.add(2.0);
            dimension.add(3.0);
            Room room = new Room("bedroom", "room", 1, dimension.get(0),dimension.get(1),dimension.get(2));
            fail("Fail. An exception must be thrown");
        } catch (RuntimeException e) {
            assertEquals("Insert a valid dimension", e.getMessage());
        }
    }


    @Test
    public void createRoomNonValidDimensions2() {
        try {
            ArrayList<Double> dimension = new ArrayList<>();
            dimension.add(3.0);
            dimension.add(-2.0);
            dimension.add(3.0);
            Room room = new Room("bedroom", "room", 1, dimension.get(0),dimension.get(1),dimension.get(2));
            fail("Fail. An exception must be thrown");
        } catch (RuntimeException e) {
            assertEquals("Insert a valid dimension", e.getMessage());
        }
    }

    @Test
    public void createRoomNonValidDimensions3() {
        try {
            ArrayList<Double> dimension = new ArrayList<>();
            dimension.add(3.0);
            dimension.add(2.0);
            dimension.add(-3.0);
            Room room = new Room("bedroom", "room", 1, dimension.get(0),dimension.get(1),dimension.get(2));
            fail("Fail. An exception must be thrown");
        } catch (RuntimeException e) {
            assertEquals("Insert a valid dimension", e.getMessage());
        }
    }


    @Test
    public void createRoomNonValidDimensions4() {
        try {
            ArrayList<Double> dimension = new ArrayList<>();
            dimension.add(0.0);
            dimension.add(2.0);
            dimension.add(3.0);
            Room room = new Room("bedroom", "room", 1, dimension.get(0),dimension.get(1),dimension.get(2));
            fail("Fail. An exception must be thrown");
        } catch (RuntimeException e) {
            assertEquals("Insert a valid dimension", e.getMessage());
        }
    }

    @Test
    public void createRoomNonValidDimensions5() {
        try {
            ArrayList<Double> dimension = new ArrayList<>();
            dimension.add(3.0);
            dimension.add(0.0);
            dimension.add(3.0);
            Room room = new Room("bedroom", "room", 1, dimension.get(0),dimension.get(1),dimension.get(2));
            fail("Fail. An exception must be thrown");
        } catch (RuntimeException e) {
            assertEquals("Insert a valid dimension", e.getMessage());
        }
    }

    @Test
    public void createRoomNonValidDimensions6() {
        try {
            ArrayList<Double> dimension = new ArrayList<>();
            dimension.add(5.0);
            dimension.add(2.0);
            dimension.add(0.0);
            Room room = new Room("bedroom", "room", 1, dimension.get(0),dimension.get(1),dimension.get(2));
            fail("Fail. An exception must be thrown");
        } catch (RuntimeException e) {
            assertEquals("Insert a valid dimension", e.getMessage());
        }
    }



    @Test
    public void createRoomNonValidDimensionsNaN() {
        try {
            ArrayList<Double> dimension = new ArrayList<>();
            dimension.add(Double.NaN);
            dimension.add(2.0);
            dimension.add(3.0);
            Room room = new Room("bedroom", "room", 1, dimension.get(0),dimension.get(1),dimension.get(2));
            fail("Fail. An exception must be thrown");
        } catch (RuntimeException e) {
            assertEquals("Insert a valid dimension", e.getMessage());
        }
    }

    @Test
    public void createRoomNonValidDimensionsNaN1() {
        try {
            ArrayList<Double> dimension = new ArrayList<>();
            dimension.add(3.1);
            dimension.add(2.0);
            dimension.add(Double.NaN);
            Room room = new Room("bedroom", "room", 1, dimension.get(0),dimension.get(1),dimension.get(2));
            fail("Fail. An exception must be thrown");
        } catch (RuntimeException e) {
            assertEquals("Insert a valid dimension", e.getMessage());
        }
    }


    @Test
    public void createRoomNonValidDimensionsNaN2() {
        try {
            ArrayList<Double> dimension = new ArrayList<>();
            dimension.add(3.0);
            dimension.add(Double.NaN);
            dimension.add(3.0);
            Room room = new Room("bedroom", "room", 1, dimension.get(0),dimension.get(1),dimension.get(2));
            fail("Fail. An exception must be thrown");
        } catch (RuntimeException e) {
            assertEquals("Insert a valid dimension", e.getMessage());
        }
    }


    @Test
    public void equalsTrueTest() {
        //ARRANGE

        ArrayList<Double> dimension = new ArrayList<>();
        dimension.add(3.0);
        dimension.add(2.0);
        dimension.add(3.0);
        Room room = new Room("bedRoom", "room", 1, dimension.get(0),dimension.get(1),dimension.get(2));
        room.getName();
        Object obj = new Room("bedRoom", "room", 1, dimension.get(0),dimension.get(1),dimension.get(2));
        ((Room) obj).getName();
        //ACT
        boolean result = room.equals(obj);
        //ASSERT
        assertTrue(result);
    }

    @Test
    public void equalsTrueTestMyself() {
        //ARRANGE
        ArrayList<Double> dimension = new ArrayList<>();
        dimension.add(3.0);
        dimension.add(2.0);
        dimension.add(3.0);
        Room room = new Room("bedRoom", "room", 1, dimension.get(0),dimension.get(1),dimension.get(2));
        Object obj = room;
        boolean result = room.equals(obj);
        //ASSERT
        assertTrue(result);
    }

    @Test
    public void equalsFalseTest() {
        //ARRANGE
        ArrayList<Double> dimension = new ArrayList<>();
        dimension.add(3.0);
        dimension.add(2.0);
        dimension.add(3.0);
        Room room = new Room("bedRoom", "room", 1, dimension.get(0),dimension.get(1),dimension.get(2));
        Object obj = new Room("livingRoom", "room", 1, dimension.get(0),dimension.get(1),dimension.get(2));
        ((Room) obj).getName();
        //ACT
        boolean result = room.equals(obj);
        //ASSERT
        assertFalse(result);
    }

    @Test
    public void equalsFalseTestOtherClass() {
        //ARRANGE
        ArrayList<Double> dimension = new ArrayList<>();
        dimension.add(3.0);
        dimension.add(2.0);
        dimension.add(3.0);
        Room room = new Room("bedRoom", "room", 1, dimension.get(0),dimension.get(1),dimension.get(2));
        room.getName();
        Object obj = new RoomSensor("1", "sensor1", new SensorType("temperature"), LocalDate.of(2018, 12, 10), "ºC");
        //ACT
        boolean result = room.equals(obj);
        //ASSERT
        assertFalse(result);
    }


    @Test
    public void testHashCode() {
        //ARRANGE
        ArrayList<Double> dimension = new ArrayList<>();
        dimension.add(3.0);
        dimension.add(2.0);
        dimension.add(3.0);
        Room room = new Room("bedRoom", "room", 1, dimension.get(0),dimension.get(1),dimension.get(2));
        room.getName();
        //ACT
        int result = room.getName().charAt(0);
        //ASSERT
        assertEquals(room.hashCode(), result);
    }

    @Test
    void newRoomNameOK() {
        //ARRANGE
        ArrayList<Double> dimension = new ArrayList<>();
        dimension.add(3.0);
        dimension.add(2.0);
        dimension.add(3.0);
        Room room = new Room("bedRoom", "room", 1, dimension.get(0),dimension.get(1),dimension.get(2));
        String result = room.getName();
        assertEquals("bedRoom", result);
    }

    @Test
    void newRoomFloor() {
        //ARRANGE
        ArrayList<Double> dimension = new ArrayList<>();
        dimension.add(3.0);
        dimension.add(2.0);
        dimension.add(3.0);
        Room room = new Room("bedRoom", "room", 1, dimension.get(0),dimension.get(1),dimension.get(2));
        //ACT
        double result = room.getHouseFloor();
        //ASSERT
        assertEquals(1.0, result);
    }

    @Test
    void testSetName() {
        //ARRANGE
        ArrayList<Double> dimension = new ArrayList<>();
        dimension.add(3.0);
        dimension.add(2.0);
        dimension.add(3.0);
        Room r1 = new Room("xpto1", "room", 1, dimension.get(0),dimension.get(1),dimension.get(2));
        r1.setName("xpto1");
        //ACT
        assertEquals("xpto1",r1.getName());
    }

    @Test
    void testSetHouseFloor() {
        //ARRANGE
        ArrayList<Double> dimension = new ArrayList<>();
        dimension.add(3.0);
        dimension.add(2.0);
        dimension.add(3.0);
        Room r1 = new Room("xpto1", "room", 1, dimension.get(0),dimension.get(1),dimension.get(2));
        r1.setHouseFloor(1);
        //ACT
        assertEquals(1.0,r1.getHouseFloor());
    }


    @Test
    void setListOfDevices() {
        ArrayList<Double> dimension = new ArrayList<>();
        dimension.add(3.0);
        dimension.add(2.0);
        dimension.add(3.0);
        Room r1 = new Room("xpto1", "room", 1, dimension.get(0),dimension.get(1),dimension.get(2));
        Dishwasher device = new Dishwasher("device1", new DishwasherSpec(), new DishwasherType());
        ListOfDevices listOfDevices = new ListOfDevices();
        r1.getListOfDevices().addDeviceToList(device);
        assertEquals(r1.getListOfDevices().getDeviceList().get(0), device);
    }

    @Test
    void addSensor() {
        ArrayList<Double> dimension = new ArrayList<>();
        dimension.add(3.0);
        dimension.add(2.0);
        dimension.add(3.0);
        Room r1 = new Room("xpto1", "room", 1, dimension.get(0),dimension.get(1),dimension.get(2));
        RoomSensor RoomSensor = new RoomSensor("1", "sensor1", new SensorType("temperature"), LocalDate.of(2018, 12, 10), "ºC");
        boolean expectedResult = r1.addSensor(RoomSensor);
        assertTrue(expectedResult);
    }

    @Test
    void addSensorFalse() {
        ArrayList<Double> dimension = new ArrayList<>();
        dimension.add(3.0);
        dimension.add(2.0);
        dimension.add(3.0);
        Room r1 = new Room("xpto1", "room", 1, dimension.get(0),dimension.get(1),dimension.get(2));
        RoomSensor RoomSensor = new RoomSensor("1", "sensor1", new SensorType("temperature"), LocalDate.of(2018, 12, 10), "ºC");
        r1.addSensor(RoomSensor);
        boolean expectedResult = r1.addSensor(RoomSensor);
        assertFalse(expectedResult);
    }

    @Test
    void addSensorDifferentNamesTypes() {
        ArrayList<Double> dimension = new ArrayList<>();
        dimension.add(3.0);
        dimension.add(2.0);
        dimension.add(3.0);
        Room r1 = new Room("xpto1", "room", 1, dimension.get(0),dimension.get(1),dimension.get(2));
        RoomSensor RoomSensor = new RoomSensor("1", "sensor1", new SensorType("temperature"), LocalDate.of(2018, 12, 10), "ºC");
        RoomSensor RoomSensor2 = new RoomSensor("1", "sensor0", new SensorType("rainfall"), LocalDate.of(2018, 12, 10), "ºC");
        r1.addSensor(RoomSensor2);
        boolean expectedResult = r1.addSensor(RoomSensor);
        assertFalse(expectedResult);
    }

    @Test
    void addSensorSameTypes() {
        ArrayList<Double> dimension = new ArrayList<>();
        dimension.add(3.0);
        dimension.add(2.0);
        dimension.add(3.0);
        Room r1 = new Room("xpto1", "room", 1, dimension.get(0),dimension.get(1),dimension.get(2));
        RoomSensor RoomSensor = new RoomSensor("1", "sensor1", new SensorType("temperature"), LocalDate.of(2018, 12, 10), "ºC");
        RoomSensor RoomSensor2 = new RoomSensor("1", "sensor0", new SensorType("temperature"), LocalDate.of(2018, 12, 10), "ºC");
        r1.addSensor(RoomSensor2);
        boolean expectedResult = r1.addSensor(RoomSensor);
        assertFalse(expectedResult);
    }


    @Test
    void getMaxRoomTemperatureNoSensor() {
        ArrayList<Double> dimension = new ArrayList<>();
        dimension.add(3.0);
        dimension.add(2.0);
        dimension.add(3.0);
        Room r1 = new Room("xpto1", "room", 1, dimension.get(0),dimension.get(1),dimension.get(2));
        RoomSensor RoomSensor = new RoomSensor("1", "sensor1", new SensorType("rainfall"), LocalDate.of(2018, 12, 10), "ºC");
        r1.addSensor(RoomSensor);
        LocalDate date1 = LocalDate.of(2018, 12, 21);
        String[] expectedResult = r1.getMaxRoomTemperature(date1);
        assertEquals("There are no temperature sensors in this room",expectedResult[0]);
    }

    @Test
    void getMaxRoomTemperatureNoReadings() {
        ArrayList<Double> dimension = new ArrayList<>();
        dimension.add(3.0);
        dimension.add(2.0);
        dimension.add(3.0);
        Room r1 = new Room("xpto1", "room", 1, dimension.get(0),dimension.get(1),dimension.get(2));
        RoomSensor RoomSensor = new RoomSensor("1", "sensor1", new SensorType("temperature"), LocalDate.of(2018, 12, 10), "ºC");
        r1.addSensor(RoomSensor);
        LocalDate date1 = LocalDate.of(2018, 12, 21);
        String[] expectedResult = r1.getMaxRoomTemperature(date1);
        assertEquals("There are no readings yet", expectedResult[0]);
    }

    @Test
    void getMaxRoomTemperatureReadings() {
        ArrayList<Double> dimension = new ArrayList<>();
        dimension.add(3.0);
        dimension.add(2.0);
        dimension.add(3.0);
        Room r1 = new Room("xpto1", "room", 1, dimension.get(0),dimension.get(1),dimension.get(2));
        RoomSensor RoomSensor = new RoomSensor("1", "sensor1", new SensorType("temperature"), LocalDate.of(2018, 12, 10), "ºC");
        LocalDateTime dateReading = LocalDateTime.of(2018, 12, 21, 0, 0, 0);
        Reading reading = new Reading(dateReading, 12.0);
        ListOfReadings listOfReadings = new ListOfReadings();
        listOfReadings.addReading(reading);
        RoomSensor.setListOfReadings(listOfReadings);
        r1.addSensor(RoomSensor);
        LocalDate date1 = LocalDate.of(2018, 12, 21);
        String[] result = r1.getMaxRoomTemperature(date1);
        String expectedResult = "There are no readings on that date";
        assertEquals(expectedResult, result[0]);
    }

    @Test
    void getMaxRoomTemperatureReadingsNoReadings() {
        ArrayList<Double> dimension = new ArrayList<>();
        dimension.add(3.0);
        dimension.add(2.0);
        dimension.add(3.0);
        Room r1 = new Room("xpto1", "room", 1, dimension.get(0),dimension.get(1),dimension.get(2));
        RoomSensor RoomSensor = new RoomSensor("1", "sensor1", new SensorType("temperature"), LocalDate.of(2018, 12, 10), "ºC");
        LocalDateTime dateReading = LocalDateTime.of(2018, 12, 21, 0, 0, 0);
        Reading reading = new Reading(dateReading, 12.0);
        ListOfReadings listOfReadings = new ListOfReadings();
        listOfReadings.addReading(reading);
        RoomSensor.setListOfReadings(listOfReadings);
        r1.addSensor(RoomSensor);
        LocalDate date1 = LocalDate.of(2018, 11, 21);
        String[] result = r1.getMaxRoomTemperature(date1);
        String expectedResult = "There are no readings on that date";
        assertEquals(expectedResult, result[0]);
    }

    @Test
    void getDevicesByID() {
        ArrayList<Double> dimension = new ArrayList<>();
        dimension.add(3.0);
        dimension.add(2.0);
        dimension.add(3.0);
        Room r1 = new Room("xpto1", "room", 1, dimension.get(0),dimension.get(1),dimension.get(2));
        Device result = r1.getListOfDevices().getDeviceByName("xpto1");
        assertEquals(null, result);
    }

    @Test
    void testInsertDeviceInRoomTrue() {
        Dishwasher device = new Dishwasher("device1", new DishwasherSpec(), new DishwasherType());
        ListOfDevices listOfDevices = new ListOfDevices();
        boolean resut = listOfDevices.addDeviceToList(device);
        assertTrue(resut);
    }


    @Test
    void listOfWaterHeatersSelectionTest() {
        //ARRANGE

        ElectricWaterHeaterType electricWaterHeaterType = new ElectricWaterHeaterType();
        DishwasherType dishwasherType = new DishwasherType();

        ElectricWaterHeater device = electricWaterHeaterType.createNewDevice("ele2");
        device.getDeviceSpecs().setAttributeValue("nominal power", 10);
        device.getDeviceSpecs().setAttributeValue("volume of water", 50);
        device.getDeviceSpecs().setAttributeValue("hot water temperature", 60);
        device.getDeviceSpecs().setAttributeValue("performance ratio", 20);


        Dishwasher device1 = dishwasherType.createNewDevice("dish1");
        device1.getDeviceSpecs().setAttributeValue("nominal power", 10);
        device1.getDeviceSpecs().setAttributeValue("capacity", 50);

        ElectricWaterHeater device2 = electricWaterHeaterType.createNewDevice("ele2");
        device.getDeviceSpecs().setAttributeValue("nominal power", 10);
        device.getDeviceSpecs().setAttributeValue("volume of water", 50);
        device.getDeviceSpecs().setAttributeValue("hot water temperature", 60);
        device.getDeviceSpecs().setAttributeValue("performance ratio", 20);

        ArrayList<Double> dimension = new ArrayList<>();
        dimension.add(3.0);
        dimension.add(2.0);
        dimension.add(3.0);
        Room r1 = new Room("xpto1", "room", 1, dimension.get(0),dimension.get(1),dimension.get(2));
        r1.getListOfDevices().addDeviceToList(device);
        r1.getListOfDevices().addDeviceToList(device1);
        r1.getListOfDevices().addDeviceToList(device2);
        //ACT
        ListOfDevices electricWaterHeaterList = r1.listOfWaterHeatersSelection();
        //ASSERT
        assertEquals(electricWaterHeaterList.getDeviceList().get(0), device);

    }

    @Test
    void listOfWaterHeatersSelectionTest2() {
        //ARRANGE
        ElectricWaterHeaterType electricWaterHeaterType = new ElectricWaterHeaterType();
        DishwasherType dishwasherType = new DishwasherType();

        ElectricWaterHeater device = electricWaterHeaterType.createNewDevice("ele1");
        device.getDeviceSpecs().setAttributeValue("nominal power", 10);
        device.getDeviceSpecs().setAttributeValue("volume of water", 50);
        device.getDeviceSpecs().setAttributeValue("hot water temperature", 60);
        device.getDeviceSpecs().setAttributeValue("performance ratio", 0.9);


        Dishwasher device1 = dishwasherType.createNewDevice("dish1");
        device1.getDeviceSpecs().setAttributeValue("nominal power", 10);
        device1.getDeviceSpecs().setAttributeValue("capacity", 50);

        ElectricWaterHeater device2 = electricWaterHeaterType.createNewDevice("ele2");
        device.getDeviceSpecs().setAttributeValue("nominal power", 10);
        device.getDeviceSpecs().setAttributeValue("volume of water", 50);
        device.getDeviceSpecs().setAttributeValue("hot water temperature", 60);
        device.getDeviceSpecs().setAttributeValue("performance ratio", 20);

        ArrayList<Double> dimension = new ArrayList<>();
        dimension.add(3.0);
        dimension.add(2.0);
        dimension.add(3.0);
        Room r1 = new Room("xpto1", "room", 1, dimension.get(0),dimension.get(1),dimension.get(2));
        r1.getListOfDevices().addDeviceToList(device);
        r1.getListOfDevices().addDeviceToList(device1);
        r1.getListOfDevices().addDeviceToList(device2);
        //ACT
        ListOfDevices electricWaterHeaterList = r1.listOfWaterHeatersSelection();
        //ASSERT
        assertEquals(electricWaterHeaterList.getDeviceList().get(1), device2);
    }


    @Test
    void testGetListOfDevicesNames() {
        ArrayList<Double> dimension = new ArrayList<>();
        dimension.add(3.0);
        dimension.add(2.0);
        dimension.add(3.0);
        ElectricWaterHeaterType electricWaterHeaterType = new ElectricWaterHeaterType();
        Room r1 = new Room("Bed Room", "room", 1, dimension.get(0),dimension.get(1),dimension.get(2));
        ElectricWaterHeater device = electricWaterHeaterType.createNewDevice("ele2");
        r1.getListOfDevices().addDeviceToList(device);
        List<String> result = r1.getListOfDevicesAsString();
        List<String> expectedResult = new ArrayList<>();
        expectedResult.add("ele2");

        assertEquals(expectedResult, result);
    }

    @Test
    void listOfSensorsOfRoomEmpty() {
        ArrayList<Double> dimension = new ArrayList<>();
        dimension.add(3.0);
        dimension.add(2.0);
        dimension.add(3.0);
        Room room = new Room("Bed Room", "room", 1, dimension.get(0),dimension.get(1),dimension.get(2));
        boolean result = room.checkListOfSensorsEmpty();
        assertTrue(result);
    }

    @Test
    void getDeviceByID() {
        ArrayList<Double> dimension = new ArrayList<>();
        dimension.add(3.0);
        dimension.add(2.0);
        dimension.add(3.0);
        Room room = new Room("Bed Room", "room", 1, dimension.get(0),dimension.get(1),dimension.get(2));
        Device d = room.getListOfDevices().getDeviceByName("xpto");
        assertEquals(null, d);
    }


    @Test
    void testAddSensorTrue() {
        //ARRANGE
        ArrayList<Double> dimension = new ArrayList<>();
        dimension.add(3.0);
        dimension.add(2.0);
        dimension.add(3.0);
        Room room = new Room("Bed Room", "room", 1, dimension.get(0),dimension.get(1),dimension.get(2));
        RoomSensor s1 = new RoomSensor("1", "sensor1",
                new SensorType("temperature"), LocalDate.of(2018, 12, 10), "ºC");
        //ACT
        boolean result = room.addSensor(s1);
        //ASSERT
        assertTrue(result);

    }

    @Test
    void testAddSensorFalse() {
        //ARRANGE
        ArrayList<Double> dimension = new ArrayList<>();
        dimension.add(3.0);
        dimension.add(2.0);
        dimension.add(3.0);
        Room room = new Room("Bed Room", "room", 1, dimension.get(0),dimension.get(1),dimension.get(2));

        RoomSensor s1 = new RoomSensor("1", "sensor1",
                new SensorType("temperature"), LocalDate.of(2018, 12, 10), "ºC");
        RoomSensor s2 = new RoomSensor("1", "sensor1",
                new SensorType("temperature"), LocalDate.of(2018, 12, 10), "ºC");
        //ACT
        room.addSensor(s1);
        boolean result = room.addSensor(s2);
        //ASSERT
        assertFalse(result);

    }


    @Test
    void testSetWith() {
        //ARRANGE
        ArrayList<Double> dimension = new ArrayList<>();
        dimension.add(3.0);
        dimension.add(2.0);
        dimension.add(3.0);
        Room r1 = new Room("xpto1", "room", 1, dimension.get(0),dimension.get(1),dimension.get(2));
        r1.setWidth(2);
        //ACT
        assertEquals(2.0,r1.getWidth());
    }


    @Test
    void testSetLength() {
        //ARRANGE
        ArrayList<Double> dimension = new ArrayList<>();
        dimension.add(3.0);
        dimension.add(2.0);
        dimension.add(3.0);
        Room r1 = new Room("xpto1", "room", 1, dimension.get(0),dimension.get(1),dimension.get(2));
        r1.setLength(3);
        //ACT
        assertEquals(3.0,r1.getLength());
    }



    @Test
    void testSetHeight() {
        //ARRANGE
        ArrayList<Double> dimension = new ArrayList<>();
        dimension.add(3.0);
        dimension.add(2.0);
        dimension.add(3.0);
        Room r1 = new Room("xpto1", "room", 1, dimension.get(0),dimension.get(1),dimension.get(2));
        r1.setHeight(4);
        //ACT
        assertEquals(4.0,r1.getHeight());
    }

    @Test
    void testGetHouseGrid() {
        //ARRANGE
        ArrayList<Double> dimension = new ArrayList<>();
        dimension.add(3.0);
        dimension.add(2.0);
        dimension.add(3.0);
        Room r1 = new Room("xpto1", "room", 1, dimension.get(0),dimension.get(1),dimension.get(2));
        HouseGrid houseGrid = new HouseGrid("xpto", 500);
        String result  = r1.getHouseGrid();
        //ACT
        assertEquals(result,r1.getHouseGrid());
    }



    @Test
    void testDescription() {
        //ARRANGE
        ArrayList<Double> dimension = new ArrayList<>();
        dimension.add(3.0);
        dimension.add(2.0);
        dimension.add(3.0);
        Room r1 = new Room("xpto1", "room", 1, dimension.get(0),dimension.get(1),dimension.get(2));
        r1.setDescription("RoomOfFun");
        //ACT
        assertEquals("RoomOfFun",r1.getDescription());
    }

    @Test
    void testConstructor() {
        try {
            Room room = new Room();
        }
        catch (IllegalStateException e){
            String message = "Utility class";
            assertEquals(message, e.getMessage());
        }
    }


    @Test
    void testGetInstancesWithTemperatureHigherLowerComfortOnRoomNull() {
        //Arrange
        ArrayList<Double> dimension = new ArrayList<>();
        dimension.add(3.0);
        dimension.add(2.0);
        dimension.add(3.0);
        Room room = new Room("xpto1", "room", 1, dimension.get(0),dimension.get(1),dimension.get(2));
        RoomSensor s1 = new RoomSensor();
        s1.setName("s1");
        s1.setSensorType(new SensorType("rainfall"));
        LocalDate startDate = LocalDate.of(2019, 01, 14);
        LocalDateTime date1 = LocalDateTime.of(2019, 1, 15, 12, 00);
        Reading r1 = new Reading(date1, 28);
        LocalDateTime date2 = LocalDateTime.of(2019, 1, 15, 12, 3);
        Reading r2 = new Reading(date2, 15);
        LocalDateTime date3 = LocalDateTime.of(2019, 1, 17, 12, 5);
        Reading r3 = new Reading(date3, 33);
        LocalDateTime date4 = LocalDateTime.of(2019, 1, 17, 12, 6);
        Reading r4 = new Reading(date4, 31);
        LocalDateTime date5 = LocalDateTime.of(2019, 1, 14, 12, 10);
        Reading r5 = new Reading(date5, -15);
        LocalDateTime date6 = LocalDateTime.of(2019, 1, 14, 12, 15);
        Reading r6 = new Reading(date6, -2);
        ListOfReadings lRead1 = new ListOfReadings();
        lRead1.addReading(r1);
        lRead1.addReading(r2);
        lRead1.addReading(r3);
        lRead1.addReading(r4);
        lRead1.addReading(r5);
        lRead1.addReading(r6);
        int cat=1;
        String option="HIGHER";
        List<Double> listHouseAreaTemp= new ArrayList<>();
        listHouseAreaTemp.add(15.0);
        listHouseAreaTemp.add(20.0);
        listHouseAreaTemp.add(25.0);
        listHouseAreaTemp.add(30.0);
        s1.setListOfReadings(lRead1);
        List<LocalDateTime>expectedResult= new ArrayList<>();

        //ACT
        List<LocalDateTime>result = room.getInstancesWithTemperatureHigherLowerComfort(listHouseAreaTemp,cat,option,startDate);
        //ASSERT
        assertEquals(expectedResult,result);
    }

    @Test
    void testGetInstancesWithTemperatureHigherLowerComfortOnRoom() {
        //Arrange
        ArrayList<Double> dimension = new ArrayList<>();
        dimension.add(3.0);
        dimension.add(2.0);
        dimension.add(3.0);
        Room room = new Room("xpto1", "room", 1, dimension.get(0),dimension.get(1),dimension.get(2));
        RoomSensor s1 = new RoomSensor();
        s1.setName("s1");
        s1.setSensorType(new SensorType("temperature"));
        LocalDate startDate = LocalDate.of(2019, 01, 14);
        LocalDateTime date1 = LocalDateTime.of(2019, 1, 15, 12, 00);
        Reading r1 = new Reading(date1, 28);
        LocalDateTime date2 = LocalDateTime.of(2019, 1, 15, 12, 3);
        Reading r2 = new Reading(date2, 15);
        LocalDateTime date3 = LocalDateTime.of(2019, 1, 17, 12, 5);
        Reading r3 = new Reading(date3, 33);
        LocalDateTime date4 = LocalDateTime.of(2019, 1, 17, 12, 6);
        Reading r4 = new Reading(date4, 31);
        LocalDateTime date5 = LocalDateTime.of(2019, 1, 14, 12, 10);
        Reading r5 = new Reading(date5, -15);
        LocalDateTime date6 = LocalDateTime.of(2019, 1, 14, 12, 15);
        Reading r6 = new Reading(date6, -2);
        ListOfReadings lRead1 = new ListOfReadings();
        lRead1.addReading(r1);
        lRead1.addReading(r2);
        lRead1.addReading(r3);
        lRead1.addReading(r4);
        lRead1.addReading(r5);
        lRead1.addReading(r6);
        room.addSensor(s1);
        int cat=1;
        String option="HIGHER";
        List<Double> listHouseAreaTemp= new ArrayList<>();
        listHouseAreaTemp.add(15.0);
        listHouseAreaTemp.add(20.0);
        listHouseAreaTemp.add(25.0);
        listHouseAreaTemp.add(30.0);
        s1.setListOfReadings(lRead1);
        List<LocalDateTime>expectedResult= new ArrayList<>();
        expectedResult.add(r1.getDateTime());
        expectedResult.add(r3.getDateTime());
        expectedResult.add(r4.getDateTime());
        //ACT
        List<LocalDateTime>result = room.getInstancesWithTemperatureHigherLowerComfort(listHouseAreaTemp,cat,option,startDate);
        //ASSERT
        assertEquals(expectedResult,result);
    }

    @Test
    void testCreateAndAddSensorToGa() {
        ArrayList<Double> dimension = new ArrayList<>();
        dimension.add(3.0);
        dimension.add(2.0);
        dimension.add(3.0);
        Room room = new Room("xpto1", "room", 1, dimension.get(0),dimension.get(1),dimension.get(2));

        SensorType sensorType1 = new SensorType("temperature");

        RoomSensor s1 = new RoomSensor("1", "sensor1", sensorType1, LocalDate.of(2019, 04, 28), "ºC");

        int expectedResult = 1;

        int result = room.createAndAddRoomSensor(MapperRoomSensor.toDto(s1));

        Assertions.assertEquals(expectedResult,result);
    }

    @Test
    void testCreateAndAddSensorToGaImpossible() {
        ArrayList<Double> dimension = new ArrayList<>();
        dimension.add(3.0);
        dimension.add(2.0);
        dimension.add(3.0);
        Room room = new Room("xpto1", "room", 1, dimension.get(0),dimension.get(1),dimension.get(2));


        SensorType sensorType1 = new SensorType("temperature");

        RoomSensor s1 = new RoomSensor("1", "sensor1", sensorType1, LocalDate.of(2019, 04, 28), "ºC");
        s1.setIdOfRoomSensor("");
        int expectedResult = -1;

        int result = room.createAndAddRoomSensor(MapperRoomSensor.toDto(s1));

        Assertions.assertEquals(expectedResult,result);
    }


    @Test
    void testCreateAndAddSensorToGaAlreadyExists() {
        ArrayList<Double> dimension = new ArrayList<>();
        dimension.add(3.0);
        dimension.add(2.0);
        dimension.add(3.0);
        Room room = new Room("xpto1", "room", 1, dimension.get(0),dimension.get(1),dimension.get(2));

        SensorType sensorType1 = new SensorType("temperature");

        RoomSensor s1 = new RoomSensor("1", "sensor1", sensorType1, LocalDate.of(2019, 04, 28), "ºC");

        int expectedResult = -2;

        room.createAndAddRoomSensor(MapperRoomSensor.toDto(s1));

        int result = room.createAndAddRoomSensor(MapperRoomSensor.toDto(s1));

        Assertions.assertEquals(expectedResult,result);
    }

    @Test
    void testCreateAndAddSensorToGa2() {
        ArrayList<Double> dimension = new ArrayList<>();
        dimension.add(3.0);
        dimension.add(2.0);
        dimension.add(3.0);
        Room room = new Room("xpto1", "room", 1, dimension.get(0),dimension.get(1),dimension.get(2));

        SensorType sensorType1 = new SensorType("temperature");

        RoomSensor s1 = new RoomSensor("1", "sensor1", sensorType1, LocalDate.of(2019, 04, 28), "ºC");

        s1.setRoom(room);
        room.createAndAddRoomSensor(MapperRoomSensor.toDto(s1));

        Assertions.assertEquals(room.getListOfSensors().get(0).getRoom(),room);
    }

}


