package pt.ipp.isep.dei.project1.controllers.house;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import pt.ipp.isep.dei.project1.dto.housedto.HouseGridDto;
import pt.ipp.isep.dei.project1.dto.housedto.ListOfHouseGridsDto;
import pt.ipp.isep.dei.project1.dto.housedto.ListOfRoomsDto;
import pt.ipp.isep.dei.project1.dto.housedto.RoomDto;
import pt.ipp.isep.dei.project1.mapper.MapperListOfHouseGrids;
import pt.ipp.isep.dei.project1.model.house.HouseGrid;
import pt.ipp.isep.dei.project1.model.house.Room;
import pt.ipp.isep.dei.project1.model.repositories.HouseGridRepo;
import pt.ipp.isep.dei.project1.services.RoomHouseGridService;
import pt.ipp.isep.dei.project1.model.repositories.RoomDomainService;
import java.util.List;


@Controller
public class AttachDetachRoomToHouseGridController {

    private final HouseGridRepo houseGridRepo;
    private final RoomDomainService roomDomainService;
    private final RoomHouseGridService roomHouseGridService;

    @Autowired
    public AttachDetachRoomToHouseGridController(HouseGridRepo houseGridRepo, RoomDomainService roomDomainService, RoomHouseGridService roomHouseGridService) {
        this.houseGridRepo = houseGridRepo;
        this.roomDomainService = roomDomainService;
        this.roomHouseGridService=roomHouseGridService;
    }

    public ListOfHouseGridsDto getListOfHouseGrids() {
        return MapperListOfHouseGrids.toDto(houseGridRepo.getListOfHouseGrids());
    }

    public List<String> getListOfRoomsAttachedToHouseGrid(HouseGridDto houseGridDto) {
        HouseGrid houseGrid = houseGridRepo.getHouseGridByCode(houseGridDto.getCode());
        return houseGrid.getRoomsList();
    }

    public ListOfRoomsDto getListOfRooms() {
        return roomDomainService.getRoomsWithoutHouseGridDTO();
    }

    public boolean attachRoomToHouseGrid(HouseGridDto houseGridDto, RoomDto roomDto) {
        HouseGrid houseGrid = houseGridRepo.getHouseGridByCode(houseGridDto.getCode());
        Room room = roomDomainService.getRoomByName(roomDto.getName());
        return (!houseGridRepo.getListOfHouseGrids().isEmpty() && roomHouseGridService.addRoomToHouseGrid(houseGrid,room));
    }

    public boolean detachRoomFromHouseGrid (HouseGridDto houseGridDto, String roomDto) {
        HouseGrid houseGrid = houseGridRepo.getHouseGridByCode(houseGridDto.getCode());
        Room room = roomDomainService.getRoomByName(roomDto);
        return (!houseGridRepo.getListOfHouseGrids().isEmpty() && roomHouseGridService.removeRoomFromHouseGrid(houseGrid,room));
    }



}
