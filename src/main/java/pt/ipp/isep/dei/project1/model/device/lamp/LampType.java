package pt.ipp.isep.dei.project1.model.device.lamp;

import pt.ipp.isep.dei.project1.model.interfaces.DeviceType;

public class LampType implements DeviceType {

    private static final String TYPE = "Lamp";

    @Override
    public String getType() {
        return TYPE;
    }

    @Override
    public Lamp createNewDevice(String nameOfDevice) {
        return new Lamp(nameOfDevice,new LampSpec(),this);
    }

    public String getTypeCode(){
        return "LAMP";
    }
}
