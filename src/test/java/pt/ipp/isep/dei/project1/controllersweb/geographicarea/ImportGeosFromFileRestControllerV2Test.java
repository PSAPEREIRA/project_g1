package pt.ipp.isep.dei.project1.controllersweb.geographicarea;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import pt.ipp.isep.dei.project1.controllers.geographicarea.ImportGeosFromFileControllerV2;

import java.io.File;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class ImportGeosFromFileRestControllerV2Test {

    @Mock
    private ImportGeosFromFileRestControllerV2 controllerV2;

    @Mock
    private ImportGeosFromFileControllerV2 controllerModel;

    @BeforeEach
    void initMocks() {
        this.controllerV2 = new ImportGeosFromFileRestControllerV2(controllerModel);
    }

    @Test
    void importFromFile() throws Exception {

        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("DataSet_sprint07_GA.json").getFile());
        String path = file.getAbsolutePath();

        ResponseEntity<Object> result = controllerV2.importFromFile(URLDecoder.decode(path, "UTF-8"));

        assertEquals(HttpStatus.OK, result.getStatusCode());
    }
}