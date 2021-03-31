package pt.ipp.isep.dei.project1.model.device.stove;

import pt.ipp.isep.dei.project1.model.interfaces.DeviceType;

public class StoveType implements DeviceType {

    private static final String TYPE = "Stove";


    @Override
    public String getType() {
        return TYPE;
    }

    @Override
    public Stove createNewDevice (String nameOfDevice) {
        return new Stove (nameOfDevice,  new StoveSpec(), this);

    }

    public String getTypeCode(){
        return "STOVE";
    }


}
