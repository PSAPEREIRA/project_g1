package pt.ipp.isep.dei.project1.controllersweb.geographicarea;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pt.ipp.isep.dei.project1.controllers.geographicarea.ImportSensorsReadingsController;
import pt.ipp.isep.dei.project1.model.repositories.RoomDomainService;

import javax.servlet.http.HttpServlet;

import java.util.List;

@RestController
@RequestMapping(value = "/geographic-area-configuration")
@CrossOrigin(origins={"http://localhost:3000","http://smarthomeg1.azurewebsites.net"}, maxAge = 3600)
public class ImportSensorsReadingsRestController extends HttpServlet {

    private final ImportSensorsReadingsController importSensorsReadingsController;

    @Autowired
    public ImportSensorsReadingsRestController(ImportSensorsReadingsController importSensorsReadingsController, RoomDomainService roomDomainService) {
        this.importSensorsReadingsController = importSensorsReadingsController;
    }

    @PostMapping(value = "/import-sensor-readings")
    public ResponseEntity<Object> addReadingsToSensorsOfGA(@RequestBody String path) throws Exception {
        List<Integer> list = importSensorsReadingsController.addReadingsToSensorsOfGA(path);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

}
