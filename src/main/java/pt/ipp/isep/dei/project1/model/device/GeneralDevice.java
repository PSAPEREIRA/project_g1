package pt.ipp.isep.dei.project1.model.device;

import pt.ipp.isep.dei.project1.model.interfaces.*;
import pt.ipp.isep.dei.project1.model.sensor.ListOfReadings;
import java.time.LocalDateTime;


public class GeneralDevice implements Device,Metered {

    private String name;
    private final DeviceSpecs deviceSpecs;
    private final DeviceType deviceType;
    private ListOfReadings listOfReadings = new ListOfReadings();
    private boolean isActive = true;

    public GeneralDevice(String name, DeviceSpecs deviceSpecs, DeviceType deviceType) {
        this.name = name;
        this.deviceSpecs = deviceSpecs;
        this.deviceType = deviceType;
    }


    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean deactivateDevice() {
        isActive = false;
        return true;
    }

    @Override
    public DeviceSpecs getDeviceSpecs() {
        return deviceSpecs;
    }

    @Override
    public DeviceType getDeviceType() {
        return deviceType;
    }

    @Override
    public ListOfReadings getListOfReadings() {
        return listOfReadings;
    }

    @Override
    public boolean isActive() {
        return isActive;
    }

    @Override
    public boolean checkIfIsProgrammable() {
        return this instanceof Programmable;
    }

    public boolean checkIfIsMetered() {
        return this instanceof Metered;
    }


    @Override
    public void setListOfReadings(ListOfReadings listOfReadings) {
        this.listOfReadings = listOfReadings;
    }

    @Override
    public double getNominalPower() {
        return deviceSpecs.getAttributeValue("nominal power");
    }

    @Override
    public double getConsumption(LocalDateTime initialDate, LocalDateTime finalDate) throws Exception {
        return listOfReadings.sumOfReadingsOnInterval(initialDate,finalDate);
    }

    @Override
    public ListOfReadings getDataSeries(LocalDateTime startDate, LocalDateTime endDate) {
        return listOfReadings.getReadingsOnIntervalCutDuplicates(startDate, endDate);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof GeneralDevice))
            return false;
        GeneralDevice generalDevice = (GeneralDevice) obj;
        return (this.name.equals(generalDevice.getName()));
    }
    @Override
    public int hashCode() {
        return this.name.charAt(0);
    }
}
