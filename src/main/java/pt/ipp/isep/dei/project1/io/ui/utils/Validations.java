package pt.ipp.isep.dei.project1.io.ui.utils;

import pt.ipp.isep.dei.project1.dto.device.ListOfDevicesDto;
import pt.ipp.isep.dei.project1.dto.geographicareadto.ListGeographicAreaDto;
import pt.ipp.isep.dei.project1.dto.geographicareadto.ListOfGeographicAreaTypesDto;
import pt.ipp.isep.dei.project1.dto.housedto.ListOfHouseGridsDto;
import pt.ipp.isep.dei.project1.dto.housedto.ListOfRoomsDto;
import pt.ipp.isep.dei.project1.dto.sensordto.ListOfRoomSensorsDto;
import pt.ipp.isep.dei.project1.dto.sensordto.ListOfSensorTypesDto;
import pt.ipp.isep.dei.project1.dto.sensordto.ListOfAreaSensorsDto;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public final class Validations {

    private static final String ACTION_1 = "Please, insert a number!";
    private static final String ACTION_2 = "Please, insert a number from the available options!";
    private static final String ACTION_3 = "------------------------------------------------------------------------------";
    private static final String ACTION_4 = "Are you sure you want to delete? (y/n):";

    private Validations() {
        throw new IllegalStateException("Utility class");
    }

    public static int verifyIntegerInputsWithLimits(int limInf, int limSup) {
        int input = -1;
        boolean error;
        do {
            error = false;
            Scanner read = new Scanner(System.in);
            try {
                input = read.nextInt();
                if (input < limInf || input > limSup)
                    System.out.println(ACTION_2);
            } catch (InputMismatchException e) {
                System.out.println(ACTION_1);
                error = true;
                read.nextLine();
            }
        } while (error || input < limInf || input > limSup);
        return input;
    }

    public static double verifyDoubleInputsPositive() {
        double input = -1;
        boolean error;
        do {
            error = false;
            Scanner read = new Scanner(System.in);
            try {
                input = read.nextDouble();
                if (input < 0)
                    System.out.println("Please, insert a number equal or greater than 0");
            } catch (InputMismatchException e) {
                System.out.println(ACTION_1);
                error = true;
                read.nextLine();
            }
        } while (error || input < 0);
        return input;
    }


    public static double verifyDoubleInputs() {
        double input = -1;
        boolean error;
        do {
            error = false;
            Scanner read = new Scanner(System.in);
            try {
                input = read.nextDouble();
            } catch (InputMismatchException e) {
                System.out.println(ACTION_1);
                error = true;
                read.nextLine();
            }
        } while (error);
        return input;
    }


    public static String readString() {
        Scanner read = new Scanner(System.in);
        String name = "";
        while ("".equals(name))
            name = read.nextLine();
        return name;
    }

    public static boolean printStringListAsMenu(List<String> inputList, String element) {
        if (inputList.isEmpty()) {
            System.out.println("No " + element + " found");
            return false;
        } else {
            System.out.println("Choose the " + element + " you want: ");
            for (int i = 0; i < inputList.size(); i++)
                System.out.println(i + " - " + inputList.get(i));
        }
        return true;
    }


    public static void printListOfStringArray(List<String[]> list) {  //Imprime lista de ArraysString de 2 posicoes

        if (list.isEmpty()) {
            System.out.println("No elements recorded");
        } else

            for (int i = 0; i < list.size(); i++) {
                System.out.println(list.get(i)[0] + " " + list.get(i)[1]);
            }
    }

    public static boolean checkWantToDelete() {
        String contOrDelete = "y";
        while ("Y".equalsIgnoreCase(contOrDelete)) {
            System.out.println(ACTION_4);
            contOrDelete = Validations.readString();
            while (!"y".equalsIgnoreCase(contOrDelete) || !"n".equalsIgnoreCase(contOrDelete)) {
                if ("y".equalsIgnoreCase(contOrDelete))
                    return true;
                else if ("n".equalsIgnoreCase(contOrDelete)) {
                    System.out.println("Device not removed!");
                    return false;
                } else {
                    System.out.println(ACTION_4);
                    contOrDelete = Validations.readString();
                }
            }
        }
        return true;

    }

    public static boolean checkWantToRemove() {
        String contOrRemove = "y";
        while ("Y".equalsIgnoreCase(contOrRemove)) {
            System.out.println("Are you sure you want to remove? (y/n):");
            contOrRemove = Validations.readString();
            while (!"y".equalsIgnoreCase(contOrRemove) || !"n".equalsIgnoreCase(contOrRemove)) {
                if ("y".equalsIgnoreCase(contOrRemove))
                    return true;
                else if ("n".equalsIgnoreCase(contOrRemove)) {
                    System.out.println("AreaSensor not removed!");
                    return false;
                } else {
                    System.out.println(ACTION_4);
                    contOrRemove = Validations.readString();
                }
            }
        }
        return true;

    }

    public static boolean checkWantToDeactivate() {
        String goOrNot = "Y";
        while ("Y".equalsIgnoreCase(goOrNot)) {
            System.out.println("Are you sure you want to deactivate? (y/n):");
            goOrNot = Validations.readString();
            while (!"y".equalsIgnoreCase(goOrNot) || !"n".equalsIgnoreCase(goOrNot)) {

                if ("y".equalsIgnoreCase(goOrNot))
                    return true;
                else if ("N".equalsIgnoreCase(goOrNot)) {
                    System.out.println("Device not deactivated!");
                    return false;
                } else {
                    System.out.println("Are you sure you want to deactivate? (y/n):");
                    goOrNot = Validations.readString();

                }
            }
        }
        return true;

    }

    public static boolean checkContinueOrBreak() {
        String continueOrBreak = "Y";
        while ("Y".equalsIgnoreCase(continueOrBreak)) {
            System.out.println("Want to continue and define another? (Y/n):");
            continueOrBreak = Validations.readString();
            while (!"y".equalsIgnoreCase(continueOrBreak) || !"n".equalsIgnoreCase(continueOrBreak)) {

                if ("y".equalsIgnoreCase(continueOrBreak))
                    return true;
                else if ("n".equalsIgnoreCase(continueOrBreak))
                    return false;
                else {
                    System.out.println("Want to continue and define another? (Y/n):");
                    continueOrBreak = Validations.readString();

                }
            }
        }
        return true;

    }

    public static boolean validateFiles(int[] input) {
        if (1440 % input[0] != 0 || 1440 % input[1] != 0) {
            System.out.println("Device Reading Interval is invalid");
            return true;
        } else if (input[0] % input[1] != 0 || input[0] < input[1]) {
            System.out.println("Device Reading must a multiple of Grid Reading");
            return true;
        }
        return false;
    }


    public static boolean verifyDateLimits(LocalDateTime finalDate, LocalDateTime initialDate) {
        if (finalDate.isBefore(initialDate)) {
            System.out.println("Second date should be after first date.");
            System.out.println("Please insert other date.");
            System.out.println(ACTION_3 );
            return true;
        }
        return false;
    }

    public static boolean verifyDateWithFile(LocalDateTime finalDate, LocalDateTime initialDate, int readingOption) throws IOException {
        if (Math.abs(finalDate.until(initialDate, ChronoUnit.MINUTES)) < Configurations.getReadingInterval()[readingOption]) {
            System.out.println("Minimum period of Reading is: " + Configurations.getReadingInterval()[readingOption] + " minutes.");
            System.out.println("Please insert other date.");
            System.out.println(ACTION_3);
            return true;
        }
        return false;
    }

    public static LocalDate checkLocalDateInput() {

        LocalDate localDate = LocalDate.now();
        System.out.println("Insert year of the date");
        int yearChoice = Validations.verifyIntegerInputsWithLimits(1990, localDate.getYear());
        System.out.println("Insert month of the date");
        int monthChoice = Validations.verifyIntegerInputsWithLimits(1, 12);
        System.out.println("Insert day of the date");
        int dayChoice = Validations.verifyIntegerInputsWithLimits(1, 31);
        return LocalDate.of(yearChoice, monthChoice, dayChoice);
    }

    public static LocalDateTime checkLocalDateTimeInput() {

        LocalDate localDate = LocalDate.now();
        System.out.println("Insert year");
        int yearChoice = Validations.verifyIntegerInputsWithLimits(1990, localDate.getYear());
        System.out.println("Insert month");
        int monthChoice = Validations.verifyIntegerInputsWithLimits(1, 12);
        System.out.println("Insert day");
        int dayChoice = Validations.verifyIntegerInputsWithLimits(1, 31);
        System.out.println("Insert hour");
        int hourChoice = Validations.verifyIntegerInputsWithLimits(0, 24);
        System.out.println("Insert minutes");
        int minutesChoice = Validations.verifyIntegerInputsWithLimits(0, 60);
        return LocalDateTime.of(yearChoice, monthChoice, dayChoice, hourChoice, minutesChoice);
    }


    public static boolean printHouseGridListAsMenu(ListOfHouseGridsDto inputList) {
        if (inputList.getListOfHouseGridDto().isEmpty()) {
            System.out.println("No house grid found");
            return false;
        } else {
            System.out.println("Choose the house grid  you want: ");
            for (int i = 0; i < inputList.getListOfHouseGridDto().size(); i++)
                System.out.println(i + " - " + inputList.getListOfHouseGridDto().get(i).getCode());
        }
        return true;
    }

    public static boolean printRoomListAsMenu(ListOfRoomsDto inputList) {
        if (inputList.getRoomDto().isEmpty()) {
            System.out.println("No room found!");
            return false;
        } else {
            System.out.println("Choose the room you want: ");
            for (int i = 0; i < inputList.getRoomDto().size(); i++)
                System.out.println(i + " - " + inputList.getRoomDto().get(i).getName());
        }
        return true;
    }

    public static boolean printListAsMenu(List<String> inputList,String element) {
        if (inputList.isEmpty()) {
            System.out.println("No "+element+" found!");
            return false;
        } else {
            System.out.println("Choose the "+element+" you want: ");
            for (int i = 0; i < inputList.size(); i++)
                System.out.println(i + " - " + inputList.get(i));
        }
        return true;
    }

    public static boolean printDeviceListAsMenu(ListOfDevicesDto inputList) {
        if (inputList.getListOfDevices().isEmpty()) {
            System.out.println("No device found");
            return false;
        } else {
            System.out.println("Choose the device you want: ");
            for (int i = 0; i < inputList.getListOfDevices().size(); i++)
                System.out.println(i + " - " + inputList.getListOfDevices().get(i).getName());
        }
        return true;
    }

    public static boolean printGAListAsMenu(ListGeographicAreaDto inputList) {
        if (inputList.getListOfGADto().isEmpty()) {
            System.out.println("No geographic area found");
            return false;
        } else {
            System.out.println("Choose the geographic area you want: ");
            for (int i = 0; i < inputList.getListOfGADto().size(); i++)
                System.out.println(i + " - " + inputList.getListOfGADto().get(i).getName());
        }
        return true;
    }

    public static boolean printGATypeListAsMenu(ListOfGeographicAreaTypesDto inputList) {
        if (inputList.getListOfGATypesDto().isEmpty()) {
            System.out.println("No geographic area type found");
            return false;
        } else {
            System.out.println("Choose the geographic area type you want: ");
            for (int i = 0; i < inputList.getListOfGATypesDto().size(); i++)
                System.out.println(i + " - " + inputList.getListOfGATypesDto().get(i).getName());
        }
        return true;
    }

    public static boolean printSensorTypeListAsMenu(ListOfSensorTypesDto inputList) {
        if (inputList.getSensorTypeDtos().isEmpty()) {
            System.out.println("No sensor type found");
            return false;
        } else {
            System.out.println("Choose sensor type you want: ");
            for (int i = 0; i < inputList.getSensorTypeDtos().size(); i++)
                System.out.println(i + " - " + inputList.getSensorTypeDtos().get(i).getDesignation());
        }
        return true;
    }

    public static boolean printSensorListAsMenu(ListOfAreaSensorsDto inputList) {
        if (inputList.getListOfAreaSensorDto().isEmpty()) {
            System.out.println("No sensor found");
            return false;
        } else {
            System.out.println("Choose sensor you want: ");
            for (int i = 0; i < inputList.getListOfAreaSensorDto().size(); i++)
                System.out.println(i + " - " + inputList.getListOfAreaSensorDto().get(i).getName());
        }
        return true;
    }


    public static boolean printRoomSensorListAsMenu(ListOfRoomSensorsDto inputList) {
        if (inputList.getListOfRoomSensorDto().isEmpty()) {
            System.out.println("No sensor found");
            return false;
        } else {
            System.out.println("Choose sensor you want: ");
            for (int i = 0; i < inputList.getListOfRoomSensorDto().size(); i++)
                System.out.println(i + " - " + inputList.getListOfRoomSensorDto().get(i).getName());
        }
        return true;
    }


    public static List<Double> defineLocation() {
        List<Double> listOfUserInputsVerified = new ArrayList<>();
        System.out.println("Enter latitude:");
        double latitudeInput = Validations.verifyDoubleInputs();
        listOfUserInputsVerified.add(latitudeInput);
        System.out.println("Enter longitude:");
        double longitudeInput = Validations.verifyDoubleInputs();
        listOfUserInputsVerified.add(longitudeInput);
        System.out.println("Enter altitude:");
        double altitudeInput = Validations.verifyDoubleInputs();
        listOfUserInputsVerified.add(altitudeInput);
        return listOfUserInputsVerified;
    }


}

