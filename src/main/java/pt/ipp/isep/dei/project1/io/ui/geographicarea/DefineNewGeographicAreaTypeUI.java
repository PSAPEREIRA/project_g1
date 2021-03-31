package pt.ipp.isep.dei.project1.io.ui.geographicarea;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pt.ipp.isep.dei.project1.controllers.geographicarea.DefineNewGeographicAreaTypeController;
import pt.ipp.isep.dei.project1.io.ui.utils.Validations;

@Component
public class DefineNewGeographicAreaTypeUI {

    @Autowired
    private DefineNewGeographicAreaTypeController mDefineNewGeographicAreaTypeController;


    public void run() {
        do {
            System.out.println("Please enter the new Geographic Area Type:");
            String name = Validations.readString();
            if (mDefineNewGeographicAreaTypeController.newGeographicAreaType(name)) {
                System.out.println("Added!");
            } else System.out.println("This type already exists");
        }
            while (Validations.checkContinueOrBreak());
        }

}

