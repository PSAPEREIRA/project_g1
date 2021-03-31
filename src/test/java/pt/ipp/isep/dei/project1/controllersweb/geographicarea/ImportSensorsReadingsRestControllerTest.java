package pt.ipp.isep.dei.project1.controllersweb.geographicarea;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import pt.ipp.isep.dei.project1.controllers.geographicarea.ImportSensorsReadingsController;
import pt.ipp.isep.dei.project1.model.repositories.RoomDomainService;
import java.io.File;

import static org.junit.Assert.assertEquals;

@ExtendWith(MockitoExtension.class)
class ImportSensorsReadingsRestControllerTest {

    @Mock
    private ImportSensorsReadingsRestController controller;
    @Mock
    private ImportSensorsReadingsController controllerModel;
    @Mock
    private RoomDomainService roomDomainService;


    @BeforeEach
    void initMocks() {
        this.controller = new ImportSensorsReadingsRestController(controllerModel, roomDomainService);

    }

    @Test
    void addReadingsToSensorsOfGA() throws Exception {

        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("DataSet_sprint07_GAData.json").getFile());
        String path = file.getAbsolutePath();

        ResponseEntity<Object> result = controller.addReadingsToSensorsOfGA(path);

        assertEquals(HttpStatus.OK, result.getStatusCode());
    }
}
