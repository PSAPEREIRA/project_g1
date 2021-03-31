package pt.ipp.isep.dei.project1.controllers.house;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import pt.ipp.isep.dei.project1.model.house.*;
import pt.ipp.isep.dei.project1.model.repositories.HouseGridRepo;
import pt.ipp.isep.dei.project1.model.repositories.HouseDomainService;
import pt.ipp.isep.dei.project1.services.RoomHouseGridService;
import pt.ipp.isep.dei.project1.model.repositories.RoomDomainService;
import pt.ipp.isep.dei.project1.readers.FileReader;
import pt.ipp.isep.dei.project1.readers.readerjson.*;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import static pt.ipp.isep.dei.project1.io.ui.utils.Configurations.importPackFromProperties;

@Controller
public class ConfigureHouseFromFileController {

    private HouseFromJsonFile mHouseFromJsonFile;

    private final HouseDomainService houseDomainService;
    private final RoomDomainService roomDomainService;
    private final HouseGridRepo houseGridRepo;
    private final RoomHouseGridService roomHouseGridService;
    private static final String MENU = "-------------------------------------------------";

    @Autowired
    public ConfigureHouseFromFileController(RoomDomainService roomDomainService, HouseGridRepo houseGridRepo, HouseDomainService houseDomainService, RoomHouseGridService roomHouseGridService) {
        this.houseGridRepo = houseGridRepo;
        this.roomDomainService = roomDomainService;
        this.roomHouseGridService = roomHouseGridService;
        this.houseDomainService = houseDomainService;
    }

    public List<FileReader> getAvailableReaderTypesObjects() throws Exception {

        List<FileReader> fileReaderList = new ArrayList<>();
        List<String> readersList = importPackFromProperties("readerOfGaExtensions");
        for (int i = 0; i < readersList.size(); i++) {
            FileReader fileReaderObject = (FileReader) Class.forName(readersList.get(i)).newInstance();
            fileReaderList.add(fileReaderObject);
        }
        return fileReaderList;
    }

    public List<String> importHouseFromFile(String path) throws Exception {

        List<String> outPut = new ArrayList<>();

        List<FileReader> fileReadersObjects = getAvailableReaderTypesObjects();

        for (FileReader fR : fileReadersObjects) {

            try {
                this.mHouseFromJsonFile = fR.importHouseFromInputPath(path);
                createHouseFromFile();
                /**checkIfHouseImported(fR);**/
                outPut.add(fR.getReaderType() + " file read with success!");

                Address houseAddress = houseDomainService.getAddress();
                List<Room> roomList = roomDomainService.getListOfRooms();
                List<HouseGrid> gridList = houseGridRepo.getListOfHouseGrids();

                outPut.add(MENU);
                outPut.add("Information read by imported file:");
                outPut.add(MENU);
                outPut.add("Address:");
                outPut.add("Street: " + houseAddress.getStreet());
                outPut.add("Number: " + houseAddress.getNumber());
                outPut.add("Zip: " + houseAddress.getZip());
                outPut.add("Town: " + houseAddress.getTown());
                outPut.add("Country: " + houseAddress.getCountry());
                outPut.add(MENU);
                outPut.add(MENU);
                outPut.add("Rooms:");
                for (Room r : roomList) {
                    outPut.add(r.getName());
                }
                outPut.add(MENU);
                outPut.add("Power Grids:");
                for (HouseGrid g : gridList) {
                    outPut.add(g.getCode());
                }
                outPut.add(MENU);
            } catch (NullPointerException g) {
                g.getSuppressed();
            } catch (FileNotFoundException e) {
                outPut.add(MENU);
                outPut.add("Invalid path/file!");
                outPut.add("Please insert a valid file name and path.");
                outPut.add(MENU);
            } catch (JsonParseException g) {
                outPut.add(MENU);
                outPut.add("This functionality only can read Json files!");
                outPut.add("Please insert a valid file name and path.");
                outPut.add(MENU);
            } catch (UnrecognizedPropertyException f) {
                outPut.add("The data from the file you are importing is not compatible with this function");
            }
        }
        return outPut;
    }

    private void createHouseFromFile() {

        Address addressImported = createAddressFromHouseFile();
        createListOfRoomsFromHouseFile();
        createListOfHouseGridsFromHouseFile();
        House house = new House();
        house.setNameOfHouse("The House");
        house.setAddress(addressImported);
        houseDomainService.add(house);
        createRoomsInGrid();

    }

    private Address createAddressFromHouseFile() {

        return new Address(mHouseFromJsonFile.getAddress().getStreet(), mHouseFromJsonFile.getAddress().getZip(),
                mHouseFromJsonFile.getAddress().getTown(), mHouseFromJsonFile.getAddress().getNumber(),
                mHouseFromJsonFile.getAddress().getCountry());
    }


    private void createListOfRoomsFromHouseFile() {
        List<RoomFromJsonFile> roomFromJsonFileList = mHouseFromJsonFile.getListOfRooms();
        for (RoomFromJsonFile roomFromJsonFile : roomFromJsonFileList) {
            Room importedRoom = new Room(roomFromJsonFile.getId(), roomFromJsonFile.getDescription(), roomFromJsonFile.getFloor(),
                    roomFromJsonFile.getLength(), roomFromJsonFile.getWidth(), roomFromJsonFile.getHeight());
            roomDomainService.addRoom(importedRoom);
        }
    }

    private void createListOfHouseGridsFromHouseFile() {
        List<HouseGridFromJsonFile> houseGridFromJsonFileList = mHouseFromJsonFile.getListOfHouseGrids();
        for (HouseGridFromJsonFile houseGridFromJsonFile : houseGridFromJsonFileList) {
            HouseGrid importedHouseGrid = new HouseGrid(houseGridFromJsonFile.getName());
            houseGridRepo.addHouseGrid(importedHouseGrid);
        }

    }

    private void createRoomsInGrid() {
        for (HouseGrid houseGrid : houseGridRepo.getListOfHouseGrids())
            for (HouseGridFromJsonFile houseGridFromJsonFile : mHouseFromJsonFile.getListOfHouseGrids())
                if (houseGrid.getCode().equalsIgnoreCase(houseGridFromJsonFile.getName())) {
                    List<String> listOfRoomsByName = houseGridFromJsonFile.getListOfRoomsByName();
                    addRoomToGrid(houseGrid, listOfRoomsByName);
                }
    }

    private void addRoomToGrid(HouseGrid houseGrid, List<String> list) {
        for (String roomName : list)
            for (Room room : roomDomainService.getListOfRooms())
                if (roomName.equalsIgnoreCase(room.getName())) {
                    roomHouseGridService.addRoomToHouseGrid(houseGrid,room);
                    room.setHouseGrid(houseGrid.getCode());
                    roomDomainService.getRoomRepository().save(room);
                }
    }
}

