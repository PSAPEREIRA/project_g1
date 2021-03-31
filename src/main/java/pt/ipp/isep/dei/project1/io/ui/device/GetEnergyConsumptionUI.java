package pt.ipp.isep.dei.project1.io.ui.device;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pt.ipp.isep.dei.project1.controllers.device.GetEnergyConsumptionController;
import pt.ipp.isep.dei.project1.dto.device.ListOfDevicesDto;
import pt.ipp.isep.dei.project1.dto.housedto.ListOfHouseGridsDto;
import pt.ipp.isep.dei.project1.dto.housedto.ListOfRoomsDto;
import pt.ipp.isep.dei.project1.io.ui.utils.Configurations;
import pt.ipp.isep.dei.project1.io.ui.utils.Validations;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import static pt.ipp.isep.dei.project1.io.ui.utils.Validations.verifyDateLimits;
import static pt.ipp.isep.dei.project1.io.ui.utils.Validations.verifyDateWithFile;


@Component
public class GetEnergyConsumptionUI {

    /**
     * UI for US720,US721,US722
     */
    @Autowired
    private GetEnergyConsumptionController mGetEnergyConsumptionController;
    private static final String NO_READINGS_PERIOD = "No readings on selected period.\n";
    private static final String UNIT = " kWh.";


    /**
     * @throws Exception
     */
    public void runUS720() throws Exception {
        ListOfRoomsDto listOfRooms = mGetEnergyConsumptionController.getListOfRooms();
        if (Validations.printRoomListAsMenu(listOfRooms)) {
            int roomChosen = Validations.verifyIntegerInputsWithLimits(0, listOfRooms.getRoomDto().size() - 1);
            ListOfDevicesDto listOfDevices = mGetEnergyConsumptionController.getListOfDevicesFromRoom(listOfRooms.getRoomDto().get(roomChosen));
            if (Validations.printDeviceListAsMenu(listOfDevices)) {
                int deviceChosen = Validations.verifyIntegerInputsWithLimits(0, listOfDevices.getListOfDevices().size() - 1);
                List<LocalDateTime> readingList = insertDates(0);
                double result = mGetEnergyConsumptionController.getEnergyConsumptionOnOneDevice(listOfRooms.getRoomDto().get(roomChosen), listOfDevices.getListOfDevices().get(deviceChosen), readingList.get(0), readingList.get(1));
                if (result <= 0)
                    System.out.println(NO_READINGS_PERIOD);
                else {
                    System.out.println("The total energy consumption of the device chosen is: " + Math.round(result) + UNIT);
                    System.out.println("\n");
                }
            }
        }
    }

    /**
     * @throws Exception
     */
    public void runUS721() throws Exception {
        ListOfRoomsDto listOfRooms = mGetEnergyConsumptionController.getListOfRooms();
        if (Validations.printRoomListAsMenu(listOfRooms)) {
            int roomChosen = Validations.verifyIntegerInputsWithLimits(0, listOfRooms.getRoomDto().size() - 1);
            List<LocalDateTime> readingList = insertDates(0);
            double result = mGetEnergyConsumptionController.getEnergyConsumptionOnOneRoom(listOfRooms.getRoomDto().get(roomChosen), readingList.get(0), readingList.get(1));
            if (result <= 0)
                System.out.println(NO_READINGS_PERIOD);
            else {
                System.out.println("The total energy consumption of the room chosen is: " + Math.round(result) + UNIT);
                System.out.println("\n");
            }
        }
    }

    /**
     * @throws Exception
     */
    public void runUS722() throws Exception {
        ListOfHouseGridsDto listOfHouseGrid = mGetEnergyConsumptionController.getListOfHouseGrid();
        if (Validations.printHouseGridListAsMenu(listOfHouseGrid)) {
            int houseGridChosen = Validations.verifyIntegerInputsWithLimits(0, listOfHouseGrid.getListOfHouseGridDto().size() - 1);
            List<LocalDateTime> readingList = insertDates(1);
            double result = mGetEnergyConsumptionController.getEnergyConsumptionInOneHouseGrid(listOfHouseGrid.getListOfHouseGridDto().get(houseGridChosen), readingList.get(0), readingList.get(1));
            if (result <= 0)
                System.out.println(NO_READINGS_PERIOD);
            else {
                System.out.println("The total energy consumption of the house grid chosen is: " + Math.round(result) + UNIT);
                System.out.println("\n");
            }
        }
    }

    /**
     * @param readingIntervalOption
     * @return
     * @throws Exception
     */
    public List<LocalDateTime> insertDates(int readingIntervalOption) throws Exception {
        List<LocalDateTime> dateList = new ArrayList<>();

        LocalDateTime initialDate = LocalDateTime.now();
        LocalDateTime finalDate = LocalDateTime.now().minusDays(1);

        while (finalDate.isBefore(initialDate) || Math.abs(finalDate.until(initialDate, ChronoUnit.MINUTES)) < Configurations.getReadingInterval()[readingIntervalOption]) {
            dateList.clear();
            System.out.println("Please enter the first date");
            initialDate = Validations.checkLocalDateTimeInput();
            dateList.add(initialDate);

            System.out.println("Please enter the second date");
            finalDate = Validations.checkLocalDateTimeInput();
            dateList.add(finalDate);

            verifyDateLimits(finalDate, initialDate);
            verifyDateWithFile(finalDate, initialDate, readingIntervalOption);
        }
        return dateList;
    }


}
