package pt.ipp.isep.dei.project1.model.device;

import lombok.Getter;
import lombok.Setter;
import pt.ipp.isep.dei.project1.model.interfaces.DeviceSpecs;
import pt.ipp.isep.dei.project1.model.interfaces.DeviceType;
import pt.ipp.isep.dei.project1.model.interfaces.Programmable;

import java.util.ArrayList;
import java.util.List;

public class GeneralDeviceProgrammable extends GeneralDevice implements Programmable {

    @Getter @Setter
    private List<String[]> listOfPrograms = new ArrayList<>();



    public GeneralDeviceProgrammable(String name, DeviceSpecs deviceSpecs, DeviceType deviceType) {
        super(name,deviceSpecs,deviceType);
    }

    @Override
    public void editSelectedProgramAndField(int programIndex, int fieldIndex, String newValue) {
        listOfPrograms.get(programIndex)[fieldIndex] = newValue;

    }

    @Override
    public void deleteProgram(int programIndex) {
        listOfPrograms.remove(programIndex);
    }


    @Override
    public void createProgramAndAddTo(String nameOfProg, double energyCons) {
        listOfPrograms.add(new String[]{nameOfProg, String.valueOf(energyCons)});
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof GeneralDeviceProgrammable))
            return false;
        GeneralDeviceProgrammable generalDevice = (GeneralDeviceProgrammable) obj;
        return (this.getName().equals(generalDevice.getName()));
    }

    @Override
    public int hashCode() {
        return this.getName().charAt(0);
    }
}
