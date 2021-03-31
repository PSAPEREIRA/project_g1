package pt.ipp.isep.dei.project1.controllersweb.housegrid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pt.ipp.isep.dei.project1.dto.housedto.HouseGridDto;
import pt.ipp.isep.dei.project1.model.repositories.HouseGridRepo;

@RestController
@RequestMapping(value = "/house-grid-configuration")
@CrossOrigin(origins={"http://localhost:3000","http://smarthomeg1.azurewebsites.net"}, maxAge = 3600)
public class CreateAHouseGridRestController {

    private final HouseGridRepo houseGridRepo;

    @Autowired
    public CreateAHouseGridRestController(HouseGridRepo houseGridRepo) {this.houseGridRepo = houseGridRepo;
    }

    @PostMapping(value = "/create-house-grid")
    public ResponseEntity <Object> addNewHouseGrid(@RequestBody HouseGridDto newHouseGridDTO) {
        boolean result = houseGridRepo.newHouseGrid(newHouseGridDTO);
        if (result)
            return new ResponseEntity<>(newHouseGridDTO.getCode() + " added!", HttpStatus.CREATED);
        return new ResponseEntity<>("Failed to create!", HttpStatus.CONFLICT);
    }

}

