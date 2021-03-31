package pt.ipp.isep.dei.project1.model.device.electricoven;

import pt.ipp.isep.dei.project1.model.interfaces.DeviceType;

public class ElectricOvenType implements DeviceType {

    private static final String TYPE = "ElectricOven";

    @Override
    public String getType() {
        return TYPE;
    }

    @Override
    public ElectricOven createNewDevice(String nameOfDevice) {
        return new ElectricOven(nameOfDevice, new ElectricOvenSpec(), this);

    }

    public String getTypeCode() {
        return "EH";
    }
}
