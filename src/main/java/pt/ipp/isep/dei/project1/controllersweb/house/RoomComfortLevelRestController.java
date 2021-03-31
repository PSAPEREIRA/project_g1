package pt.ipp.isep.dei.project1.controllersweb.house;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pt.ipp.isep.dei.project1.dto.housedto.RoomDto;
import pt.ipp.isep.dei.project1.model.repositories.HouseDomainService;
import pt.ipp.isep.dei.project1.model.repositories.RoomDomainService;
import pt.ipp.isep.dei.project1.services.RoomGeoAreaService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping(value = "/house-management")
@CrossOrigin(origins={"http://localhost:3000","http://smarthomeg1.azurewebsites.net"}, maxAge = 3600)
public class RoomComfortLevelRestController {

    private final HouseDomainService houseDomainService;
    private final RoomGeoAreaService roomGeoAreaService;
    private final RoomDomainService roomDomainService;

    @Autowired
    public RoomComfortLevelRestController(RoomGeoAreaService roomGeoAreaService, HouseDomainService houseDomainService, RoomDomainService roomDomainService) {
        this.roomGeoAreaService = roomGeoAreaService;
        this.houseDomainService = houseDomainService;
        this.roomDomainService = roomDomainService;
    }


    @GetMapping(value = "/comfort-level/{roomName}/{cat}/{option}")
    public ResponseEntity<Object> getInstancesWithTemperatureHigherLowerComfortLevel(@PathVariable String roomName, @PathVariable int cat, @PathVariable String option, @RequestParam(name = "s") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate, @RequestParam(name = "e") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate finalDate) {
        RoomDto roomDto = roomDomainService.getRoomByNameDto(roomName);
        if (roomDto == null)
            return new ResponseEntity<>(("The name of the room is not acceptable!"), HttpStatus.NOT_FOUND);
            /**else if (!roomGeoAreaService.checkTemperatureSensorsOfHouseArea(houseDomainService.getGeographicAreaID(), houseDomainService.getLocation()))
             return new ResponseEntity<>(("There are no house area sensors!"), HttpStatus.NOT_FOUND);**/
        else if (!roomDomainService.checkTemperatureSensorsOfRoomV2(roomName))
            return new ResponseEntity<>(("There are no room sensors!"), HttpStatus.NOT_FOUND);
        List<LocalDateTime> result = roomGeoAreaService.getInstancesWithTemperatureHigherLowerComfortLevel(roomDto, cat, option, startDate, finalDate, houseDomainService.getGeographicAreaID(), houseDomainService.getLocation());


        if(result.isEmpty()){
            List<String> errMsg = new ArrayList<>();
            errMsg.add("No readings registered on the selected period");
            return new ResponseEntity<>((errMsg), HttpStatus.OK);
        }

        return new ResponseEntity<>((result), HttpStatus.OK);
    }

}
