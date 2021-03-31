package pt.ipp.isep.dei.project1.controllers.device;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pt.ipp.isep.dei.project1.dto.device.DeviceDto;
import pt.ipp.isep.dei.project1.dto.device.ListOfDevicesDto;
import pt.ipp.isep.dei.project1.dto.housedto.ListOfRoomsDto;
import pt.ipp.isep.dei.project1.dto.housedto.RoomDto;
import pt.ipp.isep.dei.project1.mapper.MapperDevice;
import pt.ipp.isep.dei.project1.mapper.MapperRoom;
import pt.ipp.isep.dei.project1.model.device.dishwasher.Dishwasher;
import pt.ipp.isep.dei.project1.model.device.dishwasher.DishwasherSpec;
import pt.ipp.isep.dei.project1.model.device.dishwasher.DishwasherType;
import pt.ipp.isep.dei.project1.model.device.electricoven.ElectricOven;
import pt.ipp.isep.dei.project1.model.device.electricoven.ElectricOvenSpec;
import pt.ipp.isep.dei.project1.model.device.electricoven.ElectricOvenType;
import pt.ipp.isep.dei.project1.model.device.eletricwaterheater.ElectricWaterHeater;
import pt.ipp.isep.dei.project1.model.device.eletricwaterheater.ElectricWaterHeaterSpec;
import pt.ipp.isep.dei.project1.model.device.eletricwaterheater.ElectricWaterHeaterType;
import pt.ipp.isep.dei.project1.model.device.fridge.Fridge;
import pt.ipp.isep.dei.project1.model.device.fridge.FridgeSpec;
import pt.ipp.isep.dei.project1.model.device.fridge.FridgeType;
import pt.ipp.isep.dei.project1.model.device.lamp.Lamp;
import pt.ipp.isep.dei.project1.model.device.lamp.LampSpec;
import pt.ipp.isep.dei.project1.model.device.lamp.LampType;
import pt.ipp.isep.dei.project1.model.device.microwaveoven.MicrowaveOven;
import pt.ipp.isep.dei.project1.model.device.microwaveoven.MicrowaveOvenSpec;
import pt.ipp.isep.dei.project1.model.device.microwaveoven.MicrowaveOvenType;
import pt.ipp.isep.dei.project1.model.device.stove.Stove;
import pt.ipp.isep.dei.project1.model.device.stove.StoveSpec;
import pt.ipp.isep.dei.project1.model.device.stove.StoveType;
import pt.ipp.isep.dei.project1.model.device.washingmachine.WashingMachine;
import pt.ipp.isep.dei.project1.model.device.washingmachine.WashingMachineSpec;
import pt.ipp.isep.dei.project1.model.device.washingmachine.WashingMachineType;
import pt.ipp.isep.dei.project1.model.geographicarea.GeographicArea;
import pt.ipp.isep.dei.project1.model.geographicarea.GeographicAreaType;
import pt.ipp.isep.dei.project1.model.geographicarea.Location;
import pt.ipp.isep.dei.project1.model.geographicarea.OccupationArea;
import pt.ipp.isep.dei.project1.model.house.Room;
import pt.ipp.isep.dei.project1.model.interfaces.Device;
import pt.ipp.isep.dei.project1.model.repositories.RoomRepository;
import pt.ipp.isep.dei.project1.model.repositories.RoomDomainService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class EditConfigurationOfDeviceControllerTest {

    @Mock
    private RoomRepository roomRepository;
    @Mock
    private RoomDomainService roomDomainService;
    @Mock
    EditConfigurationOfDeviceController ctrl215;


    @BeforeEach
    void initUseCase() throws Exception {
        roomDomainService = new RoomDomainService(roomRepository);
        ctrl215  = new EditConfigurationOfDeviceController(roomDomainService);
    }

    @Test
    public void checkIfchoosenDeviceToEdit() throws Exception {

        GeographicArea gA = new GeographicArea("Porto", "City", new OccupationArea(new Location(10, 20, 0), 10, 10), new GeographicAreaType("City"));

        List<Room> listOfRooms = new ArrayList<>();


        ArrayList<Double> dimension = new ArrayList<>();
        dimension.add(3.0);
        dimension.add(2.0);
        dimension.add(3.0);
        Room r1 = new Room("Room", "classroom", 2, dimension.get(0),dimension.get(1),dimension.get(2));
        Room r2 = new Room("Room2", "classroom", 1, dimension.get(0),dimension.get(1),dimension.get(2));


        Lamp d1 = new Lamp("lamp", new LampSpec(), new LampType());

        Fridge d2 = new Fridge("fridge", new FridgeSpec(), new FridgeType());

        r1.getListOfDevices().addDeviceToList(d1);
        r1.getListOfDevices().addDeviceToList(d2);


        listOfRooms.add(r1);
        listOfRooms.add(r2);


        roomDomainService.setListOfRooms(listOfRooms);

        DeviceDto deviceDto = MapperDevice.toDto(d1);
        RoomDto roomDto = MapperRoom.toDto(r1);

        Device result = d1;
        Device expectedResult = ctrl215.chosenDeviceToEdit(roomDto,deviceDto);

        assertEquals(result, expectedResult);
    }

    @Test
    public void checkIfEditAttributeValue() throws Exception {

        GeographicArea gA = new GeographicArea("Porto", "City", new OccupationArea(new Location(10, 20, 0), 10, 10), new GeographicAreaType("City"));

        ElectricWaterHeater d1 = new ElectricWaterHeater("Electric Water Heater", new ElectricWaterHeaterSpec(), new ElectricWaterHeaterType());


        ctrl215.editAttributeValue(d1, 0, 50);

        double result = d1.getDeviceSpecs().getAttributeValue("nominal power");
        double expectedResult = 50;

        assertEquals(result, expectedResult);

    }

    @Test
    void deleteProgramByIndex() throws Exception {
        GeographicArea gA = new GeographicArea("Porto", "City", new OccupationArea(new Location(10, 20, 0), 10, 10), new GeographicAreaType("City"));


        List<Room> listOfRooms = new ArrayList<>();

        ArrayList<Double> dimension = new ArrayList<>();
        dimension.add(3.0);
        dimension.add(2.0);
        dimension.add(3.0);
        Room r1 = new Room("Room", "classroom", 2, dimension.get(0),dimension.get(1),dimension.get(2));
        Room r2 = new Room("Room2", "classroom", 1, dimension.get(0),dimension.get(1),dimension.get(2));


        String[] program1 = new String[2];
        program1[0] = "Glasses";
        program1[1] = "0.9";

        String[] program2 = new String[2];
        program2[0] = "Echo";
        program2[1] = "1.3";

        String[] program3 = new String[2];
        program3[0] = "Dishes";
        program3[1] = "2.1";

        String[] program4 = new String[2];
        program4[0] = "Echo Turbo";
        program4[1] = "1.9";

        List<String[]> listOfPrograms = new ArrayList<>();

        listOfPrograms.add(program1);
        listOfPrograms.add(program2);
        listOfPrograms.add(program3);
        listOfPrograms.add(program4);


        Dishwasher d1 = new Dishwasher("d1", new DishwasherSpec(), new DishwasherType());
        Dishwasher d2 = new Dishwasher("d1", new DishwasherSpec(), new DishwasherType());

        d1.setListOfPrograms(listOfPrograms);

        r1.getListOfDevices().addDeviceToList(d1);
        r1.getListOfDevices().addDeviceToList(d2);

        listOfRooms.add(r1);
        listOfRooms.add(r2);

        roomDomainService.setListOfRooms(listOfRooms);


        ctrl215.deleteProgramByIndex(0, 0, 1);

        //int result = dws.getListOfPrograms().size();
        //int expectedResult = 3;
        //assertEquals(result,expectedResult);

        String result = d1.getListOfPrograms().get(1)[0];
        String expectedResult = "Dishes";

        assertEquals(result, expectedResult);


    }

    @Test
    void deleteProgramByIndex2() throws Exception {
        GeographicArea gA = new GeographicArea("Porto", "City", new OccupationArea(new Location(10, 20, 0), 10, 10), new GeographicAreaType("City"));


        List<Room> listOfRooms = new ArrayList<>();

        ArrayList<Double> dimension = new ArrayList<>();
        dimension.add(3.0);
        dimension.add(2.0);
        dimension.add(3.0);
        Room r1 = new Room("Room", "classroom", 2, dimension.get(0),dimension.get(1),dimension.get(2));
        Room r2 = new Room("Room2", "classroom", 1, dimension.get(0),dimension.get(1),dimension.get(2));


        String[] program1 = new String[2];
        program1[0] = "Glasses";
        program1[1] = "0.9";

        String[] program2 = new String[2];
        program2[0] = "Echo";
        program2[1] = "1.3";

        String[] program3 = new String[2];
        program3[0] = "Dishes";
        program3[1] = "2.1";

        String[] program4 = new String[2];
        program4[0] = "Echo Turbo";
        program4[1] = "1.9";

        List<String[]> listOfPrograms = new ArrayList<>();

        listOfPrograms.add(program1);
        listOfPrograms.add(program2);
        listOfPrograms.add(program3);
        listOfPrograms.add(program4);


        WashingMachine d1 = new WashingMachine("d1", new WashingMachineSpec(), new WashingMachineType());
        WashingMachine d2 = new WashingMachine("d2", new WashingMachineSpec(), new WashingMachineType());

        d1.setListOfPrograms(listOfPrograms);

        r1.getListOfDevices().addDeviceToList(d1);
        r1.getListOfDevices().addDeviceToList(d2);

        listOfRooms.add(r1);
        listOfRooms.add(r2);

        roomDomainService.setListOfRooms(listOfRooms);


        ctrl215.deleteProgramByIndex(0, 0, 1);

        //int result = dws.getListOfPrograms().size();
        //int expectedResult = 3;
        //assertEquals(result,expectedResult);

        String result = d1.getListOfPrograms().get(1)[0];
        String expectedResult = "Dishes";

        assertEquals(result, expectedResult);


    }

    @Test
    void checkIfEditProgramValueByIndex() throws Exception {

        GeographicArea gA = new GeographicArea("Porto", "City", new OccupationArea(new Location(10, 20, 0), 10, 10), new GeographicAreaType("City"));


        List<Room> listOfRooms = new ArrayList<>();

        ArrayList<Double> dimension = new ArrayList<>();
        dimension.add(3.0);
        dimension.add(2.0);
        dimension.add(3.0);
        Room r1 = new Room("Kitchen", "classroom", 2, dimension.get(0),dimension.get(1),dimension.get(2));
        Room r2 = new Room("Bedroom", "classroom", 1, dimension.get(0),dimension.get(1),dimension.get(2));


        String[] program1 = new String[2];
        program1[0] = "Glasses";
        program1[1] = "0.9";

        String[] program2 = new String[2];
        program2[0] = "Echo";
        program2[1] = "1.3";

        String[] program3 = new String[2];
        program3[0] = "Dishes";
        program3[1] = "2.1";

        String[] program4 = new String[2];
        program4[0] = "Echo Turbo";
        program4[1] = "1.9";

        List<String[]> listOfPrograms = new ArrayList<>();

        listOfPrograms.add(program1);
        listOfPrograms.add(program2);
        listOfPrograms.add(program3);
        listOfPrograms.add(program4);


        Dishwasher d1 = new Dishwasher("d1", new DishwasherSpec(), new DishwasherType());
        Dishwasher d2 = new Dishwasher("d1", new DishwasherSpec(), new DishwasherType());

        d1.setListOfPrograms(listOfPrograms);

        r1.getListOfDevices().addDeviceToList(d1);
        r1.getListOfDevices().addDeviceToList(d2);

        listOfRooms.add(r1);
        listOfRooms.add(r2);

        roomDomainService.setListOfRooms(listOfRooms);


        ctrl215.editProgramValueByIndex(0, 0, 1, 1, "2");


        String result = d1.getListOfPrograms().get(1)[1];
        String expectedResult = "2";

        assertEquals(expectedResult, result);
    }


    @Test
    void checkIfEditProgramValueByIndex2() throws Exception {

        GeographicArea gA = new GeographicArea("Porto", "City", new OccupationArea(new Location(10, 20, 0), 10, 10), new GeographicAreaType("City"));


        List<Room> listOfRooms = new ArrayList<>();

        ArrayList<Double> dimension = new ArrayList<>();
        dimension.add(3.0);
        dimension.add(2.0);
        dimension.add(3.0);
        Room r1 = new Room("Kitchen", "classroom", 2, dimension.get(0),dimension.get(1),dimension.get(2));
        Room r2 = new Room("Bedroom", "classroom", 1, dimension.get(0),dimension.get(1),dimension.get(2));


        String[] program1 = new String[2];
        program1[0] = "Glasses";
        program1[1] = "0.9";

        String[] program2 = new String[2];
        program2[0] = "Echo";
        program2[1] = "1.3";

        String[] program3 = new String[2];
        program3[0] = "Dishes";
        program3[1] = "2.1";

        String[] program4 = new String[2];
        program4[0] = "Echo Turbo";
        program4[1] = "1.9";

        List<String[]> listOfPrograms = new ArrayList<>();

        listOfPrograms.add(program1);
        listOfPrograms.add(program2);
        listOfPrograms.add(program3);
        listOfPrograms.add(program4);


        WashingMachine d1 = new WashingMachine("d1", new WashingMachineSpec(), new WashingMachineType());
        WashingMachine d2 = new WashingMachine("d2", new WashingMachineSpec(), new WashingMachineType());

        d1.setListOfPrograms(listOfPrograms);

        r1.getListOfDevices().addDeviceToList(d1);
        r1.getListOfDevices().addDeviceToList(d2);

        listOfRooms.add(r1);
        listOfRooms.add(r2);

        roomDomainService.setListOfRooms(listOfRooms);


        ctrl215.editProgramValueByIndex(0, 0, 1, 1, "2");


        String result = d1.getListOfPrograms().get(1)[1];
        String expectedResult = "2";

        assertEquals(expectedResult, result);
    }


    @Test
    void checkIfGetListOfRoomsNameOfHouseAsString() throws Exception {

        GeographicArea gA = new GeographicArea("Porto", "City", new OccupationArea(new Location(10, 20, 0), 10, 10), new GeographicAreaType("City"));


        List<Room> listOfRooms = new ArrayList<>();

        ArrayList<Double> dimension = new ArrayList<>();
        dimension.add(3.0);
        dimension.add(2.0);
        dimension.add(3.0);
        Room r1 = new Room("Kitchen", "classroom", 2, dimension.get(0),dimension.get(1),dimension.get(2));
        Room r2 = new Room("Bedroom", "classroom", 1, dimension.get(0),dimension.get(1),dimension.get(2));

        listOfRooms.add(r1);
        listOfRooms.add(r2);

        roomDomainService.setListOfRooms(listOfRooms);


        ListOfRoomsDto result = ctrl215.getListOfRoomsDto();

        List<String> expectedResult = new ArrayList<>();
        expectedResult.add("Kitchen");
        expectedResult.add("Bedroom");

        assertEquals(expectedResult.get(0), result.getRoomDto().get(0).getName());
    }

    @Test
    void checkIfGetListOfDevicesOfSelectedRoom() {

        GeographicArea gA = new GeographicArea("Porto", "City", new OccupationArea(new Location(10, 20, 0), 10, 10), new GeographicAreaType("City"));

        List<Room> listOfRooms = new ArrayList<>();

        ArrayList<Double> dimension = new ArrayList<>();
        dimension.add(3.0);
        dimension.add(2.0);
        dimension.add(3.0);
        Room r1 = new Room("Kitchen", "classroom", 2, dimension.get(0),dimension.get(1),dimension.get(2));
        Room r2 = new Room("Bedroom", "classroom", 1, dimension.get(0),dimension.get(1),dimension.get(2));


        String[] program1 = new String[2];
        program1[0] = "Glasses";
        program1[1] = "0.9";

        String[] program2 = new String[2];
        program2[0] = "Echo";
        program2[1] = "1.3";

        String[] program3 = new String[2];
        program3[0] = "Dishes";
        program3[1] = "2.1";

        String[] program4 = new String[2];
        program4[0] = "Echo Turbo";
        program4[1] = "1.9";

        List<String[]> listOfPrograms = new ArrayList<>();

        listOfPrograms.add(program1);
        listOfPrograms.add(program2);
        listOfPrograms.add(program3);
        listOfPrograms.add(program4);

        String nameD1 = "DishWasherTest";
        String nameD2 = "DishWasherTest2";

        Dishwasher d1 = new Dishwasher("DishWasherTest", new DishwasherSpec(), new DishwasherType());
        Dishwasher d2 = new Dishwasher("DishWasherTest2", new DishwasherSpec(), new DishwasherType());

        d1.setListOfPrograms(listOfPrograms);

        r1.getListOfDevices().addDeviceToList(d1);
        r1.getListOfDevices().addDeviceToList(d2);

        listOfRooms.add(r1);
        listOfRooms.add(r2);

        roomDomainService.setListOfRooms(listOfRooms);


        RoomDto roomDto = MapperRoom.toDto(r1);

        ListOfDevicesDto result = ctrl215.getListOfDevicesOfSelectedRoom(roomDto);

        List<String> expectedResult = new ArrayList<>();
        expectedResult.add(nameD1);
        expectedResult.add(nameD2);

        assertEquals(expectedResult.get(0), result.getListOfDevices().get(0).getName());
    }


    @Test
    void deleteProgramByIndex3() throws Exception {
        GeographicArea gA = new GeographicArea("Porto", "City", new OccupationArea(new Location(10, 20, 0), 10, 10), new GeographicAreaType("City"));


        List<Room> listOfRooms = new ArrayList<>();

        ArrayList<Double> dimension = new ArrayList<>();
        dimension.add(3.0);
        dimension.add(2.0);
        dimension.add(3.0);
        Room r1 = new Room("Room", "classroom", 2, dimension.get(0),dimension.get(1),dimension.get(2));
        Room r2 = new Room("Room2", "classroom", 1, dimension.get(0),dimension.get(1),dimension.get(2));


        String[] program1 = new String[2];
        program1[0] = "Glasses";
        program1[1] = "0.9";

        String[] program2 = new String[2];
        program2[0] = "Echo";
        program2[1] = "1.3";

        String[] program3 = new String[2];
        program3[0] = "Dishes";
        program3[1] = "2.1";

        String[] program4 = new String[2];
        program4[0] = "Echo Turbo";
        program4[1] = "1.9";

        List<String[]> listOfPrograms = new ArrayList<>();

        listOfPrograms.add(program1);
        listOfPrograms.add(program2);
        listOfPrograms.add(program3);
        listOfPrograms.add(program4);


        Stove d1 = new Stove("d1", new StoveSpec(), new StoveType());
        Stove d2 = new Stove("d2", new StoveSpec(), new StoveType());

        d1.setListOfPrograms(listOfPrograms);

        r1.getListOfDevices().addDeviceToList(d1);
        r1.getListOfDevices().addDeviceToList(d2);

        listOfRooms.add(r1);
        listOfRooms.add(r2);

        roomDomainService.setListOfRooms(listOfRooms);


        ctrl215.deleteProgramByIndex(0, 0, 1);

        //int result = dws.getListOfPrograms().size();
        //int expectedResult = 3;
        //assertEquals(result,expectedResult);

        String result = d1.getListOfPrograms().get(1)[0];
        String expectedResult = "Dishes";

        assertEquals(result, expectedResult);


    }

    @Test
    void checkIfEditProgramValueByIndex3() throws Exception {

        GeographicArea gA = new GeographicArea("Porto", "City", new OccupationArea(new Location(10, 20, 0), 10, 10), new GeographicAreaType("City"));


        List<Room> listOfRooms = new ArrayList<>();

        ArrayList<Double> dimension = new ArrayList<>();
        dimension.add(3.0);
        dimension.add(2.0);
        dimension.add(3.0);
        Room r1 = new Room("Kitchen", "classroom", 2, dimension.get(0),dimension.get(1),dimension.get(2));
        Room r2 = new Room("Bedroom", "classroom", 1, dimension.get(0),dimension.get(1),dimension.get(2));


        String[] program1 = new String[2];
        program1[0] = "Glasses";
        program1[1] = "0.9";

        String[] program2 = new String[2];
        program2[0] = "Echo";
        program2[1] = "1.3";

        String[] program3 = new String[2];
        program3[0] = "Dishes";
        program3[1] = "2.1";

        String[] program4 = new String[2];
        program4[0] = "Echo Turbo";
        program4[1] = "1.9";

        List<String[]> listOfPrograms = new ArrayList<>();

        listOfPrograms.add(program1);
        listOfPrograms.add(program2);
        listOfPrograms.add(program3);
        listOfPrograms.add(program4);


        Stove d1 = new Stove("d1", new StoveSpec(), new StoveType());
        Stove d2 = new Stove("d2", new StoveSpec(), new StoveType());

        d1.setListOfPrograms(listOfPrograms);

        r1.getListOfDevices().addDeviceToList(d1);
        r1.getListOfDevices().addDeviceToList(d2);

        listOfRooms.add(r1);
        listOfRooms.add(r2);

        roomDomainService.setListOfRooms(listOfRooms);


        ctrl215.editProgramValueByIndex(0, 0, 1, 1, "2");


        String result = d1.getListOfPrograms().get(1)[1];
        String expectedResult = "2";

        assertEquals(expectedResult, result);
    }

    @Test
    void deleteProgramByIndex4() throws Exception {
        GeographicArea gA = new GeographicArea("Porto", "City", new OccupationArea(new Location(10, 20, 0), 10, 10), new GeographicAreaType("City"));


        List<Room> listOfRooms = new ArrayList<>();

        ArrayList<Double> dimension = new ArrayList<>();
        dimension.add(3.0);
        dimension.add(2.0);
        dimension.add(3.0);
        Room r1 = new Room("Room", "classroom", 2, dimension.get(0),dimension.get(1),dimension.get(2));
        Room r2 = new Room("Room2", "classroom", 1, dimension.get(0),dimension.get(1),dimension.get(2));


        String[] program1 = new String[2];
        program1[0] = "Glasses";
        program1[1] = "0.9";

        String[] program2 = new String[2];
        program2[0] = "Echo";
        program2[1] = "1.3";

        String[] program3 = new String[2];
        program3[0] = "Dishes";
        program3[1] = "2.1";

        String[] program4 = new String[2];
        program4[0] = "Echo Turbo";
        program4[1] = "1.9";

        List<String[]> listOfPrograms = new ArrayList<>();

        listOfPrograms.add(program1);
        listOfPrograms.add(program2);
        listOfPrograms.add(program3);
        listOfPrograms.add(program4);


        ElectricOven d1 = new ElectricOven("d1", new ElectricOvenSpec(), new ElectricOvenType());
        ElectricOven d2 = new ElectricOven("d2", new ElectricOvenSpec(), new ElectricOvenType());

        d1.setListOfPrograms(listOfPrograms);

        r1.getListOfDevices().addDeviceToList(d1);
        r1.getListOfDevices().addDeviceToList(d2);

        listOfRooms.add(r1);
        listOfRooms.add(r2);

        roomDomainService.setListOfRooms(listOfRooms);


        ctrl215.deleteProgramByIndex(0, 0, 1);

        //int result = dws.getListOfPrograms().size();
        //int expectedResult = 3;
        //assertEquals(result,expectedResult);

        String result = d1.getListOfPrograms().get(1)[0];
        String expectedResult = "Dishes";

        assertEquals(result, expectedResult);


    }

    @Test
    void checkIfEditProgramValueByIndex4() throws Exception {

        GeographicArea gA = new GeographicArea("Porto", "City", new OccupationArea(new Location(10, 20, 0), 10, 10), new GeographicAreaType("City"));


        List<Room> listOfRooms = new ArrayList<>();

        ArrayList<Double> dimension = new ArrayList<>();
        dimension.add(3.0);
        dimension.add(2.0);
        dimension.add(3.0);
        Room r1 = new Room("Kitchen", "classroom", 2, dimension.get(0),dimension.get(1),dimension.get(2));
        Room r2 = new Room("Bedroom", "classroom", 1, dimension.get(0),dimension.get(1),dimension.get(2));


        String[] program1 = new String[2];
        program1[0] = "Glasses";
        program1[1] = "0.9";

        String[] program2 = new String[2];
        program2[0] = "Echo";
        program2[1] = "1.3";

        String[] program3 = new String[2];
        program3[0] = "Dishes";
        program3[1] = "2.1";

        String[] program4 = new String[2];
        program4[0] = "Echo Turbo";
        program4[1] = "1.9";

        List<String[]> listOfPrograms = new ArrayList<>();

        listOfPrograms.add(program1);
        listOfPrograms.add(program2);
        listOfPrograms.add(program3);
        listOfPrograms.add(program4);


        ElectricOven d1 = new ElectricOven("d1", new ElectricOvenSpec(), new ElectricOvenType());
        ElectricOven d2 = new ElectricOven("d2", new ElectricOvenSpec(), new ElectricOvenType());

        d1.setListOfPrograms(listOfPrograms);

        r1.getListOfDevices().addDeviceToList(d1);
        r1.getListOfDevices().addDeviceToList(d2);

        listOfRooms.add(r1);
        listOfRooms.add(r2);

        roomDomainService.setListOfRooms(listOfRooms);


        ctrl215.editProgramValueByIndex(0, 0, 1, 1, "2");


        String result = d1.getListOfPrograms().get(1)[1];
        String expectedResult = "2";

        assertEquals(expectedResult, result);
    }

    @Test
    void deleteProgramByIndex5() throws Exception {
        GeographicArea gA = new GeographicArea("Porto", "City", new OccupationArea(new Location(10, 20, 0), 10, 10), new GeographicAreaType("City"));


        List<Room> listOfRooms = new ArrayList<>();

        ArrayList<Double> dimension = new ArrayList<>();
        dimension.add(3.0);
        dimension.add(2.0);
        dimension.add(3.0);
        Room r1 = new Room("Room", "classroom", 2, dimension.get(0),dimension.get(1),dimension.get(2));
        Room r2 = new Room("Room2", "classroom", 1, dimension.get(0),dimension.get(1),dimension.get(2));


        String[] program1 = new String[2];
        program1[0] = "Glasses";
        program1[1] = "0.9";

        String[] program2 = new String[2];
        program2[0] = "Echo";
        program2[1] = "1.3";

        String[] program3 = new String[2];
        program3[0] = "Dishes";
        program3[1] = "2.1";

        String[] program4 = new String[2];
        program4[0] = "Echo Turbo";
        program4[1] = "1.9";

        List<String[]> listOfPrograms = new ArrayList<>();

        listOfPrograms.add(program1);
        listOfPrograms.add(program2);
        listOfPrograms.add(program3);
        listOfPrograms.add(program4);


        MicrowaveOven d1 = new MicrowaveOven("d1", new MicrowaveOvenSpec(), new MicrowaveOvenType());
        MicrowaveOven d2 = new MicrowaveOven("d2", new MicrowaveOvenSpec(), new MicrowaveOvenType());

        d1.setListOfPrograms(listOfPrograms);

        r1.getListOfDevices().addDeviceToList(d1);
        r1.getListOfDevices().addDeviceToList(d2);

        listOfRooms.add(r1);
        listOfRooms.add(r2);

        roomDomainService.setListOfRooms(listOfRooms);


        ctrl215.deleteProgramByIndex(0, 0, 1);

        //int result = dws.getListOfPrograms().size();
        //int expectedResult = 3;
        //assertEquals(result,expectedResult);

        String result = d1.getListOfPrograms().get(1)[0];
        String expectedResult = "Dishes";

        assertEquals(result, expectedResult);


    }

    @Test
    void checkIfEditProgramValueByIndex5() throws Exception {

        GeographicArea gA = new GeographicArea("Porto", "City", new OccupationArea(new Location(10, 20, 0), 10, 10), new GeographicAreaType("City"));


        List<Room> listOfRooms = new ArrayList<>();

        ArrayList<Double> dimension = new ArrayList<>();
        dimension.add(3.0);
        dimension.add(2.0);
        dimension.add(3.0);
        Room r1 = new Room("Kitchen", "classroom", 2, dimension.get(0),dimension.get(1),dimension.get(2));
        Room r2 = new Room("Bedroom", "classroom", 1, dimension.get(0),dimension.get(1),dimension.get(2));


        String[] program1 = new String[2];
        program1[0] = "Glasses";
        program1[1] = "0.9";

        String[] program2 = new String[2];
        program2[0] = "Echo";
        program2[1] = "1.3";

        String[] program3 = new String[2];
        program3[0] = "Dishes";
        program3[1] = "2.1";

        String[] program4 = new String[2];
        program4[0] = "Echo Turbo";
        program4[1] = "1.9";

        List<String[]> listOfPrograms = new ArrayList<>();

        listOfPrograms.add(program1);
        listOfPrograms.add(program2);
        listOfPrograms.add(program3);
        listOfPrograms.add(program4);


        MicrowaveOven d1 = new MicrowaveOven("d1", new MicrowaveOvenSpec(), new MicrowaveOvenType());
        MicrowaveOven d2 = new MicrowaveOven("d2", new MicrowaveOvenSpec(), new MicrowaveOvenType());

        d1.setListOfPrograms(listOfPrograms);

        r1.getListOfDevices().addDeviceToList(d1);
        r1.getListOfDevices().addDeviceToList(d2);

        listOfRooms.add(r1);
        listOfRooms.add(r2);

        roomDomainService.setListOfRooms(listOfRooms);


        ctrl215.editProgramValueByIndex(0, 0, 1, 1, "2");


        String result = d1.getListOfPrograms().get(1)[1];
        String expectedResult = "2";

        assertEquals(expectedResult, result);
    }
}