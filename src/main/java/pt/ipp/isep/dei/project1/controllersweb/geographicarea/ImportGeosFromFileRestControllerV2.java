package pt.ipp.isep.dei.project1.controllersweb.geographicarea;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pt.ipp.isep.dei.project1.controllers.geographicarea.ImportGeosFromFileControllerV2;

import java.util.List;


@RestController
@RequestMapping(value = "/geographic-area-configuration")
@CrossOrigin(origins={"http://localhost:3000","http://smarthomeg1.azurewebsites.net"}, maxAge = 3600)
public class ImportGeosFromFileRestControllerV2 {


    private final ImportGeosFromFileControllerV2 modelController;

    @Autowired
    public ImportGeosFromFileRestControllerV2(ImportGeosFromFileControllerV2 importGeosFromFileRestControllerV2) {
        this.modelController = importGeosFromFileRestControllerV2;
    }

    @PostMapping(value = "/import")
    public ResponseEntity<Object> importFromFile(@RequestBody String path) throws Exception {

        List<String> geoNames = modelController.importFromFile(path);

        return new ResponseEntity<>(geoNames, HttpStatus.OK);
    }

}