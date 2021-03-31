package pt.ipp.isep.dei.project1.io.ui.geographicarea;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pt.ipp.isep.dei.project1.controllers.geographicarea.ImportSensorsReadingsController;
import pt.ipp.isep.dei.project1.io.ui.utils.Validations;
import pt.ipp.isep.dei.project1.readers.FileReader;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class ImportSensorsReadingsUI {

    @Autowired
    private ImportSensorsReadingsController mUS020;

    private static final String SEPARATOR = "-------------------------------------------------";

    public void run(int option) throws Exception {

        System.out.println("This functionality can read the following types of files: ");

        List<FileReader> fileReadersObjects = new ArrayList<>();
        try {
            fileReadersObjects = mUS020.getListOfReadersOfSensorReadings();
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (FileReader fileReader : fileReadersObjects) {
            System.out.println(fileReader.getReaderType());
            System.out.println("-----------------------------");
        }
        System.out.println("Now please insert the path where to import from!");
        System.out.println(" ");
        System.out.println("Please mind that to be valid, the path needs to respect the following format:");
        System.out.println("Example: drive\\target\\..\\filename.extension");
        boolean error;
        String pathInput;
        LocalDateTime start;
        List<Integer> list = new ArrayList<>();
        do {
            error = false;
            pathInput = Validations.readString();
            start = LocalDateTime.now();
            try {
                if (option == 6)
                    list = mUS020.addReadingsToSensorsOfGA(pathInput);
                else
                    list = mUS020.addReadingsToSensorsOfHouse(pathInput);
            } catch (Exception e) {
                System.out.println("Invalid path/file!");
                System.out.println("Please insert a valid file name and path\n");
                error = true;
            }
        }
        while (error);
        checkLinesRead(start, list, fileReadersObjects);
    }

    public void checkLinesRead(LocalDateTime start, List<Integer> list, List<FileReader> fileReadersObjects) {
        if (list.isEmpty()) {
            System.out.println(" ");
            System.out.println("Can't read this extension!");
            System.out.println("This functionality can read the following types of files: ");
            for (FileReader fileReader2 : fileReadersObjects) {
                System.out.println(fileReader2.getReaderType());
                System.out.println(SEPARATOR);
            }
        } else if (list.get(0) == -1) {
            System.out.println("Invalid path/file!");
            System.out.println("Please insert a valid file name and path\n");
        } else {
            System.out.println("File imported with Success!");
            System.out.println(SEPARATOR);
            System.out.println("Information read by imported file:");
            System.out.println(SEPARATOR);
            System.out.println(list.get(0) + " read lines from file");
            System.out.println(list.get(1) + " reading(s) added to sensor(s)");
            System.out.println(list.get(2) + " reading(s) impossible to add");

            LocalDateTime end = LocalDateTime.now();
            Duration seconds = Duration.between(start, end);
            System.out.println(SEPARATOR);
            System.out.println("This operation took " + seconds.toMillis() + " milliseconds");
        }
    }

}


