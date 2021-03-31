package pt.ipp.isep.dei.project1.io.ui.geographicarea;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pt.ipp.isep.dei.project1.controllers.geographicarea.ImportGeosFromFileControllerV2;
import pt.ipp.isep.dei.project1.io.ui.utils.Validations;
import pt.ipp.isep.dei.project1.readers.FileReader;

import java.util.ArrayList;
import java.util.List;

@Component
public class ImportGeosFromFileUIV2 {


    @Autowired
    private ImportGeosFromFileControllerV2 controllerV2;

    public void run() {

        System.out.println("This functionality can read the following types of files: ");

        List<FileReader> fileReadersObjects = new ArrayList<>();
        try {
            fileReadersObjects = controllerV2.getAvailableReaderTypesObjects();
        } catch (Exception e) {
        e.printStackTrace();
    }

        for (FileReader fileReader : fileReadersObjects) {
            System.out.println(fileReader.getReaderType());
            System.out.println("-----------------------------");
        }
        /**
         * C:\Users\Ivo Guerra\Documents\Ivo\Switch\2Semestre\DSOFT\DataSet_sprint04_GA.json
         * C:\Users\Ivo Guerra\Documents\Ivo\Switch\2Semestre\DSOFT\DataSet_sprint05_GA.xml
         * C:\Users\Ivo Guerra\Documents\Ivo\Switch\2Semestre\DSOFT\ReadingsXml.xml
         C:\switchprojects\project_g1\src\test\resources\DataSet_sprint06_GA.json
         */


        System.out.println("Now please insert the path where to import from!");
        System.out.println(" ");
        System.out.println("Please mind that to be valid, the path needs to respect the following format:");
        System.out.println("Example: drive\\target\\..\\filename.extension");
        String pathInput = Validations.readString();

        List<String> geoNames = null;
        try {
            geoNames = controllerV2.importFromFile(pathInput);
        } catch (Exception e) {
            e.printStackTrace();
        }


        for (String list : geoNames) {
            System.out.println();
            System.out.println(list);
        }

    }

}