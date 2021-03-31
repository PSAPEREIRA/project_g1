package pt.ipp.isep.dei.project1.controllersweb.housegrid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pt.ipp.isep.dei.project1.controllersweb.room.RoomRestController;
import pt.ipp.isep.dei.project1.dto.housedto.HouseGridDto;
import pt.ipp.isep.dei.project1.model.repositories.HouseGridRepo;
import java.util.List;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@RequestMapping(value = "/house-grid-configuration")
@CrossOrigin(origins={"http://localhost:3000","http://smarthomeg1.azurewebsites.net"}, maxAge = 3600)
public class HouseGridRestController {

    private final HouseGridRepo houseGridRepo;

    @Autowired
    public HouseGridRestController(HouseGridRepo houseGridRepo) {
        this.houseGridRepo = houseGridRepo;
    }

    @GetMapping(value = "/{house-grid-code}")
    public ResponseEntity<Object> getHouseGrid(@PathVariable("house-grid-code") String code) {
        HouseGridDto houseGridDto = houseGridRepo.getHouseGridByCodeDto(code);
        if(houseGridDto==null)
            return new ResponseEntity<>("House Grid " + code + "not found!",HttpStatus.NOT_FOUND);
        List<String> list = houseGridDto.getRoomList();
        for (String roomName : list) {
            Link selfLink = linkTo(methodOn(RoomRestController.class).getRoom(roomName)).withSelfRel();
            houseGridDto.add(selfLink);
        }
        return new ResponseEntity<>(houseGridDto, HttpStatus.OK);
    }
}
