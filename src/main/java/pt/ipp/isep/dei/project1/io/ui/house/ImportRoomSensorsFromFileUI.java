package pt.ipp.isep.dei.project1.io.ui.house;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pt.ipp.isep.dei.project1.controllers.house.ImportRoomSensorsFromFileController;
import pt.ipp.isep.dei.project1.io.ui.utils.Validations;
import pt.ipp.isep.dei.project1.readers.FileReader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class ImportRoomSensorsFromFileUI {

    @Autowired
    private ImportRoomSensorsFromFileController importRoomSensorsFromFileController;


    public void run() {
        System.out.println("This functionality can read the following types of files: ");

        List<FileReader> fileReadersObjects = new ArrayList<>();
        try {
            fileReadersObjects = importRoomSensorsFromFileController.getAvailableReaderTypesObjects();
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("-----------------------------");
        for (FileReader fileReader : fileReadersObjects) {
            System.out.println(fileReader.getReaderType());
            System.out.println("-----------------------------");
        }


        System.out.println("Now please insert the path where to import from!");
        System.out.println(" ");
        System.out.println("Please mind that to be valid, the path needs to respect the following format:");
        System.out.println("Example: drive\\target\\..\\filename.extension");

        String path = Validations.readString();
        List<String[]> result = new ArrayList<>();
        int error = 0;
        try {
            result = importRoomSensorsFromFileController.importSensorsToHouseRooms(path);
        } catch (IOException e) {
            System.out.println("Invalid path/file!");
            System.out.println("Please insert a valid file name and path.");
            error = 1;
        }
        if (error == 0) {
            System.out.println("File read with Success!");
            System.out.println("-------------------------------------------------");
            System.out.println("Information read by imported file:");
            System.out.println("-------------------------------------------------");
            System.out.println(result.get(result.size() - 1)[0] + result.get(result.size() - 1)[1]);
            System.out.println(result.get(result.size() - 2)[0] + result.get(result.size() - 2)[1]);
            System.out.println(" ");
            for (int i = 0; i < result.size() - 2; i++) {
                System.out.println("RoomSensor: " + result.get(i)[1] + " added to Room: " + result.get(i)[0]);
            }
        }
    }

}