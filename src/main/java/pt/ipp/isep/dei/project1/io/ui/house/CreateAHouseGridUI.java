package pt.ipp.isep.dei.project1.io.ui.house;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pt.ipp.isep.dei.project1.controllers.house.CreateAHouseGridController;
import pt.ipp.isep.dei.project1.io.ui.utils.Validations;

@Component
public class CreateAHouseGridUI {

    @Autowired
    private CreateAHouseGridController createAHouseGridController;

    /**ReaderOfSensorReadings the code and power of one house grid
     *
     */
    public void run() {
        System.out.println("What the code of new house Grid?");
        String codeOfHg = Validations.readString();
        System.out.println("What the power of the new HouseGrid?");
        double powerOfHg = Validations.verifyDoubleInputsPositive();
        if (createAHouseGridController.createNewHouseGrid(codeOfHg, powerOfHg))
            System.out.println("House Grid created! ");
        else System.out.println("Impossible to create, this House Grid already exists");
    }
}

