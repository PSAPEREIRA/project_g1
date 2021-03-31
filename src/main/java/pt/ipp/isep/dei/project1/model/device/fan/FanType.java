package pt.ipp.isep.dei.project1.model.device.fan;

import pt.ipp.isep.dei.project1.model.interfaces.DeviceType;

public class FanType implements DeviceType {

    private static final String TYPE = "Fan";


    @Override
    public String getType() {
        return TYPE;
    }

    @Override
    public Fan createNewDevice (String nameOfDevice) {
        return new Fan (nameOfDevice,  new FanSpec(), this);

    }

    public String getTypeCode(){
        return "FAN";
    }
}

