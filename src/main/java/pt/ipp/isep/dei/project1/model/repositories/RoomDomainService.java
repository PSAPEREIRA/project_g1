package pt.ipp.isep.dei.project1.model.repositories;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.ipp.isep.dei.project1.dto.housedto.ListOfRoomsDto;
import pt.ipp.isep.dei.project1.dto.housedto.RoomDto;
import pt.ipp.isep.dei.project1.dto.sensordto.RoomSensorDto;
import pt.ipp.isep.dei.project1.io.ui.utils.Configurations;
import pt.ipp.isep.dei.project1.mapper.MapperListOfRoomSensor;
import pt.ipp.isep.dei.project1.mapper.MapperListOfRooms;
import pt.ipp.isep.dei.project1.mapper.MapperRoom;
import pt.ipp.isep.dei.project1.model.device.ListOfDevices;
import pt.ipp.isep.dei.project1.model.house.Room;
import pt.ipp.isep.dei.project1.model.interfaces.DeviceType;
import pt.ipp.isep.dei.project1.model.sensor.RoomSensor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class RoomDomainService {

    @Getter
    @Setter
    private List<Room> listOfRooms;
    private final RoomRepository roomRepository;

    @Autowired
    public RoomDomainService(RoomRepository roomRepository) throws Exception {
        this.roomRepository = roomRepository;
        this.listOfRooms = roomRepository.findAll();
        getListOfDeviceTypes();
    }

    public RoomRepository getRoomRepository() {
        return roomRepository;
    }

    /**
     * Rooms Methods
     */

    public boolean addRoom(Room room) {
        if (!listOfRooms.contains(room)) {
            listOfRooms.add(room);
            roomRepository.save(room);
            return true;
        } else
            return false;
    }

    public boolean newRoom(RoomDto roomDto) {
        Room room;
        try {
            room = new Room(roomDto.getName(), roomDto.getDescription(), roomDto.getHouseFloor(), roomDto.getWidth(),
                    roomDto.getLength(), roomDto.getHeight());
        } catch (RuntimeException e) {
            return false;
        }
        return addRoom(room);
    }

    public Room getRoomByName(String roomName) {
        for (Room room : listOfRooms)
            if (room.getName().equals(roomName))
                return room;
        return null;
    }

    public RoomDto getRoomByNameDto(String roomName) {
        for (Room room : listOfRooms)
            if (room.getName().equals(roomName))
                return MapperRoom.toDto(room);
        return null;
    }

    public ListOfRoomsDto getRoomsDTO() {
        return MapperListOfRooms.toDto(listOfRooms);
    }


    public ListOfRoomsDto getRoomsWithoutHouseGridDTO() {
        List<Room> listOfRoomsWithoutHouseGrid = new ArrayList<>();
        for (Room room : listOfRooms)
            if (room.getHouseGrid().equals(""))
                listOfRoomsWithoutHouseGrid.add(room);
        return MapperListOfRooms.toDto(listOfRoomsWithoutHouseGrid);
    }

    public ListOfDevices getListOfAllDevicesInsideHouse() {
        ListOfDevices listOfDevices = new ListOfDevices();
        for (int roomIndex = 0; roomIndex < listOfRooms.size(); roomIndex++) {
            ListOfDevices devicesInRoomList = listOfRooms.get(roomIndex).getListOfDevices();
            listOfDevices.getDeviceList().addAll(devicesInRoomList.getDeviceList());
        }
        return listOfDevices;
    }

    public void goToRoomListToDeleteProgram(int roomIndex, int deviceIndex, int programIndex) {
        listOfRooms.get(roomIndex).goToDevListToDeleteProg(deviceIndex, programIndex);
    }

    public void getRoomToEditProgramField(int roomIndex, int deviceIndex, int programIndex, int fieldIndex, String newValue) {
        listOfRooms.get(roomIndex).editProgramFieldOnDevice(deviceIndex, programIndex, fieldIndex, newValue);
    }

    public List<DeviceType> getListOfDeviceTypes() throws Exception {
        DeviceType mDeviceType;
        List<DeviceType> deviceTypeList = new ArrayList<>();
        List<String> readListOfDeviceTypes = Configurations.readListOfDeviceTypes();

        for (int indexDeviceType = 0; indexDeviceType < readListOfDeviceTypes.size(); indexDeviceType++) {
            mDeviceType = (DeviceType) Class.forName(readListOfDeviceTypes.get(indexDeviceType)).newInstance();
            deviceTypeList.add(mDeviceType);
        }
        return deviceTypeList;
    }

    public List<Room> getRoomsAttachedToHouseGrid(String houseGridCode) {
        List<Room> roomList = new ArrayList<>();
        for (Room room : listOfRooms) {
            if (room.getHouseGrid().equals(houseGridCode))
                roomList.add(room);
        }
        return roomList;
    }

    /**
     * Sensor Methods
     */

    public boolean addSensorToList(RoomSensor newRoomSensor) {
        Room room = getRoomByName(newRoomSensor.getRoom().getName());
        if (!room.getListOfSensors().contains(newRoomSensor)) {
            room.getListOfSensors().add(newRoomSensor);
            roomRepository.save(room);
            return true;
        } else
            return false;
    }


    public List<RoomSensor> getAllSensors() {
        List<RoomSensor> roomSensors = new ArrayList<>();
        for (Room room : listOfRooms)
            roomSensors.addAll(room.getListOfSensors());
        return roomSensors;
    }


    public List<LocalDateTime> getInstancesWithTemperatureHigherLowerComfort(RoomDto roomOption, List<Double> listHouseAreaTemp, int cat, String option, LocalDate startDate) {
        ArrayList<LocalDateTime> instanceResultList = new ArrayList<>();
        for (Room room : listOfRooms)
            if (room.getName().equalsIgnoreCase(roomOption.getName()))
                instanceResultList.addAll(room.getInstancesWithTemperatureHigherLowerComfort(listHouseAreaTemp, cat, option, startDate));
        return instanceResultList;
    }

    public boolean checkTemperatureSensorsOfRoom(RoomDto roomDto) {
        RoomSensor roomSensor = null;
        for (Room room : listOfRooms)
            if (room.getName().equalsIgnoreCase(roomDto.getName()))
                roomSensor = room.checkTemperatureSensorOfRoom();
        return roomSensor != null;
    }

    public boolean checkTemperatureSensorsOfRoomV2(String roomName) {
        RoomDto roomDto = getRoomByNameDto(roomName);
        RoomSensor roomSensor = null;
        for (Room room : listOfRooms)
            if (room.getName().equalsIgnoreCase(roomDto.getName()))
                roomSensor = room.checkTemperatureSensorOfRoom();
        return roomSensor != null;
    }

    public String[] getMaxRoomTemperature(RoomDto roomDto, LocalDate date) {
        Room room = getRoomByName(roomDto.getName());
        return room.getMaxRoomTemperature(date);
    }

    public boolean checkListOfSensorsEmpty(String roomName) {
        Room room = getRoomByName(roomName);
        return  room.checkListOfSensorsEmpty();
    }

    public String[] getCurrentTemperature(String roomName) {
        Room room = getRoomByName(roomName);
        return room.getCurrentTemperature();
    }

    public RoomDto editRoom(String name, RoomDto roomDto) {
        Room room = getRoomByName(name);
        if (roomDto.getDescription() != null)
            room.setDescription(roomDto.getDescription());
        if (!Double.isNaN(roomDto.getHouseFloor()))
            room.setHouseFloor(roomDto.getHouseFloor());
        if (!Double.isNaN(roomDto.getHeight()))
            room.setHeight(roomDto.getHeight());
        if (!Double.isNaN(roomDto.getLength()))
            room.setLength(roomDto.getLength());
        if (!Double.isNaN(roomDto.getWidth()))
            room.setWidth(roomDto.getWidth());
        roomRepository.save(room);
        return MapperRoom.toDto(room);
    }


    public List<RoomSensorDto> getListOfAllSensorsInARoomDto(String roomName) {
        Room room = getRoomByName(roomName);
        return MapperListOfRoomSensor.toDto(room.getListOfSensors()).getListOfRoomSensorDto();
    }

    public int createAndAddSensor(RoomSensorDto roomSensorDto, String roomName) {
        Room room = getRoomByName(roomName);
        int result = room.createAndAddRoomSensor(roomSensorDto);
        roomRepository.save(room);
        return result;
    }
}
