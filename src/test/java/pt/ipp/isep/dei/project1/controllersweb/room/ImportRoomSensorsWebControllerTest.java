package pt.ipp.isep.dei.project1.controllersweb.room;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import pt.ipp.isep.dei.project1.controllers.house.ImportRoomSensorsFromFileController;
import pt.ipp.isep.dei.project1.model.repositories.RoomDomainService;
import pt.ipp.isep.dei.project1.model.repositories.RoomRepository;

import java.io.File;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ImportRoomSensorsWebControllerTest {

    @Mock
    private ImportRoomSensorsWebController controller;

    @Mock
    private ImportRoomSensorsFromFileController controllerModel;

    @Mock
    private RoomDomainService roomDomainService;

    @Mock
    private RoomRepository roomRepository;


    @BeforeEach
    void initMocks() throws Exception {
        this.roomDomainService = new RoomDomainService(roomRepository);
        this.controller = new ImportRoomSensorsWebController(roomDomainService, controllerModel);
    }

    @Test
    void importRoomSensors() throws Exception {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("DataSet_sprint07_HouseSensors.json").getFile());
        String path = file.getAbsolutePath();

        ResponseEntity<Object> result = controller.importRoomSensors(URLDecoder.decode(path, "UTF-8"));

        assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    @Test
    void importRoomSensorsInvalidPath() throws Exception{
        ResponseEntity result = controller.importRoomSensors(URLDecoder.decode("invalidPath", "UTF-8"));
        List<String[]> badPath = new ArrayList<>();
        String[] msg = new String[]{"Invalid path! ", " File not found"};
        badPath.add(msg);
        badPath.add(new String[]{"Please insert a valid path"});
        ResponseEntity expectedResult = new ResponseEntity<>(badPath, HttpStatus.OK);
        assertEquals(expectedResult.getStatusCode(),result.getStatusCode());
    }

    @Test
    void importRoomSensorsInvalidJsonFile() throws Exception{
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("DataSet_sprint06_House.json").getFile());
        String path = file.getAbsolutePath();
        List<String[]> wrongJsonFile = new ArrayList<>();
        String[] msg = new String[]{"Selected Json file is not valid!"};
        wrongJsonFile.add(msg);
        ResponseEntity expectedResult = new ResponseEntity<>(wrongJsonFile, HttpStatus.OK);
        ResponseEntity<Object> result = controller.importRoomSensors(URLDecoder.decode(path, "UTF-8"));
        assertEquals(expectedResult.getStatusCode(),result.getStatusCode());
    }

    @Test
    void importRoomSensorsInvalidFile() throws Exception{
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("DataSet_sprint05_GA.xml").getFile());
        String path = file.getAbsolutePath();
        List<String[]> wrongFileType = new ArrayList<>();
        String[] msg = new String[]{"Unexpected character read, verify and insert a valid file"};
        wrongFileType.add(msg);
        ResponseEntity expectedResult = new ResponseEntity<>(wrongFileType, HttpStatus.OK);
        ResponseEntity<Object> result = controller.importRoomSensors(URLDecoder.decode(path, "UTF-8"));
        assertEquals(expectedResult.getStatusCode(),result.getStatusCode());
    }

}