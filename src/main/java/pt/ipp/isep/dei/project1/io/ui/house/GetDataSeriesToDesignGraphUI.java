package pt.ipp.isep.dei.project1.io.ui.house;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pt.ipp.isep.dei.project1.controllers.house.GetDataSeriesToDesignGraphController;
import pt.ipp.isep.dei.project1.dto.device.ListOfDevicesDto;
import pt.ipp.isep.dei.project1.dto.housedto.ListOfHouseGridsDto;
import pt.ipp.isep.dei.project1.dto.housedto.ListOfRoomsDto;
import pt.ipp.isep.dei.project1.io.ui.utils.Validations;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

@Component
public class GetDataSeriesToDesignGraphUI {

    @Autowired
    private GetDataSeriesToDesignGraphController getDataSeriesCTRL;
    private static final String ACTION= "Please click any key to advance";
    private static final String ESTABILISH_PERIOD = "Now lets establish the period to get readings from:";
    private static final String START_PERIOD = "Lets start by defining the start of the period date/time";
    private static final String END_PERIOD = "And now lets establish when the period ends:";

    /**
     * US730 As a Power User [or Administrator],
     * I want to have the data series necessary to design an energy consumption chart of the metered energy consumption
     * of a device/room/grid in a given time interval.
     */

    public void run() {

        System.out.println("So you want data series to make a graph hein?");
        System.out.println("Please select the source of data you want to receive from:");
        System.out.println("1 - Device");
        System.out.println("2 - Room");
        System.out.println("3 - Grid");
        System.out.println("0 - Bye");

        Scanner read = new Scanner(System.in);

        int input = Validations.verifyIntegerInputsWithLimits(0, 3);
        ListOfDevicesDto listOfAllDevices = getDataSeriesCTRL.getListOfAllDevicesInsideHouse();
        ListOfRoomsDto listOfRooms = getDataSeriesCTRL.getListOfRooms();
        ListOfHouseGridsDto listOfHouseGrid = getDataSeriesCTRL.getListOfHouseGrid();

        switch (input) {
            case 1:
                Validations.printDeviceListAsMenu(listOfAllDevices);
                actionDevice(listOfAllDevices);
                System.out.println(ACTION);
                read.nextLine();
                break;

            case 2:
                Validations.printRoomListAsMenu(listOfRooms);
                actionRoom(listOfRooms);
                System.out.println(ACTION);
                read.nextLine();
                break;

            case 3:
                Validations.printHouseGridListAsMenu(listOfHouseGrid);
                actionHouseGrid(listOfHouseGrid);
                System.out.println(ACTION);
                read.nextLine();

                break;

            case 0:
                break;

            default:
                break;

        }

    }

    public void actionDevice(ListOfDevicesDto listOfDevices){
        int indexSelectedDevice = Validations.verifyIntegerInputsWithLimits(0, listOfDevices.getListOfDevices().size() - 1);
        System.out.println(ESTABILISH_PERIOD);
        System.out.println(START_PERIOD);
        LocalDateTime startDate = Validations.checkLocalDateTimeInput();
        System.out.println(END_PERIOD);
        LocalDateTime endDate = Validations.checkLocalDateTimeInput();
        List<String[]> finalList = getDataSeriesCTRL.getReadingsOfDeviceOnPeriodAsString(indexSelectedDevice, startDate, endDate);
        Validations.printListOfStringArray(finalList);
    }

    public void actionRoom(ListOfRoomsDto listOfRooms){
        int indexRoomSelected = Validations.verifyIntegerInputsWithLimits(0, listOfRooms.getRoomDto().size() - 1);
        System.out.println(ESTABILISH_PERIOD);
        System.out.println(START_PERIOD);
        LocalDateTime startDate = Validations.checkLocalDateTimeInput();
        System.out.println(END_PERIOD);
        LocalDateTime endDate = Validations.checkLocalDateTimeInput();
        Validations.printListOfStringArray(getDataSeriesCTRL.getReadingsOfRoomOnPeriodAsString(listOfRooms.getRoomDto().get(indexRoomSelected), startDate, endDate));

    }

    public void actionHouseGrid(ListOfHouseGridsDto listOfHouseGrid){
        int indexSelectedHouseGrid = Validations.verifyIntegerInputsWithLimits(0, listOfHouseGrid.getListOfHouseGridDto().size() - 1);
        System.out.println(ESTABILISH_PERIOD);
        System.out.println(START_PERIOD);
        LocalDateTime startDate = Validations.checkLocalDateTimeInput();
        System.out.println(END_PERIOD);
        LocalDateTime endDate = Validations.checkLocalDateTimeInput();
        Validations.printListOfStringArray(getDataSeriesCTRL.getReadingsOfHouseGridOnPeriodAsString(listOfHouseGrid.getListOfHouseGridDto().get(indexSelectedHouseGrid), startDate, endDate));

    }
}
