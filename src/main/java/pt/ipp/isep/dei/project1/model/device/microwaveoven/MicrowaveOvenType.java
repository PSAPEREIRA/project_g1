package pt.ipp.isep.dei.project1.model.device.microwaveoven;

import pt.ipp.isep.dei.project1.model.interfaces.DeviceType;

public class MicrowaveOvenType implements DeviceType {

    private static final String TYPE = "MicrowaveOven";


    @Override
    public String getType() {
        return TYPE;
    }

    @Override
    public MicrowaveOven createNewDevice (String nameOfDevice) {
        return new MicrowaveOven (nameOfDevice,  new MicrowaveOvenSpec(), this);

    }

    public String getTypeCode(){
        return "MICROWAVE";
    }
}

