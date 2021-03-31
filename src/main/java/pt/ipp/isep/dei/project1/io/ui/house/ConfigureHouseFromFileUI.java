package pt.ipp.isep.dei.project1.io.ui.house;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pt.ipp.isep.dei.project1.controllers.house.ConfigureHouseFromFileController;
import pt.ipp.isep.dei.project1.io.ui.utils.Validations;
import pt.ipp.isep.dei.project1.readers.FileReader;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Component
public class ConfigureHouseFromFileUI {

    @Autowired
    private ConfigureHouseFromFileController ctrl;

    private long millis;


    public void run() {


        System.out.println("This functionality can read the following types of files: ");

        List<FileReader> fileReadersObjects = new ArrayList<>();
        try {
            fileReadersObjects = ctrl.getAvailableReaderTypesObjects();
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(fileReadersObjects.get(0).getReaderType());
        System.out.println("-----------------------------");


        System.out.println("Now please insert the path where to import from!");
        System.out.println(" ");
        System.out.println("Please mind that to be valid, the path needs to respect the following format:");
        System.out.println("Example: drive\\target\\..\\filename.extension");
        String pathInput = Validations.readString();

        List<String> outPut = null;

        try {
            LocalDateTime start = LocalDateTime.now();
            outPut = ctrl.importHouseFromFile(pathInput);
            LocalDateTime end = LocalDateTime.now();
            Duration dur = Duration.between(start, end);
            millis = dur.toMillis();

        } catch (Exception e) {
            e.printStackTrace();
        }

        for (String list : outPut) {
            System.out.println(list);
        }
        if (outPut != null && outPut.size() > 10) {

            System.out.println("This operation took " + millis + " milliseconds");

        }


    }
}
