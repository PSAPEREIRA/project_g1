package pt.ipp.isep.dei.project1.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.ipp.isep.dei.project1.model.device.ListOfDevices;
import pt.ipp.isep.dei.project1.model.house.HouseGrid;
import pt.ipp.isep.dei.project1.model.house.Room;
import pt.ipp.isep.dei.project1.model.interfaces.Device;
import pt.ipp.isep.dei.project1.model.repositories.HouseGridRepo;
import pt.ipp.isep.dei.project1.model.repositories.RoomDomainService;
import pt.ipp.isep.dei.project1.model.sensor.ListOfReadings;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Service
public class RoomHouseGridService {

    private final HouseGridRepo houseGridRepo;
    private final RoomDomainService roomDomainService;

    @Autowired
    public RoomHouseGridService(HouseGridRepo houseGridRepo, RoomDomainService roomDomainService) {
        this.houseGridRepo = houseGridRepo;
        this.roomDomainService = roomDomainService;
    }

    public boolean addRoomToHouseGrid(HouseGrid houseGrid, Room roomToAdd) {
        if (!houseGrid.getRoomsList().contains(roomToAdd.getName())) {
            houseGrid.getRoomsList().add(roomToAdd.getName());
            roomToAdd.setHouseGrid(houseGrid.getCode());
            houseGridRepo.getHouseGridRepository().save(houseGrid);
            roomDomainService.getRoomRepository().save(roomToAdd);
            return true;
        } else
            return false;
    }

    public boolean addRoomToHouseGridIds(String hgCode, String roomName) {
        HouseGrid houseGrid = houseGridRepo.getHouseGridByCode(hgCode);
        Room roomToAdd = roomDomainService.getRoomByName(roomName);
        return addRoomToHouseGrid(houseGrid, roomToAdd);
    }

    public boolean removeRoomFromHouseGrid(HouseGrid houseGrid, Room roomToRemove) {
        if (houseGrid.getRoomsList().contains(roomToRemove.getName())) {
            houseGrid.getRoomsList().remove(roomToRemove.getName());
            roomToRemove.setHouseGrid("");
            houseGridRepo.getHouseGridRepository().save(houseGrid);
            roomDomainService.getRoomRepository().save(roomToRemove);
            return true;
        } else
            return false;
    }

    public boolean removeRoomFromHouseGridIds(String hgCode, String roomName) {
        HouseGrid houseGrid = houseGridRepo.getHouseGridByCode(hgCode);
        Room roomToRemove = roomDomainService.getRoomByName(roomName);
        return removeRoomFromHouseGrid(houseGrid, roomToRemove);
    }

    public List<Room> getRoomsConnectedToHouseGrid(List<String> roomsNames) {
        List<Room> roomConnectedToHG = new ArrayList<>();
        for (String name : roomsNames) {
            Room room = roomDomainService.getRoomByName(name);
            roomConnectedToHG.add(room);
        }
        return roomConnectedToHG;
    }

    public double getNominalPower(String houseGridCode) {
        HouseGrid houseGrid = houseGridRepo.getHouseGridByCode(houseGridCode);
        List<Room> listOfRooms = getRoomsConnectedToHouseGrid(houseGrid.getRoomsList());
        double nominalPower = 0;
        for (int roomIndex = 0; roomIndex < listOfRooms.size(); roomIndex++)
            nominalPower += listOfRooms.get(roomIndex).getNominalPower();
        return nominalPower;
    }

    public double getConsumption(LocalDateTime initialDate, LocalDateTime finalDate, String houseGridCode) throws Exception {
        HouseGrid houseGrid = houseGridRepo.getHouseGridByCode(houseGridCode);
        List<Room> listOfRooms = getRoomsConnectedToHouseGrid(houseGrid.getRoomsList());
        double consumption = 0;
        for (int roomIndex = 0; roomIndex < listOfRooms.size(); roomIndex++)
            consumption += listOfRooms.get(roomIndex).getConsumption(initialDate, finalDate);
        return consumption;
    }

    public ListOfReadings getDataSeries(LocalDateTime startDate, LocalDateTime endDate, String houseGridCod) {
        ListOfDevices listOfDevices = getAllDevicesFromListOfRooms(houseGridCod);
        return listOfDevices.getListOfReadingsOfAllDevicesInListOfDev().getReadingsOnIntervalCutDuplicates(startDate, endDate);
    }

    private ListOfDevices getAllDevicesFromListOfRooms(String houseGridCode) {
        HouseGrid houseGrid = houseGridRepo.getHouseGridByCode(houseGridCode);
        List<Room> listOfRooms = getRoomsConnectedToHouseGrid(houseGrid.getRoomsList());
        ListOfDevices listOfDevices = new ListOfDevices();
        for (int roomIndex = 0; roomIndex < listOfRooms.size(); roomIndex++)
            listOfDevices.getDeviceList().addAll(listOfRooms.get(roomIndex).getListOfDevices().getDeviceList());
        return listOfDevices;
    }

    public List<ListOfDevices> getListOfDeviceWaterHeatersInHouse() {
        List<ListOfDevices> listOfDevices = new ArrayList<>();
        for (int houseGridListIndex = 0; houseGridListIndex < houseGridRepo.getListOfHouseGrids().size(); houseGridListIndex++) {
            HouseGrid houseGrid = houseGridRepo.getListOfHouseGrids().get(houseGridListIndex);
            listOfDevices.addAll(getWaterHeaterDevicesThroughHouseGrids(houseGrid.getCode()));
        }
        return listOfDevices;
    }

    public List<ListOfDevices> getWaterHeaterDevicesThroughHouseGrids(String houseGridCode) {
        HouseGrid houseGrid = houseGridRepo.getHouseGridByCode(houseGridCode);
        List<Room> listOfRooms = getRoomsConnectedToHouseGrid(houseGrid.getRoomsList());
        List<ListOfDevices> listOfWaterHeaters = new ArrayList<>();
        for (Room room : listOfRooms)
            listOfWaterHeaters.add(room.listOfWaterHeatersSelection());
        return listOfWaterHeaters;
    }

    public Room getRoomByName(String roomName, String hgCode) {
        HouseGrid houseGrid = houseGridRepo.getHouseGridByCode(hgCode);
        List<Room> listOfRooms = getRoomsConnectedToHouseGrid(houseGrid.getRoomsList());
        for (Room room : listOfRooms)
            if (room.getName().equals(roomName))
                return room;
        return null;
    }

    public ListOfDevices getListOfDevicesOnRoomAttachedToHouseGrid(String room) {
        Room room1 = roomDomainService.getRoomByName(room);
        return room1.getListOfDevices();
    }

    public Device getDeviceFromRoom(String code, int index, int j) {
        HouseGrid houseGrid = houseGridRepo.getHouseGridByCode(code);
        String roomName = houseGrid.getRoomsList().get(index);
        Room room = roomDomainService.getRoomByName(roomName);
        return room.getListOfDevices().getDeviceList().get(j);
    }


}