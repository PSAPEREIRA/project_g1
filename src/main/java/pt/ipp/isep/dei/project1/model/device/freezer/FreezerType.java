package pt.ipp.isep.dei.project1.model.device.freezer;

import pt.ipp.isep.dei.project1.model.interfaces.DeviceType;

public class FreezerType implements DeviceType {

    private static final String TYPE = "Freezer";

    @Override
    public String getType() {
        return TYPE;
    }

    @Override
    public Freezer createNewDevice(String nameOfDevice) {
        return new Freezer(nameOfDevice,new FreezerSpec(),this);
    }

    public String getTypeCode(){
        return "FRZR";
    }
}

