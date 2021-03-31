package pt.ipp.isep.dei.project1.controllersweb.room;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import pt.ipp.isep.dei.project1.controllers.geographicarea.ImportSensorsReadingsController;
import pt.ipp.isep.dei.project1.model.repositories.GeographicAreaDomainService;
import pt.ipp.isep.dei.project1.model.repositories.GeographicAreaRepository;
import pt.ipp.isep.dei.project1.model.repositories.RoomDomainService;
import pt.ipp.isep.dei.project1.model.repositories.RoomRepository;

import java.io.File;
import java.net.URLDecoder;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ImportSensorsReadingsOfRoomRestControllerTest {

    @Mock
    private ImportSensorsReadingsOfRoomRestController controller;
    @Mock
    private RoomDomainService roomDomainService;
    @Mock
    private RoomRepository roomRepository;
    @Mock
    private ImportSensorsReadingsController controllerModel;
    @Mock
    private GeographicAreaDomainService geographicAreaDomainService;
    @Mock
    private GeographicAreaRepository geographicAreaRepository;

    @BeforeEach
    void initMocks() throws Exception {
        this.roomDomainService = new RoomDomainService(roomRepository);
        this.geographicAreaDomainService = new GeographicAreaDomainService(geographicAreaRepository);
        this.controllerModel = new ImportSensorsReadingsController(geographicAreaDomainService, roomDomainService);
        this.controller = new ImportSensorsReadingsOfRoomRestController(controllerModel,roomDomainService);
    }

    @Test
    void addReadingsToSensorsOfGA() throws Exception {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("DataSet_sprint07_GAData.json").getFile());
        String path = file.getAbsolutePath();

        ResponseEntity<Object> result = controller.addReadingsToSensorsOfGA(URLDecoder.decode(path, "UTF-8"));

        assertEquals(HttpStatus.OK, result.getStatusCode());
    }
}