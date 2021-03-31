package pt.ipp.isep.dei.project1.model.device.kettler;

import pt.ipp.isep.dei.project1.model.interfaces.DeviceType;

public class KettlerType implements DeviceType {

    private static final String TYPE = "Kettler";

    @Override
    public String getType() {
        return TYPE;
    }

    @Override
    public Kettler createNewDevice(String nameOfDevice) {
        return new Kettler(nameOfDevice, new KettleSpec(), this);
    }

    public String getTypeCode() {
        return "KLR";
    }

}
