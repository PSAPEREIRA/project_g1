package pt.ipp.isep.dei.project1.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.ipp.isep.dei.project1.dto.housedto.ListOfRoomsDto;
import pt.ipp.isep.dei.project1.dto.housedto.RoomDto;
import pt.ipp.isep.dei.project1.model.geographicarea.Location;
import pt.ipp.isep.dei.project1.model.repositories.GeographicAreaDomainService;
import pt.ipp.isep.dei.project1.model.repositories.RoomDomainService;
import pt.ipp.isep.dei.project1.model.sensor.SensorType;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class RoomGeoAreaService {

    private final GeographicAreaDomainService geographicAreaDomainService;
    private final RoomDomainService roomDomainService;

    @Autowired
    public RoomGeoAreaService(GeographicAreaDomainService geographicAreaDomainService, RoomDomainService roomDomainService) {
        this.geographicAreaDomainService = geographicAreaDomainService;
        this.roomDomainService = roomDomainService;
    }

    public ListOfRoomsDto getListOfRoomsDto(){
        return roomDomainService.getRoomsDTO();
    }

    public boolean checkTemperatureSensorsOfHouseArea(String geoAreaOfHouse, Location locationOfTheHouse) {
        return geographicAreaDomainService.checkIfHaveSensorsOnHouseAreaWithTypeAndReadings(geoAreaOfHouse, locationOfTheHouse, new SensorType("Temperature"));
    }

    public List<LocalDateTime> getInstancesWithTemperatureHigherLowerComfortLevel(RoomDto roomOption, int cat, String option, LocalDate startDate, LocalDate finalDate, String geoAreaOfHouse, Location locationOfTheHouse){
        List<Double> temperatureOnHouseArea = geographicAreaDomainService.getAverageDailyTemperatureInHouseArea(geoAreaOfHouse,locationOfTheHouse,startDate,finalDate);
        return roomDomainService.getInstancesWithTemperatureHigherLowerComfort(roomOption,temperatureOnHouseArea,cat,option,startDate);
    }

    public boolean checkTemperatureSensorsOfRoom(RoomDto roomDto) {
        return roomDomainService.checkTemperatureSensorsOfRoom(roomDto);
    }
}
