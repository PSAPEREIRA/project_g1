package pt.ipp.isep.dei.project1.model.device.winecooler;

import pt.ipp.isep.dei.project1.model.interfaces.DeviceType;

public class WineCoolerType implements DeviceType {

    private static final String TYPE = "Wine cooler";

    @Override
    public String getType() {
        return TYPE;
    }

    @Override
    public WineCooler createNewDevice(String nameOfDevice) {
        return new WineCooler(nameOfDevice,new WineCoolerSpec(),this);
    }

    public String getTypeCode(){
        return "WC";
    }
}
