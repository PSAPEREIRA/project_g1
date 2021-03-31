package pt.ipp.isep.dei.project1.controllers.house;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import pt.ipp.isep.dei.project1.dto.housedto.ListOfRoomsDto;
import pt.ipp.isep.dei.project1.dto.housedto.RoomDto;
import pt.ipp.isep.dei.project1.model.repositories.HouseDomainService;
import pt.ipp.isep.dei.project1.services.RoomGeoAreaService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Controller
public class RoomComfortLevelCTRL {

    private final HouseDomainService houseDomainService;
    private final RoomGeoAreaService roomGeoAreaService;

    @Autowired
    public RoomComfortLevelCTRL(RoomGeoAreaService roomGeoAreaService, HouseDomainService houseDomainService){
        this.roomGeoAreaService=roomGeoAreaService;
        this.houseDomainService = houseDomainService;
    }

    public boolean checkGeographicAreaOfTheHouse() {
        return houseDomainService.getGeographicAreaID() == null;
    }

    public ListOfRoomsDto getListOfRoomsDto(){
        return roomGeoAreaService.getListOfRoomsDto();
    }

    public boolean checkTemperatureSensorsOfHouseArea() {
        return roomGeoAreaService.checkTemperatureSensorsOfHouseArea(houseDomainService.getGeographicAreaID(), houseDomainService.getLocation());
    }

    public List<LocalDateTime> getInstancesWithTemperatureHigherLowerComfortLevel(RoomDto roomOption, int cat, String option, LocalDate startDate, LocalDate finalDate){
        return roomGeoAreaService.getInstancesWithTemperatureHigherLowerComfortLevel(roomOption,cat,option,startDate,finalDate, houseDomainService.getGeographicAreaID(), houseDomainService.getLocation());
    }

    public boolean checkTemperatureSensorsOfRoom(RoomDto roomDto) {
        return roomGeoAreaService.checkTemperatureSensorsOfRoom(roomDto);
    }

}
