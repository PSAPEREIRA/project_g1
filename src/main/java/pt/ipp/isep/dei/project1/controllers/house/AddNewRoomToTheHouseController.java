package pt.ipp.isep.dei.project1.controllers.house;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import pt.ipp.isep.dei.project1.model.house.Room;
import pt.ipp.isep.dei.project1.model.repositories.RoomDomainService;

import java.util.List;

/**
 * US105 As an Administrator, I want to add a new room to the house, in order to configure
 * it (name, house floor and dimensions).
 */

@Controller
public class AddNewRoomToTheHouseController {

    private final RoomDomainService roomDomainService;

    @Autowired
    public AddNewRoomToTheHouseController(RoomDomainService roomDomainService) {
        this.roomDomainService = roomDomainService;
    }

    public boolean createNewRoom(String nameOfRoom, String description, double houseFloor, List<Double> dimensions) {
        Room newRoom = new Room(nameOfRoom, description, houseFloor, dimensions.get(0), dimensions.get(1), dimensions.get(2));
        return roomDomainService.addRoom(newRoom);
    }

}
