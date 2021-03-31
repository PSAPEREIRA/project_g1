package pt.ipp.isep.dei.project1.model.interfaces;

import pt.ipp.isep.dei.project1.model.sensor.ListOfReadings;


public interface Device{

    String getName();
    boolean isActive();
    boolean deactivateDevice();
    void setName(String mName);
    DeviceSpecs getDeviceSpecs();
    DeviceType getDeviceType();
    ListOfReadings getListOfReadings();
    void setListOfReadings(ListOfReadings listOfReadings);
    boolean checkIfIsProgrammable();




}
