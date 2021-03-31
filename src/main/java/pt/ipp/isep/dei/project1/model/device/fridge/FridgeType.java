package pt.ipp.isep.dei.project1.model.device.fridge;

import pt.ipp.isep.dei.project1.model.interfaces.DeviceType;

public class FridgeType implements DeviceType {

    private static final String TYPE = "Fridge";

    @Override
    public String getType() {
        return TYPE;
    }

    @Override
    public Fridge createNewDevice(String nameOfDevice) {
        return new Fridge(nameOfDevice,new FridgeSpec(),this);
    }

    public String getTypeCode(){
        return "FRDG";
    }
}
