package pt.ipp.isep.dei.project1.controllers.device;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import pt.ipp.isep.dei.project1.dto.housedto.HouseGridDto;
import pt.ipp.isep.dei.project1.dto.housedto.ListOfHouseGridsDto;
import pt.ipp.isep.dei.project1.dto.housedto.ListOfRoomsDto;
import pt.ipp.isep.dei.project1.mapper.MapperListOfHouseGrids;
import pt.ipp.isep.dei.project1.mapper.MapperListOfRooms;
import pt.ipp.isep.dei.project1.model.device.ListOfDevices;
import pt.ipp.isep.dei.project1.model.device.eletricwaterheater.ElectricWaterHeater;
import pt.ipp.isep.dei.project1.model.house.HouseGrid;
import pt.ipp.isep.dei.project1.model.repositories.HouseGridRepo;
import pt.ipp.isep.dei.project1.services.RoomHouseGridService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Controller
public class WaterHeatersEstimatedPowerConsumptionController {

    private final HouseGridRepo houseGridRepo;
    private final RoomHouseGridService roomHouseGridService;

    @Autowired
    public WaterHeatersEstimatedPowerConsumptionController(HouseGridRepo houseGridRepo,RoomHouseGridService roomHouseGridService) {
        this.houseGridRepo = houseGridRepo;
        this.roomHouseGridService = roomHouseGridService;
    }

    private List<ListOfDevices> electricWaterHeatersListCreation() {

        return roomHouseGridService.getListOfDeviceWaterHeatersInHouse();

/**        int count = 0;
        for (int waterHeaterIndex = 0; waterHeaterIndex < listOfWaterHeaters.size(); waterHeaterIndex++) {
            if (listOfWaterHeaters.get(waterHeaterIndex).getDeviceList().isEmpty())
                count++;
        }
        if (count == listOfWaterHeaters.size())
            return Collections.emptyList();
        return listOfWaterHeaters;*/
    }

    public boolean checkElectricWaterHeatersListCreation (){
        return electricWaterHeatersListCreation().equals(Collections.emptyList());
    }

    public Double electricHeatingWaterDevicesDayConsumptionEstimation(List<Double> specificAttributeValues) {
        List<Double> auxList = new ArrayList<>();
        auxList.add(specificAttributeValues.get(1));
        auxList.add(specificAttributeValues.get(2));
        specificAttributeValues.set(1,Collections.max(auxList));
        specificAttributeValues.set(2,Collections.min(auxList));
        double estimatedEnergyConsumption = 0;
        List<ListOfDevices> listOfWaterHeaters = electricWaterHeatersListCreation();
        if (listOfWaterHeaters.equals(Collections.emptyList()))
            return -1.0;
        else {
            for (int i = 0; i < listOfWaterHeaters.size(); i++)
                for (int deviceListIndex = 0; deviceListIndex < listOfWaterHeaters.get(i).getDeviceList().size(); deviceListIndex++)
                    estimatedEnergyConsumption += ((ElectricWaterHeater)listOfWaterHeaters.get(i).getDeviceList().get(deviceListIndex)).getEstimatedConsumption(specificAttributeValues) * 24;
            return estimatedEnergyConsumption;
        }
    }

    public ListOfRoomsDto getListOfRooms (HouseGridDto houseGridDto){
        HouseGrid houseGrid = houseGridRepo.getHouseGridByCode(houseGridDto.getCode());
        return MapperListOfRooms.toDto(roomHouseGridService.getRoomsConnectedToHouseGrid(houseGrid.getRoomsList()));
    }

    public ListOfHouseGridsDto getListOfHouseGrid() {
        return MapperListOfHouseGrids.toDto(houseGridRepo.getListOfHouseGrids());
    }

}