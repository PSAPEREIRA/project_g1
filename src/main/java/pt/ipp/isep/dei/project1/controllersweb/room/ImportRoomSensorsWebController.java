package pt.ipp.isep.dei.project1.controllersweb.room;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pt.ipp.isep.dei.project1.controllers.house.ImportRoomSensorsFromFileController;
import pt.ipp.isep.dei.project1.model.house.Room;
import pt.ipp.isep.dei.project1.model.repositories.RoomDomainService;
import pt.ipp.isep.dei.project1.readers.readerjson.RoomSensorsFromJson;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping(value = "/room-configuration")
@CrossOrigin(origins={"http://localhost:3000","http://smarthomeg1.azurewebsites.net"}, maxAge = 3600)
public class ImportRoomSensorsWebController {

    private final RoomDomainService roomDomainService;
    private final ImportRoomSensorsFromFileController controller;

    @Autowired
    public ImportRoomSensorsWebController(RoomDomainService roomDomainService, ImportRoomSensorsFromFileController controller) {
        this.roomDomainService = roomDomainService;
        this.controller = controller;
    }

    @PostMapping(value = "/import-sensors")
    public ResponseEntity<Object> importRoomSensors(@RequestBody String path) throws Exception {
        /**List<String[]> list = new ArrayList<>();**/
        try {
            List<Room> listOfRooms;
            ObjectMapper objectMapper = new ObjectMapper();
            File file = new File(path);
            RoomSensorsFromJson roomSensorsFromJson;
            roomSensorsFromJson = objectMapper.readValue(file, RoomSensorsFromJson.class);
            listOfRooms = roomDomainService.getListOfRooms();
            List<String[]> result = controller.setSensorsToRooms(listOfRooms, roomSensorsFromJson);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (FileNotFoundException e) {
            List<String[]> badPath = new ArrayList<>();
            String[] msg = new String[]{"Invalid path! ", " File not found"};
            badPath.add(msg);
            badPath.add(new String[]{"Please insert a valid path"});
            return new ResponseEntity<>(badPath, HttpStatus.OK);

        } catch (UnrecognizedPropertyException e) {
            List<String[]> wrongJsonFile = new ArrayList<>();
            String[] msg = new String[]{"Selected Json file is not valid!"};
            wrongJsonFile.add(msg);
            return new ResponseEntity<>(wrongJsonFile, HttpStatus.OK);
        }
        catch (JsonParseException e) {
            List<String[]> wrongFileType = new ArrayList<>();
            String[] msg = new String[]{"Unexpected character read, verify and insert a valid file"};
            wrongFileType.add(msg);
            return new ResponseEntity<>(wrongFileType, HttpStatus.OK);
        }
        /**  try {
         listOfRooms = roomDomainService.getListOfRooms();
         } catch (NullPointerException e) {
         String[] string = new String[2];                         Desde que se comecou a usar roomService já não se apanha esta exception
         string[0] = "The House doesn't have rooms yet. ";
         string[1] = "Please add rooms first!";
         list.add(string);
         return list;
         }**/
    }
}

