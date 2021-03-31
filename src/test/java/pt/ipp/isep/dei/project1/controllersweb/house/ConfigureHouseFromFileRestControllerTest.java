package pt.ipp.isep.dei.project1.controllersweb.house;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import pt.ipp.isep.dei.project1.controllers.house.ConfigureHouseFromFileController;
import pt.ipp.isep.dei.project1.controllersweb.geographicarea.ImportGeosFromFileRestControllerV2;
import pt.ipp.isep.dei.project1.io.ui.house.ConfigureHouseFromFileUI;

import java.io.File;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ConfigureHouseFromFileRestControllerTest {

    @Mock
    private ConfigureHouseFromFileRestController ctrllr;

    @Mock
    private ConfigureHouseFromFileController ctrllrModel;

    @BeforeEach
    void initMocks() {
        this.ctrllr = new ConfigureHouseFromFileRestController(ctrllrModel);
    }

    @Test
    void importHouseFromFile() throws Exception {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("DataSet_sprint06_House.json").getFile());
        String path = file.getAbsolutePath();

        ResponseEntity<Object> result = ctrllr.importHouseFromFile(URLDecoder.decode(path, "UTF-8"));

        assertEquals(HttpStatus.OK, result.getStatusCode());
    }
}