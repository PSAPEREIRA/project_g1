package pt.ipp.isep.dei.project1.controllersweb.room;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pt.ipp.isep.dei.project1.controllers.geographicarea.ImportSensorsReadingsController;
import pt.ipp.isep.dei.project1.model.repositories.RoomDomainService;

import java.util.List;

@RestController
@RequestMapping(value = "/room-configuration")
@CrossOrigin(origins={"http://localhost:3000","http://smarthomeg1.azurewebsites.net"}, maxAge = 3600)
public class ImportSensorsReadingsOfRoomRestController {


    private final ImportSensorsReadingsController importSensorsReadingsController;

    @Autowired
    public ImportSensorsReadingsOfRoomRestController(ImportSensorsReadingsController importSensorsReadingsController, RoomDomainService roomDomainService) {
        this.importSensorsReadingsController = importSensorsReadingsController;
    }

    @PostMapping(value = "/import-sensor-readings")
    public ResponseEntity<Object> addReadingsToSensorsOfGA(@RequestBody String path) throws Exception {
        List<Integer> list = importSensorsReadingsController.addReadingsToSensorsOfHouse(path);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
}
