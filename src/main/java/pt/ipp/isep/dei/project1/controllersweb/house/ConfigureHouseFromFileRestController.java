package pt.ipp.isep.dei.project1.controllersweb.house;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pt.ipp.isep.dei.project1.controllers.house.ConfigureHouseFromFileController;

import java.util.List;


@RestController
@RequestMapping(value = "/house-configuration")
@CrossOrigin(origins={"http://localhost:3000","http://smarthomeg1.azurewebsites.net"}, maxAge = 3600)
public class ConfigureHouseFromFileRestController {

    private final ConfigureHouseFromFileController controller;

    @Autowired
    public ConfigureHouseFromFileRestController(ConfigureHouseFromFileController controllerPar) {
        this.controller = controllerPar;
    }

    @PostMapping(value = "/import")
    public ResponseEntity<Object> importHouseFromFile(@RequestBody String path) throws Exception {

        List<String> outPut = controller.importHouseFromFile(path);

        return new ResponseEntity<>(outPut, HttpStatus.OK);
    }

}

