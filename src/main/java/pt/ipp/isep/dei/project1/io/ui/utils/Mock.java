package pt.ipp.isep.dei.project1.io.ui.utils;

import pt.ipp.isep.dei.project1.model.device.ListOfDevices;
import pt.ipp.isep.dei.project1.model.device.dishwasher.Dishwasher;
import pt.ipp.isep.dei.project1.model.device.dishwasher.DishwasherSpec;
import pt.ipp.isep.dei.project1.model.device.dishwasher.DishwasherType;
import pt.ipp.isep.dei.project1.model.device.eletricwaterheater.ElectricWaterHeater;
import pt.ipp.isep.dei.project1.model.device.eletricwaterheater.ElectricWaterHeaterSpec;
import pt.ipp.isep.dei.project1.model.device.eletricwaterheater.ElectricWaterHeaterType;
import pt.ipp.isep.dei.project1.model.device.washingmachine.WashingMachine;
import pt.ipp.isep.dei.project1.model.device.washingmachine.WashingMachineSpec;
import pt.ipp.isep.dei.project1.model.device.washingmachine.WashingMachineType;
import pt.ipp.isep.dei.project1.model.geographicarea.GeographicArea;
import pt.ipp.isep.dei.project1.model.geographicarea.GeographicAreaType;
import pt.ipp.isep.dei.project1.model.geographicarea.Location;
import pt.ipp.isep.dei.project1.model.geographicarea.OccupationArea;
import pt.ipp.isep.dei.project1.model.house.*;
import pt.ipp.isep.dei.project1.model.sensor.ListOfReadings;
import pt.ipp.isep.dei.project1.model.sensor.Reading;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


public final class Mock {

    private Mock() {
        throw new UnsupportedOperationException();
    }
    private static final String CLASSROOM = "classroom";

    public static List<String[]> mockListOfDevicesDishwasher() {
        String[] program1 = new String[2];
        program1[0] = "Glasses";
        program1[1] = "0.9";

        String[] program2 = new String[2];
        program2[0] = "Echo";
        program2[1] = "1.3";

        String[] program3 = new String[3];
        program3[0] = "Echo turbo";
        program3[1] = "1.7";

        String[] program4 = new String[3];
        program4[0] = "Dishes";
        program4[1] = "2.1";

        List<String[]> listOfProgramsDishwasher = new ArrayList<>();

        listOfProgramsDishwasher.add(program1);
        listOfProgramsDishwasher.add(program2);
        listOfProgramsDishwasher.add(program3);
        listOfProgramsDishwasher.add(program4);

        return listOfProgramsDishwasher;


    }

    public static List<String[]> mockListOfDevicesWashingMachine() {

        String[] program5 = new String[2];
        program5[0] = "Wool";
        program5[1] = "0.9";

        String[] program6 = new String[2];
        program6[0] = "Fast";
        program6[1] = "1.3";

        String[] program7 = new String[2];
        program7[0] = "Fast Plus";
        program7[1] = "1.7";

        String[] program8 = new String[2];
        program8[0] = "Synthetic";
        program8[1] = "2.1";

        List<String[]> listOfProgramsWashingMachine = new ArrayList<>();

        listOfProgramsWashingMachine.add(program5);
        listOfProgramsWashingMachine.add(program6);
        listOfProgramsWashingMachine.add(program7);
        listOfProgramsWashingMachine.add(program8);

        return listOfProgramsWashingMachine;
    }


    public static ListOfDevices mockListOfDevicesB107() throws IOException {

        //B109


        List<String[]> listOfProgramsDishwasher = mockListOfDevicesDishwasher();
        List<String[]> listOfProgramsWashingMachine = mockListOfDevicesWashingMachine();

        ListOfDevices listOfDevices = new ListOfDevices();

        ElectricWaterHeater electricWaterHeaterB107 = new ElectricWaterHeater("EHW B107", new ElectricWaterHeaterSpec(1.5, 100, 55, 0.91),new ElectricWaterHeaterType());
        Dishwasher dishwasherB107 = new Dishwasher("Dishwasher B107", new DishwasherSpec(1.5,10), new DishwasherType());
        WashingMachine washingMachineB107 = new WashingMachine("Washing Machine B107", new WashingMachineSpec(), new WashingMachineType());
        dishwasherB107.setListOfPrograms(listOfProgramsDishwasher);
        washingMachineB107.setListOfPrograms(listOfProgramsWashingMachine);

        listOfDevices.addDeviceToList(electricWaterHeaterB107);
        listOfDevices.addDeviceToList(dishwasherB107);
        listOfDevices.addDeviceToList(washingMachineB107);
        electricWaterHeaterB107.setListOfReadings(mockListToEHWB107());
        dishwasherB107.setListOfReadings(mockListToDISWB107());
        washingMachineB107.setListOfReadings(mockListToWASMB107());
        return listOfDevices;
    }


    public static ListOfDevices mockListOfDevicesB109() throws IOException {


        List<String[]> listOfProgramsDishwasher = mockListOfDevicesDishwasher();

        List<String[]> listOfProgramsWashingMachine = mockListOfDevicesWashingMachine();

        ListOfDevices listOfDevices = new ListOfDevices();

        ElectricWaterHeater electricWaterHeaterB109 = new ElectricWaterHeater("EHW B109", new ElectricWaterHeaterSpec(2.0, 100, 55, 0.91),new ElectricWaterHeaterType());
        Dishwasher dishwasherB109 = new Dishwasher("DishwasherSpec B109", new DishwasherSpec(1.5,10), new DishwasherType());
        WashingMachine washingMachineB109 = new WashingMachine("Washing Machine B109", new WashingMachineSpec(),new WashingMachineType());

        dishwasherB109.setListOfPrograms(listOfProgramsDishwasher);
        washingMachineB109.setListOfPrograms(listOfProgramsWashingMachine);
        listOfDevices.addDeviceToList(electricWaterHeaterB109);
        listOfDevices.addDeviceToList(dishwasherB109);
        listOfDevices.addDeviceToList(washingMachineB109);
        electricWaterHeaterB109.setListOfReadings(mockListToEWHB109());
        washingMachineB109.setListOfReadings(mockListToWASMB109());
        return listOfDevices;
    }


    public static ListOfDevices mockListOfDevicesB106() {


        List<String[]> listOfPrograms = mockListOfPrograms();

        ListOfDevices listOfDevices = new ListOfDevices();
        ElectricWaterHeater electricWaterHeaterB106 = new ElectricWaterHeater("EHW B106", new ElectricWaterHeaterSpec(2.2,150,55,0.92),new ElectricWaterHeaterType());
        Dishwasher dishwasherB106 = new Dishwasher("Dishwasher B106", new DishwasherSpec(1.4,10), new DishwasherType());

        dishwasherB106.setListOfPrograms(listOfPrograms);
        listOfDevices.addDeviceToList(electricWaterHeaterB106);
        listOfDevices.addDeviceToList(dishwasherB106);

        return listOfDevices;
    }

    public static List<String[]> mockListOfPrograms() {
        String[] program1 = new String[2];
        program1[0] = "Glasses";
        program1[1] = "0.8";

        String[] program2 = new String[2];
        program2[0] = "Light";
        program2[1] = "1.3";

        String[] program3 = new String[2];
        program2[0] = "Light Turbo";
        program2[1] = "1.9";

        String[] program4 = new String[2];
        program3[0] = "Dishes";
        program3[1] = "2.3";

        List<String[]> listOfPrograms = new ArrayList<>();

        listOfPrograms.add(program1);
        listOfPrograms.add(program2);
        listOfPrograms.add(program3);
        listOfPrograms.add(program4);

        return listOfPrograms;
    }


    public static ListOfReadings mockListOfReadingsOfTemperature() {
        ListOfReadings listOfReadingsTemperature = new ListOfReadings();
        listOfReadingsTemperature.addReading(new Reading(LocalDateTime.of(2018, 12, 30, 00, 00), 14));
        listOfReadingsTemperature.addReading(new Reading(LocalDateTime.of(2018, 12, 30, 00, 00), 13.7));
        listOfReadingsTemperature.addReading(new Reading(LocalDateTime.of(2018, 12, 30, 00, 00), 16.5));
        listOfReadingsTemperature.addReading(new Reading(LocalDateTime.of(2018, 12, 30, 00, 00), 15.1));
        listOfReadingsTemperature.addReading(new Reading(LocalDateTime.of(2018, 12, 31, 00, 00), 13.8));
        listOfReadingsTemperature.addReading(new Reading(LocalDateTime.of(2018, 12, 31, 00, 00), 13.3));
        listOfReadingsTemperature.addReading(new Reading(LocalDateTime.of(2018, 12, 31, 00, 00), 15.5));
        listOfReadingsTemperature.addReading(new Reading(LocalDateTime.of(2018, 12, 31, 00, 00), 14.2));
        listOfReadingsTemperature.addReading(new Reading(LocalDateTime.of(2019, 01, 01, 00, 00), 12.5));
        listOfReadingsTemperature.addReading(new Reading(LocalDateTime.of(2019, 01, 01, 00, 00), 12.4));
        listOfReadingsTemperature.addReading(new Reading(LocalDateTime.of(2019, 01, 01, 00, 00), 13.8));
        listOfReadingsTemperature.addReading(new Reading(LocalDateTime.of(2019, 01, 01, 00, 00), 12.9));
        listOfReadingsTemperature.addReading(new Reading(LocalDateTime.of(2019, 01, 02, 00, 00), 11.5));
        listOfReadingsTemperature.addReading(new Reading(LocalDateTime.of(2019, 01, 02, 00, 00), 11.2));
        listOfReadingsTemperature.addReading(new Reading(LocalDateTime.of(2019, 01, 02, 00, 00), 13.5));
        listOfReadingsTemperature.addReading(new Reading(LocalDateTime.of(2019, 01, 02, 00, 00), 12.8));
        listOfReadingsTemperature.addReading(new Reading(LocalDateTime.of(2018, 01, 02, 10, 00), 16.5));


        return listOfReadingsTemperature;
    }

    public static ListOfReadings mockListOfReadingsOfRainfall() {
        ListOfReadings listOfReadingsRainfall = new ListOfReadings();

        LocalDateTime date17 = LocalDateTime.of(2018, 12, 29, 00, 00);
        Reading r17 = new Reading(date17, 14.0);
        LocalDateTime date18 = LocalDateTime.of(2018, 12, 30, 00, 00);
        Reading r18 = new Reading(date18, 13.7);
        LocalDateTime date19 = LocalDateTime.of(2018, 12, 31, 00, 00);
        Reading r19 = new Reading(date19, 16.5);
        LocalDateTime date20 = LocalDateTime.of(2019, 01, 01, 00, 00);
        Reading r20 = new Reading(date20, 15.1);
        LocalDateTime date21 = LocalDateTime.of(2019, 02, 02, 00, 00);
        Reading r21 = new Reading(date21, 13.8);
        LocalDateTime date22 = LocalDateTime.of(2019, 03, 03, 00, 00);
        Reading r22 = new Reading(date22, 13.3);
        LocalDateTime date23 = LocalDateTime.of(2019, 04, 04, 00, 00);
        Reading r23 = new Reading(date23, 15.5);

        listOfReadingsRainfall.addReading(r17);
        listOfReadingsRainfall.addReading(r18);
        listOfReadingsRainfall.addReading(r19);
        listOfReadingsRainfall.addReading(r20);
        listOfReadingsRainfall.addReading(r21);
        listOfReadingsRainfall.addReading(r22);
        listOfReadingsRainfall.addReading(r23);

        return listOfReadingsRainfall;
    }


    public static ListOfReadings mockListToEHWB107() throws IOException {
        ListOfReadings listOfReadingsEHW = new ListOfReadings();
        LocalDateTime dateEHW1 = LocalDateTime.of(2018, 12, 31, 8, 0);
        Reading rEHW1 = new Reading(dateEHW1, 0.2);
        LocalDateTime dateEHW2 = LocalDateTime.of(2018, 12, 31, 8, 15);
        Reading rEHW2 = new Reading(dateEHW2, 0.375);
        LocalDateTime dateEHW3 = LocalDateTime.of(2018, 12, 31, 8, 30);
        Reading rEHW3 = new Reading(dateEHW3, 0.375);
        LocalDateTime dateEHW4 = LocalDateTime.of(2018, 12, 31, 9, 00);
        Reading rEHW4 = new Reading(dateEHW4, 0.375);
        LocalDateTime dateEHW5 = LocalDateTime.of(2018, 12, 31, 9, 15);
        Reading rEHW5 = new Reading(dateEHW5, 0.375);
        LocalDateTime dateEHW6 = LocalDateTime.of(2018, 12, 31, 9, 30);
        Reading rEHW6 = new Reading(dateEHW6, 0.25);
        LocalDateTime dateEHW7 = LocalDateTime.of(2018, 12, 31, 10, 30);
        Reading rEHW7 = new Reading(dateEHW7, 0.2);
        LocalDateTime dateEHW8 = LocalDateTime.of(2018, 12, 31, 10, 45);
        Reading rEHW8 = new Reading(dateEHW8, 0.2);
        LocalDateTime dateEHW9 = LocalDateTime.of(2018, 12, 31, 12, 00);
        Reading rEHW9 = new Reading(dateEHW9, 0.2);
        LocalDateTime dateEHW10 = LocalDateTime.of(2018, 12, 31, 12, 15);
        Reading rEHW10 = new Reading(dateEHW10, 0.375);
        LocalDateTime dateEHW11 = LocalDateTime.of(2018, 12, 31, 12, 30);
        Reading rEHW11 = new Reading(dateEHW11, 0.375);
        LocalDateTime dateEHW12 = LocalDateTime.of(2018, 12, 31, 12, 45);
        Reading rEHW12 = new Reading(dateEHW12, 0.375);
        LocalDateTime dateEHW13 = LocalDateTime.of(2018, 12, 31, 13, 00);
        Reading rEHW13 = new Reading(dateEHW13, 0.375);
        LocalDateTime dateEHW14 = LocalDateTime.of(2018, 12, 31, 13, 15);
        Reading rEHW14 = new Reading(dateEHW14, 0.2);
        LocalDateTime dateEHW15 = LocalDateTime.of(2018, 12, 31, 20, 15);
        Reading rEHW15 = new Reading(dateEHW15, 0.1);
        LocalDateTime dateEHW16 = LocalDateTime.of(2018, 12, 31, 20, 30);
        Reading rEHW16 = new Reading(dateEHW16, 0.375);
        LocalDateTime dateEHW17 = LocalDateTime.of(2018, 12, 31, 20, 45);
        Reading rEHW17 = new Reading(dateEHW17, 0.375);
        LocalDateTime dateEHW18 = LocalDateTime.of(2018, 12, 31, 21, 00);
        Reading rEHW18 = new Reading(dateEHW18, 0.375);
        LocalDateTime dateEHW19 = LocalDateTime.of(2018, 12, 31, 21, 15);
        Reading rEHW19 = new Reading(dateEHW19, 0.15);

        listOfReadingsEHW.addReadingToDeviceListOfReadings(rEHW1, true);
        listOfReadingsEHW.addReadingToDeviceListOfReadings(rEHW2, true);
        listOfReadingsEHW.addReadingToDeviceListOfReadings(rEHW3, true);
        listOfReadingsEHW.addReadingToDeviceListOfReadings(rEHW4, true);
        listOfReadingsEHW.addReadingToDeviceListOfReadings(rEHW5, true);
        listOfReadingsEHW.addReadingToDeviceListOfReadings(rEHW6, true);
        listOfReadingsEHW.addReadingToDeviceListOfReadings(rEHW7, true);
        listOfReadingsEHW.addReadingToDeviceListOfReadings(rEHW8, true);
        listOfReadingsEHW.addReadingToDeviceListOfReadings(rEHW9, true);
        listOfReadingsEHW.addReadingToDeviceListOfReadings(rEHW10, true);
        listOfReadingsEHW.addReadingToDeviceListOfReadings(rEHW11, true);
        listOfReadingsEHW.addReadingToDeviceListOfReadings(rEHW12, true);
        listOfReadingsEHW.addReadingToDeviceListOfReadings(rEHW13, true);
        listOfReadingsEHW.addReadingToDeviceListOfReadings(rEHW14, true);
        listOfReadingsEHW.addReadingToDeviceListOfReadings(rEHW15, true);
        listOfReadingsEHW.addReadingToDeviceListOfReadings(rEHW16, true);
        listOfReadingsEHW.addReadingToDeviceListOfReadings(rEHW17, true);
        listOfReadingsEHW.addReadingToDeviceListOfReadings(rEHW18, true);
        listOfReadingsEHW.addReadingToDeviceListOfReadings(rEHW19, true);


        return listOfReadingsEHW;
    }


    public static ListOfReadings mockListToDISWB107() throws IOException {
        ListOfReadings listOfReadingsDISW = new ListOfReadings();
        LocalDateTime dateDW1 = LocalDateTime.of(2018, 12, 31, 13, 00);
        Reading rDishWash1 = new Reading(dateDW1, 0.2);
        LocalDateTime dateDW2 = LocalDateTime.of(2018, 12, 31, 13, 15);
        Reading rDishWash2 = new Reading(dateDW2, 0.3);
        LocalDateTime dateDW3 = LocalDateTime.of(2018, 12, 31, 13, 30);
        Reading rDishWash3 = new Reading(dateDW3, 0.2);
        LocalDateTime dateDW4 = LocalDateTime.of(2018, 12, 31, 13, 45);
        Reading rDishWash4 = new Reading(dateDW4, 0.2);
        LocalDateTime dateDW5 = LocalDateTime.of(2018, 12, 31, 20, 15);
        Reading rDishWash5 = new Reading(dateDW5, 0.2);
        LocalDateTime dateDW6 = LocalDateTime.of(2018, 12, 31, 21, 15);
        Reading rDishWash6 = new Reading(dateDW6, 0.1);
        LocalDateTime dateDW7 = LocalDateTime.of(2018, 12, 31, 21, 30);
        Reading rDishWash7 = new Reading(dateDW7, 0.1);
        LocalDateTime dateDW8 = LocalDateTime.of(2018, 12, 31, 21, 45);
        Reading rDishWash8 = new Reading(dateDW8, 0.375);
        LocalDateTime dateDW9 = LocalDateTime.of(2018, 12, 31, 22, 00);
        Reading rDishWash9 = new Reading(dateDW9, 0.375);
        LocalDateTime dateDW10 = LocalDateTime.of(2018, 12, 31, 22, 15);
        Reading rDishWash10 = new Reading(dateDW10, 0.2);


        listOfReadingsDISW.addReadingToDeviceListOfReadings(rDishWash1, true);
        listOfReadingsDISW.addReadingToDeviceListOfReadings(rDishWash2, true);
        listOfReadingsDISW.addReadingToDeviceListOfReadings(rDishWash3, true);
        listOfReadingsDISW.addReadingToDeviceListOfReadings(rDishWash4, true);
        listOfReadingsDISW.addReadingToDeviceListOfReadings(rDishWash5, true);
        listOfReadingsDISW.addReadingToDeviceListOfReadings(rDishWash6, true);
        listOfReadingsDISW.addReadingToDeviceListOfReadings(rDishWash7, true);
        listOfReadingsDISW.addReadingToDeviceListOfReadings(rDishWash8, true);
        listOfReadingsDISW.addReadingToDeviceListOfReadings(rDishWash9, true);
        listOfReadingsDISW.addReadingToDeviceListOfReadings(rDishWash10, true);


        return listOfReadingsDISW;
    }


    public static ListOfReadings mockListToWASMB107() throws IOException {
        ListOfReadings listOfReadingsWASM = new ListOfReadings();
        LocalDateTime dateWasMach1 = LocalDateTime.of(2018, 12, 31, 10, 30);
        Reading rWasMach1 = new Reading(dateWasMach1, 0.4);
        LocalDateTime dateWasMach2 = LocalDateTime.of(2018, 12, 31, 10, 45);
        Reading rWasMach2 = new Reading(dateWasMach2, 0.2);
        LocalDateTime dateWasMach3 = LocalDateTime.of(2018, 12, 31, 11, 00);
        Reading rWasMach3 = new Reading(dateWasMach3, 0.25);
        LocalDateTime dateWasMach4 = LocalDateTime.of(2018, 12, 31, 11, 15);
        Reading rWasMach4 = new Reading(dateWasMach4, 0.25);


        listOfReadingsWASM.addReadingToDeviceListOfReadings(rWasMach1, true);
        listOfReadingsWASM.addReadingToDeviceListOfReadings(rWasMach2, true);
        listOfReadingsWASM.addReadingToDeviceListOfReadings(rWasMach3, true);
        listOfReadingsWASM.addReadingToDeviceListOfReadings(rWasMach4, true);


        return listOfReadingsWASM;
    }


    public static ListOfReadings mockListToEWHB109() throws IOException {
        ListOfReadings listOfReadingsEHW = new ListOfReadings();
        LocalDateTime dateRoom109EHW1 = LocalDateTime.of(2018, 12, 31, 8, 0);
        Reading rEWHeater1 = new Reading(dateRoom109EHW1, 0.2);
        LocalDateTime dateRoom109EHW2 = LocalDateTime.of(2018, 12, 31, 8, 15);
        Reading rEWHeater2 = new Reading(dateRoom109EHW2, 0.5);
        LocalDateTime dateRoom109EHW3 = LocalDateTime.of(2018, 12, 31, 8, 30);
        Reading rEWHeater3 = new Reading(dateRoom109EHW3, 0.5);
        LocalDateTime dateRoom109EHW4 = LocalDateTime.of(2018, 12, 31, 8, 45);
        Reading rEWHeater4 = new Reading(dateRoom109EHW4, 0.5);
        LocalDateTime dateRoom109EHW5 = LocalDateTime.of(2018, 12, 31, 9, 00);
        Reading rEWHeater5 = new Reading(dateRoom109EHW5, 0.5);
        LocalDateTime dateRoom109EHW6 = LocalDateTime.of(2018, 12, 31, 9, 15);
        Reading rEWHeater6 = new Reading(dateRoom109EHW6, 0.25);
        LocalDateTime dateRoom109EHW7 = LocalDateTime.of(2018, 12, 31, 10, 30);
        Reading rEWHeater7 = new Reading(dateRoom109EHW7, 0.2);
        LocalDateTime dateRoom109EHW8 = LocalDateTime.of(2018, 12, 31, 10, 45);
        Reading rEWHeater8 = new Reading(dateRoom109EHW8, 0.2);
        LocalDateTime dateRoom109EHW9 = LocalDateTime.of(2018, 12, 31, 12, 00);
        Reading rEWHeater9 = new Reading(dateRoom109EHW9, 0.2);
        LocalDateTime dateRoom109EHW10 = LocalDateTime.of(2018, 12, 31, 12, 00);
        Reading rEWHeater10 = new Reading(dateRoom109EHW10, 0.2);
        LocalDateTime dateRoom109EHW11 = LocalDateTime.of(2018, 12, 31, 12, 15);
        Reading rEWHeater11 = new Reading(dateRoom109EHW11, 0.5);
        LocalDateTime dateRoom109EHW12 = LocalDateTime.of(2018, 12, 31, 12, 30);
        Reading rEWHeater12 = new Reading(dateRoom109EHW12, 0.5);
        LocalDateTime dateRoom109EHW13 = LocalDateTime.of(2018, 12, 31, 12, 45);
        Reading rEWHeater13 = new Reading(dateRoom109EHW13, 0.5);
        LocalDateTime dateRoom109EHW14 = LocalDateTime.of(2018, 12, 31, 13, 00);
        Reading rEWHeater14 = new Reading(dateRoom109EHW14, 0.5);
        LocalDateTime dateRoom109EHW15 = LocalDateTime.of(2018, 12, 31, 13, 15);
        Reading rEWHeater15 = new Reading(dateRoom109EHW15, 0.2);
        LocalDateTime dateRoom109EHW16 = LocalDateTime.of(2018, 12, 31, 20, 15);
        Reading rEWHeater16 = new Reading(dateRoom109EHW16, 0.1);
        LocalDateTime dateRoom109EHW17 = LocalDateTime.of(2018, 12, 31, 20, 30);
        Reading rEWHeater17 = new Reading(dateRoom109EHW17, 0.5);
        LocalDateTime dateRoom109EHW18 = LocalDateTime.of(2018, 12, 31, 20, 45);
        Reading rEWHeater18 = new Reading(dateRoom109EHW18, 0.5);
        LocalDateTime dateRoom109EHW19 = LocalDateTime.of(2018, 12, 31, 21, 00);
        Reading rEWHeater19 = new Reading(dateRoom109EHW19, 0.5);
        LocalDateTime dateRoom109EHW20 = LocalDateTime.of(2018, 12, 31, 21, 15);
        Reading rEWHeater20 = new Reading(dateRoom109EHW20, 0.15);

        listOfReadingsEHW.addReadingToDeviceListOfReadings(rEWHeater1, true);
        listOfReadingsEHW.addReadingToDeviceListOfReadings(rEWHeater2, true);
        listOfReadingsEHW.addReadingToDeviceListOfReadings(rEWHeater3, true);
        listOfReadingsEHW.addReadingToDeviceListOfReadings(rEWHeater4, true);
        listOfReadingsEHW.addReadingToDeviceListOfReadings(rEWHeater5, true);
        listOfReadingsEHW.addReadingToDeviceListOfReadings(rEWHeater6, true);
        listOfReadingsEHW.addReadingToDeviceListOfReadings(rEWHeater7, true);
        listOfReadingsEHW.addReadingToDeviceListOfReadings(rEWHeater8, true);
        listOfReadingsEHW.addReadingToDeviceListOfReadings(rEWHeater9, true);
        listOfReadingsEHW.addReadingToDeviceListOfReadings(rEWHeater10, true);
        listOfReadingsEHW.addReadingToDeviceListOfReadings(rEWHeater11, true);
        listOfReadingsEHW.addReadingToDeviceListOfReadings(rEWHeater12, true);
        listOfReadingsEHW.addReadingToDeviceListOfReadings(rEWHeater13, true);
        listOfReadingsEHW.addReadingToDeviceListOfReadings(rEWHeater14, true);
        listOfReadingsEHW.addReadingToDeviceListOfReadings(rEWHeater15, true);
        listOfReadingsEHW.addReadingToDeviceListOfReadings(rEWHeater16, true);
        listOfReadingsEHW.addReadingToDeviceListOfReadings(rEWHeater17, true);
        listOfReadingsEHW.addReadingToDeviceListOfReadings(rEWHeater18, true);
        listOfReadingsEHW.addReadingToDeviceListOfReadings(rEWHeater19, true);
        listOfReadingsEHW.addReadingToDeviceListOfReadings(rEWHeater20, true);

        return listOfReadingsEHW;
    }


    public static ListOfReadings mockListToWASMB109() throws IOException {
        ListOfReadings listOfReadingsWASM = new ListOfReadings();
        LocalDateTime date1 = LocalDateTime.of(2018, 12, 31, 10, 15);
        Reading r1 = new Reading(date1, 0.4);
        LocalDateTime date2 = LocalDateTime.of(2018, 12, 31, 10, 30);
        Reading r2 = new Reading(date2, 0.2);
        LocalDateTime date3 = LocalDateTime.of(2018, 12, 31, 10, 45);
        Reading r3 = new Reading(date3, 0.2);
        LocalDateTime date4 = LocalDateTime.of(2018, 12, 31, 11, 00);
        Reading r4 = new Reading(date4, 0.25);
        LocalDateTime date5 = LocalDateTime.of(2018, 12, 31, 11, 15);
        Reading r5 = new Reading(date5, 0.25);

        listOfReadingsWASM.addReadingToDeviceListOfReadings(r1, true);
        listOfReadingsWASM.addReadingToDeviceListOfReadings(r2, true);
        listOfReadingsWASM.addReadingToDeviceListOfReadings(r3, true);
        listOfReadingsWASM.addReadingToDeviceListOfReadings(r4, true);
        listOfReadingsWASM.addReadingToDeviceListOfReadings(r5, true);

        return listOfReadingsWASM;
    }



 /**public static ListOfSensors mockListOfListOfSensorsToRoom() {

        ListOfReadings listOfReadingsTemperature = mockListOfReadingsOfTemperature();
        LocalDate instalationDate = LocalDate.of(2016, 11, 15);
        AreaSensor sensor = new AreaSensor("Temperature B109", new Location(0, 0, 0), new SensorType(TEMPERATURE), instalationDate, "Celsius");

        sensor.setListOfReadings(listOfReadingsTemperature);

        ListOfSensors listOfSensors = new ListOfSensors();
        listOfSensors.addSensorToList(sensor);

        return listOfSensors;
    }
**/

    public static House mockOfHouse() {

        Address address = new Address("Rua Dr António Bernardino de Almeida, 431","4200-072","Porto","123","Portugal");

        String id = "ISEP";
        String description = "Campus do ISEP";
        OccupationArea occupationArea = new OccupationArea(new Location(0, 0, 0), 10, 10);
        GeographicAreaType geographicAreaType = new GeographicAreaType("urban area");
        GeographicArea geographicArea = new GeographicArea(id, description, occupationArea, geographicAreaType);

        return new House("Edifício B", new Location(0, 0, 0), geographicArea.getName(), address);
    }

    public static List<Room> mockListOfRooms() throws IOException {
        ArrayList<Double> dimensions = new ArrayList<>();
        dimensions.add(7.0);
        dimensions.add(11.0);
        dimensions.add(3.5);

        Room room1 = new Room("B107",CLASSROOM, 1, dimensions.get(0),dimensions.get(1),dimensions.get(2));
        Room room2 = new Room("B109",CLASSROOM, 1, dimensions.get(0),dimensions.get(1),dimensions.get(2));
        Room room3 = new Room("B106",CLASSROOM, 1, dimensions.get(0),dimensions.get(1),dimensions.get(2));

        room1.setListOfDevices(mockListOfDevicesB107());
        room2.setListOfDevices(mockListOfDevicesB109());
        room3.setListOfDevices(mockListOfDevicesB106());

     /**room2.setListOfAreaSensors(Mock.mockListOfListOfSensorsToRoom());**/

        List<Room> listOfRooms = new ArrayList<>();
        listOfRooms.add(room1);
        listOfRooms.add(room2);
        listOfRooms.add(room3);


        return listOfRooms;
    }



}

