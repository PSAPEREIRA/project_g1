package pt.ipp.isep.dei.project1.model.interfaces;

import java.util.List;

public interface Programmable {

    List<String[]> getListOfPrograms();
    void setListOfPrograms(List<String[]> listOfPrograms);
    void createProgramAndAddTo(String nameOfProgram, double energyCons);
    void editSelectedProgramAndField(int programIndex, int fieldIndex, String newValue);
    void deleteProgram(int programIndex);



}
